package com.parser.cp.impl;

import com.parser.cp.DomParser;
import com.parser.cp.DomParserFactory;
import com.parser.cp.exception.NotSupportedException;
import com.parser.cp.model.BrowserPayLoad;
import com.parser.cp.model.Task;
import com.parser.cp.util.FileUtility;
import com.parser.cp.util.JacksonUtility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.logging.Logger;

public class HackerRankDomParserImplTest {
    private static final Logger LOGGER = Logger.getLogger(HackerRankDomParserImplTest.class.getName());
    private static final String HACKER_RANK = "sampleInput.json";
    private BrowserPayLoad browserPayLoad;

    @Before
    public void initialize() throws Exception {
        String hackerRankSample = FileUtility.readFromResourcesDirectory(HACKER_RANK, getClass().getClassLoader());
        Optional<BrowserPayLoad> optionalBrowserPayLoad = JacksonUtility.jsonPtrExpr(hackerRankSample, "/hackerRank/pre/single", BrowserPayLoad.class);
        optionalBrowserPayLoad.ifPresent(tempObject -> browserPayLoad = optionalBrowserPayLoad.get());
    }

    @Test
    public void testParser() throws Exception {
        browserPayLoad.setSender();
        DomParser hackerRankDomParser = DomParserFactory.getParser(browserPayLoad.getWebsiteName());
        Task task = hackerRankDomParser.parse(browserPayLoad.getPayload().getMessage());
        Assert.assertEquals("Input did not match", "2", task.getQuestions().get(0).getInput());
        Assert.assertEquals("Output did not match", "2 x 1 = 2\n" +
                "2 x 2 = 4\n" +
                "2 x 3 = 6\n" +
                "2 x 4 = 8\n" +
                "2 x 5 = 10\n" +
                "2 x 6 = 12\n" +
                "2 x 7 = 14\n" +
                "2 x 8 = 16\n" +
                "2 x 9 = 18\n" +
                "2 x 10 = 20", task.getQuestions().get(0).getOutput());
    }

    @Test
    public void testForMultiplePre() throws Exception {
        String hackerRankSample = FileUtility.readFromResourcesDirectory(HACKER_RANK, getClass().getClassLoader());
        Optional<BrowserPayLoad> optionalBrowserPayLoad = JacksonUtility.jsonPtrExpr(hackerRankSample, "/hackerRank/pre/multiple", BrowserPayLoad.class);
        optionalBrowserPayLoad.ifPresent(tempObject -> browserPayLoad = optionalBrowserPayLoad.get());
        browserPayLoad.setSender();
        DomParser hackerRankDomParser = DomParserFactory.getParser(browserPayLoad.getWebsiteName());
        try {
            hackerRankDomParser.parse(browserPayLoad.getPayload().getMessage());
            Assert.fail();
        } catch (NotSupportedException ex) {
            LOGGER.info("We do not support multiple io as of yet");
        }
    }


}
