package com.parser.cp.ui;

import javax.swing.*;

public class SetupProjectDialog {
    public static void init() {
        String projectName = "java-template";
        Object[] options = {"Cancel",
                "Clone/Download from GitHub",
                "Open Project"
        };
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        int n = JOptionPane.showOptionDialog(jFrame,
                "You don't seem to have " + projectName + " open!. You can do one of the following",
                "Mind sport",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);
        switch (n) {
            case 0:
                System.out.println("Cancel operation");
                break;
            case 1:
                System.out.println("Clone Project");

                break;
            case 2:
                System.out.println("Open Project");
//                https://github.com/JetBrains/intellij-community/blob/master/platform/platform-impl/src/com/intellij/ide/actions/OpenFileAction.java
                // Problem with getting base path to open dialog
                //This is wrong as it doens' open dialog box and runs in background
                /*ProjectUtil.openProject(null, null, false);*/
                break;
            default:
                System.err.println("Should not have happened/ user closed the window??");
                break;
        }
        JOptionPane.getRootFrame().dispose();
    }
}
