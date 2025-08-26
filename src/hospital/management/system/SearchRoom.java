package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class SearchRoom extends JFrame {

    Choice choice;
    JTable table;

    SearchRoom(){


        JPanel panel = new JPanel();
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(770, 600)); // allow scroll if needed


        JLabel For = new JLabel("Search For Room");
        For.setBounds(250,11,300,31);
        For.setForeground(Color.WHITE);
        For.setFont(new Font("Tahoma",Font.BOLD,20));
        panel.add(For);


        JLabel status = new JLabel("Status");
        status.setBounds(70,70,80,20);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(status);


        choice = new Choice();
        choice.setBounds(170,70,120,20);
        choice.add("Available");
        choice.add("Occupied");
        choice.add("Show All");
        panel.add(choice);


        table = new JTable();
        table.setBackground(new Color(90,156,163));
        table.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 185, 740, 200);
        panel.add(scrollPane);


        try {
            conn c = new conn();
            String q = "select * from Room";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch(Exception e){
            e.printStackTrace();
        }


        JLabel RoomNo = new JLabel("Room No");
        RoomNo.setBounds(23, 163, 150, 20);
        RoomNo.setForeground(Color.WHITE);
        RoomNo.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(RoomNo);

        JLabel Availability = new JLabel("Availability");
        Availability.setBounds(210, 163, 150, 20);
        Availability.setForeground(Color.WHITE);
        Availability.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(Availability);

        JLabel Price = new JLabel("Price");
        Price.setBounds(458, 163, 150, 20);
        Price.setForeground(Color.WHITE);
        Price.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(Price);

        JLabel bed = new JLabel("Bed Type");
        bed.setBounds(580, 163, 150, 20);
        bed.setForeground(Color.WHITE);
        bed.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(bed);


        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(200, 420, 120, 30);
        btnSearch.setBackground(Color.BLACK);
        btnSearch.setForeground(Color.WHITE);
        panel.add(btnSearch);

        btnSearch.addActionListener(e -> {
            try {
                conn c = new conn();
                String selected = choice.getSelectedItem();
                String q;

                if (selected.equals("Show All")) {
                    q = "select * from Room";
                } else {
                    q = "select * from Room where Availability = '"+selected+"'";
                }

                ResultSet resultSet = c.statement.executeQuery(q);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));

            } catch(Exception ex) {
                ex.printStackTrace();
            }
        });


        JButton btnBack = new JButton("Back");
        btnBack.setBounds(350, 420, 120, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        panel.add(btnBack);

        btnBack.addActionListener(e -> setVisible(false));


        JScrollPane mainScroll = new JScrollPane(panel);
        mainScroll.setBounds(0, 0, 780, 600);
        add(mainScroll);


        setUndecorated(true);
        setSize(800,650);
        setLayout(null);
        setLocation(450,250);
        setVisible(true);
    }

    public static void main(String[] args){
        new SearchRoom();
    }
}
