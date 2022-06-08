package org.zr7.kafkaservice.services;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.zr7.kafkaservice.entities.Facture;
import org.zr7.kafkaservice.entities.FactureEntity;
import org.zr7.kafkaservice.repository.FactureRepository;
import org.zr7.kafkaservice.web.FactureRestController;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
public class FactureService {

    //private FactureRestController factureRestController;
    private FactureRepository factureRepository;

    @Bean
    public Consumer<Facture> factureConsumer(){
        return (input)->{
            factureRepository.save(new FactureEntity(null, input.getNameClient(), input.getFacturePrice()));
            System.out.println(input.toString());
            System.out.println("************* Saved *************");
        };
    }

    @Bean
    public Supplier<Facture> factureSupplier(){
        return ()-> new Facture(
                1L,
                Math.random() > 0.5?"zaki":"jamal",
                new Random().nextInt(2000)
        );
    }
}
