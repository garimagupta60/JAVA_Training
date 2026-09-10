class Student {

    String name;

    Student(String name) {
        this.name = name;
    }
}

public class Main {

    public static void main(String[] args) {

        final Student student = new Student("Rahul");
        student.name = "Amit";
        System.out.println(student.name);
        student = new Student("Priya");
    }
}