package fiiPractic.TwitterWebApp.repos.Dao;

import fiiPractic.TwitterWebApp.advice.exception.NotFoundException;
import fiiPractic.TwitterWebApp.model.entity.MentionEntity;
import fiiPractic.TwitterWebApp.model.entity.PostEntity;
import fiiPractic.TwitterWebApp.model.entity.ReplyEntity;
import fiiPractic.TwitterWebApp.model.entity.UserEntity;
import fiiPractic.TwitterWebApp.repos.mapper.MentionRowMapper;
import fiiPractic.TwitterWebApp.repos.mapper.ReplyRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MentionDAO {
    private JdbcTemplate jdbcTemplate ;
    @Autowired
    public void setDataSource(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Autowired
    private PostDAO postRepository;

    public void addMention(Integer userId ,Integer postId ){
        jdbcTemplate.update("INSERT INTO T_MENTION (USER_ID , POST_ID) VALUES (?,?)",userId,postId);
    }

    public List<MentionEntity> getMentionOfCurrentUser(Integer userId){
        List<MentionEntity> list = jdbcTemplate.query("SELECT m.ID , m.USER_ID ,u1.USER_NAME , u1.FIRST_NAME, u1.LAST_NAME,\n" +
                "u1.EMAIL , u1.PASSWORD , m.POST_ID, p.MESSAGE , P.TIME,p.USER_ID,\n" +
                "u2.USER_NAME , u2.FIRST_NAME, u2.LAST_NAME,\n" +
                "u2.EMAIL , u2.PASSWORD \n" +
                "FROM T_MENTION AS m\n" +
                "LEFT JOIN T_USER AS u1\n" +
                "ON m.USER_ID = u1.ID\n" +
                "LEFT JOIN T_POST AS p \n" +
                "ON p.ID = m.POST_ID\n" +
                "LEFT JOIN T_USER AS u2\n" +
                "ON p.USER_ID = u2.ID\n" +
                "WHERE m.USER_ID = ?",new MentionRowMapper(),userId);
        if(list.isEmpty()){
            throw new NotFoundException("This user doesn't have any mentions");
        }
        getListWithReplies(list,true);

        return list;
    }
    private void getListWithReplies(List<MentionEntity> list , boolean allPublic) {
        for (MentionEntity mentionEntity : list) {

            List<ReplyEntity> replyEntities = jdbcTemplate.query("SELECT r.id,r.USER_ID,r.message,r.time,r.IS_PUBLIC,\n" +
                    "u.USER_NAME,u.FIRST_NAME,u.LAST_NAME,u.email,u.PASSWORD FROM T_REPLY AS r\n" +
                    "LEFT JOIN T_USER AS u\n" +
                    "ON r.USER_ID = u.id\n" +
                    "WHERE r.POST_ID = ?", new ReplyRowMapper(), mentionEntity.getPostEntity().getId());
            if (allPublic) {
                mentionEntity.getPostEntity().setReplies(replyEntities);
            } else {
                replyEntities.removeIf(replyEntity -> !replyEntity.isPublic());
                mentionEntity.getPostEntity().setReplies(replyEntities);
            }
        }
    }

}
