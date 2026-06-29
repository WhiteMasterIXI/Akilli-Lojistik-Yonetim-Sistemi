package View;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.RoleController;
import Controller.UserController;
import RoleSystem.Role;
import Services.UserService;
import Services.ViewSwitcherPattern.Navigator;
import UserSystem.User;
import Utils.InputController;
import Utils.Session;
import dao.RoleDao;

public class RegisterView {

    public static JPanel createRegisterPanel(Navigator nav) {

        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 240));
        panel.setLayout(new GridBagLayout());

        JPanel registerPanel = new JPanel();
        registerPanel.setBackground(new Color(250, 250, 250, 200));
        registerPanel.setLayout(new GridBagLayout());
        registerPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(230, 230, 230)),
                        BorderFactory.createEmptyBorder(20, 20, 20, 20)
                )
        );

        panel.add(registerPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Başlık
        JLabel title = new JLabel("Musteri Kayit");
        title.setForeground(new Color(110, 119, 129));
        title.setFont(new Font("Arial", Font.BOLD, 22));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        registerPanel.add(title, gbc);

        // Name
        JLabel nameLabel = new JLabel("Ad:");
        JTextField nameField = new JTextField(15);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        registerPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        registerPanel.add(nameField, gbc);

        // Surname
        JLabel surnameLabel = new JLabel("Soyad:");
        JTextField surnameField = new JTextField(15);

        gbc.gridy++;
        gbc.gridx = 0;
        registerPanel.add(surnameLabel, gbc);

        gbc.gridx = 1;
        registerPanel.add(surnameField, gbc);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(15);

        gbc.gridy++;
        gbc.gridx = 0;
        registerPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        registerPanel.add(emailField, gbc);

        // Password
        JLabel passLabel = new JLabel("Sifre:");
        JPasswordField passField = new JPasswordField(15);

        gbc.gridy++;
        gbc.gridx = 0;
        registerPanel.add(passLabel, gbc);

        gbc.gridx = 1;
        registerPanel.add(passField, gbc);

        // Telephone
        JLabel phoneLabel = new JLabel("Telefon:");
        JTextField phoneField = new JTextField(15);

        gbc.gridy++;
        gbc.gridx = 0;
        registerPanel.add(phoneLabel, gbc);

        gbc.gridx = 1;
        registerPanel.add(phoneField, gbc);

        // Register button
        JButton registerBtn = new JButton("Kayit Ol");
        registerBtn.setBackground(new Color(75, 159, 234));
        registerBtn.setForeground(Color.white);
        registerBtn.setFocusPainted(false);
        
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        registerPanel.add(registerBtn, gbc);
        
        // Login Ekrani gecis
        JButton goLoginBtn = new JButton("Zaten hesabin var mi? Giris Yap");
        goLoginBtn.setBackground(new Color(220, 220, 220));
        goLoginBtn.setFocusPainted(false);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        registerPanel.add(goLoginBtn, gbc);

        
        goLoginBtn.addActionListener(e -> {
        	nav.ShowPanel("LOGIN");
        });
        

        // ACTION
        registerBtn.addActionListener(e -> {

            String name = nameField.getText();
            String surname = surnameField.getText();
            String email = emailField.getText();
            String pass = new String(passField.getPassword());
            String phone = phoneField.getText();

            if (name.isBlank() || surname.isBlank()) {
                Session.FailMessage = "Ad/Soyad bos olamaz.";
            }
            else if (!InputController.isEmail(email)) {
                Session.FailMessage = "Email hatali.";
            }
            else if (pass.isBlank()) {
                Session.FailMessage = "Sifre bos olamaz.";
            }
            else {

                Role musteriRole = RoleDao.findByName("Musteri"); // DB’den veya cache
                User new_user = new User(name,surname,phone,email,pass,musteriRole);
               
                
                UserController.Register(new_user);

                Session.SuccessMessage = "Kayit basarili!";
            }
            
            
           
            title.setText(Session.FailMessage);
            title.setText(Session.SuccessMessage);
            Session.FailMessage = "";
            Session.SuccessMessage = ""; 
            
            nav.ShowPanel("LOGIN");
        });

        return panel;
    }
}