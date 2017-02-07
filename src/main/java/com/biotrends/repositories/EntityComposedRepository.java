package com.biotrends.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * @author PSL.SA - OMalagonM
 * @since 07/02/2017
 */
@NoRepositoryBean
public interface EntityComposedRepository<T extends Serializable>
    extends JpaRepository<T, String>, QueryByExampleExecutor<T> {
}
