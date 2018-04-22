package com.parser.cp;

import com.intellij.ide.RecentProjectsManager;
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
import com.parser.cp.util.Common;
import com.parser.cp.util.Constant;
import com.parser.cp.util.FileUtility;
import com.parser.cp.util.JacksonUtility;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Source: - https://www.plugin-dev.com/intellij/
 */
public class MyApplicationComponent implements ApplicationComponent {
    private static final Logger LOGGER = Logger.getLogger(MyApplicationComponent.class.getSimpleName());
    private static final int PORT = 4243;
    private ServerSocket serverSocket;
    private Project project;
    private String page;
    private boolean projectLoaded = false;

    private void setProject(Project project) {
        this.project = project;
    }

    @Override
    public void disposeComponent() {
        LOGGER.info("Disposing plugin data structures");
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "Application";
    }

    @Override
    public void initComponent() {
        LOGGER.info("Initializing plugin data structures");
        try {
            serverSocket = new ServerSocket(PORT);
            new Thread(() -> {
                while (true) {
                    if (serverSocket.isClosed())
                        return;
                    try {
                        Socket socket = serverSocket.accept();
                        BufferedReader bufferedReader = new BufferedReader(
                                new InputStreamReader(socket.getInputStream(), "UTF-8"));
                        StringBuilder builder = new StringBuilder();
                        String s;
                        while ((s = bufferedReader.readLine()) != null)
                            builder.append(s).append('\n');
                        page = builder.toString();
                        socket.close();
                        bufferedReader.close();
                        if (page.equals("")) {
                            Common.sendMessage("Page :" + page, NotificationType.INFORMATION);
                            return;
                        }
                        Common.sendMessage("Page :" + page, NotificationType.INFORMATION);
                        LOGGER.info(page.substring(page.indexOf(Constant.JSON), page.length() - 1));
                        Optional<Task> optionalTask = Common.deSerialize(page.substring(page.indexOf(Constant.JSON), page.length() - 1), Task.class);
                        if (optionalTask.isPresent()) {
                            TransactionGuard.getInstance().submitTransactionAndWait(() -> {
                                if (!projectLoaded)
                                    loadProject();
                            });
                            TransactionGuard.getInstance().submitTransactionAndWait(() -> {
                                try {
                                    /*DomParser domParser = DomParserFactory.getParser(optionalTask.get().getWebsiteName());*/
                                    /*Task task = domParser.parse(optionalTask.get());*/
                                    initializeTask(optionalTask.get());
                                } catch (Exception parserDoNotExistException) {
                                    Common.sendMessage("Error occurred during initialization of task. Error : " + parserDoNotExistException.getLocalizedMessage(), NotificationType.ERROR);
                                }
                            });
                        }
                        /*1. Parse DOM in background. This means
                         * 1.1 If any other project is open then prompt user to load our module*/
                    } catch (IOException e) {
                        Common.sendMessage("Input from plugin : \r\n" + page + "\r\n Error : " + e.getLocalizedMessage(), NotificationType.ERROR);
                        LOGGER.log(Level.SEVERE, "Error occurred during socket acceptance ", e);
                        return;
                    }
                }
            }).start();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error occurred ", e);
            Common.sendMessage("Input from plugin : \r\n" + page + "\r\n Error : " + e.getLocalizedMessage(), NotificationType.ERROR);
        }
        /*VirtualFile userHomeDir = VfsUtil.getUserHomeDir();
        userHomeDir.findChild("java-cp");*/
    }

    /**
     * Responsible for opening the project which is created from custom competitive programming template.
     */
    private void loadProject() {
        /*1. Check if project already exists*/
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
                createProjectFromTemplate();
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
     *
     * @return Returns false if user cancels the action
     */
    private boolean actionDialog() {
        Common.sendMessage("Kindly open " + Constant.PROJECT_NAME + " project. If you don't have the project <a href='https://github.com/tgvdinesh/java-template'>clone it from Github Repository</a>", NotificationType.ERROR);
        return false;
    }

    /**
     * 1.1.1 Get user workspace
     *
     * @return If project is opened from recent location then send true else false
     */
    private boolean openFromRecent() {
        AnAction[] anActions = RecentProjectsManager.getInstance().getRecentProjectsActions(false);
        if (anActions == null || anActions.length == 0) return false;
        AnAction action = anActions[0];
        if (((ReopenProjectAction) action).getProjectName().equals(Constant.PROJECT_NAME)) {
            // Got our project from recently opened history. Open it.
            String projectPath = ((ReopenProjectAction) action).getProjectPath();
            ProjectUtil.openOrImport(projectPath, null, false);
            return true;
        }
        return true;
    }

    private void createProjectFromTemplate() {

    }

    private void initializeTask(Task task) throws IOException {
        /*1. Has the DOM parser completed it's task?*/
        /*1.1 If (it is still processing) then show loading*/
        /*1.2 If (it has already processed) then initialize the task*/
        /*2. User has completed testing. Now enable copy solution button*/
        Project[] openProjects = ProjectManager.getInstance().getOpenProjects();
        Optional<Project> neededProject = Arrays.stream(openProjects).filter(currentProject -> currentProject.getName().equals(Constant.PROJECT_NAME)).findFirst();
        if (neededProject.isPresent()) {
            Project project = neededProject.get();
            setProject(project);
            writeTestCases(JacksonUtility.deSerialize(task.getTests(), true));
            /*task.getQuestions().forEach(question -> {
                try {
                    initializeModule(question);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });*/
        } else {
            LOGGER.severe("This should never happen.");
        }
    }

    /*private void initializeModule(Question question) throws IOException {
        if (project == null ||
                project.getBaseDir() == null ||
                project.getBaseDir().findFileByRelativePath(Constant.TEST_CASE_DIRECTORY) == null ||
                project.getBaseDir().findFileByRelativePath(Constant.TEST_CASE_DIRECTORY) == null)
            throw new IOException("File not found ");
        else {
            FileUtility.writeTextFile(project.getBaseDir().findFileByRelativePath(Constant.TEST_CASE_DIRECTORY), Constant.INPUT_TEST_CASE_FILE, question.getInput());
            FileUtility.writeTextFile(project.getBaseDir().findFileByRelativePath(Constant.TEST_CASE_DIRECTORY), Constant.OUTPUT_TEST_CASE_FILE, question.getOutput());
        }
    }*/

    private void writeTestCases(String jsonString) throws IOException {
        VirtualFile testCaseDirectory = project.getBaseDir().findFileByRelativePath(Constant.TEST_CASE_DIRECTORY);
        if (project == null ||
                project.getBaseDir() == null ||
                testCaseDirectory == null) {
            throw new IOException("File not found ");
        } else {
            FileUtility.writeTextFile(testCaseDirectory, Constant.IO, jsonString);
        }
    }

    /**
     * At times this plugin opens the same project. Hence creating a method to validate contents.
     */
    private void validateProject() {

    }
}
