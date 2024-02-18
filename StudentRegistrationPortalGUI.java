import javax.swing.*;

public class StudentRegistrationPortalGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Registration Portal");
        frame.setSize(3000, 2900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JLabel welcomeLabel = new JLabel("Welcome to Student's Registration Portal!");
        JLabel nameLabel = new JLabel("First Name:");
        JTextField nameField = new JTextField(20);
        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField(20);
        JLabel sexLabel = new JLabel("Sex (M/F):");
        JTextField sexField = new JTextField(5);
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField(5);
        JLabel departmentLabel = new JLabel("Department:");
        JTextField departmentField = new JTextField(20);
        JLabel academicYearLabel = new JLabel("Academic Year:");
        JTextField academicYearField = new JTextField(5);
        JLabel semesterLabel = new JLabel("Current Semester (I/II):");
        JTextField semesterField = new JTextField(5);
        JLabel gpaLabel = new JLabel("GPA:");
        JTextField gpaField = new JTextField(5);

        JButton submitButton = new JButton("Submit");

        JTextArea outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        frame.add(welcomeLabel);
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(lastNameLabel);
        frame.add(lastNameField);
        frame.add(sexLabel);
        frame.add(sexField);
        frame.add(ageLabel);
        frame.add(ageField);
        frame.add(departmentLabel);
        frame.add(departmentField);
        frame.add(academicYearLabel);
        frame.add(academicYearField);
        frame.add(semesterLabel);
        frame.add(semesterField);
        frame.add(gpaLabel);
        frame.add(gpaField);
        frame.add(submitButton);
        frame.add(scrollPane);

        submitButton.addActionListener(e -> {
            String firstName = nameField.getText();
            String lastName = lastNameField.getText();
            String sex = sexField.getText();
            int age = Integer.parseInt(ageField.getText());
            String department = departmentField.getText();
            int academicYear = Integer.parseInt(academicYearField.getText());
            String semester = semesterField.getText();
            double gpa = Double.parseDouble(gpaField.getText());

            outputArea.append("Name: " + firstName + " " + lastName + "\n");
            outputArea.append("Sex: " + sex + "\n");
            outputArea.append("Age: " + age + "\n");
            outputArea.append("Department: " + department + "\n");
            outputArea.append("Academic Year: " + academicYear + "\n");
            outputArea.append("Current Semester: " + semester + "\n");
            outputArea.append("GPA: " + gpa + "\n\n");
        });

        frame.setVisible(true);
    }
}
