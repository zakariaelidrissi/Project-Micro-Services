package org.zr7.kafkaservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.zr7.kafkaservice.entities.FactureEntity;
import org.zr7.kafkaservice.repository.FactureRepository;

import java.util.Random;

@SpringBootApplication
public class KafkaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaServiceApplication.class, args);
    }

    //Bean
    CommandLineRunner start(FactureRepository factureRepository, RepositoryRestConfiguration restConfiguration){
        restConfiguration.exposeIdsFor(FactureEntity.class);
        return args -> {
            factureRepository.save(new FactureEntity(null, "Zakaria", new Random().nextInt(7000)));
            factureRepository.save(new FactureEntity(null, "Jamal", new Random().nextInt(7000)));
            factureRepository.save(new FactureEntity(null, "Hamza", new Random().nextInt(7000)));
            factureRepository.findAll().forEach(c->{
                System.out.println(c.toString());
            });
        };
    }

}
