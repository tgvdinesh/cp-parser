package com.parser.cp;

import com.parser.cp.impl.CodeChefDomParserImpl;
import com.parser.cp.impl.HackerRankDomParserImpl;

public class DomParserFactory {
    static DomParser getParser(String senderName) throws Exception {
        switch (senderName) {
            case "hackerrank":
                return new HackerRankDomParserImpl();
            case "codechef":
                return new CodeChefDomParserImpl();
            default:
                throw new Exception("Doesn't exist");
        }
    }
}
