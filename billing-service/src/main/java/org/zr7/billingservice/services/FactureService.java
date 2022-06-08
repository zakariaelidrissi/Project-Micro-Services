package org.zr7.billingservice.services;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.zr7.billingservice.entities.Facture;

import java.util.function.Consumer;

@Service
public class FactureService {
    @Bean
    public Consumer<Facture> factureConsumer(){
        return (input)->{
            System.out.println("*******************************");
            System.out.println(input.getFactureID());
            System.out.println(input.getClientName());
            System.out.println(input.getFacturePrice());
            System.out.println("*******************************");
        };
    }
}
