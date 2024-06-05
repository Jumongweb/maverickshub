package com.maverickstube.maverickshub.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;
import static java.time.LocalDateTime.now;

@Entity
@Getter
@Setter
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String description;
    @Enumerated(STRING)
    private Category category;
    @Setter(AccessLevel.NONE)
    private LocalDateTime timeCreated;
    @ManyToOne
    private User uploader;

    @PrePersist
    public void setTimeCreated(){
        this.timeCreated = now();
    }

}
