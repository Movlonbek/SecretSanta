package secret;

import java.util.ArrayList;
import java.util.Random;

public class Santa1 {

    private static Santa1 Santa1;

    public synchronized static Santa1 get() {
        if (Santa1 == null) {
            Santa1 = new Santa1();
        }
        return Santa1;
    }

    public String santaPart1(ArrayList<String> names, String secretSantaOf) {
        ArrayList<String> list = new ArrayList<String>();
        for (int a = 0; a < names.size(); a++) {
            list.add(names.get(a));
        }
        System.out.println("Family members are : " + list);
        list.remove(secretSantaOf);
        Random r = new Random();
        int index = r.nextInt(list.size());
        String secret = list.get(index);

        System.out.println(secretSantaOf + "'s secret Santa for this year is " + secret);
        return secret;
    }
}
