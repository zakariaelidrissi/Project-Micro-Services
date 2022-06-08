package org.zr7.kafkaservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.zr7.kafkaservice.entities.FactureEntity;
import org.zr7.kafkaservice.repository.FactureRepository;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
public class FactureService {

    @Autowired
    private FactureRepository factureRepository;

    @Bean
    public Consumer<FactureEntity> factureConsumer(){
        return (input)->{
            System.out.println(input.toString());
            FactureEntity factureEntity = new FactureEntity(null, input.getClientName(), input.getFacturePrice());
            factureRepository.save(factureEntity);
            System.out.println("************* Saved *************");
        };
    }

    @Bean
    public Supplier<FactureEntity> factureSupplier(){
        return ()-> new FactureEntity(
                null,
                Math.random() > 0.5?"zaki":"jamal",
                new Random().nextInt(2000)
        );
    }
}
