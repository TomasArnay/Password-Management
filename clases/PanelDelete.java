package clases;

import com.company.BackgroundPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PanelDelete implements ActionListener {
    public void startEnglishBackgroundStyle(ImageIcon backgroundImage, Color backgroundColorTF, Color foregroundColor, Border margin){
        this.backgroundButton = backgroundColorTF;
        this.foregroundButton = foregroundColor;
        this.margin = margin;
        panel = new BackgroundPanel(backgroundImage);
        frame = new JFrame("Delete Password");
        explanation = new JLabel("Enter the name of the record you want to delete");
        nameEntered = new JTextField(8);
        deleteRecord = new JButton("Delete");

        setComponents();
        addComponentsPanelStyle();
        setFrame();
    }

    public void startSpanishBackgroundStyle(ImageIcon backgroundImage, Color backgroundColorTF, Color foregroundColor, Border margin){
        this.backgroundButton = backgroundColorTF;
        this.foregroundButton = foregroundColor;
        this.margin = margin;
        panel = new BackgroundPanel(backgroundImage);
        frame = new JFrame("Eliminar Contraseña");
        explanation = new JLabel("Ingrese el nombre del registro a eliminar");
        nameEntered = new JTextField(8);
        deleteRecord = new JButton("Eliminar");

        setComponents();
        addComponentsPanelStyle();
        setFrame();
    }

    public void startEnglish(Color backgroundColor, Color backgroundColorTF, Color foregroundColor, Border margin){
        this.backgroundColor = backgroundColor;
        this.backgroundButton = backgroundColorTF;
        this.foregroundButton = foregroundColor;
        this.margin = margin;
        jPanel = new JPanel(null);
        frame = new JFrame("Delete Password");
        explanation = new JLabel("Enter the name of the record you want to delete");
        nameEntered = new JTextField(8);
        deleteRecord = new JButton("Delete");

        setComponents();
        addComponentsJPanel();
        setFrame();
    }

    public void startSpanish(Color backgroundColor, Color backgroundColorTF, Color foregroundColor, Border margin){
        this.backgroundColor = backgroundColor;
        this.backgroundButton = backgroundColorTF;
        this.foregroundButton = foregroundColor;
        this.margin = margin;
        jPanel = new JPanel(null);
        frame = new JFrame("Eliminar Contraseña");
        explanation = new JLabel("Ingrese el nombre del registro a eliminar");
        nameEntered = new JTextField(8);
        deleteRecord = new JButton("Eliminar");

        setComponents();
        addComponentsJPanel();
        setFrame();
    }

    private void addComponentsJPanel(){
        jPanel.setBackground(backgroundColor);
        jPanel.add(explanation);
        jPanel.add(nameEntered);
        jPanel.add(deleteRecord);

        frame.add(jPanel);
    }

    private void addComponentsPanelStyle(){
        panel.add(explanation);
        panel.add(nameEntered);
        panel.add(deleteRecord);

        frame.add(panel);
    }

    private void setFrame(){
        CenterFrame cf = new CenterFrame();
        Dimension resolution = cf.center();
        int width = resolution.width;
        int height = resolution.height;

        frame.setBounds(width / 2 - 150, height / 2 - 150, 350, 250);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void setComponents(){
        explanation.setBounds(20, 20, 300, 50);
        explanation.setForeground(Color.WHITE);
        explanation.setFont(new Font("Segoe UI", Font.BOLD, 13));

        nameEntered.setBounds(85, 80, 150, 30);
        nameEntered.setFont(new Font("Segoe UI", Font.BOLD, 14));
        nameEntered.setForeground(foregroundButton);
        nameEntered.setBackground(backgroundButton);

        deleteRecord.setBounds(110, 150, 100, 30);
        deleteRecord.setFont(new Font("Segoe UI", Font.BOLD, 14));
        deleteRecord.setBorder(margin);
        deleteRecord.setFocusable(false);
        deleteRecord.setBackground(backgroundButton);
        deleteRecord.setForeground(foregroundButton);
        deleteRecord.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Delete") || e.getActionCommand().equals("Eliminar")){
            name = nameEntered.getText();
            if (!nameEntered.getText().equals("")){           //Si el campo no esta vacío
                if (verifyName(name)){          //Si el nombre ingresado es correcto
                    int flag = deleteRecord(name);
                    if(flag == 0){            //Si el registro se borró con éxito
                        JOptionPane.showMessageDialog(frame,
                                "The record has been successfully deleted",
                                "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                        frame.setVisible(false);
                    }else if(flag == 1){
                        JOptionPane.showMessageDialog(frame,
                                "The record that you have requested to delete does not exist",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(frame,
                                "An error occurred with the connection to the database",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(frame,
                            "The field can not contain a space",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(frame,
                        "The field is empty", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public int deleteRecord(String name){
        Query borrar = new Query();
        try {
            int cantidad = borrar.deleteRecords(name);
            if (cantidad == 1){
                return 0;
            }else{
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 2;
        }
    }

    public boolean verifyName(String s){
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == 32){
                return false;
            }
        }
        return true;
    }

    //Variables
    private JFrame frame;
    private BackgroundPanel panel;
    private JPanel jPanel;
    private Color backgroundColor;
    private Color backgroundButton;
    private Color foregroundButton;
    private Border margin;
    private JLabel explanation;
    private JTextField nameEntered;
    private JButton deleteRecord;
    String name;
}