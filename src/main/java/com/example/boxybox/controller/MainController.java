package com.example.boxybox.controller;

import com.example.boxybox.domain.Delivery;
import com.example.boxybox.domain.User;
import com.example.boxybox.repos.DeliveryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;


/**
 *
 * @author Artsiom Andryianau
 *
 * main controller of app
 */
@Controller
public class MainController {


    @Autowired
    private DeliveryRepo deliveryRepo;

    @Value("${upload.path}")
    private String uploadpath;


    @GetMapping("/")
    public String greeting(Map<String,Object> model){
        return "greeting";
    }

    @GetMapping("/main")
    public String main( Model model) {
        Iterable<Delivery> deliveries = deliveryRepo.findAll();

        model.addAttribute("deliveries", deliveries);


        return "main";
    }


    @PostMapping("/main")
    public String add(@RequestParam String nameOfCommodity,
                      @RequestParam String dateOfDelivery,
                      @RequestParam String addressCity,
                      @RequestParam String addressStreet,
                      @RequestParam String addressNumberOfBuilding,
                      @RequestParam String addressNumberOfApartament,
                      @RequestParam String commodityPrise,

                      @AuthenticationPrincipal User user,
                      Map<String, Object> model,
                      @RequestParam("file")MultipartFile file) throws IOException {

        Double commodityPriseDouble = Double.parseDouble(commodityPrise);
        Delivery delivery = new Delivery(dateOfDelivery, nameOfCommodity, addressCity, addressStreet,
                addressNumberOfBuilding, addressNumberOfApartament, commodityPriseDouble,user);




        if(file!=null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadpath);

            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();


            file.transferTo(new File(uploadpath + "/" + resultFileName));
            delivery.setFilename(resultFileName);
        }
        deliveryRepo.save(delivery);

        Iterable<Delivery> deliveries = deliveryRepo.findAll();

        model.put("deliveries", deliveries);

        return "main";
    }

    @Transactional
    @PostMapping("/delete/{id}")
    public String deleteDelivery(@PathVariable("id") Integer  id,
                             Map<String, Object> model) {

            deliveryRepo.deleteById(id);

        return "redirect:/main";
    }

    @Transactional
    @PostMapping("/editD/{id}")
    public String editDateOfDelivery(@PathVariable("id") Integer  id,
                             @RequestParam String newDate,
                             Map<String, Object> model) {


        deliveryRepo.findById(id).setDateOfDelivery(newDate);

        return "redirect:/main";
    }

    @Transactional
    @PostMapping("/editA/{id}")
    public String editAddressOfDelivery(@PathVariable("id") Integer  id,
                                     @RequestParam String newAddress,
                                     Map<String, Object> model) {

        String[] addressTab = newAddress.split(" ");

        if(!addressTab[0].equals("Wroclaw")){
            addressTab[0] = "Nie";
            addressTab[1] = " dowaozimy ";
            addressTab[2] = " tam ";
            addressTab[3] = " !";
        }

        deliveryRepo.findById(id).setAddressCity(addressTab[0]);
        deliveryRepo.findById(id).setAddressStreet(addressTab[1]);
        deliveryRepo.findById(id).setAddressNumberOfBuilding(addressTab[2]);
        deliveryRepo.findById(id).setAddressNumberOfApartament(addressTab[3]);

        return "redirect:/main";
    }

    @Transactional
    @PostMapping("/editI/{id}")
    public String editIsDelivered(@PathVariable("id") Integer  id,
                                        Map<String, Object> model) {



        deliveryRepo.findById(id).setThatIsDelivered();
        return "redirect:/main";
    }


}
