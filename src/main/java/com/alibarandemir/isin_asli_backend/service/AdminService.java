package com.alibarandemir.isin_asli_backend.service;

import com.alibarandemir.isin_asli_backend.dto.CompanyRequestDto;
import com.alibarandemir.isin_asli_backend.dto.DashboardStatsDto;
import com.alibarandemir.isin_asli_backend.dto.ResponseDto;
import com.alibarandemir.isin_asli_backend.entity.Candidate;
import com.alibarandemir.isin_asli_backend.entity.Company;
import com.alibarandemir.isin_asli_backend.repository.CandidateRepository;
import com.alibarandemir.isin_asli_backend.repository.CompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {

    private final CandidateRepository candidateRepository;
    private final CompanyRepository companyRepository;

    public AdminService(CandidateRepository candidateRepository, CompanyRepository companyRepository) {
        this.candidateRepository = candidateRepository;
        this.companyRepository = companyRepository;
    }

    public ResponseDto<DashboardStatsDto> getDashboardStats() {
        long totalUsers = candidateRepository.count();
        long activeUsers = candidateRepository.countByEnabled(true);
        long totalCompanies = companyRepository.count();
        long activeCompanies = companyRepository.countByIsActive(true);

        DashboardStatsDto stats = new DashboardStatsDto(
            totalUsers,
            activeUsers,
            totalCompanies,
            activeCompanies
        );

        return ResponseDto.success(stats, "Dashboard istatistikleri başarıyla getirildi.");
    }

    public ResponseDto<Page<Candidate>> getAllUsers(int page, int size) {
        Page<Candidate> users = candidateRepository.findAll(PageRequest.of(page, size));
        return ResponseDto.success(users, "Kullanıcılar başarıyla getirildi.");
    }

    @Transactional
    public ResponseDto<?> updateUserStatus(Long id, boolean active) {
        Candidate user = candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı."));

        user.setEnabled(active);
        candidateRepository.save(user);

        return ResponseDto.success(null, "Kullanıcı durumu güncellendi.");
    }

    public ResponseDto<Page<Company>> getAllCompanies(int page, int size) {
        Page<Company> companies = companyRepository.findAll(PageRequest.of(page, size));
        return ResponseDto.success(companies, "Şirketler başarıyla getirildi.");
    }

    @Transactional
    public ResponseDto<?> createCompany(CompanyRequestDto request) {
        Company company = new Company();
        company.setName(request.getName());
        company.setDescription(request.getDescription());
        company.setActive(true);

        companyRepository.save(company);
        return ResponseDto.success(null, "Şirket başarıyla oluşturuldu.");
    }

    @Transactional
    public ResponseDto<?> updateCompany(Long id, CompanyRequestDto request) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Şirket bulunamadı."));

        company.setName(request.getName());
        company.setDescription(request.getDescription());

        companyRepository.save(company);
        return ResponseDto.success(null, "Şirket başarıyla güncellendi.");
    }

    @Transactional
    public ResponseDto<?> deleteCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Şirket bulunamadı."));

        companyRepository.delete(company);
        return ResponseDto.success(null, "Şirket başarıyla silindi.");
    }
} 