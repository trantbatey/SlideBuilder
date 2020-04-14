package com.codeup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * A utility used ofr copying a directory using Java code.
 * FROM:
 * https://howtodoinjava.com/java/io/how-to-create-a-new-file-in-java/
 */
public class DirectoryCopy {
    public static void main(String[] args) throws IOException
    {
        //Source directory which you want to copy to new location
        File sourceFolder = new File("temp");

        //Target directory where files should be copied
        File destinationFolder = new File("tempNew");

        //Call Copy function
        copyFolder(sourceFolder, destinationFolder);
    }
    /**
     * This function recursively copy all the sub folder and files from sourceFolder to destinationFolder
     * */
    public static void copyFolder(File sourceFolder, File destinationFolder) throws IOException
    {
        //Check if sourceFolder is a directory or file
        //If sourceFolder is file; then copy the file directly to new location
        if (sourceFolder.isDirectory())
        {
            //Verify if destinationFolder is already present; If not then create it
            if (!destinationFolder.exists())
            {
                destinationFolder.mkdir();
            }

            //Get all files from source directory
            String files[] = sourceFolder.list();

            //Iterate over all files and copy them to destinationFolder one by one
            for (String file : files)
            {
                File srcFile = new File(sourceFolder, file);
                File destFile = new File(destinationFolder, file);

                //Recursive function call
                copyFolder(srcFile, destFile);
            }
        }
        else
        {
            //Copy the file content from one place to another
            Files.copy(sourceFolder.toPath(), destinationFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
