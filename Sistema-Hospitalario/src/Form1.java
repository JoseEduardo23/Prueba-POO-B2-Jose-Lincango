import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Form1 {
    public JTextField CUser;
    private JPasswordField Cpassword;
    private JButton button1;
    public JPanel MainPanel1;

    public Form1() throws SQLException, RuntimeException {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/shospitalario";
                String user = "root";
                String password = "j.eduardo23";
                String query = "SELECT * FROM USUARIO WHERE username = ? AND password = ?";

                String Usuario = CUser.getText();
                String Password = new String(Cpassword.getPassword());

                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    System.out.println("Conectado a la base de datos");

                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, Usuario);
                    statement.setString(2, Password);

                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(null, "Usuario y Password Correcto");


                        Form2 form2 = new Form2();
                        form2.setVisible(true);
                        form2.setSize(800, 600);
                        form2.setPreferredSize(new Dimension(650, 400));
                        form2.pack();
                        form2.setLocationRelativeTo(null);


                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o Password Incorrecto");

                    }
                } catch (SQLException exception) {
                    throw new RuntimeException(exception);
                }
            }
        });
    }

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("Form1");
        frame.setContentPane(new Form1().MainPanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setPreferredSize(new Dimension(500, 300));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}

