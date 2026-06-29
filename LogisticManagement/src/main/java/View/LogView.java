package View;

import NotifySystem.LogRecord;
import Controller.LogController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LogView {

    public static JPanel createLogPanel() {

        List<LogRecord> logs =
                LogController.getAllLogs();

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(
                new Color(245,245,245)
        );

        // =================================================
        // HEADER
        // =================================================

        JPanel headerPanel =
                new JPanel();

        headerPanel.setBackground(
                new Color(52,152,219)
        );

        headerPanel.setPreferredSize(
                new Dimension(100,110)
        );

        headerPanel.setLayout(
                new BoxLayout(
                        headerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel("Sistem Log Kayıtları");

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle =
                new JLabel(
                        "Kullanıcı hareketleri ve sistem işlemleri"
                );

        subtitle.setForeground(
                new Color(230,230,230)
        );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(Box.createVerticalGlue());
        headerPanel.add(title);
        headerPanel.add(Box.createVerticalStrut(8));
        headerPanel.add(subtitle);
        headerPanel.add(Box.createVerticalGlue());

        // =================================================
        // CENTER PANEL
        // =================================================

        JPanel centerWrapper =
                new JPanel(new BorderLayout());

        centerWrapper.setBackground(
                new Color(245,245,245)
        );

        // =================================================
        // LOG LIST
        // =================================================

        JPanel logPanel =
                new JPanel();

        logPanel.setBackground(
                new Color(245,245,245)
        );

        logPanel.setLayout(
                new BoxLayout(
                        logPanel,
                        BoxLayout.Y_AXIS
                )
        );

        logPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,20,20,20
                )
        );

        for(LogRecord log : logs) {

            JPanel card =
                    new JPanel(new BorderLayout());

            card.setBackground(Color.WHITE);

            card.setMaximumSize(
                    new Dimension(
                            Integer.MAX_VALUE,
                            120
                    )
            );

            card.setBorder(
                    BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(
                                    new Color(225,225,225)
                            ),
                            BorderFactory.createEmptyBorder(
                                    15,15,15,15
                            )
                    )
            );

            JPanel top =
                    new JPanel(new BorderLayout());

            top.setOpaque(false);

            String userText =
                    log.getActor() != null
                    ? log.getActor().getName()
                    + " "
                    + log.getActor().getSurname()
                    : "Bilinmeyen Kullanıcı";

            JLabel userLabel =
                    new JLabel(userText);

            userLabel.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            17
                    )
            );

            userLabel.setForeground(
                    new Color(52,152,219)
            );

            JLabel dateLabel =
                    new JLabel(
                            String.valueOf(
                                    log.getTarih()
                            )
                    );

            dateLabel.setForeground(Color.GRAY);

            top.add(userLabel, BorderLayout.WEST);
            top.add(dateLabel, BorderLayout.EAST);

            JTextArea message =
                    new JTextArea(
                            log.getMessage()
                    );

            message.setEditable(false);

            message.setLineWrap(true);

            message.setWrapStyleWord(true);

            message.setBackground(Color.WHITE);

            message.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            14
                    )
            );

            message.setBorder(
                    BorderFactory.createEmptyBorder(
                            10,0,0,0
                    )
            );

            card.add(top, BorderLayout.NORTH);
            card.add(message, BorderLayout.CENTER);

            logPanel.add(card);
            logPanel.add(Box.createVerticalStrut(15));
        }

        JScrollPane scrollPane =
                new JScrollPane(logPanel);

        scrollPane.setBorder(null);

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        // =================================================
        // RIGHT PANEL
        // =================================================

        JPanel rightPanel =
                new JPanel();

        rightPanel.setPreferredSize(
                new Dimension(320,0)
        );

        rightPanel.setBackground(
                new Color(245,245,245)
        );

        rightPanel.setLayout(
                new BoxLayout(
                        rightPanel,
                        BoxLayout.Y_AXIS
                )
        );

        rightPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,10,20,20
                )
        );

        JPanel searchCard =
                new JPanel();

        searchCard.setBackground(Color.WHITE);

        searchCard.setLayout(
                new BoxLayout(
                        searchCard,
                        BoxLayout.Y_AXIS
                )
        );

        searchCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225,225,225)
                        ),
                        BorderFactory.createEmptyBorder(
                                20,20,20,20
                        )
                )
        );

        JLabel searchTitle =
                new JLabel("Kullanıcı Log Ara");

        searchTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        23
                )
        );

        JTextField idField =
                new JTextField();

        idField.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45
                )
        );

        JButton searchBtn =
                new JButton("Logları Getir");

        searchBtn.setBackground(
                new Color(52,152,219)
        );

        searchBtn.setForeground(Color.WHITE);

        searchBtn.setFocusPainted(false);

        JPanel resultPanel =
                new JPanel();

        resultPanel.setBackground(Color.WHITE);

        resultPanel.setLayout(
                new BoxLayout(
                        resultPanel,
                        BoxLayout.Y_AXIS
                )
        );

        searchBtn.addActionListener(e -> {

            resultPanel.removeAll();

            try {

                long user_id =
                        Long.parseLong(
                                idField.getText()
                        );

                List<LogRecord> userLogs =
                        LogController
                                .getUserLogs(user_id);

                if(userLogs == null
                        || userLogs.isEmpty()) {

                    JLabel empty =
                            new JLabel(
                                    "Log bulunamadı."
                            );

                    empty.setForeground(Color.GRAY);

                    resultPanel.add(empty);

                } else {

                    for(LogRecord log : userLogs) {

                        JPanel miniCard =
                                new JPanel(
                                        new BorderLayout()
                                );

                        miniCard.setBackground(
                                new Color(248,248,248)
                        );

                        miniCard.setMaximumSize(
                                new Dimension(
                                        Integer.MAX_VALUE,
                                        90
                                )
                        );

                        miniCard.setBorder(
                                BorderFactory.createCompoundBorder(
                                        BorderFactory.createLineBorder(
                                                new Color(230,230,230)
                                        ),
                                        BorderFactory.createEmptyBorder(
                                                10,10,10,10
                                        )
                                )
                        );

                        JLabel tarih =
                                new JLabel(
                                        String.valueOf(
                                                log.getTarih()
                                        )
                                );

                        tarih.setForeground(Color.GRAY);

                        JTextArea msg =
                                new JTextArea(
                                        log.getMessage()
                                );

                        msg.setEditable(false);

                        msg.setLineWrap(true);

                        msg.setWrapStyleWord(true);

                        msg.setBackground(
                                new Color(248,248,248)
                        );

                        miniCard.add(tarih, BorderLayout.NORTH);
                        miniCard.add(msg, BorderLayout.CENTER);

                        resultPanel.add(miniCard);
                        resultPanel.add(
                                Box.createVerticalStrut(10)
                        );
                    }
                }

                resultPanel.revalidate();
                resultPanel.repaint();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "Geçersiz kullanıcı id"
                );
            }
        });

        searchCard.add(searchTitle);
        searchCard.add(Box.createVerticalStrut(20));

        searchCard.add(
                new JLabel("Kullanıcı ID")
        );

        searchCard.add(Box.createVerticalStrut(6));

        searchCard.add(idField);

        searchCard.add(Box.createVerticalStrut(20));

        searchCard.add(searchBtn);

        searchCard.add(Box.createVerticalStrut(20));

        JScrollPane resultScroll =
                new JScrollPane(resultPanel);

        resultScroll.setPreferredSize(
                new Dimension(250,350)
        );

        resultScroll.setBorder(null);

        searchCard.add(resultScroll);

        rightPanel.add(searchCard);

        // =================================================
        // FOOTER
        // =================================================

        JPanel footer =
                new JPanel();

        footer.setBackground(
                new Color(230,230,230)
        );

        footer.setPreferredSize(
                new Dimension(100,40)
        );

        JLabel footerText =
                new JLabel(
                        "Log Yönetim Sistemi © 2026"
                );

        footerText.setForeground(
                Color.DARK_GRAY
        );

        footer.add(footerText);

        // =================================================
        // ADD
        // =================================================

        centerWrapper.add(scrollPane, BorderLayout.CENTER);
        centerWrapper.add(rightPanel, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centerWrapper, BorderLayout.CENTER);
        mainPanel.add(footer, BorderLayout.SOUTH);

        return mainPanel;
    }
}