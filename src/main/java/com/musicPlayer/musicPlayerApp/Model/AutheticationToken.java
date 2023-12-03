package com.musicPlayer.musicPlayerApp.Model;


import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class AutheticationToken {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminTokenId;
    private String adminToken;
    private LocalDate adminTokenCreationDate;

    @OneToOne
    @JoinColumn(nullable = false, name = "fk_admin_id")
    private Admin admin;

    public AutheticationToken(Admin admin)
    {
        this.admin = admin;
        this.adminTokenCreationDate = LocalDate.now();
        this.adminToken = UUID.randomUUID().toString();
    }

}
