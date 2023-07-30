import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

class Review extends JFrame implements ActionListener {
    private JLabel destination,desifood,publictrans,place,overall,label1;
    private JTextField desf,foodf,transf,placef,overf;
    private ImageIcon icon;
    private JButton post;
    public Review(){
        Font font = new Font("Helvetica",Font.CENTER_BASELINE,17);
        setSize(500,500);
        setTitle("Reviews");
        ImageIcon icon1 = new ImageIcon("C:\\Users\\Salik Pasha\\Desktop\\FOP\\FOP\\background.png");
        label1 = new JLabel(icon1);
        label1.setBounds(0, 0, 300, 300);
        destination = new JLabel("Destination:");
        destination.setBounds(100,100,200,30);
        destination.setFont(font);
        destination.setForeground(Color.WHITE);
        desifood = new JLabel("Food:");
        desifood.setBounds(100,170,200,30);
        desifood.setFont(font);
        desifood.setForeground(Color.WHITE);
        publictrans = new JLabel("Public Transport:");
        publictrans.setBounds(100,240,200,30);
        publictrans.setFont(font);
        publictrans.setForeground(Color.WHITE);
        place = new JLabel("Famous Place:");
        place.setBounds(100,310,200,30);
        place.setFont(font);
        place.setForeground(Color.WHITE);
        overall = new JLabel("Overall Rating:");
        overall.setBounds(100,380,200,30);
        overall.setFont(font);
        overall.setForeground(Color.WHITE);
        desf = new JTextField();
        desf.setBounds(320,100,100,30);
        desf.setFont(font);
        desf.setBackground(Color.WHITE);
        desf.setForeground(Color.BLUE);
        foodf = new JTextField();
        foodf.setBounds(320,170,100,30);
        foodf.setFont(font);
        foodf.setBackground(Color.WHITE);
        foodf.setForeground(Color.BLUE);
        transf = new JTextField();
        transf.setBounds(320,240,100,30);
        transf.setFont(font);
        transf.setBackground(Color.WHITE);
        transf.setForeground(Color.BLUE);
        placef = new JTextField();
        placef.setBounds(320,310,100,30);
        placef.setFont(font);
        placef.setBackground(Color.WHITE);
        placef.setForeground(Color.BLUE);
        overf = new JTextField();
        overf.setBounds(320,380,100,30);
        overf.setFont(font);
        overf.setForeground(Color.BLUE);
        overf.setBackground(Color.WHITE);

        post = new JButton("POST");
        post.setBounds(200,420,100,30);
        post.setFont(font);
        post.setForeground(Color.BLUE);
        post.setBackground(Color.WHITE);
        post.addActionListener(this );

        add(post);
        add(destination);
        add(desifood);
        add(publictrans);
        add(place);
        add(overall);
        add(desf);
        add(foodf);
        add(placef);
        add(transf);
        add(overf);
        add(label1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Objects.equals(desf.getText(), "") || foodf.getText().equals("")|| placef.getText().equals("")|| transf.getText().equals("")||overf.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please fill the required fields");
        }
        else {
            String name = desf.getText();
            int food = Integer.parseInt(foodf.getText());
            int place = Integer.parseInt(placef.getText());
            int transport = Integer.parseInt(transf.getText());
            int over = Integer.parseInt(overf.getText());
            String insert = "INSERT INTO Reviews (username,name,desifood,public,place,overall) VALUES(?,?,?,?,?,?) ";
            Conn c = new Conn();
            try {
                Connection connection = c.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(insert);
                pstmt.setString(1,LoginPage.perfectName);
                pstmt.setString(2, name);
                pstmt.setString(3, String.valueOf(food));
                pstmt.setString(4, String.valueOf(transport));
                pstmt.setString(5, String.valueOf(place));
                pstmt.setString(6, String.valueOf(over));
                pstmt.executeUpdate();
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(null, "Your review has been posted");
        }
    }
}
