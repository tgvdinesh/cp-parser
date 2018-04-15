package com.parser.cp;

import com.parser.cp.impl.CodeChefDomParserImpl;
import com.parser.cp.impl.HackerRankDomParserImpl;
import com.parser.cp.model.WebsiteName;
import com.parser.cp.util.Common;
import org.junit.Assert;
import org.junit.Test;

import java.util.logging.Logger;

public class DomParserFactoryTest {
    private static final Logger LOGGER = Logger.getLogger(DomParserFactoryTest.class.getName());

    @Test
    public void testFactory() {
        try {
            Assert.assertTrue(DomParserFactory.getParser(WebsiteName.CODE_CHEF) instanceof CodeChefDomParserImpl);
            Assert.assertTrue(DomParserFactory.getParser(WebsiteName.HACKER_RANK) instanceof HackerRankDomParserImpl);
        } catch (Exception e) {
            LOGGER.severe(Common.stackToString(e));
        }
    }
}
