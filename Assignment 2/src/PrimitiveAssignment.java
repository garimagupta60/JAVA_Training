public class PrimitiveAssignment {
    public static void main(String[] args) {

        int value1 = 10;
        int value2 = value1;
        System.out.println("Before modification:");
        System.out.println("value1 = " + value1);
        System.out.println("value2 = " + value2);
        value2 = 99;
        System.out.println("\nAfter modifying value2 (value2 = 99):");
        System.out.println("value1 = " + value1);
        System.out.println("value2 = " + value2);
    }
}
