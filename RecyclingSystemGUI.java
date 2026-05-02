package project;

import javax.swing.*;
import java.awt.*;

public class RecyclingSystemGUI extends JFrame {

    private RecyclingSystem system = new RecyclingSystem();

    public RecyclingSystemGUI() {

        setTitle("Geri Donusum Takip Sistemi");
        setSize(540, 520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel title = new JLabel("Geri Donusum Sistemi", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        panel.add(title, BorderLayout.NORTH);

        JPanel buttons = new JPanel(new GridLayout(6, 1, 10, 10));

        JButton btnAddCustomer = new JButton("Musteri Ekle");
        JButton btnAddWaste = new JButton("Atik Ekle");
        JButton btnCustomerDetail = new JButton("Musteri Detaylari");
        JButton btnAllWastes = new JButton("Tum Atiklar");
        JButton btnVehicles = new JButton("Araclar");
        JButton btnExit = new JButton("Cikis");

        buttons.add(btnAddCustomer);
        buttons.add(btnAddWaste);
        buttons.add(btnCustomerDetail);
        buttons.add(btnAllWastes);
        buttons.add(btnVehicles);
        buttons.add(btnExit);

        panel.add(buttons, BorderLayout.CENTER);
        add(panel);

        // ================= MUSTERI EKLE =================
        btnAddCustomer.addActionListener(e -> {
            try {
                int id = Integer.parseInt(
                        JOptionPane.showInputDialog("Musteri ID:"));

                String name = JOptionPane.showInputDialog("Isim:");
                String phone = JOptionPane.showInputDialog("Telefon:");

                if (system.addCustomer(id, name, phone))
                    showMsg("Musteri eklendi.");
                else
                    showError("Bu ID zaten var!");

            } catch (Exception ex) {
                showError("Hatali giris!");
            }
        });

        // ================= ATIK EKLE =================
        btnAddWaste.addActionListener(e -> {
            try {
                int id = Integer.parseInt(
                        JOptionPane.showInputDialog("Musteri ID:"));

                if (!system.hasCustomer(id)) {
                    showError("Bu ID ye ait musteri yok!");
                    return;
                }

                String[] types = {"Plastik", "Cam", "Kagit"};
                String type = (String) JOptionPane.showInputDialog(
                        null, "Atik Turu", "Secim",
                        JOptionPane.QUESTION_MESSAGE,
                        null, types, types[0]);

                if (type == null)
                    return;

                double amount = Double.parseDouble(
                        JOptionPane.showInputDialog("Miktar (kg):"));

                if (system.addWaste(id, type, amount))
                    showMsg("Atik eklendi.");
                else
                    showError("Atik eklenemedi!");

            } catch (Exception ex) {
                showError("Hatali giris!");
            }
        });

        // ================= TEK MUSTERI DETAY (ID ILE) =================
        btnCustomerDetail.addActionListener(e -> {
            try {
                int id = Integer.parseInt(
                        JOptionPane.showInputDialog("Musteri ID giriniz:"));

                if (!system.hasCustomer(id)) {
                    showError("Bu ID ye ait musteri yok!");
                    return;
                }

                showText("Musteri Detayi",
                        system.getCustomerDetailById(id));

            } catch (Exception ex) {
                showError("Hatali giris!");
            }
        });

        btnAllWastes.addActionListener(e ->
                showText("Tum Atiklar",
                        system.getAllWastesText()));

        btnVehicles.addActionListener(e ->
                showText("Araclar",
                        system.getVehiclesText()));

        btnExit.addActionListener(e -> System.exit(0));
    }

    // ================= YARDIMCI METOTLAR =================
    private void showMsg(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(
                this, msg, "Hata", JOptionPane.ERROR_MESSAGE);
    }

    private void showText(String title, String text) {
        JTextArea area = new JTextArea(text, 15, 40);
        area.setEditable(false);
        JOptionPane.showMessageDialog(
                this,
                new JScrollPane(area),
                title,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new RecyclingSystemGUI().setVisible(true));
    }
}