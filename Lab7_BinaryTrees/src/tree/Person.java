package tree;

public class Person implements Comparable<Person> {
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public int compareTo(Person person) {
        return name.compareTo(person.name);
    }

    @Override
    public String toString() {
        return name +", " + age;
    }
}
