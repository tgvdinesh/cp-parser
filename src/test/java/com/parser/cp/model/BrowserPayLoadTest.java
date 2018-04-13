package com.parser.cp.model;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.parser.cp.util.Common;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class BrowserPayLoadTest {

    @Test
    public void bindingSuccess() throws IOException {
        String sampleJson = "{\"sender\":\"hackerrank\",\"htmlBody\":\"<html></html>\",\"url\":\"https://www.hackerrank.com/challenges/30-loops/problem\"}";
        Assert.assertTrue("POJO binding error", Common.jsonToJava(sampleJson).isPresent());
    }

    @Test
    public void bindingError() {
        String sampleJson = "{\"incorrectTagName\":\"hackerrank\",\"htmlBody\":\"<html></html>\",\"url\":\"https://www.hackerrank.com/challenges/30-loops/problem\"}";
        try {
            Common.jsonToJava(sampleJson);
        } catch (IOException e) {
            if (e instanceof UnrecognizedPropertyException)
                Assert.assertFalse("Incorrect mapping should throw error", false);
        }
    }
}
