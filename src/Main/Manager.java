package Main;


import Lib.XFile;
import Model.Clothes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Manager extends JFrame {
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtYear;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton sortByNameButton;
    private JTable tbClothes;
    private JComboBox cbType;
    private JComboBox cbBrand;
    private JPanel mainPanel;
    private JTextField txtQuantity;

    JFrame frontScreen;
    DefaultTableModel tbModel;
    DefaultComboBoxModel cbModel = new DefaultComboBoxModel();
    DefaultComboBoxModel cbTrademark = new DefaultComboBoxModel();
    ArrayList<Clothes> canList;
    String filePath = "./src/Data/manage.dat";
    int currentRow = -1;
    public Manager(String title, Login aThis){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        frontScreen = aThis;
        initTable();
        loadCb();
        tbClothes.setDefaultEditor(Object.class, null);

        //load data file(in your project)
        canList = new ArrayList<>();
        boolean file = loadFile();
        if(file){
            fillToTable();
        }else{
            showMess("Nothing to show");
        }
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {addList();}
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {updateList();}
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {deleteList();}
        });
        tbClothes.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                currentRow = tbClothes.getSelectedRow();
                showDetail(currentRow);
            }
        });
        sortByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sort();
                fillToTable();
            }
        });
    }
    private void sort() {
        Collections.sort(canList, new Comparator<Clothes>() {
            @Override
            public int compare(Clothes o1, Clothes o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    private void deleteList() {
        //1.delete a candidate to arraylist
        deleteManager();
        //2.fill to table (renew table)
        fillToTable();
        //3.Save arrayList candidate
        saveFile();
    }

    private void deleteManager() {
        int re =JOptionPane.showConfirmDialog(this, " +" +
                "Do you want to delete this one?", "Delete Warning",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        if(re == JOptionPane.YES_OPTION)
            canList.remove(currentRow);
        resetForm();
    }
    private void resetForm(){
        txtId.setText("");
        txtName.setText("");
        txtQuantity.setText("");
        txtYear.setText("");
        cbType.setSelectedIndex(0);
        cbBrand.setSelectedIndex(0);
    }

    private void addList() {
        //1.update a candidate to arraylist
        addManager();
        //2.fill to table (renew table)
        fillToTable();
        //3.Save arrayList candidate
        saveFile();
    }

    private void addManager() {
        String id = txtId.getText();
        String name = txtName.getText();
        if (id.isEmpty() || name.isEmpty() ) {
            showMess("Please check your inputs");
            return ;
        }
        String type = cbType.getSelectedItem().toString();
        String brand = cbBrand.getSelectedItem().toString();
        String year = txtYear.getText();
        int quantity = Integer.parseInt(txtQuantity.getText().toString());
        Clothes c = new Clothes(id, name, type, brand, year, quantity);
        canList.add(c);


    }

    private void updateList() {
        //1.update a candidate to arraylist
        updateManager();
        //2.fill to table (renew table)
        fillToTable();
        //3.Save arrayList candidate
        saveFile();
    }

    private void updateManager() {
        Clothes c = canList.get(currentRow);
        String id = txtId.getText();
        c.setID(id);
        String name = txtName.getText();
        c.setName(name);
        String type = cbType.getSelectedItem().toString();
        c.setType(type);
        String brand = cbBrand.getSelectedItem().toString();
        c.setBrand(brand);
        String year = txtYear.getText();
        int quantity = Integer.parseInt(txtQuantity.getText());
        c.setQuantity(quantity);
    }

    private void showDetail(int currentRow){
        Clothes c = canList.get(currentRow);
        String id = c.getID();
        txtId.setText(id);
        String name = c.getName();
        txtName.setText(name);
        String type = c.getType();
        cbType.setSelectedItem(type);
        String year = c.getYear();
        txtYear.setText(year);
        int quantity = c.getQuantity();
        txtQuantity.setText(String.valueOf(quantity));
    }
    private void fillToTable(){
        //Clear old date in the table
        tbModel.setRowCount(0);
        for(Clothes c : canList){
            Object[] row = new Object[]{
                    c.getID(), c.getName(), c.getType(), c.getBrand(), c.getYear(), c.getQuantity()
            };
            tbModel.addRow(row);
        }
    }

    private boolean loadFile() {
        if(XFile.readObject(filePath)==null){
            return false;
        }
        canList = (ArrayList<Clothes>) XFile.readObject(filePath);
        return true;
    }
    private void loadCb() {
        String[] typeLst = {"Choose your clothes","T-shirt","Shorts","Jeans"};
        for(String s:typeLst){
            cbModel.addElement(s);
        }
        cbType.setModel(cbModel);

        String[] brandLst = {"Choose your brand", "Gucci","Louis vuitton","Prada","Chanel"};
        for (String h:brandLst){
            cbTrademark.addElement(h);
        }
        cbBrand.setModel(cbTrademark);
    }
    private void saveFile() {
        XFile.writeObject(filePath, canList);
    }
    private void initTable() {
        String[] columnNames ={"ID","Name","Type","Brand","Public year","Quantity"};
        tbModel = new DefaultTableModel(columnNames,0);
        tbClothes.setModel(tbModel);

    }


    private void showMess(String mess) {
        JOptionPane.showMessageDialog(Manager.this,mess);
    }
//    public static void main(String[] args) {
//        Manager c = new Manager("Manager clothes shop");
//        c.setVisible(true);
//        c.setLocationRelativeTo(null);
//    }
}
