package View;

import Utils.Session;

import javax.swing.*;
import java.awt.*;

public class UserView {

    public static JPanel createUserPanel() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245,245,245));

        // ================= HEADER =================
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(52,152,219));
        headerPanel.setPreferredSize(new Dimension(100,100));
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Kullanici Profili");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Hesap bilgileri ve islemler");
        subtitle.setForeground(Color.WHITE);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(Box.createVerticalGlue());
        headerPanel.add(title);
        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(subtitle);
        headerPanel.add(Box.createVerticalGlue());

        // ================= CENTER =================
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(245,245,245));

        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(500,420));
        card.setBackground(Color.WHITE);
        card.setLayout(new GridBagLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220,220,220)),
                BorderFactory.createEmptyBorder(20,20,20,20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ================= USER INFO =================
        JLabel profileTitle = new JLabel("Kullanici Bilgileri");
        profileTitle.setFont(new Font("Arial", Font.BOLD, 22));
        profileTitle.setForeground(new Color(52,152,219));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        card.add(profileTitle, gbc);

        gbc.gridwidth = 1;

        addInfoRow(card, gbc, 1, "Ad:",
                Session.user != null ? Session.user.getName() : "-");

        addInfoRow(card, gbc, 2, "Soyad:",
                Session.user != null ? Session.user.getSurname() : "-");

        addInfoRow(card, gbc, 3, "Email:",
                Session.user != null ? Session.user.getEmail() : "-");

        addInfoRow(card, gbc, 4, "Telefon:",
                Session.user != null ? Session.user.getTelephone() : "-");

        addInfoRow(card, gbc, 5, "Rol:",
                Session.user != null && Session.user.getRole() != null
                        ? Session.user.getRole().getName()
                        : "-");

        // ================= BUTTONS =================
        JPanel buttonPanel = new JPanel(new GridLayout(2,2,15,15));
        buttonPanel.setBackground(Color.WHITE);

        JButton editBtn = createButton("Bilgileri Duzenle");
        JButton activityBtn = createButton("Islem Gecmisi");
        JButton ordersBtn = createButton("Siparislerim");
        JButton securityBtn = createButton("Guvenlik Ayarlari");

        buttonPanel.add(editBtn);
        buttonPanel.add(activityBtn);
        buttonPanel.add(ordersBtn);
        buttonPanel.add(securityBtn);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30,10,10,10);

        card.add(buttonPanel, gbc);

        // ================= ACTIONS =================

        editBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,
                    "Bilgi duzenleme ekrani yakinda eklenecek.");
        });

        activityBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,
                    "Kullanici hareketleri listelenecek.");
        });

        ordersBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,
                    "Siparis gecmisi listelenecek.");
        });

        securityBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,
                    "Guvenlik ayarlari yakinda aktif olacak.");
        });

        centerPanel.add(card);

        // ================= FOOTER =================
        JPanel footer = new JPanel();
        footer.setBackground(new Color(230,230,230));
        footer.setPreferredSize(new Dimension(100,40));

        JLabel footerText = new JLabel("Kullanici Paneli © 2026");
        footerText.setForeground(Color.DARK_GRAY);

        footer.add(footerText);

        // ================= ADD =================
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(footer, BorderLayout.SOUTH);

        return mainPanel;
    }

    private static void addInfoRow(
            JPanel panel,
            GridBagConstraints gbc,
            int y,
            String labelText,
            String valueText
    ) {

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 15));

        JLabel value = new JLabel(valueText);
        value.setFont(new Font("Arial", Font.PLAIN, 15));
        value.setForeground(Color.DARK_GRAY);

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(label, gbc);

        gbc.gridx = 1;
        panel.add(value, gbc);
    }

    private static JButton createButton(String text) {

        JButton btn = new JButton(text);

        btn.setBackground(new Color(52,152,219));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 14));

        return btn;
    }
}