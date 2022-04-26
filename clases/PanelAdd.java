package clases;

import com.company.BackgroundPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class PanelAdd implements ActionListener {

    public void startSpanishBackgroundStyle(ImageIcon backgroundImage, Color backgroundColorTF, Color foregroundColor, Border margin) {
        flagLanguage = true;
        this.backgroundColorTF = backgroundColorTF;
        this.foregroundColor = foregroundColor;
        this.margin = margin;
        frame = new JFrame("Agregar Contraseña");
        panel = new BackgroundPanel(backgroundImage);
        name = new JLabel("Nombre");
        account = new JLabel("Cuenta");
        password = new JLabel("Contraseña");
        accept = new JButton("Aceptar");
        passwordGenerator = new JButton();

        setComponents();
        addComponentsPanelStyle();
        setFrame();
    }

    public void startSpanish(Color backgroundColor, Color backgroundColorTF, Color foregroundColor, Border margin) {
        flagLanguage = true;
        this.backgroundColor = backgroundColor;
        this.backgroundColorTF = backgroundColorTF;
        this.foregroundColor = foregroundColor;
        this.margin = margin;
        frame = new JFrame("Agregar Contraseña");
        jPanel = new JPanel(null);
        name = new JLabel("Nombre");
        account = new JLabel("Cuenta");
        password = new JLabel("Contraseña");
        accept = new JButton("Aceptar");
        passwordGenerator = new JButton();

        setComponents();
        addComponentsJPanel();
        setFrame();
    }

    public void startEnglishBackgroundStyle(ImageIcon backgroundImage, Color backgroundColorTF, Color foregroundColor, Border margin) {
        flagLanguage = false;
        this.backgroundColorTF = backgroundColorTF;
        this.foregroundColor = foregroundColor;
        this.margin = margin;
        frame = new JFrame("Add Password");
        panel = new BackgroundPanel(backgroundImage);
        name = new JLabel("Name");
        account = new JLabel("Account");
        password = new JLabel("Password");
        accept = new JButton("Accept");
        passwordGenerator = new JButton();

        setComponents();
        addComponentsPanelStyle();
        setFrame();
    }

    public void startEnglish(Color backgroundColor, Color backgroundColorTF, Color foregroundColor, Border margin) {
        flagLanguage = false;
        this.backgroundColor = backgroundColor;
        this.backgroundColorTF = backgroundColorTF;
        this.foregroundColor = foregroundColor;
        this.margin = margin;
        frame = new JFrame("Add Password");
        jPanel = new JPanel(null);
        name = new JLabel("Name");
        account = new JLabel("Account");
        password = new JLabel("Password");
        accept = new JButton("Accept");
        passwordGenerator = new JButton();

        setComponents();
        addComponentsJPanel();
        setFrame();
    }

    private void addComponentsJPanel(){
        jPanel.setBackground(backgroundColor);
        jPanel.add(name);
        jPanel.add(account);
        jPanel.add(password);
        jPanel.add(passwordGenerator);
        jPanel.add(boxName);
        jPanel.add(boxAccount);
        jPanel.add(boxPassword);
        jPanel.add(accept);

        frame.add(jPanel);
    }

    private void addComponentsPanelStyle(){
        panel.add(name);
        panel.add(account);
        panel.add(password);
        panel.add(passwordGenerator);
        panel.add(boxName);
        panel.add(boxAccount);
        panel.add(boxPassword);
        panel.add(accept);

        frame.add(panel);
    }

    private void setFrame(){
        CenterFrame cf = new CenterFrame();
        Dimension resolution = cf.center();
        int width = resolution.width;
        int height = resolution.height;

        frame.setBounds(width / 2 - 150, height / 2 - 150, 300, 250);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    private void setComponents(){
        name.setBounds(10, 20, 70, 30);
        account.setBounds(10, 70, 70, 30);
        password.setBounds(10, 120, 70, 30);

        boxName.setBounds(80, 20, 140, 30);
        boxAccount.setBounds(80, 70, 140, 30);
        boxPassword.setBounds(80, 120, 140, 30);

        accept.setBounds(75, 170, 100, 30);
        accept.setFocusable(false);
        accept.addActionListener(this);
        accept.setBackground(backgroundColorTF);
        accept.setForeground(foregroundColor);
        accept.setBorder(margin);
        accept.setFont(new Font("Segoe UI", Font.BOLD, 14));

        //PasswordGenerator button
        passwordGenerator.setBounds(230, 120, 30, 30);
        passwordGenerator.setIcon(imageGenerator);
        passwordGenerator.setPressedIcon(imageGeneratorPressed);
        passwordGenerator.setFocusable(false);
        passwordGenerator.setBorder(margin);
        passwordGenerator.setToolTipText("Generate Password");
        passwordGenerator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PasswordGenerator p = new PasswordGenerator();
                String generatedPassword = p.generate(16);
                boxPassword.setText(generatedPassword);
            }
        });

        addComponents();
    }

    //Agrega los componentes a sus arraylist
    private void addComponents(){
        labelArrayList.add(name);
        labelArrayList.add(account);
        labelArrayList.add(password);
        for (int i = 0; i < labelArrayList.size(); i++){
            labelArrayList.get(i).setForeground(foregroundColor);
            labelArrayList.get(i).setFont(new Font("Segoe UI", Font.BOLD, 14));
        }

        textFieldArrayList.add(boxName);
        textFieldArrayList.add(boxAccount);
        textFieldArrayList.add(boxPassword);
        for (int i = 0; i < textFieldArrayList.size(); i++){
            textFieldArrayList.get(i).setForeground(foregroundColor);
            textFieldArrayList.get(i).setBackground(backgroundColorTF);
            textFieldArrayList.get(i).setFont(new Font("Segoe UI", Font.BOLD, 14));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Accept") || e.getActionCommand().equals("Aceptar")){
            if(!boxName.getText().equals("") &&
                    !boxAccount.getText().equals("") &&
                    !boxPassword.getText().equals("")
            ){
                Record record = new Record();
                record.setName(boxName.getText());
                record.setAccount(boxAccount.getText());
                record.setPassword(boxPassword.getText());

                if (verifyData(record.getName()) && verifyData(record.getAccount()) && verifyData(record.getPassword())){
                    Query q = new Query();
                    int control = q.queryAdd(record.getName(), record.getAccount(), record.getPassword());

                    if(control == 2){
                        JOptionPane.showMessageDialog(null,
                                "There was a problem connecting to the database");
                    }else if(control == 0){
                        JOptionPane.showMessageDialog(null,
                                "The record already exists");
                    }else{
                        JOptionPane.showMessageDialog(frame,
                                "The record has been created successfully",
                                "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                        frame.setVisible(false);
                    }
                }else{
                    JOptionPane.showMessageDialog(frame,
                            "The fields can not contain a space",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(frame,
                        "All fields must be complete",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public boolean verifyData(String s){
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == 32){
                return false;
            }
        }
        return true;
    }

    //Convert color
    private int hex(String color_hex) {
        return Integer.parseInt(color_hex, 16);
    }

    //Variables
    private JFrame frame;
    private BackgroundPanel panel;
    private JPanel jPanel;
    private JLabel name;
    private JLabel account;
    private JLabel password;
    private ArrayList<JLabel> labelArrayList = new ArrayList<>(3);
    private JTextField boxName = new JTextField(45);
    private JTextField boxAccount = new JTextField(45);
    private JTextField boxPassword = new JTextField(8);
    private ArrayList<JTextField> textFieldArrayList = new ArrayList<>(3);
    private JButton accept;
    private JButton passwordGenerator;
    private Border margin;
    private Color foregroundColor;
    private Color backgroundColorTF;
    private Color backgroundColor;
    private ImageIcon imageGenerator = new ImageIcon("src/Images/generator.png");
    private ImageIcon imageGeneratorPressed = new ImageIcon("src/Images/generatorPressed.png");
    private boolean flagLanguage;       //Si es verdadero es ingles, si es falso es espanol
}
