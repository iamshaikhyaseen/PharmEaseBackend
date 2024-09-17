package com.Sem5.PharmEase.Repository;

import com.Sem5.PharmEase.Models.Sales;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface SalesRepository extends MongoRepository<Sales,String> {
    List<Sales> findByDateBetween(Date startDate, Date endDate);
}
