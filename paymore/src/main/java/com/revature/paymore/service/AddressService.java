package com.revature.paymore.service;

import com.revature.paymore.model.Address;
import com.revature.paymore.model.DTO.AddressDTO;
import com.revature.paymore.model.DTO.UserDTO;
import com.revature.paymore.model.User;
import com.revature.paymore.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }


    public AddressDTO registerAddress(Address address){
        addressRepository.save(address);
        return new AddressDTO(address);

    }




}
