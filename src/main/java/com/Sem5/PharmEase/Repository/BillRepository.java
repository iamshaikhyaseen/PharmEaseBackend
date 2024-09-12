package com.Sem5.PharmEase.Repository;

import com.Sem5.PharmEase.Models.Bill;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BillRepository extends MongoRepository<Bill, String> {
}
