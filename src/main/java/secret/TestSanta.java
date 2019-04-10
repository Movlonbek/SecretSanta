package secret;

import java.util.ArrayList;

public class TestSanta {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("Zan");
        list.add("Syed");
        list.add("Sabuz");
        list.add("Shafiq");
        list.add("Selina");
        list.add("Ron");
        list.add("Jack");
        list.add("Roy");

        //Santa1.get().santaPart1(list, "Zan");
        Santa2.get().santaPart2(list, "Zan");

    }
}
