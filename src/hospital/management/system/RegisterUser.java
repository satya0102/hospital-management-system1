package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegisterUser extends JFrame implements ActionListener {

    JTextField usernameField;
    JPasswordField passwordField, confirmPasswordField;
    JButton registerBtn, backBtn;

    RegisterUser() {
        setTitle("New User Registration");
        setSize(400, 300);
        setLocation(500, 250);
        setLayout(null);
        getContentPane().setBackground(new Color(200, 230, 250));

        // Username
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 100, 30);
        userLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(170, 50, 150, 30);
        add(usernameField);

        // Password
        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 100, 100, 30);
        passLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(170, 100, 150, 30);
        add(passwordField);

        // Confirm Password
        JLabel confirmLabel = new JLabel("Confirm:");
        confirmLabel.setBounds(50, 150, 100, 30);
        confirmLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(confirmLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(170, 150, 150, 30);
        add(confirmPasswordField);

        // Register Button
        registerBtn = new JButton("Register");
        registerBtn.setBounds(60, 200, 120, 30);
        registerBtn.setBackground(Color.GREEN.darker());
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFont(new Font("Serif", Font.BOLD, 15));
        registerBtn.addActionListener(this);
        add(registerBtn);

        // Back Button
        backBtn = new JButton("Back");
        backBtn.setBounds(200, 200, 120, 30);
        backBtn.setBackground(Color.RED);
        backBtn.setForeground(Color.WHITE);
        backBtn.setFont(new Font("Serif", Font.BOLD, 15));
        backBtn.addActionListener(this);
        add(backBtn);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerBtn) {
            String user = usernameField.getText();
            String pass = String.valueOf(passwordField.getPassword());
            String confirm = String.valueOf(confirmPasswordField.getPassword());

            if (user.equals("") || pass.equals("") || confirm.equals("")) {
                JOptionPane.showMessageDialog(null, "All fields are required!");
                return;
            }

            if (!pass.equals(confirm)) {
                JOptionPane.showMessageDialog(null, "Passwords do not match!");
                return;
            }

            try {
                conn c = new conn();
                String query = "INSERT INTO login (ID, PW) VALUES ('" + user + "', '" + pass + "')";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "User registered successfully!");
                setVisible(false);
                new Login();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Username already exists or DB error!");
                ex.printStackTrace();
            }
        } else if (e.getSource() == backBtn) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new RegisterUser();
    }
}

