
package com.parser.cp.model.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type"
})
public class Input {

    @JsonProperty("type")
    private String type;

    /**
     * No args constructor for use in serialization
     */
    public Input() {
    }

    /**
     * @param type
     */
    public Input(String type) {
        super();
        this.type = type;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("type", type).toString();
    }

}
