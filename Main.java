package project;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RecyclingSystemGUI gui = new RecyclingSystemGUI();
            gui.setVisible(true);
        });
    }
}