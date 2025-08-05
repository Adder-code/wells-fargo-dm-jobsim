package com.example.app.entities;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "financial_advisors",
       uniqueConstraints = @UniqueConstraint(name = "uk_advisor_email", columnNames = "email"))
@Getter @Setter
public class FinancialAdvisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long advisorId;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, length = 320)
    private String email;

    @Column(length = 40)
    private String phone;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "advisor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Client> clients = new ArrayList<>();

    protected FinancialAdvisor() { }

    public FinancialAdvisor(String fullName, String email, String phone, Instant createdAt, Instant updatedAt) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
