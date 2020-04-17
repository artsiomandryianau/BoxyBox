package com.example.boxybox.rs;

import com.example.boxybox.domain.Delivery;
import com.example.boxybox.domain.User;
import com.example.boxybox.dao.DeliveryRepo;
import com.example.boxybox.service.DeliveryService;
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

@Controller
public class MainController {


    @Autowired
    private DeliveryRepo deliveryRepo;

    @Autowired
    private DeliveryService deliveryService;

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
        deliveryService.newDelivery(nameOfCommodity, dateOfDelivery, addressCity, addressStreet, addressNumberOfBuilding, addressNumberOfApartament,
                commodityPrise, user, file);
        model.put("deliveries",  deliveryRepo.findAll());
        return "main";
    }

    @Transactional
    @PostMapping("/delete/{id}")
    public String deleteDelivery(@PathVariable("id") Integer  id, Map<String, Object> model) {
            deliveryRepo.deleteById(id);
        return "redirect:/main";
    }

    @Transactional
    @PostMapping("/editD/{id}")
    public String editDateOfDelivery(@PathVariable("id") Integer  id, @RequestParam String newDate, Map<String, Object> model) {
        deliveryRepo.findById(id).setDateOfDelivery(newDate);
        return "redirect:/main";
    }

    @Transactional
    @PostMapping("/editA/{id}")
    public String editAddressOfDelivery(@PathVariable("id") Integer  id, @RequestParam String newAddress, Map<String, Object> model) {
        deliveryService.editAddress(id, newAddress);
        return "redirect:/main";
    }


}
