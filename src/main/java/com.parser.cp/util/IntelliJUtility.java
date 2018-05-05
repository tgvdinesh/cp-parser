package com.parser.cp.util;

import com.intellij.ide.RecentProjectsManager;
import com.intellij.openapi.actionSystem.AnAction;

public class IntelliJUtility {
    public static AnAction[] getRecentProject() {
        if (RecentProjectsManager.getInstance() != null)
            return RecentProjectsManager.getInstance().getRecentProjectsActions(false);
        else return null;
    }
}
