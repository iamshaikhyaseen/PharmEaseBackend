package com.Sem5.PharmEase.Controller;

import com.Sem5.PharmEase.Models.LoginRequest;
import com.Sem5.PharmEase.Models.Medicals;
import com.Sem5.PharmEase.Repository.MedicalsRepository;
import com.Sem5.PharmEase.ResourceNotFoundException;
import com.Sem5.PharmEase.Service.MedicalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicals")
public class MedicalController {
    @Autowired
    private MedicalService medicalService;
    private MedicalsRepository medicalsRepository;

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
                return ResponseEntity.ok("Login successful");
            }
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @GetMapping
    public List<Medicals> getAllMedicals(){
        return medicalService.getAllMedicals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicals> getMedicalById(@PathVariable ObjectId id){
        Optional<Medicals> medical=medicalService.findMedicalById(id);
        return medical
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @GetMapping("/login/dlno/{dlNo}")
    public ResponseEntity<Medicals> findMedicalByDlNo(@PathVariable String dlNo){
        Optional<Medicals> medical= medicalService.findMedicalByDlNo(dlNo);
        return medical
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }
    @GetMapping("/login/{email}")
    public ResponseEntity<Medicals> findMedicalByEmail(@PathVariable String email){
        Optional<Medicals> medical= medicalService.findMedicalByEmail(email);
        return medical
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedical(@PathVariable ObjectId id){
        try {
            medicalService.deleteMedical(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicals> updateProduct(@PathVariable ObjectId id, @RequestBody Medicals medicalDetails){
        try {
            Medicals updatedMedical=medicalService.updateMedical(id,medicalDetails);
            return ResponseEntity.ok(updatedMedical);
        } catch (ResourceNotFoundException e){
            return ResponseEntity.notFound().build();

        }
    }
}
