package com.example.ldapsms.demo.controller;

import com.example.demo.dto.PinDTO;
import com.example.demo.dto.RootDTO;
import com.example.demo.entities.AcessoEntity;
import com.example.demo.entities.AcessoEntityRepository;
import com.example.demo.entities.PinEntity;
import com.example.demo.entities.PinEntityRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/acesso")
public class AcessoController {


    @Autowired
    AcessoEntityRepository acessoEntityRepository;

    @Autowired
    PinEntityRepository pinEntityRepository;

    @PostMapping("/acesso")
    String acesso(@RequestBody RootDTO acesso) throws NoSuchAlgorithmException {
        System.out.println(acesso);

        final String pin = RandomStringUtils.randomNumeric(6);

        PinEntity pinEntity = new PinEntity();
        pinEntity.setMobile(acesso.getMobile());
        pinEntity.setPin(pin);
        pinEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));

        pinEntityRepository.save(pinEntity);


        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        AcessoEntity rootEntity = modelMapper.map(acesso, AcessoEntity.class);

        final AcessoEntity save = acessoEntityRepository.save(rootEntity);
        System.out.println(save);


        return pin;
    }

    @PostMapping("/pin")
    AcessoEntity pin(@RequestBody PinDTO pin, @RequestHeader("mobile") Long mobile) {

        LocalDateTime dateTime = LocalDateTime.now().minus(Duration.of(1, ChronoUnit.MINUTES));
        Date tmfn = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());


        Optional<PinEntity> pinEntity = pinEntityRepository.findByMobileAndTimestampAfter(Long.valueOf(mobile), new Timestamp(tmfn.getTime()));

       if(pinEntity.isPresent()) {
           if (pinEntity.get().getPin().equals(pin.getPin())) {
               AcessoEntity acessoEntity = acessoEntityRepository.findFirstByMobileOrderByIdDesc(mobile);
               acessoEntity.setPin(pin.getPin());
               acessoEntity.setUsuarioAD("user");
               acessoEntity.setPasswordAD(RandomStringUtils.randomAscii(10));
               acessoEntityRepository.save(acessoEntity);
               pinEntityRepository.delete(pinEntity.get());
               return acessoEntity;
           }
           throw new RuntimeException("pin errado");
       }
       throw new RuntimeException("mobile inexistente");
    }


}
