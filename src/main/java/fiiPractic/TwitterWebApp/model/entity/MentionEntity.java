package fiiPractic.TwitterWebApp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MentionEntity {

    private Integer id;

    private UserEntity userEntity;

    private PostEntity postEntity;

}
