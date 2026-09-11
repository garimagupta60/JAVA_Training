package college.q5;

public class Main {
    public static void main(String[] args) {
        college.q5.student.Student student = new college.q5.student.Student();
        college.q5.teacher.Student teacher = new college.q5.teacher.Student();

        student.study();
        teacher.teach();
    }
}
