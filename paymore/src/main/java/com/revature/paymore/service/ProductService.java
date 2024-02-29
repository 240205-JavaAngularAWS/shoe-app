package com.revature.paymore.service;

import com.revature.paymore.exception.BadRequestException;
import com.revature.paymore.exception.InvalidProductException;
import com.revature.paymore.model.dto.ProductDTO;
import com.revature.paymore.model.Product;
import com.revature.paymore.model.enums.Category;
import com.revature.paymore.model.enums.Gender;
import com.revature.paymore.repository.ProductRepository;
import com.revature.paymore.repository.SellerRepository;
import com.revature.paymore.validation.ProductValidator;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {


    private final ProductRepository productRepository;

    private final SellerRepository sellerRepository;

    private final ProductValidator productValidator;

    @Autowired
    public ProductService(ProductRepository productRepository, SellerRepository sellerRepository, ProductValidator productValidator) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
        this.productValidator = productValidator;
    }

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);


    private ModelMapper modelMapper = new ModelMapper();

    private ProductDTO convertToDto(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }


    private void validateProduct(Product product){
        Errors errors = new BeanPropertyBindingResult(product, "product");
        productValidator.validate(product, errors);
        if (errors.hasErrors()) {
            throw new InvalidProductException("Product input is Invalid.", errors);
        }
    }


    public ProductDTO addProduct(Product product){
        // check if seller exists
        sellerRepository.findById(product.getSeller().getId())
                .orElseThrow(() -> new EntityNotFoundException(" This product does not have any Seller "));

        validateProduct(product);
        productRepository.save(product);
        return new ProductDTO(product);
    }


    public boolean deleteProduct(long productId){
        return productRepository.findById(productId)
                                .map(user -> { productRepository.deleteById(productId);
                                return true;})
                                .orElse(false);
    }



    public List<ProductDTO> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    public List<ProductDTO> findProductsBySellerID(Long sellerId){
        sellerRepository.findById(sellerId)
                .orElseThrow(() -> new EntityNotFoundException("There is no product because seller wasn't found"));
        return productRepository.findBySellerId(sellerId).stream().map(ProductDTO::new).toList();
    }


    //
    public ProductDTO findProductById(Long productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("There is no product because given ID wasn't found"));
        return convertToDto(product);
    }

}
