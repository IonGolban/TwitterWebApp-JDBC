package fiiPractic.TwitterWebApp.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostEntity {

    private Integer id ;

    private UserEntity userEntity;

    private String message ;

    private Timestamp time ;

    private List<ReplyEntity> replies ;

    public PostEntity(Integer id, UserEntity user, String message, Timestamp time){
            this.id= id ;
            this.userEntity= user;
            this.message = message ;
            this.time = time ;
    }

}
