package com.alibarandemir.isin_asli_backend.service.auth;
import com.alibarandemir.isin_asli_backend.dto.LoginRequestDto;
import com.alibarandemir.isin_asli_backend.dto.RegisterRequestDto;
import com.alibarandemir.isin_asli_backend.dto.ResponseDto;
import com.alibarandemir.isin_asli_backend.entity.Candidate;
import com.alibarandemir.isin_asli_backend.repository.CandidateRepository;
import com.alibarandemir.isin_asli_backend.utils.JwtUtil;
import com.alibarandemir.isin_asli_backend.service.CloudinaryService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.alibarandemir.isin_asli_backend.enums.Role;
import com.alibarandemir.isin_asli_backend.service.auth.EmailService;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthService {

    private final CandidateRepository candidateRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final CloudinaryService cloudinaryService;

    public AuthService(CandidateRepository candidateRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager, EmailService emailService, CloudinaryService cloudinaryService) {
        this.candidateRepository = candidateRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.emailService = emailService;
        this.cloudinaryService = cloudinaryService;
    }

    public ResponseDto<String> register(RegisterRequestDto request) {
        if (candidateRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseDto.fail("Bu e-posta ile kayıtlı kullanıcı zaten var.");
        }

        try {
            Candidate user = new Candidate();
            user.setName(request.getName());
            user.setSurname(request.getSurname());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(Role.USER);

            if (request.getCandidatePhoto() != null && !request.getCandidatePhoto().isEmpty()) {
                Map<String, String> uploadResult = cloudinaryService.uploadProfilePhoto(request.getCandidatePhoto());
                user.setPhotoUrl(uploadResult.get("url"));
                user.setPhotoPublicId(uploadResult.get("public_id"));
                user.setPhotoName(request.getCandidatePhoto().getOriginalFilename());
            }

            candidateRepository.save(user);

            String token = jwtUtil.generateToken(user.getEmail());
            return ResponseDto.success(token, "Kayıt başarılı");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.fail("Kayıt sırasında bir hata oluştu: " + e.getMessage());
        }
    }

    public ResponseDto<String> login(LoginRequestDto request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            return ResponseDto.fail("Email veya şifre hatalı.");
        }

        Candidate user = candidateRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı."));

        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseDto.success(token, "Giriş başarılı.");
    }

    public ResponseDto<String> forgotPassword(String email){
        Candidate user = candidateRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı."));
        String token = UUID.randomUUID().toString(); // basit ama yeterli
        user.setResetToken(token);
        user.setResetTokenExpiration(LocalDateTime.now().plusMinutes(30)); // 30 dakika geçerli
        candidateRepository.save(user);
        emailService.sendResetPasswordEmail(user.getEmail(),token);
        return ResponseDto.success(null,"Şifre sıfırlama bağlantısı e-posta adresinize gönderildi.");


    }
    public ResponseDto<String> resetPassword(String token,String newPassword){
        Candidate user= candidateRepository.findByResetToken(token).orElseThrow(()->new RuntimeException("Geçersiz veya süresi dolmuş token"));
        if(user.getResetTokenExpiration()==null|| user.getResetTokenExpiration().isBefore(LocalDateTime.now())){
            return ResponseDto.fail("Süresi dolmuş token");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setResetTokenExpiration(null);
        candidateRepository.save(user);

        return ResponseDto.success(null, "Şifre başarıyla güncellendi.");
    }
}

