package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


public class Login extends JFrame implements ActionListener {

    JTextField textField;
    JPasswordField jPasswordField;
    JButton b1, b2, b3;   // <-- Added new user button

    Login() {
        // Username Label
        JLabel namelabel = new JLabel("Username");
        namelabel.setBounds(40, 20, 100, 30);
        namelabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(namelabel);

        // Password Label
        JLabel password = new JLabel("Password");
        password.setBounds(40, 70, 100, 30);
        password.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(password);

        // Username TextField
        textField = new JTextField();
        textField.setBounds(150, 20, 150, 30);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField.setBackground(new Color(255, 179, 0));
        add(textField);

        // Password Field
        jPasswordField = new JPasswordField();
        jPasswordField.setBounds(150, 70, 150, 30);
        jPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        jPasswordField.setBackground(new Color(255, 179, 0));
        add(jPasswordField);

        // Load and add image
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/login.png"));
        Image i1 = imageIcon.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(i1);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(320, -30, 400, 300);
        add(label);

        // Login Button
        b1 = new JButton("Login");
        b1.setBounds(40, 140, 120, 30);
        b1.setFont(new Font("Serif", Font.BOLD, 15));
        b1.setBackground(Color.RED);
        b1.setForeground(Color.white);
        b1.addActionListener(this);
        add(b1);

        // Cancel Button
        b2 = new JButton("Cancel");
        b2.setBounds(180, 140, 120, 30);
        b2.setFont(new Font("Serif", Font.BOLD, 15));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.white);
        b2.addActionListener(this);
        add(b2);

        // New User Button
        b3 = new JButton("New User");
        b3.setBounds(110, 190, 120, 30);  // Position below login/cancel
        b3.setFont(new Font("Serif", Font.BOLD, 15));
        b3.setBackground(new Color(0, 102, 204));
        b3.setForeground(Color.white);
        b3.addActionListener(this);
        add(b3);

        // Frame settings
        getContentPane().setBackground(new Color(109, 164, 170));
        setTitle("Login");
        setSize(750, 300);
        setLocation(400, 270);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1){
            try{
                conn c = new conn();
                String user = textField.getText();
                String Pass = jPasswordField.getText();

                String q = "select * from login where ID = '"+user+"' and PW = '"+Pass+"'";
                ResultSet resultSet = c.statement.executeQuery(q);

                if(resultSet.next()){
                    new Reception();
                    setVisible(false);

                }else{
                    JOptionPane.showMessageDialog(null,"Invalid Username or Password");
                }

            }catch(Exception E){
                E.printStackTrace();
            }

        } else if(e.getSource() == b2){
            System.exit(0);

        } else if(e.getSource() == b3){
            // Open New User registration form (you need to create this class)
            new RegisterUser();   // <-- create a RegisterUser.java
            setVisible(false);
        }
    }
}
