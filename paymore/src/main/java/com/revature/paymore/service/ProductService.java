package com.revature.paymore.service;

import com.revature.paymore.model.Seller;
import com.revature.paymore.model.dto.ProductDTO;
import com.revature.paymore.model.Product;
import com.revature.paymore.model.enums.Category;
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

    private final ModelMapper modelMapper;


    @Autowired
    public ProductService(ProductRepository productRepository, SellerRepository sellerRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
    }

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);




    private ProductDTO convertToDto(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }






    public ProductDTO addProduct(Product product){
        // check if seller exists
        Seller seller = sellerRepository.findById(product.getSeller().getId())
                .orElseThrow(() -> new EntityNotFoundException(" This product does not have any Seller "));
        product.setSeller(seller);
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

    public List<ProductDTO> findProductsByCategory(Category category){
        return productRepository.findByCategory(category).stream().map(product -> modelMapper.map(product, ProductDTO.class)).toList();
    }

    public ProductDTO changeProductQuantity(long productId, int quantityChange){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product Not Found"));

        int newQuantity = product.getQuantity() + quantityChange;

        // make sure newQuantity is not negative
        newQuantity = Math.max(newQuantity, 0);

        product.setQuantity(newQuantity);

        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDTO.class);

    }

    public ProductDTO changeProductPicture(long productId, String imageUrl){
        if (imageUrl.startsWith("\"") && imageUrl.endsWith("\"")) {
            imageUrl = imageUrl.substring(1, imageUrl.length() - 1);
        }
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product Not Found"));

        product.setImageUrl(imageUrl);

        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDTO.class);


    }


    public List<ProductDTO> findProductsBySellerIdAndCategory(Long sellerId, Category category){
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new EntityNotFoundException("There is no product because seller wasn't found"));
        return productRepository.findBySellerAndCategory(seller, category).stream().map(product -> modelMapper.map(product, ProductDTO.class)).toList();
    }


    //
    public ProductDTO findProductById(Long productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("There is no product because given ID wasn't found"));
        return convertToDto(product);
    }

    public List<ProductDTO> findProductsByKeyword(String keyword) {
        return productRepository.findByProductNameContainingIgnoreCase(keyword).stream()
                .map(product -> modelMapper.map(product, ProductDTO.class)).toList();
    }

}
