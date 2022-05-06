package clases;

import com.company.BackgroundPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings implements ActionListener {
    public Settings(ImageIcon backgroundImage, Color backgroundComponent, Color foregroundComponent, Border marginComponent){
        this.backgroundComponent = backgroundComponent;
        this.foregroundComponent = foregroundComponent;
        this.marginComponent = marginComponent;
        frame = new JFrame("Settings");
        panel = new BackgroundPanel(backgroundImage);
        confirmChanges = new JButton("Confirm");
        explanationBackgroundStyle = new JLabel("Select Background Style");
        explanationColor = new JLabel("Select Color");
        explanationLanguage = new JLabel("Select Languague");
        comboBoxStyleBackground = new JComboBox<>(optionsStyleBackground);
        comboBoxColor = new JComboBox<>(optionsColor);
        comboBoxlanguage = new JComboBox<>(optionsLanguage);

        setComponents();
        addComponentsPanelStyle();
        setFrame();
    }

    public Settings(Color backgroundColor, Color backgroundComponent, Color foregroundComponent, Border marginComponent){
        this.backgroundColor = backgroundColor;
        this.backgroundComponent = backgroundComponent;
        this.foregroundComponent = foregroundComponent;
        this.marginComponent = marginComponent;
        frame = new JFrame("Settings");
        jPanel = new JPanel(null);
        confirmChanges = new JButton("Confirm");
        explanationBackgroundStyle = new JLabel("Select Background Style");
        explanationColor = new JLabel("Select Color");
        explanationLanguage = new JLabel("Select Languague");
        comboBoxStyleBackground = new JComboBox<>(optionsStyleBackground);
        comboBoxColor = new JComboBox<>(optionsColor);
        comboBoxlanguage = new JComboBox<>(optionsLanguage);

        setComponents();
        addComponentsJPanel();
        setFrame();
    }

    private void addComponentsJPanel(){
        jPanel.setBackground(backgroundColor);
        jPanel.add(explanationBackgroundStyle);
        jPanel.add(explanationLanguage);
        jPanel.add(confirmChanges);
        jPanel.add(comboBoxStyleBackground);
        jPanel.add(comboBoxlanguage);
        jPanel.add(comboBoxColor);

        frame.add(jPanel);
    }

    private void addComponentsPanelStyle(){
        panel.add(explanationBackgroundStyle);
        panel.add(explanationLanguage);
        panel.add(confirmChanges);
        panel.add(comboBoxStyleBackground);
        panel.add(comboBoxlanguage);
        panel.add(comboBoxColor);

        frame.add(panel);
    }

    private void setFrame(){
        CenterFrame cf = new CenterFrame();
        Dimension resolution = cf.center();
        int width = resolution.width;
        int height = resolution.height;

        frame.setBounds(width / 2 - 150, height / 2 - 150, 350, 300);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    private void setComponents(){
        explanationBackgroundStyle.setBounds(10, 10, 200, 30);
        explanationLanguage.setBounds(200, 10, 130, 30);

        explanationBackgroundStyle.setForeground(foregroundComponent);
        explanationBackgroundStyle.setFont(new Font("Segoe UI", Font.BOLD, 13));

        explanationLanguage.setForeground(foregroundComponent);
        explanationLanguage.setFont(new Font("Segoe UI", Font.BOLD, 13));

        confirmChanges.setBounds(120, 220, 100, 25);
        confirmChanges.setFont(new Font("Segoe UI", Font.BOLD, 14));
        confirmChanges.setBorder(marginComponent);
        confirmChanges.setFocusable(false);
        confirmChanges.setBackground(backgroundComponent);
        confirmChanges.setForeground(foregroundComponent);
        confirmChanges.addActionListener(this);

        comboBoxStyleBackground.setBounds(10, 50, 160, 30);
        comboBoxStyleBackground.setFocusable(false);
        comboBoxStyleBackground.setBackground(backgroundComponent);
        comboBoxStyleBackground.setForeground(foregroundComponent);

        comboBoxlanguage.setBounds(200, 50, 120, 30);
        comboBoxlanguage.setFocusable(false);
        comboBoxlanguage.setBackground(backgroundComponent);
        comboBoxlanguage.setForeground(foregroundComponent);

        comboBoxColor.setBounds(10, 130, 160, 30);
        comboBoxColor.setFocusable(false);
        comboBoxColor.setBackground(backgroundComponent);
        comboBoxColor.setForeground(foregroundComponent);
        comboBoxColor.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Confirm")){
            if (comboBoxStyleBackground.getItemAt(comboBoxStyleBackground.getSelectedIndex()).equals("Solid Color") && flag){
                comboBoxColor.setVisible(true);
                flag = false;
            }else {
                if (comboBoxlanguage.getItemAt(comboBoxlanguage.getSelectedIndex()).equals("Spanish")){
                    selectedLanguage = 0;   //Idioma espanol
                }else{
                    selectedLanguage = 1;   //Idioma ingles
                }
                if (!flag && comboBoxColor.getItemAt(comboBoxColor.getSelectedIndex()).equals("Black")){
                    selectedColor = 1;
                }else if(!flag && comboBoxColor.getItemAt(comboBoxColor.getSelectedIndex()).equals("Grey")){
                    selectedColor = 2;
                }
                frame.setVisible(false);
                Main.startInterface(selectedLanguage, selectedColor);
            }

        }
    }

    //Variables
    private JFrame frame;
    private BackgroundPanel panel;
    private JPanel jPanel;
    private Color backgroundComponent;
    private Color foregroundComponent;
    private Border marginComponent;
    private JComboBox<String> comboBoxStyleBackground;
    private JComboBox<String> comboBoxColor;
    private JComboBox<String> comboBoxlanguage;
    private JButton confirmChanges;
    private int selectedColor = 0;
    private int selectedLanguage = 0;
    private JLabel explanationLanguage;
    private JLabel explanationBackgroundStyle;
    private JLabel explanationColor;
    private String[] optionsStyleBackground = {"Background With Style", "Solid Color"};
    private String[] optionsColor = {"Black", "Grey"};
    private String[] optionsLanguage = {"English", "Spanish"};
    private boolean flag = true; //Lo utilizo como bandera parta determinar cuando sale el combobox de los colores solidos
    private Color backgroundColor;
}
