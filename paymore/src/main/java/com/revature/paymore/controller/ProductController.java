package com.revature.paymore.controller;
import com.revature.paymore.exception.BadRequestException;
import com.revature.paymore.model.dto.ProductDTO;
import com.revature.paymore.model.Product;
import com.revature.paymore.model.dto.ReviewDTO;
import com.revature.paymore.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    ProductService productService;

    @Autowired
    ProductController(ProductService productService){
        this.productService = productService;
    }

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);


    // adding a Product
    @PostMapping("/products")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody Product product){
        // add product to seller
        ProductDTO addedProduct = productService.addProduct(product);
        return new ResponseEntity<>(addedProduct, HttpStatus.OK);
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
    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> addProduct(){

        List<ProductDTO> allProducts = productService.getAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }



    // find product by sellerId
    @GetMapping("/products/seller/{sellerId}")
    public ResponseEntity<List<ProductDTO>> findProductsBySellerId(@PathVariable Long sellerId){

        List<ProductDTO> allProducts = productService.findProductsBySellerID(sellerId);
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }


    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable Long productId){

        ProductDTO product = productService.findProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


}
