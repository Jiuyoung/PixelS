package edu.xidian.pixels.Mapper;

import edu.xidian.pixels.Entity.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentMapper {

    @Select("select * from comment where id=#{id}")
    Comment select(int id);

    @Select("select * from comment where article_id=#{articleId}")
    List<Comment> selectByArticle(int articleId);

    @Select("select * from comment where user_id=#{userId}")
    List<Comment> selectByUser(int userId);

    @Insert("insert into comment(article_id,user_id,message,time) " +
            "values(#{articleId},#{userId},#{message},#{time})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(Comment comment);

    @Delete("delete from comment where id=#{id}")
    int delete(int id);
}
