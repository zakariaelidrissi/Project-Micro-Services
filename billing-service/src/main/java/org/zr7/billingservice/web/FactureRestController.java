package org.zr7.billingservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.zr7.billingservice.entities.Bill;
import org.zr7.billingservice.entities.Facture;
import org.zr7.billingservice.feign.CustomerRestClient;
import org.zr7.billingservice.feign.ProductItemRestClient;
import org.zr7.billingservice.model.Customer;
import org.zr7.billingservice.model.Product;
import org.zr7.billingservice.repository.BillRepository;

@RestController
public class FactureRestController {
    @Autowired
    private StreamBridge streamBridge;
    private BillRepository billRepository;
    private CustomerRestClient customerRestClient;
    private ProductItemRestClient productItemRestClient;

    @GetMapping("/publish/{topic}/{id}")
    public Facture publish(@PathVariable String topic, @PathVariable Long id){

        Bill bill = billRepository.findById(id).get();
        Customer customer = customerRestClient.getCustomerById(bill.getCustomerID());

        final double[] priceT = {0};
        bill.getProductItems().forEach(pi->{
            Product product = productItemRestClient.getProductById(pi.getProductID());
            priceT[0] += product.getPrice();
        });

        Facture facture = new Facture(id,customer.getName(), priceT[0]);

        streamBridge.send(topic, facture);

        return facture;
    }
}
