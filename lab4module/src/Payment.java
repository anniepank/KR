import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Payment
        implements Iterable<String>, Comparable<Payment>
{

    public static class ArgumentException extends Exception{
        ArgumentException(String str) { super (str); }
    }

    private String name;
    private int yearOfEmployment;
    private double salary;
    private int workingDays; // колькасць працоўных дзён на месяц
    private int performedDays; // колькасць адпрацаваных дзён за месяц
    private double surchargePercent; // адсотак надбаўкі
    private double taxPercent; // адсотак падаткаў
    private double accruedMoney; // налічаныя грошы
    private double withheldMoney; // утрыманыя грошы
    private double totalMoney; // агульная колькасць грошай

    private int fieldsNumber = 10;
    public String[] fieldsValues = new String[fieldsNumber];

    public enum fieldsEnum {
        name,
        yearOfEmployment,
        salary,
        workingDays,
        performedDays,
        surchargePercent,
        taxPercent,
        accruedMoney,
        withheldMoney,
        totalMoney
    }

    public static String[] fieldsNames = {
        "Імя", "Год найму", "Зарплата", "Працоўныя дні",
            "Адпрацаваныя дні", "Адсотак надбаўкі", "Адсотак падаткаў",
            "Налічаныя грошы", "Вылічаныя грошы", "Агульная сума"
    };

    public void fillFieldsValues(){
        fieldsValues[0] = name;
        fieldsValues[1] = Integer.toString(yearOfEmployment);
        fieldsValues[2] = Double.toString(salary);
        fieldsValues[3] = Integer.toString(workingDays);
        fieldsValues[4] = Integer.toString(performedDays);
        fieldsValues[5] = Double.toString(surchargePercent);
        fieldsValues[6] = Double.toString(taxPercent);
        fieldsValues[7] = Double.toString(accruedMoney);
        fieldsValues[8] = Double.toString(withheldMoney);
        fieldsValues[9] = Double.toString(totalMoney);
    }

    // вылічыць налічаную суму
    public double calculateAccruedMoney(){
        accruedMoney =  salary * performedDays / workingDays * (1 + surchargePercent);
        return accruedMoney;
    }

    // вылічыць утрыманую суму
    public double calculateWithheldMoney(){
        withheldMoney = calculateAccruedMoney() * taxPercent;
        return withheldMoney;
    }

    // сума грошай, якую выдаюць на рукі
    public double calculateTotalMoney(){
        totalMoney =  calculateAccruedMoney() - calculateWithheldMoney();
        return totalMoney;
    }

    public Payment(){
        // default constructor. no need to do anything
    }

    public Payment (String s) throws ArgumentException {
        initializeFromString(s);
    }

    public void initializeFromString(String s){

        ArrayList<String> al = new ArrayList<String>(Arrays.asList(s.split(" ")));
        al.removeAll(Arrays.asList("", null));

        assert (al.size() == 7) : "You must pass 7 arguments to initialize object";

        this.name = al.get(0);
        this.yearOfEmployment = Integer.valueOf(al.get(1));
        this.salary = Double.valueOf(al.get(2));
        this.workingDays = Integer.valueOf(al.get(3));
        this.performedDays = Integer.valueOf(al.get(4));
        this.surchargePercent = Double.valueOf(al.get(5));
        this.taxPercent = Double.valueOf(al.get(6));

        calculateTotalMoney();

    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (String val : this){
            sb.append(val);
            sb.append('|');
        }
        return sb.toString();
    }

    //-------- Iterable implementation

    @Override
    public Iterator<String> iterator() {
        return new PaymentIterator();
    }

    private class PaymentIterator implements Iterator<String> {

        public PaymentIterator(){
            fillFieldsValues();
            reset();
        }

        private int currentIx = -1;

        public void reset(){
            currentIx = -1;
        }

        @Override
        public boolean hasNext(){
            return (currentIx < fieldsNumber - 1);
        }

        @Override
        public String next() {
            if (hasNext()){
                return fieldsValues[++currentIx];
            }
            else
                throw new NoSuchElementException("Trying to access unexisting element");
        }
    }

    //-------- Comparable implementation

    private static int compareIndex = 0;

    @Override
    public int compareTo(Payment o) throws NullPointerException {

        fillFieldsValues(); // initialize fieldsValues
        o.fillFieldsValues();

        String s1 = fieldsValues[Payment.compareIndex];
        String s2 = o.fieldsValues[Payment.compareIndex];

        if (s1 == null || s2 == null)
            throw new NullPointerException("The object to compare has not initialized field");

        if (compareIndex == fieldsEnum.salary.ordinal()                 ||
                compareIndex == fieldsEnum.surchargePercent.ordinal()   ||
                compareIndex == fieldsEnum.taxPercent.ordinal()         ||
                compareIndex == fieldsEnum.accruedMoney.ordinal()       ||
                compareIndex == fieldsEnum.withheldMoney.ordinal()      ||
                compareIndex == fieldsEnum.totalMoney.ordinal()
                ){
            return Double.valueOf(s1).compareTo(Double.valueOf(s2));
        }

        return s1.compareTo(s2);
    }

    public static void setSortBy (fieldsEnum value){
        compareIndex = value.ordinal();
    }

    public static int getSortBy (){
        return fieldsEnum.values()[compareIndex].ordinal();
    }

    public static String[] fieldsNamesFormatSpecifiers =  {
            "%-10s",   // name
            "%-18s",   // yearOfEmployment
            "%-12s",   // salary
            "%-16s",   // workingDays
            "%-18s",  // performedDays
            "%-20s",  // surchargePercent
            "%-18s",   // taxPercent
            "%-18s",    // accruedMoney
            "%-18s",    // withheldMoney
            "%-18s"    // totalMoney
    };

    public static String[] fieldsFormatSpecifiers =  {
            "%-10s",   // name
            "%-18s",   // yearOfEmployment
            "%-12.10s",   // salary
            "%-16s",   // workingDays
            "%-18s",  // performedDays
            "%-20.10s",  // surchargePercent
            "%-18.10s",   // taxPercent
            "%-18.10s",    // accruedMoney
            "%-18.10s",    // withheldMoney
            "%-18.12s"    // totalMoney
    };

    public static String format( Iterable<String> src ) {
        StringBuilder result = new StringBuilder();
        int ix = 0;
        for( String str : src ) {
            result.append( String.format( fieldsFormatSpecifiers[ix++], str ) );
        }

        return result.toString();
    }

    public static String formatFieldsNames(){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < fieldsNames.length; i++){
            result.append( String.format ( fieldsNamesFormatSpecifiers[i], fieldsNames[i] ) );
        }

        return result.toString();
    }
}

