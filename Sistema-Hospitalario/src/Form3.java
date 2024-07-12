import javax.swing.*;

public class Form3 extends JFrame {

    public JPanel MainPanel3;
    private JTextField BCI;
    private JTextField Ttel;
    private JTextField TEd;
    private JTextField Tdes;
    private JTextField TNR;
    private JTextField TNM;
    private JTextField TAp;
    private JButton buscarButton;

    public Form3(){
        setTitle("REGISTRO DE PACIENTES");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(MainPanel3);
        pack();
    }
}
