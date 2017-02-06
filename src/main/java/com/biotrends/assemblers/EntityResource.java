package com.biotrends.assemblers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;

/**
 * @author Oscar Malagon
 * @since 4/02/2017.
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EntityResource extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = -8439801602895311193L;

    @JsonProperty("id")
    private String identifier;

}
