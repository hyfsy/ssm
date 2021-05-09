package com.hyf.ssm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author baB_hyf
 * @date 2020/05/17
 */
@Configuration
@Import(AppConfig.class)
public class RootConfig {
}
