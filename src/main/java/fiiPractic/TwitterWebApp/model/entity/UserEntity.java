package fiiPractic.TwitterWebApp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserEntity {

    private Integer id ;

    private String userName;

    private String firstName;

    private String lastName;

    private String email;

    private String password;


}
