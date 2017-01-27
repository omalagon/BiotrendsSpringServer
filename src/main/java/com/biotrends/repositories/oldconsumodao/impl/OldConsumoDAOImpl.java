package com.biotrends.repositories.oldconsumodao.impl;

import com.biotrends.entities.oldconsumo.OldConsumoDTO;
import com.biotrends.repositories.oldconsumodao.OldConsumoDAO;
import com.biotrends.repositories.oldconsumodao.mapper.OldConsumoRowMapper;
import com.biotrends.repositories.oldconsumodao.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static com.biotrends.config.repository.RepositoryConfig.DATASOURCE_OLD;

/**
 * @author Oscar Malagon
 * @since 27/01/2017.
 */
@Repository
public class OldConsumoDAOImpl extends JdbcDaoSupport implements OldConsumoDAO {

    private final OldConsumoRowMapper mapper;
    private final ResourceService resourceService;

    private static final String QUERY_CONSUMOS = "query_consumos.sql";
    private static final String QUERY_VIEW_1 = "query_view1.sql";
    private static final String QUERY_VIEW_2 = "query_view2.sql";
    private static final String QUERY_DELETE_VIEW_1 = "query_delete_view1.sql";
    private static final String QUERY_DELETE_VIEW_2 = "query_delete_view2.sql";

    @Autowired
    public OldConsumoDAOImpl(@Qualifier(DATASOURCE_OLD) DataSource dataSource,
        OldConsumoRowMapper mapper, ResourceService resourceService){

        setDataSource(dataSource);
        this.mapper = mapper;
        this.resourceService = resourceService;
    }

    @Override
    public List<OldConsumoDTO> getConsumos() {

        createViews();
        String query = resourceService.getResourceAsString(QUERY_CONSUMOS);
        List<OldConsumoDTO> lstConsumos = getJdbcTemplate().query(query, mapper);
        deleteViews();

        return lstConsumos;

    }

    private void createViews(){
        String query1 = resourceService.getResourceAsString(QUERY_VIEW_1);
        String query2 = resourceService.getResourceAsString(QUERY_VIEW_2);
        getJdbcTemplate().update(query1);
        getJdbcTemplate().update(query2);
    }

    private void deleteViews(){
        String query1 = resourceService.getResourceAsString(QUERY_DELETE_VIEW_1);
        String query2 = resourceService.getResourceAsString(QUERY_DELETE_VIEW_2);
        getJdbcTemplate().update(query1);
        getJdbcTemplate().update(query2);
    }
}
