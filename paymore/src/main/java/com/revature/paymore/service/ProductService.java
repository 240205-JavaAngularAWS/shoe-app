package com.revature.paymore.service;

import com.revature.paymore.exception.BadRequestException;
import com.revature.paymore.exception.GlobalExceptionHandler;
import com.revature.paymore.model.DTO.ProductDTO;
import com.revature.paymore.model.Product;
import com.revature.paymore.model.Seller;
import com.revature.paymore.model.enums.Category;
import com.revature.paymore.model.enums.Gender;
import com.revature.paymore.repository.ProductRepository;
import com.revature.paymore.repository.ReviewRepository;
import com.revature.paymore.repository.SellerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;

    public ProductService(ProductRepository productRepository, SellerRepository sellerRepository) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);



    public ProductDTO addProducts(Product product){

        // check if seller exists
        Seller activeSeller = sellerRepository.findById(product.getSeller().getId())
                .orElseThrow(() -> new EntityNotFoundException( " This product does not have any Seller " ));

        // check if price and quantity is greater than 0
        if(product.getPrice() < 0 && product.getQuantity() < 0){
            throw new BadRequestException("Product's price or quantity is not valid");
        }

        // check if user inputs a valid gender and category
        if(product.getGender() != Gender.MENS && product.getGender() != Gender.WOMENS){
            throw new BadRequestException("Product gender input is invalid");
        }

        if(product.getCategory() != Category.ATHLETIC && product.getCategory() != Category.CASUAL && product.getCategory() != Category.DRESS){
            throw new BadRequestException("Product category input is invalid");
        }

        productRepository.save(product);
        return new ProductDTO(product);
    }


    public boolean deleteProduct(long productId){

        return productRepository.findById(productId)
                                .map(user -> { productRepository.deleteById(productId);
                                return true;})
                                .orElse(false);
    }




}
