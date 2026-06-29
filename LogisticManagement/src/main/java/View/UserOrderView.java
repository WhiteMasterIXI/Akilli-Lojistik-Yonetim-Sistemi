package View;

import ShoppingSystem.Order;
import ShoppingSystem.orderStatus;
import ShoppingSystem.Products.OrderedProduct;
import Utils.Session;

import javax.swing.*;

import Controller.OrderController;
import Controller.OrderedProductController;
import Services.FrontControllerPattern.Route;
import Services.FrontControllerPattern.ViewController;

import java.awt.*;
import java.util.List;

public class UserOrderView {

    public static JPanel createOrderPanel() {

    	
    	List<Order> orders = OrderController.getOrderByUserId(Session.user.getId());
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245,245,245));

        // ================= HEADER =================
        JPanel header = new JPanel();
        header.setBackground(new Color(52,152,219));
        header.setPreferredSize(new Dimension(100,90));
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Siparislerim");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel sub = new JLabel("Tum siparis gecmisiniz");
        sub.setForeground(Color.WHITE);
        sub.setFont(new Font("Arial", Font.PLAIN, 14));
        sub.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(Box.createVerticalGlue());
        header.add(title);
        header.add(Box.createVerticalStrut(8));
        header.add(sub);
        header.add(Box.createVerticalGlue());

        // ================= CENTER =================
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(new Color(245,245,245));
        center.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        for (Order order : orders) {

            JPanel card = new JPanel(new BorderLayout());
            card.setBackground(Color.WHITE);
            card.setMaximumSize(new Dimension(900,160));

            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(220,220,220)),
                    BorderFactory.createEmptyBorder(15,15,15,15)
            ));

            // ================= LEFT INFO =================
            JPanel info = new JPanel();
            info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
            info.setBackground(Color.WHITE);

            JLabel orderId = new JLabel("Order #" + order.getId());
            orderId.setFont(new Font("Arial", Font.BOLD, 18));

            JLabel price = new JLabel("Toplam: " + order.getPrice() + " ₺");
            JLabel status = new JLabel("Durum: " + order.getStatus());
            JLabel date = new JLabel("Tarih: " + order.getOrderdate());

            info.add(orderId);
            info.add(Box.createVerticalStrut(5));
            info.add(price);
            info.add(status);
            info.add(date);

            // ================= RIGHT BUTTONS =================
            JPanel actions = new JPanel(new GridLayout(3,1,5,5));
            actions.setBackground(Color.WHITE);

            JButton detailBtn = createButton("Detay");
            JButton cancelBtn = createButton("Iptal");
            JButton trackBtn = createButton("Takip");

            actions.add(detailBtn);
            actions.add(cancelBtn);
            actions.add(trackBtn);

            // ================= ACTIONS =================
            detailBtn.addActionListener(e -> {
            	
                StringBuilder sb = new StringBuilder();
                List<OrderedProduct> siparis_urunleri = OrderedProductController.getOrderedProductsByUserId(order.getId());
                for (OrderedProduct p : siparis_urunleri) {
                    sb.append(p.getProduct().getName())
                            .append(" x ")
                            .append(p.getAmount())
                            .append("\n");
                }

                JOptionPane.showMessageDialog(null,
                        "Siparis Detaylari\n\n" + sb);
            });

            cancelBtn.addActionListener(e -> {
                JOptionPane.showMessageDialog(null,
                        "Siparis iptal sistemi yakinda.");
                if(order.getStatus() == orderStatus.HAZIRLANIYOR) {    	
                	order.setStatus(orderStatus.IPTAL_EDILDI);
                	OrderController.updateOrder(order);
                	ViewController.RefreshContentPanel(Route.CUSTOMER_ORDER);
                }else {
                	JOptionPane.showMessageDialog(null,
                            "Sipariş iptal edilemez.");
                }
                OrderController.updateOrder(order);
            });

            trackBtn.addActionListener(e -> {
                JOptionPane.showMessageDialog(null,
                        "Kargo takibi yakinda.");
            });

            card.add(info, BorderLayout.WEST);
            card.add(actions, BorderLayout.EAST);

            center.add(card);
            center.add(Box.createVerticalStrut(15));
        }

        JScrollPane scroll = new JScrollPane(center);
        scroll.setBorder(null);

        // ================= ADD =================
        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(scroll, BorderLayout.CENTER);

        return mainPanel;
    }

    private static JButton createButton(String text) {

        JButton btn = new JButton(text);
        btn.setBackground(new Color(52,152,219));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);

        return btn;
    }
}
