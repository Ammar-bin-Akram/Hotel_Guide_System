import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

class Preference extends JFrame implements ActionListener {
    private final JLabel label1;
    private final JLabel QuesLabel;
    private final JLabel preferLabel;
    private final JLabel label2;
    private final ImageIcon icon1, islIcon, kIcon, lIcon, mIcon;
    public int desifood = 2, continentalFood = 2, pakistaniFood = 2, history = 2, greenery = 2, beaches = 2, mountains = 2, walking = 2, privateTransport = 2, publicTransport = 2;
    Font font;
    private final JLabel pic1;
    private String desi, continental,his, green, beach, mountain, walk, privateTrans, publicTrans, result;
    private final JButton showQues;
    private final JButton all;
    private JButton showDestinations;
    String highestDestination = "";
    public Preference() {
        setSize(900, 690);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(300, 30);
        font = new Font("Serif", Font.ITALIC, 20);
        QuesLabel = new JLabel("Please answer the following to get a preference for the tourism site!");
        QuesLabel.setBounds(10, 60, 700, 20);
        QuesLabel.setFont(font);
        QuesLabel.setForeground(Color.WHITE);
        showQues = new JButton("Show Questions");
        showQues.setBounds(50, 100, 200, 50);
        showQues.setFont(font);
        showQues.setForeground(Color.BLUE);
        showQues.setBackground(Color.WHITE);
        showQues.addActionListener(this);
        all = new JButton("");

        icon1 = new ImageIcon("C:\\Users\\Salik Pasha\\Desktop\\FOP\\FOP\\background.png");
        label1 = new JLabel(icon1);
        label1.setBounds(0, 0, 900, 650);
        label2 = new JLabel("");


        //images of destinations
        islIcon = new ImageIcon("C:\\Users\\Salik Pasha\\Pictures\\IslamabadResized.jpeg");
        kIcon = new ImageIcon("C:\\Users\\Salik Pasha\\Pictures\\KarachiResized.jpeg");
        lIcon = new ImageIcon("C:\\Users\\Salik Pasha\\Pictures\\LahoreResized.jpeg");
        mIcon = new ImageIcon("C:\\Users\\Salik Pasha\\Pictures\\MurreeResized.jpg");
        pic1 = new JLabel();
        pic1.setBounds(320, 220, 200, 200);



        //label that shows the preference
        preferLabel = new JLabel("");
        //adding components to JFrame
        add(label1);
        add(label2);

        add(preferLabel);
        add(QuesLabel);
        add(showQues);
        add(pic1);
        add(all);
        add(label1);
    }
    public void GivePreference() {
        int lahore, islamabad, karachi, murree;
        lahore = desifood + history + privateTransport;
        islamabad = continentalFood + greenery + publicTransport;
        karachi = desifood + beaches + publicTransport;
        murree = desifood + mountains + walking;
        highestDestination = "";
        double highestValue = Double.MIN_VALUE;
        Conn c = new Conn();
        try {
            Connection connection = c.getConnection();
            String query = "SELECT name,desifood,public,place FROM Reviews";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                String dest = rs.getString("name");
                int desi = rs.getInt("desifood");
                int trans = rs.getInt("public");
                int famous = rs.getInt("place");
                //calculate euclidean distance
                double similarity = calculateSimilarity(desifood,publicTransport,desi,trans);
                if(dest.equals("Lahore"))
                    similarity += lahore;
                else if(dest.equals("Islamabad"))
                    similarity += islamabad;
                else if(dest.equals("Karachi"))
                    similarity += karachi;
                else if(dest.equals("Murree"))
                    similarity += murree;
                //System.out.println(dest+": "+similarity);
                if(similarity >highestValue){
                    highestValue = similarity;
                    highestDestination = dest;
                }
            }
        }catch(SQLException se){se.printStackTrace();}
        result = "Based on your responses, the best preference for you is "+highestDestination;
    }
    public static double calculateSimilarity(int desifood,int publictrans,int desi,int trans){
        double distance = Math.sqrt(Math.pow(desifood-desi,2))+Math.sqrt(Math.pow(publictrans-trans,2));
        return distance;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showQues) {
            desi = JOptionPane.showInputDialog(null, "How much do you rate desi food out of 10?");
            if (!Objects.equals(desi, "")) {
                desifood = Integer.parseInt(desi);
            }
            continental = JOptionPane.showInputDialog(null, "How much do you rate continental food out of 10?");
            if (!Objects.equals(continental, "")) {
                continentalFood = Integer.parseInt(continental);
            }
            //Getting ratings of famous places
            his = JOptionPane.showInputDialog(null, "How much do you rate historical sights out of 10?");
            if (!Objects.equals(his, "")) {
                history = Integer.parseInt(his);
            }
            green = JOptionPane.showInputDialog(null, "How much do you rate greenery out of 10?");
            if (!Objects.equals(green, "")) {
                greenery = Integer.parseInt(green);
            }
            beach = JOptionPane.showInputDialog(null, "How much do you rate beaches out of 10?");
            if (!Objects.equals(beach, "")) {
                beaches = Integer.parseInt(beach);
            }
            mountain = JOptionPane.showInputDialog(null, "How do much you rate mountains out of 10?");
            if (!Objects.equals(mountain, "")) {
                mountains = Integer.parseInt(mountain);
            }
            //Getting ratings of transports
            walk = JOptionPane.showInputDialog(null, "How much do you like walking out of 10?");
            if (!Objects.equals(walk, "")) {
                walking = Integer.parseInt(walk);
            }
            privateTrans = JOptionPane.showInputDialog(null, "How much do you like private transport out of 10?");
            if (!Objects.equals(privateTrans, "")) {
                privateTransport = Integer.parseInt(privateTrans);
            }
            publicTrans = JOptionPane.showInputDialog(null, "How much do you like public transport out of 10?");
            if (!Objects.equals(publicTrans, "")) {
                publicTransport = Integer.parseInt(publicTrans);
            }
            GivePreference();
            if(highestDestination.equals("Islamabad")){
                label2.setText("Islamabad: Most beautiful city of Pakistan");
                label2.setBounds(300,450,300,30);
                label2.setFont(new Font("Serif", Font.ITALIC, 15));
                label2.setForeground(Color.WHITE);
                pic1.setIcon(islIcon);
            }
            else if(highestDestination.equals("Lahore")){
                label2.setText("Lahore: Historical city of Pakistan");
                label2.setBounds(300,450,300,30);
                label2.setFont(new Font("Serif", Font.ITALIC, 15));
                label2.setForeground(Color.WHITE);
                pic1.setIcon(lIcon);
            }
            else if(highestDestination.equals("Karachi")){
                label2.setText("Karachi: Industrial hub of Pakistan");
                label2.setBounds(300,450,300,30);
                label2.setFont(new Font("Serif", Font.ITALIC, 15));
                label2.setForeground(Color.WHITE);
                pic1.setIcon(kIcon);
            }
            else if(highestDestination.equals("Murree")){
                label2.setText("Murree: Mountain resort in Pakistan");
                label2.setBounds(300,450,300,30);
                label2.setFont(new Font("Serif", Font.ITALIC, 15));
                label2.setForeground(Color.WHITE);
                pic1.setIcon(mIcon);
            }
            //System.out.println(result);
            preferLabel.setText(result);
            preferLabel.setBounds(10, 150, 700, 100);
            preferLabel.setFont(font);
            preferLabel.setForeground(Color.WHITE);
            all.setText("All Destinations");
            all.setBounds(330, 500, 200, 50);
            all.setForeground(Color.BLUE);
            all.setBackground(Color.WHITE);
            all.setFont(font);
            all.addActionListener(this);
        } else if (e.getSource() == all) {
            setVisible(false);
            new ShowDestinations().setVisible(true);
        }
    }
}
