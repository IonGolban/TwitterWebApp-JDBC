package fiiPractic.TwitterWebApp.controller;

import fiiPractic.TwitterWebApp.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping(value = "/users/id={userId}/posts/like/id={postId}", produces = "application/json")
        public void likePost(@PathVariable Integer userId,@PathVariable Integer postId){
            likeService.likePost(userId,postId);
        }

    @DeleteMapping(value = "/users/id={userId}/posts/removeLike/id={postId}", produces = "application/json")
        public void removeLike(@PathVariable Integer userId,@PathVariable Integer postId){
            likeService.removeLikeByPostId(userId,postId);
        }

}
