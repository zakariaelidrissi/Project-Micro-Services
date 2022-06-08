package org.zr7.kafkaservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.*;
import org.zr7.kafkaservice.entities.Facture;
import org.zr7.kafkaservice.entities.FactureEntity;
import org.zr7.kafkaservice.repository.FactureRepository;

import java.util.Random;

@RestController
public class FactureRestController {

    @Autowired
    private StreamBridge streamBridge;
    @Autowired
    //private FactureRepository factureRepository;

    @GetMapping(path = "/publish/{topic}/{name}")
    public Facture publish(@PathVariable String topic, @PathVariable String name){
        Facture facture = new Facture(1L, name, new Random().nextInt(6000));
        streamBridge.send(topic, facture);

        return facture;
    }



    // Data Base

    //@RequestMapping(value = "/factures", method = RequestMethod.POST)
    /*
    public void save(FactureEntity fe){
        factureRepository.save(fe);
    }*/

}
