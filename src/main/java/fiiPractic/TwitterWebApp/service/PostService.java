package fiiPractic.TwitterWebApp.service;

import fiiPractic.TwitterWebApp.model.dto.PostCreateDto;
import fiiPractic.TwitterWebApp.model.dto.PostDto;

import java.util.List;

public interface PostService  {

    List<PostDto> getAllPosts();

    void addPost(PostCreateDto postDto);

    List<PostDto> getPostByUserId(Integer id);

    void deletePostById(Integer id);

    List<PostDto> getFeed(Integer userId);

    void copyAnExistingPost(Integer currentUserId, Integer postId);

}
