package com.hyf.ssm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author baB_hyf
 * @date 2021/05/09
 */
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class BasicConfiguration {
}
