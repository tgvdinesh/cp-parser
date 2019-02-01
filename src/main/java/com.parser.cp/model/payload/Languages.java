
package com.parser.cp.model.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "java"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Languages {

    @JsonProperty("java")
    private Java java;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Languages() {
    }

    /**
     * 
     * @param java
     */
    public Languages(Java java) {
        super();
        this.java = java;
    }

    @JsonProperty("java")
    public Java getJava() {
        return java;
    }

    @JsonProperty("java")
    public void setJava(Java java) {
        this.java = java;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("java", java).toString();
    }

}
