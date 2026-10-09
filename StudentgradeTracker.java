
import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {

    // Student class to store name and grades
    static class Student {
        String name;
        ArrayList<Double> grades;

        Student(String name) {
            this.name = name;
            grades = new ArrayList<>();
        }

        // Add subject marks
        void addGrade(double marks) {
            grades.add(marks);
        }

        // Calculate average marks
        double calculateAverage() {
            double sum = 0;

            for (double marks : grades) {
                sum += marks;
            }

            return grades.isEmpty() ? 0 : sum / grades.size();
        }

        // Find highest marks
        double findHighest() {
            double highest = grades.get(0);

            for (double marks : grades) {
                if (marks > highest) {
                    highest = marks;
                }
            }

            return highest;
        }

        // Find lowest marks
        double findLowest() {
            double lowest = grades.get(0);

            for (double marks : grades) {
                if (marks < lowest) {
                    lowest = marks;
                }
            }

            return lowest;
        }

        // Display student report
        void displayReport() {
            System.out.printf(
                "%-15s %-25s %-12.2f %-12.2f %-12.2f%n",
                name,
                grades.toString(),
                calculateAverage(),
                findHighest(),
                findLowest()
            );
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        int choice;

        do {
            System.out.println("\n==================================");
            System.out.println("      STUDENT GRADE TRACKER");
            System.out.println("==================================");
            System.out.println("1. Add Student");
            System.out.println("2. Display Student Reports");
            System.out.println("3. Search Student");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Enter a valid menu number: ");
                sc.next();
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter student name: ");
                    String name = sc.nextLine().trim();

                    if (name.isEmpty()) {
                        System.out.println(
                            "Student name cannot be empty."
                        );
                        break;
                    }

                    System.out.print("Enter number of subjects: ");

                    while (!sc.hasNextInt()) {
                        System.out.print(
                            "Enter a valid number of subjects: "
                        );
                        sc.next();
                    }

                    int count = sc.nextInt();

                    if (count <= 0) {
                        System.out.println(
                            "Number of subjects must be positive."
                        );
                        sc.nextLine();
                        break;
                    }

                    Student student = new Student(name);

                    for (int i = 0; i < count; i++) {
                        double marks;

                        while (true) {
                            System.out.print(
                                "Enter marks for subject "
                                + (i + 1) + " (0-100): "
                            );

                            if (!sc.hasNextDouble()) {
                                System.out.println(
                                    "Please enter a valid number."
                                );
                                sc.next();
                                continue;
                            }

                            marks = sc.nextDouble();

                            if (marks >= 0 && marks <= 100) {
                                break;
                            }

                            System.out.println(
                                "Marks must be between 0 and 100."
                            );
                        }

                        student.addGrade(marks);
                    }

                    sc.nextLine();
                    students.add(student);

                    System.out.println(
                        "Student added successfully!"
                    );
                    break;

                case 2:
                    if (students.isEmpty()) {
                        System.out.println(
                            "No student records available."
                        );
                        break;
                    }

                    System.out.println(
                        "\n========== SUMMARY REPORT =========="
                    );

                    System.out.printf(
                        "%-15s %-25s %-12s %-12s %-12s%n",
                        "Name", "Grades", "Average",
                        "Highest", "Lowest"
                    );

                    System.out.println(
                        "--------------------------------------------------------------------------"
                    );

                    for (Student s : students) {
                        s.displayReport();
                    }
                    break;

                case 3:
                    System.out.print(
                        "Enter student name to search: "
                    );

                    String searchName = sc.nextLine().trim();
                    boolean found = false;

                    for (Student s : students) {
                        if (s.name.equalsIgnoreCase(searchName)) {
                            System.out.println(
                                "\nStudent record found:"
                            );

                            System.out.printf(
                                "%-15s %-25s %-12s %-12s %-12s%n",
                                "Name", "Grades", "Average",
                                "Highest", "Lowest"
                            );

                            s.displayReport();
                            found = true;
                        }
                    }

                    if (!found) {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    System.out.println(
                        "Thank you for using Student Grade Tracker!"
                    );
                    break;

                default:
                    System.out.println(
                        "Invalid choice. Please select 1-4."
                    );
            }

        } while (choice != 4);

        sc.close();
    }
}
