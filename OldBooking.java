import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class OldBooking extends JFrame implements ActionListener {
    private JLabel booked;
    private JLabel headings,label1,books,username,name;
    private ImageIcon icon1;
    private JButton review;
    public OldBooking() throws SQLException {
        setSize(500,500);
        Font font = new Font("Helvetica",Font.CENTER_BASELINE,20);
        icon1 = new ImageIcon("C:\\Users\\Salik Pasha\\Desktop\\FOP\\FOP\\background.png");
        label1 = new JLabel(icon1);
        label1.setBounds(0, 0, 900, 650);
        booked = new JLabel("Old Bookings:");
        booked.setBounds(10,10,150,40);
        booked.setForeground(Color.white);
        booked.setFont(font);
        username = new JLabel("Username:     "+ LoginPage.perfectName);
        username.setBounds(10,130,400,40);
        username.setFont(font);
        username.setForeground(Color.WHITE);
        name = new JLabel("Name:     "+retrieveName(LoginPage.perfectName));
        name.setBounds(10,190,400,40);
        name.setForeground(Color.WHITE);
        name.setFont(font);
        books = new JLabel(retrieveBookings(LoginPage.perfectName));
        books.setFont(font);
        books.setForeground(Color.WHITE);
        books.setBounds(10,250,400,40);
        review = new JButton("Review");
        review.setBounds(150,400,150,30);
        review.setForeground(Color.BLUE);
        review.setFont(font);
        review.setBackground(Color.white);
        review.addActionListener(this);

        add(booked);
        add(name);
        add(username);
        add(books);
        add(review);
        add(label1);
    }
    public String retrieveBookings(String perfectName) throws SQLException {
        String des = null;
        int days = 0;
        Conn c = new Conn();
        Connection connection = c.getConnection();
        String select = "SELECT * FROM Bookings WHERE username = ?";
        PreparedStatement stmt = connection.prepareStatement(select);
        stmt.setString(1,perfectName);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            des = rs.getString("destination");
            days = rs.getInt("days");
            System.out.println(des+": "+days);
        }
        rs.close();
        stmt.close();
        connection.close();
        return des+":          "+days;
    }
    public String retrieveName(String perfectName) throws SQLException{
        String name = null;
        Conn c = new Conn();
        Connection connection = c.getConnection();
        String select = "SELECT * FROM Bookings WHERE username = ?";
        PreparedStatement pstmt = connection.prepareStatement(select);
        pstmt.setString(1,perfectName);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            name = rs.getString("name");
        }
        rs.close();
        pstmt.close();
        connection.close();
        return name;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        new Review().setVisible(true);
    }
}
