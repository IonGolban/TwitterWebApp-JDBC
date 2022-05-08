package fiiPractic.TwitterWebApp.repos.util.converter;

import fiiPractic.TwitterWebApp.model.dto.ReplyDto;
import fiiPractic.TwitterWebApp.model.entity.ReplyEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReplyConverter {

    public static List<ReplyDto> toReplyDto(List<ReplyEntity> replyEntities){
        List<ReplyDto> replyDtoList = new ArrayList<>();
        for(ReplyEntity replyEntity : replyEntities){
            replyDtoList.add( new ReplyDto(
                    replyEntity.getId(),
                    Optional.ofNullable(replyEntity.getUserEntity())
                    .map(UserConverter::toUserDto)
                    .orElse(null),
                    replyEntity.getMessage(),
                    replyEntity.getTime(),
                    replyEntity.isPublic())
            );
        }
        return replyDtoList;
    }
}
