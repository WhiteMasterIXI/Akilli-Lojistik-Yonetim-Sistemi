package View;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.LogController;
import Controller.WareHouseController;
import RoleSystem.Authority;
import RoleSystem.Role;
import UserSystem.User;
import Services.UserService;
import Services.FrontControllerPattern.Route;
import Services.FrontControllerPattern.ViewController;
import Services.ViewSwitcherPattern.Navigator;
import UI.Button;
import Utils.InputController;
import Utils.Session;
import dao.UserDao;
import View.LogView;

public class LoginView {
    public static JPanel createLoginPanel(Navigator nav) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240,240,240));
        panel.setLayout(new GridBagLayout()); // ortalamak için
        
        
        JPanel LoginPanel = new JPanel();
        LoginPanel.setBackground(new Color(250,250,250,200));
        LoginPanel.setLayout(new GridBagLayout());
        LoginPanel.setBorder(
        		BorderFactory.createCompoundBorder(
        		BorderFactory.createMatteBorder(1,1,1,1,new Color(230,230,230)),
        	    BorderFactory.createEmptyBorder(20, 20, 20, 20))
        	);
        
        panel.add(LoginPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Başlık
        JLabel title = new JLabel("Uygulamaya Hosgeldiniz");
        title.setForeground(new Color(110, 119, 129));
        title.setFont(new Font("Arial", Font.BOLD, 22));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        LoginPanel.add(title, gbc);

        // Username label
        JLabel userLabel = new JLabel("Email:");
        userLabel.setForeground(new Color(110, 119, 129));

        gbc.gridy++;
        gbc.gridwidth = 1;
        LoginPanel.add(userLabel, gbc);

        // Username field
        JTextField usernameField = new JTextField(15);

        gbc.gridx = 1;
        LoginPanel.add(usernameField, gbc);

        // Password label
        JLabel passLabel = new JLabel("Sifre:");
        passLabel.setForeground(new Color(110, 119, 129));

        gbc.gridx = 0;
        gbc.gridy++;
        LoginPanel.add(passLabel, gbc);

        // Password field
        JPasswordField passwordField = new JPasswordField(15);

        gbc.gridx = 1;
        LoginPanel.add(passwordField, gbc);

        // Login button
        JButton loginBtn = new JButton("Giris yap");
        loginBtn.setBackground(new Color(75, 159, 234));
        loginBtn.setForeground(Color.white);
        loginBtn.setFocusPainted(false);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        LoginPanel.add(loginBtn, gbc);
        
        JButton goRegisterBtn = new JButton("Hesabınız mı yok, kayıt olunuz.");
        goRegisterBtn.setBackground(new Color(75, 159, 234));
        goRegisterBtn.setForeground(Color.white);
        goRegisterBtn.setFocusPainted(false);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        LoginPanel.add(goRegisterBtn, gbc);

        // Action
        goRegisterBtn.addActionListener(e -> {
        	nav.ShowPanel("REGISTER");
        });
        
        // Panellere erişim butonları oluşturuluyor 
        
        Button HomeBtn = new Button("Ana Sayfa",new Color(52, 160, 230),Color.WHITE,new Color(52, 152, 219));
        Button ProductBtn = new Button("Ürünler",new Color(52, 160, 230),Color.WHITE,new Color(52, 152, 219));
        Button UserBtn = new Button("Profilim",new Color(52, 160, 230),Color.WHITE,new Color(52, 152, 219));
        Button CusOrderBtn = new Button("Siparişlerim",new Color(52, 160, 230),Color.WHITE,new Color(52, 152, 219));
        Button InventoryBtn = new Button("Depo",new Color(52, 160, 230),Color.WHITE,new Color(52, 152, 219));
        Button ExitBtn = new Button("Çıkış",new Color(250, 70, 130),Color.WHITE,new Color(250, 70, 130));
        Button ListInventoryBtn = new Button("Depolar",new Color(52, 160, 230),Color.WHITE,new Color(52, 152, 219));
        Button NotifyBtn = new Button("Bildirimler",new Color(52, 160, 230),Color.WHITE,new Color(52, 152, 219));
        Button LogViewBtn = new Button("Kayıtlar",new Color(52, 160, 230),Color.WHITE,new Color(52, 152, 219));
        Button SupShippmentBtn = new Button("Siparişler",new Color(52, 160, 230),Color.WHITE,new Color(52, 152, 219));
        Button WareHShippmentBtn = new Button("Siparişler",new Color(52, 160, 230),Color.WHITE,new Color(52, 152, 219));
        Button ListSupplierBtn = new Button("Tedarikçiler",new Color(52, 160, 230),Color.WHITE,new Color(52, 152, 219));
        
        ExitBtn.addActionListener(e -> {
        	Session.user = null;
        	nav.UnderMenuVisibility(false);
          	nav.ClearNavigation();
        	nav.ShowPanel("LOGIN");
        });
        
        HomeBtn.addActionListener(e -> {
        	ViewController.ShowPanel(Route.HOME);
        	nav.ShowPanel("HOME");
        });
        
        ProductBtn.addActionListener(e -> {
        	ViewController.ShowPanel(Route.PRODUCT);
        	nav.ShowPanel("PRODUCT");
        });
        
        UserBtn.addActionListener(e -> {
        	ViewController.ShowPanel(Route.USER);
        	nav.ShowPanel("USER");
        });
        
        CusOrderBtn.addActionListener(e -> {
        	ViewController.ShowPanel(Route.CUSTOMER_ORDER);
        });
        
        InventoryBtn.addActionListener(e -> {
        	ViewController.ShowPanel(Route.INVENTORY);
        });
        
        ListInventoryBtn.addActionListener(e -> {
        	ViewController.ShowPanel(Route.INVENTORYLIST);
        });
        
        NotifyBtn.addActionListener(e -> {
        	ViewController.ShowPanel(Route.NOTIFICATION);
        });
        
        LogViewBtn.addActionListener(e -> {
        	ViewController.ShowPanel(Route.LOGRECORDS);
        });
        SupShippmentBtn.addActionListener(e -> {
        	ViewController.ShowPanel(Route.SHIPMENT_SP);
        });
        
        WareHShippmentBtn.addActionListener(e -> {
        	ViewController.ShowPanel(Route.SHIPMENT_WH);
        });
        
        ListSupplierBtn.addActionListener(e -> {
        	ViewController.ShowPanel(Route.SUPPLIERLIST);
        });
        
        loginBtn.addActionListener(e -> {
        	
            String email = usernameField.getText();
            String pass = new String(passwordField.getPassword());
            
            if(email.isBlank()) {
            	Session.FailMessage = "Lutfen Emailinizi giriniz.";
            }else if(!InputController.isEmail(email)){
            	Session.FailMessage = "Lutfen Emailinizi duzgun bir sekilde giriniz.";
            }
            else if(pass.isBlank()) {
            	Session.FailMessage = "Lutfen Sifrenizi giriniz.";
            } 
            else {
            	Session.user = UserService.LoginUser(email,pass);
            }

            if(Session.user != null)
            	{
            	Role role = Session.user.getRole();
            	
            	Set<Authority> authorities = role.getAuthorities();
            	/*
            	public enum Authority{
            		URUNLERI_GORUNTULE,
            		URUN_YONET,
            		URUN_SATINAL,
            		SEPET_GORUNTULE,
            		BILGILERIMI_GORUNTULE, 
            		DEPO_YONET, 
            		CALISANLARI_YONET, 
            		KULLANICILARI_YONET,
            		TEDARIKCILERI_YONET, 
            		KARGOFIRMASI_YONET,
            		URUN_KABULETME, 
            		MUSTERISIPARI_SGORUNTULE, 
            		KURYESIPARIS_GORUNTULE;
            	}
            	*/
            	ViewController.AddPanel(Route.USER);
                nav.AddToNavButton(UserBtn);
            	
            	//HomeBtn.setVisibility(true);
            	
            	
            	if(authorities.contains(Authority.URUNLERI_GORUNTULE)) { 
            		ViewController.AddPanel(Route.PRODUCT);
            		nav.AddToNavButton(ProductBtn);
            	}
            		
            	ViewController.AddPanel(Route.NOTIFICATION);
            		nav.AddToNavButton(NotifyBtn);

            	if(authorities.contains(Authority.MUSTERISIPARIS_GORUNTULE)) {
            		ViewController.AddPanel(Route.CUSTOMER_ORDER);
            		nav.AddToNavButton(CusOrderBtn);
            	}
            	
            	if(authorities.contains(Authority.KURYESIPARIS_GORUNTULE)) {
            		ViewController.AddPanel(Route.SHIPMENT_SP);
            		nav.AddToNavButton(SupShippmentBtn);
            	}
            	
            	if(authorities.contains(Authority.DEPO_YONET)) {
            		ViewController.AddPanel(Route.INVENTORY);
            		nav.AddToNavButton(InventoryBtn);
            		ViewController.AddPanel(Route.SHIPMENT_WH);
            		nav.AddToNavButton(WareHShippmentBtn);
            	}
            	
            	if(authorities.contains(Authority.DEPOLARI_GORUNTULE)) {
            		ViewController.AddPanel(Route.INVENTORYLIST);
            		nav.AddToNavButton(ListInventoryBtn);
            		// geçici ekledim
            		ViewController.AddPanel(Route.LOGRECORDS);
            		nav.AddToNavButton(LogViewBtn);
            		
            		ViewController.AddPanel(Route.SUPPLIERLIST);
            		nav.AddToNavButton(ListSupplierBtn);
            	}
            	
            	if(authorities.contains(Authority.LOGKAYITLARI_GORUNTULE)) {
            		ViewController.AddPanel(Route.LOGRECORDS);
            		nav.AddToNavButton(LogViewBtn);
            	}
            	
            	
            	 LogController.LogSave( Session.user.getName() + " " + Session.user.getSurname() + " adlı kisi uygulamaya giris yapmistir." );
            	
            	nav.AddToNavButton(ExitBtn);
            	nav.UnderMenuVisibility(true);
            	
            	
            	ViewController.RefreshContentPanel(Route.HOME);
            	}
            
            if(Session.FailMessage != "")
            title.setText(Session.FailMessage);
            if(Session.SuccessMessage != "")
            title.setText(Session.SuccessMessage);
            Session.FailMessage = "";
            Session.SuccessMessage = "";
				
        });
	
        return panel;
    }
}
