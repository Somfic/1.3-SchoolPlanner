package IO;

import Logging.Logger;
import sun.rmi.runtime.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileManager {
    public static String read(String path) {
        File file = new File(path);

        if(!file.exists()) {
            Logger.warn("Could not find file: " + path);
            return "";
        }

        try {
            Scanner scanner = new Scanner(new FileReader(file));
            StringBuilder source = new StringBuilder();

            while(scanner.hasNextLine()) {
                source.append(scanner.nextLine());
                source.append("\n");
            }

            return source.toString();
        } catch (Exception e) {
            Logger.warn(e, "Could not read file: " + path);
            return "";
        }
    }

    public static void write(String path, String contents) {
        try (PrintWriter writer = new PrintWriter(path)) {
            writer.print(contents);
        } catch (Exception e) {
            Logger.warn(e, "Could not write to file: " + path);
        }
    }
}
