
package com.parser.cp.model.payload;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.parser.cp.model.WebsiteName;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "group",
        "input",
        "languages",
        "memoryLimit",
        "name",
        "output",
        "testType",
        "tests",
        "timeLimit",
        "url"
})
public class Task {

    @JsonProperty("group")
    private String group;
    @JsonProperty("input")
    private Input input;
    @JsonProperty("languages")
    private Languages languages;
    @JsonProperty("memoryLimit")
    private long memoryLimit;
    @JsonProperty("name")
    private String name;
    @JsonProperty("output")
    private Output output;
    @JsonProperty("testType")
    private String testType;
    @JsonProperty("tests")
    private List<Test> tests = new ArrayList<Test>();
    @JsonProperty("timeLimit")
    private long timeLimit;
    @JsonProperty("url")
    private String url;
    @JsonIgnore
    private WebsiteName websiteName;

    /**
     * No args constructor for use in serialization
     */
    public Task() {
    }

    /**
     * @param timeLimit
     * @param languages
     * @param testType
     * @param input
     * @param tests
     * @param name
     * @param output
     * @param group
     * @param url
     * @param memoryLimit
     */
    public Task(String group, Input input, Languages languages, long memoryLimit, String name, Output output, String testType, List<Test> tests, long timeLimit, String url) {
        super();
        this.group = group;
        this.input = input;
        this.languages = languages;
        this.memoryLimit = memoryLimit;
        this.name = name;
        this.output = output;
        this.testType = testType;
        this.tests = tests;
        this.timeLimit = timeLimit;
        this.url = url;
    }

    @JsonProperty("group")
    public String getGroup() {
        return group;
    }

    @JsonProperty("group")
    public void setGroup(String group) {
        this.group = group;
    }

    @JsonProperty("input")
    public Input getInput() {
        return input;
    }

    @JsonProperty("input")
    public void setInput(Input input) {
        this.input = input;
    }

    @JsonProperty("languages")
    public Languages getLanguages() {
        return languages;
    }

    @JsonProperty("languages")
    public void setLanguages(Languages languages) {
        this.languages = languages;
    }

    @JsonProperty("memoryLimit")
    public long getMemoryLimit() {
        return memoryLimit;
    }

    @JsonProperty("memoryLimit")
    public void setMemoryLimit(long memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("output")
    public Output getOutput() {
        return output;
    }

    @JsonProperty("output")
    public void setOutput(Output output) {
        this.output = output;
    }

    @JsonProperty("testType")
    public String getTestType() {
        return testType;
    }

    @JsonProperty("testType")
    public void setTestType(String testType) {
        this.testType = testType;
    }

    @JsonProperty("tests")
    public List<Test> getTests() {
        return tests;
    }

    @JsonProperty("tests")
    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    @JsonProperty("timeLimit")
    public long getTimeLimit() {
        return timeLimit;
    }

    @JsonProperty("timeLimit")
    public void setTimeLimit(long timeLimit) {
        this.timeLimit = timeLimit;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
        setWebsiteName();
    }

    @JsonIgnore
    public WebsiteName getWebsiteName() {
        return websiteName;
    }

    @JsonIgnore
    private void setWebsiteName(WebsiteName websiteName) {
        this.websiteName = websiteName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("group", group).append("input", input).append("languages", languages).append("memoryLimit", memoryLimit).append("name", name).append("output", output).append("testType", testType).append("tests", tests).append("timeLimit", timeLimit).append("url", url).toString();
    }

    @JsonIgnore
    private void setWebsiteName() {
        if (url != null) {
            if (url.matches("^https:\\/\\/(www[.])?hackerrank[.]com.*$"))
                setWebsiteName(WebsiteName.HACKER_RANK);
            else if (url.matches("^https:\\/\\/(www[.])?codechef[.]com.*$"))
                setWebsiteName(WebsiteName.CODE_CHEF);
            else
                setWebsiteName(null);
        }
    }

}
