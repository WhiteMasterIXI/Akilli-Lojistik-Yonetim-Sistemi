package View;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class InventoryView {

    public static JPanel createInventoryPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245,245,245));
        // 🔹 ORTA PANEL (ürün listesi)
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);

        String[] columns = {"ID", "Ürün Adı", "Stok", "Fiyat","Islem"};
        
        JButton Button = new JButton("Duzenle");
        Button.setBackground(new Color(60,100,230));

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));


        // 🔹 SAĞ PANEL (istatistik)
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(200, 0));
        rightPanel.setBackground(new Color(250,250,250));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));



        // tarih
        JLabel dateLabel = new JLabel("Tarih: " + LocalDate.now());
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 13));

        // ekstra bilgi
        JLabel infoLabel = new JLabel("Durum: Aktif");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 13));

        rightPanel.add(Box.createVerticalStrut(20));
        rightPanel.add(dateLabel);
        rightPanel.add(Box.createVerticalStrut(20));
        rightPanel.add(infoLabel);

        // 🔹 ekle
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        return mainPanel;
    }

}