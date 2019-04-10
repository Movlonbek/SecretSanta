package secret;

import utility.DataReader;

import java.util.ArrayList;
import java.util.Random;

public class Santa2 {
    private static Santa2 Santa2;

    public synchronized static Santa2 get() {
        if (Santa2 == null) {
            Santa2 = new Santa2();
        }
        return Santa2;
    }

    public String santaPart2(ArrayList<String> names, String secretSantaOf) {
        ArrayList<String> list = new ArrayList<String>();
        for (int a = 0; a < names.size(); a++) {
            list.add(names.get(a));
        }
        System.out.println("Family members are : " + list);

        String data1 = DataReader.readText("src/main/resources/HistoryOf" + secretSantaOf + ".txt");
        System.out.println("Previous santa for " + secretSantaOf + " are : " + data1);
        String[] arr = data1.split(",");
        list.remove(secretSantaOf);
        list.remove(arr[arr.length - 1]);
        list.remove(arr[arr.length - 2]);
        list.remove(arr[arr.length - 3]);

        Random r = new Random();
        int index = r.nextInt(list.size());
        String secret = list.get(index);

        System.out.println(secretSantaOf + "'s secret Santa for this year is " + secret);
        DataReader.appendStrToFile("src/main/resources/HistoryOf" + secretSantaOf + ".txt", secret);
        return secret;


    }
}
