package utility;

import java.io.*;

public class DataReader {
    public static String readText(String filePath) throws IOException {
        String text;
        String a = null;

        FileReader reader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(reader);
        while ((text = bufferedReader.readLine()) != null) {
            a = text;
        }
        reader.close();
        return a;
    }

    public static void appendStrToFile(String fileName, String str) {

        try {
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(fileName, true));
            out.write(str + ",");
            out.close();
        } catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }
}