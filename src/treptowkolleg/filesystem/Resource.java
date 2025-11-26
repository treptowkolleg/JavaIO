package treptowkolleg.filesystem;

import java.io.*;
import java.io.FileReader;
import java.util.Scanner;

public class Resource {

    public static InputStream getConfig(String file) throws IOException {
        return getConfig(FileTarget.PROJECT_DIR, File.separatorChar + file);
    }

    public static InputStream getConfig(FileTarget basePath, String file) throws IOException {
        return getURL(basePath, "config", File.separatorChar + file);
    }

    public static InputStream getURL(FileTarget basePath, String subPath,
                                     String file) throws IOException {
        File f = new File(basePath.getBaseDir() + subPath + File.separatorChar + file);
        return new FileInputStream(f);
    }
}
