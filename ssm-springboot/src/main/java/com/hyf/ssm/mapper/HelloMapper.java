package com.hyf.ssm.mapper;

import com.hyf.ssm.pojo.Hello;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author baB_hyf
 * @date 2020/05/17
 */
@Mapper
public interface HelloMapper {
    Hello hello(Integer id);
}
