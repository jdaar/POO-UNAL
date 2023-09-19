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

public class ejercicio_tres {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Cual es mayor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 150);

        GridLayout layout = new GridLayout(3, 1);
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

        JButton subir_formulario_button = new JButton("Cual es mayor?");
        subir_formulario_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double a = Double.parseDouble(a_input.getText());
                double b = Double.parseDouble(b_input.getText());
                JOptionPane.showMessageDialog(null, a < b ? "B es mayor que A" : "A es mayor que B");
            }
        });

        content_pane.add(a_field);
        content_pane.add(b_field);
        content_pane.add(subir_formulario_button);
        frame.setVisible(true);
    }
}

