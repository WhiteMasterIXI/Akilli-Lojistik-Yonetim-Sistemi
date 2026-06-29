package View;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import BuildingSystem.Supplier;
import Controller.LogController;
import Controller.RoleController;
import Controller.SupplierController;
import Controller.UserController;
import RoleSystem.Role;
import Services.FrontControllerPattern.Route;
import Services.FrontControllerPattern.ViewController;
import Services.StrategyPattern.Shipments.Shipments;
import UserSystem.User;

public class SupplierListView {

    public static JPanel createSupplierPanel() {

        List<Supplier> suppliers =
                SupplierController.GetAllSuppliers();

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(
                new Color(245,245,245)
        );

        // =================================================
        // HEADER
        // =================================================

        JPanel topBanner =
                new JPanel(new BorderLayout());

        topBanner.setBackground(
                new Color(52,152,219)
        );

        topBanner.setBorder(
                BorderFactory.createEmptyBorder(
                        25,30,25,30
                )
        );

        JLabel title =
                new JLabel("Kargo & Tedarikçi Yönetimi");

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Kargo şirketlerini yönetin ve yeni tedarikçiler ekleyin"
                );

        subtitle.setForeground(
                new Color(220,230,255)
        );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
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

        titleArea.add(title);
        titleArea.add(Box.createVerticalStrut(8));
        titleArea.add(subtitle);

        topBanner.add(titleArea, BorderLayout.WEST);

        // =================================================
        // CENTER
        // =================================================

        JPanel centerWrapper =
                new JPanel(new BorderLayout());

        centerWrapper.setBackground(
                new Color(245,245,245)
        );

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

