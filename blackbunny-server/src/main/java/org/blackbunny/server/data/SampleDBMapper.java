package org.blackbunny.server.data;

import org.apache.ibatis.annotations.*;
import org.blackbunny.data.DBMapper;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: leoincedo
 * Date: 5/15/12
 */

@DBMapper
public interface SampleDBMapper {

    @Select("SELECT * FROM USER WHERE ID = #{id};")
    @Results( value = {
            @Result(property="uid"),
            @Result(property="id"),
            @Result(property="nickname")
    })
    public User selectById( String id );


    @Select("SELECT * FROM USER;")
    @Results(value = {
            @Result(property="uid"),
            @Result(property="id"),
            @Result(property="nickname")
    })
    List<User> selectAll();

    @Update("UPDATE USER SET NICKNAME = #{nickname} WHERE ID = #{id};")
    void updateNick( @Param("id") String id, @Param("nickname") String nickname );


    @Delete("DELETE FROM USER WHERE ID = #{id}")
    void deleteUser( String id );

    @Insert("INSERT INTO USER ( ID, NICKNAME ) VALUES ( #{id}, #{nickname} );")
    @Options(useGeneratedKeys = true, keyProperty = "uid")
    void insertUser( User user );
}
