package fiiPractic.TwitterWebApp.controller;

import fiiPractic.TwitterWebApp.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FollowController {

    @Autowired
    private FollowService followService;

    @PostMapping (value = "/users/id={userId}/follow/{followingUserId}", produces = "appplication/json")
    public void followUser(@PathVariable Integer userId, @PathVariable Integer followingUserId){

        followService.followUser(userId,followingUserId);

    }

    @DeleteMapping(value = "/users/id={userId}/unfollow/{followingUserId}",produces = "appplication/json")
    public void unfollowUser(@PathVariable Integer userId, @PathVariable Integer followingUserId){

        followService.unfollowUser(userId,followingUserId);

    }

}
