package com.parser.cp.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JsoupUtility {
    public static int countTag(String domAsString, String tag) {
        return stringToDocument(domAsString).select(tag).size();
    }

    public static Elements getElements(String domAsString, String tag) {
        return stringToDocument(domAsString).select(tag);
    }

    private static Document stringToDocument(String domAsString) {
        return Jsoup.parse(domAsString);
    }
}
