package secret;

import utility.DataReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * This program uses the FamilyMembers class and allows a user to:
 * Create a list of participants
 * Create "Secret Santa" pairings
 * Each participant is randomly assigned a person to send a gift to.
 * There should not be two people sending a gift to the same person.
 * View all the "Secret Santa" pairings
 * View the recipient for a specific participant
 * Save the participants list and their pairings to a desired file
 *
 * @Author - Errr0rr404
 */

public class SecretSanta {

    /**
     * Represents the threshold multiplier to reset the loop when
     * an impossible matching scenario occurs
     */
    public static final int CountRetry = 2;

    public static void main(String[] args) {

        Random rand = new Random();
        Scanner console = new Scanner(System.in);
        printInTheConsole("LETS FIND SECRET SANTA ;) ");
        FamilyMembers[] familyMembers = createParticipants(console);

        String choice;
        do {
            printInTheConsole("MAIN MENU");
            System.out.println("Enter C : Create new familyMembers list.");
            System.out.println("Enter L : List the names of all familyMembers.");
            System.out.println("Enter P : Pair creation for the familyMembers.");
            System.out.println("Enter V : View all the pairings of familyMembers.");
            System.out.println("Enter S : Save familyMembers list and their pairings to a file.");
            System.out.println("Enter Q : Quit.");

            choice = console.nextLine();
            if (choice.equalsIgnoreCase("c")) {
                familyMembers = createParticipants(console);
            } else if (choice.equalsIgnoreCase("l")) {
                listParticipants(familyMembers);
            } else if (choice.equalsIgnoreCase("p")) {
                assignRecipients(familyMembers, rand);
            } else if (choice.equalsIgnoreCase("v")) {
                printAll(familyMembers);
            } else if (choice.equalsIgnoreCase("s")) {
                saveAll(familyMembers);
            }
            System.out.println();
        } while (!choice.equalsIgnoreCase("q"));
    }

    /**
     * This method Prints modified text to highlight
     *
     * @param text The desired text
     */
    public static void printInTheConsole(String text) {
        System.out.println("*** " + text + " ***");
    }


    /**
     * This method will Prompts the user for the number of participants and the names of each participant.
     *
     * @param console console for user input
     *                returns  list of FamilyMembers objects (FamilyMembers[])
     */
    public static FamilyMembers[] createParticipants(Scanner console) {
        System.out.println();
        printInTheConsole("CREATE YOUR PARTICIPANTS LIST");
        System.out.println();
        printInTheConsole("Enter the number of the desired familyMembers");
        int number = console.nextInt();
        while (number <= 0) {
            printInTheConsole("Please enter a valid number.");
            number = console.nextInt();
        }
        console.nextLine();

        FamilyMembers[] familyMembers = new FamilyMembers[number];
        for (int i = 0; i < familyMembers.length; i++) {
            System.out.println();
            printInTheConsole("Enter the name for participant #" + (i + 1));
            String name = console.nextLine();
            familyMembers[i] = new FamilyMembers(name);
        }

        System.out.println();
        return familyMembers;
    }

    /**
     * This method will Lists the names of each participant.
     *
     * @param familyMembers the desired list of FamilyMembers objects
     */
    public static void listParticipants(FamilyMembers[] familyMembers) {
        System.out.println();
        printInTheConsole("LIST NAMES OF ALL PARTICIPANTS");
        for (int i = 0; i < familyMembers.length; i++) {
            FamilyMembers person = familyMembers[i];
            System.out.println((i + 1) + ": \"" + person.getName() + "\"");
        }

        System.out.println();
    }

    /**
     * This method will Assigns the recipients of each participant.
     *
     * @param familyMembers the desired list of FamilyMembers objects
     * @param random        the random object
     */


