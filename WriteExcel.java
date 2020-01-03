import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class WriteExcel {
    private String fileName;
    private Dance[] dances;
    private LinkedList<Dancer> unplacedDancers;

    public WriteExcel(String fileName, Dance[] dances, LinkedList<Dancer> unplacedDancers){
        this.fileName = fileName;
        this.dances = dances;
        this.unplacedDancers = unplacedDancers;
    }

    public void write() {
        try {
            FileWriter fw = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fw);
            for(int i = 0; i < dances.length; i++) {
                printWriter.println(dances[i].getDanceName());
                printWriter.println("Number of Dancers: " + dances[i].getNumberOfDancers() + " Leads: " + dances[i].getLeads() + " Follows: " + dances[i].getFollows());
                printWriter.println();
                printWriter.println("Dancer Name \t Email \t Phone Number \t Shirt Size \t Year");
                for(int j = 0; j < dances[i].getNumberOfDancers(); j++) {
                    printWriter.println(dances[i].getDancer(j).getName() + "\t" + dances[i].getDancer(j).getEmail() + "\t" +  dances[i].getDancer(j).getPhone() + "\t" + dances[i].getDancer(j).getShirtSize() + "\t"+ dances[i].getDancer(j).getYear());
                }
                printWriter.println();
            }

            printWriter.println("Unplaced Dancers: " + unplacedDancers.size());
            printWriter.println("Dancer Name \t Lead/Follow \t First Pick \t Second Pick \t Third Pick \t Fourth Pick \t Partner");
            for(int k = 0; k < unplacedDancers.size(); k++){

                printWriter.println(unplacedDancers.get(k).getName() + "\t" + unplacedDancers.get(k).getPosition() + "\t" + unplacedDancers.get(k).getFirstDance() + "\t" +
                        unplacedDancers.get(k).getSecondDance() + "\t" + unplacedDancers.get(k).getThirdDance() + "\t" + unplacedDancers.get(k).getFourthDance() + "\t" +
                        unplacedDancers.get(k).getPartner());
            }
            printWriter.close();
        } catch (IOException ex){
            System.out.println("Print Exception");
        }
    }
}
