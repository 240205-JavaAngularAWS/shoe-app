package com.revature.paymore.service;

import com.revature.paymore.model.Address;
import com.revature.paymore.model.dto.AddressDTO;
import com.revature.paymore.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {


    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }


    public AddressDTO registerAddress(Address address){
        addressRepository.save(address);
        return new AddressDTO(address);

    }




}
