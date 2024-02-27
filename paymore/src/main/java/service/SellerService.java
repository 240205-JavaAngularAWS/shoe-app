package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.SellerRepository;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;


    public SellerService(SellerRepository sellerRepository){
        this.sellerRepository = sellerRepository;
    }


    private static final Logger logger = LoggerFactory.getLogger(SellerService.class);




}
