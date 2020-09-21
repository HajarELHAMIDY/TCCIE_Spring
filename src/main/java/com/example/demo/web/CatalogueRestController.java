package com.example.demo.web;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.dao.ClientRepository;
import com.example.demo.dao.OptionRepository;
import com.example.demo.dao.ProductRepository;
import com.example.demo.dao.RangRepository;
import com.example.demo.dao.ResellerRepository;
import com.example.demo.dao.VilleRepository;
import com.example.demo.entities.Category;
import com.example.demo.entities.OrderC;
import com.example.demo.entities.Product;
import com.example.demo.entities.Rang;
import com.example.demo.entities.Reseller;
@CrossOrigin("*")
@RestController
public class CatalogueRestController {
	@Autowired
	private ProductRepository productdao;
	@Autowired 
	private CategoryRepository catdao;
	@Autowired
	private RangRepository rangRepository;
	@Autowired
	private ResellerRepository resellerRepository;
	@Autowired
	private ClientRepository clientdao;
	@Autowired
	private VilleRepository villeRepository;
	@Autowired
	private OptionRepository optiondao; 
	@GetMapping(path="/photoProducts/{id}", produces=MediaType.IMAGE_JPEG_VALUE) 
	public byte[] getPhoto(@PathVariable("id") Long id) throws Exception {
		Product p = productdao.findById(id).get();
	//	System.err.println(p.getIdProduct());
		return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/tccie_ecommerce/produits/"+p.getImageP()));
		
	}

	@GetMapping(path="/photoResellers/{id}", produces=MediaType.IMAGE_JPEG_VALUE) 
	public byte[] getPhotoReseller(@PathVariable("id") Long id) throws Exception {
		Reseller p = resellerRepository.findById(id).get();
	//	System.err.println(p.getIdProduct());
		return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/tccie_ecommerce/revendeurs/"+p.getImage()));
		
	}

	@PostMapping(path="/uploadPhotoReseller/{id}")
	public void uploadPhotoReseller(MultipartFile file,@PathVariable("id") Long idReseller) throws Exception {
		Reseller p = resellerRepository.findById(idReseller).get();
		p.setImage(idReseller+".jpg");
		Files.write(Paths.get(System.getProperty("user.home")+"/tccie_ecommerce/revendeurs/"+p.getImage()), file.getBytes());
		resellerRepository.save(p);
		
		
	}
	@PostMapping(path="/uploadPhoto/{idProduct}")
	public void uploadPhoto(MultipartFile file,@PathVariable Long idProduct) throws Exception {
		Product p = productdao.findById(idProduct).get();
		p.setImageP(idProduct+".jpg");
		Files.write(Paths.get(System.getProperty("user.home")+"/tccie_ecommerce/produits/"+p.getImageP()), file.getBytes());
		productdao.save(p);
		
		
	}
	@DeleteMapping("/products/delete/{idProduct}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "idProduct") Long idProduct)
			throws ResourceNotFoundException {
		System.err.println(idProduct);
		Product p = productdao.findById(idProduct).get();

		productdao.delete(p);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	@PutMapping("/products/update")
	public ResponseEntity<Product> updateProduct(
			@Validated @RequestBody ProductDetails pd) throws ResourceNotFoundException {
		
		Product p = productdao.findById(pd.getP().getIdProduct()).get();
		Category c = catdao.findByNameCategory(pd.getCategory());
		Rang r = rangRepository.findByNameRang(pd.getGamme());
		p.setCategory(c);
		p.setOpt(pd.getP().getOpt());
		p.setDescProduct(pd.getP().getDescProduct());
		p.setNameProduct(pd.getP().getNameProduct());
		p.setPrice(pd.getP().getPrice());
		p.setPromotion(pd.getP().getPromotion());
		p.setStock(pd.getP().getStock());
		p.setRang(r);
				

		final Product updatedProduct = productdao.save(p);
		return ResponseEntity.ok(updatedProduct);
	}
	@PostMapping("/products/add")
	public void  createProduct(@Validated @RequestBody ProductDetails p) {
		System.err.println("test tes test ");
		Category c = catdao.findByNameCategory(p.getCategory());
		Rang r = rangRepository.findByNameRang(p.getGamme());
		Product prod = p.getP();
		  System.err.println(p.getOp());
	    prod.setOpt(p.getOp());
		prod.setCategory(c);
		prod.setImageP("image.png");
		prod.setRang(r);
	productdao.save(prod);
	}
	
	@DeleteMapping("/categories/delete/{id}")
	public Map<String, Boolean> deleteCategiry(@PathVariable(value = "id") Long idCategory)
			throws ResourceNotFoundException {
		
		Category c = catdao.findById(idCategory).get();

		catdao.delete(c);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	@PutMapping("/categories/update")
	public ResponseEntity<Category> updateCategory(
			@Validated @RequestBody Category category) throws ResourceNotFoundException {
		
		Category cat = catdao.findById(category.getIdCategory()).get();
		
		cat.setDesCategory(category.getDesCategory());
		cat.setNameCategory(category.getNameCategory());

		final Category updatedCat = catdao.save(cat);
		return ResponseEntity.ok(updatedCat);
	}
	@PostMapping("/categories/add")
	public Category createCategory(@Validated @RequestBody Category cat) {

				return catdao.save(cat);
	}
	
	@DeleteMapping("/rangs/delete/{id}")
	public Map<String, Boolean> deleteRang(@PathVariable(value = "id") Long idRang)
			throws ResourceNotFoundException {
		
		Rang r = rangRepository.findById(idRang).get();

		rangRepository.delete(r);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	@PutMapping("/rangs/update")
	public ResponseEntity<Rang> updateRang(
			@Validated @RequestBody Rang rang) throws ResourceNotFoundException {
		
		Rang r = rangRepository.findById(rang.getIdRang()).get();
		
		r.setDescRang(rang.getDescRang());
		r.setNameRang(rang.getNameRang());

		final Rang updatedCat = rangRepository.save(r);
		return ResponseEntity.ok(updatedCat);
	}
	
	
	@DeleteMapping("/resellers/delete/{id}")
	public Map<String, Boolean> deleteReseller(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		
		Reseller r = resellerRepository.findById(id).get();

		resellerRepository.delete(r);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	@PutMapping("/resellers/update")
	public ResponseEntity<Reseller> updateReseller(
			@Validated @RequestBody Reseller r) throws ResourceNotFoundException {
		
	//	Reseller reseller = resellerRepository.findById(r.getIdReseller()).get();
		
		//r.setAddresse(r);
		final Reseller updatedCat = resellerRepository.save(r);
		return ResponseEntity.ok(updatedCat);
	}
	@PostMapping("/resellers/add")
	public Reseller createReseller(@Validated @RequestBody Reseller r) {
	            
                r.setImage("r.jpg");
         
				return resellerRepository.save(r);
	}

	@PostMapping("/rangs/add")
	public Rang createRang(@Validated @RequestBody Rang rang) {
	  
   System.err.println("test rang");
				return rangRepository.save(rang);
	}
	


}


class ProductDetails{
	private Product p ;
	private String category;
	private String gamme;
	private Boolean op; 
	public Product getP() {
		return p;
	}
	public void setP(Product p) {
		this.p = p;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Boolean getOp() {
		return op;
	}
	public void setOp(Boolean op) {
		this.op = op;
	}
	public String getGamme() {
		return gamme;
	}
	public void setGamme(String gamme) {
		this.gamme = gamme;
	}
	
}

