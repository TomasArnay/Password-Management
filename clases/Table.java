package clases;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Table {
    public Table(JPanel panel){
        this.panel = panel;
        model = new DefaultTableModel();
    }

    public void setTabla(int x, int y, int width, int height){
        table = new JTable();

        //Table attributes
        table.setFocusable(false);
        table.setIntercellSpacing(dimension);
        table.setRowHeight(25);
        table.setSelectionBackground(selectionBackground);
        table.setSelectionForeground(selectionForeground);
        table.setShowVerticalLines(false);
        table.getTableHeader().setFont( new Font("Segue UI", Font.BOLD, 12));
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(headerBackground);
        table.getTableHeader().setForeground(headerForeground);
        table.setRowHeight(25);

        setBoundsTable(x, y, width, height);
    }

    public void setHeadboard(String name, String account, String pass){
        model.addColumn("ID");
        model.addColumn(name);
        model.addColumn(account);
        model.addColumn(pass);

        table.setModel(model);
    }

    private void setBoundsTable(int x, int y, int width, int height){
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(x, y, width, height);
        panel.add(scrollPane);
        scrollPane.setViewportView(table);
    }

    //Convert color
    private int hex(String color_hex) {
        return Integer.parseInt(color_hex, 16);
    }

    public JTable getTabla(){ return this.table; }

    public DefaultTableModel getModel(){
        return this.model;
    }

    //Variables
    private JPanel panel;
    private JTable table;
    private DefaultTableModel model;
    private Dimension dimension = new Dimension(0,0);
    private Color selectionBackground = new Color(hex("515151"));
    private Color selectionForeground = new Color(hex("ffffff"));
    private Color headerBackground = new Color(hex("000000"));
    private Color headerForeground = new Color(hex("ffffff"));
}
