package entregable_tres;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ejercicio_seis {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Resolver ecuaciones de segundo grado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 200);

        GridLayout layout = new GridLayout(4, 1);
        GridLayout layout_field = new GridLayout(1, 2);
        Container content_pane = frame.getContentPane();
        content_pane.setLayout(layout);


        JPanel a_field = new JPanel(layout_field);
        JLabel a_label = new JLabel("Valor de A");
        JTextField a_input = new JTextField(126);

        a_field.add(a_label);
        a_field.add(a_input);

        JPanel b_field = new JPanel(layout_field);
        JLabel b_label = new JLabel("Valor de B");
        JTextField b_input = new JTextField(126);

        b_field.add(b_label);
        b_field.add(b_input);

        JPanel c_field = new JPanel(layout_field);
        JLabel c_label = new JLabel("Valor de C");
        JTextField c_input = new JTextField(126);

        c_field.add(c_label);
        c_field.add(c_input);

        JButton subir_formulario_button = new JButton("Resolver ecuacion");
        subir_formulario_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double a = Double.parseDouble(a_input.getText());
                double b = Double.parseDouble(b_input.getText());
                double c = Double.parseDouble(c_input.getText());
                double discriminante = Math.pow(b, 2) - 4 * a * c;

                String output = "";
                if (discriminante < 0) {
                    output = String.format("La ecuacion %.2fx^2+%.2fx+%.2f = 0 no tiene soluciones", a, b, c);
                } else {
                    double solucion_1 = (-1 * b + Math.sqrt(discriminante)) / (2 * a);
                    double solucion_2 = (-1 * b - Math.sqrt(discriminante)) / (2 * a);
                    output = String.format("Las soluciones de la ecuacion %.2fx^2+%.2fx+%.2f = 0 son x=%.2f, x=%.2f", a, b, c, solucion_1, solucion_2);
                }
                JOptionPane.showMessageDialog(null, output);
            }
        });

        content_pane.add(a_field);
        content_pane.add(b_field);
        content_pane.add(c_field);
        content_pane.add(subir_formulario_button);
        frame.setVisible(true);
    }
}
