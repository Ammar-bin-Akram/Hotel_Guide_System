
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class LoginPage extends JFrame implements ActionListener {
    //The varibales with 1 are for sign up button and with 2 are for login button
    private final JButton blogin;
    private final JButton bsign;
    private final JButton bforget;
    private final JLabel label1;
    private final JLabel msgLabel;
    private final JLabel userName;
    private final JLabel password;
    private final JTextField utf;
    private final JTextField p1;
    private final ImageIcon icon1;
    public static String perfectName;
    public LoginPage() {
        setSize(400, 430);
        setLayout(null);
        setTitle("Login Page");
        setDefaultLookAndFeelDecorated(false);
        setLocation(300, 30);
        Font font = new Font("Helvetica", Font.ROMAN_BASELINE, 19);
        //labels of first, last, username, password and caution
        userName = new JLabel("UserName:");
        userName.setBounds(50, 70, 100, 30);
        userName.setFont(font);
        userName.setForeground(Color.white);
        password = new JLabel("Password:");
        password.setBounds(50, 130, 100, 30);
        password.setFont(font);
        password.setForeground(Color.white);
        //All text fields added
        utf = new JTextField("");
        utf.setBounds(150, 70, 150, 30);
        utf.setFont(font);
        utf.setForeground(Color.BLUE);
        p1 = new JTextField("");
        p1.setBounds(150, 130, 150, 30);
        p1.setFont(font);
        p1.setForeground(Color.BLUE);
        msgLabel = new JLabel("Please enter credentials to login.");
        msgLabel.setBounds(30, 200, 350, 30);
        msgLabel.setFont(font);
        msgLabel.setForeground(Color.WHITE);
        //sign up functionality below
        bsign = new JButton("Sign Up");
        bsign.addActionListener(this);
        bsign.setBounds(210, 270, 150, 50);
        bsign.setFont(font);
        bsign.setBackground(Color.WHITE);
        bsign.setForeground(Color.BLUE);
        //login functionality below
        blogin = new JButton("Login");
        blogin.setBounds(30, 270, 150, 50);
        blogin.setFont(font);
        blogin.setBackground(Color.WHITE);
        blogin.setForeground(Color.BLUE);
        blogin.addActionListener(this);
        //forget password button functionality
        bforget = new JButton("Forgot Password");
        bforget.setBounds(100, 330, 180, 50);
        bforget.setFont(font);
        bforget.setBackground(Color.WHITE);
        bforget.setForeground(Color.BLUE);
        bforget.addActionListener(this);
        //image set as background
        icon1 = new ImageIcon("C:\\Users\\Salik Pasha\\Desktop\\FOP\\FOP\\background.png");
        label1 = new JLabel(icon1);
        label1.setBounds(0, 0, 900, 650);
        //components are added to JFrame
        add(blogin);
        add(bsign);
        add(bforget);
        add(userName);
        add(password);
        add(p1);
        add(utf);
        add(msgLabel);
        add(label1);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        perfectName = utf.getText();
        Conn c = new Conn();
        if (e.getSource() == blogin) {
            if (utf.getText().equals("") || p1.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill the required fields to login to your account!");
            } else {
                try {
                    Connection connection = c.getConnection();
                    String select = "SELECT * FROM users WHERE username = ?";
                    PreparedStatement pstmt = connection.prepareStatement(select);
                    pstmt.setString(1, perfectName);
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        if (p1.getText().equals(rs.getString("password"))) {
                            setVisible(false);
                            new Preference().setVisible(true);
                        } else if (!(p1.getText().equals(rs.getString("password"))))
                            JOptionPane.showMessageDialog(null, "Username or password is incorrect!");
                    }
                    connection.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
        else if (e.getSource() == bsign) {
            new SignPage().setVisible(true);
        }else if (e.getSource() == bforget) {
            setVisible(false);
            new ForgetPassword();
        }
    }
    public static void main(String[] args){
        new LoginPage().setVisible(true);
    }
}
