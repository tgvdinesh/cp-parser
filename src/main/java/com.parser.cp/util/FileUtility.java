package com.parser.cp.util;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.Charset;
import java.util.logging.Logger;

public class FileUtility {
    private static final Logger LOGGER = Logger.getLogger(FileUtility.class.getSimpleName());

    public static VirtualFile getFile(Project project, String location) {
        VirtualFile baseDir = project.getBaseDir();
        if (baseDir == null) {
            return null;
        }
        return baseDir.findFileByRelativePath(location);
    }

    public static String readTextFile(VirtualFile file) {
        try {
            return VfsUtil.loadText(file);
        } catch (IOException e) {
            return null;
        }
    }

    public static void writeTextFile(@NotNull final VirtualFile location, final String fileName, final String fileContent) {
        ApplicationManager.getApplication().runWriteAction(() -> {
            VirtualFile file;
            try {
                file = location.findOrCreateChildData(null, fileName);
                try (OutputStream stream = file.getOutputStream(null)) {
                    stream.write(fileContent.getBytes(Charset.forName("UTF-8")));
                } catch (IOException ignored) {
                    Common.sendErrorMessage("Writing file failed", ignored);
                }
            } catch (IOException ioException) {
                Common.sendErrorMessage("Failed to find the file.", ioException);
            }
        });
    }

    public static String readFromResourcesDirectory(String filePath, ClassLoader classLoader)
            throws IOException {
        InputStream inputStream = classLoader.getResourceAsStream(filePath);
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}
