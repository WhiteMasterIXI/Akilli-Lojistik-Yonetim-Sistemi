package View;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import BuildingSystem.Supplier;
import BuildingSystem.CompanyDepartments.WareHouse;
import Controller.InventoryProductController;
import Controller.LogController;
import Controller.RoleController;
import Controller.UserController;
import Controller.WareHouseController;
import Controller.WareHouseSupplierController;
import RoleSystem.Role;
import Services.FrontControllerPattern.Route;
import Services.FrontControllerPattern.ViewController;
import ShoppingSystem.Products.InventoryProduct;
import UserSystem.User;

public class InventoryListView {

    public static JPanel createInventoryListPanel() {

        List<WareHouse> warehouses =
                WareHouseController.getAll();

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(
                new Color(245,245,245)
        );

        // =================================================
        // TOP MODERN HEADER
        // =================================================

        JPanel topBanner =
                new JPanel(new BorderLayout());

        topBanner.setBackground(
        		new Color(52, 152, 219)
        );

        topBanner.setBorder(
                BorderFactory.createEmptyBorder(
                        25,30,25,30
                )
        );

        JPanel titleArea =
                new JPanel();

        titleArea.setOpaque(false);

        titleArea.setLayout(
                new BoxLayout(
                        titleArea,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel("Depo Yönetim Merkezi");

        title.setFont(
                new Font("Segoe UI", Font.BOLD, 30)
        );

        title.setForeground(Color.WHITE);

        JLabel subtitle =
                new JLabel(
                        "Depoları ve yöneticileri görüntüleyin"
                );

        subtitle.setForeground(
                new Color(220,230,255)
        );

        subtitle.setFont(
                new Font("Segoe UI", Font.PLAIN, 15)
        );

        titleArea.add(title);
        titleArea.add(Box.createVerticalStrut(8));
        titleArea.add(subtitle);

        topBanner.add(titleArea, BorderLayout.WEST);

        // =================================================
        // CENTER CONTENT
        // =================================================

        JPanel centerWrapper =
                new JPanel(new BorderLayout());

        centerWrapper.setBackground(
                new Color(245,245,245)
        );

        // =================================================
        // DEPO KARTLARI
        // =================================================

        JPanel contentPanel =
                new JPanel();

        contentPanel.setBackground(
                new Color(245,245,245)
        );

        contentPanel.setLayout(
                new GridLayout(0,3,20,20)
        );

        contentPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,20,20,20
                )
        );

        for(WareHouse depo : warehouses) {

            List<InventoryProduct> urunler =
                    InventoryProductController
                            .getInvProductsByWareHouse(
                                    depo.getId()
                            );

            List<Supplier> tedarikciler =
                    WareHouseSupplierController
                            .GetSuppliersByWarehouse(
                                    depo.getId()
                            );

            JPanel card =
                    new JPanel(new BorderLayout());

            card.setBackground(Color.WHITE);

            card.setPreferredSize(
                    new Dimension(280,230)
            );

            card.setBorder(
                    BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(
                                    new Color(225,225,225)
                            ),
                            BorderFactory.createEmptyBorder(
                                    18,18,18,18
                            )
                    )
            );

            // ================= TOP =================

            JPanel topPanel =
                    new JPanel();

            topPanel.setOpaque(false);

            topPanel.setLayout(
                    new BoxLayout(
                            topPanel,
                            BoxLayout.Y_AXIS
                    )
            );

            JLabel depoName =
                    new JLabel(depo.getLocation());

            depoName.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            22
                    )
            );

            depoName.setForeground(
                    new Color(35,35,35)
            );

            JLabel phone =
                    new JLabel(
                            "Telefon: "
                            + depo.getPhone()
                    );

            phone.setForeground(Color.GRAY);

            JLabel stockWarning =
                    new JLabel(
                            "Minimum Ürün: "
                            + depo.getMinProductSize()
                    );

            stockWarning.setForeground(
                    new Color(230,140,20)
            );

            topPanel.add(depoName);
            topPanel.add(Box.createVerticalStrut(10));
            topPanel.add(phone);
            topPanel.add(Box.createVerticalStrut(5));
            topPanel.add(stockWarning);

            // ================= CENTER =================

            JPanel centerPanel =
                    new JPanel(new GridLayout(1,2,12,0));

            centerPanel.setOpaque(false);

            centerPanel.setBorder(
                    BorderFactory.createEmptyBorder(
                            18,0,18,0
                    )
            );

            JPanel productInfo =
                    createInfoCard(
                            "Ürün",
                            String.valueOf(
                                    urunler != null
                                            ? urunler.size()
                                            : 0
                            )
                    );

            JPanel supplierInfo =
                    createInfoCard(
                            "Tedarikçi",
                            String.valueOf(
                                    tedarikciler != null
                                            ? tedarikciler.size()
                                            : 0
                            )
                    );

            centerPanel.add(productInfo);
            centerPanel.add(supplierInfo);

            // ================= BOTTOM =================

            JPanel buttonPanel =
                    new JPanel(new GridLayout(1,1));

            buttonPanel.setOpaque(false);

            JButton editBtn =
                    new JButton("Depoyu Yönet");

            editBtn.setFocusPainted(false);

            editBtn.setBackground(
            		new Color(52, 152, 219)
            );

            editBtn.setForeground(Color.WHITE);

            // =================================================
            // DIALOG
            // =================================================

            editBtn.addActionListener(e -> {

                JDialog dialog = new JDialog();

                dialog.setTitle("Depo Detay");

                dialog.setSize(750, 500);

                dialog.setLocationRelativeTo(null);

                dialog.setModal(true);

                dialog.setLayout(new BorderLayout());

                // ================= HEADER =================

                JPanel header = new JPanel();

                header.setBackground(
                		new Color(52, 152, 219)
                );

                header.setPreferredSize(
                        new Dimension(100,70)
                );

                header.setLayout(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                20,
                                20
                        )
                );

                JLabel Inventory_title =
                        new JLabel(
                                depo.getLocation()
                        );

                Inventory_title.setForeground(Color.WHITE);

                Inventory_title.setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                24
                        )
                );

                header.add(Inventory_title);

                // ================= CENTER =================

                JPanel center =
                        new JPanel();

                center.setBackground(
                        new Color(245,245,245)
                );

                center.setLayout(
                        new BoxLayout(
                                center,
                                BoxLayout.Y_AXIS
                        )
                );

                center.setBorder(
                        BorderFactory.createEmptyBorder(
                                20,20,20,20
                        )
                );

                // ===== INFO CARD =====

                JPanel infoCard =
                        new JPanel();

                infoCard.setLayout(
                        new BoxLayout(
                                infoCard,
                                BoxLayout.Y_AXIS
                        )
                );

                infoCard.setBackground(Color.WHITE);

                infoCard.setBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(
                                        new Color(220,220,220)
                                ),
                                BorderFactory.createEmptyBorder(
                                        15,15,15,15
                                )
                        )
                );

                JLabel phoneLabel =
                        new JLabel(
                                "Telefon: "
                                + depo.getPhone()
                        );

                JLabel minLabel =
                        new JLabel(
                                "Minimum Ürün: "
                                + depo.getMinProductSize()
                        );

                JLabel managerLabel;

                if(depo.getManager() != null) {

                    managerLabel =
                            new JLabel(
                                    "Yönetici: "
                                    + depo.getManager().getName()
                                    + " "
                                    + depo.getManager().getSurname()
                            );

                }
                else {

                    managerLabel =
                            new JLabel(
                                    "Yönetici atanmadı"
                            );
                }

                phoneLabel.setFont(
                        new Font(
                                "Segoe UI",
                                Font.PLAIN,
                                16
                        )
                );

                minLabel.setFont(
                        new Font(
                                "Segoe UI",
                                Font.PLAIN,
                                16
                        )
                );

                managerLabel.setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                16
                        )
                );

                infoCard.add(phoneLabel);
                infoCard.add(Box.createVerticalStrut(10));
                infoCard.add(minLabel);
                infoCard.add(Box.createVerticalStrut(10));
                infoCard.add(managerLabel);

                // ===== USERS =====

                JLabel assignTitle =
                        new JLabel(
                                "Depo Yöneticisi Ata"
                        );

                assignTitle.setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                20
                        )
                );

                assignTitle.setBorder(
                        BorderFactory.createEmptyBorder(
                                20,0,10,0
                        )
                );

                JPanel usersPanel =
                        new JPanel();

                usersPanel.setBackground(
                        new Color(245,245,245)
                );

                usersPanel.setLayout(
                        new BoxLayout(
                                usersPanel,
                                BoxLayout.X_AXIS
                        )
                );

                Role user_role =
                        RoleController.findRoleByName(
                                "Depo_Yoneticisi"
                        );

                List<User> users =
                        UserController.getUsersByRoleId(
                                user_role.getId()
                        );

                for(User user : users) {

                    JPanel userCard =
                            new JPanel();

                    userCard.setPreferredSize(
                            new Dimension(200,170)
                    );

                    userCard.setMaximumSize(
                            new Dimension(200,170)
                    );

                    userCard.setBackground(Color.WHITE);

                    userCard.setLayout(
                            new BoxLayout(
                                    userCard,
                                    BoxLayout.Y_AXIS
                            )
                    );

                    userCard.setBorder(
                            BorderFactory.createCompoundBorder(
                                    BorderFactory.createLineBorder(
                                            new Color(220,220,220)
                                    ),
                                    BorderFactory.createEmptyBorder(
                                            15,15,15,15
                                    )
                            )
                    );

                    JLabel userName =
                            new JLabel(
                                    user.getName()
                                    + " "
                                    + user.getSurname()
                            );

                    userName.setFont(
                            new Font(
                                    "Segoe UI",
                                    Font.BOLD,
                                    16
                            )
                    );

                    JLabel roleLabel =
                            new JLabel(
                                    user.getRole().getName()
                            );

                    roleLabel.setForeground(Color.GRAY);

                    JButton assignBtn =
                            new JButton("Ata");

                    assignBtn.setBackground(
                    		new Color(52, 152, 219)
                    );

                    assignBtn.setForeground(Color.WHITE);

                    assignBtn.addActionListener(ev -> {

                        depo.setManager(user);

                        boolean updated =
                                WareHouseController
                                        .UpdateWareHouse(depo);

                        if(updated) {

                            JOptionPane.showMessageDialog(
                                    null,
                                    "Yönetici başarıyla atandı."
                            );
                            
                            LogController.LogSave( depo.getLocation() +" deposuna " + user.getName() +" " + user.getSurname() + " kisi atandı" );

                            dialog.dispose();

                        } else {

                            JOptionPane.showMessageDialog(
                                    null,
                                    "Atama başarısız."
                            );
                        }
                        
                        ViewController.RefreshContentPanel(Route.INVENTORYLIST);
                    });

                    userCard.add(userName);
                    userCard.add(Box.createVerticalStrut(10));
                    userCard.add(roleLabel);
                    userCard.add(Box.createVerticalGlue());
                    userCard.add(assignBtn);

                    usersPanel.add(userCard);
                    usersPanel.add(Box.createHorizontalStrut(15));
                }

                JScrollPane userScroll =
                        new JScrollPane(usersPanel);

                userScroll.setHorizontalScrollBarPolicy(
                        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
                );

                userScroll.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_NEVER
                );

                userScroll.setPreferredSize(
                        new Dimension(650,220)
                );

                userScroll.setBorder(null);

                center.add(infoCard);
                center.add(assignTitle);
                center.add(userScroll);

                dialog.add(header, BorderLayout.NORTH);
                dialog.add(center, BorderLayout.CENTER);

                dialog.setVisible(true);
            });

            buttonPanel.add(editBtn);

            // ================= ADD =================

            card.add(topPanel, BorderLayout.NORTH);
            card.add(centerPanel, BorderLayout.CENTER);
            card.add(buttonPanel, BorderLayout.SOUTH);

            contentPanel.add(card);
        }

        JScrollPane scrollPane =
                new JScrollPane(contentPanel);

        scrollPane.setBorder(null);

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        // =================================================
        // RIGHT ADD PANEL
        // =================================================

     // ================= RIGHT PANEL =================

        JPanel rightPanel = new JPanel();

        rightPanel.setPreferredSize(
                new Dimension(320,0)
        );

        rightPanel.setBackground(Color.WHITE);

        rightPanel.setBorder(
        	    BorderFactory.createCompoundBorder(
        	        BorderFactory.createMatteBorder(
        	            0, 1, 0, 0,          // Üst, Sol, Alt, Sağ
        	            new Color(230,230,230)
        	        ),
        	        BorderFactory.createEmptyBorder(
        	            20,20,20,20
        	        )
        	    )
        	);
        
        rightPanel.setLayout(
                new BoxLayout(rightPanel, BoxLayout.Y_AXIS)
        );

        // ================= TITLE =================

        JLabel addTitle =
                new JLabel("Yeni Depo");

        addTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        addTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        // ================= FIELDS =================

        JTextField locationField =
                new JTextField();

        JTextField phoneField =
                new JTextField();

        JTextField minField =
                new JTextField();

        Dimension fieldSize =
                new Dimension(Integer.MAX_VALUE,45);

        locationField.setMaximumSize(fieldSize);
        phoneField.setMaximumSize(fieldSize);
        minField.setMaximumSize(fieldSize);

        // ================= BUTTON =================

        JButton createBtn =
                new JButton("Depo Oluştur");

        createBtn.setBackground(
                new Color(52,152,219)
        );

        createBtn.setForeground(Color.WHITE);

        createBtn.setFocusPainted(false);

        createBtn.setMaximumSize(
                new Dimension(Integer.MAX_VALUE,45)
        );

        createBtn.addActionListener(e -> {

            try {

                WareHouse depo =
                        new WareHouse();

                depo.setLocation(
                        locationField.getText()
                );

                depo.setPhone(
                        phoneField.getText()
                );

                depo.setMinUrunSayisi(
                        Integer.parseInt(
                                minField.getText()
                        )
                );

                boolean created =
                        WareHouseController
                                .CreateWareHouse(depo);

                if(created) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Depo oluşturuldu."
                    );
                    
                    LogController.LogSave( depo.getLocation() +" adresinde yeni bir depo oluşturuldu.");
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Depo oluşturulamadı."
                    );
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "Bilgiler hatalı girildi."
                );
            }
            
            ViewController.RefreshContentPanel(Route.INVENTORYLIST);
        });

        // ================= ADD =================

        rightPanel.add(addTitle);

        rightPanel.add(createBtn);

        rightPanel.add(addTitle);
        rightPanel.add(Box.createVerticalStrut(20));

        rightPanel.add(new JLabel("Depo Konumu"));
        rightPanel.add(Box.createVerticalStrut(6));
        rightPanel.add(locationField);

        rightPanel.add(Box.createVerticalStrut(15));

        rightPanel.add(new JLabel("Telefon"));
        rightPanel.add(Box.createVerticalStrut(6));
        rightPanel.add(phoneField);

        rightPanel.add(Box.createVerticalStrut(15));

        rightPanel.add(new JLabel("Minimum Ürün"));
        rightPanel.add(Box.createVerticalStrut(6));
        rightPanel.add(minField);

        rightPanel.add(Box.createVerticalStrut(25));
        rightPanel.add(createBtn);

        // =================================================
        // MAIN ADD
        // =================================================

        centerWrapper.add(scrollPane, BorderLayout.CENTER);
        centerWrapper.add(rightPanel, BorderLayout.EAST);

        mainPanel.add(topBanner, BorderLayout.NORTH);
        mainPanel.add(centerWrapper, BorderLayout.CENTER);

        return mainPanel;
    }

    // =================================================
    // MINI INFO CARD
    // =================================================

    private static JPanel createInfoCard(
            String title,
            String value
    ) {

        JPanel panel =
                new JPanel(new BorderLayout());

        panel.setBackground(
                new Color(248,248,248)
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(230,230,230)
                        ),
                        BorderFactory.createEmptyBorder(
                                12,12,12,12
                        )
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setForeground(Color.GRAY);

        JLabel valueLabel =
                new JLabel(value);

        valueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(valueLabel, BorderLayout.CENTER);

        return panel;
    }
    
    private static JLabel createFieldLabel(String text) {

        JLabel label = new JLabel(text);

        label.setFont(new Font("Segoe UI", Font.BOLD, 14));

        return label;
    }
}