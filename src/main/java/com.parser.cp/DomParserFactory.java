package com.parser.cp;

import com.parser.cp.impl.CodeChefDomParserImpl;
import com.parser.cp.impl.HackerRankDomParserImpl;
import com.parser.cp.model.WebsiteName;

public class DomParserFactory {
    public static DomParser getParser(WebsiteName websiteName) throws Exception {
        switch (websiteName) {
            case HACKER_RANK:
                return new HackerRankDomParserImpl();
            case CODE_CHEF:
                return new CodeChefDomParserImpl();
            default:
                throw new Exception("Parser not supported yet");
        }
    }
}
