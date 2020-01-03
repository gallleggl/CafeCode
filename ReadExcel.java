
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;



public class ReadExcel {

    private static String fileName;
    private static int numberOfDancers;

    public ReadExcel(String fileName, int numberOfDancers) {
        this.fileName = fileName; this.numberOfDancers = numberOfDancers; }

    public LinkedList<Dancer> read()
            throws IOException {
        File inputWorkBook = new File(fileName);
        LinkedList<Dancer> dancerSignedUp = new LinkedList<Dancer>();

        try {
            Workbook w = Workbook.getWorkbook(inputWorkBook); // gets the workbook with file name attached to inputWorkbook
            Sheet sheet = w.getSheet(0); // gets the first sheet in the workbook

            for (int i = 0; i < numberOfDancers; i++) {
                Cell emailCell = sheet.getCell(0,i);
                String email = emailCell.getContents();
                Cell firstNameCell = sheet.getCell(1, i);
                Cell lastNameCell = sheet.getCell(2,i);
                String dancerName = firstNameCell.getContents().toLowerCase().trim() + " " + lastNameCell.getContents().toLowerCase().trim();
                Cell phoneCell = sheet.getCell(3,i);
                String phone = phoneCell.getContents();
                Cell positionCell = sheet.getCell(4,i);
                String position = positionCell.getContents().toLowerCase();
                Cell firstDanceCell = sheet.getCell(5,i);
                String firstDance = firstDanceCell.getContents().toLowerCase().trim();
                Cell secondDanceCell = sheet.getCell(6,i);
                String secondDance = secondDanceCell.getContents().toLowerCase().trim();
                Cell thirdDanceCell = sheet.getCell(7,i);
                String thirdDance = thirdDanceCell.getContents().toLowerCase().trim();
                Cell fourthDanceCell = sheet.getCell(8,i);
                String fourthDance = fourthDanceCell.getContents().toLowerCase().trim();
                Cell fifthDanceCell = sheet.getCell(9,i);
                String fifthDance = fifthDanceCell.getContents().toLowerCase().trim();
                Cell numberOfDancesCell = sheet.getCell(10,i);
                String numberOfDancesS = numberOfDancesCell.getContents();
                int numberOfDances = Integer.parseInt(numberOfDancesS);
                Cell partnerCell = sheet.getCell(11,i);
                String partner = partnerCell.getContents().toLowerCase().trim();
                if(partner.equals(dancerName)) {
                    partner = "";
                }
                Cell shirtSizeCell = sheet.getCell(12,i);
                String shirtSize = shirtSizeCell.getContents();
                Cell yearCell = sheet.getCell(13,i);
                String year = yearCell.getContents();
                dancerSignedUp.add(new Dancer(dancerName, position, firstDance, secondDance, thirdDance, fourthDance,
                            fifthDance, numberOfDances, partner,email,phone, shirtSize, year));
            }
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return dancerSignedUp;
    }
}
