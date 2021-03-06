package edu.xidian.pixels.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import edu.xidian.pixels.Entity.User;
import org.springframework.stereotype.Repository;

/**
 * UserMapper
 */
@Repository
@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    User findById(int id);

    @Select("select * from user where account = #{account}")
    User findByAccount(String account);

    @Insert("insert into user(name,account,password,regist_time,phone)" + 
            "values(#{name},#{account},#{password},#{registTime},#{phone})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    @Update("update user set password=#{password}, message=#{message}, phone=#{phone} where id=#{id}")
    int update(User user);

    @Update("update user set stars_num=#{starsNum} where id=#{id}")
    int editStarsNum(User user);

    @Update("update user set article_num=#{articleNum} where id=#{id}")
    int editArticleNum(User user);
}