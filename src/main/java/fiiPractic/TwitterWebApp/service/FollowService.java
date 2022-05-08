package fiiPractic.TwitterWebApp.service;

public interface FollowService {
    void followUser(Integer userId , Integer followingUserId);

    void unfollowUser(Integer userId , Integer followingUserId);
}
