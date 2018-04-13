package com.parser.cp;

import com.parser.cp.impl.CodeChefDomParserImpl;
import com.parser.cp.impl.HackerRankDomParserImpl;

public class DomParserFactory {
    static DomParser getParser(String parserType) throws Exception {
        switch (parserType) {
            case "HackerRank":
                return new HackerRankDomParserImpl();
            case "CodeChef":
                return new CodeChefDomParserImpl();
            default:
                throw new Exception("Doesn't exist");
        }
    }
}
