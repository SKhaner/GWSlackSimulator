import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class GWackClientGUI extends JFrame{
    
    private Socket socket;
    private PrintWriter pw;
    private BufferedReader buff;
    private Sender nn;
    private final String[] values = {"Available", "Away", "Busy"};
    private final Color[] color = {java.awt.Color.BLACK, java.awt.Color.ORANGE, java.awt.Color.RED, java.awt.Color.BLUE, java.awt.Color.MAGENTA, java.awt.Color.PINK};
    private final String[] colors = {"Black", "Orange", "Red", "Blue", "Magenta", "Pink"};
    private final int[] fonts = {Font.PLAIN, Font.ITALIC, Font.BOLD};
    private final String[] font = {"Plain", "Italic", "Bold"};
    private final String[] themes = {"LightMode", "Darkmode"};
    private JPanel panel; 
    private JLabel text; 
    private JLabel name;
    private JLabel ttype;
    private JLabel stat;
    private JLabel choosecolor;
    private JLabel IPadLabel;
    private JLabel PortLabel;
    private JLabel MembersLabel;
    private JLabel MessagesLabel;
    private JLabel ComposeLabel;
    private JTextField IPField;
    private JTextField PortField;
    private JTextField NameField;
    private JButton cd;
    private JButton send;
    private JTextArea Members;
    private JTextArea Messages;
    private JTextArea Compose;
    private JComboBox status; 
    private JComboBox colorbox;
    private JComboBox fbox;
    private JComboBox theme;

    public void lightmode(){
        Color rr = new Color (35, 78, 112);
        Color gwY = new ColorUIResource (251, 248, 190);
        panel.setBackground (rr);
        text.setForeground(gwY);
        name.setForeground (gwY); 
        ttype.setForeground (gwY);
        stat.setForeground (gwY);
        choosecolor.setForeground (gwY);
        IPadLabel.setForeground (gwY);
        PortLabel.setForeground (gwY);
        MembersLabel.setForeground (gwY);
        MessagesLabel.setForeground (gwY);
        ComposeLabel.setForeground (gwY);
        IPField.setBackground (gwY);
        PortField.setBackground(gwY);
        NameField.setBackground(gwY);
        cd.setBackground (gwY);
        send.setBackground (gwY);
        Members.setBackground(gwY);
        Messages.setBackground (gwY);
        Compose.setBackground (gwY); 
        status.setBackground (gwY);
        colorbox.setBackground (gwY);
        fbox.setBackground (gwY);
        theme.setBackground (gwY);
    }

    public void Darkmode (){
        Color gwY = new ColorUIResource (251, 248, 190);
        panel.setBackground (java.awt.Color.darkGray);
        text.setForeground (gwY) ;
        name.setForeground (gwY) ; 
        ttype.setForeground (gwY);
        stat.setForeground(gwY) ;
        choosecolor.setForeground (gwY);
        IPadLabel.setForeground(gwY) ;
        PortLabel.setForeground (gwY) ;
        MembersLabel.setForeground (gwY);
        MessagesLabel.setForeground(gwY);
        ComposeLabel.setForeground (gwY) ;
        IPField.setBackground(gwY);
        PortField.setBackground(gwY);
        NameField.setBackground (gwY); 
        cd.setBackground (gwY);
        send.setBackground (gwY);
        Members.setBackground(gwY);
        Messages.setBackground (gwY) ;
        Compose.setBackground(gwY);
        status.setBackground (gwY); 
        colorbox.setBackground (gwY); 
        fbox.setBackground (gwY) ; 
        theme.setBackground (gwY);
    }

    public void disconnect(){
        try{
            pw.close();
            buff.close();
            socket.close();
        } catch(IOException e){
            JOptionPane.showMessageDialog(new JFrame(), "Server disconnected", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void connect(Integer port, String ip){
        try{
            socket = new Socket(ip, port);
            pw = new PrintWriter(socket.getOutputStream());
            buff = new BufferedReader(new java.io.InputStreamReader(socket.getInputStream()));
            pw.println("SECRET");
            pw.println("3c3c4ac618656ae32b7f3431e75f7b26b1a14a87");
            pw.println("NAME");
            pw.println(NameField.getText() + " (" + values[status.getSelectedIndex()] + ")");
            pw.flush();
            nn = new Sender(pw, buff, GWackClientGUI.this);
        } catch(Exception e){
            JOptionPane.showMessageDialog(new JFrame(), "Cannot Connect", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void clearmembers(){
        Members.setText("");
    }

    public void addmember(String message){
        Members.append(message + "\n");
    }

    public void addmsg(String message){
        if(theme.getSelectedIndex() == 0){
            lightmode();
        } else {
            Darkmode();
        }
        Messages.append(message + "\n");
        Color cc = color[colorbox.getSelectedIndex()];
        Messages.setForeground(cc);
        Font type = new Font("Some Font", fonts[fbox.getSelectedIndex()], 12);
        Messages.setFont(type);
    }

    public void clearmessages(){
        Messages.setText("");
    }
    public Socket getSocket(){
        return socket;
    }
    public void sendmsg(){
        String empty = "";
        if(!empty.equals(Compose.getText())){
            nn.sms(Compose.getText());
            Compose.setText("");
        }
    }

    //gui
    private GWackClientGUI(){ 
        socket = null;
        panel = new JPanel();
        this.setSize(930,625);
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.setTitle("GWack -- GW Slack Simulator");
        panel.setLayout (null);
        Color rr = new Color (35, 78, 112);
        Color gwY = new ColorUIResource (251, 248, 190);
        panel.setBackground (rr);
        //comboboxes
        status = new JComboBox<>(values);
        status.setBounds (50, 20,80,25);
        panel.add (status) ;
        status.setBackground (gwY);
        status.setForeground(rr);

        colorbox = new JComboBox<>(colors) ; 
        colorbox.setBounds (230,20,80,25);
        panel.add (colorbox);
        colorbox.setBackground (gwY);
        colorbox.setForeground(rr);

        fbox = new JComboBox<>(font); 
        fbox.setBounds (243,60, 80,25) ; 
        panel.add(fbox);
        fbox.setBackground(gwY);
        fbox.setForeground(rr);

        theme = new JComboBox<>(themes) ;
        theme.setBounds (50,60, 120,25) ;
        panel.add (theme);
        theme.setBackground (gwY);
        theme.setForeground(rr);
        
        ttype = new JLabel ("Theme");
        ttype.setFont(new Font("Courier New", Font.ITALIC, 16));
        ttype.setForeground(gwY);
        ttype.setBounds (0,60,80,25);
        panel.add (ttype);
        ttype.setBackground (gwY);
        text = new JLabel ("Text Font");
        text.setFont(new Font("Courier New", Font.ITALIC, 14));
        text.setForeground(gwY);
        text.setBounds (170,60, 80,25);
        panel.add (text);
        text.setBackground(gwY);
        stat = new JLabel ("Status");
        stat.setFont(new Font("Courier New", Font.ITALIC, 13));
        stat.setForeground(gwY);
        stat.setBounds (0,20,80,25) ;
        panel.add (stat);
        stat.setBackground (gwY);
        choosecolor = new JLabel("Text Color");
        choosecolor.setFont(new Font("Courier New", Font.ITALIC, 13));
        choosecolor.setForeground(gwY);
        choosecolor.setBounds (150, 20, 80,25) ;
        panel.add (choosecolor);
        choosecolor.setBackground (gwY) ;
        name = new JLabel ("Name");
        name.setFont(new Font("Courier New", Font.ITALIC, 16));
        name.setForeground(gwY);
        name.setBounds (320,20,80,25) ;
        panel.add (name) ;
        name.setBackground (gwY);
        IPadLabel = new JLabel("IP Address");
        IPadLabel.setFont(new Font("Courier New", Font.ITALIC, 12));
        IPadLabel.setForeground(gwY);
        IPadLabel.setBounds (495,20,80, 25) ; 
        panel.add (IPadLabel);
        IPadLabel.setBackground (gwY);
        // PortLabel = new JLabel ("Port");
        // PortLabel.setBounds (790,20, 80, 25) ; 
        // panel.add (PortLabel);
        // PortLabel.setBackground(gwY);

        PortLabel = new JLabel ("Port");
        PortLabel.setFont(new Font("Courier New", Font.ITALIC, 14));
        PortLabel.setForeground(gwY);
        PortLabel.setBounds(695, 20,80, 25); 
        panel.add(PortLabel);
        PortLabel.setBackground(gwY);

        MembersLabel = new JLabel("Members Online");
        MembersLabel.setFont(new Font("Courier New", Font.ITALIC, 16));
        MembersLabel.setForeground(gwY);
        MembersLabel.setBounds(0, 150, 150, 25);
        panel.add(MembersLabel);
        MembersLabel.setBackground(gwY);
        
        MessagesLabel = new JLabel("Messages");
        MessagesLabel.setFont(new Font("Courier New", Font.ITALIC, 16));
        MessagesLabel.setForeground(gwY);
        MessagesLabel.setBounds(200, 150, 150, 25);
        panel.add(MessagesLabel);
        //MessagesLabel.setBackground(gwY);

        ComposeLabel = new JLabel("Compose");
        ComposeLabel.setFont(new Font("Courier New", Font.ITALIC, 16));
        ComposeLabel.setForeground(gwY);
        ComposeLabel.setBounds(200, 420, 150, 25);
        panel.add(ComposeLabel);
        ComposeLabel.setBackground(gwY);

        //textfiels
        NameField = new JTextField();
        NameField.setBounds(360, 20, 130, 25);
        panel.add(NameField);
        NameField.setBackground(gwY);

        IPField = new JTextField();
        IPField.setBounds(565, 20, 130, 25);
        panel.add(IPField);
        IPField.setBackground(gwY);

        PortField = new JTextField();
        IPField.setBounds(565, 20, 130, 25);
        panel.add(IPField);
        IPField.setBackground(gwY);

        PortField = new JTextField();
        PortField.setBounds(730, 20, 80, 25);
        panel.add(PortField);
        PortField.setBackground(gwY);

        //buttoms
        cd = new JButton("Connect");
        cd.setFont(new Font("Courier New", Font.ITALIC, 14));
        cd.setBounds (820,20, 100,25);
        panel.add(cd);
        cd.setBackground(gwY);
        cd.setForeground(rr);

        send = new JButton("Send");
        send.setFont(new Font("Courier New", Font.ITALIC, 14));
        send.setBounds (840, 550,70,25) ;
        panel.add(send);
        send.setBackground(gwY);
        send.setForeground(rr);
        //action listners
        cd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(cd.getText().equals("Connect")){
                    int val = 0; 
                    boolean port = true;
                    if(NameField.getText().equals("")){
                        JOptionPane.showMessageDialog(new JFrame(), "No Name","Inane error",JOptionPane.ERROR_MESSAGE);
                    }
                    if(PortField.getText().equals("")){
                        JOptionPane.showMessageDialog(new JFrame(), "Invalid Port","Inane error",JOptionPane.ERROR_MESSAGE);
                        port = false;
                    }
                    else{
                        val = Integer.parseInt(PortField.getText());
                    }

                    if((val == 8886 || val == 8887 || val == 8888) && (IPField.getText().equals("ssh-cs2113.adamaviv.com")) && (port)){
                        cd.setText("Disconnect");
                        connect(val, IPField.getText());
                        Members.setEditable(false);
                        Messages.setEditable(false);
                        MessagesLabel.setText("");
                        GWackClientGUI.this.setTitle("GWack -- GW Simulater (Connected)");
                    }
                    if(!(IPField.getText().equals("ssh-cs2113.adamaviv.com"))){
                        JOptionPane.showMessageDialog(new JFrame(), "Invalid Host","Inane error",JOptionPane.ERROR_MESSAGE);
                    }
                    if(!(val == 8886 || val == 8887 || val == 8888) && (port)){
                        JOptionPane.showMessageDialog(new JFrame(), "Invalid Port","Inane error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{ 
                    cd.setText("Connect");
                    Members.setEditable(true);
                    Messages.setEditable(true);
                    GWackClientGUI.this.setTitle("GWack -- GW Simulater (Disconnected)");
                    clearmembers();
                    clearmessages();
                    disconnect();
                }
            }
        });
        send.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent a){
                sendmsg();
            }
        });

        Members = new JTextArea();
        Members.setBounds(0, 170, 180, 345);
        JScrollPane scroll1 = new JScrollPane (Members, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll1.setBounds(0, 170, 180, 345);
        panel.add(scroll1);
        Members.setBackground(gwY);
        Members.setEditable(false);
        Members.setLineWrap(true);
        Members.setWrapStyleWord(true); 

        Messages = new JTextArea();
        Messages.setBounds(200, 170, 720, 240);
        JScrollPane scroll = new JScrollPane (Messages, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(200, 170, 720, 240);
        //panel.add(Messages);
        panel.add(scroll);
        Messages.setBackground(gwY);
        Messages.setEditable(false);
        Messages.setLineWrap(true);
        Messages.setWrapStyleWord(true);   
        

        Compose = new JTextArea();
        Compose.setBounds(200, 440, 720, 75);
        panel.add(Compose);
        Compose.setBackground(gwY);
        Compose.setLineWrap(true);
        Compose.setWrapStyleWord(true);  

        ImageIcon pic = new ImageIcon("gw_horizontal_blk.png");
        pic.setImage(pic.getImage().getScaledInstance(600, 100, Image.SCALE_DEFAULT));
        JLabel picLabel = new JLabel(pic);
        picLabel.setBounds(325, 58, 600, 100);
        panel.add(picLabel);

        Compose.addKeyListener(new KeyListener(){
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    sendmsg();
                }
            }

            @Override
            public void keyTyped(KeyEvent e){

            }

            @Override
            public void keyReleased(KeyEvent e){

            }
        });
        this.setVisible(true);
    }

    public static void main(String args[]) {
        //main method
        GWackClientGUI u = new GWackClientGUI();
        u.setVisible(true);
    }
    
}