package fiiPractic.TwitterWebApp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PostDto {

    //TODO UUID
    private Integer id ;

    private UserDto user ;

    private String message ;

    private Timestamp time ;

    private List<UserDto> usersWhoLikedPost;

    private List<ReplyDto> replies;


    PostDto(Integer id ,UserDto user, String message, Timestamp time) {
        this.id = id;
        this.user = user ;
        this.message = message;
        this.time = time ;
    }
}


