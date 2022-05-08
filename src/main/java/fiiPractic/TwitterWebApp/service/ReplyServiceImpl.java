package fiiPractic.TwitterWebApp.service;

import fiiPractic.TwitterWebApp.model.dto.ReplyCreateDto;
import fiiPractic.TwitterWebApp.model.entity.ReplyEntity;
import fiiPractic.TwitterWebApp.repos.Dao.ReplyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService{

    @Autowired
    private ReplyDAO replyRepository;

    @Override
    public void replyPost(ReplyCreateDto replyCreateDto, Integer postId) {
        replyRepository.replyPost(replyCreateDto,postId);
    }
}
