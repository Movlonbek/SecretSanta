package utility;

import java.io.*;

public class DataReader {
    public static String readText(String filePath) {
        String text;
        String a = null;
        try {
            FileReader reader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while ((text = bufferedReader.readLine()) != null) {
                a = text;
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return a;
    }

    public static void appendStrToFile(String fileName, String str) {
        try {
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(fileName, true));
            out.write("," + str);
            out.close();
        } catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }

}
