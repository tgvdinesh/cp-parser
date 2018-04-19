package com.parser.cp.impl;

import com.parser.cp.DomParser;
import com.parser.cp.model.payload.Task;
import com.parser.cp.util.Common;

import java.util.LinkedList;
import java.util.List;

public class CodeChefDomParserImpl implements DomParser {
    private static final List<String> INPUT_SELECTORS = new LinkedList<>();
    private static final List<String> OUTPUT_SELECTORS = new LinkedList<>();

    static {
        INPUT_SELECTORS.add("#content div.challenge_sample_input_body");
        OUTPUT_SELECTORS.add("#content div.challenge_sample_output_body");
        INPUT_SELECTORS.add("#problem-statement pre:nth-child(16)");
        OUTPUT_SELECTORS.add("#problem-statement pre:nth-child(18)");
    }

    @Override
    public Task parse(String domAsString)  {
        /*return Common.getTask(domAsString, INPUT_SELECTORS, OUTPUT_SELECTORS);*/
        return null;
    }


}
