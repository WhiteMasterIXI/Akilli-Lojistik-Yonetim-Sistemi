package View;

import NotifySystem.Notification;
import NotifySystem.notificationStatus;
import Services.FrontControllerPattern.Route;
import Services.FrontControllerPattern.ViewController;
import UserSystem.User;
import Utils.Session;

import javax.swing.*;

import Controller.NotificationController;
import Controller.UserController;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NotificationsView {

    public static JPanel createNotificationPanel() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245,245,245));

        // ================= HEADER =================
        JPanel header = new JPanel();
        header.setBackground(new Color(52,152,219));
        header.setPreferredSize(new Dimension(100,100));
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Bildirim Sistemi");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel sub = new JLabel("Mesajlar • Duyurular • Sistem Bildirimleri");
        sub.setForeground(Color.WHITE);
        sub.setFont(new Font("Arial", Font.PLAIN, 14));
        sub.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(Box.createVerticalGlue());
        header.add(title);
        header.add(Box.createVerticalStrut(8));
        header.add(sub);
        header.add(Box.createVerticalGlue());

        // ================= CENTER SPLIT =================
        JPanel center = new JPanel(new GridLayout(1,2,20,0));
        center.setBackground(new Color(245,245,245));
        center.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        // ================= LEFT (LIST) =================
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        JLabel listTitle = new JLabel("Bildirimler");
        listTitle.setFont(new Font("Arial", Font.BOLD, 20));
        listTitle.setForeground(new Color(52,152,219));

        leftPanel.add(listTitle);
        leftPanel.add(Box.createVerticalStrut(15));
        
        // burda bildirimleri çekeceğiz kişiye gelen okunmamış olanları
        List<Notification> notifications = NotificationController.getUnreadMessagesByUserId(Session.user.getId());
      
        for (Notification n : notifications) {

            JPanel card = new JPanel(new BorderLayout());
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
            card.setPreferredSize(new Dimension(0, 45));
            card.setBackground(new Color(250, 250, 250));

            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(220, 220, 220)),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));

            // ================= LEFT (TEXT) =================
            JPanel textPanel = new JPanel();
            textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
            textPanel.setBackground(new Color(250, 250, 250));

            JLabel msg = new JLabel(n.getMesaj());
            msg.setFont(new Font("Arial", Font.BOLD, 13));

            JLabel info = new JLabel(
                    "Gönderen: " + n.getSender()
                            + " | " + n.getTarih()
            );
            info.setFont(new Font("Arial", Font.PLAIN, 11));
            info.setForeground(Color.GRAY);

            textPanel.add(msg);
            textPanel.add(info);

            // ================= RIGHT (BUTTON) =================
            JButton readBtn = new JButton("Okundu");

            readBtn.setFocusPainted(false);
            readBtn.setBackground(new Color(52, 183, 83));
            readBtn.setForeground(Color.WHITE);
            readBtn.setFont(new Font("Arial", Font.BOLD, 11));
            readBtn.setPreferredSize(new Dimension(90, 30));

            readBtn.addActionListener(e -> {

                n.setNotificationStatus(notificationStatus.OKUNDU);
                NotificationController.Update(n);

                ViewController.RefreshContentPanel(Route.NOTIFICATION);
            });

            // ================= ADD =================
            card.add(textPanel, BorderLayout.CENTER);
            card.add(readBtn, BorderLayout.EAST);

            leftPanel.add(card);
            leftPanel.add(Box.createVerticalStrut(5));
        }

        JScrollPane scroll = new JScrollPane(leftPanel);
        scroll.setBorder(null);

        // ================= RIGHT (SEND PANEL) =================
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        JLabel sendTitle = new JLabel("Bildirim Gönder");
        sendTitle.setFont(new Font("Arial", Font.BOLD, 20));
        sendTitle.setForeground(new Color(52,152,219));

        JTextArea messageArea = new JTextArea(5,20);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);

        JScrollPane msgScroll = new JScrollPane(messageArea);
        
        List<User> users = UserController.ListUsers();

        
        List<User> all_users = UserController.ListUsers();
        JComboBox<User> usersCombobox = new JComboBox<User>();
        
        usersCombobox.addItem(null);
        
        for(User user : all_users) {
        	if(user != Session.user)
        	usersCombobox.addItem(user);
        }
        
        usersCombobox.setMaximumSize(new Dimension(Integer.MAX_VALUE,35));

        JButton sendBtn = new JButton("Gönder");
        sendBtn.setBackground(new Color(52,152,219));
        sendBtn.setForeground(Color.WHITE);
        sendBtn.setFocusPainted(false);
        sendBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE,40));

        sendBtn.addActionListener(e -> {

            String msg = messageArea.getText();
            //String target = targetField.getText();

            if(msg.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Mesaj boş olamaz");
                return;
            }

            Notification notify = new Notification();
            notify.setMesaj(msg);
            
            notify.setSender(Session.user.getName() + " " + Session.user.getSurname());
            notify.setNotificationStatus(notificationStatus.OKUNMADI);
            notify.setTarih(LocalDate.now());
            
            List<User> receiver_users = new ArrayList<>();
            
            if(usersCombobox.getSelectedItem() == null) {
            	receiver_users = all_users;
            }else {
            	receiver_users.add((User) usersCombobox.getSelectedItem());
            }
            
            
         if(   NotificationController.SendNotificationToUsers(notify, receiver_users))
            JOptionPane.showMessageDialog(null, "Bildirim gönderildi");
         else
        	 JOptionPane.showMessageDialog(null, "Bildirim gönderilemedi");

            messageArea.setText("");
            
            receiver_users.clear();
            ViewController.RefreshContentPanel(Route.NOTIFICATION);
        });

        rightPanel.add(sendTitle);
        rightPanel.add(Box.createVerticalStrut(15));
        rightPanel.add(new JLabel("Alıcı (opsiyonel)"));
        rightPanel.add(usersCombobox);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(new JLabel("Mesaj"));
        rightPanel.add(msgScroll);
        rightPanel.add(Box.createVerticalStrut(15));
        rightPanel.add(sendBtn);

        // ================= ADD CENTER =================
        center.add(scroll);
        center.add(rightPanel);

        // ================= FOOTER =================
        JPanel footer = new JPanel();
        footer.setBackground(new Color(230,230,230));
        footer.setPreferredSize(new Dimension(100,40));

        JLabel footerText =
                new JLabel("Akıllı Lojistik Yönetim Sistemi © 2026");

        footerText.setForeground(Color.DARK_GRAY);

        footer.add(footerText);

        // ================= ADD MAIN =================
        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(center, BorderLayout.CENTER);
        mainPanel.add(footer, BorderLayout.SOUTH);

        return mainPanel;
    }
}