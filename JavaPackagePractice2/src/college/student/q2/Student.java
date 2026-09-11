package college.student.q2;

public class Student {

    String name;
    int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void displayStudent() {
        System.out.println("Student Name: " + name);
        System.out.println("Student Age: " + age);

        StudentMarks marks = new StudentMarks(85);

        marks.displayMarks();
    }
}
