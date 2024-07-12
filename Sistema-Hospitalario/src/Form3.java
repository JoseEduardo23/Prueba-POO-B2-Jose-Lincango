import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

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
    private JButton regresarButton;

    public Form3(){
        setTitle("REGISTRO DE PACIENTES");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(MainPanel3);
        pack();
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/shospitalario";
                String user = "root";
                String password = "j.eduardo23";

                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    System.out.println("Conectado a la base de datos");

                    String query = "SELECT * FROM PACIENTE WHERE cedula = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, BCI.getText());
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        String Numero_registro = resultSet.getString("n_historial_clinico");
                        String nombre = resultSet.getString("nombre");
                        String apellido = resultSet.getString("apellido");
                        String telefono = resultSet.getString("telefono");
                        String edad = resultSet.getString("edad");
                        String Descripcion = resultSet.getString("descripcion_enfermedad");

                        TNR.setText(Numero_registro);
                        TNM.setText(nombre);
                        TAp.setText(String.valueOf(apellido));
                        Ttel.setText(String.valueOf(telefono));
                        TEd.setText(String.valueOf(edad));
                        Tdes.setText(Descripcion);
                    } else {
                        JOptionPane.showMessageDialog(null, "PACIENTE NO ENCONTRADO", null, JOptionPane.WARNING_MESSAGE);
                        TNM.setText("");
                        TAp.setText("");
                        Ttel.setText("");
                        TEd.setText("");
                        Tdes.setText("");
                    }
                } catch (SQLException e1) {
                    System.out.println(e1);
                    TNM.setText("");
                    TAp.setText("");
                    Ttel.setText("");
                    TEd.setText("");
                    Tdes.setText("");
                }
            }
        });
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Form2 form2 = new Form2();
                form2.setVisible(true);
                form2.setSize(800, 600);
                form2.setPreferredSize(new Dimension(650, 400));
                form2.pack();
                form2.setLocationRelativeTo(null);
                dispose();
            }
        });
    }
}
