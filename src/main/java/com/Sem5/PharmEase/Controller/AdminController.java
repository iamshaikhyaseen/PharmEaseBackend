package com.Sem5.PharmEase.Controller;

import com.Sem5.PharmEase.Models.Admin;
import com.Sem5.PharmEase.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admins")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin){
        return adminService.createAdmin(admin);
    }

    @PostMapping("/login")
    public ResponseEntity<Admin>loginAdmin(@RequestBody Admin adminDetails){
        try {
            Admin admin=adminService.authenticateAdmin(adminDetails.getEmail(), adminDetails.getPassword());
            return ResponseEntity.ok(admin);
        }catch (Exception e){
            return ResponseEntity.status(401).body(null);
        }
    }
}
