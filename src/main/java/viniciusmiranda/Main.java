package viniciusmiranda;

import javax.swing.SwingUtilities;

import viniciusmiranda.view.*;

public class Main {
    int accountType = 0;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainView::new);
    }
}