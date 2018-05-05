package com.parser.cp;

import com.intellij.openapi.components.ApplicationComponent;
import com.parser.cp.model.payload.Task;
import com.parser.cp.ui.SetupProjectDialog;
import com.parser.cp.util.FileUtility;
import com.parser.cp.util.JacksonUtility;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.Optional;

public class MyApplicationComponentTest {
    private static final String HACKER_RANK = "sampleInput.json";
    private String payLoad;

    @Before
    public void initialize() throws Exception {
        String hackerRankSample = FileUtility.readFromResourcesDirectory(HACKER_RANK, getClass().getClassLoader());
        Optional<Task> optionalTask = JacksonUtility.jsonPtrExpr(hackerRankSample, "/hackerRank/single", Task.class);
        if (optionalTask.isPresent()) {
            payLoad = JacksonUtility.deSerialize(optionalTask.get(), false);
        }
    }

    @Test
    public void testSetupProjectDialog() {
        ApplicationComponent applicationComponent = new ApplicationComponent() {
            @Override
            public void initComponent() {
                EventQueue.invokeLater(SetupProjectDialog::init);
            }
        };
        applicationComponent.initComponent();
    }
}
