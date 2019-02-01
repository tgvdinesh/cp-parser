
package com.parser.cp.model.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "mainClass",
    "taskClass"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Java {

    @JsonProperty("mainClass")
    private String mainClass;
    @JsonProperty("taskClass")
    private String taskClass;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Java() {
    }

    /**
     * 
     * @param taskClass
     * @param mainClass
     */
    public Java(String mainClass, String taskClass) {
        super();
        this.mainClass = mainClass;
        this.taskClass = taskClass;
    }

    @JsonProperty("mainClass")
    public String getMainClass() {
        return mainClass;
    }

    @JsonProperty("mainClass")
    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    @JsonProperty("taskClass")
    public String getTaskClass() {
        return taskClass;
    }

    @JsonProperty("taskClass")
    public void setTaskClass(String taskClass) {
        this.taskClass = taskClass;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("mainClass", mainClass).append("taskClass", taskClass).toString();
    }

}
