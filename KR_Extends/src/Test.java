public class Test {
    public static void main(String[] args) {
        MyContainer<Double> container = new MyContainer<>();
        container.add(5.0);
        container.add(-6.0);
        container.add(77.88);

        System.out.println(container.findMax());
    }
}
