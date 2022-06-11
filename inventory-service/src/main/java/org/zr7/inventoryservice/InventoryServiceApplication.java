package org.zr7.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.zr7.inventoryservice.entities.Product;
import org.zr7.inventoryservice.repository.ProductRepository;

import java.util.concurrent.ConcurrentMap;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration){
		restConfiguration.exposeIdsFor(Product.class);
		return args -> {
			productRepository.save(new Product(null, "Computer", 4000, 420));
			productRepository.save(new Product(null, "Smart Phone", 1288, 112));
			productRepository.save(new Product(null, "Smart TV", 3500, 200));
			productRepository.findAll().forEach(p->{
				System.out.println(p.getName());
			});
		};
	}

}
