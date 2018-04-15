package com.parser.cp.model;

import com.parser.cp.util.FileUtility;
import com.parser.cp.util.JacksonUtility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BrowserPayLoadTest {
    private static final String HACKER_RANK = "sampleInput.json";
    private BrowserPayLoad browserPayLoad;

    @Before
    public void initializeValue() throws IOException {
        String hackerRankSample = FileUtility.readFromResourcesDirectory(HACKER_RANK, getClass().getClassLoader());
        Optional<BrowserPayLoad> optionalBrowserPayLoad = JacksonUtility.jsonPtrExpr(hackerRankSample, "/hackerRank/pre/single", BrowserPayLoad.class);
        optionalBrowserPayLoad.ifPresent(tempObject -> browserPayLoad = optionalBrowserPayLoad.get());
    }

    @Test
    public void deSerializationSuccessAndSenderValidation() {
        browserPayLoad.setSender();
        Assert.assertNotNull("Sender should be present", browserPayLoad.getWebsiteName());
    }

    @Test
    public void testBrowserPayLoadSetSender() {
        Map<WebsiteName, String> supportedWebsiteNameMap = new HashMap<>();
        supportedWebsiteNameMap.put(WebsiteName.CODE_CHEF, "https://www.codechef.com/");
        supportedWebsiteNameMap.put(WebsiteName.HACKER_RANK, "https://www.hackerrank.com/");

        supportedWebsiteNameMap.forEach((key, websiteURL) -> {
            browserPayLoad.setUrl(websiteURL);
            browserPayLoad.setSender();
            Assert.assertEquals("Should be equal", key, browserPayLoad.getWebsiteName());
        });
    }
}
