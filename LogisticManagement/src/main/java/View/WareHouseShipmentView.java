package View;

import ShoppingSystem.Order;
import ShoppingSystem.orderStatus;

import javax.swing.*;

import Controller.OrderController;
import Services.FrontControllerPattern.Route;
import Services.FrontControllerPattern.ViewController;

import java.awt.*;
import java.util.List;

public class WareHouseShipmentView {

    public static JPanel createShipmentPanel() {
    	
    	List<Order> orders = OrderController.getOrderByStatus(orderStatus.HAZIRLANIYOR);

        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(new Color(245,245,245));

        // ================= HEADER =================
        JPanel header = new JPanel();
        header.setBackground(new Color(52, 152, 219));
        header.setPreferredSize(new Dimension(100, 90));
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        
        JButton orderBtn = createButton("Siparişi Oluştur");
        orderBtn.setMaximumSize(new Dimension(260,40));
        orderBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("Kargo Gönderileri");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel sub = new JLabel("kargo şirketinizin göndermesi gereken siparişler");
        sub.setForeground(Color.WHITE);
        sub.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(Box.createVerticalGlue());
        header.add(title);
        header.add(Box.createVerticalStrut(5));
        header.add(sub);
        header.add(Box.createVerticalGlue());

        // ================= CENTER =================
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(new Color(245,245,245));
        center.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        for (Order o : orders) {

            JPanel card = new JPanel(new BorderLayout());
            card.setBackground(Color.WHITE);
            card.setMaximumSize(new Dimension(900, 160));

            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(220,220,220)),
                    BorderFactory.createEmptyBorder(15,15,15,15)
            ));

            // ================= INFO =================
            JPanel info = new JPanel();
            info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
            info.setBackground(Color.WHITE);

            JLabel orderId = new JLabel("Order #" + o.getId());
            orderId.setFont(new Font("Arial", Font.BOLD, 16));

            JLabel status = new JLabel("Durum: " + o.getStatus());
            JLabel date = new JLabel("Tarih: " + o.getOrderdate());

            JLabel address = new JLabel("Adres: " + o.getDeliveryaddress());
            JLabel note = new JLabel("Not: " + o.getRecievernote());

            info.add(orderId);
            info.add(status);
            info.add(date);
            info.add(address);
            info.add(note);

            // ================= ACTIONS =================
            JPanel actions = new JPanel(new GridLayout(2,1,5,5));
            actions.setBackground(Color.WHITE);

            JButton deliveredBtn = createButton("Kargoya ver");
            JButton failedBtn = createButton("Konum Dışında");

            actions.add(deliveredBtn);
            actions.add(failedBtn);

            // ================= LOGIC =================

            deliveredBtn.addActionListener(e -> {
                o.setStatus(orderStatus.KARGODA);
                JOptionPane.showMessageDialog(null,
                        "Sipariş Kargoya Verildi olarak güncellendi (Order #" + o.getId() + ")");
                OrderController.updateOrder(o);
                ViewController.RefreshContentPanel(Route.SHIPMENT_WH);
            });

            failedBtn.addActionListener(e -> {
                o.setStatus(orderStatus.KONUM_DISI);
                JOptionPane.showMessageDialog(null,
                        "Sipariş Konum Dışı olarak güncellendi (Order #" + o.getId() + ")");
                OrderController.updateOrder(o);
                ViewController.RefreshContentPanel(Route.SHIPMENT_WH);
            });

            card.add(info, BorderLayout.WEST);
            card.add(actions, BorderLayout.EAST);

            center.add(card);
            center.add(Box.createVerticalStrut(12));
        }

        JScrollPane scroll = new JScrollPane(center);
        scroll.setBorder(null);

        main.add(header, BorderLayout.NORTH);
        main.add(scroll, BorderLayout.CENTER);

        return main;
    }

    private static JButton createButton(String text) {

        JButton btn = new JButton(text);

        btn.setBackground(new Color(52,152,219));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 12));

        return btn;
    }
}
