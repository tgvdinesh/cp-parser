package com.parser.cp;

import com.parser.cp.impl.CodeChefDomParserImpl;
import com.parser.cp.impl.HackerRankDomParserImpl;

public class DomParserFactory {
    static DomParser getParser(String competitiveProgrammingWebsiteURL) throws Exception {
        switch (competitiveProgrammingWebsiteURL) {
            case "https://www.hackerrank.com/*":
                return new HackerRankDomParserImpl();
            case "http://www.codechef.com/*":
            case "https://www.codechef.com/*":
                return new CodeChefDomParserImpl();
            default:
                throw new Exception("Doesn't exist");
        }
    }
}
