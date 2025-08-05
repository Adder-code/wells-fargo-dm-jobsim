package com.example.app.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "holdings",
       indexes = {
           @Index(name = "ix_holding_portfolio", columnList = "portfolio_id"),
           @Index(name = "ix_holding_security", columnList = "security_id")
       })
@Getter @Setter
public class Holding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long holdingId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false,
                foreignKey = @ForeignKey(name = "fk_holding_portfolio"))
    private Portfolio portfolio;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "security_id", nullable = false,
                foreignKey = @ForeignKey(name = "fk_holding_security"))
    private Security security;

    @Column(nullable = false)
    private LocalDate purchaseDate;

    @Column(nullable = false, precision = 18, scale = 6)
    private BigDecimal purchasePrice;

    @Column(nullable = false, precision = 28, scale = 8)
    private BigDecimal quantity;

    @Column(length = 1000)
    private String notes;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    protected Holding() { }

    public Holding(Portfolio portfolio, Security security, LocalDate purchaseDate,
                   BigDecimal purchasePrice, BigDecimal quantity, String notes,
                   Instant createdAt, Instant updatedAt) {
        this.portfolio = portfolio;
        this.security = security;
        this.purchaseDate = purchaseDate;
        this.purchasePrice = purchasePrice;
        this.quantity = quantity;
        this.notes = notes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
