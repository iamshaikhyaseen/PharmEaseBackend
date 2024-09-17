package com.Sem5.PharmEase.Controller;

import com.Sem5.PharmEase.Models.LoginRequest;
import com.Sem5.PharmEase.Models.Medicals;
import com.Sem5.PharmEase.Repository.MedicalsRepository;
import com.Sem5.PharmEase.ResourceNotFoundException;
import com.Sem5.PharmEase.Service.MedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicals")
public class MedicalController {
    @Autowired
    private MedicalService medicalService;
    private MedicalsRepository medicalsRepository;
    private BCryptPasswordEncoder encoder;
    @PostMapping
    public ResponseEntity<String> createMedical(@RequestBody Medicals medicals) {
        try {
            medicalService.createMedical(medicals);
            return ResponseEntity.status(HttpStatus.CREATED).body("Medical created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        Optional<Medicals> optionalMedical = medicalService.findMedicalByEmail(loginRequest.getEmail());
        if (optionalMedical.isPresent()) {
            Medicals medical = optionalMedical.get();
            if (medicalService.validatePassword(loginRequest.getPassword(), medical.getPassword())) {
                return optionalMedical
                        .map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
            }
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @GetMapping
    public List<Medicals> getAllMedicals(){
        return medicalService.getAllMedicals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicals> getMedicalById(@PathVariable String id){
        Optional<Medicals> medical=medicalService.findMedicalById(id);
        medical.get().setPassword(null);
        return medical
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/login/{email}")
    public ResponseEntity<Medicals> findMedicalByEmail(@PathVariable String email){
        Optional<Medicals> medical= medicalService.findMedicalByEmail(email);
        return medical
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedical(@PathVariable String id){
        try {
            medicalService.deleteMedical(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody Medicals medicalDetails){
        try {
            Medicals updatedMedical=medicalService.updateMedical(id,medicalDetails);
            return ResponseEntity.ok(updatedMedical);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Medicals>> searchMedicals(@RequestParam String query) {
        List<Medicals> results = medicalService.searchMedicals(query);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/search/dlno")
    public ResponseEntity<List<Medicals>> searchByDlNo(@RequestParam String dlNo) {
        List<Medicals> results = medicalService.searchByDlNo(dlNo);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/search/gstin")
    public ResponseEntity<List<Medicals>> searchByGstIn(@RequestParam String gstin) {
        List<Medicals> results = medicalService.searchByGstin(gstin);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/search/email")
    public ResponseEntity<List<Medicals>> searchByEmail(@RequestParam String email) {
        List<Medicals> results = medicalService.searchByEmail(email);
        return ResponseEntity.ok(results);
    }

    // Sort medicals by region
    @GetMapping("/sort")
    public ResponseEntity<List<Medicals>> sortMedicalsByRegion(@RequestParam String region) {
        List<Medicals> sortedMedicals = medicalService.sortMedicalsByRegion(region);
        return ResponseEntity.ok(sortedMedicals);
    }
}
