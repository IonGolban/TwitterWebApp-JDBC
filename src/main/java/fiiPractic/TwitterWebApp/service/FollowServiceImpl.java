package fiiPractic.TwitterWebApp.service;

import fiiPractic.TwitterWebApp.repos.Dao.FollowDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowDAO followRepository;

    @Override
    public void followUser(Integer userId, Integer followingUserId){
        followRepository.followUser(userId,followingUserId);
    }

    @Override
    public void unfollowUser(Integer userId, Integer followingUserId) {
        followRepository.unfollowUser(userId,followingUserId);
    }
}
