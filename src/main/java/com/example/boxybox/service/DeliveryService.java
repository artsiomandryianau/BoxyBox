package com.example.boxybox.service;


import com.example.boxybox.dao.DeliveryRepo;
import com.example.boxybox.domain.Delivery;
import com.example.boxybox.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepo deliveryRepo;

    @Value("${upload.path}")
    private String uploadPath;


    public void newDelivery(String nameOfCommodity, String dateOfDelivery, String addressCity, String addressStreet,
                            String addressNumberOfBuilding, String addressNumberOfApartament, String commodityPrise,
                            User user, MultipartFile file) throws IOException {
        Double commodityPriseDouble = Double.parseDouble(commodityPrise);
        Delivery delivery = new Delivery(dateOfDelivery, nameOfCommodity, addressCity, addressStreet,
                addressNumberOfBuilding, addressNumberOfApartament, commodityPriseDouble,user);
        if(file!=null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            delivery.setFilename(resultFileName);
        }
        deliveryRepo.save(delivery);
    }

    public void editAddress(Integer  id, String newAddress) {
        String[] addressTab = newAddress.split(" ");
        Delivery delivery = deliveryRepo.findById(id);
        delivery.setAddressCity(addressTab[0]);
        delivery.setAddressStreet(addressTab[1]);
        delivery.setAddressNumberOfBuilding(addressTab[2]);
        delivery.setAddressNumberOfApartament(addressTab[3]);
    }
}
