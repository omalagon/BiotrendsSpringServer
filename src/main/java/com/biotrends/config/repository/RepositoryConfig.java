package com.biotrends.config.Repository;

import com.biotrends.config.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Oscar Malagon
 * @since 8/12/2016.
 */
@Slf4j
@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
@EnableJpaRepositories({"com.biotrends.entities.*","com.biotrends.repositories.*"})
@ComponentScan({"com.biotrends.entities.*","com.biotrends.repositories.*"})
public class RepositoryConfig {

    private static final String[] ENTITY_PACKAGES = {"com.biotrends.entities"};

    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String HIBERNATE_HBM2DDL_AUTO_UPDATE = "update";
    private static final String HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";
    private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String HIBERNATE_DEFAULT_SCHEMA = "hibernate.default_schema";
    private static final String HIBERNATE_HBM2DDL_SCRIPTS = "hibernate.hbm2ddl.import_files";
    private static final String INIT_SCRIPT = "db-scripts/seed.sql";

    @Primary
    @Bean(destroyMethod = "close")
    public DataSource dataSource(){
        String driver = Constants.DRIVER_CLASS_NAME;
        String url = Constants.DATABASE_URL;
        String username = Constants.DATABASE_USER;
        String password = Constants.DATABASE_PASSWORD;
        String schema = null;//Constants.DATABASE_SCHEMA;
        return DataSourceUtil.builder()
            .driver(driver)
            .url(url)
            .username(username)
            .password(password)
            .schema(schema)
            .build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        String dialect = Constants.HIBERNATE_DIALECT_VALUE;
        String strategy = Constants.NAMING_STRATEGY;
        String showSQL = Constants.HIBERNATE_SHOW_SQL_VALUE;
        String formatSQL = Constants.HIBERNATE_FORMAT_SQL_VALUE;
        String schema = Constants.DATABASE_SCHEMA;

        Properties properties = new Properties();
        properties.put(HIBERNATE_DIALECT, dialect);
        properties.put(HIBERNATE_NAMING_STRATEGY, strategy);
        properties.put(HIBERNATE_SHOW_SQL, showSQL);
        properties.put(HIBERNATE_FORMAT_SQL, formatSQL);
        properties.put(HIBERNATE_DEFAULT_SCHEMA, schema);

        properties.put(HIBERNATE_HBM2DDL_AUTO, HIBERNATE_HBM2DDL_AUTO_UPDATE);
        properties.put(HIBERNATE_HBM2DDL_SCRIPTS, INIT_SCRIPT);

        log.info("Iniciando scripts");
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan(ENTITY_PACKAGES);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setJpaProperties(properties);

        return entityManagerFactoryBean;
    }

    @Primary
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
