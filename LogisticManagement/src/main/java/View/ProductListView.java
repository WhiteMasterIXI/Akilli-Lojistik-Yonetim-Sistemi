package View;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import BuildingSystem.Supplier;
import Controller.CartController;
import Controller.ProductController;
import Controller.ReqProComController;
import Controller.RequestProductController;
import Controller.SupplierController;
import Services.FrontControllerPattern.Route;
import Services.FrontControllerPattern.ViewController;
import Services.StrategyPattern.PaymentStrategy.IPaymentStrategy;
import Services.StrategyPattern.PaymentStrategy.PaymentFactory;
import Services.StrategyPattern.PaymentStrategy.PaymentType;
import Services.StrategyPattern.Shipments.IShippingStrategy;
import Services.StrategyPattern.Shipments.Shipments;
import Services.StrategyPattern.Shipments.ShippingContext;
import Services.StrategyPattern.Shipments.ShippingStrategyFactory;
import Services.ViewSwitcherPattern.Navigator;
import ShoppingSystem.Cart;
import ShoppingSystem.Products.AllowedComponent;
import ShoppingSystem.Products.OrderedProductComponent;
import ShoppingSystem.Products.Product;
import ShoppingSystem.Products.RequestProduct;
import ShoppingSystem.Products.RequestProductComponent;
import Utils.Session;
import dao.ProductDao;

public class ProductListView {
	
	static JPanel ContentPanel;
	static JPanel CartPanel;
	static JScrollPane ProductScroll;
	static Navigator nav;
	
	// ilerde bunu düzelt şimdilik böyle olsun panel tamamen silip tekrar ekleme yapıyor
	
	private static JPanel BuildContentPanel() {
		
		// ================= Card Informations =====================
		
        Cart current_Cart =  CartController.getCartByUserId(Session.user.getId());
        
        if(current_Cart == null) {
        	current_Cart = new Cart();
        	current_Cart.setUser(Session.user);
        	CartController.AddCart(current_Cart);
        	current_Cart = CartController.getCartByUserId(Session.user.getId());
        }
        
        
        // sol ürünler parçası
        List<Product> products = ProductController.getProducts();
        JScrollPane productScroll = BuildProductPanel(products);
        
        // sağ cart parçası
        
		List<RequestProduct> req_products = RequestProductController.getRequestProductsByCartId(current_Cart.getId());
        JPanel cartPanel = BuildCartPanel(req_products); 
        // ================= CENTER LAYOUT =================

        ContentPanel = new JPanel(new BorderLayout());
        ContentPanel.setBackground(new Color(245,245,245));

        // ================= CONTENT ADD =================

        ContentPanel.add(productScroll, BorderLayout.CENTER);
        ContentPanel.add(cartPanel, BorderLayout.EAST);
        return ContentPanel;
	}
	
