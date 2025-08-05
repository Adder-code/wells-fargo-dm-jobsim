package com.example.app.entities;

import javax.persistence.*;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "audit_logs",
       indexes = {
           @Index(name = "ix_audit_entity", columnList = "entityType,entityId"),
           @Index(name = "ix_audit_timestamp", columnList = "timestamp")
       })
@Getter @Setter
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditId;

    @Column(nullable = false, length = 32)
    private String actorType; // ADVISOR or SYSTEM

    @Column
    private Long actorId; // nullable

    @Column(nullable = false, length = 32)
    private String action; // CREATE, UPDATE, DELETE

    @Column(nullable = false, length = 64)
    private String entityType; // e.g. Client, Holding

    @Column(nullable = false)
    private Long entityId;

    @Column(nullable = false)
    private Instant timestamp;

    @Lob
    private String details; // JSON text

    protected AuditLog() { }

    public AuditLog(String actorType, Long actorId, String action,
                    String entityType, Long entityId, Instant timestamp, String details) {
        this.actorType = actorType;
        this.actorId = actorId;
        this.action = action;
        this.entityType = entityType;
        this.entityId = entityId;
        this.timestamp = timestamp;
        this.details = details;
    }
}
