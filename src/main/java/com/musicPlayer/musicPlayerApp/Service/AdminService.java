package com.musicPlayer.musicPlayerApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicPlayer.musicPlayerApp.Model.Admin;
import com.musicPlayer.musicPlayerApp.Model.Dto.signInOutput;
import com.musicPlayer.musicPlayerApp.Repository.AdminRepository;
import com.musicPlayer.musicPlayerApp.Service.Utility.PasswordEncrypter;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepo;

    public signInOutput signUpAdmin(Admin admin){

        boolean signUpStatus = true;
        String signUpStatusMessage = null;
        //getting admin email
        String newEmail = admin.getAdminEmail();
        if(newEmail == null){
            signUpStatusMessage = "Invalid email";
            signUpStatus = false;
            return new signInOutput(signUpStatus, signUpStatusMessage);
        }
        //check if this admin email already exists ??
        Admin existingAdmin = adminRepo.findFirstByAdminEmail(newEmail);
        if(existingAdmin != null){
            signUpStatusMessage = "Email already registered!!!";
            signUpStatus = false;
            return new signInOutput(signUpStatus, signUpStatusMessage);
        }
        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(admin.getAdminPassword());
            //save the admin with the new encrypted password
            admin.setAdminPassword(encryptedPassword);
            adminRepo.save(admin);
            return new signInOutput(signUpStatus, "Admin registered successfully!");
        }
        catch(Exception e) {
            signUpStatusMessage = "Something Went Wrong !";
            signUpStatus = false;
            return new signInOutput(signUpStatus,signUpStatusMessage);
        }
    }
}
