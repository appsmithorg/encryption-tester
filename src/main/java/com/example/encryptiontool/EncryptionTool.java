package com.example.encryptiontool;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HexFormat;

public class EncryptionTool extends JFrame {

    private JTextField saltField;
    private JTextField passwordField;
    private JTextArea plainTextArea;
    private JTextArea resultArea;

    public EncryptionTool() {
        setTitle("Text Encryption/Decryption Tool");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        // UI Layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        // Salt
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Salt:"), gbc);
        saltField = new JTextField();
        gbc.gridx = 1; gbc.weightx = 1.0;
        panel.add(saltField, gbc);

        // Password
        gbc.gridx = 0; gbc.gridy++;
        gbc.weightx = 0;
        panel.add(new JLabel("Password:"), gbc);
        passwordField = new JTextField();
        gbc.gridx = 1; gbc.weightx = 1.0;
        panel.add(passwordField, gbc);

        // Plain Text Input
        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(new JLabel("Plain / Encrypted Text:"), gbc);
        plainTextArea = new JTextArea(5, 40);
        JScrollPane scroll1 = new JScrollPane(plainTextArea);
        gbc.gridy++;
        panel.add(scroll1, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton encryptButton = new JButton("Encrypt");
        JButton decryptButton = new JButton("Decrypt");
        buttonPanel.add(encryptButton);
        buttonPanel.add(decryptButton);
        gbc.gridy++;
        panel.add(buttonPanel, gbc);

        // Result
        gbc.gridy++;
        panel.add(new JLabel("Result:"), gbc);
        resultArea = new JTextArea(5, 40);
        resultArea.setEditable(false);
        JScrollPane scroll2 = new JScrollPane(resultArea);
        gbc.gridy++;
        panel.add(scroll2, gbc);

        add(panel);

        // Action Listeners
        encryptButton.addActionListener(this::encryptAction);
        decryptButton.addActionListener(this::decryptAction);
    }

    private void encryptAction(ActionEvent e) {
        try {
            String salt = saltField.getText();
            String password = passwordField.getText();
            String plaintext = plainTextArea.getText();
            TextEncryptor encryptor = makeEncryptor(password, salt);
            String encrypted = encryptor.encrypt(plaintext);
            resultArea.setText(encrypted);
        } catch (Exception ex) {
            resultArea.setText("❌ Error: " + ex.getMessage());
        }
    }

    private void decryptAction(ActionEvent e) {
        try {
            String salt = saltField.getText();
            String password = passwordField.getText();
            String encryptedText = plainTextArea.getText();
            TextEncryptor encryptor = makeEncryptor(password, salt);
            String decrypted = encryptor.decrypt(encryptedText);
            resultArea.setText(decrypted);
        } catch (Exception ex) {
            resultArea.setText("❌ Error: " + ex.getMessage());
        }
    }

    private TextEncryptor makeEncryptor(String password, String salt) {
        HexFormat hexFormat = HexFormat.of();
        String saltHex = hexFormat.formatHex(salt.getBytes());
        return Encryptors.delux(password, saltHex);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EncryptionTool().setVisible(true));
    }
}


