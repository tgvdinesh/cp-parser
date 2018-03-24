# Load Project
1. Load Project
    1.1 If project already exists,then open it & initializeTask().
            1.1.1 Using cache - How do we know if project exists ? Should we use some cache and look for similar names for "java-cp"
            1.1.2 Should we just look for the directory and project further?
    1.2 If project does not exist, then create project from template & initialize task[1](https://github.com/joewalnes/idea-community/blob/1fa2c45953ed08667da52b1b83d44c56eb83b043/platform/lang-impl/src/com/intellij/ide/fileTemplates/actions/CreateFromTemplateGroup.java)
                     [2](https://github.com/joewalnes/idea-community/blob/1fa2c45953ed08667da52b1b83d44c56eb83b043/platform/lang-impl/src/com/intellij/ide/fileTemplates/FileTemplateManager.java)
    1.3 Should we ask the user where to store the project or is it already covered by intellij?[1](https://github.com/joewalnes/idea-community/blob/1fa2c45953ed08667da52b1b83d44c56eb83b043/plugins/svn4idea/src/org/jetbrains/idea/svn/dialogs/browser/MkdirOptionsDialog.form)
References:
    https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/openapi/fileChooser/FileSystemTree.java
    https://github.com/JetBrains/intellij-community/blob/master/platform/platform-impl/src/com/intellij/ide/RecentDirectoryProjectsManager.java
    https://github.com/JetBrains/intellij-community/blob/master/platform/platform-impl/src/com/intellij/openapi/fileChooser/actions/GotoProjectDirectory.java
    https://github.com/JetBrains/intellij-community/blob/master/platform/platform-impl/src/com/intellij/openapi/fileChooser/actions/GotoHomeAction.java

# Multiple modules
https://github.com/JetBrains/intellij-community/blob/master/platform/lang-impl/src/com/intellij/ide/util/DirectoryUtil.java
# Go to module
https://github.com/JetBrains/intellij-community/blob/master/platform/lang-impl/src/com/intellij/openapi/fileChooser/actions/GotoModuleDirectory.java
 