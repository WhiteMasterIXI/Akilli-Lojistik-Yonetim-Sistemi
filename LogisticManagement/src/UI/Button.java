package UI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class Button {
	private JButton HomeBtn;
	
	public JButton GetBtn() {
		return HomeBtn;
	}
	
	public void addActionListener(java.awt.event.ActionListener e) {
	    HomeBtn.addActionListener(e);
	}
	
	public Button(String Text, Color ButtonCol ,Color FontColor,Color HoverCol) {
		
		HomeBtn = new JButton(Text);
		HomeBtn.setFocusPainted(false);     
	    HomeBtn.setBorderPainted(false);     
	    HomeBtn.setBackground(ButtonCol);
	    HomeBtn.setForeground(FontColor);
	    HomeBtn.setFont(new Font("Arial", Font.BOLD, 14));
	
    
    HomeBtn.setFocusPainted(false);     
    HomeBtn.setBorderPainted(false);     
    HomeBtn.setBackground(new Color(52, 160, 230));
    HomeBtn.setForeground(Color.WHITE);
    HomeBtn.setFont(new Font("Arial", Font.BOLD, 14));
    
    HomeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
        	HomeBtn.setBackground(HoverCol);
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
        	HomeBtn.setBackground(HoverCol);
        }
             
        public void mouseReleased(java.awt.event.MouseEvent e) {
        	HomeBtn.setBackground(ButtonCol);
        }
    });
	
	}
    
}
