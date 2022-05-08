package fiiPractic.TwitterWebApp.service;

import fiiPractic.TwitterWebApp.model.dto.MentionDto;

import java.util.List;


public interface MentionService {

    void addMention(Integer userId ,Integer postId );

    List<MentionDto> getMentionOfCurrentUser(Integer userId);
}

