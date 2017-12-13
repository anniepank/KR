import java.util.Iterator;

public class Account implements Comparable<Account>, Iterable<Object> {

    @Override
    public Iterator<Object> iterator() {
        return new AccountIterator();
    }

    public enum CompareBy {
        name,
        accountNumber,
        currencyType,
        yearlyRate,
        amount
    }

    protected String name;
    protected int accountNumber;
    protected String currencyType;
    protected double yearlyRate;
    protected double amount;
    static CompareBy compareField = CompareBy.name;

    Account(String name,
            int accountNumber,
            String currencyType,
            double yearlyRate,
            double amount) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.currencyType = currencyType;
        this.yearlyRate = yearlyRate;
        this.amount = amount;
    }

    public Account(String s) {
        String fields[] = s.split(" ");
        assert s.length() == 5;
        name = fields[0];
        accountNumber = Integer.parseInt(fields[1]);
        currencyType = fields[2];
        yearlyRate = Double.parseDouble(fields[3]);
        amount = Integer.parseInt(fields[4]);
    }

    private <T extends Comparable<T>>
    int myCompare(Account a) {
        try {
            T valueA = (T) Account.class.getDeclaredField(compareField.toString()).get(this);
            T valueB = (T) Account.class.getDeclaredField(compareField.toString()).get(a);
            return valueA.compareTo(valueB);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int compareTo(Account account) {
        return myCompare(account);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(name);
        s.append(" ");
        s.append(accountNumber);
        s.append(" ");
        s.append(currencyType);
        s.append(" ");
        s.append(yearlyRate);
        s.append(" ");
        s.append(amount);
        return s.toString();
    }

    public void takeMoney(double a) throws NotEnoughFunds {
        if (a <= amount) {
            amount -= a;
        }
        else {
            throw new NotEnoughFunds();
        }
    }

    public void putMoney(double a) {
        amount += a;
    }

    public void makeRate(int days) {
        double newAmount = (double) Math.pow((1 + yearlyRate/100), (days/365));
        amount *= newAmount;
    }

    public class AccountIterator implements Iterator<Object> {
        int current = 0;

        public AccountIterator() {
            reset();
        }

        public void reset(){
            current = 0;
        }

        @Override
        public boolean hasNext() {
            return current != 5;
        }

        @Override
        public Object next() {
            Object[] objs = new Object[]{name, accountNumber, currencyType, yearlyRate, amount};
            return objs[current++];
        }
    }
}
