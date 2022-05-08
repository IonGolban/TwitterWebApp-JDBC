package fiiPractic.TwitterWebApp.repos.util.converter;

import fiiPractic.TwitterWebApp.model.dto.PostDto;
import fiiPractic.TwitterWebApp.model.entity.PostEntity;
import fiiPractic.TwitterWebApp.repos.util.converter.UserConverter;

import java.util.Optional;

public class PostConverter {

        public static PostDto toPostDto(PostEntity postEntity){
            return PostDto.builder()
                    .id(postEntity.getId())
                    .user(Optional.ofNullable(postEntity.getUserEntity())
                        .map(UserConverter::toUserDto)
                        .orElse(null))
                    .message(postEntity.getMessage())
                    .time(postEntity.getTime())
                    .replies(Optional.ofNullable(postEntity.getReplies())
                            .map(ReplyConverter::toReplyDto)
                            .orElse(null))
                    .build();
        }
}
