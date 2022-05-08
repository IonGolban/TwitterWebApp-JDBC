package fiiPractic.TwitterWebApp.model.dto;

import lombok.*;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class ReplyDto extends PostDto{

   private Boolean isPublic;

    public ReplyDto(Integer id , UserDto user,String message,Timestamp time,Boolean isPublic){
        super(id ,user , message , time);
        this.isPublic=isPublic;

    }
}
