package com.biotrends.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

/**
 * @author Oscar Malagon
 * @since 5/12/2016.
 */
@MappedSuperclass
@Data
@SuppressWarnings("squid:S1068")
public class BiotrendsBaseEntity implements Persistable<String>{

    private static final long serialVersionUID = -4472919931255457229L;

    public static final String USER_DEFAULT = "system";

    @Id
    @Column(length = 32, unique = true, nullable = false)
    private String id;

    @CreatedBy
    @Column(name = "CREATED_BY", nullable = false, length = 50, updatable = false)
    @JsonIgnore
    private String createdBy;

    @CreatedDate
    @Column(updatable = false,name = "CREATED_DATE", nullable = false)
    @JsonIgnore
    @DateTimeFormat(iso = DATE_TIME)
    private Date createdDate = new Date();

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY", length = 50)
    @JsonIgnore
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    @JsonIgnore
    @DateTimeFormat(iso = DATE_TIME)
    private Date lastModifiedDate = new Date();

    @PrePersist
    public void prePersist() {
        if(id==null){
            this.id = BiotrendsUUIDFactory.getInstance().createUUID();
        }
        this.createdBy = USER_DEFAULT;
        this.createdDate = new Date();
        this.lastModifiedDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastModifiedDate = new Date();
        this.lastModifiedBy = USER_DEFAULT;
    }

    @Override
    public boolean isNew() {
        return getId() == null;
    }
}
