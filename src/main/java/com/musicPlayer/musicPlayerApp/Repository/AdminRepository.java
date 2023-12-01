package com.musicPlayer.musicPlayerApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musicPlayer.musicPlayerApp.Model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    Admin findFirstByAdminEmail(String newEmail);
}
