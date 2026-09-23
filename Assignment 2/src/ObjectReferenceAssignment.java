public class ObjectReferenceAssignment {
    public static void main(String[] args) {

        Person person1 = new Person();
        person1.setName("Garima");
        Person person2 = person1;

        System.out.println("Before modification:");
        System.out.println("person1 = " + person1);
        System.out.println("person2 = " + person2);

        person2.setName("Ravi");

        System.out.println("After modifying person2:");
        System.out.println("person1 = " + person1);
        System.out.println("person2 = " + person2);
    }
}
