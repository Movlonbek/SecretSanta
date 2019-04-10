package secret;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Rough {
    public static String read(String filePath) {

        File file = new File(filePath);
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String fileContent = "";
        while (scan.hasNextLine()) {
            fileContent = fileContent.concat(scan.nextLine() + "\n");
        }
        return fileContent;

    }


    public static void write(String text, String filePath) {

        FileWriter writer = null;
        try {
            writer = new FileWriter(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public static void main(String[] args) {
        String read = read("/Users/zann/eclipse-workspace/SecretSanta/src/main/resources/Document.txt");

        HashMap<Integer,String> hashMap = new HashMap<Integer, String>();
        hashMap.put(1,"")
       // write("new","/Users/zann/eclipse-workspace/SecretSanta/src/main/resources/Document.txt");
    }*/
    public static void main(String[] args) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        BufferedReader in = new BufferedReader(new FileReader("src/main/resources/Document.txt"));
        String line = "";
        while ((line = in.readLine()) != null) {
            String parts[] = line.split("\t");
            for (int i = 0; i < parts.length; i++) {
                String newP[] = parts[i].split("=");
                map.put(newP[newP.length - 2], newP[newP.length - 1]);
            }
        }
        in.close();
        System.out.println(map.toString());
        System.out.println(map.keySet());
        System.out.println(map.values());
    }

//
  /*   map.remove(secretSantaOf);
        list.remove(arr[arr.length - 1]);
        list.remove(arr[arr.length - 2]);
        list.remove(arr[arr.length - 3]);

    Random r = new Random();
    int index = r.nextInt(list.size());
    String secret = list.get(index);

        System.out.println(secretSantaOf + "'s secret Santa for this year is " + secret);
        DataReader.appendStrToFile("src/main/resources/HistoryOf"+secretSantaOf+".txt", secret);*/


    //
}


