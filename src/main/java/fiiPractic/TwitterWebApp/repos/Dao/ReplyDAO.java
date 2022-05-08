package fiiPractic.TwitterWebApp.repos.Dao;

import fiiPractic.TwitterWebApp.model.dto.ReplyCreateDto;
import fiiPractic.TwitterWebApp.model.entity.PostEntity;
import fiiPractic.TwitterWebApp.model.entity.ReplyEntity;
import fiiPractic.TwitterWebApp.repos.mapper.PostRowMapper;
import fiiPractic.TwitterWebApp.repos.mapper.ReplyRowMapper;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
//@XSlf4j
@Repository
public class ReplyDAO {

    private JdbcTemplate jdbcTemplate ;

    @Autowired
    public void setDataSource(final DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void replyPost(ReplyCreateDto replyCreateDto, Integer postId ){
        jdbcTemplate.update("INSERT INTO T_REPLY(POST_ID,USER_ID,MESSAGE , TIME , IS_PUBLIC)\n" +
                "\tVALUES(?,?,?,?,?)",postId,replyCreateDto.getUserId(),replyCreateDto.getMessage(),
                new Timestamp(System.currentTimeMillis()),replyCreateDto.getIsPublic());


    }


}
