import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Form2 extends JFrame {
    private JTextField CCI;
    private JTextField CNR;
    private JTextField CNom;
    private JTextField CApel;
    private JTextField CTel;
    private JTextField CEd;
    private JTextField CDes;
    private JButton REGISTRARButton;
    private JButton siguienteButton;
    private JPanel MainPanel2;

    public Form2() {
        setTitle("REGISTRO DE PACIENTES");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(MainPanel2);
        pack();
        REGISTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/shospitalario";
                String user = "root";
                String password = "j.eduardo23";

                String cedula = CCI.getText();
                String Nregistro = CNR.getText();
                String nombre = CNom.getText();
                String apellido = CApel.getText();
                String Telf = CTel.getText();
                String Edad = CEd.getText();
                String Des = CDes.getText();

                String sql = "INSERT INTO PACIENTE (cedula, n_historial_clinico, nombre, apellido, telefono, edad, descripcion_enfermedad) VALUES (?, ?, ?, ?, ?, ?, ?)";

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    try (Connection connection = DriverManager.getConnection(url, user, password)) {
                        PreparedStatement cadenaPreparada = connection.prepareStatement(sql);

                        cadenaPreparada.setString(1, cedula);
                        cadenaPreparada.setString(2, Nregistro);
                        cadenaPreparada.setString(3, nombre);
                        cadenaPreparada.setString(4, apellido);
                        cadenaPreparada.setString(5, Telf);
                        cadenaPreparada.setString(6, Edad);
                        cadenaPreparada.setString(7, Des);

                        int filasInsertadas = cadenaPreparada.executeUpdate();
                        if (filasInsertadas > 0) {
                            System.out.println("Se ha insertado correctamente el paciente.");
                            JOptionPane.showMessageDialog(null, "DATOS INGRESADOS", null, JOptionPane.WARNING_MESSAGE);
                        } else {
                            System.out.println("No se ha podido insertar el paciente.");
                        }
                    }
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Form2");
        frame.setContentPane(new Form2().MainPanel2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}