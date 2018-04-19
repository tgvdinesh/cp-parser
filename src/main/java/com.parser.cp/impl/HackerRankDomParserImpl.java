package com.parser.cp.impl;

import com.parser.cp.DomParser;
import com.parser.cp.exception.*;
import com.parser.cp.model.payload.Task;
import com.parser.cp.util.*;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class HackerRankDomParserImpl implements DomParser {
    private static final Logger LOGGER = Logger.getLogger(HackerRankDomParserImpl.class.getName());
    private static final String CODE_WRAPPER = "pre";
    private static final List<String> INPUT_SELECTORS = new LinkedList<>();
    private static final List<String> OUTPUT_SELECTORS = new LinkedList<>();

    static {
        INPUT_SELECTORS.add("#content div.challenge_sample_input_body");
        OUTPUT_SELECTORS.add("#content div.challenge_sample_output_body");
    }

    @Override
    public Task parse(String domAsString) throws NotSupportedException {
        int tagCount = JsoupUtility.countTag(domAsString, CODE_WRAPPER);
        /*if (tagCount > 2) {
            LOGGER.warning("We don't support multiple input test cases as of yet");
            *//*This means we have output for every input given.*//*
            if (tagCount % 2 == 0) {
                Elements elements = JsoupUtility.getElements(domAsString, CODE_WRAPPER);
                Task task = new Task();
                *//*Under assumption that odd element is input and alternate element is output*//*
         *//*for (int currentSample = 0; currentSample <= (tagCount / 2); currentSample = currentSample + 2) {
                    Question question = new Question();
                    question.setInput(elements.get(currentSample).text());
                    question.setInput(elements.get(currentSample + 1).text());
                    task.addQuestions(question);
                }*//*
         *//*LOGGER.info("Total questions = " + task.getQuestions().size());*//*
            }
        } else if (tagCount == 2) {
            Elements elements = JsoupUtility.getElements(domAsString, CODE_WRAPPER);
            Task task = new Task();
            Question question = new Question();
            question.setInput(elements.get(0).text());
            question.setOutput(elements.get(1).text());
            task.addQuestions(question);
            return task;
        } else {
            *//*It is possible to get test case from DOM element path rather than depending on wrapper
         * Eg: #content div.challenge_sample_input_body for input
         * Eg: #content div.challenge_sample_output_body for output
         * Read from the DOM element
         * *//*
            try {
                return Common.getTask(domAsString, INPUT_SELECTORS, OUTPUT_SELECTORS);
            } catch (Exception e) {
                throw new ImpartialException("DOM Parsing failed (or) we do not have exact CSS Query");
            }
        }*/
        throw new NotSupportedException("DOM Parsing failed");
    }
}
