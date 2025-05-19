package com.alibarandemir.isin_asli_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.*;
@Data
@Entity
@Table(name="companies")
@EqualsAndHashCode(callSuper = true)
public class Company extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;
    @Column(name = "sector")
    private String sector;
    @Column(name = "location")
    private String location;
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;

}



