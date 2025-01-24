package com.mohtashim.sami.lawEntry.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class LawEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientName;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime date;
    private String caseName;
    @Column(length=10000)
    private String description;

    public LawEntry() {

    }

    public LawEntry(Long id, String clientName, LocalDateTime date, String caseName, String description) {
        this.id = id;
        this.clientName = clientName;
        this.date = date;
        this.caseName = caseName;
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getClientName() {
        return clientName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getCaseName() {
        return caseName;
    }

    public String getDescription() {
        return description;
    }
}
