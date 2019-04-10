package secret;

import utility.DataReader;

import java.util.*;

public class Santa3 {
    private static Santa3 Santa3;

    public synchronized static Santa3 get() {
        if (Santa3 == null) {
            Santa3 = new Santa3();
        }
        return Santa3;
    }

    //  public String santaPart3(ArrayList<String> familyNames, String secretSantaOf) {

    public static void main(String[] args) {

        List<String> familyOfZan = new LinkedList<String>();
        familyOfZan.add("Zan's Son");
        familyOfZan.add("Zan's Doughter");
        familyOfZan.add("Zan's Wife");

        LinkedList<String> familyOfMim = new LinkedList<String>();
        familyOfMim.add("Mim's Son");
        familyOfMim.add("Mim's Doughter");
        familyOfMim.add("Mim's Husband");

        List<String> familyOfEva = new LinkedList<String>();
        familyOfEva.add("Eva's Son");
        familyOfEva.add("Eva's Doughter");
        familyOfEva.add("Eva's Husband");

        List<String> familyOfSyed = new LinkedList<String>();
        familyOfSyed.add("Syed's Son");
        familyOfSyed.add("Syed's Doughter");
        familyOfSyed.add("Syed's Wife");

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("Zan", familyOfZan);
        map.put("Mim", familyOfMim);
        map.put("Eva", familyOfEva);
        map.put("Syed", familyOfSyed);


        String secretSantaOf = "Zan";
        /*ArrayList<String> list = new ArrayList<String>();
        for (int a = 0; a < map.size(); a++) {
            list.add(names.get(a));
        }
        System.out.println("Family members are : "+list);*/

        String data1 = DataReader.readText("src/main/resources/HistoryOf" + secretSantaOf + ".txt");
        System.out.println("Previous santa for " + secretSantaOf + " are : " + data1);
        //String[] arr = data1.split(",");

      /*  for(Map.Entry<String, List<String>> key: map.entrySet()) {
            map.get(key.getKey()).remove(arr[arr.length - 1]);
            map.get(key.getKey()).remove(arr[arr.length - 2]);
            map.get(key.getKey()).remove(arr[arr.length - 3]);
        }
*/
        List<String> list = new ArrayList<String>(map.keySet());
        List<String> list2 = new ArrayList<String>();
        for (int a = 0; a < list.size(); a++) {
            list2.add(list.get(a));
        }

        Iterator<Map.Entry<String, List<String>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            String arr = it.next().getValue().get(it.hashCode());
        }


        System.out.println("Main Family members are : " + list);
        System.out.println("All Family members are : " + list2);


        Random r = new Random();
        int index = r.nextInt(list2.size());
        String secret = list.get(index);

        System.out.println(secretSantaOf + "'s secret Santa for this year is " + secret);
        DataReader.appendStrToFile("src/main/resources/HistoryOf" + secretSantaOf + ".txt", secret);


    }
}
