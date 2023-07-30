import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class ForgetPassword extends JFrame implements ActionListener {
        private JLabel label1,username,firstname,lastname,password;
        private ImageIcon icon1;
        private JButton search,back;
        private JTextField un,fn,ln,p;
        public ForgetPassword(){
            setSize(500,500);
            Font font = new Font("Helvetica",Font.ROMAN_BASELINE,19);
            //Labels here
            username = new JLabel("Username:");
            username.setBounds(50,50,150,30);
            username.setForeground(Color.white);
            username.setFont(font);
            firstname = new JLabel("Firstname:");
            firstname.setBounds(50,120,150,30);
            firstname.setForeground(Color.white);
            firstname.setFont(font);
            lastname = new JLabel("Lastname:");
            lastname.setBounds(50,190,150,30);
            lastname.setForeground(Color.white);
            lastname.setFont(font);
            password = new JLabel("Password");
            password.setBounds(50,260,150,30);
            password.setFont(font);
            password.setForeground(Color.white);

            //text fields here
            un = new JTextField("");
            un.setBounds(170,50,150,30);
            un.setFont(font);
            un.setForeground(Color.BLUE);
            fn = new JTextField("");
            fn.setBounds(170,120,150,30);
            fn.setFont(font);
            fn.setForeground(Color.BLUE);
            ln = new JTextField("");
            ln.setBounds(170,190,150,30);
            ln.setFont(font);
            ln.setForeground(Color.BLUE);
            p = new JTextField("");
            p.setBounds(170,260,150,30);
            p.setFont(font);
            p.setForeground(Color.BLUE);



            //Buttons and their functionality
            search = new JButton("Search");
            search.setBounds(340,50,130,30);
            search.setFont(font);
            search.setBackground(Color.white);
            search.setForeground(Color.BLUE);
            search.addActionListener(this);
            back = new JButton("Back");
            back.setBounds(150,310,130,30);
            back.setBackground(Color.white);
            back.setFont(font);
            back.setForeground(Color.BLUE);
            back.addActionListener(this);



            icon1 = new ImageIcon("C:\\Users\\Salik Pasha\\Desktop\\FOP\\FOP\\background.png");
            label1 = new JLabel(icon1);
            label1.setBounds(0, 0, 500, 500);
            add(username);
            add(firstname);
            add(lastname);
            add(password);
            add(un);
            add(fn);
            add(ln);
            add(p);
            add(search);
            add(back);
            add(label1);
            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String usn = un.getText();
            if(e.getSource() == back){
                setVisible(false);
                new LoginPage().setVisible(true);
            }else if(e.getSource() == search) {
                String select = "SELECT * FROM users WHERE username = ?";
                Conn c = new Conn();
                try {
                    Connection connection = c.getConnection();
                    PreparedStatement pstmt =connection.prepareStatement(select);
                    pstmt.setString(1,usn);
                    ResultSet rs = pstmt.executeQuery();
                    while(rs.next()){
                        fn.setText(rs.getString("firstname"));
                        ln.setText(rs.getString("lastname"));
                        p.setText(rs.getString("password"));
                    }
                    rs.close();
                    pstmt.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
    public static void main(String [] args){
        new ForgetPassword().setVisible(true);
    }
}
