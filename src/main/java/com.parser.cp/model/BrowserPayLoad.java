package com.parser.cp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "action",
        "payload",
        "url"
})
public class BrowserPayLoad {

    @JsonProperty("action")
    private Integer action;
    @JsonProperty("payload")
    private PayLoad payload;
    @JsonProperty("url")
    private String url;
    private WebsiteName websiteName;

    public WebsiteName getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(WebsiteName websiteName) {
        this.websiteName = websiteName;
    }

    @JsonProperty("action")
    public Integer getAction() {
        return action;
    }

    @JsonProperty("action")
    public void setAction(Integer action) {
        this.action = action;
    }

    @JsonProperty("payload")
    public PayLoad getPayload() {
        return payload;
    }

    @JsonProperty("payload")
    public void setPayload(PayLoad payload) {
        this.payload = payload;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("action", action).append("payload", payload).append("url", url).toString();
    }

    public void setSender() {
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