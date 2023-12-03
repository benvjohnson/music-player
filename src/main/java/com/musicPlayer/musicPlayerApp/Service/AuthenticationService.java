package com.musicPlayer.musicPlayerApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.musicPlayer.musicPlayerApp.Model.AutheticationToken;
import com.musicPlayer.musicPlayerApp.Repository.AuthenticationRepository;


@Service
public class AuthenticationService {

    @Autowired
    AuthenticationRepository authRepo;

    public void saveAuthToken(AutheticationToken authToken) {
        authRepo.save(authToken);
    }
    
}
