package fiiPractic.TwitterWebApp.service;

public interface LikeService {

    void likePost(Integer userId,Integer postId);
    void removeLikeByPostId(Integer userId,Integer postId);
}
