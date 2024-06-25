package viniciusmiranda.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainView extends JFrame {
    // utilizar CardLayout para layouts differentes
    public MainView() {
        setTitle("Banco");
        setLayout(new FlowLayout());
        setVisible(true);
        setSize(200, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton btnRegister = new JButton("Cadastrar");
        JButton btnLogin = new JButton("Login");

        add(btnRegister);
        add(btnLogin);

        btnLogin.addActionListener(ae -> {
            setVisible(false);
            SwingUtilities.invokeLater(LoginWindowView::new);
        });
        btnRegister.addActionListener(ae -> {
            setVisible(false);
            SwingUtilities.invokeLater(RegisterWindowView::new);
        });
    }
}
