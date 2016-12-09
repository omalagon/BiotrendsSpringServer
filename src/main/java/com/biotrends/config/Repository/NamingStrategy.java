package com.biotrends.config;

import org.hibernate.cfg.ImprovedNamingStrategy;
import static com.google.common.base.Strings.isNullOrEmpty;
import static org.hibernate.internal.util.StringHelper.unqualify;

/**
 * @author Oscar Malagon
 * @since 8/12/2016.
 */
public class NamingStrategy extends ImprovedNamingStrategy {

    private static final long serialVersionUID = 1312100688427723355L;

    @Override
    public String classToTableName(String className) {
        return super.classToTableName(className).toUpperCase();
    }

    @Override
    public String tableName(String tableName) {
        return super.tableName(tableName).toUpperCase();
    }

    @Override
    public String foreignKeyColumnName(String propertyName, String propertyEntityName,
        String propertyTableName, String referencedColumnName) {
        String name = propertyTableName;
        if (!isNullOrEmpty(propertyTableName)) {
            name = unqualify(propertyName);
        }

        return columnName(name) + "_" + referencedColumnName;
    }
}
