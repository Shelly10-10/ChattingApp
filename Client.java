

package chattingapplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;
public class Client implements ActionListener{
    JTextField text;
    static JPanel p1;
    static Box vertical=Box.createVerticalBox();
    static DataOutputStream dout;
    static JFrame f=new JFrame();
    Client(){
        
        f.setLayout(null);
        //header
        JPanel p=new JPanel();
        p.setBounds(0, 0,450, 70);
        p.setBackground(Color.PINK);
        f.add(p);
        
        ImageIcon i0=new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i01=i0.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i02=new ImageIcon(i01);
        JLabel back=new JLabel(i02);
        back.setBounds(5, 20, 30, 30);
        p.setLayout(null);
        p.add(back);
        
        back.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent m){
            System.exit(0);
        }
    } );
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/gaitonde.jpeg"));
        Image i2=i1.getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel profile=new JLabel(i3);
        profile.setBounds(40,10,50,50);
        p.add(profile);
        
        
        ImageIcon i20=new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i21=i20.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i22=new ImageIcon(i21);
        JLabel ph=new JLabel(i22);
        ph.setBounds(280, 20, 30, 30);
        p.add(ph);
        
        ImageIcon i30=new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i31=i30.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i32=new ImageIcon(i31);
        JLabel vid=new JLabel(i32);
        vid.setBounds(330, 20, 30, 30);
        p.add(vid);
        
        ImageIcon i40=new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i41=i40.getImage().getScaledInstance(10, 25,Image.SCALE_DEFAULT);
        ImageIcon i42=new ImageIcon(i41);
        JLabel icon=new JLabel(i42);
        icon.setBounds(380, 20, 10, 25);
        p.add(icon);
        
        JLabel name=new JLabel("Sunny");
        name.setBounds(110,18,200,20);
        name.setForeground(Color.BLACK);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,16));
        p.add(name);
        
        JLabel status=new JLabel("Active Now");
        status.setBounds(110,18,200,58);
        status.setForeground(Color.black);
        status.setFont(new Font("SAN_SERIF",Font.BOLD,10));
        p.add(status);
        
        
        //BODY
        
        p1=new JPanel();
        p1.setBounds(5,75,440,570);
        p1.setBackground(Color.white);
        f.add(p1);
        
        text=new JTextField();
        text.setBounds(5, 653, 310, 40);
        text.setBackground(Color.LIGHT_GRAY);
        text.setFont(new Font("Times New Roman",Font.ITALIC,16));
        f.add(text);
        
        JButton btn=new JButton("Send");
        btn.setBounds(320, 653, 123, 40);
        btn.setBackground(Color.pink);
        btn.addActionListener(this);
        btn.setFont(new Font("Times New Roman",Font.ITALIC,20));
        btn.setForeground(Color.BLACK);
        f.add(btn);
        
        
        
        f.setSize(450,700);
        f.setLocation(900,50);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.black);
        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        try{
        String ans=text.getText();
        JPanel p2=formatLabel(ans);
        p1.setLayout(new BorderLayout());
        p2.setBackground(Color.white);
        JPanel right=new JPanel(new BorderLayout());
        right.setBackground(Color.white);
        right.setForeground(Color.white);
        right.add(p2,BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(10));
        p1.add(vertical,BorderLayout.PAGE_START);
        dout.writeUTF(ans);
        text.setText("");
        f.repaint();
        f.invalidate();
        f.validate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static JPanel formatLabel(String ans){
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JLabel output=new JLabel(ans);
        output.setBackground(Color.LIGHT_GRAY);
        output.setForeground(Color.black);
        output.setOpaque(true);
        output.setFont(new Font("Times New Roman",Font.ITALIC,16));
        output.setBorder(new EmptyBorder(10,10,10,35));
        panel.add(output);
        
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        JLabel time=new JLabel();
        time.setFont(new Font("Times New Roman",Font.ITALIC,11));
        time.setBackground(Color.LIGHT_GRAY);
        time.setForeground(Color.BLACK);
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        return panel;
    }
    public static void main(String[] args) {
        new Client();
        try{
            Socket s=new Socket("127.0.0.1",6001);//this socket is connected to server
            DataInputStream din=new DataInputStream(s.getInputStream());
           dout=new DataOutputStream(s.getOutputStream());
            while(true){
                p1.setLayout(new BorderLayout());
                String msg=din.readUTF();
                JPanel panel=formatLabel(msg);
                panel.setBackground(Color.white);
                JPanel left=new JPanel(new BorderLayout());
                left.add(panel,BorderLayout.LINE_START);
                left.setBackground(Color.white);
                left.setBackground(Color.white);
                vertical.add(left);
                vertical.add(Box.createVerticalStrut(15));
                p1.add(vertical,BorderLayout.PAGE_START);
                
                f.validate();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
