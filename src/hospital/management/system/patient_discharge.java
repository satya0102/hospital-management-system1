package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class patient_discharge extends JFrame {

    patient_discharge(){

        JPanel panel = new JPanel();
        panel.setBounds(5,5,790,390);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);

        JLabel label = new JLabel("CHECK-OUT");
        label.setBounds(100,20,150,20);
        label.setFont(new Font("Tahoma",Font.BOLD,14));
        label.setForeground(Color.RED);
        panel.add(label);

        JLabel label2 = new JLabel("Customer-id");
        label2.setBounds(30,80,150,20);
        label2.setForeground(Color.BLACK);
        label2.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label2);

        Choice choice = new Choice();
        choice.setBounds(200,80,150,25);
        panel.add(choice);

        try{
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from Patient_info");
            while(resultSet.next()){
                choice.add(resultSet.getString("number"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label3 = new JLabel("Room Number");
        label3.setBounds(30,130,150,20);
        label3.setForeground(Color.BLACK);
        label3.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label3);

        JLabel RNo = new JLabel();
        RNo.setBounds(200,130,150,20);
        RNo.setFont(new Font("Tahoma",Font.BOLD,14));
        RNo.setForeground(Color.BLACK);
        panel.add(RNo);

        JLabel label4 = new JLabel("In-Time");
        label4.setBounds(30,180,250,20);
        label4.setForeground(Color.BLACK);
        label4.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label4);

        JLabel INtime = new JLabel();
        INtime.setBounds(200,180,300,20);
        INtime.setFont(new Font("Tahoma",Font.BOLD,14));
        INtime.setForeground(Color.BLACK);
        panel.add(INtime);

        JLabel label5 = new JLabel("Out-Time");
        label5.setBounds(30,230,150,20);
        label5.setFont(new Font("Tahoma",Font.BOLD,14));
        label5.setForeground(Color.BLACK);
        panel.add(label5);

        Date date = new Date();

        JLabel OUTTIME = new JLabel("" + date);
        OUTTIME.setBounds(200,230,250,20);
        OUTTIME.setFont(new Font("Tahoma",Font.BOLD,14));
        OUTTIME.setForeground(Color.BLACK);
        panel.add(OUTTIME);

        JButton discharge = new JButton("DISCHARGE");
        discharge.setBounds(30,300,120,30);
        discharge.setBackground(Color.BLACK);
        discharge.setForeground(Color.WHITE);
        panel.add(discharge);

        discharge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn c = new conn();
                try{
                    // Use executeUpdate for DELETE and UPDATE
                    c.statement.executeUpdate("delete from Patient_info where number = '"+choice.getSelectedItem()+"'");
                    c.statement.executeUpdate("update room set Availability = 'Available' where room_no = '"+RNo.getText()+"'");

                    JOptionPane.showMessageDialog(null,"Patient discharged successfully!");
                    setVisible(true);
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });


        JButton check = new JButton("CHECK");
        check.setBounds(170,300,120,30);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        panel.add(check);

        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn c = new conn();
                try{
                    ResultSet resultSet = c.statement.executeQuery("select * from Patient_info where number = '"+choice.getSelectedItem()+"'");
                    while(resultSet.next()){
                        RNo.setText(resultSet.getString("Room_Number"));
                        INtime.setText(resultSet.getString("Time"));

                    }
                }catch(Exception E){
                    E.printStackTrace();

                }
            }
        });

        JButton back = new JButton("BACK");
        back.setBounds(300,300,120,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(800,400);
        setLayout(null);
        setLocation(400,250);
        setVisible(true);
    }

    public static void main(String[] args){
        new patient_discharge();
    }
}
