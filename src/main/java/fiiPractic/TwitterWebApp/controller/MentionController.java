package fiiPractic.TwitterWebApp.controller;

import fiiPractic.TwitterWebApp.model.dto.MentionDto;
import fiiPractic.TwitterWebApp.model.entity.MentionEntity;
import fiiPractic.TwitterWebApp.service.MentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MentionController {
    @Autowired
    private MentionService mentionService;

    @PostMapping (value = "/users/id={userId}/posts/id={postId}/mention", produces = "application/json" )
    public void addMention(@PathVariable Integer userId,@PathVariable Integer postId){
        mentionService.addMention(userId,postId);
    }

    @GetMapping(value = "/users/id={userId}/posts/mentions",produces = "application/json" )
    public List<MentionDto> getMentionOfCurrentUser(@PathVariable Integer userId){
        return mentionService.getMentionOfCurrentUser(userId);
    }

}
