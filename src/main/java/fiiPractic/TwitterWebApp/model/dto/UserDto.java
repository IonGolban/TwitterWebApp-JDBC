package fiiPractic.TwitterWebApp.model.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@Builder
public class UserDto {

    private Integer id ;

    private String firstName;

    private String lastName;

}
