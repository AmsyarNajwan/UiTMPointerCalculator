import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class App {

    @SuppressWarnings("unchecked")
    private static JComboBox<String>[] creditBoxes = new JComboBox[7];

    @SuppressWarnings("unchecked")
    private static JComboBox<String>[] gradeBoxes = new JComboBox[7];

    private static JTextField[] remarkFields = new JTextField[7]; // Add JTextField for remarks

    public static void main(String[] args) throws Exception {

        // Declare the ImageIcon variable outside of the try block
        ImageIcon originalLogo;

        try {
            // Attempt to load the image from an absolute path
            originalLogo = new ImageIcon("C:\\Users\\AMSYAR NAJWAN\\Desktop\\UiTM Pointer Calculator\\lib\\logo.png");
        } catch (Exception ex) {
            // If loading from the absolute path fails, fall back to the relative path
            originalLogo = new ImageIcon("logo.png");
        }

        // Main Frame
        JFrame frame = new JFrame();

        // Scale the logo
        Image scaledLogo = originalLogo.getImage().getScaledInstance(
            (int) (originalLogo.getIconWidth() * 0.30),
            (int) (originalLogo.getIconHeight() * 0.30),
            Image.SCALE_DEFAULT
        );

        ImageIcon logo = new ImageIcon(scaledLogo);

        frame.setTitle("UiTM Pointer Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 700);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(logo.getImage());
        frame.setResizable(false);

        // Title
        JLabel title = new JLabel("UiTM Pointer Calculator");
        title.setBounds(175, 20, 300, 50);
        frame.add(title);

        // Logo
        JLabel label = new JLabel();
        label.setIcon(logo);
        label.setBounds(180, -180, 400, 600);
        frame.add(label);
        frame.requestFocusInWindow();

        // Button
        JButton CalcButton = new JButton("Calculate");
        JButton ClearButton = new JButton("Clear");

        frame.add(CalcButton);
        frame.add(ClearButton);

        CalcButton.setBounds(100, 600, 100, 40);
        CalcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Calculate GPA
                double totalCreditHours = 0.0;
                double totalObtained = 0.0;

                for (int i = 0; i < creditBoxes.length; i++) {
                    String selectedCredit = (String) creditBoxes[i].getSelectedItem();
                    String selectedGrade = (String) gradeBoxes[i].getSelectedItem();

                    if (!selectedCredit.equals("-") && !selectedGrade.equals("-")) {
                        double credit = Double.parseDouble(selectedCredit);
                        double grade = getGradePoint(selectedGrade);

                        totalCreditHours += credit;
                        totalObtained += credit * grade;

                    }
                }

                if (totalCreditHours > 0.0) {
                    double gpa = totalObtained / totalCreditHours;
                    DecimalFormat df = new DecimalFormat("#.##");
                    String gpaFormatted = df.format(gpa);

                    JOptionPane.showMessageDialog(frame, "Your GPA is: " + gpaFormatted, "GPA Calculation", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "No courses selected for GPA calculation", "GPA Calculation", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        ClearButton.setBounds(300, 600, 100, 40);
        ClearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear all selected combo boxes and text fields to default
                clearComboBoxes(frame);
                clearTextFields();
            }
        });

        // JLabels for Text
        JLabel Credit = new JLabel("Credit Hour");
        Credit.setBounds(230, 170, 100, 30);
        frame.add(Credit);

        JLabel Grade = new JLabel("Grade");
        Grade.setBounds(350, 170, 100, 30);
        frame.add(Grade);

        JLabel Remarks = new JLabel("Course");
        Remarks.setBounds(50, 170, 100, 30);
        frame.add(Remarks);

        JLabel version = new JLabel("Ver 1.0");
        version.setBounds(440, 640, 100, 30);
        frame.add(version);

        JLabel name = new JLabel(" @AmsyarNajwan");
        name.setBounds(0, 640, 120, 30);
        frame.add(name);

        for (int i = 0; i < creditBoxes.length; i++) {
            creditBoxes[i] = new JComboBox<>(new String[]{"-", "1.00", "2.00", "3.00", "4.00", "5.00", "6.00", "7.00"});
            creditBoxes[i].setBounds(230, 200 + i * 50, 100, 30);
            frame.add(creditBoxes[i]);
            creditBoxes[i].requestFocusInWindow();

            gradeBoxes[i] = new JComboBox<>(new String[]{"-", "A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "E", "F"});
            gradeBoxes[i].setBounds(350, 200 + i * 50, 100, 30);
            frame.add(gradeBoxes[i]);
            gradeBoxes[i].requestFocusInWindow();

            // Add text fields for remarks
            remarkFields[i] = new JTextField("");
            remarkFields[i].setBounds(50, 200 + i * 50, 100, 30);
            frame.add(remarkFields[i]);
            remarkFields[i].setVisible(true);
            remarkFields[i].requestFocusInWindow();
        }
    }

    private static void clearComboBoxes(JFrame frame) {
        for (java.awt.Component component : frame.getContentPane().getComponents()) {
            if (component instanceof JComboBox) {
                JComboBox<?> comboBox = (JComboBox<?>) component;
                comboBox.setSelectedIndex(0);
            }
        }
    }

    private static void clearTextFields() {
        for (int i = 0; i < remarkFields.length; i++) {
            remarkFields[i].setText(""); // Clear text in text fields
        }
    }

    private static double getGradePoint(String grade) {
        switch (grade) {
            case "A+":
                return 4.00;
            case "A":
                return 4.00;
            case "A-":
                return 3.67;
            case "B+":
                return 3.33;
            case "B":
                return 3.00;
            case "B-":
                return 2.67;
            case "C+":
                return 2.33;
            case "C":
                return 2.00;
            case "C-":
                return 1.67;
            case "D+":
                return 1.33;
            case "D":
                return 1.00;
            case "E":
                return 0.67;
            case "F":
                return 0.00;
            default:
                return 0.00;
        }
    }
}
