package fiiPractic.TwitterWebApp.repos.Dao;

import fiiPractic.TwitterWebApp.model.entity.UserEntity;
import fiiPractic.TwitterWebApp.repos.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class LikeDAO {
    private JdbcTemplate jdbcTemplate ;
    @Autowired
    public void setDataSource(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void likePost(Integer userId, Integer postId){
            jdbcTemplate.update("INSERT INTO T_LIKE(USER_ID,POST_ID,TIME) VALUES (?,?,?)",userId,postId,new Timestamp(System.currentTimeMillis()));
    }

    public void removeLikeByPostId(Integer userId, Integer postId){
            jdbcTemplate.update("DELETE FROM T_LIKE WHERE POST_ID = ? AND USER_ID = ?",postId,userId);
    }

    public List<UserEntity> getUsersByLikedPost(Integer postId){

        List<UserEntity> userEntityList =jdbcTemplate.query("SELECT u.ID, u.USER_NAME, u.FIRST_NAME,u.LAST_NAME, u.EMAIL,u.password \n" +
                "FROM T_LIKE AS l\n" +
                "LEFT JOIN T_POST AS p \n" +
                "ON p.ID = l.POST_ID \n" +
                "LEFT JOIN T_USER AS u \n" +
                "ON u.ID = l.USER_ID \n" +
                "WHERE l.POST_ID = ?;",new UserRowMapper(),postId);

        return userEntityList;
    }


}
