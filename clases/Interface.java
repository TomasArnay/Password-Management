package clases;

import com.company.BackgroundPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Interface implements ActionListener{
    public Interface(Color backgroundColorButton, Color foregroundColor, Border margin){
        this.backgroundColorButton = backgroundColorButton;
        this.foregroundColor = foregroundColor;
        this.margin = margin;
    }

    public void startSpanish(Color backgroundColor){
        flagBackground = false;
        flagLanguage = false;
        this.backgroundColor = backgroundColor;
        frame = new JFrame("Gestion de Contraseñas");
        newPass = new JButton("Agregar Contraseña");
        updatePass = new JButton("Actualizar Contraseña");
        deletePass = new JButton("Borrar Contraseña");
        jPanel = new JPanel(null);
        table = new Table(jPanel);
        titleColumnName = "Nombre";
        titleColumnAccount = "Cuenta";
        titleColumnPassword = "Contraseña";
        addComponentsPanelSolid(backgroundColor);

        setComponents();
        showTable();
        setFrame();
    }

    public void startSpanishBackgroundStyle(ImageIcon backgroundStyle){
        flagBackground = true;
        flagLanguage = false;
        this.image = backgroundStyle;
        frame = new JFrame("Gestion de Contraseñas");
        newPass = new JButton("Agregar Contraseña");
        updatePass = new JButton("Actualizar Contraseña");
        deletePass = new JButton("Borrar Contraseña");
        panelStyle = new BackgroundPanel(image);
        table = new Table(panelStyle);
        titleColumnName = "Nombre";
        titleColumnAccount = "Cuenta";
        titleColumnPassword = "Contraseña";

        setComponents();
        addComponentsToFrame();
        showTable();
        setFrame();
    }

    public void startEnglish(Color backgroundColor){
        flagBackground = false;
        flagLanguage = true;
        this.backgroundColor = backgroundColor;
        frame = new JFrame("Password Management");
        newPass = new JButton("Add Password");
        updatePass = new JButton("Update Password");
        deletePass = new JButton("Delete Password");
        jPanel = new JPanel(null);
        table = new Table(jPanel);
        titleColumnName = "Name";
        titleColumnAccount = "Account";
        titleColumnPassword = "Password";
        addComponentsPanelSolid(backgroundColor);

        setComponents();
        showTable();
        setFrame();
    }

    public void startEnglishBackgroundStyle(ImageIcon backgroundStyle){
        flagBackground = true;
        flagLanguage = true;
        this.image = backgroundStyle;
        frame = new JFrame("Password Management");
        newPass = new JButton("Add Password");
        updatePass = new JButton("Update Password");
        deletePass = new JButton("Delete Password");
        panelStyle = new BackgroundPanel(image);
        titleColumnName = "Name";
        titleColumnAccount = "Account";
        titleColumnPassword = "Password";
        table = new Table(panelStyle);

        setComponents();
        addComponentsToFrame();
        showTable();
        setFrame();
    }

    //Metodo que cambia el tamanio de una imagen

    //Setea el color del panel y agrega todo al frame
    private void addComponentsPanelSolid(Color backgroundColor){
        jPanel.setBackground(backgroundColor);
        setBoundsComponents();
        jPanel.add(newPass);
        jPanel.add(updatePass);
        jPanel.add(deletePass);
        jPanel.add(settings);
        jPanel.add(refresh);

        frame.add(jPanel);
    }

    //Agrega los componentes del fondo con estilo al frame
    private void addComponentsToFrame(){
        setBoundsComponents();
        panelStyle.add(newPass);
        panelStyle.add(updatePass);
        panelStyle.add(deletePass);
        panelStyle.add(settings);
        panelStyle.add(refresh);

        frame.add(panelStyle);
    }

    public void setFrame(){
        CenterFrame cf = new CenterFrame();
        Dimension resolution = cf.center();
        int width = resolution.width;
        int height = resolution.height;

        frame.setBounds(width / 2 - 300, height / 2 - 150, x, y);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setComponents(){
        al_b.add(newPass);
        al_b.add(updatePass);
        al_b.add(deletePass);

        for(int i = 0; i < al_b.size(); i++){
            al_b.get(i).setFocusable(false);
            al_b.get(i).addActionListener(this);
            al_b.get(i).setBackground(backgroundColorButton);
            al_b.get(i).setForeground(foregroundColor);
            al_b.get(i).setBorder(margin);
            al_b.get(i).setFont(new Font("Segoe UI", Font.BOLD, 14));
        }

        //Settings button
        settings.setIcon(imageSettings);
        settings.setPressedIcon(imageSettingsPressed);
        settings.setFocusable(false);
        settings.setBorder(margin);
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flagBackground){
                    Settings s = new Settings(image, backgroundColorButton, foregroundColor, margin);
                }else{
                    Settings s = new Settings(backgroundColor, backgroundColorButton, foregroundColor, margin);
                }
                frame.setVisible(false);
                //Resolver bug cuando se cierra la ventana de settings
            }
        });

        //Refresh button
        refresh.setIcon(imageRefreshPressed);
        refresh.setPressedIcon(icon);
        refresh.setFocusable(false);
        refresh.setBorder(margin);
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRecordsTable();
                showTable();
            }
        });
    }

    private void setBoundsComponents(){
        newPass.setBounds(450, 60, 200, 30);
        updatePass.setBounds(450, 120, 200, 30);
        deletePass.setBounds(450, 180, 200, 30);

        refresh.setBounds(590, 5, 40, 40);
        settings.setBounds(630, 0, 50, 50);
    }

    public void showTable(){
        MyConnection connection = new MyConnection();
        String query = "select * from registros";
        String [] data = new String[4];

        table.setTabla(20, 30, 380, 200);
        table.setHeadboard(titleColumnName, titleColumnAccount, titleColumnPassword);
        try {
            st = connection.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            DefaultTableModel model = table.getModel();

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
    }

    public void deleteRecordsTable(){
        table.getModel().setColumnCount(0);
        table.getModel().setRowCount(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Password") || e.getActionCommand().equals("Agregar Contraseña")){
            PanelAdd panelAdd = new PanelAdd();
            if (flagBackground){
                if (flagLanguage){
                    panelAdd.startEnglishBackgroundStyle(image, backgroundColorButton, foregroundColor, margin);
                }else{
                    panelAdd.startSpanishBackgroundStyle(image, backgroundColorButton, foregroundColor, margin);
                }
            }else{
                if (flagLanguage){
                    panelAdd.startEnglish(backgroundColor, backgroundColorButton, foregroundColor, margin);
                }else{
                    panelAdd.startSpanish(backgroundColor, backgroundColorButton, foregroundColor, margin);
                }
            }
        }
        if (e.getActionCommand().equals("Update Password") || e.getActionCommand().equals("Actualizar Contraseña")){
            PanelUpdate panelUpdate = new PanelUpdate();
            if (flagBackground){
                if (flagLanguage){
                    panelUpdate.startEnglishBackgroundStyle(image, backgroundColorButton, foregroundColor, margin);
                }else {
                    panelUpdate.startSpanishBackgroundStyle(image, backgroundColorButton, foregroundColor, margin);
                }
            }else{
                if (flagLanguage){
                    panelUpdate.startEnglish(backgroundColor, backgroundColorButton, foregroundColor, margin);
                }else {
                    panelUpdate.startSpanish(backgroundColor, backgroundColorButton, foregroundColor, margin);
                }
            }
        }
        if (e.getActionCommand().equals("Delete Password") || e.getActionCommand().equals("Borrar Contraseña")){
            PanelDelete panelDelete = new PanelDelete();
            if (flagBackground){
                if (flagLanguage){
                    panelDelete.startEnglishBackgroundStyle(image, backgroundColorButton, foregroundColor, margin);
                }else{
                    panelDelete.startSpanishBackgroundStyle(image, backgroundColorButton, foregroundColor, margin);
                }
            }else{
                if (flagLanguage){
                    panelDelete.startEnglish(backgroundColor, backgroundColorButton, foregroundColor, margin);
                }else{
                    panelDelete.startSpanish(backgroundColor, backgroundColorButton, foregroundColor, margin);
                }
            }
        }
    }

    //Variables
    private JFrame frame;
    private ImageIcon image;
    private BackgroundPanel panelStyle;  //Panel con estilo
    private JPanel jPanel;
    private JButton newPass;
    private JButton updatePass;
    private JButton deletePass;
    private JButton settings = new JButton();
    private JButton refresh = new JButton();
    int x = 700;
    int y = 300;
    Table table;
    Statement st;
    private ArrayList<JButton> al_b = new ArrayList<>();
    private Color backgroundColorButton;
    private Color foregroundColor;
    private Color backgroundColor;
    private Border margin;
    private String titleColumnName;     //Nombre de la columna de la tabla
    private String titleColumnAccount;      //Nombre de la columna de la tabla
    private String titleColumnPassword;     //Nombre de la columna de la tabla
    private ImageIcon imageSettings = new ImageIcon(getClass().getResource("/Images/settings.png"));
    private ImageIcon imageSettingsPressed = new ImageIcon(getClass().getResource("/Images/settingsPressed.png"));
    private ImageIcon imageRefreshPressed = new ImageIcon(getClass().getResource("/Images/reload_pressed.png"));

    private ImageIcon imageRefresh = new ImageIcon(getClass().getResource("/Images/reload.png"));
    Image scaleImage = imageRefresh.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);   //Convierto la imagen a la escala que tiene el boton
    ImageIcon icon = new ImageIcon(scaleImage); //Convierto a Image a tipo ImageIcon para que lo tome el boton

    private boolean flagBackground;     //Si es verdadero es un fondo con estilo, si es falso es un fondo solido
    private boolean flagLanguage;   //Si es verdadero es ingles, si es falso es español
}
