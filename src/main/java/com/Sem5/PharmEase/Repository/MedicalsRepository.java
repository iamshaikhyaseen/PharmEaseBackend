package com.Sem5.PharmEase.Repository;

import com.Sem5.PharmEase.Models.Medicals;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MedicalsRepository extends MongoRepository<Medicals, String> {

    Optional<Medicals> findById(String id);
    Optional<Medicals> findByEmail(String email);
    Optional<Medicals> findByGstin(String gstin);
    Optional<Medicals> findByDlNo(String dlNo);

    List<Medicals> findByNameContainingIgnoreCase(String name);
    List<Medicals> findByRegionOrderByRegionAsc(String region);
    List<Medicals> findByEmailContainingIgnoreCase(String email);
    List<Medicals> findByGstinContainingIgnoreCase(String gstin);
    List<Medicals> findByDlNoContainingIgnoreCase(String dlNo);
}
