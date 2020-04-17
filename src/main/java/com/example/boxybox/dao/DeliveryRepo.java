package com.example.boxybox.dao;

import com.example.boxybox.domain.Delivery;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeliveryRepo extends CrudRepository<Delivery, Long> {

    Delivery findById(Integer id);

    List<Delivery> deleteById(Integer id);

}