	private static JPanel BuildCartPanel(List<RequestProduct> request_products) {
        // ================= CART PANEL =================
        
        JPanel cartPanel = new JPanel(new BorderLayout());
        cartPanel.setPreferredSize(new Dimension(320,0));
        cartPanel.setBackground(Color.WHITE);

        cartPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0,1,0,0,new Color(220,220,220)),
                BorderFactory.createEmptyBorder(15,15,15,15)
        ));

        JLabel cartTitle = new JLabel("Sepet");
        cartTitle.setFont(new Font("Arial", Font.BOLD, 24));
        cartTitle.setForeground(new Color(52,152,219));

        // ===== CART ITEMS =====

        JPanel cartItemsPanel = new JPanel();
        cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));
        cartItemsPanel.setBackground(Color.WHITE);

        // ÖRNEK ÜRÜN
        										
        
        
        double Toplam_Fiyat = 0;
        for(RequestProduct req_pro : request_products) {

            JPanel itemCard = new JPanel(new BorderLayout());
            itemCard.setMaximumSize(new Dimension(260,120));
            itemCard.setBackground(new Color(248,248,248));

            itemCard.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(230,230,230)),
                    BorderFactory.createEmptyBorder(10,10,10,10)
            ));

            JPanel itemInfo = new JPanel();
            itemInfo.setBackground(new Color(248,248,248));
            itemInfo.setLayout(new BoxLayout(itemInfo, BoxLayout.Y_AXIS));

            JLabel itemName = new JLabel(req_pro.getProduct().getName());
            itemName.setFont(new Font("Arial", Font.BOLD, 16));

            
            // parçalarıda var onları eklemeyi unutma.
            Toplam_Fiyat += req_pro.getAmount() * req_pro.getProduct().getPrice();
            JLabel itemCount = new JLabel( "Miktar: "  + req_pro.getAmount());
            JLabel itemPrice = new JLabel("Toplam: " + req_pro.getProduct().getPrice() + "₺");
            
	        List<RequestProductComponent> parts =
	                ReqProComController.getByRequesProductId(req_pro.getProduct().getId());

	        for (RequestProductComponent parca : parts) {
	        	Product child_product = parca.getChild();	
	        	Toplam_Fiyat += parca.getQuantity() * child_product.getPrice();
	        }

            itemInfo.add(itemName);
            itemInfo.add(Box.createVerticalStrut(5));
            itemInfo.add(itemCount);
            itemInfo.add(itemPrice);

            JPanel itemButtons = new JPanel(new GridLayout(2,1,0,5));
            itemButtons.setBackground(new Color(248,248,248));

            JButton configBtn = createButton("Düzenle");
            JButton removeBtn = createButton("Kaldır");

            
            removeBtn.addActionListener(e -> {
            	Cart current_Cart =  CartController.getCartByUserId(Session.user.getId());
            	current_Cart.setUrunler(RequestProductController.getRequestProductsByCartId(current_Cart.getId()));
            	RequestProductController.DeleteReqProduct(req_pro.getId());
            	ViewController.RefreshContentPanel(Route.PRODUCT);
            });
            
            configBtn.addActionListener(e -> {
            	JOptionPane.showMessageDialog(null, "ürün düzenleme sayfası açılacak");
            });
            
            itemButtons.add(configBtn);
            itemButtons.add(removeBtn);

            itemCard.add(itemInfo, BorderLayout.CENTER);
            itemCard.add(itemButtons, BorderLayout.EAST);

            cartItemsPanel.add(itemCard);
            cartItemsPanel.add(Box.createVerticalStrut(10));
        }

        JScrollPane cartScroll = new JScrollPane(cartItemsPanel);
        cartScroll.setBorder(null);
        
     // ===== CART FOOTER =====

        JPanel cartFooter = new JPanel();
        cartFooter.setLayout(new BoxLayout(cartFooter, BoxLayout.Y_AXIS));
        cartFooter.setAlignmentX(Component.CENTER_ALIGNMENT);
        cartFooter.setBackground(Color.WHITE);

        cartFooter.setBorder(
                BorderFactory.createEmptyBorder(10,5,5,5)
        );

        JLabel totalLabel = new JLabel("Toplam: " + Toplam_Fiyat + "₺");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // ===== FORM PANEL =====

        JPanel formPanel = new JPanel(new GridLayout(1,2,10,0));
        formPanel.setBackground(Color.WHITE);

        // ===== NOT =====

        JPanel notePanel = new JPanel();
        notePanel.setLayout(new BoxLayout(notePanel, BoxLayout.Y_AXIS));
        notePanel.setBackground(Color.WHITE);

        JLabel noteLabel = new JLabel("Sipariş Notu");
        noteLabel.setFont(new Font("Arial", Font.BOLD, 13));

        JTextArea noteArea = new JTextArea(3,12);
        noteArea.setLineWrap(true);
        noteArea.setWrapStyleWord(true);

        JScrollPane noteScroll = new JScrollPane(noteArea);
        noteScroll.setPreferredSize(new Dimension(120,60));

        // ===== ADDRESS =====

        JPanel addressPanel = new JPanel();
        addressPanel.setLayout(new BoxLayout(addressPanel, BoxLayout.Y_AXIS));
        addressPanel.setBackground(Color.WHITE);

        JLabel addressLabel = new JLabel("Teslimat Adresi");
        addressLabel.setFont(new Font("Arial", Font.BOLD, 13));

        JTextArea addressArea = new JTextArea(3,12);
        addressArea.setLineWrap(true);
        addressArea.setWrapStyleWord(true);

        JScrollPane addressScroll = new JScrollPane(addressArea);
        addressScroll.setPreferredSize(new Dimension(120,60));

        // ===== ADD =====

        notePanel.add(noteLabel);
        notePanel.add(Box.createVerticalStrut(5));
        notePanel.add(noteScroll);

        addressPanel.add(addressLabel);
        addressPanel.add(Box.createVerticalStrut(5));
        addressPanel.add(addressScroll);

        formPanel.add(notePanel);
        formPanel.add(addressPanel);

        // ===== BUTTON =====

        JButton orderBtn = createButton("Siparişi Oluştur");
        orderBtn.setMaximumSize(new Dimension(260,40));
        orderBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        
     // ===== PAYMENT & SHIPPING PANEL =====

        JPanel paymentShippingPanel =
                new JPanel(new GridLayout(1,2,10,0));

        paymentShippingPanel.setBackground(Color.WHITE);

        // ===== PAYMENT PANEL =====

        JPanel paymentPanel =
                new JPanel();

        paymentPanel.setBackground(Color.WHITE);

        paymentPanel.setLayout(
                new BoxLayout(paymentPanel, BoxLayout.Y_AXIS)
        );

        JLabel paymentLabel =
                new JLabel("Ödeme Yöntemi");

        paymentLabel.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        String[] paymentMethods = {
                "Kredi Kartı",
                "Hesap Bakiyesi",
                "Kripto Para"
        };

        JComboBox<String> paymentBox =
                new JComboBox<>(paymentMethods);

        paymentBox.setMaximumSize(
                new Dimension(Integer.MAX_VALUE,35)
        );

        paymentPanel.add(paymentLabel);
        paymentPanel.add(Box.createVerticalStrut(5));
        paymentPanel.add(paymentBox);

        // ===== SHIPPING PANEL =====

        JPanel shippingPanel =
                new JPanel();

        shippingPanel.setBackground(Color.WHITE);

        shippingPanel.setLayout(
                new BoxLayout(shippingPanel, BoxLayout.Y_AXIS)
        );

        JLabel cargoLabel =
                new JLabel("Kargo Şirketi");

        cargoLabel.setFont(
                new Font("Arial", Font.BOLD, 13)
        );
        
        List<Supplier> tedarikciler = SupplierController.GetAllSuppliers();
        
        JComboBox<Supplier> cargoBox =
                new JComboBox<>();
        
        for(Supplier supp : tedarikciler) {
            cargoBox.addItem(supp);
        }

        cargoBox.setMaximumSize(
                new Dimension(Integer.MAX_VALUE,35)
        );

        shippingPanel.add(cargoLabel);
        shippingPanel.add(Box.createVerticalStrut(5));
        shippingPanel.add(cargoBox);

        // ===== ADD =====

        paymentShippingPanel.add(paymentPanel);
        paymentShippingPanel.add(shippingPanel);
        // ===== EXTRA OPTIONS =====

        JLabel extraLabel =
                new JLabel("Ek Taşıma Seçenekleri");

        extraLabel.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        JCheckBox fragileCheck =
                new JCheckBox("Kırılabilir Ürün");

        fragileCheck.setBackground(Color.WHITE);

        JCheckBox secureCheck =
                new JCheckBox("Güvenlikli Taşıma");

        secureCheck.setBackground(Color.WHITE);

        // ===== FOOTER ADD =====
        
        totalLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        formPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        paymentShippingPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        orderBtn.setAlignmentX(Component.LEFT_ALIGNMENT);

        cartFooter.add(totalLabel,BorderLayout.LINE_START);
        cartFooter.add(Box.createVerticalStrut(10));

        cartFooter.add(formPanel);
        cartFooter.add(Box.createVerticalStrut(15));
        
        
     // PAYMENT
        cartFooter.add(paymentShippingPanel);
        cartFooter.add(Box.createVerticalStrut(15));

        // EXTRA OPTIONS
        cartFooter.add(extraLabel);
        cartFooter.add(Box.createVerticalStrut(5));
        cartFooter.add(fragileCheck);
        cartFooter.add(secureCheck);

        cartFooter.add(Box.createVerticalStrut(15));

        cartFooter.add(orderBtn);
        
        orderBtn.addActionListener(e -> {
        	

            String orderNote = noteArea.getText();
            String deliveryAddress = addressArea.getText();

            Cart current_Cart = CartController.getCartByUserId(Session.user.getId());
            
            // payment strategy 
            String selectedPayment =
                    (String) paymentBox.getSelectedItem();
            
            PaymentType paymentType = switch (selectedPayment) {
            case "Kredi Kartı" -> PaymentType.CREDIT_CARD;
            case "Hesap Bakiyesi" -> PaymentType.BANK_TRANSFER;
            case "Kripto Para" -> PaymentType.CRYPTO;
            default -> throw new RuntimeException("Invalid payment");
        };
        
        IPaymentStrategy strategy = PaymentFactory.setStrategy(paymentType);
        
        strategy.Pay(312);
            
            // Shipment Strategy
            
            Supplier selectedSupplier =
                    (Supplier) cargoBox.getSelectedItem() ;
        
        IShippingStrategy shippingStrategy =
                ShippingStrategyFactory.get(selectedSupplier.getShipment());
            
            // sabit ücretler 
            boolean fragile = fragileCheck.isSelected();
            boolean secure = secureCheck.isSelected();
            
            
            ShippingContext shippingContext =
                    new ShippingContext();

            shippingContext.setShippingStrategy(shippingStrategy);

            
            shippingContext.setFragile(fragile);
            shippingContext.setSecure(secure);
            shippingContext.setSupplier(selectedSupplier);
            if(CartController.CardToOrder(current_Cart, Session.user,orderNote,deliveryAddress,shippingContext,strategy)) {

                JOptionPane.showMessageDialog(
                        null,
                        "Sipariş oluşturuldu.\n"
                        + "Not: " + orderNote
                        + "\nAdres: " + deliveryAddress
                );

            } else {

                JOptionPane.showMessageDialog(
                        null,
                        "Sipariş oluşturulamadı."
                );
            }
            ViewController.RefreshContentPanel(Route.PRODUCT);
            ViewController.RefreshContentPanel(Route.CUSTOMER_ORDER);
            
        });

        // ===== CART ADD =====

        cartPanel.add(cartTitle, BorderLayout.NORTH);
        cartPanel.add(cartScroll, BorderLayout.CENTER);
        cartPanel.add(cartFooter, BorderLayout.SOUTH);
        
        
        return cartPanel;
	}
	
	private static JScrollPane BuildProductPanel(List<Product> products) {
	       // ================= PRODUCT GRID =================

        JPanel gridPanel = new JPanel(new GridLayout(0,3,20,20));
        gridPanel.setBackground(new Color(245,245,245));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        

        for(Product product : products) {

            JPanel card = new JPanel(new BorderLayout());
            card.setBackground(Color.WHITE);
            card.setPreferredSize(new Dimension(240,280));

            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(225,225,225)),
                    BorderFactory.createEmptyBorder(15,15,15,15)
            ));

            // ===== IMAGE =====

            JPanel imagePanel = new JPanel();
            imagePanel.setPreferredSize(new Dimension(200,90));
            imagePanel.setBackground(new Color(235,240,245));

            JLabel imageText = new JLabel("ÜRÜN");
            imageText.setFont(new Font("Arial", Font.BOLD, 20));
            imageText.setForeground(Color.GRAY);

            imagePanel.add(imageText);

            // ===== INFO =====

            JPanel infoPanel = new JPanel();
            infoPanel.setBackground(Color.WHITE);
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.setBorder(BorderFactory.createEmptyBorder(15,0,15,0));

            JLabel nameLabel = new JLabel(product.getName());
            nameLabel.setFont(new Font("Arial", Font.BOLD, 18));

            JLabel priceLabel = new JLabel(product.getPrice() + " ₺");
            priceLabel.setFont(new Font("Arial", Font.BOLD, 20));
            priceLabel.setForeground(new Color(52,152,219));

            JLabel stockLabel = new JLabel("Stok: " + product.getAmount());
            JLabel massLabel = new JLabel("Ağırlık: " + product.getMass() + " kg");

            infoPanel.add(nameLabel);
            infoPanel.add(Box.createVerticalStrut(10));
            infoPanel.add(priceLabel);
            infoPanel.add(Box.createVerticalStrut(10));
            infoPanel.add(stockLabel);
            infoPanel.add(Box.createVerticalStrut(5));
            infoPanel.add(massLabel);

            // ===== BUTTONS =====

            JPanel buttonPanel = new JPanel(new GridLayout(1,2,10,0));
            buttonPanel.setBackground(Color.WHITE);

            JButton detailBtn = createButton("Detay");
            JButton addBtn = createButton("Sepete");

            detailBtn.addActionListener(e -> {
                JOptionPane.showMessageDialog(null,
                        product.getName() + " detay sayfası yakında.");
            });

            //============================= Add btn ==========================
            addBtn.addActionListener(e -> {
                JDialog dialog = new JDialog();
                dialog.setTitle("Ürün Sepete Ekle");
                dialog.setSize(500, 600);
                dialog.setLocationRelativeTo(null);
                dialog.setModal(true);
                dialog.setLayout(new BorderLayout());
                dialog.getContentPane().setBackground(new Color(245,245,245));

                // ================= HEADER =================

                JPanel header = new JPanel(new BorderLayout());
                header.setBackground(new Color(52,152,219));
                header.setPreferredSize(new Dimension(100,80));

                JLabel inside_title = new JLabel(product.getName());
                inside_title.setForeground(Color.WHITE);
                inside_title.setFont(new Font("Arial", Font.BOLD, 24));
                inside_title.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));

                header.add(inside_title, BorderLayout.WEST);

                // ================= CENTER =================

                JPanel centerPanel = new JPanel();
                centerPanel.setBackground(new Color(245,245,245));
                centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
                centerPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

                // ===== ÜRÜN BİLGİLERİ =====

                JPanel infoCard = new JPanel();
                infoCard.setLayout(new BoxLayout(infoCard, BoxLayout.Y_AXIS));
                infoCard.setBackground(Color.WHITE);

                infoCard.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(220,220,220)),
                        BorderFactory.createEmptyBorder(15,15,15,15)
                ));

                JLabel productName = new JLabel(product.getName());
                productName.setFont(new Font("Arial", Font.BOLD, 22));

                JLabel inside_pricelabel = new JLabel("Fiyat: " + product.getPrice() + " ₺");
                inside_pricelabel.setFont(new Font("Arial", Font.PLAIN, 16));

                JLabel inside_stockLabel = new JLabel("Stok: " + product.getAmount());
                inside_stockLabel.setFont(new Font("Arial", Font.PLAIN, 16));

                JLabel inside_masslabel = new JLabel("Ağırlık: " + product.getMass() + " kg");
                inside_masslabel.setFont(new Font("Arial", Font.PLAIN, 16));

                infoCard.add(productName);
                infoCard.add(Box.createVerticalStrut(10));
                infoCard.add(inside_pricelabel);
                infoCard.add(inside_stockLabel);
                infoCard.add(inside_masslabel);

                // ===== ADET =====

                JPanel amountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                amountPanel.setBackground(new Color(245,245,245));

                JLabel amountLabel = new JLabel("Ürün Adedi:");
                amountLabel.setFont(new Font("Arial", Font.BOLD, 16));

                JSpinner amountSpinner =
                        new JSpinner(new SpinnerNumberModel(1,1,999,1));

                amountSpinner.setPreferredSize(new Dimension(80,30));

                amountPanel.add(amountLabel);
                amountPanel.add(amountSpinner);

                // ===== PARÇA ALANI =====

                JPanel componentPanel = new JPanel();
                componentPanel.setBackground(Color.WHITE);
                componentPanel.setLayout(new BoxLayout(componentPanel, BoxLayout.Y_AXIS));

                componentPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(220,220,220)),
                        BorderFactory.createEmptyBorder(15,15,15,15)
                ));

                JLabel componentTitle = new JLabel("Ürün Parçaları");
                componentTitle.setFont(new Font("Arial", Font.BOLD, 18));

                componentPanel.add(componentTitle);
                componentPanel.add(Box.createVerticalStrut(15));

                // ÖRNEK PARÇA SATIRI
                List<AllowedComponent> urun_parcalari = ProductController.getAllowedComponentByParent(product.getId());
                
                for(AllowedComponent urun_parcasi : urun_parcalari) {

                    JPanel partRow = new JPanel(new BorderLayout());
                    partRow.setBackground(new Color(248,248,248));
                    partRow.setMaximumSize(new Dimension(400,60));

                    partRow.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(new Color(230,230,230)),
                            BorderFactory.createEmptyBorder(10,10,10,10)
                    ));

                    JLabel partName = new JLabel(urun_parcasi.getChild().getName());
                    partName.setFont(new Font("Arial", Font.BOLD, 15));

                    JPanel rightButtons = new JPanel(new FlowLayout());
                    rightButtons.setBackground(new Color(248,248,248));

                    JButton addPartBtn = new JButton("Ekle");
                    JButton removePartBtn = new JButton("Çıkar");

                    addPartBtn.setBackground(new Color(52,152,219));
                    addPartBtn.setForeground(Color.WHITE);

                    removePartBtn.setBackground(new Color(220,70,70));
                    removePartBtn.setForeground(Color.WHITE);

                    rightButtons.add(addPartBtn);
                    rightButtons.add(removePartBtn);

                    partRow.add(partName, BorderLayout.WEST);
                    partRow.add(rightButtons, BorderLayout.EAST);

                    componentPanel.add(partRow);
                    componentPanel.add(Box.createVerticalStrut(10));
                }

                // ===== TOPLAM =====

                JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                totalPanel.setBackground(new Color(245,245,245));

                JLabel totalPrice = new JLabel(
                        "Toplam: " + product.getPrice() + " ₺"
                );

                totalPrice.setFont(new Font("Arial", Font.BOLD, 22));
                totalPrice.setForeground(new Color(52,152,219));

                totalPanel.add(totalPrice);

                // ===== CENTER ADD =====

                centerPanel.add(infoCard);
                centerPanel.add(Box.createVerticalStrut(20));
                centerPanel.add(amountPanel);
                centerPanel.add(Box.createVerticalStrut(20));
                centerPanel.add(componentPanel);
                centerPanel.add(Box.createVerticalStrut(20));
                centerPanel.add(totalPanel);

                JScrollPane scrollPane = new JScrollPane(centerPanel);
                scrollPane.setBorder(null);

                // ================= FOOTER =================

                JPanel footer = new JPanel(new GridLayout(1,2,15,0));
                footer.setBackground(new Color(245,245,245));
                footer.setBorder(BorderFactory.createEmptyBorder(15,20,20,20));

                JButton cancelBtn = new JButton("İptal");
                JButton confirmBtn = new JButton("Sepete Ekle");

                cancelBtn.setBackground(new Color(220,70,70));
                cancelBtn.setForeground(Color.WHITE);

                confirmBtn.setBackground(new Color(52,152,219));
                confirmBtn.setForeground(Color.WHITE);

                cancelBtn.addActionListener(ev -> {
                    dialog.dispose();
                });
                

                confirmBtn.addActionListener(ev -> {
                	
                	Cart request_card = CartController.getCartByUserId(Session.user.getId());
                	request_card.setUrunler(RequestProductController.getRequestProductsByCartId(request_card.getId()));

                    int amount = (Integer) amountSpinner.getValue();

                    if(RequestProductController.AddReqProduct(request_card.addProduct(product, amount))) {
                    JOptionPane.showMessageDialog(null,
                            amount + " adet " + product.getName()
                                    + " sepete eklendi.");
                    }else {
                        JOptionPane.showMessageDialog(null,
                        	product.getName()+ " sepete eklenemedi.");
                    }

                    
                    ViewController.RefreshContentPanel(Route.PRODUCT);    

                    dialog.dispose();
                });

                footer.add(cancelBtn);
                footer.add(confirmBtn);

                // ================= DIALOG ADD =================

                dialog.add(header, BorderLayout.NORTH);
                dialog.add(scrollPane, BorderLayout.CENTER);
                dialog.add(footer, BorderLayout.SOUTH);

                dialog.setVisible(true);
            });

            buttonPanel.add(detailBtn);
            buttonPanel.add(addBtn);

            card.add(imagePanel, BorderLayout.NORTH);
            card.add(infoPanel, BorderLayout.CENTER);
            card.add(buttonPanel, BorderLayout.SOUTH);

            gridPanel.add(card);
        }

        JScrollPane productScroll = new JScrollPane(gridPanel);
        productScroll.setBorder(null);
        productScroll.getVerticalScrollBar().setUnitIncrement(16);
        
        return productScroll;
		
	}
	

    public static JPanel createProductListPanel(Navigator new_nav) {
    	nav = new_nav;
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245,245,245));

        // ================= HEADER =================

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(52,152,219));
        headerPanel.setPreferredSize(new Dimension(100,90));

        JLabel title = new JLabel("Ürün Kataloğu");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(0,25,0,0));

        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(220,35));

        JPanel searchPanel = new JPanel();
        searchPanel.setOpaque(false);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,20));

        searchPanel.add(searchField);
        headerPanel.add(title, BorderLayout.WEST);
        headerPanel.add(searchPanel, BorderLayout.EAST);

        

        // ================= FOOTER =================

        JPanel footer = new JPanel();
        footer.setBackground(new Color(230,230,230));
        footer.setPreferredSize(new Dimension(100,40));

        
        List<Product> products = ProductController.getProducts();
        JLabel footerText = new JLabel(
                "Toplam Ürün Sayısı: " + products.size()
        );

        footer.add(footerText);

        // ================= MAIN ADD =================
        
        BuildContentPanel();

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(ContentPanel, BorderLayout.CENTER);
        mainPanel.add(footer, BorderLayout.SOUTH);
        
        return mainPanel;
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