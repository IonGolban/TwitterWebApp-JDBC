package fiiPractic.TwitterWebApp.model.entity;

import fiiPractic.TwitterWebApp.model.entity.PostEntity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter

public class ReplyEntity extends PostEntity
 {

    private boolean isPublic ;


     public ReplyEntity(Integer id, UserEntity user, String message, Timestamp time) {
         super(id, user, message, time);
     }
 }
