package per.zyf.springbootdruidatomikos.config.datasource;

import java.util.Properties;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = "per.zyf.springbootdruidatomikos.dao.schema1", sqlSessionTemplateRef = "schema1SqlSessionTemplate")
public class Schema1DataSourceConfig {

    /** DataSource **/
    @Bean(name = "schema1DataSource")
    @Primary
    @Autowired
    public AtomikosDataSourceBean schema1DataSource(Environment env) {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        Properties prop = PropertiesBuilder.build(env, "spring.datasource.druid.schema1DB.");
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        ds.setUniqueResourceName("schema1DB");
        ds.setPoolSize(5);
        ds.setXaProperties(prop);
        return ds;
    }

    /** Mybatis SqlSession **/
    @Bean(name = "schema1SqlSessionFactory")
    @Primary
    public SqlSessionFactory schema1SqlSessionFactory(@Qualifier("schema1DataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/schema1/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "schema1SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate schema1SqlSessionTemplate(
            @Qualifier("schema1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
