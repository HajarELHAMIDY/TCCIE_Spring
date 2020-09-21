package com.example.demo.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ClientRepository;
import com.example.demo.dao.OrderItemsRepository;
import com.example.demo.dao.OrderRepository;
import com.example.demo.dao.ProductRepository;
import com.example.demo.dao.VilleRepository;
import com.example.demo.entities.Category;
import com.example.demo.entities.Client;
import com.example.demo.entities.OrderC;
import com.example.demo.entities.OrderForm;
import com.example.demo.entities.OrderItems;
import com.example.demo.entities.Product;
import com.example.demo.entities.Ville;

@CrossOrigin("*")
@RestController
public class OrderController {
	@Autowired
	private ClientRepository clientdao;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemsRepository orderItemsdao;
	@Autowired
	private ProductRepository productdao;
	@Autowired
	private VilleRepository villeRepository;
	
	@PostMapping("/orderItem")
	public void listeOrders(@RequestBody Long id){
	//	OrderC o =orderRepository.findById(id).get();
	//	List<OrderItems> liste = orderItemsdao.findByOrder(o);
		
		//return orderItemsdao.findAll();
	}

	@PostMapping("/orders")
	public OrderC saveOrder(@RequestBody OrderForm orderForm) {

		Client client = new Client();

		Ville ville = villeRepository.findByName(orderForm.getVille());
		client.setCity(ville);

		client.setFirstName(orderForm.getClient().getFirstName());
		client.setLastName(orderForm.getClient().getLastName());
		client.setAddress(orderForm.getClient().getAddress());

		client.setEmail(orderForm.getClient().getEmail());
		client.setPhone(orderForm.getClient().getPhone());
		client.setCompte(false);
		// client.setLogin(orderForm.getClientD().getClient().getLogin());
		clientdao.save(client);

		OrderC order = new OrderC();
		order.setClient(client);
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(date);
		order.setDate(strDate);
		order.setMethodePayement(orderForm.getMethode());
		order.setOrderItems(orderForm.getProducts());
		order.setMethodePayement("en ligne");
		orderRepository.save(order);
		double total = 0;

		for (OrderItems op : orderForm.getProducts()) {
			OrderItems orderItem = new OrderItems();
			orderItem.setOrder(order);

			Product product = op.getProduct();

			orderItem.setProduct(product);
			orderItem.setPrice(product.getPrice());
			orderItem.setQuantity(product.getQuantity());
			orderItem.setOptionP(op.getOptionP());
			orderItemsdao.save(orderItem);

			total += op.getQuantity() * product.getPrice();

		}
		order.setTotalAmount(total);
		return orderRepository.save(order);

	}
	@DeleteMapping("/orders/delete/{id}")
	public Map<String, Boolean> deleteOrder(@PathVariable(value = "id") Long idOrder)
			throws ResourceNotFoundException {
		
		OrderC r = orderRepository.findById(idOrder).get();

		orderRepository.delete(r);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	@PostMapping("/order/add")
	void addOrderByAdmin(@RequestBody OrderC order) {
		System.err.println(order);
		order.setMethodePayement("Espece");
		order.setClient(null);
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(date);
		order.setDate(strDate);

		orderRepository.save(order);
		
	}
	@PutMapping("/orders/update")
	public ResponseEntity<OrderC> updateOrder(
			@Validated @RequestBody OrderC order) throws ResourceNotFoundException {
		
		OrderC cat = orderRepository.findById(order.getIdOrder()).get();
		
		cat.setTotalAmount(order.getTotalAmount());
      
		final OrderC updatedCat = orderRepository.save(cat);
		return ResponseEntity.ok(updatedCat);
	}

}
