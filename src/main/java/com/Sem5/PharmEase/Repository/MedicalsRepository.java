package com.Sem5.PharmEase.Repository;

import com.Sem5.PharmEase.Models.Medicals;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MedicalsRepository extends MongoRepository<Medicals, String> {

    Optional<Medicals> findById(String id);
    Optional<Medicals> findByEmail(String email);
}
