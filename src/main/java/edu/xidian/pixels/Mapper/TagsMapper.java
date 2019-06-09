package edu.xidian.pixels.Mapper;

import edu.xidian.pixels.Entity.Tags;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TagsMapper {

    @Select("select * from tags where id=#{id}")
    Tags findById(int id);
}
