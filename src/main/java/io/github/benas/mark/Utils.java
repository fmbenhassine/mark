package io.github.benas.mark; 

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {

    public static void initDatabaseFile() throws IOException {
        Path markPath = Paths.get(System.getProperty("user.home"), ".mark");
        Path markData = Paths.get(markPath.toString(), "bookmarks.db");
        if (markPath.toFile().exists()) {
            if (!markData.toFile().exists()) {
                Files.createFile(markData);
            }
        } else {
            Files.createDirectory(markPath);
            Files.createFile(markData);
        }
    }

}
