package com.Sem5.PharmEase.Service;

import com.Sem5.PharmEase.Models.Medicals;
import com.Sem5.PharmEase.Repository.MedicalsRepository;
import com.Sem5.PharmEase.ResourceNotFoundException;
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

    public Medicals createMedical(Medicals medicals) throws Exception{
        Optional<Medicals> existingmedicalByEmail=medicalsRepository.findByEmail(medicals.getEmail());

        if (existingmedicalByEmail.isPresent()){
            throw new Exception("Email already in use");
        }

        Optional<Medicals> existingMedicalByGstIn =medicalsRepository.findByGstin(medicals.getGstin());
        if (existingMedicalByGstIn.isPresent()) {
            throw new Exception("GSTIN is already in use.");
        }

        Optional<Medicals> existingMedicalByDlNo =medicalsRepository.findByDlNo(medicals.getDlNo());
        if (existingMedicalByDlNo.isPresent()) {
            throw new Exception("DlNo is already in use.");
        }

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

    public Optional<Medicals> findMedicalById(String id){
        return medicalsRepository.findById(id);
    }

    public void deleteMedical(String id){
        medicalsRepository.deleteById(id);
    }


    public Optional<Medicals> findMedicalByEmail(String email){
        return medicalsRepository.findByEmail(email);
    }

    public Medicals updateMedical(String id,Medicals medicalDetails) throws Exception{

        if (medicalsRepository.findByDlNo(medicalDetails.getDlNo()).isPresent()) {
            throw new Exception("DlNo already exists.");
        }
        if (medicalsRepository.findByGstin(medicalDetails.getGstin()).isPresent()) {
            throw new Exception("GSTIN already exists.");
        }
        return medicalsRepository.findById(id).map(medicals -> {
            medicals.setName(medicalDetails.getName());
            medicals.setAddress(medicalDetails.getAddress());
            medicals.setRegion(medicalDetails.getRegion());
            medicals.setDlNo(medicalDetails.getDlNo());
            medicals.setGstin(medicalDetails.getGstin());
            if (medicalDetails.getPassword() != null && !medicalDetails.getPassword().isEmpty()) {
                String hashedPassword = encoder.encode(medicalDetails.getPassword());
                medicals.setPassword(hashedPassword); // Save the hashed password
            }
            return medicalsRepository.save(medicals);
        }).orElseThrow(()->new ResourceNotFoundException("Medical not found with id"+id));

    }

    public List<Medicals> searchMedicals(String query) {
        return medicalsRepository.findByNameContainingIgnoreCase(query);
    }

    // Search by DlNo
    public List<Medicals> searchByDlNo(String dlNo) {
        return medicalsRepository.findByDlNoContainingIgnoreCase(dlNo);
    }

    // Search by GSTIN
    public List<Medicals> searchByGstin(String gstin) {
        return medicalsRepository.findByGstinContainingIgnoreCase(gstin);
    }

    // Search by Email
    public List<Medicals> searchByEmail(String email) {
        return medicalsRepository.findByEmailContainingIgnoreCase(email);
    }

    // Sort Medicals by region
    public List<Medicals> sortMedicalsByRegion(String region) {
        return medicalsRepository.findByRegionOrderByRegionAsc(region);
    }
}
