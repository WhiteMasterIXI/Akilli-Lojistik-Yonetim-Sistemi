package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Model.User;
import Services.UserServices;
import Utils.InputController;
import Utils.Session;
import dao.UserDao;

public class LoginView {
    public static JPanel createLoginPanel() {
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
        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(new Color(110, 119, 129));

        gbc.gridy++;
        gbc.gridwidth = 1;
        LoginPanel.add(userLabel, gbc);

        // Username field
        JTextField usernameField = new JTextField(15);

        gbc.gridx = 1;
        LoginPanel.add(usernameField, gbc);

        // Password label
        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(new Color(110, 119, 129));

        gbc.gridx = 0;
        gbc.gridy++;
        LoginPanel.add(passLabel, gbc);

        // Password field
        JPasswordField passwordField = new JPasswordField(15);

        gbc.gridx = 1;
        LoginPanel.add(passwordField, gbc);

        // Login button
        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(75, 159, 234));
        loginBtn.setForeground(Color.white);
        loginBtn.setFocusPainted(false);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        LoginPanel.add(loginBtn, gbc);

        // Action
        /*loginBtn.addActionListener(e -> {
        	String HataMesaji = "";
            String email = usernameField.getText();
            String pass = new String(passwordField.getPassword());
            
            if(email.isBlank()) {
            	HataMesaji = "Lutfen Emailinizi giriniz.";
            }else if(!InputController.isEmail(email)){
            	HataMesaji = "Lutfen Emailinizi duzgun bir sekilde giriniz.";
            }
            else if(pass.isBlank()) {
            	HataMesaji = "Lutfen Sifrenizi giriniz.";
            } 
            else if(UserDao.isExist(email)) {
            	HataMesaji = "Kisi Bulundu";
            }
            
            
            if(HataMesaji != "")
            title.setText(HataMesaji);
            
            try {
				Session.user = UserServices.LoginUser(email,pass);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
            
            
        });
	*/
        return panel;
    }
}
