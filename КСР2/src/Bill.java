import java.io.Serializable;
import java.util.Scanner;

public class Bill implements Serializable{
    String houseNumber;
    String flatNumber;
    String address;
    String name;
    String paymentDate;
    String paymentAmount;
    String surchargeProcent;
    String delayInDays;

    public static Bill read( Scanner fin ) {
        Bill bill = new Bill();
        bill.houseNumber = fin.nextLine();
        if ( ! fin.hasNextLine()) return null;
        bill.flatNumber = fin.nextLine();
        if ( ! fin.hasNextLine()) return null;
        bill.address = fin.nextLine();
        if ( ! fin.hasNextLine()) return null;
        bill.name = fin.nextLine();
        if ( ! fin.hasNextLine()) return null;
        bill.paymentDate = fin.nextLine();
        if ( ! fin.hasNextLine()) return null;
        bill.paymentAmount = fin.nextLine();
        if ( ! fin.hasNextLine()) return null;
        bill.surchargeProcent = fin.nextLine();
        if ( ! fin.hasNextLine()) return null;
        bill.delayInDays = fin.nextLine();
        return bill;
    }

    public String toString() {
        return houseNumber + "|" + flatNumber + "|" +
                address + "|" +
                name + "|" +
                paymentDate + "|" +
                paymentAmount + "|" +
                surchargeProcent + "|" +
                delayInDays;
    }
}
