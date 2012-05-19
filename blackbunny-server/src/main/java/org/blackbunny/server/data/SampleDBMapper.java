package org.blackbunny.server.data;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.blackbunny.data.DBMapper;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: leoincedo
 * Date: 5/15/12
 */

@DBMapper
public interface SampleDBMapper {

    @Select("SELECT * FROM USER WHERE ID = #{id}")
    @Results( value = {
            @Result(property="uid"),
            @Result(property="id"),
            @Result(property="nickname")
    })
    public User selectById( String id );


    @Select("SELECT * FROM USER")
    @Results(value = {
            @Result(property="uid"),
            @Result(property="id"),
            @Result(property="nickname")
    })
    List<User> selectAll();
}
