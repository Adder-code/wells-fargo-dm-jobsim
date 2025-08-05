package com.example.app.entities;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "securities",
       indexes = {
           @Index(name = "ix_security_ticker", columnList = "tickerSymbol"),
           @Index(name = "ix_security_isin", columnList = "isin")
       })
@Getter @Setter
public class Security {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long securityId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category; // e.g. Equity, Bond, ETF

    @Column(length = 32)
    private String tickerSymbol;

    @Column(length = 32)
    private String isin;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "security", fetch = FetchType.LAZY)
    private List<Holding> holdings = new ArrayList<>();

    protected Security() { }

    public Security(String name, String category, String tickerSymbol, String isin,
                    Instant createdAt, Instant updatedAt) {
        this.name = name;
        this.category = category;
        this.tickerSymbol = tickerSymbol;
        this.isin = isin;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
