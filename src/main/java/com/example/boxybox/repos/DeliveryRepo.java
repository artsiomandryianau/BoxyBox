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


    /**
     *
     * @param id - id of delivery
     * @return delivery
     */
    Delivery findById(Integer id);


    /**
     *
     * @param id - id of delivery
     * @return list of deliveries
     */
    List<Delivery> deleteById(Integer id);

}
