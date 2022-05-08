package fiiPractic.TwitterWebApp.repos.Dao;

import fiiPractic.TwitterWebApp.advice.exception.NotFoundException;
import fiiPractic.TwitterWebApp.model.entity.FollowEntity;
import fiiPractic.TwitterWebApp.model.entity.PostEntity;
import fiiPractic.TwitterWebApp.model.entity.UserEntity;
import fiiPractic.TwitterWebApp.repos.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.List;

@Repository
public class FollowDAO {
    private JdbcTemplate jdbcTemplate ;

    @Autowired
    public void setDataSource(final DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void followUser(Integer userId, Integer followingUserId){
        try{
            UserEntity user = jdbcTemplate.queryForObject("SELECT * FROM T_USER WHERE ID = ?",new UserRowMapper(),userId);
        }catch(EmptyResultDataAccessException e){
            throw new NotFoundException("User with id = "+ userId + " not found");
        }

        try{
            UserEntity user = jdbcTemplate.queryForObject("SELECT * FROM T_USER WHERE ID = ?",new UserRowMapper(),followingUserId);
        }catch(EmptyResultDataAccessException e){
            throw new NotFoundException("User with id = "+ followingUserId +" not found");}


            jdbcTemplate.update("INSERT INTO T_FOLLOW(USER_ID , FOLLOWING_USER_ID,TIME)VALUES (?,?,CURRENT_TIMESTAMP)",userId,followingUserId);
    }
    public void unfollowUser(Integer userId, Integer followingUserId){

        jdbcTemplate.update("DELETE FROM T_FOLLOW WHERE USER_ID = ? AND FOLLOWING_USER_ID = ?",userId,followingUserId);
    }

}
