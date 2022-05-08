package fiiPractic.TwitterWebApp.service;

import fiiPractic.TwitterWebApp.model.dto.MentionDto;
import fiiPractic.TwitterWebApp.model.entity.MentionEntity;
import fiiPractic.TwitterWebApp.repos.Dao.LikeDAO;
import fiiPractic.TwitterWebApp.repos.Dao.MentionDAO;
import fiiPractic.TwitterWebApp.repos.util.converter.MentionConverter;
import fiiPractic.TwitterWebApp.repos.util.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MentionServiceImpl implements MentionService{

    @Autowired
    private MentionDAO mentionRepository;

    @Autowired
    private LikeDAO likeRepository ;

    @Override
    public void addMention(Integer userId, Integer postId) {
        mentionRepository.addMention(userId,postId);
    }

    @Override
    public List<MentionDto> getMentionOfCurrentUser(Integer userId) {
        List<MentionDto> list = mentionRepository.getMentionOfCurrentUser(userId).stream()
                .map(MentionConverter::toMentionDto)
                .collect(Collectors.toList());
        list.forEach(mentionDto -> {
            mentionDto.getPostDto().setUsersWhoLikedPost(likeRepository.getUsersByLikedPost(mentionDto.getPostDto().getId()).stream()
                    .map(UserConverter::toUserDto)
                    .collect(Collectors.toList()));
        });

        return list ;
    }
}
