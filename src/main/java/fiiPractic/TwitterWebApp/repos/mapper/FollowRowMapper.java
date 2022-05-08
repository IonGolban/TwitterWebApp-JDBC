package fiiPractic.TwitterWebApp.repos.mapper;


import fiiPractic.TwitterWebApp.model.entity.FollowEntity;
import fiiPractic.TwitterWebApp.model.entity.PostEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FollowRowMapper implements RowMapper<FollowEntity> {

    @Override
    public FollowEntity mapRow(final ResultSet rs, final int rowNum) throws SQLException {

                return null ;
    }

}
