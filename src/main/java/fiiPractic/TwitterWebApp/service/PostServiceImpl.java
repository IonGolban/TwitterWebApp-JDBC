package fiiPractic.TwitterWebApp.service;

import fiiPractic.TwitterWebApp.model.dto.PostCreateDto;
import fiiPractic.TwitterWebApp.model.dto.PostDto;
import fiiPractic.TwitterWebApp.repos.Dao.LikeDAO;
import fiiPractic.TwitterWebApp.repos.Dao.PostDAO;
import fiiPractic.TwitterWebApp.repos.util.converter.PostConverter;
import fiiPractic.TwitterWebApp.repos.util.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDAO postRepository;

    @Autowired
    private LikeDAO likeRepository;

    @Override
    public List<PostDto> getAllPosts() {

        List<PostDto>list= postRepository.findAllPost().stream()
                .map(PostConverter::toPostDto)
                .collect(Collectors.toList());

        list.forEach(postDto -> {
            postDto.setUsersWhoLikedPost(likeRepository.getUsersByLikedPost(postDto.getId()).stream()
                    .map(UserConverter::toUserDto)
                    .collect(Collectors.toList()));
        });

        return list ;

    }

    @Override
    public void addPost(PostCreateDto postDto) {
        postRepository.addPost(postDto.getUserId(),postDto.getMessage(),new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public List<PostDto> getPostByUserId(Integer id) {
        List<PostDto> list =
                postRepository.getPostsByUserId(id).stream()
                .map(PostConverter::toPostDto)
                .collect(Collectors.toList());
        list.forEach(postDto -> {
                postDto.setUsersWhoLikedPost(likeRepository.getUsersByLikedPost(postDto.getId()).stream()
                        .map(UserConverter::toUserDto)
                        .collect(Collectors.toList()));
        });
        return list ;
    }

    @Override
    public void deletePostById(Integer id) {
        postRepository.deletePostById(id);
    }

    @Override
    public List<PostDto> getFeed(Integer userId) {
        List<PostDto> list = postRepository.getFeed(userId).stream()
                .map(PostConverter::toPostDto)
                .collect(Collectors.toList());
        list.forEach(postDto -> {
            postDto.setUsersWhoLikedPost(likeRepository.getUsersByLikedPost(postDto.getId()).stream()
                    .map(UserConverter::toUserDto)
                    .collect(Collectors.toList()));
        });
        return list ;
    }

    @Override
    public void copyAnExistingPost(Integer currentUserId, Integer postId) {
        postRepository.copyAnExistingPost(currentUserId,postId);
    }

}
