package org.kx.nick;

import org.apache.ibatis.annotations.*;

/**
 * create by sunkx on 2018/3/17
 * `USERNAME` varchar(45) DEFAULT NULL,
 `PASSWORD` varchar(45) DEFAULT NULL,
 */
@Mapper
public interface UserDAO {
    @Insert("INSERT INTO USER(USERNAME, PASSWORD) values( #{userName},#{password})")
    public int insert(@Param("userName") String userName,@Param("password") String password);

    @Select("SELECT * FROM USER WHERE id = #{id}")
    public User selectByUserId(@Param("id") Long id) ;


    @Select("SELECT * FROM USER WHERE id = #{id} for update")
    public User selectByUserIdForUpdate (@Param("id") Long id) ;

    @Select("SELECT * FROM USER WHERE id = #{id} lock in share mode")
    public User selectByUserIdShare (@Param("id") Long id) ;



    @Update("UPDATE USER SET USERNAME =#{userName}, PASSWORD = #{password} WHERE id = #{id}")
    public int udpateById(@Param("userName") String userName,@Param("password") String password, @Param("id") Long id) ;

    @Delete("DELETE  FROM USER WHERE id = #{id}")
    public int deleteById(@Param("id") Long id) ;




}
