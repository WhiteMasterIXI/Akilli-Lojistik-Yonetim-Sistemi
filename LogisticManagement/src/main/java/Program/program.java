package Program;
import java.awt.BorderLayout;

import UI.Button;
import Utils.Session;
import View.HomeView;
import View.InventoryView;
import View.LoginView;
import View.ProductListView;
import View.RegisterView;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

import Controller.ProductController;
import Controller.RoleController;
import Controller.UserController;
import Controller.WareHouseController;
import Services.FrontControllerPattern.Route;
import Services.FrontControllerPattern.ViewController;
import Services.ViewSwitcherPattern.Navigator;
import SystemTest.SystemTest;
import UserSystem.User;




public class program {
	
	public static void Init() {
		SystemTest.InitSystemData();
	}
	
	public static void main(String[] args) {
		
		
		 CardLayout cardLayout = new CardLayout();
		 JPanel mainPanel = new JPanel(cardLayout);
		
		Init();

        JFrame frame = new JFrame("Akıllı Tedarik Sistemi");
        frame.setSize(1200, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
		Navigator navigator = new Navigator(frame, mainPanel, cardLayout);
        
		ViewController.setNavigator(navigator);
        
        // yetkilere göre sayfalar eklenicek zaten user da yetkiler.
        ViewController.AddPanel(Route.LOGIN);
        ViewController.AddPanel(Route.REGISTER);
	}
}
