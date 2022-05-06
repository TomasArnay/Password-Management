package clases;

import com.company.BackgroundPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel implements ActionListener{

    public LoginPanel(){
        //Test
        if (testLoginPassword() == 0) {
            System.out.println("Test OK");
        }else {
            System.out.println("Test Error");
        }
    }

    public void startEnglishBackgroundStyle(ImageIcon backgroundImage, Color backgroundTF, Color foregroundColor, Border margin){
        panel = new BackgroundPanel(backgroundImage);
        this.backgroundTF = backgroundTF;
        this.foregroundColor = foregroundColor;
        this.margin = margin;
        frame = new JFrame("Login");
        loginPassword = new JTextField(30);
        txtLogin = new JLabel("Login");
        accept = new JButton("Accept");

        setComponents();
        addComponents();
        setFrame();
    }

    private void setComponents(){
        txtLogin.setBounds(130, 35, 100, 30);
        loginPassword.setBounds(75, 90, 140, 30);
        accept.setBounds(95, 150, 100, 30);

        txtLogin.setForeground(foregroundColor);

        loginPassword.setBackground(backgroundTF);
        loginPassword.setForeground(foregroundColor);
        loginPassword.setFont(new Font("Segoe UI", Font.BOLD, 14));

        accept.setFocusable(false);
        accept.addActionListener(this);
        accept.setBackground(backgroundTF);
        accept.setForeground(foregroundColor);
        accept.setBorder(margin);
        accept.setFont(new Font("Segoe UI", Font.BOLD, 14));
    }

    private void addComponents(){
        panel.add(loginPassword);
        panel.add(txtLogin);
        panel.add(accept);

        frame.add(panel);
    }

    private void setFrame(){
        CenterFrame cf = new CenterFrame();
        Dimension resolution = cf.center();
        int width = resolution.width;
        int height = resolution.height;

        frame.setBounds(width / 2 - 150, height / 2 - 150, 300, 250);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public int comparationPassword(String firstString, String secondString){
        if(firstString.equals(secondString)){
            return 1;
        }
        return 0;
    }

    //Test del metodo de comparacion de strings
    public int testLoginPassword(){
        if(comparationPassword("Hello World", "Hello World") != 1){
            return 2;
        }
        return 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Accept")){
            frame.setVisible(false);

            Main start = new Main();
            String loginPass = "Alfredo1959";
            start.connection(comparationPassword(loginPassword.getText(), loginPass));
        }//Escribir el codigo cuando la contrasenia no coincide
    }

    //Variables
    private JFrame frame;
    private BackgroundPanel panel;
    private JTextField loginPassword;
    private JLabel txtLogin;
    private JButton accept;
    private Color backgroundTF;
    private Color foregroundColor;
    private Border margin;
}