    public static void assignRecipients(FamilyMembers[] familyMembers,
                                        Random random) {
        System.out.println();
        printInTheConsole("ASSIGN RECIPIENTS FOR ALL PARTICIPANTS");

        boolean allHaveMatches = false;
        while (!allHaveMatches) {
            resetMatches(familyMembers);

            for (int i = 0; i < familyMembers.length; i++) {
                FamilyMembers person = familyMembers[i];
                printInTheConsole("SEARCH FOR: \"" + person.getName().toUpperCase() + "\"");
                String data1;
                ArrayList<String> newArrayList = new ArrayList<String>();

                try {
                    data1 = DataReader.readText(familyMembers[i].name + "'sHistory.txt");
                    System.out.println("Previous santa for " + familyMembers[i].name + " was : " + data1);
                    String[] arrt = data1.split(",");
                    try {
                        newArrayList.add(arrt[arrt.length - 1]);
                        newArrayList.add(arrt[arrt.length - 2]);
                        newArrayList.add(arrt[arrt.length - 3]);
                    } catch (Exception e) {

                    }

                    int retryCount = 0;
                    while (!person.hasRecipient() &&
                            retryCount < familyMembers.length * CountRetry) {
                        int num = random.nextInt(familyMembers.length);
                        if (num != i && !familyMembers[num].hasSender()) {

                            boolean flag = false;
                            int number = 0;
                            if (newArrayList.size() == 1) {
                                number = 1;
                            } else if (newArrayList.size() == 2) {
                                number = 2;
                            } else if (newArrayList.size() == 3) {
                                number = 3;
                            }
                            for (int s = 0; s < number; s++) {
                                if (familyMembers[num].equals(newArrayList.get(s))) {
                                    flag = false;
                                } else flag = true;
                            }
                            if (flag) {
                                person.setRecipient(familyMembers[num]);
                                familyMembers[num].setSender(person);
                                System.out.println("Random participant found.");
                            } else {
                                List<String> pinklist = newArrayList;
                                newArrayList.add(familyMembers[i].name);
                                List<String> normallist = new ArrayList<String>();
                                for (int s = 0; s < familyMembers.length; s++) {
                                    normallist.add(familyMembers[s].name);
                                }
                                ArrayList<String> uniques = new ArrayList<String>(normallist);
                                uniques.removeAll(pinklist);

                                Random r = new Random();
                                int index = r.nextInt(uniques.size());
                                int x = findIndex(familyMembers, uniques, index);

                                System.out.println(x + "=x" + familyMembers[x].name);
                                person.setRecipient(familyMembers[x]);
                                familyMembers[x].setSender(person);
                            }
                        } else if (num == i) {
                            System.out.println("Random participant was themselves..." +
                                    "RETRYING.");
                        } else if (familyMembers[num].hasSender()) {
                            System.out.println("Random participant was \"" +
                                    familyMembers[num].getName() +
                                    "\"...RETRYING.");
                            retryCount++;
                        } else {
                            System.out.println("Other error...RETRYING");
                            retryCount++;
                        }
                        if (retryCount > familyMembers.length * CountRetry) {
                            printInTheConsole("OVER RETRY THRESHOLD...WILL RESET AFTER LOOP");
                        }
                    }
                    System.out.println();
                } catch (Exception ee) {

                    int retryCount = 0;
                    while (!person.hasRecipient() &&
                            retryCount < familyMembers.length * CountRetry) {
                        int num = random.nextInt(familyMembers.length);
                        if (num != i && !familyMembers[num].hasSender()) {
                            person.setRecipient(familyMembers[num]);
                            familyMembers[num].setSender(person);
                            System.out.println("Random participant found.");
                        } else if (num == i) {
                            System.out.println("Random participant was themselves..." +
                                    "RETRYING.");
                        } else if (familyMembers[num].hasSender()) {
                            System.out.println("Random participant was \"" +
                                    familyMembers[num].getName() +
                                    "\"...RETRYING.");
                            retryCount++;
                        } else {
                            System.out.println("Other error...RETRYING");
                            retryCount++;
                        }
                        if (retryCount > familyMembers.length * CountRetry) {
                            printInTheConsole("OVER RETRY THRESHOLD...WILL RESET AFTER LOOP");
                        }
                    }
                    System.out.println();
                }
            }
            allHaveMatches = allHaveMatches(familyMembers);

            if (allHaveMatches) {
                printInTheConsole("ALL PARTICIPANTS HAVE MATCHES");
            } else {
                printInTheConsole("NOT ALL PARTICIPANTS HAVE MATCHES");
                System.out.println();
                printInTheConsole("RESTARTING LOOP");
            }

            System.out.println();
        }


    }

