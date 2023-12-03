package com.musicPlayer.musicPlayerApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicPlayer.musicPlayerApp.Model.Admin;
import com.musicPlayer.musicPlayerApp.Model.AutheticationToken;
import com.musicPlayer.musicPlayerApp.Model.Dto.signInInput;
import com.musicPlayer.musicPlayerApp.Model.Dto.signInOutput;
import com.musicPlayer.musicPlayerApp.Repository.AdminRepository;
import com.musicPlayer.musicPlayerApp.Service.Utility.EmailHandler;
import com.musicPlayer.musicPlayerApp.Service.Utility.PasswordEncrypter;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepo;
    @Autowired
    AuthenticationService authService;

    /**
     * Service Function to Register a Admin
     * @param admin
     * @return
     */

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

    /**
     * Service Function To Sign in as a Admin 
     * @param signInput
     * @return
     */

    public String signInAdmin(signInInput signInput)
    {
        String signInStatusMessage = null;
        String signInMail =  signInput.getEmail();
        //check weather the email exists 
        if( signInMail == null) {
            signInStatusMessage = "Invalid email";
            return signInStatusMessage;
        }
        Admin existingAdmin = adminRepo.findFirstByAdminEmail(signInMail);
        if(existingAdmin == null) {
            signInStatusMessage = "Email not registered!!!";
            return signInStatusMessage;
        }
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(signInput.getPassword());
            if(existingAdmin.getAdminPassword().equals(encryptedPassword)) {
                AutheticationToken authToken = new AutheticationToken(existingAdmin);
                authService.saveAuthToken(authToken);
                EmailHandler.sendEmail("emailtest@gmail.com","email testing",authToken.getAdminToken());
                return "Token sent to your email";
            } else {
                signInStatusMessage = "Invalid credentials!!!";
                return signInStatusMessage;
            }

        } catch (Exception e) {
            signInStatusMessage = "Internal error occurred during sign in";
            return signInStatusMessage;
        }

    }


}
