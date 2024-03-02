package com.revature.paymore.service;

import com.revature.paymore.model.Address;
import com.revature.paymore.model.dto.AddressDTO;
import com.revature.paymore.repository.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {


    private final AddressRepository addressRepository;



    @Autowired
    public AddressService(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    ModelMapper modelMapper = new ModelMapper();


    public AddressDTO registerAddress(Address address){
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
