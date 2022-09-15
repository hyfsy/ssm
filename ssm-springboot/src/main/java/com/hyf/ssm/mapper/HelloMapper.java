package com.hyf.ssm.mapper;

import com.hyf.ssm.pojo.Hello;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author baB_hyf
 * @date 2020/05/17
 */
@Mapper
public interface HelloMapper {
    Hello hello(Integer id);

    @Select("select * from hello where id = #{id}")
    Hello hello2(Integer id);
}
