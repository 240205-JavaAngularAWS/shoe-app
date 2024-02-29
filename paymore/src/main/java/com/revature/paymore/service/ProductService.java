package com.revature.paymore.service;

import com.revature.paymore.model.Product;
import com.revature.paymore.repository.ProductRepository;
import com.revature.paymore.repository.ReviewRepository;
import com.revature.paymore.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository

    public ProductService(ProductRepository productRepository, SellerRepository sellerRepository) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }

    public Product addProducts(Product product){


        return null;
    }


}
