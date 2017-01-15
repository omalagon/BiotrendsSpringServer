package com.biotrends.repositories;

import com.biotrends.entities.BiotrendsBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * @author PSL.SA - OMalagonM
 * @since 02/08/2016
 */
@NoRepositoryBean
public interface EntityRepository<T extends BiotrendsBaseEntity>
    extends JpaRepository<T, String>, QueryByExampleExecutor<T> {
}
