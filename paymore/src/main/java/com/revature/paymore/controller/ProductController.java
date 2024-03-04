package com.revature.paymore.controller;
import com.revature.paymore.exception.BadRequestException;
import com.revature.paymore.model.dto.ProductDTO;
import com.revature.paymore.model.Product;
import com.revature.paymore.model.enums.Category;
import com.revature.paymore.service.ProductService;
import com.revature.paymore.service.ResponseHelperService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private final ProductService productService;
    private final ResponseHelperService responseHelperService;


    @Autowired
    ProductController(ProductService productService, ResponseHelperService responseHelperService){
        this.productService = productService;
        this.responseHelperService = responseHelperService;
    }

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);


    // adding a Product
    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@Valid @RequestBody Product product, BindingResult bindingResult){
        // Maybe change association to ProductDTO? Might make it easier to add new products without inputting the entire Seller Object.
        // Validation annotations copied to the ProductDTO already in case we decide to do that.
        if (bindingResult.hasErrors()) {
            return responseHelperService.getBindingErrors(bindingResult);
        }
        // add product to seller
        ProductDTO response = productService.addProduct(product);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // deleting a Product
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long productId){

        boolean deleted = productService.deleteProduct(productId);
        if(!deleted){
            throw new BadRequestException("Product with id " + productId + " was not found.");
        }
        return new ResponseEntity<>("{\"message\":\"Product Successfully Deleted\"}", HttpStatus.OK);
    }

    // find all products
    @GetMapping("/products/all")
    public ResponseEntity<List<ProductDTO>> findAllProducts(){

        List<ProductDTO> response = productService.getAllProducts();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    // find product by sellerId
    @GetMapping("/products/filterBy")
    public ResponseEntity<List<ProductDTO>> findProducts(
            @RequestParam(name = "category", required = false) Optional<String> category,
            @RequestParam(name = "sellerId", required = false) Optional<Long> sellerId) {

        List<ProductDTO> response;

        // Check if both parameters are missing
        if (category.isEmpty() && sellerId.isEmpty()) {
            throw new BadRequestException("Please provide at least one filter: category or sellerId.");
        }

        Category convertedCategory = null;
        if (category.isPresent()) {
            try {
                convertedCategory = Category.valueOf(category.get().toUpperCase());
                if (convertedCategory != Category.ATHLETIC && convertedCategory != Category.CASUAL && convertedCategory != Category.DRESS) {
                    throw new BadRequestException("Invalid category. Please format the enum as an all caps string of the available choices.");
                }
            } catch (IllegalArgumentException e) {
                throw new BadRequestException("Invalid category provided.");
            }
        }

        if (category.isPresent() && sellerId.isEmpty()) {
            response = productService.findProductsByCategory(convertedCategory);
        } else if (category.isEmpty() && sellerId.isPresent()) {
            response = productService.findProductsBySellerID(sellerId.get());
        } else {
            response = productService.findProductsBySellerIdAndCategory(sellerId.get(), convertedCategory);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable Long productId){

        ProductDTO product = productService.findProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @PutMapping("/products/update")
    public ResponseEntity<ProductDTO> changeProductQuantity(@RequestParam(name = "quantity") int quantity,
                                                         @RequestParam(name = "productId") long productId) {
        ProductDTO products = productService.changeProductQuantity(productId, quantity);
        return ResponseEntity.ok(products);
    }


    @PutMapping("/products/{productId}")
    public ResponseEntity<ProductDTO> changeProductPicture(@PathVariable long productId, @RequestBody String imageUrl) {
        ProductDTO products = productService.changeProductPicture(productId, imageUrl);
        return ResponseEntity.ok(products);
    }



    @GetMapping("/products/searchBy")
    public ResponseEntity<List<ProductDTO>> findProductsByKeyword(@RequestParam(name = "keyword") String keyword) {
        List<ProductDTO> products = productService.findProductsByKeyword(keyword);
        return ResponseEntity.ok(products);
    }



}
