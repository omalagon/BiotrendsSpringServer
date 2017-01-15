package com.biotrends.config.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Builder;

import javax.sql.DataSource;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * @author Oscar Malagon
 * @since 8/12/2016.
 */
public class DataSourceUtil {
    private static final String DEFAULT_SCHEMA_SQL = "ALTER SESSION SET CURRENT_SCHEMA=";

    @Builder
    @SuppressWarnings("squid:S00107")
    public static DataSource target(String driver, String url,
        String username, String password,
        String schema) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);

        HikariDataSource dataSource = new HikariDataSource(config);

        if (!isNullOrEmpty(schema)) {
            dataSource.setConnectionInitSql(DEFAULT_SCHEMA_SQL + schema);
        }

        return dataSource;
    }
}
