package org.java.spring_crud5;

import java.util.List;
import java.util.Optional;

import org.java.spring_crud5.db.pojo.Order;
import org.java.spring_crud5.db.pojo.Product;
import org.java.spring_crud5.db.pojo.Customer;
import org.java.spring_crud5.db.service.CustomerService;
import org.java.spring_crud5.db.service.OrderService;
import org.java.spring_crud5.db.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCrud5Application implements CommandLineRunner {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private OrderService orderService;

	@Autowired 
	private ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(SpringCrud5Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Product p1 = new Product("Penna", 4, 22);
		Product p2 = new Product("Bicchiere", 2, 20);

		productService.save(p1);
		productService.save(p2);

		Order o1 = new Order(p1);
		Order o2 = new Order(p2);

		orderService.save(o1);
		orderService.save(o2);

		Customer c1 = new Customer("Vincenzo", "Longo", "123@gmail.com", "3667989090", o1);
		Customer c2 = new Customer("Luigi", "Longo", "123@libero.it", "4508908877", o2);

		customerService.save(c1);
		customerService.save(c2);		

		List<Customer> customers = customerService.findAll();
		List<Order> orders = orderService.findAll();
		List<Product> products = productService.findAll();

		customers.forEach(System.out::println);
		System.out.println("-------------------------------------------------------");
		orders.forEach(System.out::println);
		System.out.println("-------------------------------------------------------");
		products.forEach(System.out::println);

		System.out.println("-------------------------------------------------------");

		Optional<Customer> optC1 = customerService.findById(1);
		if (optC1.isEmpty()) {
			System.out.println("Customer not found");
			return;
		}

		Customer deletedCustomer = optC1.get();
		customerService.delete(deletedCustomer);
		customers.forEach(System.out::println);


	}

}
