import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class SignPage extends JFrame implements ActionListener {
    private final JLabel firstName;
    private final JLabel lastName;
    private final JLabel userName;
    private final JLabel password;
    private final JLabel label1;
    private final JTextField fn;
    private final JTextField ln;
    private final JTextField un;
    private final JTextField p;
    private final JButton bsign;
    private final ImageIcon icon1;

    public SignPage() {
        Font font = new Font("Helvetica", Font.CENTER_BASELINE, 19);
        setSize(400, 490);
        setTitle("SignUp Page");
        setLocation(500, 160);
        firstName = new JLabel("FirstName:");
        firstName.setBounds(40, 100, 120, 30);
        firstName.setFont(font);
        firstName.setForeground(Color.white);
        lastName = new JLabel("LastName:");
        lastName.setBounds(40, 150, 120, 30);
        lastName.setFont(font);
        lastName.setForeground(Color.white);
        userName = new JLabel("UserName:");
        userName.setBounds(40, 200, 120, 30);
        userName.setFont(font);
        userName.setForeground(Color.white);
        password = new JLabel("Password:");
        password.setBounds(40, 250, 120, 30);
        password.setFont(font);
        password.setForeground(Color.white);
        fn = new JTextField("");
        fn.setBounds(190, 100, 120, 30);
        fn.setFont(font);
        fn.setForeground(Color.BLUE);
        ln = new JTextField("");
        ln.setBounds(190, 150, 120, 30);
        ln.setFont(font);
        ln.setForeground(Color.BLUE);
        un = new JTextField("");
        un.setBounds(190, 200, 120, 30);
        un.setFont(font);
        un.setForeground(Color.BLUE);
        p = new JTextField("");
        p.setBounds(190, 250, 120, 30);
        p.setFont(font);
        p.setForeground(Color.BLUE);

        bsign = new JButton("SignUp");
        bsign.setBounds(110, 300, 150, 50);
        bsign.setFont(font);
        bsign.setBackground(Color.WHITE);
        bsign.setForeground(Color.BLUE);
        bsign.addActionListener(this);

        icon1 = new ImageIcon("C:\\Users\\Salik Pasha\\Desktop\\FOP\\FOP\\background.png");
        label1 = new JLabel(icon1);
        label1.setBounds(0, 0, 900, 650);
        add(firstName);
        add(lastName);
        add(userName);
        add(password);
        add(fn);
        add(ln);
        add(un);
        add(p);
        add(bsign);
        add(label1);
    }
    public boolean userExistance(String name){
        Conn c = new Conn();
        boolean b = false;
        String usn = un.getText();
        String select = "SELECT COUNT(*) AS count FROM  users WHERE username = ?";
        try{
            Connection connection = c.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(select);
            pstmt.setString(1,usn);
            ResultSet rs = pstmt.executeQuery();
            int count = rs.getInt("count");
            if(count == 0){
                b = true;
            }
            else {b = false;}
            connection.close();
        }catch(SQLException se){se.printStackTrace();}
        return b;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (un.getText().equals("")|| fn.getText().equals("")|| ln.getText().equals("")|| p.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill up the required fields");
        } else {
            String fsn = fn.getText();
            String lsn = ln.getText();
            String usn = un.getText();
            String pass = p.getText();
            if(!(userExistance(usn))){
                JOptionPane.showMessageDialog(null,"The username already exists!");
            }else{
                String insertUser = "INSERT INTO users (firstname,lastname,username,password) VALUES(?,?,?,?)";
                try{
                    Conn c = new Conn();
                    Connection connection = c.getConnection();
                    PreparedStatement pstmt = connection.prepareStatement(insertUser);
                    pstmt.setString(1,fsn);
                    pstmt.setString(2,lsn);
                    pstmt.setString(3,usn);
                    pstmt.setString(4,pass);
                    pstmt.executeUpdate();
                    connection.close();
                }
                catch(Exception a){a.printStackTrace();}
                JOptionPane.showMessageDialog(null, "You have signed up for TourToday");
                setVisible(false);}
        }
    }
    public static void main(String[] args){
        new SignPage().setVisible(true);
    }
}