        for(Supplier supplier : suppliers) {

            JPanel card =
                    new JPanel(new BorderLayout());

            card.setBackground(Color.WHITE);

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

            JLabel supplierName =
                    new JLabel(
                            supplier.getName()
                    );

            supplierName.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            22
                    )
            );

            supplierName.setForeground(
                    new Color(35,35,35)
            );

            JLabel status =
                    new JLabel(
                            supplier.isActive()
                            ? "Durum: Aktif"
                            : "Durum: Pasif"
                    );

            status.setForeground(
                    supplier.isActive()
                    ? new Color(46,204,113)
                    : Color.RED
            );

            topPanel.add(supplierName);
            topPanel.add(Box.createVerticalStrut(10));
            topPanel.add(status);

            // ================= CENTER =================

            JPanel infoPanel =
                    new JPanel(
                            new GridLayout(3,1,0,10)
                    );

            infoPanel.setOpaque(false);

            infoPanel.setBorder(
                    BorderFactory.createEmptyBorder(
                            20,0,20,0
                    )
            );

            infoPanel.add(
                    createInfoCard(
                            "Başlangıç Ücreti",
                            supplier.getBasePrice() + "₺"
                    )
            );

            infoPanel.add(
                    createInfoCard(
                            "KG Ücreti",
                            supplier.getPricePerKg() + "₺"
                    )
            );

            infoPanel.add(
                    createInfoCard(
                            "Mesafe Çarpanı",
                            String.valueOf(
                                    supplier.getDistanceMultiplier()
                            )
                    )
            );

            // ================= BOTTOM =================

            JPanel buttonPanel =
                    new JPanel(
                            new GridLayout(1,2,10,0)
                    );

            buttonPanel.setOpaque(false);

            JButton detailBtn =
                    new JButton("Detay");

            detailBtn.setBackground(
                    new Color(52,152,219)
            );

            detailBtn.setForeground(Color.WHITE);

            JButton deleteBtn =
                    new JButton("Sil");

            deleteBtn.setBackground(
                    new Color(231,76,60)
            );

            deleteBtn.setForeground(Color.WHITE);

            detailBtn.addActionListener(e -> {

                String managerName =
                        supplier.getManager() != null
                        ? supplier.getManager().getName()
                          + " "
                          + supplier.getManager().getSurname()
                        : "Atanmadı";

                JOptionPane.showMessageDialog(
                        null,
                        """
                        Kargo Şirketi: %s
                        
                        Başlangıç Ücreti: %s₺
                        
                        KG Ücreti: %s₺
                        
                        Mesafe Çarpanı: %s
                        
                        Yönetici: %s
                        """.formatted(
                                supplier.getName(),
                                supplier.getBasePrice(),
                                supplier.getPricePerKg(),
                                supplier.getDistanceMultiplier(),
                                managerName
                        )
                );
            });

            deleteBtn.addActionListener(e -> {

                int confirm =
                        JOptionPane.showConfirmDialog(
                                null,
                                "Tedarikçi silinsin mi?"
                        );

                if(confirm == 0) {

                    SupplierController.DeleteSupplier(
                            supplier.getId()
                    );

                    LogController.LogSave(
                            supplier.getName()
                            + " isimli tedarikçi silindi."
                    );

                    ViewController.RefreshContentPanel(
                            Route.SUPPLIERLIST
                    );
                }
            });

            buttonPanel.add(detailBtn);
            buttonPanel.add(deleteBtn);

            // ================= ADD =================

            card.add(topPanel, BorderLayout.NORTH);
            card.add(infoPanel, BorderLayout.CENTER);
            card.add(buttonPanel, BorderLayout.SOUTH);

            contentPanel.add(card);
        }

        JScrollPane scrollPane =
                new JScrollPane(contentPanel);

        scrollPane.setBorder(null);

        // =================================================
        // RIGHT PANEL
        // =================================================

        JPanel rightPanel =
                new JPanel();

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
                new BoxLayout(
                        rightPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel addTitle =
                new JLabel("Yeni Tedarikçi");

        addTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );
        
        JLabel shipmentLabel =
                new JLabel("Kargo Tipi");

        Shipments[] shipmentTypes =
                Shipments.values();

        JComboBox<Shipments> shipmentBox =
                new JComboBox<>(shipmentTypes);

        shipmentBox.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 40)
        );
        

        JTextField nameField =
                createField();

        JTextField baseField =
                createField();

        JTextField kgField =
                createField();

        JTextField distanceField =
                createField();

        JCheckBox activeBox =
                new JCheckBox("Aktif");

        activeBox.setBackground(Color.WHITE);

        // ================= USER SELECT =================

        JLabel managerLabel =
                new JLabel("Kargo Yöneticisi");
        
        Role user_role = RoleController.findRoleByName("Kargo_Yetkilisi");

        List<User> users =
                UserController.getUsersByRoleId(user_role.getId());

        JComboBox<User> managerBox =
                new JComboBox<>();

        for(User u : users) {
            managerBox.addItem(u);
        }

        managerBox.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        40
                )
        );

        JButton createBtn =
                new JButton("Tedarikçi Oluştur");

        createBtn.setBackground(
                new Color(52,152,219)
        );

        createBtn.setForeground(Color.WHITE);

        createBtn.setFocusPainted(false);

        createBtn.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45
                )
        );

        createBtn.addActionListener(e -> {

            try {

                Supplier supplier =
                        new Supplier();

                supplier.setName(
                        nameField.getText()
                );

                supplier.setBasePrice(
                        Double.parseDouble(
                                baseField.getText()
                        )
                );

                supplier.setPricePerKg(
                        Double.parseDouble(
                                kgField.getText()
                        )
                );

                supplier.setDistanceMultiplier(
                        Double.parseDouble(
                                distanceField.getText()
                        )
                );
                
                supplier.setShipment(
                        (Shipments) shipmentBox.getSelectedItem()
                );

                supplier.setActive(
                        activeBox.isSelected()
                );

                User selectedUser =
                        (User) managerBox.getSelectedItem();

                supplier.setManager(selectedUser);

                boolean created =
                        SupplierController
                                .CreateSupplier(supplier);

                if(created) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Tedarikçi oluşturuldu."
                    );

                    LogController.LogSave(
                            supplier.getName()
                            + " isimli tedarikçi oluşturuldu."
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            null,
                            "Tedarikçi oluşturulamadı."
                    );
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "Bilgiler hatalı."
                );
            }

            ViewController.RefreshContentPanel(
                    Route.SUPPLIERLIST
            );
        });

        // ================= ADD =================

        rightPanel.add(addTitle);

        rightPanel.add(Box.createVerticalStrut(20));

        rightPanel.add(createFieldLabel("Şirket Adı"));
        rightPanel.add(Box.createVerticalStrut(6));
        rightPanel.add(nameField);
        
        rightPanel.add(shipmentLabel);
        rightPanel.add(shipmentBox);
        rightPanel.add(Box.createVerticalStrut(10));


        rightPanel.add(createFieldLabel("Başlangıç Ücreti"));
        rightPanel.add(Box.createVerticalStrut(6));
        rightPanel.add(baseField);

        rightPanel.add(Box.createVerticalStrut(15));

        rightPanel.add(createFieldLabel("KG Başına Ücret"));
        rightPanel.add(Box.createVerticalStrut(6));
        rightPanel.add(kgField);

        rightPanel.add(Box.createVerticalStrut(15));

        rightPanel.add(createFieldLabel("Mesafe Çarpanı"));
        rightPanel.add(Box.createVerticalStrut(6));
        rightPanel.add(distanceField);

        rightPanel.add(Box.createVerticalStrut(15));

        rightPanel.add(managerLabel);
        rightPanel.add(Box.createVerticalStrut(6));
        rightPanel.add(managerBox);

        rightPanel.add(Box.createVerticalStrut(15));

        rightPanel.add(activeBox);

        rightPanel.add(Box.createVerticalStrut(25));

        rightPanel.add(createBtn);

        rightPanel.add(Box.createVerticalGlue());

        // =================================================
        // ADD
        // =================================================

        centerWrapper.add(
                scrollPane,
                BorderLayout.CENTER
        );

        centerWrapper.add(
                rightPanel,
                BorderLayout.EAST
        );

        mainPanel.add(
                topBanner,
                BorderLayout.NORTH
        );

        mainPanel.add(
                centerWrapper,
                BorderLayout.CENTER
        );

        return mainPanel;
    }

    // =================================================
    // INFO CARD
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
                                10,10,10,10
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
                        18
                )
        );

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(valueLabel, BorderLayout.CENTER);

        return panel;
    }

    private static JTextField createField() {

        JTextField field =
                new JTextField();

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45
                )
        );

        return field;
    }

    private static JLabel createFieldLabel(
            String text
    ) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        return label;
    }
}