package com.revature.paymore;


import com.revature.paymore.model.*;
import com.revature.paymore.model.dto.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class MMConfiguration {



    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

            modelMapper.addMappings(new PropertyMap<Review, ReviewDTO>() {
                @Override
                protected void configure() {
                    map().setProductId(source.getProduct().getId());
                }
            });


        // custom mapping for Product DTOs in SellerDTO
        modelMapper.typeMap(Seller.class, SellerDTO.class).addMappings(mapper -> {

            mapper.<Set<ProductDTO>>map(src -> {
                Set<Product> products = src.getProducts();
                return (products != null) ? products.stream()
                        .map(product -> modelMapper.map(product, ProductDTO.class))
                        .collect(Collectors.toSet()) : Collections.emptySet();
            }, SellerDTO::setProducts);
        });

        // custom mapping for Various DTOs in UserDTO
        modelMapper.typeMap(User.class, UserDTO.class).addMappings(mapper -> {

            mapper.<Set<AddressDTO>>map(src -> {
                Set<Address> addresses = src.getAddresses();
                return (addresses != null) ? addresses.stream()
                        .map(address -> modelMapper.map(address, AddressDTO.class))
                        .collect(Collectors.toSet()) : Collections.emptySet();
            }, UserDTO::setAddresses);


            mapper.<Set<CreditCardDTO>>map(src -> {
                Set<CreditCard> creditCards = src.getCreditCards();
                return (creditCards != null) ? creditCards.stream()
                        .map(creditCard -> modelMapper.map(creditCard, CreditCardDTO.class))
                        .collect(Collectors.toSet()) : Collections.emptySet();
            }, UserDTO::setCreditCards);


            mapper.<Set<OrderDTO>>map(src -> {
                Set<Order> orders = src.getOrders();
                return (orders != null) ? orders.stream()
                        .map(order -> modelMapper.map(order, OrderDTO.class))
                        .collect(Collectors.toSet()) : Collections.emptySet();
            }, UserDTO::setOrders);


        });


        // custom mapping for Review DTOs in ProductDTO
        modelMapper.typeMap(Product.class, ProductDTO.class).addMappings(mapper -> {

            mapper.<List<ReviewDTO>>map(src -> {
                List<Review> reviews = src.getReviews();
                return (reviews != null) ? reviews.stream()
                        .map(review -> modelMapper.map(review, ReviewDTO.class))
                        .collect(Collectors.toSet()) : Collections.emptySet();
            }, ProductDTO::setReviews);
        });


        // custom mapping for OrderItems DTOs in OrderDTO
        modelMapper.typeMap(Order.class, OrderDTO.class).addMappings(mapper -> {

            mapper.<Set<OrderItemDTO>>map(src -> {
                Set<OrderItem> orderItems = src.getOrderItems();
                return (orderItems != null) ? orderItems.stream()
                        .map(orderItem -> modelMapper.map(orderItem, OrderItemDTO.class))
                        .collect(Collectors.toSet()) : Collections.emptySet();
            }, OrderDTO::setOrderItems);
        });


        return modelMapper;



    }
}
