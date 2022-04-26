package com.company;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {

    private ImageIcon imagen;

    @Override
    public void setLayout(LayoutManager mgr) {
        super.setLayout(null);
    }

    public BackgroundPanel(ImageIcon imagenInicial){
        if (imagenInicial != null){
            imagen = imagenInicial;
        }
    }

    public void paint(Graphics g){
        if (imagen != null){
            g.drawImage(imagen.getImage(), 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
        }else {
            setOpaque(true);
        }

        super.paint(g);
    }
}