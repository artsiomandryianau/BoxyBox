package com.example.boxybox.repos;

import com.example.boxybox.domain.Delivery;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *
 * @author Artsiom Andryianau
 *
 */
public interface DeliveryRepo extends CrudRepository<Delivery, Long> {

    Delivery findById(Integer id);

    List<Delivery> deleteById(Integer id);

}
