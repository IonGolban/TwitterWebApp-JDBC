package fiiPractic.TwitterWebApp.service;

import fiiPractic.TwitterWebApp.repos.Dao.LikeDAO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService{

    @Autowired
    private LikeDAO likeRepository ;

    @Override
    public void likePost(Integer userId, Integer postId) {
        likeRepository.likePost(userId,postId);
    }

    @Override
    public void removeLikeByPostId(Integer userId,Integer postId) {
        likeRepository.removeLikeByPostId(userId,postId);
    }

}
