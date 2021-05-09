package com.hyf.ssm.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author baB_hyf
 * @date 2021/05/09
 */
@Configuration
@MapperScan("com.hyf.ssm.mapper")
public class MyBatisConfiguration {
}
