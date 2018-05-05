package com.parser.cp;

import com.intellij.ide.ReopenProjectAction;
import com.intellij.ide.impl.ProjectUtil;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.application.TransactionGuard;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.parser.cp.model.payload.Task;
import com.parser.cp.util.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MyApplicationComponent implements ApplicationComponent {
    private static final Logger LOGGER = Logger.getLogger(MyApplicationComponent.class.getSimpleName());
    private static final int PORT = 4243;
    private static Project project;
    private static boolean projectLoaded = false;

    @Override
    public void disposeComponent() {
        LOGGER.info("Disposing plugin data structures");
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "Application";
    }

    /**
     * Responsible for opening the project which is created from custom competitive programming template.
     */
    private static void loadProject() {
        Project[] openProjects = ProjectManager.getInstance().getOpenProjects();
        if (openProjects.length == 0) {
            try {
                if (!openFromRecent()) {
                    actionDialog();
                } else {
                    projectLoaded = true;
                }
            } catch (Exception e) {
                LOGGER.info("Needed project was not found in recent history. Load as new project from template");
                /*createProjectFromTemplate();*/
            }
        } else {
            Optional<Project> neededProject = Arrays.stream(openProjects).filter(currentProject -> currentProject.getName().equals(Constant.PROJECT_NAME)).findFirst();
            if (!neededProject.isPresent()) {
                Common.sendMessage(Constant.PROJECT_NAME + " project is not open", NotificationType.ERROR);
                /*LOGGER.info("Project is already open");
                try {
                    openFromRecent();
                } catch (Exception e) {
                    LOGGER.info("Needed project was not found in recent history. Load as new project from template");
                    createProjectFromTemplate();
                }*/
            } else {
                projectLoaded = true;
                LOGGER.info("We have the project open already. Load data in it.");
            }
        }
    }

    /**
     * If the user didn't have the project open/ didn't have the project in recent dialog, ask the user
     * with the following option.
     * 1. Open directory
     * 2. Clone from URL
     * 3. Cancel task.
     */
    private static void actionDialog() {
        Common.sendMessage("Kindly open " + Constant.PROJECT_NAME + " project. If you don't have the project <a href='https://github.com/tgvdinesh/java-template'>clone it from Github Repository</a>", NotificationType.ERROR);
        /*EventQueue.invokeLater(SetupProjectDialog::init);*/
    }

    /**
     * 1.1.1 Get user workspace
     *
     * @return If project is opened from recent location then send true else false
     */
    private static boolean openFromRecent() {
        AnAction[] anActions = IntelliJUtility.getRecentProject();
        if (anActions == null || anActions.length == 0) return false;
        AnAction action = anActions[0];
        if (((ReopenProjectAction) action).getProjectName().equals(Constant.PROJECT_NAME)) {
            String projectPath = ((ReopenProjectAction) action).getProjectPath();
            ProjectUtil.openOrImport(projectPath, null, false);
            return true;
        }
        return true;
    }

    private static void initializeTask(Task task) throws IOException {
        Project[] openProjects = ProjectManager.getInstance().getOpenProjects();
        Optional<Project> neededProject = Arrays.stream(openProjects).filter(currentProject -> currentProject.getName().equals(Constant.PROJECT_NAME)).findFirst();
        if (neededProject.isPresent()) {
            project = neededProject.get();
            writeTestCases(JacksonUtility.deSerialize(task.getTests(), false));
        } else {
            LOGGER.severe("This should never happen.");
        }
    }

    private static void writeTestCases(String jsonString) throws IOException {
        VirtualFile testCaseDirectory = project.getBaseDir().findFileByRelativePath(Constant.TEST_CASE_DIRECTORY);
        if (project == null ||
                project.getBaseDir() == null ||
                testCaseDirectory == null) {
            throw new IOException("File not found ");
        } else {
            FileUtility.writeTextFile(testCaseDirectory, Constant.IO, jsonString);
        }
    }

    private void createProjectFromTemplate() {

    }

    @Override
    public void initComponent() {
        HttpServer server;
        try {
            server = HttpServer.create(new InetSocketAddress(PORT), 0);
            server.createContext("/", new CustomHttpHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
        } catch (IOException e) {
            Common.sendMessage("Server initialization failed.\r\nError : " + e.getLocalizedMessage(), NotificationType.ERROR);
        }
    }

    static class CustomHttpHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange httpExchange) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()));
                 OutputStream os = httpExchange.getResponseBody()
            ) {
                String page = bufferedReader.lines().collect(Collectors.joining(""));
                if (page.equals("")) {
                    Common.sendMessage("Page :" + page, NotificationType.INFORMATION);
                    return;
                }
                HTMLParser htmlParser = new HTMLParser("pre", page);
                Optional<Task> optionalTask = htmlParser.parse();
                if (optionalTask.isPresent()) {
                    TransactionGuard.getInstance().submitTransactionAndWait(() -> {
                        if (!projectLoaded)
                            loadProject();
                    });
                    TransactionGuard.getInstance().submitTransactionAndWait(() -> {
                        try {
                            initializeTask(optionalTask.get());
                        } catch (Exception parserDoNotExistException) {
                            Common.sendMessage("Error occurred during initialization of task. Error : " + parserDoNotExistException.getLocalizedMessage(), NotificationType.ERROR);
                        }
                    });
                } else {
                    /*Raise issue in Mind sport GitHub page without duplicate*/
                    Common.sendMessage("Error occurred while initializing task. Consider raising a issue!", NotificationType.ERROR);
                    return;
                }
                byte[] response = "".getBytes();
                httpExchange.sendResponseHeaders(200, 0);
                os.write(response);
            } catch (Exception e) {
                /*Raise issue in Mind sport GitHub page without duplicate*/
                Common.sendMessage("Error occurred during initialization of task. Error : " + e.getLocalizedMessage(), NotificationType.ERROR);
            }
        }
    }

    /**
     * At times this plugin opens the same project. Hence creating a method to validate contents.
     */
    private void validateProject() {

    }


}
