package Lab7;

public class StudentSort {

    static class Student {
        String name;
        int cmsID;
        double gpa;

        Student(String name, int cmsID, double gpa) {
            this.name = name;
            this.cmsID = cmsID;
            this.gpa = gpa;
        }
    }
    public static void sortByGPA(Student[] students) {
        for (int i = 0; i < students.length - 1; i++) {
            for (int j = 0; j < students.length - i - 1; j++) {
                if (students[j].gpa < students[j + 1].gpa) {
                    // Swap students if next one has higher GPA
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
    }

    public static void printStudents(Student[] students) {
        for (Student s : students) {
            System.out.println(s.name + " | CMS-ID: " + s.cmsID + " | GPA: " + s.gpa);
        }
    }
    public static void main(String[] args) {
        Student[] students = {
                new Student("Ali", 101, 3.2),
                new Student("Sara", 102, 3.9),
                new Student("Bilal", 103, 2.8),
                new Student("Ayesha", 104, 3.5)
        };

        System.out.println("Before Sorting:");
        printStudents(students);
        sortByGPA(students);
        System.out.println("\nAfter Sorting by GPA (Descending):");
        printStudents(students);
    }
}
