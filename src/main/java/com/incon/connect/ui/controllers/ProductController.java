package com.incon.connect.ui.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.incon.connect.ui.dto.ProductDto;
import com.incon.connect.ui.entities.Brand;
import com.incon.connect.ui.entities.Product;
import com.incon.connect.ui.entities.ProductCategory;
import com.incon.connect.ui.entities.ProductDivision;
import com.incon.connect.ui.entities.ProductOffers;
import com.incon.connect.ui.entities.SubscriberDetails;
import com.incon.connect.ui.services.ProductService;
import com.incon.connect.ui.services.UserPointsService;
import com.incon.connect.ui.util.Response;

@Controller
@RequestMapping("/products")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	UserPointsService userpointsService;
	@Autowired
	ProductService productService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public List<Product> fetchProducts() {
		List<Product> products = null;
		try {
			products = productService.fetchAllProducts();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseBody
	public Page<Product> fetchUserProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Page<Product> products = null;
		try {
			products = productService.fetchUserProducts(page, size);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	
	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	@ResponseBody
	public SubscriberDetails findDepot(@PathVariable("code") String code, @PathVariable("msisdn") String msisdn) {
		return userpointsService.findUserPoints(Long.parseLong(code));
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public Page<Product> fetchAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, 
			@RequestParam(defaultValue = "false") boolean component) {

		return productService.fetchAllProducts(page, size, component);
	}

	@RequestMapping(value = "/{Id}", method = RequestMethod.GET)
	@ResponseBody
	public Product findById(@PathVariable("Id") Long code) {
		return productService.findById(code);
	}

	@RequestMapping(value = "/checkunique/{type}/{name}", method = RequestMethod.GET)
	@ResponseBody
	public String findByName(@PathVariable("name") String name, @PathVariable("type") String type) {
		String result = "false";
		Product product = productService.checkUnique(name, type);
		if (null != product) {
			result = "true";
		}
		return result;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public List<Product> searchProducts(@RequestBody ProductDto product) {
		return productService.searchProducts(product, product.getPage(), product.getSize());
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Response addProducts(@RequestParam(value = "product") String userInfo, @RequestPart(value = "file", required = false) MultipartFile file,
			@RequestPart(value = "image", required = false) MultipartFile image, HttpServletRequest request)
			throws IOException {
		Product product = null;
		System.out.println("Inside File upload" + userInfo);
		ObjectMapper mapper = new ObjectMapper();
		Response response = new Response();
		response.setSuccess(true);
		response.setMessage("");
		List<FieldError> errors = new ArrayList<>();
		try {
			product = mapper.readValue(userInfo, Product.class);
			product.setFile(file);
			byte[] flogo = null;
			if (null != product.getFile() && !product.getFile().isEmpty()) {
				flogo = product.getFile().getBytes();
//				BufferedImage io = ImageIO.read(product.getFile().getInputStream());
//				BufferedImage resizedImage = new BufferedImage(56, 33, io.getType());
//				Graphics2D g = resizedImage.createGraphics();
//				g.drawImage(io, 0, 0, 56, 33, null);
//				g.dispose();
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();
//				ImageIO.write(resizedImage, "jpg", baos);
//				baos.flush();
//				flogo = baos.toByteArray();
//				baos.close();
				
			}

			if (null != image && !image.isEmpty()) {
				byte[] imageBytes = image.getBytes();
				product.setImage(imageBytes);
			}
			product.setLogo(flogo);
			productService.addProduct(product);
		} catch (Exception ex) {
			logger.error("Error while saving product", ex);
			FieldError error = new FieldError("product", "logo", ex.getMessage());
			errors.add(error);
			response.setErrors(errors);
			response.setSuccess(false);
			response.setMessage("Error while saving product");
		}
		return response;// "redirect:/add-products";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Response updateProducts(@RequestParam(value = "product") String userInfo, @RequestPart(value = "file", required = false) MultipartFile file,
			@RequestPart(value = "image", required = false) MultipartFile image, 
			HttpServletRequest request) {
		Response response = new Response();
		BindingResult result = null;
		List<FieldError> errors = new ArrayList<>();
		response.setSuccess(true);
		response.setMessage("");
		Product product = null;
		System.out.println("Inside File upload" + userInfo);
		ObjectMapper mapper = new ObjectMapper();
		try {
			product = mapper.readValue(userInfo, Product.class);
			product.setFile(file);
			byte[] flogo = null;
			if (null != file && !product.getFile().isEmpty()) {
				flogo = product.getFile().getBytes();
				/*BufferedImage io = ImageIO.read(product.getFile().getInputStream());
				io.getHeight();
				io.getWidth();
				if (56 < io.getHeight() || 56 < io.getWidth()) {
					FieldError error = new FieldError("product", "logo", "Max resolution allowed for logo is 56 x 56");
					errors.add(error);
					logger.error("Image size is more");
					response.setSuccess(false);
					response.setMessage("Max resolution allowed for logo is 56 x 56");
					response.setErrors(errors);
					return response;
				}*/
			}
			product.setLogo(flogo);

			if (null != image && !image.isEmpty()) {
				byte[] imageBytes = image.getBytes();
				product.setImage(imageBytes);
			}
			productService.updateProduct(product);
		} catch (Exception e) {
			logger.error("Error while updating product", e);
			FieldError error = new FieldError("product", "logo", e.getMessage());
			errors.add(error);
			response.setErrors(errors);
			response.setSuccess(false);
			response.setMessage("Error while updating product");
		}
		return response;

	}

//	@RequestMapping(value = "/companies", method = RequestMethod.GET)
//	@ResponseBody
//	public List<Company> getCompanies() {
//		return productService.fetchAllCompanies();
//	}
	
	
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductCategory> getCategories() {
		return productService.fetchCategories();
	}
	
	@RequestMapping(value = "/types/{categoryid}", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductDivision> getTypes(@PathVariable("categoryid") Long categoryId) {
		
		return productService.fetchTypes(categoryId);
	}
	
	@RequestMapping(value = "/brands/{divisionid}", method = RequestMethod.GET)
	@ResponseBody
	public List<Brand> getBrands(@PathVariable("divisionid") Long divisionId) {
		
		return productService.fetchBrands(divisionId);
	}
	
	@RequestMapping(value = "/logo/{id}", method = RequestMethod.GET)
	public void getLogo(@PathVariable("id") Long productId, HttpServletRequest request, HttpServletResponse response) {
		byte[] contents = productService.fetchLogo(productId, 1);
		response.setContentType("image/png"); // in my example this
		response.setContentLength(contents.length);
		response.setHeader("Content-Disposition", "attachment; filename=" + productId + "_image.png");
		try {
			FileCopyUtils.copy(new ByteArrayInputStream(contents), response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
	public void getImage(@PathVariable("id") Long productId, HttpServletRequest request, HttpServletResponse response) {
		byte[] contents = productService.fetchLogo(productId, 2);
		response.setContentType("image/png"); // in my example this
		response.setContentLength(contents.length);
		response.setHeader("Content-Disposition", "attachment; filename=" + productId + "_image.png");
		try {
			FileCopyUtils.copy(new ByteArrayInputStream(contents), response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
	
	
	@RequestMapping(value = "/saveoffer", method = RequestMethod.POST)
	@ResponseBody
	public Response saveOffer(@RequestBody @Valid ProductOffers offer, BindingResult result)
			throws IOException {
		Response response = new Response();
		response.setSuccess(true);
		response.setMessage("");
		if (result.hasErrors()) {
			response.setSuccess(false);
			response.setErrors(result.getFieldErrors());
			response.setMessage("Unable to add notifications");
			return response;
		}
		productService.addOffer(offer);
		return response;// "redirect:/add-products";
	}
	
	@RequestMapping(value = "/findbyentrypoint", method = RequestMethod.GET)
	@ResponseBody
	public Page<Product> findByEntryPoint(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, 
			@RequestParam("entrypoint") Integer entryPoint)
			throws IOException {
		Page<Product> list = productService.findByEntryPoint(page, size, entryPoint);
		return list;
	}

}
