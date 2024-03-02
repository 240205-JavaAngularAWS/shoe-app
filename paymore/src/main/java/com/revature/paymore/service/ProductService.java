package com.revature.paymore.service;

import com.revature.paymore.model.Seller;
import com.revature.paymore.model.dto.ProductDTO;
import com.revature.paymore.model.Product;
import com.revature.paymore.repository.ProductRepository;
import com.revature.paymore.repository.SellerRepository;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


import java.util.List;


@Service
public class ProductService {


    private final ProductRepository productRepository;

    private final SellerRepository sellerRepository;


    @Autowired
    public ProductService(ProductRepository productRepository, SellerRepository sellerRepository) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);


    private ModelMapper modelMapper = new ModelMapper();

    private ProductDTO convertToDto(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }






    public ProductDTO addProduct(Product product){
        // check if seller exists
        sellerRepository.findById(product.getSeller().getId())
                .orElseThrow(() -> new EntityNotFoundException(" This product does not have any Seller "));

        productRepository.save(product);
        return modelMapper.map(product, ProductDTO.class);
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
                .map(this::convertToDto).toList();
    }


    public List<ProductDTO> findProductsBySellerID(Long sellerId){
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new EntityNotFoundException("There is no product because seller wasn't found"));
        return productRepository.findBySeller(seller).stream().map(product -> modelMapper.map(product, ProductDTO.class)).toList();
    }


    //
    public ProductDTO findProductById(Long productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("There is no product because given ID wasn't found"));
        return convertToDto(product);
    }

}
