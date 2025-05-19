package com.alibarandemir.isin_asli_backend.controller;

import com.alibarandemir.isin_asli_backend.dto.CompanyRequestDto;
import com.alibarandemir.isin_asli_backend.dto.ResponseDto;
import com.alibarandemir.isin_asli_backend.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Dashboard istatistikleri
    @GetMapping("/dashboard/stats")
    public ResponseEntity<ResponseDto<?>> getDashboardStats() {
        return ResponseEntity.ok(adminService.getDashboardStats());
    }

    // Kullanıcı yönetimi
    @GetMapping("/users")
    public ResponseEntity<ResponseDto<?>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(adminService.getAllUsers(page, size));
    }

    @PutMapping("/users/{id}/status")
    public ResponseEntity<ResponseDto<?>> updateUserStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {
        return ResponseEntity.ok(adminService.updateUserStatus(id, active));
    }

    // Şirket yönetimi
    @GetMapping("/companies")
    public ResponseEntity<ResponseDto<?>> getAllCompanies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(adminService.getAllCompanies(page, size));
    }

    @PostMapping("/companies")
    public ResponseEntity<ResponseDto<?>> createCompany(@RequestBody CompanyRequestDto request) {
        return ResponseEntity.ok(adminService.createCompany(request));
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<ResponseDto<?>> updateCompany(
            @PathVariable Long id,
            @RequestBody CompanyRequestDto request) {
        return ResponseEntity.ok(adminService.updateCompany(id, request));
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<ResponseDto<?>> deleteCompany(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.deleteCompany(id));
    }
} 