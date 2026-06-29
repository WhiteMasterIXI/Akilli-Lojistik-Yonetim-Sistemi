package Services.ViewSwitcherPattern;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import UI.Button;

public class Navigator {
	
	JFrame screen;
	JPanel mainpanel;
	CardLayout cardlayout;
	JMenuBar navbar = new JMenuBar();
	
	private void Init() {
		// navbar menü
		
		navbar.setBorder(BorderFactory.createCompoundBorder(
        	    BorderFactory.createMatteBorder(1, 0, 0, 0,new Color(200,200,200)),
        	    BorderFactory.createEmptyBorder(10, 10, 10, 10)
        	));    
		
		navbar.setBackground(new Color(243,243,243));
		navbar.setLayout( new GridLayout(1,5,6,0));
		navbar.setVisible(false);
		
		screen.add(navbar, BorderLayout.SOUTH);
		screen.add(mainpanel, BorderLayout.CENTER);
 
	}
	
	public Navigator(JFrame new_screen ,JPanel new_main,CardLayout new_layout) {
		screen = new_screen;
		mainpanel = new_main;
		cardlayout = new_layout;
		
		Init();
	}
	
	public void AddPanel(JPanel new_panel,String panel_ad) {
		new_panel.setName(panel_ad);
		mainpanel.add(new_panel,panel_ad);
	}
	
	public void AddToNavButton(Button new_btn) {
		navbar.add(new_btn.GetBtn());
	}
	
	public void UnderMenuVisibility(boolean visible) {
		navbar.setVisible(visible);
	}
	
	public void ShowPanel(String panel_ad) {
		cardlayout.show(mainpanel, panel_ad);
	}
	
	public void ClearNavigation() {

	    navbar.removeAll();

	    Component[] components = mainpanel.getComponents();

	    for(Component comp : components) {

	        String name = comp.getName();

	        if(name == null) continue;

	        if(!name.equals("LOGIN") && !name.equals("REGISTER") &&!name.equals("HOME")) {
	            mainpanel.remove(comp);
	        }
	    }

	    mainpanel.revalidate();
	    mainpanel.repaint();
	}
	
	public void RemovePanel(String comp_name) {
	    Component[] components = mainpanel.getComponents();
	    

	    for(Component comp : components) {

	        String name = comp.getName();
	        
	        if(name.equals(comp_name)) {
	            mainpanel.remove(comp);
	    	    mainpanel.revalidate();
	    	    mainpanel.repaint();
	            return;
	        }
	    }
	}
	
}
