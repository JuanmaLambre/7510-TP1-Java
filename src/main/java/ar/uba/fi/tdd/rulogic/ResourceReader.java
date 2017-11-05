package ar.uba.fi.tdd.rulogic;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;


public class ResourceReader {

    private String filepath;

    public ResourceReader(String path) {
        this.filepath = path;
    }

    public String read() {
        StringBuilder result = new StringBuilder("");
        ClassLoader classLoader = getClass().getClassLoader();
        URL res = classLoader.getResource(this.filepath);
        if (res == null) {
            return null;
        }

        File file = new File(res.getFile());
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

}