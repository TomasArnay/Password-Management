package clases;

import com.company.BackgroundPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PanelUpdate implements ActionListener {

    public void startEnglishBackgroundStyle(ImageIcon backgroundImage, Color backgroundColorTF, Color foregroundColor, Border margin){
        this.backgroundColorTF = backgroundColorTF;
        this.foregroundColor = foregroundColor;
        this.margin = margin;
        panel = new BackgroundPanel(backgroundImage);
        table = new Table(panel);
        frame = new JFrame("Update Password");
        nameLabel = new JLabel("Name");
        accountLabel = new JLabel("Account");
        passwordLabel = new JLabel("Password");
        updateButton = new JButton("Update");
        passwordGenerator = new JButton();
        titleColumnName = "Name";
        titleColumnAccount = "Account";
        titleColumnPassword = "Password";

        addComponents();
        setComponents();
        addComponentsPanelStyle();
        showTable();
        setFrame();
    }

    public void startSpanishBackgroundStyle(ImageIcon backgroundImage, Color backgroundColorTF, Color foregroundColor, Border margin){
        this.backgroundColorTF = backgroundColorTF;
        this.foregroundColor = foregroundColor;
        this.margin = margin;
        panel = new BackgroundPanel(backgroundImage);
        table = new Table(panel);
        frame = new JFrame("Actualizar Contraseña");
        nameLabel = new JLabel("Nombre");
        accountLabel = new JLabel("Cuenta");
        passwordLabel = new JLabel("Contraseña");
        updateButton = new JButton("Actualizar");
        passwordGenerator = new JButton();
        titleColumnName = "Nombre";
        titleColumnAccount = "Cuenta";
        titleColumnPassword = "Contraseña";

        addComponents();
        setComponents();
        addComponentsPanelStyle();
        showTable();
        setFrame();
    }

    public void startEnglish(Color backgroundColor, Color backgroundColorTF, Color foregroundColor, Border margin){
        this.backgroundColor = backgroundColor;
        this.backgroundColorTF = backgroundColorTF;
        this.foregroundColor = foregroundColor;
        this.margin = margin;
        jPanel = new JPanel(null);
        table = new Table(jPanel);
        frame = new JFrame("Update Password");
        nameLabel = new JLabel("Name");
        accountLabel = new JLabel("Account");
        passwordLabel = new JLabel("Password");
        updateButton = new JButton("Update");
        passwordGenerator = new JButton();
        titleColumnName = "Name";
        titleColumnAccount = "Account";
        titleColumnPassword = "Password";

        addComponents();
        setComponents();
        addComponentsJPanel();
        showTable();
        setFrame();
    }

    public void startSpanish(Color backgroundColor, Color backgroundColorTF, Color foregroundColor, Border margin){
        this.backgroundColor = backgroundColor;
        this.backgroundColorTF = backgroundColorTF;
        this.foregroundColor = foregroundColor;
        this.margin = margin;
        jPanel = new JPanel(null);
        table = new Table(jPanel);
        frame = new JFrame("Actualizar Contraseña");
        nameLabel = new JLabel("Nombre");
        accountLabel = new JLabel("Cuenta");
        passwordLabel = new JLabel("Contraseña");
        updateButton = new JButton("Actualizar");
        passwordGenerator = new JButton();
        titleColumnName = "Nombre";
        titleColumnAccount = "Cuenta";
        titleColumnPassword = "Contraseña";

        addComponents();
        setComponents();
        addComponentsJPanel();
        showTable();
        setFrame();
    }

    private void addComponentsJPanel(){
        jPanel.setBackground(backgroundColor);
        jPanel.add(nameLabel);
        jPanel.add(accountLabel);
        jPanel.add(passwordLabel);
        jPanel.add(boxName);
        jPanel.add(boxAccount);
        jPanel.add(boxPassword);
        jPanel.add(updateButton);
        jPanel.add(passwordGenerator);

        frame.add(jPanel);
    }

    private void addComponentsPanelStyle(){
        panel.setBackground(backgroundColor);
        panel.add(nameLabel);
        panel.add(accountLabel);
        panel.add(passwordLabel);
        panel.add(boxName);
        panel.add(boxAccount);
        panel.add(boxPassword);
        panel.add(updateButton);
        panel.add(passwordGenerator);

        frame.add(panel);
    }

    private void setFrame(){
        CenterFrame cf = new CenterFrame();
        Dimension resolution = cf.center();
        int width = resolution.width;
        int height = resolution.height;

        frame.setBounds(width / 2 - 300, height / 2 - 150, 700, 300);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setComponents(){
        nameLabel.setBounds(400, 20, 80,30);
        accountLabel.setBounds(400, 80, 80,30);
        passwordLabel.setBounds(400, 140, 80,30);

        boxName.setBounds(480, 20, 150, 30);
        boxAccount.setBounds(480, 80, 150, 30);
        boxPassword.setBounds(480, 140, 150, 30);

        updateButton.setBounds(465, 200, 100, 30);
        updateButton.setFocusable(false);
        updateButton.addActionListener(this);
        updateButton.setBackground(backgroundColorTF);
        updateButton.setForeground(foregroundColor);
        updateButton.setBorder(margin);
        updateButton.setFont(new Font("Segoe UI", Font.BOLD, 14));

        //PasswordGenerator button
        passwordGenerator.setBounds(640, 140, 30, 30);
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
    }

    //Add components to arraylist
    public void addComponents(){
        labelArrayList.add(nameLabel);
        labelArrayList.add(accountLabel);
        labelArrayList.add(passwordLabel);
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

    public void showTable(){
        MyConnection connection = new MyConnection();
        String query = "select * from registros";
        String [] data = new String[4];

        table.setTabla(20, 30, 350, 200);
        table.setHeadboard(titleColumnName, titleColumnAccount, titleColumnPassword);
        try {
            st = connection.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            model = table.getModel();

            while(rs.next()){
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);
                data[3] = rs.getString(4);
                model.addRow(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JTable myTable = table.getTabla();
        myTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            //Esto ocurre cuando una fila es seleccionada
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (myTable.getSelectedRow() != -1){
                    int row = myTable.getSelectedRow(); //Guarda el numero de fila seleccionada
                    id = myTable.getValueAt(row, 0).toString();
                    boxName.setText(myTable.getValueAt(row, 1).toString());
                    boxAccount.setText(myTable.getValueAt(row, 2).toString());
                    boxPassword.setText(myTable.getValueAt(row, 3).toString());

                    System.out.println(id);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Update") || e.getActionCommand().equals("Actualizar")){
            if(!boxName.getText().equals("") &&
                    !boxAccount.getText().equals("") &&
                    !boxPassword.getText().equals("")
            ){
                String[] data = {boxName.getText(),
                        boxAccount.getText(),
                        boxPassword.getText(),
                        id};
                Query sql = new Query();
                int control = sql.updateRecord(data);
                if (control != 0){
                    JOptionPane.showMessageDialog(frame,
                            "The record has been update successfully",
                            "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    frame.setVisible(false);
                }
            }
        }
    }

    //Variables
    private JFrame frame;
    private BackgroundPanel panel;
    private JPanel jPanel;
    private JLabel nameLabel;
    private JLabel accountLabel;
    private JLabel passwordLabel;
    private ArrayList<JLabel> labelArrayList = new ArrayList<>(3);
    private JTextField boxName = new JTextField(45);
    private JTextField boxAccount = new JTextField(45);
    private JTextField boxPassword = new JTextField(8);
    private ArrayList<JTextField> textFieldArrayList = new ArrayList<>(3);
    private JButton updateButton;
    private JButton passwordGenerator;
    private DefaultTableModel model;
    private String titleColumnName;     //Nombre de la columna de la tabla
    private String titleColumnAccount;      //Nombre de la columna de la tabla
    private String titleColumnPassword;     //Nombre de la columna de la tabla
    Table table;
    Statement st;
    String id;
    private Border margin;
    private Color foregroundColor;
    private Color backgroundColorTF;
    private Color backgroundColor;
    private ImageIcon imageGenerator = new ImageIcon(getClass().getResource("/Images/generator.png"));
    private ImageIcon imageGeneratorPressed = new ImageIcon(getClass().getResource("/Images/generatorPressed.png"));
}
