import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Account a1 = new Account("first", 10, "$", 5, 500);
        Account a2 = new Account("last", 10, "euro", 0.5, 100);

        System.out.println("Two account created. They are : ");
        System.out.println(a1);
        System.out.println(a2);

        Account.compareField = Account.CompareBy.accountNumber;
        System.out.println("compare field is account number");
        System.out.println("result in comparing is " + a1.compareTo(a2));

        Account.compareField = Account.CompareBy.name;
        System.out.println("Compare by name " + a1.compareTo(a2));

        for(Object o : a1) {
            System.out.println(o);
        }

        Account a3 = new Account("second 2 euro 25 300");

        System.out.println("----a3----");
        System.out.println(a3);


        Account array[] = {a1, a2, a3};
        Arrays.sort(array);

        System.out.println("Sorted by name");
        System.out.println();

        for (int i = 0 ; i < array.length; i++) {
            System.out.println(array[i]);
        }

        Account.compareField = Account.CompareBy.accountNumber;
        Arrays.sort(array);

        System.out.println("Sorted by Account number");
        System.out.println();

        for (int i = 0 ; i < array.length; i++) {
            System.out.println(array[i]);
        }


        Account.compareField = Account.CompareBy.amount;
        Arrays.sort(array);

        System.out.println();
        System.out.println("Sorted by amount");

        for (int i = 0 ; i < array.length; i++) {
            System.out.println(array[i]);
        }


        Account.compareField = Account.CompareBy.currencyType;
        Arrays.sort(array);

        System.out.println();
        System.out.println("Sorted by currency type");

        for (int i = 0 ; i < array.length; i++) {
            System.out.println(array[i]);
        }

        a3.putMoney(100);
        System.out.println(a3);

        try {
            a3.takeMoney(500);
        }
        catch(NotEnoughFunds e) {
            System.out.println("Not enough funds");
        }
        System.out.println(a3);

        a3.makeRate(365);
        System.out.println(a3);

        a3.putMoney(10);
        a3.makeRate(200);
        System.out.println(a3);

    }
}
