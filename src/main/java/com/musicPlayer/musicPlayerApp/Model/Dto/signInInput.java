package com.musicPlayer.musicPlayerApp.Model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class signInInput 
{
    private String email;
    private String password;
    
}
