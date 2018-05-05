package com.parser.cp.util;

import com.parser.cp.model.payload.Task;
import com.parser.cp.model.payload.Test;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HTMLParser {
    private String tag;
    private String html;

    public HTMLParser(String tag, String html) {
        this.tag = tag;
        this.html = html;
    }

    public Optional<Task> parse() {
        int testCases = JsoupUtility.countTag(html, tag);
        List<Test> tests = new ArrayList<>();
        Elements elements = JsoupUtility.getElements(html, tag);
        if (testCases % 2 == 0) {
            for (int i = 0; i < testCases; i = i + 2) {
                Test test = new Test();
                test.setInput(elements.get(i).toString());
                test.setOutput(elements.get(i + 1).toString());
                tests.add(test);
            }
            Task task = new Task();
            task.setTests(tests);
            return Optional.of(task);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Task> newParser() throws Exception {
        return Common.deSerialize(html.substring(html.indexOf(Constant.JSON), html.length()), Task.class);
    }

}
