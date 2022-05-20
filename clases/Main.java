package clases;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        start();
    }

    public static void start(){
        LoginPanel login = new LoginPanel();
        login.startEnglishBackgroundStyle(image, backgroundColorButton, foregroundColor, margin);
    }

    //Recibe un entero, 0 si es falso 1 si es verdadero
    public void connection(int numberComparation) throws SQLException{

        if(numberComparation == 1){
            String url = "jdbc:mysql://localhost:3306/gestion_contrasenias";    //Falla
            String password = "riverplate20010304";
            String user = "root";
            MyConnection c = new MyConnection(user, password, url);
            startInterface(1, 0);    //Always start in english with background image
        }else{
            start();
        }
    }

    public static void startInterface(int language, int backgroundColor) {
        Interface i = new Interface(backgroundColorButton, foregroundColor, margin);
        if (language == 0) {
            switch (backgroundColor){
                case 0:
                    i.startSpanishBackgroundStyle(image);
                    break;
                case 1:
                    i.startSpanish(black);
                    break;
                case 2:
                    i.startSpanish(grey);
            }
        } else if (language == 1) {
            switch (backgroundColor){
                case 0:
                    i.startEnglishBackgroundStyle(image);
                    break;
                case 1:
                    i.startEnglish(black);
                    break;
                case 2:
                    i.startEnglish(grey);
            }
        }
    }

    //Convert color
    private static int hex(String color_hex) { return Integer.parseInt(color_hex, 16); }

    //Variables
    private static final ImageIcon image = new ImageIcon(Main.class.getResource("/Images/cool-background.png"));
    private static final Color backgroundColorButton = new Color(hex("515151"));
    private static final Color foregroundColor = new Color(hex("ffffff"));
    private static final Border margin = new EmptyBorder(5, 15, 5, 15);
    private static final Color black = new Color(hex("000000"));
    private static final Color grey = new Color(hex("7B7B7B"));
}



