package com.parser.cp.model;

import com.parser.cp.model.payload.Task;
import com.parser.cp.util.FileUtility;
import com.parser.cp.util.JacksonUtility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TaskTest {
    private static final String HACKER_RANK = "sampleInput.json";
    private Task task;

    @Before
    public void initializeValue() throws IOException {
        String hackerRankSample = FileUtility.readFromResourcesDirectory(HACKER_RANK, getClass().getClassLoader());
        Optional<Task> optionalTask = JacksonUtility.jsonPtrExpr(hackerRankSample, "/hackerRank/single", Task.class);
        optionalTask.ifPresent(tempObject -> task = optionalTask.get());
    }

    @Test
    public void deSerializationSuccessAndSenderValidation() {
        Assert.assertNotNull("Sender should be present", task.getWebsiteName());
    }

    @Test
    public void testBrowserPayLoadSetSender() {
        Map<WebsiteName, String> supportedWebsiteNameMap = new HashMap<>();
        supportedWebsiteNameMap.put(WebsiteName.CODE_CHEF, "https://www.codechef.com/");
        supportedWebsiteNameMap.put(WebsiteName.HACKER_RANK, "https://www.hackerrank.com/");

        supportedWebsiteNameMap.forEach((key, websiteURL) -> {
            task.setUrl(websiteURL);
            Assert.assertEquals("Should be equal", key, task.getWebsiteName());
        });
    }
}
