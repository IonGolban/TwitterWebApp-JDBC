package fiiPractic.TwitterWebApp.repos.Dao;

import fiiPractic.TwitterWebApp.advice.exception.NotFoundException;
import fiiPractic.TwitterWebApp.advice.exception.NotUniqueException;
import fiiPractic.TwitterWebApp.model.dto.UserRegisterDto;
import fiiPractic.TwitterWebApp.model.entity.UserEntity;
import fiiPractic.TwitterWebApp.repos.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository

public class UserDAO  {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public List<UserEntity> getAllUsers(){
        return jdbcTemplate.query("SELECT * FROM T_USER",new UserRowMapper());
    }
    public UserEntity getUserById(Integer id){
        return jdbcTemplate.queryForObject("SELECT * FROM T_USER WHERE ID = ?", new UserRowMapper(),id);
    }
    public UserEntity getUserByUserName(String userName){
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM T_USER WHERE USER_NAME = ?",new UserRowMapper(),userName);
        }catch(EmptyResultDataAccessException e){
            throw new NotFoundException("User with username "+userName+" not found");
        }
    }

    public List<UserEntity> getUserByFirstName(String firstName){
        List<UserEntity> list =jdbcTemplate.query("SELECT * FROM T_USER WHERE FIRST_NAME = ?", new UserRowMapper(), firstName);

        if(list.isEmpty()){
            throw new NotFoundException("Users with firstname = "+firstName+" not found");}

        return list;
    }

    public List<UserEntity> getUserByLastName(String lastName){
        List<UserEntity> list =jdbcTemplate.query("SELECT * FROM T_USER WHERE LAST_NAME = ?", new UserRowMapper(), lastName);

        if(list.isEmpty()){
            throw new NotFoundException("Users with firstname = "+lastName+" not found");}

        return list;
    }
    public void createUser(String userName, String firstName, String  lastName, String email, String password){
        jdbcTemplate.update("INSERT INTO T_USER (USER_NAME,FIRST_NAME, LAST_NAME, EMAIL, PASSWORD) VALUES (?, ?, ?, ?, ?)",userName, firstName, lastName, email, password);
    }

    public void deleteUser(Integer userId){

        try{
            UserEntity user = jdbcTemplate.queryForObject("SELECT * FROM T_USER WHERE ID = ?",new UserRowMapper(),userId);
        }catch(EmptyResultDataAccessException e){
            throw new NotFoundException("User not found");
        }

        jdbcTemplate.update("DELETE FROM T_POST WHERE USER_ID = ? ",userId);
        jdbcTemplate.update("DELETE FROM T_FOLLOW WHERE USER_ID = ? OR FOLLOWING_USER_ID = ?",userId,userId);
        jdbcTemplate.update("DELETE FROM T_USER WHERE ID = ?",userId);
    }

    public void validateUser(UserRegisterDto userDto) {

        try {
            jdbcTemplate.queryForObject("SELECT * FROM T_USER WHERE USER_NAME = ?",new UserRowMapper(),userDto.getUserName());
            throw new NotUniqueException("User with this username already exist");
        } catch (EmptyResultDataAccessException ignored){}

        try {
            jdbcTemplate.queryForObject("SELECT * FROM T_USER WHERE FIRST_NAME = ? AND LAST_NAME = ? ",new UserRowMapper(),userDto.getFirstName(),userDto.getLastName());
            throw new NotUniqueException("User with this firstname and lastname already exist");
        } catch (EmptyResultDataAccessException ignored) {}

        try {
           jdbcTemplate.queryForObject("SELECT * FROM T_USER WHERE EMAIL = ?",new UserRowMapper(),userDto.getEmail());
            throw new NotUniqueException("User with this email already exist");
        } catch (EmptyResultDataAccessException ignored) {}

        try {
           jdbcTemplate.queryForObject("SELECT * FROM T_USER WHERE PASSWORD = ?",new UserRowMapper(),userDto.getPassword());
            throw new NotUniqueException("User with this password already exist");
        } catch (EmptyResultDataAccessException ignored) {}

    }
}
