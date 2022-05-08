package fiiPractic.TwitterWebApp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class FollowEntity {

    private UserEntity user;

    private UserEntity followingUser;

    private Timestamp time ;

}
