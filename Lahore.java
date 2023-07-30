import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Lahore extends JFrame {
    private  JLabel locate,label1;
    private JLabel des,place,budget,trans,rev;
    public Lahore() {
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
        rev.setForeground(Color.WHITE);
        rev.setFont(font);
        Conn c = new Conn();
        try {
            Connection connection = c.getConnection();
            String select = "SELECT * FROM destinations WHERE name = ?";
            PreparedStatement pstmt = connection.prepareStatement(select);
            pstmt.setString(1,"Lahore");
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
            pstmt.setString(1,"Lahore");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                rev.setText("Ratings by Others: "+(int)(Float.parseFloat(rs.getString("avg(overall)"))));
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
