package com.example.app.entities;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter @Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "advisor_id", nullable = false, foreignKey = @ForeignKey(name = "fk_client_advisor"))
    private FinancialAdvisor advisor;

    @Column(nullable = false)
    private String fullName;

    @Column(length = 320)
    private String email;

    @Column(length = 40)
    private String phone;

    private LocalDate dateOfBirth;

    @Column(length = 500)
    private String address;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Portfolio portfolio;

    protected Client() { }

    public Client(FinancialAdvisor advisor, String fullName, String email, String phone,
                  LocalDate dateOfBirth, String address, Instant createdAt, Instant updatedAt) {
        this.advisor = advisor;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
