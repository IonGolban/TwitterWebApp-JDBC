package fiiPractic.TwitterWebApp.repos.util.converter;

import fiiPractic.TwitterWebApp.model.dto.MentionDto;
import fiiPractic.TwitterWebApp.model.entity.MentionEntity;

import java.util.Optional;

public class MentionConverter {

    public static MentionDto toMentionDto(MentionEntity mentionEntity){
        return MentionDto.builder()
                .id(mentionEntity.getId())
                .userDto(Optional.ofNullable(mentionEntity.getUserEntity())
                        .map(UserConverter::toUserDto)
                        .orElse(null))
                .postDto(Optional.ofNullable(mentionEntity.getPostEntity())
                        .map(PostConverter::toPostDto)
                        .orElse(null))
                .build();

    }
}
