package com.Sem5.PharmEase.Repository;

import com.Sem5.PharmEase.Models.Medicals;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MedicalsRepository extends MongoRepository<Medicals, ObjectId> {

    Optional<Medicals> findById(ObjectId id);
    Optional<Medicals> findByDlNo(String dlNo);
    Optional<Medicals> findByEmail(String email);
}
