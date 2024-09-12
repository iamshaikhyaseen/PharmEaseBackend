package com.Sem5.PharmEase.Controller;

import com.Sem5.PharmEase.Models.LoginRequest;
import com.Sem5.PharmEase.Models.Medicals;
import com.Sem5.PharmEase.Repository.MedicalsRepository;
import com.Sem5.PharmEase.ResourceNotFoundException;
import com.Sem5.PharmEase.Service.MedicalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Medicals createMedical(@RequestBody Medicals medicals){
        return medicalService.createMedical(medicals);
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
    public ResponseEntity<Medicals> updateProduct(@PathVariable String id, @RequestBody Medicals medicalDetails){
        try {
            Medicals updatedMedical=medicalService.updateMedical(id,medicalDetails);
            return ResponseEntity.ok(updatedMedical);
        } catch (ResourceNotFoundException e){
            return ResponseEntity.notFound().build();

        }
    }
}
