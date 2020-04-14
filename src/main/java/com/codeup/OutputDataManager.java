package com.codeup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OutputDataManager {

    private static final String outputDir = "output";
    private static final String resourceDir = "resource";

    static void createOutputFileStructure() throws IOException {
        createOutputDirectoryIfNotThere();
        copyResourcesToOutputDirectory();
    }

    private static void createOutputDirectoryIfNotThere() throws IOException {
        Path dirPath = Paths.get(outputDir);
        if (Files.exists(dirPath)) deleteOutputDirectory();
        Files.createDirectories(dirPath);
    }

    private static void copyResourcesToOutputDirectory() throws IOException {
        File sourceFolder = new File(resourceDir);
        File destinationFolder = new File(outputDir, resourceDir);
        DirectoryCopy.copyFolder(sourceFolder, destinationFolder);
    }

    static void deleteOutputDirectory() {
        File directoryToBeDeleted = new File(outputDir);
        deleteDirectory(directoryToBeDeleted);
    }

    /**
     * Method to delete a directory with all files and sub directories.
     * From: https://www.baeldung.com/java-delete-directory
     *
     * @param directoryToBeDeleted
     */
    static void deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        if (!directoryToBeDeleted.delete()) {
            System.err.println("Output directory delete failed in deleteDirectory()");
        }
    }
}
