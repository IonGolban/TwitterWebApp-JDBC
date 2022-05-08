package fiiPractic.TwitterWebApp.model.entity;

import fiiPractic.TwitterWebApp.repos.util.converter.PostConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
public class LikeEntity {

    private UserEntity userEntity;

    private PostEntity postEntity   ;

    private Timestamp timestamp;

}
