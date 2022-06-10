package org.zr7.customerservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.zr7.customerservice.entities.Customer;
import org.zr7.customerservice.repository.CustomerRepository;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration restConfiguration){
		restConfiguration.exposeIdsFor(Customer.class);
		return args -> {
			customerRepository.save(new Customer(null, "Zakaria", "zaki@gmail.com"));
			customerRepository.save(new Customer(null, "Jamal", "jamal@gmail.com"));
			customerRepository.save(new Customer(null, "Hamza", "hamza@gmail.com"));
			customerRepository.save(new Customer(null, "Omar", "omar@gmail.com"));
			customerRepository.save(new Customer(null, "Abir", "abir@gmail.com"));
			customerRepository.save(new Customer(null, "Hamid", "hamid@gmail.com"));
			customerRepository.save(new Customer(null, "Hasna", "hasna@gmail.com"));
			customerRepository.save(new Customer(null, "Mohamed", "mohamed@gmail.com"));
			customerRepository.save(new Customer(null, "Mourad", "mourad@gmail.com"));
			customerRepository.findAll().forEach(c->{
				System.out.println(c.toString());
			});
		};
	}

}
