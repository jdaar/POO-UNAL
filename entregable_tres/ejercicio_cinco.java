package entregable_tres;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ejercicio_cinco {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calcular salario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 200);

        GridLayout layout = new GridLayout(4, 1);
        GridLayout layout_field = new GridLayout(1, 2);
        Container content_pane = frame.getContentPane();
        content_pane.setLayout(layout);


        JPanel nombres_field = new JPanel(layout_field);
        JLabel nombres_label = new JLabel("Nombres del empleado");
        JTextField nombres_input = new JTextField(126);

        nombres_field.add(nombres_label);
        nombres_field.add(nombres_input);

        JPanel salario_field = new JPanel(layout_field);
        JLabel salario_label = new JLabel("Salario por hora del empleado");
        JTextField salario_input = new JTextField(126);

        salario_field.add(salario_label);
        salario_field.add(salario_input);

        JPanel horas_field = new JPanel(layout_field);
        JLabel horas_label = new JLabel("Horas trabajadas por el empleado");
        JTextField horas_input = new JTextField(126);

        horas_field.add(horas_label);
        horas_field.add(horas_input);

        JButton subir_formulario_button = new JButton("Calcular salario");
        subir_formulario_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombres_input.getText();
                BigDecimal salario_hora = new BigDecimal(salario_input.getText());
                BigDecimal horas = new BigDecimal(horas_input.getText());
                JOptionPane.showMessageDialog(null, 
                    String.format(salario_hora.multiply(horas).compareTo(BigDecimal.valueOf(450000)) > 0 ? "Nombre del empleado: %s. Salario del empleado: %s\n" : "Nombre del empleado: %s\n", nombre, salario_hora.multiply(horas).toString())
                );
            }
        });

        content_pane.add(nombres_field);
        content_pane.add(salario_field);
        content_pane.add(horas_field);
        content_pane.add(subir_formulario_button);
        frame.setVisible(true);
    }
}