    /**
     * This method will search with familyMember obj & return the index from an arraylist
     *
     * @param familyMembers the desired list of FamilyMembers objects
     * @param arrayList
     * @param index
     * @return int          index of the searched familyMember obj
     */

    public static int findIndex(FamilyMembers familyMembers[], ArrayList arrayList, int index) {
        if (familyMembers == null) {
            return -1;
        }
        int len = familyMembers.length;
        int i = 0;
        while (i < len) {
            if (familyMembers[i].name == arrayList.get(index)) {
                return i;
            } else {
                i = i + 1;
            }
        }
        return -1;
    }

    /**
     * This method will Checks if all familyMembers have matches/pairings
     *
     * @param familyMembers the desired list of FamilyMembers objects
     * @return boolean        whether or not all familyMembers have matches/pairings
     */
    public static boolean allHaveMatches(FamilyMembers[] familyMembers) {
        for (FamilyMembers person : familyMembers) {
            if (familyMembers.length > 10 && (!person.hasRecipient() || !person.hasSender())) {
                return false;
            } else if (!person.hasRecipient() && familyMembers.length < 10)
                return false;
        }
        return true;
    }

    /**
     * This method will Reset all matches/pairings of the familyMembers
     *
     * @param familyMembers the desired list of FamilyMembers objects
     */
    public static void resetMatches(FamilyMembers[] familyMembers) {
        for (FamilyMembers person : familyMembers) {
            person.resetRecipient();
            person.resetSender();
        }
    }

    /**
     * This method will Prints all matches/pairings of the familyMembers
     *
     * @param familyMembers the desired list of FamilyMembers objects
     */
    public static void printAll(FamilyMembers[] familyMembers) {
        System.out.println();
        printInTheConsole("MATCHES OF ALL PARTICIPANTS");

        for (FamilyMembers person : familyMembers) {
            System.out.println(person.getRecipientName().toUpperCase() +
                    "'s Secret Santa will be \"" + person.getName() + "\"");
        }

        System.out.println();
    }


    /**
     * This method will Prompts a user for a desired file and outputs all the familyMembers
     * names, and their pairings to the desired file
     *
     * @param familyMembers the desired list of FamilyMembers objects
     */
    public static void saveAll(FamilyMembers[] familyMembers) {
        System.out.println();
        printInTheConsole("SAVE ALL PARTICIPANTS NAMES AND THEIR PAIRINGS TO A PROPERTIES FILE");
        String history;
        if (allHaveMatches(familyMembers)) {
            for (int i = 0; i < familyMembers.length; i++) {
                FamilyMembers person = familyMembers[i];
                history = person.getRecipientName() + "'sHistory" + ".txt";
                try {
                    DataReader.readText(history);
                    DataReader.appendStrToFile(history, person.getName());
                } catch (Exception ee) {
                    DataReader.appendStrToFile(history, person.getName());
                }
            }
        } else {
            printInTheConsole(
                    "There are no pairings created for the familyMembers yet.");
            System.out.println();
        }
    }

    private static class FamilyMembers {

        private String name;
        private FamilyMembers recipient;
        private FamilyMembers sender;

        public FamilyMembers(String name) {
            this.name = name;

        }

        public String getName() {
            return name;
        }

        public boolean hasRecipient() {
            return recipient != null;
        }

        public String getRecipientName() {
            return recipient.getName();
        }

        public void setRecipient(FamilyMembers recipient) {
            this.recipient = recipient;
        }

        public void resetRecipient() {
            this.recipient = null;
        }

        public boolean hasSender() {
            return sender != null;
        }

        public void setSender(FamilyMembers sender) {
            this.sender = sender;
        }

        public void resetSender() {
            this.sender = null;
        }
    }

}