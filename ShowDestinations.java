import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

class ShowDestinations extends JFrame implements ActionListener {
    private final JLabel iLabel;
    private final JLabel kLabel;
    private final JLabel mLabel;
    private final JLabel lLabel;
    private final JLabel label1;
    private final JLabel isl;
    private final JLabel mur;
    private final JLabel lhr;
    private final JLabel khi;
    private final JButton details1;
    private final JButton details2;
    private final JButton details3;
    private final JButton details4;
    private final JButton bookBtn;
    private JButton oldbook;
    private final ImageIcon islIcon;
    private final ImageIcon kIcon;
    private final ImageIcon mIcon;
    private final ImageIcon lIcon;
    private final ImageIcon icon1;

    public ShowDestinations() {
        setSize(1366, 768);
        setLocation(400, 100);
        setTitle("All Destinations");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLUE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        Font font = new Font("Serif", Font.ITALIC, 20);

        icon1 = new ImageIcon("C:\\Users\\Salik Pasha\\Desktop\\FOP\\FOP\\background2.png");
        label1 = new JLabel(icon1);
        label1.setBounds(0, 0, 900, 650);
        //destinations names labels
        islIcon = new ImageIcon("C:\\Users\\Salik Pasha\\Pictures\\Islamabadmain.jpeg");
        iLabel = new JLabel(islIcon);
        iLabel.setBounds(20, 50, 320, 384);
        kIcon = new ImageIcon("C:\\Users\\Salik Pasha\\Pictures\\Karachimain.jpeg");
        kLabel = new JLabel(kIcon);
        kLabel.setBounds(360, 50, 320, 384);
        lIcon = new ImageIcon("C:\\Users\\Salik Pasha\\Pictures\\Lahoremain.jpeg");
        lLabel = new JLabel(lIcon);
        lLabel.setBounds(700, 50, 320, 384);
        mIcon = new ImageIcon("C:\\Users\\Salik Pasha\\Pictures\\Murreemain.jpg");
        mLabel = new JLabel(mIcon);
        mLabel.setBounds(1040, 50, 320, 384);
        isl = new JLabel("Islamabad");
        isl.setBounds(100, 454, 160, 30);
        isl.setFont(new Font("Helvetica", Font.BOLD + Font.ITALIC, 30));
        isl.setForeground(Color.WHITE);
        khi = new JLabel("Karachi");
        khi.setBounds(450, 454, 160, 30);
        khi.setForeground(Color.WHITE);
        khi.setFont(new Font("Helvetica", Font.BOLD + Font.ITALIC, 30));
        lhr = new JLabel("Lahore");
        lhr.setBounds(800, 454, 160, 30);
        lhr.setFont(new Font("Helvetica", Font.BOLD + Font.ITALIC, 30));
        lhr.setForeground(Color.WHITE);
        mur = new JLabel("Murree");
        mur.setBounds(1150, 454, 160, 30);
        mur.setFont(new Font("Helvetica", Font.BOLD + Font.ITALIC, 30));
        mur.setForeground(Color.WHITE);

        //buttons to show descriptions of destinations
        details1 = new JButton("Details");
        details1.setBounds(110, 500, 120, 30);
        details1.setBackground(Color.WHITE);
        details1.setForeground(Color.BLUE);
        details1.setFont(new Font("Helvetica", Font.BOLD + Font.ITALIC, 15));
        details1.addActionListener(this);

        details2 = new JButton("Details");
        details2.setBounds(450, 500, 120, 30);
        details2.setForeground(Color.BLUE);
        details2.setBackground(Color.WHITE);
        details2.setFont(new Font("Helvetica", Font.BOLD + Font.ITALIC, 15));
        details2.addActionListener(this);

        details3 = new JButton("Details");
        details3.setBounds(800, 500, 120, 30);
        details3.setForeground(Color.BLUE);
        details3.setBackground(Color.WHITE);
        details3.setFont(new Font("Helvetica", Font.BOLD + Font.ITALIC, 15));
        details3.addActionListener(this);

        details4 = new JButton("Details");
        details4.setBounds(1150, 500, 120, 30);
        details4.setForeground(Color.BLUE);
        details4.setBackground(Color.WHITE);
        details4.setFont(new Font("Helvetica", Font.BOLD + Font.ITALIC, 15));
        details4.addActionListener(this);

        bookBtn = new JButton("Book");
        bookBtn.setBounds(620, 600, 150, 30);
        bookBtn.setForeground(Color.BLUE);
        bookBtn.setBackground(Color.WHITE);
        bookBtn.setFont(new Font("Helvetica", Font.BOLD + Font.ITALIC, 15));
        bookBtn.addActionListener(this);

        oldbook = new JButton("Old Bookings");
        oldbook.setBounds(620,650,150,30);
        oldbook.setFont(font);
        oldbook.setForeground(Color.BLUE);
        oldbook.setBackground(Color.white);
        oldbook.addActionListener(this);

        add(lLabel);
        add(kLabel);
        add(iLabel);
        add(mLabel);
        add(isl);
        add(khi);
        add(lhr);
        add(mur);
        add(details1);
        add(details2);
        add(details3);
        add(details4);
        add(bookBtn);
        add(oldbook);
        add(label1);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == bookBtn) {
            new Booking().setVisible(true);
        }
        else if (e.getSource() == oldbook){
            try {
                new OldBooking().setVisible(true);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }//deatils1->islamabad,details2->karachi,details3->lahore,details4->murree
        else if(e.getSource() == details1) {
            new Islamabad().setVisible(true);
        }
        else if(e.getSource() == details2){
            new Karachi().setVisible(true);
        }
        else if(e.getSource() == details3){
            new Lahore().setVisible(true);
        }
        else if(e.getSource() == details4){
            new Murree().setVisible(true);
        }
    }
}