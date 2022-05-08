package fiiPractic.TwitterWebApp.repos.Dao;

import fiiPractic.TwitterWebApp.advice.exception.NotFoundException;
import fiiPractic.TwitterWebApp.model.entity.PostEntity;
import fiiPractic.TwitterWebApp.model.entity.ReplyEntity;
import fiiPractic.TwitterWebApp.repos.mapper.PostRowMapper;
import fiiPractic.TwitterWebApp.repos.mapper.ReplyRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class PostDAO {

    private JdbcTemplate jdbcTemplate ;
    @Autowired
    public void setDataSource(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<PostEntity> getPostsByUserId(Integer id){
        String sql = "SELECT * FROM T_POST AS p\n" +
                "LEFT JOIN T_USER AS U  \n" +
                "ON  P.USER_ID = U.ID \n" +
                "WHERE P.USER_ID = ?\n" +
                "ORDER BY TIME DESC";
        List<PostEntity> list = jdbcTemplate.query(sql,new PostRowMapper(), id);
        if(list.isEmpty()){
            throw new NotFoundException("This user doesn't have any posts");
        }
        getListWithReplies(list,true);

        return list;
    }

    public void addPost(Integer userId , String message , Timestamp time ){

        jdbcTemplate.update("INSERT INTO T_POST (USER_ID,MESSAGE, TIME) VALUES(?,?,?)",userId ,message ,time  );
    }

    public List<PostEntity> findAllPost(){
        List<PostEntity> list = jdbcTemplate.query("SELECT * FROM T_POST AS p  \n" +
                "LEFT JOIN T_USER AS U  \n" +
                "ON  P.USER_ID = U.ID ", new PostRowMapper());

        getListWithReplies(list,true);
        return list;
    }


    public void deletePostById(Integer id){

        jdbcTemplate.update("DELETE FROM T_MENTION WHERE POST_ID = ?",id);
        jdbcTemplate.update("DELETE FROM T_REPLY WHERE POST_ID = ?",id);
        jdbcTemplate.update("DELETE FROM T_LIKE WHERE POST_ID = ?",id);
        jdbcTemplate.update("DELETE FROM T_POST WHERE ID = ?",id);
    }


    public List<PostEntity> getFeed(Integer userId){
        List<PostEntity> list = jdbcTemplate.query("SELECT p.id, p.USER_ID,p.MESSAGE,p.TIME,u.ID,u.USER_NAME,u.FIRST_NAME,u.LAST_NAME, u.EMAIL, u.PASSWORD\n" +
                "FROM T_POST AS p  \n" +
                "RIGHT JOIN T_FOLLOW AS f  \n" +
                "ON f.FOLLOWING_USER_ID = P.USER_ID\n" +
                "LEFT JOIN T_USER AS u\n" +
                "ON P.USER_ID = U.ID\n" +
                "WHERE f.USER_ID = ?;",new PostRowMapper(),userId);;
        if(list.isEmpty()){
            throw new NotFoundException("This user doesn't have feed");
        }
        getListWithReplies(list,false);
        return list ;

    }
    public void copyAnExistingPost(Integer currentUserId,Integer postId){
        PostEntity post;
        try {
            post = jdbcTemplate.queryForObject("\n" +
                    "SELECT * FROM T_POST AS p\n" +
                    "LEFT JOIN T_USER AS u\n" +
                    "ON p.USER_ID = u.id\n" +
                    "WHERE p.id = ?",new PostRowMapper(),postId);
        }
        catch(EmptyResultDataAccessException e){
            throw new NotFoundException("Post not found");
        }


        jdbcTemplate.update("INSERT INTO T_POST (USER_ID,MESSAGE, TIME) VALUES(?,?,?)",currentUserId,post.getMessage(),new Timestamp(System.currentTimeMillis()));
    }

    private void getListWithReplies(List<PostEntity> list , boolean allPublic){
        for(PostEntity postEntity :list){
            List<ReplyEntity> replyEntities=jdbcTemplate.query("SELECT r.id,r.USER_ID,r.message,r.time,r.IS_PUBLIC,\n" +
                    "u.USER_NAME,u.FIRST_NAME,u.LAST_NAME,u.email,u.PASSWORD FROM T_REPLY AS r\n" +
                    "LEFT JOIN T_USER AS u\n" +
                    "ON r.USER_ID = u.id\n" +
                    "WHERE r.POST_ID = ?" ,new ReplyRowMapper(),postEntity.getId());
            if(allPublic) {
                postEntity.setReplies(replyEntities);
            }
            else{
                replyEntities.removeIf(replyEntity -> !replyEntity.isPublic());
                postEntity.setReplies(replyEntities);
            }
        }
    }
//"SELECT * FROM T_USER u \n" +
//                "RIGHT JOIN T_POST p ON p.USER_ID =u.ID;
}
