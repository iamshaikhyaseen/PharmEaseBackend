package com.Sem5.PharmEase.Service;

import com.Sem5.PharmEase.Models.Medicals;
import com.Sem5.PharmEase.Repository.MedicalsRepository;
import com.Sem5.PharmEase.ResourceNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalService {
    @Autowired
    private MedicalsRepository medicalsRepository;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

    public Medicals createMedical(Medicals medicals){
        String hashedPassword=encoder.encode(medicals.getPassword());
        medicals.setPassword(hashedPassword);
        return medicalsRepository.save(medicals);
    }
    public boolean validatePassword(String inputPassword, String storedPasswordHash) {
        return encoder.matches(inputPassword, storedPasswordHash);
    }

    public List<Medicals> getAllMedicals(){
        return medicalsRepository.findAll();
    }

    public Optional<Medicals> findMedicalById(ObjectId id){
        return medicalsRepository.findById(id);
    }

    public void deleteMedical(ObjectId id){
        medicalsRepository.deleteById(id);
    }

    public Optional<Medicals> findMedicalByDlNo(String dlNo){
        return medicalsRepository.findByDlNo(dlNo);
    }
    public Optional<Medicals> findMedicalByEmail(String email){
        return medicalsRepository.findByEmail(email);
    }

    public Medicals updateMedical(ObjectId id,Medicals medicalDetails){
        return medicalsRepository.findById(id).map(medicals -> {
            medicals.setName(medicalDetails.getName());
            medicals.setAddress(medicalDetails.getAddress());
            medicals.setRegion(medicalDetails.getRegion());
            medicals.setDlNo(medicalDetails.getDlNo());
            medicals.setGstIn(medicalDetails.getGstIn());
            medicals.setProductsList(medicalDetails.getProductsList());
            return medicalsRepository.save(medicals);
        }).orElseThrow(()->new ResourceNotFoundException("Medical not found with id"+id));

    }
}
