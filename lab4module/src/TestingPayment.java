import java.util.Arrays;

// to enable assert tool, add -ea to VM options

public class TestingPayment {

    public static void sortAndPrintPaymentArray(Payment[] arr, Payment.fieldsEnum sortOrder){

        Payment.setSortBy(sortOrder);
        Arrays.sort( arr );

        System.out.println("------- Крытэр сартыроўкі: " + Payment.fieldsNames[Payment.getSortBy()] + " -------");
        System.out.println(Payment.formatFieldsNames());
        for (Payment p : arr){
            System.out.println(Payment.format(p));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        try{
            Payment[] paymentArray = new Payment[] {
                    new Payment ("Зміцер 1999 2000 20 17 0.4 0.15"),
                    new Payment ("Раман 1976 4005 25 4 0.2 0.17"),
                    new Payment ("Наста 1978 1450 14 14 0.5 0.24"),
                    new Payment ("Яўген 1980 1400 25 15 0.3 0.18"),
                    new Payment()
            };

//            paymentArray[paymentArray.length - 1].initializeFromString("Павал 1992 800 22 20 0.73 0.11");

            sortAndPrintPaymentArray(paymentArray, Payment.fieldsEnum.name);
            sortAndPrintPaymentArray(paymentArray, Payment.fieldsEnum.totalMoney);
            sortAndPrintPaymentArray(paymentArray, Payment.fieldsEnum.accruedMoney);
            sortAndPrintPaymentArray(paymentArray, Payment.fieldsEnum.surchargePercent);
            sortAndPrintPaymentArray(paymentArray, Payment.fieldsEnum.yearOfEmployment);

            System.out.println("<----------*   *----------->");
            System.out.println("paymentArray[3].toString(): " + paymentArray[3].toString());
            int cnt = 0;
            for (String s : paymentArray[3]){
                System.out.println(Payment.fieldsNames[cnt++] + ": " + s);
            }
        }
        catch (Exception e){
            System.err.println("Exception handling! " + e);
        }

    }

}
