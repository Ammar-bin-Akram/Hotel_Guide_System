import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

class Booking extends JFrame implements ActionListener {
    private final JLabel userName;
    private final JLabel cardNo;
    private final JLabel des;
    private JLabel NoofDays,name,hotel;
    private final JLabel label1;
    private JTextField n,un, cn, d, nd;
    private JComboBox hotels;
    private final ImageIcon icon1;
    private JButton book;
    public Booking() {
        super("Booking");
        String[] h = new String[]{"Crown", "Envoy", "Pearl Continental"};
        setSize(500, 500);
        setLocation(400, 90);
        icon1 = new ImageIcon("C:\\Users\\Salik Pasha\\Desktop\\FOP\\FOP\\background.png");
        label1 = new JLabel(icon1);
        label1.setBounds(0, 0, 900, 650);
        name = new JLabel("Name:");
        name.setBounds(100,75,150,30);
        name.setFont(new Font("Helvetica", Font.ITALIC, 20));
        name.setForeground(Color.WHITE);
        userName = new JLabel("UserName:");
        userName.setBounds(100, 125, 150, 30);
        userName.setForeground(Color.WHITE);
        userName.setFont(new Font("Helvetica", Font.ITALIC, 20));
        userName.setToolTipText("Provide your user name");
        cardNo = new JLabel("Card Number:");
        cardNo.setBounds(100, 175, 150, 30);
        cardNo.setForeground(Color.WHITE);
        cardNo.setFont(new Font("Helvetica", Font.ITALIC, 20));
        cardNo.setToolTipText("Provide your debit/credit card number.");
        des = new JLabel("Destination:");
        des.setBounds(100, 225, 150, 30);
        des.setForeground(Color.WHITE);
        des.setFont(new Font("Helvetica", Font.ITALIC, 20));
        des.setToolTipText("Provide the destination you want to book");
        hotel = new JLabel("Hotel:");
        hotel.setBounds(100,275,150,30);
        hotel.setFont(new Font("Helvetica", Font.ITALIC, 20));
        hotel.setForeground(Color.WHITE);
        hotel.setToolTipText("Please select a hotel ");
        NoofDays = new JLabel("Days:");
        NoofDays.setBounds(100, 325, 150, 30);
        NoofDays.setFont(new Font("Helvetica", Font.ITALIC, 20));
        NoofDays.setForeground(Color.WHITE);
        NoofDays.setToolTipText("Provide the no of days you want to stay");

        n = new JTextField("");
        n.setBounds(250,75,150,30);
        n.setForeground(Color.BLUE);
        n.setFont(new Font("Helvetica", Font.ITALIC, 20));
        un = new JTextField("");
        un.setBounds(250, 125, 150, 30);
        un.setForeground(Color.BLUE);
        un.setFont(new Font("Helvetica", Font.ITALIC, 20));
        cn = new JTextField("");
        cn.setFont(new Font("Helvetica", Font.ITALIC, 20));
        cn.setBounds(250, 175, 150, 30);
        cn.setForeground(Color.BLUE);
        d = new JTextField("");
        d.setFont(new Font("Helvetica", Font.ITALIC, 20));
        d.setForeground(Color.BLUE);
        d.setBounds(250, 225, 150, 30);
        nd = new JTextField("");
        nd.setBounds(250, 325, 150, 30);
        nd.setFont(new Font("Helvetica", Font.ITALIC, 20));
        nd.setForeground(Color.BLUE);

        hotels = new JComboBox(h);
        hotels.setBounds(250,275,170,30);
        hotels.setForeground(Color.BLUE);
        hotels.setFont(new Font("Helvetica", Font.ITALIC, 20));
        hotels.addActionListener(this);

        book = new JButton("Book");
        book.setBounds(175, 375, 150, 30);
        book.setForeground(Color.BLUE);
        book.setBackground(Color.WHITE);
        book.addActionListener(this);


        add(book);
        add(name);
        add(hotel);
        add(d);
        add(nd);
        add(cn);
        add(un);
        add(n);
        add(des);
        add(hotels);
        add(NoofDays);
        add(cardNo);
        add(userName);
        add(label1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (d.getText().equals("") || nd.getText().equals("") || cn.getText().equals("") || des.getText().equals("") || n.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill the required fields");
        } else {
            String username = un.getText();
            String name = n.getText();
            String card = cn.getText();
            String days = nd.getText();
            String destination = d.getText();
            String hotel = (String) hotels.getItemAt(hotels.getSelectedIndex());
            String insert = "INSERT INTO Bookings (name,username,destination,hotel,days,cardnumber) VALUES(?,?,?,?,?,?)";
            try {
                Conn c = new Conn();
                Connection connection = c.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(insert);
                pstmt.setString(1, name);
                pstmt.setString(2,username);
                pstmt.setString(3, destination);
                pstmt.setString(4, hotel);
                pstmt.setString(5, days);
                pstmt.setString(6,card);
                pstmt.executeUpdate();
                connection.close();
            } catch (Exception ae) {
                ae.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Your booking for " + d.getText() + " for " + nd.getText() + " days is done");
        }
    }
}