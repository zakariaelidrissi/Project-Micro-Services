package org.zr7.billingservice;

import org.hibernate.mapping.Collection;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;
import org.zr7.billingservice.entities.Bill;
import org.zr7.billingservice.entities.ProductItem;
import org.zr7.billingservice.feign.CustomerRestClient;
import org.zr7.billingservice.feign.ProductItemRestClient;
import org.zr7.billingservice.model.Customer;
import org.zr7.billingservice.model.Product;
import org.zr7.billingservice.repository.BillRepository;
import org.zr7.billingservice.repository.ProductItemRepository;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BillRepository billRepository,
							ProductItemRepository productItemRepository,
							CustomerRestClient customerRestClient,
							ProductItemRestClient productItemRestClient){
		return args -> {

			Customer customer = customerRestClient.getCustomerById(1L);
			Bill bill1 = billRepository.save(new Bill(null, new Date(), null, customer.getId(), null));
			//Bill bill2 = billRepository.findById(1L).get();
			PagedModel<Product> productPagedModel = productItemRestClient.pageProducts();
			productPagedModel.forEach(p->{
				ProductItem productItem = new ProductItem();
				productItem.setPrice(p.getPrice());
				productItem.setQuantity(new Random().nextInt(100));
				productItem.setBill(bill1);
				productItem.setProductID(p.getId());
				productItemRepository.save(productItem);
			});
		};
	}

}
