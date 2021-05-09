package com.hyf.ssm.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.context.annotation.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author baB_hyf
 * @date 2020/05/17
 */
@Configuration
@ComponentScan(value = "com.hyf.ssm", excludeFilters = @ComponentScan.Filter(Controller.class))
@PropertySource("classpath:jdbc.properties")
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableTransactionManagement
public class AppConfig implements TransactionManagementConfigurer {

    // FIXME 此处无法注入，因为 MapperScannerConfigurer 依赖于该配置类
    //       注意 BeanFactoryPostProcessor 的执行流程

//    @Value("${jdbc.mysql.driver}")
//    private String driver;
//    @Value("${jdbc.mysql.url}")
//    private String url;
//    @Value("${jdbc.mysql.username}")
//    private String userName;
//    @Value("${jdbc.mysql.password}")
//    private String password;

    private static final String JDBC_PATH = "jdbc.properties";
    private static final String DRIVER_REF = "jdbc.mysql.driver";
    private static final String URL_REF = "jdbc.mysql.url";
    private static final String USERNAME_REF = "jdbc.mysql.username";
    private static final String PASSWORD_REF  = "jdbc.mysql.password";
    private static final String MAPPER_SCAN_BASE_PACKAGE = "com.hyf.ssm.mapper";
    private DataSource dataSource;

    @Bean
    public DataSource dataSource() {
        if (dataSource != null) {
            return dataSource;
        }

        try {
            DruidDataSource dataSource = new DruidDataSource();
            Properties prop = new Properties();
            prop.load(getClass().getClassLoader().getResourceAsStream(JDBC_PATH));
            dataSource.setDriverClassName(prop.getProperty(DRIVER_REF));
            dataSource.setUrl(prop.getProperty(URL_REF));
            dataSource.setUsername(prop.getProperty(USERNAME_REF));
            dataSource.setPassword(prop.getProperty(PASSWORD_REF));
            this.dataSource = dataSource;
            return this.dataSource;
        } catch (Exception e) {
            throw new BeanInstantiationException(DataSource.class, "properties load fail...");
        }
    }

    //---------------------------------------------------------------------
    // mybatis
    //---------------------------------------------------------------------

    @Bean
    public org.apache.ibatis.session.Configuration configuration() {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        return configuration;
    }

    /**
     * 处理Mapper配置文件
     */
    @Bean("sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setConfiguration(configuration());
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*Mapper.xml"));
        return sqlSessionFactoryBean;
    }

    /**
     * 扫描Mapper接口
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage(MAPPER_SCAN_BASE_PACKAGE);
        return mapperScannerConfigurer;
    }

    //---------------------------------------------------------------------
    // tx
    //---------------------------------------------------------------------

    @Bean
    @Override
    public TransactionManager annotationDrivenTransactionManager() {
        DataSourceTransactionManager txManager = new DataSourceTransactionManager();
        txManager.setDataSource(dataSource());
        return txManager;
    }
}
