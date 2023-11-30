package com.musicPlayer.musicPlayerApp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class signInOutput 
{
    private boolean signUpStatus;
    private String signUpMessage;
    
}
