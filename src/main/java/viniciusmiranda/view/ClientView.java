package viniciusmiranda.view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import viniciusmiranda.model.Account;

public class ClientView {
    private String selectedAccount;
    public ClientView() {
        // Create an array of cities.
        String[] cities = { "New York", "Chicago", "Houston",
                "Denver", "Los Angeles", "Seattle",
                "London", "Paris", "New Delhi",
                "Hong Kong", "Tokyo", "Sydney" };

        JFrame jfrm = new JFrame("JListDemo");
        jfrm.setLayout(new FlowLayout());
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setSize(200, 200);
        // Create a JList.
        JList<String> jlst = new JList<String>(cities);
        // Set the list selection mode to single-selection.
        jlst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Add the list to a scroll pane.
        JScrollPane jscrlp = new JScrollPane(jlst);
        // Set the preferred size of the scroll pane.
        jscrlp.setPreferredSize(new Dimension(200, 300));
        // Make a label that displays the selection.
        JLabel jlab = new JLabel("Choose a City");
        JButton confirmButton = new JButton("Confirmar");

        jlst.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent le) {
                var city = jlst.getSelectedValue();
                jlab.setText("Current selection: " + city);
            }
        });
        // Add the list and label to the content pane.
        jfrm.add(jscrlp);
        jfrm.add(jlab);
        // Display the frame.
        jfrm.setVisible(true);
    }
}