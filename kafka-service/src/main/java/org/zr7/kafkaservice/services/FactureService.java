package org.zr7.kafkaservice.services;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.zr7.kafkaservice.entities.FactureEntity;
import org.zr7.kafkaservice.repository.FactureRepository;

import java.awt.geom.QuadCurve2D;
import java.time.Duration;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
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

    @Bean
    public Function<KStream<String, FactureEntity>, KStream<String, Long>> kStreamFunction(){
        return (input)->{
            return input
                    .map((k,v)->new KeyValue<>(v.getClientName(), v.getFactureID()))
                    .groupBy((k,v)->k, Grouped.with(Serdes.String(), Serdes.Long()))
                    .count()
                    .toStream();
        };
    }

}
