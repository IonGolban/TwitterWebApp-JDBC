package fiiPractic.TwitterWebApp.model.dto;

import lombok.Data;

@Data
public class ReplyCreateDto {

   private Integer userId;

   private String Message;

   private Boolean isPublic;

}
