package com.parser.cp.model.payload;

import com.parser.cp.util.FileUtility;
import com.parser.cp.util.JacksonUtility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class TestModelTest {
    private static final String HACKER_RANK = "sampleInput.json";
    private String payLoad;
    private com.parser.cp.model.payload.Test[] tests;

    @Before
    public void initialize() throws Exception {
        String hackerRankSample = FileUtility.readFromResourcesDirectory(HACKER_RANK, getClass().getClassLoader());
        Optional<com.parser.cp.model.payload.Test[]> optionalTask = JacksonUtility.jsonPtrExpr(hackerRankSample, "/hackerRank/single/tests", com.parser.cp.model.payload.Test[].class);
        if (optionalTask.isPresent()) {
            tests = optionalTask.get();
            payLoad = JacksonUtility.deSerialize(optionalTask.get(), false);
        }
    }

    @Test
    public void testSerialize() throws Exception {
        Assert.assertEquals(payLoad, JacksonUtility.deSerialize(tests, false));
    }
}
