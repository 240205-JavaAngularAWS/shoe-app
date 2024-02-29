package com.revature.paymore.controller;
import com.revature.paymore.exception.BadRequestException;
import com.revature.paymore.model.DTO.ProductDTO;
import com.revature.paymore.model.Product;
import com.revature.paymore.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);


    // adding Products
    @PostMapping("/products")
    public ResponseEntity<?> addProducts(@RequestBody Product product){

        ProductDTO addedProduct = productService.addProducts(product);
        return new ResponseEntity<>(addedProduct, HttpStatus.OK);
    }


    // deleting Products
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProducts(@PathVariable Long productId){

        boolean deleted = productService.deleteProduct(productId);
        if(!deleted){
            throw new BadRequestException("Product with id " + productId + " was not found.");
        }
        return new ResponseEntity<>("{\"message\":\"Successfully Deleted\"}", HttpStatus.OK);
    }

}
