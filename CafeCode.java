import java.io.IOException;
import java.util.*;
import java.io.File;
import java.util.List;

public class CafeCode {

    private static LinkedList<Dancer> unplacedDancers = new LinkedList<Dancer>();

    public static void main(String[] args)
            throws IOException {
        Scanner keyboard = new Scanner(System.in);
        String fileName = getFileName(keyboard);
        int numberOfDancers = getNumberOfDancers(keyboard);
        ReadExcel thisFile = new ReadExcel(fileName, numberOfDancers);
        LinkedList<Dancer> dancerSignedUp = thisFile.read();
        Dance[] possibleDance = getPossibleDances(dancerSignedUp);
        assignFirstPick(dancerSignedUp, possibleDance);
        boolean balanced = printStatus(possibleDance, keyboard);
        int attempt = 0;
        while (!balanced) {
            balanceDances(possibleDance, numberOfDancers,attempt);
            balanced = printStatus(possibleDance, keyboard);
            attempt++;
        }
        printDances(possibleDance);


    }

    public static String getFileName(Scanner keyboard) {
        System.out.print("Type the file name: ");
        String fileName = keyboard.nextLine().trim();
        File fileToCheck = new File(fileName);
        while (!fileToCheck.exists()) {
            System.out.print("Type a file that exists");
            fileName = keyboard.nextLine().trim();
            fileToCheck = new File(fileName);
        }
        return fileName;
    }

    public static int getNumberOfDancers(Scanner keyboard) {
        System.out.print("Type number of dancers: ");
        String numberOfDancersS = keyboard.nextLine().trim();
        int numberOfDancers = Integer.parseInt(numberOfDancersS);
        return numberOfDancers;
    }

    public static Dance[] getPossibleDances(LinkedList<Dancer> dancersSignedUp) {
        String[] allPossibleDances = new String[dancersSignedUp.size() * 5];

        for (int i = 0; i < dancersSignedUp.size(); i++) {
            String dance1Name = dancersSignedUp.get(i).getFirstDance();
            String dance2Name = dancersSignedUp.get(i).getSecondDance();
            String dance3Name = dancersSignedUp.get(i).getThirdDance();
            String dance4Name = dancersSignedUp.get(i).getFourthDance();
            String dance5Name = dancersSignedUp.get(i).getFifthDance();
            allPossibleDances[i] = dance1Name;
            allPossibleDances[i + dancersSignedUp.size()] = dance2Name;
            allPossibleDances[i + dancersSignedUp.size() * 2] = dance3Name;
            allPossibleDances[i + dancersSignedUp.size() * 3] = dance4Name;
            allPossibleDances[i + dancersSignedUp.size() * 4] = dance5Name;
        }

        Set<List<String>> noDuplicateDances = new HashSet<List<String>>();

        for (int i = 0; i < allPossibleDances.length; i++) {
            noDuplicateDances.add(Arrays.asList(allPossibleDances[i]));
        }

        int numberOfDances = noDuplicateDances.size();

        String[] possibleDances = new HashSet<String>(Arrays.asList(allPossibleDances)).toArray(new String[0]);

        Dance[] possibleDance = new Dance[numberOfDances];

        Dance dance = null;

        for (int i = 0; i < possibleDances.length; i++) {
            String danceName = possibleDances[i];
            LinkedList<Dancer> dancersInDance = new LinkedList<Dancer>();
            if (danceName.equals("reggaeton") || danceName.equals("samba")) {
                dance = new Dance(danceName, 0, false, dancersInDance);
            } else {
                dance = new Dance(danceName, 0, true, dancersInDance);
            }
            possibleDance[i] = dance;
        }
        Dance[] fixedPossibleDance = new Dance[possibleDance.length - 1];
        for (int i = 0; i < fixedPossibleDance.length; i++) {
            fixedPossibleDance[i] = possibleDance[i + 1];
        }
        possibleDance = fixedPossibleDance;
        return possibleDance;
    }


    public static void assignFirstPick(LinkedList<Dancer> dancersSignedUp, Dance[] possibleDance) {
        while (!dancersSignedUp.isEmpty()) {
            Dancer current = dancersSignedUp.getFirst();
            for (int k = 0; k < current.getNumberOfDances(); k++) {
                String danceName = current.dancePref[k];
                for (int j = 0; j < possibleDance.length; j++) {
                    if (possibleDance[j].getDanceName().equals(danceName) && !possibleDance[j].inDance(current)) {
                        possibleDance[j].addDancer(current);
                        if (!current.findPartner(current, dancersSignedUp).isEmpty
                                (current.findPartner(current, dancersSignedUp))) {
                            Dancer partner = current.findPartner(current, dancersSignedUp);
                            for (int l = 0; l < 5; l++) {
                                if (partner.dancePref[l].equals(possibleDance[j].getDanceName())) {
                                    possibleDance[j].addDancer(partner);
                                    if (partner.getNumberOfDances() == 1) {
                                        dancersSignedUp.remove(partner);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            dancersSignedUp.remove(current);
        }
    }

    public static boolean printStatus(Dance[] possibleDance, Scanner keyboard) {
        for (int i = 0; i < possibleDance.length; i++) {
            possibleDance[i].printStatus();
        }
        System.out.println("Press Y to print dances. Press N to balance dances.");
        String response = keyboard.nextLine().toLowerCase().trim();
        if (response.equals("y")) {
            return true;
        } else {
            return false;
        }
    }

    public static void balanceDances(Dance[] possibleDance, int numberOfDancers,int attempt) {
        for (int i = 0; i < possibleDance.length; i++) {
            if (possibleDance[i].getNumberOfDancers() < (numberOfDancers / possibleDance.length)) {
                // do nothing
            } else {
                LinkedList<Dancer> toBeMoved = possibleDance[i].balanceDance(numberOfDancers / possibleDance.length);
                assignNextPick(toBeMoved, possibleDance,attempt);
            }
        }
    }

    public static void assignNextPick(LinkedList<Dancer> movingDancers, Dance[] possibleDance,int attempt) {
        while(!movingDancers.isEmpty()){
            Dancer current = movingDancers.getFirst();
            if(current.getNumberOfDances() + attempt < 5) {
                String danceName = current.dancePref[current.getNumberOfDances() + attempt];
                for (int j = 0; j < possibleDance.length; j++) {
                    if (possibleDance[j].getDanceName().equals(danceName) && !possibleDance[j].inDance(current)) {
                        possibleDance[j].addDancer(current);
                        movingDancers.remove(current);
                    }
                }
                if (!movingDancers.isEmpty() && current == movingDancers.getFirst() && current.getNumberOfDances() + attempt < 3) {
                    danceName = current.dancePref[current.getNumberOfDances() + attempt + 1];
                    for (int j = 0; j < possibleDance.length; j++) {
                        if (possibleDance[j].getDanceName().equals(danceName) && !possibleDance[j].inDance(current)) {
                            possibleDance[j].addDancer(current);
                            movingDancers.remove(current);
                        }
                    }
                }
            }
            if (!movingDancers.isEmpty() && current == movingDancers.getFirst()) {
                unplacedDancers.add(current);
                movingDancers.remove(current);
            }
        }
    }

    public static void printDances(Dance[] possibleDance) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Type the file destination to export");
        String fileName = keyboard.nextLine().trim();
        WriteExcel sortedFile = new WriteExcel(fileName, possibleDance, unplacedDancers);
        sortedFile.write();
    }
}
