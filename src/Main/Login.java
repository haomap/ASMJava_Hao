package Main;

import Model.User;
//import demo.FirstGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{
    private JPasswordField txtPass;
    private JTextField txtUsr;
    private JButton bntLogin;
    private JButton bntCancel;
    private JPanel mainPanel;

    public Login() {
        super("Clothes Shop");
        this.setContentPane(mainPanel);
        this.setLocationRelativeTo(null);
        this.pack();
        bntLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        bntCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
    }

    private void cancel() {
        int re = JOptionPane.showConfirmDialog(this,"Do you want exit?","Exit",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);

        if(re == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }

    private void showMess(String mess) {
        JOptionPane.showMessageDialog(this,mess);

    }

    private void login() {
        String name = txtUsr.getText();
        String pass = String.valueOf(txtPass.getPassword());
        Manager r = null;
        User admin = new User("admin", "123");
        User checkUser = new User(name,pass);

        boolean login = false;

        if (admin.equals(checkUser)){
            r = new Manager(name, this);
            login = true;
        }
        if (login){
            r.setVisible(true);
            this.setVisible(false);
        }else{
            showMess("Login failed");
        }
    }

    public static void main(String[] args) {
        JFrame f = new Login();
        f.setVisible(true);
    }

}
