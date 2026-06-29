package View;

import javax.swing.*;

import BuildingSystem.CompanyDepartments.WareHouse;

import java.awt.*;
import java.util.List;

import Controller.InventoryProductController;
import Controller.LogController;
import Controller.ProductController;
import Controller.WareHouseController;
import Services.FrontControllerPattern.Route;
import Services.FrontControllerPattern.ViewController;
import ShoppingSystem.Products.InventoryProduct;
import ShoppingSystem.Products.Product;
import Utils.Session;
import dao.InventoryProductDao;
import dao.ProductDao;

public class InventoryView {

    public static JPanel createInventoryPanel() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245,245,245));

        // ================= HEADER =================

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(52,152,219));
        headerPanel.setPreferredSize(new Dimension(100,90));
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Depo Yönetimi");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Depodaki ürünleri yönetin");
        subtitle.setForeground(Color.WHITE);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(Box.createVerticalGlue());
        headerPanel.add(title);
        headerPanel.add(Box.createVerticalStrut(8));
        headerPanel.add(subtitle);
        headerPanel.add(Box.createVerticalGlue());

        // ================= CENTER =================

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(245,245,245));

        JPanel productsPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        productsPanel.setBackground(new Color(245,245,245));
        productsPanel.setBorder(
                BorderFactory.createEmptyBorder(20,20,20,20)
        );

        WareHouse depo =
                WareHouseController.getWareHouseByUserId(Session.user.getId());

        List<InventoryProduct> products =
                InventoryProductController.getInvProductsByWareHouse(depo.getId());

        for (InventoryProduct inv : products) {

        	JPanel card = new JPanel(new BorderLayout(0,15));
        	card.setBackground(Color.WHITE);

        	card.setBorder(
        	        BorderFactory.createCompoundBorder(
        	                BorderFactory.createLineBorder(
        	                        new Color(220,220,220)
        	                ),
        	                BorderFactory.createEmptyBorder(15,15,15,15)
        	        )
        	);

        	JPanel infoPanel = new JPanel();
        	infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        	infoPanel.setBackground(Color.WHITE);

        	JLabel name =
        	        new JLabel(inv.getProduct().getName());

        	name.setFont(
        	        new Font("Segoe UI", Font.BOLD, 18)
        	);

        	JLabel stock =
        	        new JLabel("Stok: " + inv.getAmount());

        	JLabel price =
        	        new JLabel(
        	                "Fiyat: "
        	                + inv.getProduct().getPrice()
        	                + " ₺"
        	        );

        	JLabel mass =
        	        new JLabel(
        	                "Ağırlık: "
        	                + inv.getProduct().getMass()
        	        );

        	stock.setForeground(Color.DARK_GRAY);
        	price.setForeground(Color.DARK_GRAY);
        	mass.setForeground(Color.DARK_GRAY);

        	infoPanel.add(name);
        	infoPanel.add(Box.createVerticalStrut(12));
        	infoPanel.add(stock);
        	infoPanel.add(Box.createVerticalStrut(5));
        	infoPanel.add(price);
        	infoPanel.add(Box.createVerticalStrut(5));
        	infoPanel.add(mass);

        	JPanel buttonPanel =
        	        new JPanel(new GridLayout(1,2,10,0));

        	buttonPanel.setBackground(Color.WHITE);

        	JButton editBtn = createButton("Düzenle");
        	JButton deleteBtn = createButton("Sil");
        	
        	
        	// ürün bilgilerini düzenleme alanı
        	
        	editBtn.addActionListener(e -> {

        	    JDialog dialog = new JDialog();
        	    dialog.setTitle("Ürün Düzenle");
        	    dialog.setSize(500, 550);
        	    dialog.setLocationRelativeTo(null);
        	    dialog.setModal(true);
        	    dialog.setLayout(new BorderLayout());
        	    dialog.getContentPane().setBackground(new Color(245,245,245));

        	    // ================= ÜST ALAN =================

        	    JPanel topPanel = new JPanel();
        	    topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        	    topPanel.setBackground(new Color(52,152,219));
        	    topPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        	    JLabel productName =
        	            new JLabel(inv.getProduct().getName());

        	    productName.setForeground(Color.WHITE);
        	    productName.setFont(new Font("Arial", Font.BOLD, 24));

        	    JLabel productInfo =
        	            new JLabel(
        	                    "Stok: "
        	                    + inv.getAmount()
        	                    + "   |   "
        	                    + inv.getProduct().getPrice()
        	                    + " ₺"
        	            );

        	    productInfo.setForeground(Color.WHITE);
        	    productInfo.setFont(new Font("Arial", Font.PLAIN, 16));

        	    topPanel.add(productName);
        	    topPanel.add(Box.createVerticalStrut(10));
        	    topPanel.add(productInfo);

        	    // ================= FORM =================

        	    JPanel formPanel = new JPanel(new GridLayout(4,2,15,15));
        	    formPanel.setBackground(Color.WHITE);

        	    formPanel.setBorder(BorderFactory.createCompoundBorder(
        	            BorderFactory.createLineBorder(new Color(220,220,220)),
        	            BorderFactory.createEmptyBorder(25,25,25,25)
        	    ));

        	    JTextField nameField =
        	            new JTextField(inv.getProduct().getName());

        	    JTextField priceField =
        	            new JTextField(
        	                    String.valueOf(inv.getProduct().getPrice())
        	            );

        	    JTextField massField =
        	            new JTextField(
        	                    String.valueOf(inv.getProduct().getMass())
        	            );

        	    JTextField stockField =
        	            new JTextField(
        	                    String.valueOf(inv.getAmount())
        	            );

        	    Font fieldFont = new Font("Arial", Font.PLAIN, 15);

        	    nameField.setFont(fieldFont);
        	    priceField.setFont(fieldFont);
        	    massField.setFont(fieldFont);
        	    stockField.setFont(fieldFont);

        	    formPanel.add(new JLabel("Ürün Adı"));
        	    formPanel.add(nameField);

        	    formPanel.add(new JLabel("Fiyat"));
        	    formPanel.add(priceField);

        	    formPanel.add(new JLabel("Ağırlık"));
        	    formPanel.add(massField);

        	    formPanel.add(new JLabel("Stok"));
        	    formPanel.add(stockField);

        	    JPanel centerWrapper = new JPanel(new BorderLayout());
        	    centerWrapper.setBackground(new Color(245,245,245));
        	    centerWrapper.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        	    centerWrapper.add(formPanel, BorderLayout.NORTH);

        	    // ================= ALT BUTONLAR =================

        	    JPanel bottomPanel =
        	            new JPanel(new GridLayout(1,2,15,0));

        	    bottomPanel.setBackground(new Color(245,245,245));

        	    bottomPanel.setBorder(
        	            BorderFactory.createEmptyBorder(
        	                    0,20,20,20
        	            )
        	    );

        	    JButton cancelBtn = new JButton("İptal");
        	    JButton saveBtn = new JButton("Kaydet");

        	    cancelBtn.setBackground(new Color(220,70,70));
        	    cancelBtn.setForeground(Color.WHITE);

        	    saveBtn.setBackground(new Color(52,152,219));
        	    saveBtn.setForeground(Color.WHITE);

        	    // ================= ACTIONS =================

        	    cancelBtn.addActionListener(ev -> {
        	        dialog.dispose();
        	    });

        	    saveBtn.addActionListener(ev -> {

        	        try {

        	            String new_Name =  nameField.getText();
        	            Double new_Price = Double.parseDouble(priceField.getText());
        	            float new_Mass =  Float.parseFloat(massField.getText());
        	            int amount =   Integer.parseInt(stockField.getText());
        	            
        	            int old_amount = inv.getAmount();
        	            
        	            Product prod = inv.getProduct();

        	            if(!new_Name.isBlank()) {
        	            	prod.setName(new_Name);
        	            }
        	            
        	            if(new_Price != 0) {
        	            	prod.setPrice(new_Price);
        	            }
        	            
        	            if(new_Mass != 0) {
        	            	prod.setMass(new_Mass);
        	            }
        	            
        	            if(amount != 0) {
        	            	inv.setAmount(amount);
        	            	
        	            	prod.setAmount(prod.getAmount() - old_amount + amount);
        	            }
        	            // dao değil controller 
        	            InventoryProductDao.update(inv);
        	            ProductDao.update(prod);
        	            
        	            JOptionPane.showMessageDialog(
        	                    dialog,
        	                    "Ürün güncellendi."
        	            );
        	            
        	            LogController.LogSave( depo.getLocation() + " deposundan " + prod.getName() + " adlı ürün güncellendi." );
        	            
        	            dialog.dispose();

        	        } catch (Exception ex) {

        	            JOptionPane.showMessageDialog(
        	                    dialog,
        	                    "Bilgiler hatalı girildi."
        	            );
        	        }
        	        
        	        ViewController.RefreshContentPanel(Route.INVENTORY);
        	        
        	    });

        	    bottomPanel.add(cancelBtn);
        	    bottomPanel.add(saveBtn);

        	    // ================= ADD =================

        	    dialog.add(topPanel, BorderLayout.NORTH);
        	    dialog.add(centerWrapper, BorderLayout.CENTER);
        	    dialog.add(bottomPanel, BorderLayout.SOUTH);

        	    dialog.setVisible(true);
        	});
            
            

            deleteBtn.addActionListener(e -> {

                int depoUrunSayisi = inv.getAmount();

                Product product =
                        ProductController.getProduct(
                                inv.getProduct().getId()
                        );

                product.setAmount(
                        product.getAmount() - depoUrunSayisi
                );

                ProductController.UpdateProduct(product);

                if (InventoryProductController
                        .DeleteInvProduct(inv.getId())) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Ürün başarıyla silindi."
                    );
                    
                    LogController.LogSave( depo.getLocation() + " deposundan " + product.getName() + " adlı ürün silindi." );
                }

                ViewController.RefreshContentPanel(Route.INVENTORY);
            });

        	editBtn.setPreferredSize(new Dimension(100,32));
        	deleteBtn.setPreferredSize(new Dimension(100,32));

        	buttonPanel.add(editBtn);
        	buttonPanel.add(deleteBtn);

        	card.add(infoPanel, BorderLayout.CENTER);
        	card.add(buttonPanel, BorderLayout.SOUTH);

        	productsPanel.add(card);
        }

        JScrollPane scrollPane = new JScrollPane(productsPanel);
        scrollPane.setBorder(null);

        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // ================= RIGHT PANEL =================

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(280, 0));
        rightPanel.setBackground(Color.WHITE);

        rightPanel.setLayout(
                new BoxLayout(rightPanel, BoxLayout.Y_AXIS)
        );

        rightPanel.setBorder(
                BorderFactory.createEmptyBorder(20,20,20,20)
        );

        JLabel formTitle = new JLabel("Yeni Ürün");
        formTitle.setFont(
                new Font("Segoe UI", Font.BOLD, 22)
        );

        JTextField nameField = createTextField();
        JTextField priceField = createTextField();
        JTextField stockField = createTextField();
        JTextField massField = createTextField();

        JButton addBtn = createButton("Ekle");

        addBtn.addActionListener(e -> {
        	
        	try {
        		
        	
		        	String product_name = nameField.getText();
		        	double product_price = Double.parseDouble(priceField.getText());
		        	float product_mass =  Float.parseFloat(massField.getText());
		        	int product_amount = Integer.parseInt(stockField.getText());
		        	
		        	Product new_product = new Product();
		        	
		        	new_product.setName(product_name);
		        	new_product.setPrice(product_price);
		        	new_product.setMass(product_mass);
		        	new_product.setAmount(product_amount);
		        	
		        	ProductController.AddProduct(new_product, depo);
		        	
		            JOptionPane.showMessageDialog(
		                    null,
		                    "Ürün başarıyla eklendi"
		            );
		            
		            LogController.LogSave( depo.getLocation() + " deposuna " + product_name + " adlı ürün eklendi." );
		            
        	
			}catch (Exception ex) {
			
			    JOptionPane.showMessageDialog(
			            null,
			            "Bilgiler hatalı girildi."
			    );
			}
        	
        	ViewController.RefreshContentPanel(Route.INVENTORY);

            nameField.setText("");
            priceField.setText("");
            stockField.setText("");
            massField.setText("");
        });

        rightPanel.add(formTitle);
        rightPanel.add(Box.createVerticalStrut(20));

        rightPanel.add(createFieldLabel("Ürün Adı"));
        rightPanel.add(nameField);

        rightPanel.add(Box.createVerticalStrut(12));

        rightPanel.add(createFieldLabel("Fiyat"));
        rightPanel.add(priceField);

        rightPanel.add(Box.createVerticalStrut(12));

        rightPanel.add(createFieldLabel("Stok"));
        rightPanel.add(stockField);

        rightPanel.add(Box.createVerticalStrut(12));

        rightPanel.add(createFieldLabel("Ağırlık"));
        rightPanel.add(massField);

        rightPanel.add(Box.createVerticalStrut(20));

        rightPanel.add(addBtn);

        rightPanel.add(Box.createVerticalGlue());

        JLabel info =
                new JLabel("Inventory Management © 2026");

        info.setForeground(Color.GRAY);
        info.setAlignmentX(Component.CENTER_ALIGNMENT);

        rightPanel.add(info);

        // ================= FOOTER =================

        JPanel footer = new JPanel();

        footer.setBackground(new Color(230,230,230));
        footer.setPreferredSize(new Dimension(100,40));

        JLabel footerText =
                new JLabel("Depo Yönetim Paneli © 2026");

        footerText.setForeground(Color.DARK_GRAY);

        footer.add(footerText);

        // ================= ADD =================

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        mainPanel.add(footer, BorderLayout.SOUTH);

        return mainPanel;
    }

    private static JTextField createTextField() {

        JTextField field = new JTextField();

        field.setMaximumSize(new Dimension(220,35));
        field.setPreferredSize(new Dimension(220,35));

        return field;
    }

    private static JLabel createFieldLabel(String text) {

        JLabel label = new JLabel(text);

        label.setFont(new Font("Segoe UI", Font.BOLD, 14));

        return label;
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