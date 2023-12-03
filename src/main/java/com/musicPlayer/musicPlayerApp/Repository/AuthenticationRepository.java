package com.musicPlayer.musicPlayerApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musicPlayer.musicPlayerApp.Model.Admin;
import com.musicPlayer.musicPlayerApp.Model.AutheticationToken;

@Repository
public interface AuthenticationRepository extends JpaRepository<AutheticationToken,Long> {
    AutheticationToken findFirstByAdminToken(String authTokenValue);
    AutheticationToken findFirstByAdmin(Admin admin);
    
} 