package com.musicPlayer.musicPlayerApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.musicPlayer.musicPlayerApp.Model.Admin;
import com.musicPlayer.musicPlayerApp.Model.Dto.signInOutput;
import com.musicPlayer.musicPlayerApp.Service.AdminService;


@RestController
@Validated

public class AdminController {
    @Autowired
    AdminService adminService;

    /**
     * Controller Function to Sign in and Sign Out  As Admin
     */

    @PostMapping("admin/signup")
    public signInOutput signUpAppAdmin(@RequestBody Admin admin)
    {
        return adminService.signUpAdmin(admin);
    }
    


}
