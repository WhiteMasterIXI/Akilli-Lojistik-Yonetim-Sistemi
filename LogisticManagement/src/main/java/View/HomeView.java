package View;

import javax.swing.*;
import java.awt.*;

public class HomeView {

    public static JPanel createHomePanel() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245,245,245));

        // ================= HEADER =================
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(52, 152, 219));
        headerPanel.setPreferredSize(new Dimension(100, 120));
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Akıllı Lojistik Yönetim Sistemi");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Tedarik • Depo • Sipariş • Kargo Yönetimi");
        subtitle.setForeground(Color.WHITE);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(Box.createVerticalGlue());
        headerPanel.add(title);
        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(subtitle);
        headerPanel.add(Box.createVerticalGlue());

        // ================= CENTER =================
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(245,245,245));
        centerPanel.setLayout(new GridLayout(2,2,20,20));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));

        centerPanel.add(createCard(
                "Depo Yönetimi",
                "Depolardaki ürün stoklarını görüntüleyin, güncelleyin ve minimum stok kontrollerini yönetin."
        ));

        centerPanel.add(createCard(
                "Sipariş Takibi",
                "Müşteri siparişlerini takip edin, teslim tarihlerini görüntüleyin ve durum güncelleyin."
        ));

        centerPanel.add(createCard(
                "Ürün Yönetimi",
                "Ürün ekleme, silme, fiyat düzenleme ve parça bazlı ürün yönetimi yapabilirsiniz."
        ));

        centerPanel.add(createCard(
                "Kargo & Tedarik",
                "Kargo süreçlerini yönetin ve tedarikçi bağlantılarınızı merkezi sistemden kontrol edin."
        ));

        // ================= RIGHT INFO =================
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(260,0));
        rightPanel.setBackground(new Color(252,252,252));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JLabel kampanyaTitle = new JLabel("Duyurular");
        kampanyaTitle.setFont(new Font("Arial", Font.BOLD, 20));

        JTextArea kampanyaText = new JTextArea(
                """
                • Yeni depo entegrasyonu aktif edildi.
                
                • Kargo takip sistemi güncellendi.
                
                • Düşük stok uyarı sistemi aktif.
                
                • Yeni kullanıcı rolleri eklendi.
                
                • Haftalık performans raporları hazırlanıyor.
                """
        );

        kampanyaText.setEditable(false);
        kampanyaText.setLineWrap(true);
        kampanyaText.setWrapStyleWord(true);
        kampanyaText.setBackground(new Color(252,252,252));
        kampanyaText.setFont(new Font("Arial", Font.PLAIN, 14));

        rightPanel.add(kampanyaTitle);
        rightPanel.add(Box.createVerticalStrut(20));
        rightPanel.add(kampanyaText);

        // ================= FOOTER =================
        JPanel footer = new JPanel();
        footer.setBackground(new Color(230,230,230));
        footer.setPreferredSize(new Dimension(100,40));

        JLabel footerText = new JLabel("Akıllı Lojistik Yönetim Sistemi © 2026");
        footerText.setForeground(Color.DARK_GRAY);

        footer.add(footerText);

        // ================= ADD =================
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        mainPanel.add(footer, BorderLayout.SOUTH);

        return mainPanel;
    }

    private static JPanel createCard(String titleText, String descText) {

        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220,220,220)),
                BorderFactory.createEmptyBorder(15,15,15,15)
        ));

        JLabel title = new JLabel(titleText);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(new Color(52, 152, 219));

        JTextArea desc = new JTextArea(descText);
        desc.setEditable(false);
        desc.setLineWrap(true);
        desc.setWrapStyleWord(true);
        desc.setBackground(Color.WHITE);
        desc.setFont(new Font("Arial", Font.PLAIN, 14));

        card.add(title, BorderLayout.NORTH);
        card.add(desc, BorderLayout.CENTER);

        return card;
    }
}