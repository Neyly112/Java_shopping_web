package vn.tdtu.edu.springcomerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.tdtu.edu.springcomerce.models.Product;
import vn.tdtu.edu.springcomerce.models.Role;
import vn.tdtu.edu.springcomerce.models.User;
import vn.tdtu.edu.springcomerce.Repository.ProductRepo;
import vn.tdtu.edu.springcomerce.Repository.UserRepo;

import java.util.Arrays;

@SpringBootApplication
public class SpringSource {


	public static void main(String[] args) {
		SpringApplication.run(SpringSource.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner createAdminUser(UserRepo userRepo, ProductRepo productRepo) {
		return args -> {
			User admin = new User();
			admin.setUsername("admin");
			admin.setPassword(passwordEncoder.encode("1"));
			admin.setRole(Role.valueOf("ADMIN"));
			userRepo.save(admin);
			if (productRepo.count() == 0) {
				Product product1 = new Product();
				product1.setName("Women's clothing");
				product1.setCategory("Full kit");
				product1.setBrand("Lok");
				product1.setColor("Black");
				product1.setPrice("600.00");
				product1.setImage("QUANDEN.jpg");
				product1.setDescription("Genuine women's pants.");
				Product product2 = new Product();
				product2.setName("Women's clothing");
				product2.setCategory("Full kit");
				product2.setBrand("VietP");
				product2.setColor("Black");
				product2.setPrice("500.00");
				product2.setImage("__-12_7532a4063c714c05bc5108246f8d0e98_1024x1024.webp");
				product2.setDescription("Genuine clothing.");
				Product product3 = new Product();
				product3.setName("Women's clothing");
				product3.setCategory("Full kit");
				product3.setBrand("JeansClo");
				product3.setColor("Blue");
				product3.setPrice("200.00");
				product3.setImage("Ecru White Modern Elegant Minimal Women_s Fashion Clothing Collection Instagram Story.png");
				product3.setDescription("Genuine clothing.");
				Product product4 = new Product();
				product4.setName("Man's clothing");
				product4.setCategory("Trousers");
				product4.setBrand("JeansClo");
				product4.setColor("Blue");
				product4.setPrice("150.00");
				product4.setImage("qn3.jpg");
				product4.setDescription("Genuine clothing.");
				Product product5 = new Product();
				product5.setName("Man's clothing");
				product5.setCategory("Trousers");
				product5.setBrand("JeansClo");
				product5.setColor("Black");
				product5.setPrice("200.00");
				product5.setImage("qn2.jpg");
				product5.setDescription("Genuine clothing.");
				Product product6 = new Product();
				product6.setName("Man's clothing");
				product6.setCategory("Trousers");
				product6.setBrand("JeansClo");
				product6.setColor("White");
				product6.setPrice("200.00");
				product6.setImage("QuanKaki.webp");
				product6.setDescription("Genuine clothing.");
				productRepo.saveAll(Arrays.asList(product1, product2, product3, product4, product5, product6));
				System.out.println("Sample products added to the database!");
			} else {
				System.out.println("Products already exist in the database.");
			}
		};
	}
}
