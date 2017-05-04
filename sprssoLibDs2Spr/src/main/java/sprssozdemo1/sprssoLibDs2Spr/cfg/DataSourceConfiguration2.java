package sprssozdemo1.sprssoLibDs2Spr.cfg;

import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.support.ibatis.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import sprssozdemo1.libCommon.util.Util1;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
TODO 目前还不能灵活切换 DataSource的实现类 ...... 也许还是应该放在上层使用者处......
 */
@Configuration
@EnableConfigurationProperties({DruidProperties2.class})
@MapperScan(basePackages = {"sprssozdemo1.sprssoLibDs2Spr.ibatisMapper"}, sqlSessionFactoryRef = "secondSqlSessionFactory")
public class DataSourceConfiguration2 {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DruidProperties2 properties2;
    

    @Bean(name = "secondDataSource")
    public DataSource secondDataSource()  
    {
    	DruidProperties2 properties = properties2;
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(properties.getDriverClass());
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        if (properties.getInitialSize() > 0) {
            dataSource.setInitialSize(properties.getInitialSize());
        }
        if (properties.getMinIdle() > 0) {
            dataSource.setMinIdle(properties.getMinIdle());
        }
        if (properties.getMaxActive() > 0) {
            dataSource.setMaxActive(properties.getMaxActive());
        }
        dataSource.setTestOnBorrow(properties.isTestOnBorrow());
        try {
            dataSource.init();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
        
        
        
//        Properties props = new Properties();
//        props.put("driverClassName", properties.getDriverClass());
//        props.put("url", properties.getUrl());
//        props.put("username", properties.getUsername());
//        props.put("password", properties.getPassword());
//        
//        try {
//        	return DruidDataSourceFactory.createDataSource(props);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }
    
    

//    @Bean(name = "secondDataSource")
//    @ConfigurationProperties(prefix = "datasource.second")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }
    
    

    @Bean(name = "secondTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("secondDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    
    @Autowired
    private Environment env;


    @Bean(name = "secondSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("secondDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(dataSource);
        
        fb.setTypeAliasesPackage("g1.ibatisMp2");
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mappers/second/**/*.xml"));//
        
        
        // 据说下面2句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
//        String mybatis_typeAliasesPackage = env.getProperty("mybatis.typeAliasesPackage"); //"g1.ibatisMp2"
//        logger.info("mybatis_typeAliasesPackage="+mybatis_typeAliasesPackage);
//        if (!Util1.isStringEmpty(mybatis_typeAliasesPackage)){
//        	fb.setTypeAliasesPackage(mybatis_typeAliasesPackage);// 指定基包
//        }else{
//        	fb.setTypeAliasesPackage("g1.ibatisMp2");
//        }
//        String mybatis_mapperLocations = env.getProperty("mybatis.mapperLocations");
//        logger.info("mybatis_mapperLocations="+mybatis_mapperLocations);
//        if (!Util1.isStringEmpty(mybatis_mapperLocations)){
//        	fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mybatis_mapperLocations));
//        }

        return fb.getObject();
    }
    
    

    
    
    
    
    
    
    
    
    
    
    
}
