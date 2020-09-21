package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.dao.ImageRepository;
import com.example.demo.dao.ProductRepository;
import com.example.demo.dao.RangRepository;
import com.example.demo.entities.Category;
import com.example.demo.entities.OptionP;
import com.example.demo.entities.OrderC;
import com.example.demo.entities.OrderItems;
import com.example.demo.entities.PayementOnline;
import com.example.demo.entities.Product;
import com.example.demo.entities.Rang;
import com.example.demo.entities.Reseller;
import com.example.demo.entities.Ville;
import com.example.demo.payement.PaypalClient;
import com.stripe.model.OrderItem;

import ch.qos.logback.core.net.server.Client;

@SpringBootApplication
public class TccieApplication implements CommandLineRunner  {

	@Autowired
	private RepositoryRestConfiguration rep;
	
	public static void main(String[] args) {
		
		SpringApplication.run(TccieApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		rep.exposeIdsFor(Product.class,Category.class,Rang.class, Reseller.class, 
				Ville.class, Client.class, OrderC.class, PayementOnline.class, OptionP.class, 
				OrderItems.class);
		
		
	}
	

}
