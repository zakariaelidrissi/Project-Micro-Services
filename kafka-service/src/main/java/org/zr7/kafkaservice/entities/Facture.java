package org.zr7.kafkaservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Facture {
    private Long factureID;
    private String nameClient;
    private double facturePrice;
}
