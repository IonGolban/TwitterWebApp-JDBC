package fiiPractic.TwitterWebApp.model.dto;

import lombok.Data;

@Data
public class UserRegisterDto {

    private String userName;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
