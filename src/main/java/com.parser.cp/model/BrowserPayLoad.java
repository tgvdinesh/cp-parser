package com.parser.cp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "htmlBody",
        "url"
})
public class BrowserPayLoad {

    @JsonProperty("htmlBody")
    private String htmlBody;
    @JsonProperty("url")
    private String url;
    @JsonProperty("sender")
    private String sender;

    @JsonProperty("htmlBody")
    public String getHtmlBody() {
        return htmlBody;
    }

    @JsonProperty("htmlBody")
    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("sender")
    public String getSender() {
        return sender;
    }

    @JsonProperty("sender")
    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("htmlBody", htmlBody).append("url", url).toString();
    }

}