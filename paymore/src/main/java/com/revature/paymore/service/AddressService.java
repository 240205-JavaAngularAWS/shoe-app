package com.revature.paymore.service;

import com.revature.paymore.exception.InvalidEntityException;
import com.revature.paymore.model.Address;
import com.revature.paymore.model.Seller;
import com.revature.paymore.model.User;
import com.revature.paymore.model.dto.AddressDTO;
import com.revature.paymore.model.enums.AddressType;
import com.revature.paymore.repository.AddressRepository;
import com.revature.paymore.repository.SellerRepository;
import com.revature.paymore.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {


    private final AddressRepository addressRepository;

    private final SellerRepository sellerRepository;

    private final UserRepository userRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, SellerRepository sellerRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.sellerRepository = sellerRepository;
        this.userRepository = userRepository;
    }

    ModelMapper modelMapper = new ModelMapper();


    public AddressDTO registerAddress(AddressDTO addressDto){
        // this method registers only Seller and Shipping Addresses.
        if(addressDto.getUserId() == 0 && addressDto.getSellerId() == 0){
            throw new InvalidEntityException("Must be attached to either a user or seller");
        }
        Address address = new Address();
        if(addressDto.getSellerId() != 0){
            Seller seller = sellerRepository.findById(addressDto.getSellerId())
                            .orElseThrow(() -> new EntityNotFoundException("Seller Not Found"));
            address.setSeller(seller);
            address.setAddressType(AddressType.SELLER);
        }
        if(addressDto.getUserId() != 0){
            User user = userRepository.findById(addressDto.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User Not Found"));
            address.setAddressType(AddressType.SHIPPING);
            address.setUser(user);

        }
                address.setAddressText(addressDto.getAddressText());
                address.setCity(addressDto.getCity());
                addressDto.setState(addressDto.getState());
                address.setZipCode(addressDto.getZipCode());

        addressRepository.save(address);
        return modelMapper.map(address, AddressDTO.class);

    }

    public boolean deleteAddress(long addressId){
        return addressRepository.findById(addressId)
                .map(user -> { addressRepository.deleteById(addressId);
                    return true;})
                .orElse(false);
    }




}
