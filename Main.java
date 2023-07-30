import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;


class MainPage extends JFrame implements ActionListener {
    private final JButton nextButton;
    private final JLabel label;
    private final ImageIcon icon;

    public MainPage() {
        setSize(1366, 768);
        setTitle("TourToday");
        setLayout(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        nextButton = new JButton("Next");
        nextButton.setBounds(1165, 560, 80, 30);
        nextButton.setBackground(Color.WHITE);
        nextButton.setForeground(Color.BLUE);
        nextButton.setFont(new Font("Serif", Font.ROMAN_BASELINE, 17));
        nextButton.addActionListener(this);
        icon = new ImageIcon("C:\\Users\\Salik Pasha\\Desktop\\FOP\\FOP\\TourToday.png");
        label = new JLabel(icon);
        label.setBounds(0, 0, 1366, 768);
        add(label);
        add(nextButton);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //setVisible(false);
        new LoginPage().setVisible(true);

    }
}
class Murree extends JFrame {
    private  JLabel locate,label1;
    private JLabel des,place,budget,trans,rev;
    public Murree() {
        Font font  = new Font("Helvetica",Font.CENTER_BASELINE,18);
        setTitle("Islamabad");
        setSize(530,400);
        ImageIcon icon1 = new ImageIcon("C:\\Users\\Salik Pasha\\Desktop\\FOP\\FOP\\background.png");
        label1 = new JLabel(icon1);
        label1.setBounds(0, 0, 300, 300);
        des = new JLabel();
        des.setBounds(10,40,700,40);
        des.setFont(font);
        des.setForeground(Color.WHITE);

        place = new JLabel();
        place.setBounds(10,100,500,40);
        place.setFont(font);
        place.setForeground(Color.WHITE);

        budget = new JLabel();
        budget.setBounds(10,160,500,40);
        budget.setFont(font);
        budget.setForeground(Color.WHITE);

        trans = new JLabel();
        trans.setBounds(10,220,500,40);
        trans.setFont(font);
        trans.setForeground(Color.WHITE);

        rev = new JLabel();
        rev.setBounds(10,280,500,40);
        rev.setFont(font);
        rev.setForeground(Color.WHITE);
        Conn c = new Conn();
        try {
            Connection connection = c.getConnection();
            String select = "SELECT * FROM destinations WHERE name = ?";
            PreparedStatement pstmt = connection.prepareStatement(select);
            pstmt.setString(1,"Murree");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                des.setText("Description: "+rs.getString("description"));
                place.setText("Famous Place: "+rs.getString("place"));
                budget.setText("Minimum Budget:" +rs.getString("budget"));
                trans.setText("Preferred Transport: "+rs.getString("transport"));
            }
        }catch(SQLException ae){ae.printStackTrace();}
        try{
            Connection connection1 = c.getConnection();
            String select = "SELECT avg(overall) FROM Reviews where name = ?";
            PreparedStatement pstmt = connection1.prepareStatement(select);
            pstmt.setString(1,"Murree");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                rev.setText("Rating by others: "+(int)(Float.parseFloat(rs.getString("avg(overall)"))));
            }
        }catch(SQLException se){se.printStackTrace();}
        add(des);
        add(place);
        add(budget);
        add(trans);
        add(rev);
        add(label1);
    }
}
public class Main {
    public static void main(String[] args) {
        MainPage mp = new MainPage();
    }
}
