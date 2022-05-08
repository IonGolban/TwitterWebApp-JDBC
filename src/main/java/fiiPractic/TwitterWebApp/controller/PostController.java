package fiiPractic.TwitterWebApp.controller;

import fiiPractic.TwitterWebApp.model.dto.PostCreateDto;
import fiiPractic.TwitterWebApp.model.dto.PostDto;
import fiiPractic.TwitterWebApp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService ;

    @GetMapping(value = "/posts",produces = "application/json")
    public ResponseEntity<List<PostDto>> getAllPosts(){
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    @PostMapping(value = "/post", produces = "application/json")
    public void addPost(@RequestBody PostCreateDto post ){
         postService.addPost(post);

    }

    @GetMapping(value = "/posts/id={userId}",produces = "application/json")
    public ResponseEntity<List<PostDto>> getPostsByUserId(@PathVariable Integer userId){
        return new ResponseEntity<>(postService.getPostByUserId(userId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/post/delete/{id}", produces = "application/json")
    public HttpStatus deletePostById(@PathVariable Integer id ){
        postService.deletePostById(id);
        return HttpStatus.OK;
    }

    @GetMapping(value = "/posts/id={userId}/feed", produces = "application/json")
    public ResponseEntity<List<PostDto>> getFeed(@PathVariable Integer userId){
        return new ResponseEntity<>(postService.getFeed(userId),HttpStatus.OK);
    }

    @PostMapping(value = "/users/id={userId}/repost/{postId}", produces = "application/json")
    public void addAnExistingPost(@PathVariable Integer userId,@PathVariable Integer postId){
        postService.copyAnExistingPost(userId,postId);

    }

}
