package Program;
import java.awt.BorderLayout;

import UI.Button;
import Utils.Session;
import View.InventoryView;
import View.LoginView;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

import Model.User;


public class program {

	public static void main(String[] args) {

        JFrame frame = new JFrame("Akıllı Tedarik Sistemi");
        frame.setSize(1200, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar under_menu = new JMenuBar();
        under_menu.setBackground(new Color(243,243,243));
        
        under_menu.setLayout( new GridLayout(1,5,6,0));
        
        
        Button HomeBtn = new Button("Home",new Color(52, 160, 230),Color.WHITE,new Color(52, 152, 219));
        Button ProductBtn = new Button("Product",new Color(52, 160, 230),Color.WHITE,new Color(52, 152, 219));
        Button UserBtn = new Button("User",new Color(52, 160, 230),Color.WHITE,new Color(52, 152, 219));
        Button OrderBtn = new Button("Order",new Color(52, 160, 230),Color.WHITE,new Color(52, 152, 219));
        Button InventoryBtn = new Button("Inventory",new Color(52, 160, 230),Color.WHITE,new Color(52, 152, 219));
        
        under_menu.setBorder(BorderFactory.createCompoundBorder(
        	    BorderFactory.createMatteBorder(1, 0, 0, 0,new Color(200,200,200)),
        	    BorderFactory.createEmptyBorder(10, 10, 10, 10)
        	));
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);
        
        JPanel HomePanel = new JPanel();
        HomePanel.setBackground(Color.black);
        
        JPanel ProductPanel = new JPanel();
        ProductPanel.setBackground(Color.BLUE);
        
        JPanel UserPanel = new JPanel();
        UserPanel.setBackground(Color.red);
        
        JPanel OrderPanel = new JPanel();
        OrderPanel.setBackground(Color.white);
        
        JPanel InventoryPanel = InventoryView.createInventoryPanel();
        ProductPanel.setBackground(Color.orange);
        
        mainPanel.add(LoginView.createLoginPanel());
        mainPanel.add(InventoryPanel);
        // Login Service lazım buraya.
        
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(mainPanel, BorderLayout.CENTER);
        

        
        // yetkilere göre sayfalar eklenicek zaten user da yetkiler.
        mainPanel.add(HomePanel,"HOME");
        mainPanel.add(ProductPanel,"PRODUCT");
        mainPanel.add(UserPanel,"USER");
        mainPanel.add(OrderPanel,"ORDER");
        mainPanel.add(InventoryPanel,"INVENTORY");
        
        
        
        HomeBtn.addActionListener(e -> {
            cardLayout.show(mainPanel, "HOME");
        });
        ProductBtn.addActionListener(e -> {
            cardLayout.show(mainPanel, "PRODUCT");
        });
        UserBtn.addActionListener(e -> {
            cardLayout.show(mainPanel, "USER");
        });
        OrderBtn.addActionListener(e -> {
            cardLayout.show(mainPanel, "ORDER");
        });
        InventoryBtn.addActionListener(e -> {
            cardLayout.show(mainPanel, "INVENTORY");
        });
        
        
        under_menu.add(HomeBtn.GetBtn());
        under_menu.add(ProductBtn.GetBtn());
        under_menu.add(UserBtn.GetBtn());
        under_menu.add(OrderBtn.GetBtn());
        under_menu.add(InventoryBtn.GetBtn());
        
        frame.add(under_menu, BorderLayout.SOUTH);
        

	}

}
