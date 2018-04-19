
package com.parser.cp.model.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "input",
    "output"
})
public class Test {

    @JsonProperty("input")
    private String input;
    @JsonProperty("output")
    private String output;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Test() {
    }

    /**
     * 
     * @param input
     * @param output
     */
    public Test(String input, String output) {
        super();
        this.input = input;
        this.output = output;
    }

    @JsonProperty("input")
    public String getInput() {
        return input;
    }

    @JsonProperty("input")
    public void setInput(String input) {
        this.input = input;
    }

    @JsonProperty("output")
    public String getOutput() {
        return output;
    }

    @JsonProperty("output")
    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("input", input).append("output", output).toString();
    }

}
