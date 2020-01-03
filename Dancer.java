import java.util.LinkedList;

public class Dancer {
    private String name;
    private  String position;
    public  String[] dancePref = new String[5];
    private int numberOfDances;
    private String partner;
    private String email;
    private String phone;
    private String shirtSize;
    private String year;

    public Dancer(String name, String position, String firstDance, String secondDance,
                  String thirdDance, String fourthDance, String fifthDance, int numberOfDances, String partner,String email, String phone, String shirtSize, String year) {
        this.name = name;
        this.position = position;
        this.dancePref[0] = firstDance;
        this.dancePref[1] = secondDance;
        this.dancePref[2] = thirdDance;
        this.dancePref[3] = fourthDance;
        this.dancePref[4] = fifthDance;
        this.numberOfDances = numberOfDances;
        this.partner = partner;
        this.email = email;
        this.phone = phone;
        this.shirtSize = shirtSize;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getFirstDance() {
        return dancePref[0];
    }

    public String getSecondDance() {
        return dancePref[1];
    }

    public String getThirdDance() {
        return dancePref[2];
    }

    public String getFourthDance() {
        return dancePref[3];
    }

    public String getFifthDance() {
        return dancePref[4];
    }

    public int getNumberOfDances() {
        return numberOfDances;
    }

    public String getPartner() {
        return partner;
    }

    public String getEmail() {return email;}

    public String getPhone() {return phone;}

    public String getShirtSize() {return shirtSize;}

    public String getYear() {return year;}

    public Dancer findPartner(Dancer primaryDancer, LinkedList<Dancer> dancerSignedUp) {
        for (int i = 0; i < dancerSignedUp.size(); i++) {
            if (primaryDancer.getPartner().equals(dancerSignedUp.get(i).getName())) {
                return dancerSignedUp.get(i);
            }
        }
        return null;
    }

    public static boolean isEmpty(Dancer dancer) {
        if (dancer == null) {
            return true;
        } else {
            return false;
        }
    }
}
