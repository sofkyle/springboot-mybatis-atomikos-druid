package per.zyf.springbootdruidatomikos.config.datasource;

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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@MapperScan(basePackages = "per.zyf.springbootdruidatomikos.dao.schema2", sqlSessionTemplateRef = "schema2SqlSessionTemplate")
public class Schema2DataSourceConfig {
    @Bean(name = "schema2DataSource")
    @Autowired
    public AtomikosDataSourceBean schema2DataSource(Environment env) {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        Properties prop = PropertiesBuilder.build(env, "spring.datasource.druid.schema2DB.");
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        ds.setUniqueResourceName("schema2DB");
        ds.setPoolSize(5);
        ds.setXaProperties(prop);
        return ds;
    }

    /** Mybatis SqlSession **/
    @Bean(name = "schema2SqlSessionFactory")
    public SqlSessionFactory schema2SqlSessionFactory(@Qualifier("schema2DataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/schema2/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "schema2SqlSessionTemplate")
    public SqlSessionTemplate schema1SqlSessionTemplate(
            @Qualifier("schema2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
