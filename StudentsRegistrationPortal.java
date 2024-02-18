import java.util.Scanner;

public class StudentsRegistrationPortal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String firstName;
        String lastName;
        String sex;
        int age;
        String department;
        int academicYear;
        String currentSemester;
        double gpa;
        System.out.println();
        System.out.println("  WELCOME TO HARAMAYA UNIVERSITY STUDENT'S REGISTRATION PORTAL!");
        System.out.println("\t********* BY ABDINAF *****************");

        while (true) {
            System.out.println("Enter your first name:");
            firstName = scanner.nextLine();

            if (isValidName(firstName)) {
                break;
            } else {
                System.out.println("This name doesn't exist. Please Re-enter.");
            }
        }

        while (true) {
            System.out.println("Enter your last name:");
            lastName = scanner.nextLine();

            if (isValidName(lastName)) {
                break;
            } else {
                System.out.println("This name doesn't exist. Please try again.");
            }
        }

        while (true) {
            System.out.println("Enter your sex:");
            sex = scanner.nextLine().toLowerCase();

            if (isValidSex(sex)) {
                break;
            } else {
                System.out.println("Invalid sex. Please Enter male(M) or female(F).");
            }
        }

        while (true) {
            System.out.println("Enter your age:");
            String ageStr = scanner.nextLine();

            if (isValidAge(ageStr)) {
                age = Integer.parseInt(ageStr);
                break;
            } else {
                System.out.println("Your age should be between 18 and 50. Please Re-enter!.");
            }
        }

        while (true) {
            System.out.println("Enter your department:");
            department = scanner.nextLine();

            if (isValidName(department)) {
                break;
            } else {
                System.out.println("There is no such department in our university. Please try again.");
            }
        }

        while (true) {
            System.out.println("Enter your academic year:");
            String academicYearStr = scanner.nextLine();

            if (isValidAcademicYear(academicYearStr)) {
                academicYear = Integer.parseInt(academicYearStr);
                break;
            } else {
                System.out.println("Academic year should be between 1 and 7. Please try again.");
            }
        }

        while (true) {
            System.out.println("Enter your current semester:");
            currentSemester = scanner.nextLine().toLowerCase();

            if (isValidSemester(currentSemester)) {
                break;
            } else {
                System.out.println("Semester should be 1, 2, i, or ii. Please try again.");
            }
        }

        while (true) {
            System.out.println("Enter your GPA:");
            String gpaStr = scanner.nextLine();

            if (isValidGpa(gpaStr)) {
                gpa = Double.parseDouble(gpaStr);
                break;
            } else {
                System.out.println("Your GPA must be between 2.00 and 4.00. Please re-enter.");
            }
        }

        // Create a Student object with the entered information
        Student student = new Student(firstName, lastName, sex, age, department,
                academicYear, currentSemester, gpa);

        System.out.println("\tThese are all about the required informations!");
        System.out.println("\t   Choose one of the following options!");

        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("---------------------");
            System.out.println("1. View Profile");
            System.out.println("2. Re-enter Information");
            System.out.println("3. Submit");
            System.out.println("---------------------");
            System.out.println("Enter your choice:");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewProfile(student);
                    break;
                case "2":
                    student = reenterInformation();
                    break;
                case "3":
                    System.out.println("\tCONGRATULATIONS!!");
                    System.out.println("You have successfully REGISTERED for this semester!!");
                    System.out.println("\t Take your slip from your department head office!!");
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    private static boolean isValidName(String name) {
        return name.matches("^[a-zA-Z]+$");
    }

    private static boolean isValidSex(String sex) {
        return sex.equals("male") || sex.equals("female") || sex.equals("m") || sex.equals("f");
    }

    private static boolean isValidAge(String ageStr) {
        try {
            int age = Integer.parseInt(ageStr);
            return age >= 18 && age <= 50;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isValidAcademicYear(String academicYearStr) {
        try {
            int academicYear = Integer.parseInt(academicYearStr);
            return academicYear >= 1 && academicYear <= 7;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isValidSemester(String semester) {
        return semester.equals("1") || semester.equals("2") || semester.equals("i") || semester.equals("ii");
    }

    private static boolean isValidGpa(String gpaStr) {
        try {
            double gpa = Double.parseDouble(gpaStr);
            return gpa >= 2.00 && gpa <= 4.00;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void viewProfile(Student student) {
        System.out.println("Name: " + student.getFirstName() + " " + student.getLastName());
        System.out.println("Sex: " + student.getSex());
        System.out.println("Age: " + student.getAge());
        System.out.println("Department: " + student.getDepartment());
        System.out.println("Academic Year: " + student.getAcademicYear());
        System.out.println("Current Semester: " + student.getCurrentSemester());
        System.out.println("GPA: " + student.getGpa());
    }

    private static Student reenterInformation() {
        // Prompt the user to re-enter information
        // and return a new Student object with the updated information
        // or modify the existing student object and return it
        return null; // Replace with appropriate implementation
    }

    // Student class implementation is not provided in the code you shared
    // You can add the Student class implementation here or replace with a placeholder
    // class or use an existing class if available.
    private static class Student {
        private String firstName;
        private String lastName;
        private String sex;
        private int age;
        private String department;
        private int academicYear;
        private String currentSemester;
        private double gpa;

        public Student(String firstName, String lastName, String sex, int age, String department, int academicYear, String currentSemester, double gpa) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.sex = sex;
            this.age = age;
            this.department = department;
            this.academicYear = academicYear;
            this.currentSemester = currentSemester;
            this.gpa = gpa;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getSex() {
            return sex;
        }

        public int getAge() {
            return age;
        }

        public String getDepartment() {
            return department;
        }

        public int getAcademicYear() {
            return academicYear;
        }

        public String getCurrentSemester() {
            return currentSemester;
        }

        public double getGpa() {
            return gpa;
        }
    }
}