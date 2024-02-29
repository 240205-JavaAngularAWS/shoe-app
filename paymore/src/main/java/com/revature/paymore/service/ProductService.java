package com.revature.paymore.service;

import com.revature.paymore.exception.BadRequestException;
import com.revature.paymore.model.dto.ProductDTO;
import com.revature.paymore.model.Product;
import com.revature.paymore.model.enums.Category;
import com.revature.paymore.model.enums.Gender;
import com.revature.paymore.repository.ProductRepository;
import com.revature.paymore.repository.SellerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

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

    private ProductDTO convertToDto2(Long id) {
        return modelMapper.map(id, ProductDTO.class);
    }


    public ProductDTO addProduct(Product product){
        // check if seller exists
        sellerRepository.findById(product.getSeller().getId())
                .orElseThrow(() -> new EntityNotFoundException(" This product does not have any Seller "));

        // check if price and quantity is greater than 0
        if(product.getPrice() < 0 && product.getQuantity() < 0){
            throw new BadRequestException("Product's price or quantity is not valid");
        }

        // check if user inputs a valid gender and category
        if(product.getGender() != Gender.MENS && product.getGender() != Gender.WOMENS){
            throw new BadRequestException("Product gender input is invalid");
        }
        // TODO: May need to be expanded if additional enum options are added to category

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
