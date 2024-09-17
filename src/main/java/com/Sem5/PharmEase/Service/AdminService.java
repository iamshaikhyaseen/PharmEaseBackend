package com.Sem5.PharmEase.Service;

import com.Sem5.PharmEase.Models.Admin;
import com.Sem5.PharmEase.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public Admin createAdmin(Admin admin){
        String hashedPassword=encoder.encode(admin.getPassword());
        admin.setPassword(hashedPassword);
        return adminRepository.save(admin);
    }

    public Admin authenticateAdmin(String email,String password){
        Optional<Admin>optionalAdmin=adminRepository.findByEmail(email);
        if (optionalAdmin.isPresent()){
            Admin admin=optionalAdmin.get();
            if (encoder.matches(password,admin.getPassword())){
                return admin;
            }
        }
        throw new RuntimeException("Invalid email or password");
    }
}
