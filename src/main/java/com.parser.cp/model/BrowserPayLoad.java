package com.parser.cp.model;

public class BrowserPayLoad {
    private String url;
    private String htmlBody;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtmlBody() {
        return htmlBody;
    }

    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }

    @Override
    public String toString() {
        return "BrowserPayLoad{" +
                "url='" + url + '\'' +
                ", htmlBody='" + htmlBody + '\'' +
                '}';
    }
}
