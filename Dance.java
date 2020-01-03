import java.util.LinkedList;
import java.util.ListIterator;

public class Dance {
    private  String danceName;
    private  int numberOfDancers;
    private  boolean partnered;
    private  LinkedList<Dancer> dancersInDance;

    public Dance(String danceName, int numberOfDancers, boolean partnered, LinkedList<Dancer> dancersInDance) {
        this.danceName = danceName;
        this.numberOfDancers = numberOfDancers;
        this.partnered = partnered;
        this.dancersInDance = dancersInDance;
    }

    public  void addDancer(Dancer dancer) {
        dancersInDance.add(dancer);
        numberOfDancers = numberOfDancers + 1;
    }

    public void removeDancer(Dancer dancer) {
        this.dancersInDance.remove(dancer);
        numberOfDancers = numberOfDancers - 1;
    }

    public boolean inDance(Dancer dancer) {
        return dancersInDance.contains(dancer);
    }

    public String getDanceName() {
        return danceName;
    }

    public int getNumberOfDancers() {
        return numberOfDancers;
    }

    public int getLeads() {
        Dancer dancer;
        int numberOfLeads = 0;

        for (int i = 0; i < dancersInDance.size(); i++) {
            dancer = dancersInDance.get(i);
            if (dancer.getPosition().toLowerCase().equals("lead") || dancer.getPosition().toLowerCase().equals("either/both")) {
                numberOfLeads = numberOfLeads + 1;
            }
        }
        return numberOfLeads;
    }

    public int getFollows() {
        Dancer dancer;
        int numberOfFollows = 0;

        for (int i = 0; i < dancersInDance.size(); i++) {
            dancer = dancersInDance.get(i);
            if (dancer.getPosition().toLowerCase().equals("follow") || dancer.getPosition().toLowerCase().equals("both")) {
                numberOfFollows = numberOfFollows + 1;
            }
        }
        return numberOfFollows;
    }

    public boolean equalPartners() {
        int numberOfLeads = this.getLeads();
        int numberOfFollows = this.getFollows();
        if (numberOfLeads == numberOfFollows) {
            return true;
        } else {
            return false;
        }
    }

    public void printDance() {
        System.out.println(danceName);
        System.out.println();

        for (int i = 0; i < dancersInDance.size(); i++) {
            String dancerName = dancersInDance.get(i).getName();
            System.out.println(dancerName);
        }
    }

    public Dancer getDancer(int i){
        return dancersInDance.get(i);
    }

    public void printStatus() {
        System.out.println(danceName + ": " + numberOfDancers + " Leads: " + this.getLeads() + " Follows: " + this.getFollows());
    }

    public LinkedList<Dancer> balanceDance(int cutoff){
        LinkedList<Dancer> toBeMoved = new LinkedList<Dancer>();
        while(cutoff < numberOfDancers) {
            Dancer current = dancersInDance.get(cutoff);
            toBeMoved.add(current);
            this.removeDancer(current);
        }
        return toBeMoved;
    }
}
