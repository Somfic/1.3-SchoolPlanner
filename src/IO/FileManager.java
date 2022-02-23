package IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileManager {
    public static String read(String path) {
        File file = new File(path);

        if(!file.exists()) {
            System.out.println("File not found");
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static void write(String path, String contents) {
        try (PrintWriter writer = new PrintWriter(path)) {
            writer.print(contents);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }
}
