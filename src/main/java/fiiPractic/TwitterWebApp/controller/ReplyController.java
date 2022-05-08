package fiiPractic.TwitterWebApp.controller;

import fiiPractic.TwitterWebApp.model.dto.ReplyCreateDto;
import fiiPractic.TwitterWebApp.model.entity.ReplyEntity;
import fiiPractic.TwitterWebApp.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @PostMapping(value = "/posts/id={postId}/reply",produces = "application/json")
    public void replyPost(@RequestBody ReplyCreateDto replyCreateDto, @PathVariable Integer postId){
        replyService.replyPost(replyCreateDto,postId);
    }

}
