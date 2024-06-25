package viniciusmiranda.view;

import javax.swing.*;

public class RegisterWindowView extends JFrame {

    private final JRadioButton clientRadioButton;
    private final JRadioButton managerRadioButton;
    private final JRadioButton employeeRadioButton;
    private final JRadioButton directorRadioButton;
    private ButtonGroup buttonGroup;
    private int accountType;

    public RegisterWindowView() {
        setTitle("User Type Selection");

        // Create radio buttons
        clientRadioButton = new JRadioButton("Cliente", true);
        employeeRadioButton = new JRadioButton("Funcionário");
        managerRadioButton = new JRadioButton("Gerente");
        directorRadioButton = new JRadioButton("Diretor");

        // Group radio buttons (only one can be selected)
        buttonGroup = new ButtonGroup();
        buttonGroup.add(clientRadioButton);
        buttonGroup.add(employeeRadioButton);
        buttonGroup.add(managerRadioButton);
        buttonGroup.add(directorRadioButton);

        // Create Register button
        JButton registerButton = new JButton("Register");

        // Action listener for Register button
        registerButton.addActionListener(ae -> {
            if (clientRadioButton.isSelected()) {
                accountType = 1;
            } else if (employeeRadioButton.isSelected()) {
                accountType = 2;
            } else if (managerRadioButton.isSelected()) {
                accountType = 3;
            } else {
                accountType = 4;
            }
            System.out.println("Selected User Type: " + accountType);
        });

        // Add components to panel
        JPanel panel = new JPanel();
        panel.add(clientRadioButton);
        panel.add(employeeRadioButton);
        panel.add(managerRadioButton);
        panel.add(directorRadioButton);
        panel.add(registerButton);

        // Add panel to frame
        add(panel);

        // Set frame properties
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
