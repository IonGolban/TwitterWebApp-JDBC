package fiiPractic.TwitterWebApp.model.dto;

import fiiPractic.TwitterWebApp.model.entity.PostEntity;
import fiiPractic.TwitterWebApp.model.entity.UserEntity;
import fiiPractic.TwitterWebApp.repos.Dao.UserDAO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MentionDto {
    private Integer id;
    private UserDto userDto;
    private PostDto postDto;
}
