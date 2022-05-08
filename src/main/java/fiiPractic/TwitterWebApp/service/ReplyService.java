package fiiPractic.TwitterWebApp.service;

import fiiPractic.TwitterWebApp.model.dto.ReplyCreateDto;
import fiiPractic.TwitterWebApp.model.entity.ReplyEntity;


public interface ReplyService {


    void replyPost(ReplyCreateDto replyCreateDto, Integer postId);

}
