import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Se implementa el patron de diseño subscriber para que los componentes de ui de la aplicacion que dependan
// del estado de las notas puedan ser notificados cuando estas cambien y actualicen su estado en consecuencia.
interface Subscriber {
     void Notify();
}

interface Suscriptable {
     void registerSubscriber(Subscriber subscriber);
     void notifySubscribers();
}

// Se implementa la interfaz subscriptable para indicar que es posible suscribirse a los cambios de estado de esta clase
class Scores implements Suscriptable {
    public Integer max_score_quantity;
    private Double[] scores;    
    private ArrayList<Subscriber> subscribers;

    public Scores(Double[] scores) {
        this.scores = scores;
        this.subscribers = new ArrayList<Subscriber>();
        max_score_quantity = scores.length;
    }

    // Se definen getters y setters para la propiedad scores para poder obtener un control mas granular sobre
    // las operaciones que se pueden efectuar sobre las notas, ya que este tiene una logica asociada al proceso de renderizado
    public Double[] getScores() {
        return scores;
    }

    public void setScores(Double[] scores) {
        this.scores = scores;
        // Aqui se puede ver la dependencia que hay sobre caracterisiticas de este objeto en el proceso de renderizado
        // Esto es debido a que la cantidad de campos que se renderizan esta directamente relacionados con la propiedad
        // max_score_quantity, por lo que si esta cambia, se debe volver a renderizar el formulario
        resize_scores(scores.length);
        max_score_quantity = scores.length;
        notifySubscribers();
    }

    // Se implementan los metodos necesarios para implementar la interfaz suscriptable
    @Override
    public void registerSubscriber(Subscriber subscriber) {
        if (Arrays.stream(subscribers.toArray(new Subscriber[subscribers.size()])).filter((Subscriber sub) -> sub == subscriber).count() > 0) return;
        subscribers.add(subscriber);
    }

    // Notifica a todos los subscriptores sobre cambios en el objeto, sin compartir dtos sobre el cambio del estado, el componente
    // que necesite acceder al nuevo estado de la aplicacion para reaccionar al evento debe obtenerlo por si mismo en el metodo Notify
    @Override
    public void notifySubscribers() {
        Arrays.stream(subscribers.toArray(new Subscriber[subscribers.size()])).forEach((Subscriber sub) -> sub.Notify());
    }

    // Se implementa un metodo para redimensionar el arreglo de notas
    public void resize_scores(Integer new_size) {
        if (new_size < max_score_quantity) return;
        scores = Arrays.copyOf(scores, new_size);
        scores = Arrays.stream(scores).map((Double score) -> score == null ? 0.0 : score).toArray(Double[]::new);
        max_score_quantity = new_size;
    }

    // Desviacion estandar del arreglo de notas
    public Double std_dev() {
        Double avg = average();
        Optional<Double> distance_to_avg_sum = Arrays.stream(scores).reduce((Double acc, Double score) -> acc + Math.pow(score - avg, 2));
        if (distance_to_avg_sum.isPresent()) {
            return Math.sqrt(distance_to_avg_sum.get() / scores.length);
        } else {
            return 0.0;
        }
    }

    // Mayor nota del arreglo de notas
    public Optional<Double> highest_score() {
        return Arrays.stream(scores).reduce((Double acc, Double score) -> Math.max(acc, score));
    }

    // Nota mas baja del arreglo de notas
    public Optional<Double> lowest_score() {
        return Arrays.stream(scores).reduce((Double acc, Double score) -> Math.min(acc, score));
    }

    // Promedio del arreglo de notas
    public Double average() {
        Optional<Double> score_sum = Arrays.stream(scores).reduce((Double acc, Double score) -> acc + score);
        if (score_sum.isPresent()) {
            return score_sum.get() / scores.length;
        } else {
            return 0.0;
        }
    }
}

class ScoreMetrics extends JPanel implements Subscriber {
    private Scores scores;
    private GridBagLayout layout;
    
    public ScoreMetrics(Scores scores) {
        this.scores = scores;
        this.scores.registerSubscriber(this);

        layout = new GridBagLayout();

        setLayout(layout);

        initializeComponents();
    }

    public void initializeComponents() {
        // Limpiar los componentes del formulario
        Arrays.stream(getComponents()).forEach((Component component) -> component.setVisible(false));
        Arrays.stream(getComponents()).forEach((Component component) -> {
            remove(component);
            revalidate();
            repaint();
        });

        // Renderizar los componentes del formulario
        JPanel metrics_panel = new JPanel(new GridLayout(0, 1));

        metrics_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Metricas"));
        metrics_panel.add(new javax.swing.JLabel("Promedio: " + scores.average()));
        metrics_panel.add(new javax.swing.JLabel("Desviacion estandar: " + scores.std_dev()));
        metrics_panel.add(new javax.swing.JLabel("Nota mas alta: " + scores.highest_score().orElse(0.0)));
        metrics_panel.add(new javax.swing.JLabel("Nota mas baja: " + scores.lowest_score().orElse(0.0)));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        add(metrics_panel, constraints);
    }

    
    @Override
    public void Notify() {
        initializeComponents();
    }
}

class ScoreForm extends JPanel implements ActionListener {
    // Composicion de la clase ScoreForm con la clase Scores para poder acceder a los datos de las notas
    private Scores scores;

    // Almacena los paneles que contienen los campos de las notas
    // para poder eliminarlos y volver a renderizarlos
    private ArrayList<JPanel> score_panels;
    // Almacena los campos de las notas para poder acceder al valor de estos
    private ArrayList<JTextField> score_fields;
    // Almacena los botones del formulario para poder eliminarlos y volver a renderizarlos
    private ArrayList<JButton> buttons;

    private GridBagLayout layout;

    public ScoreForm(Scores scores) {
        this.scores = scores;
        this.score_fields = new ArrayList<JTextField>();
        this.score_panels = new ArrayList<JPanel>();
        this.buttons = new ArrayList<JButton>();

        layout = new GridBagLayout();

        setLayout(layout);

        initializeComponents();
    }

    public void initializeForm() {
        // Limpiar los componentes del formulario
        Arrays.stream(score_fields.toArray(new JTextField[score_fields.size()])).forEach((JTextField field) -> field.setVisible(false));
        Arrays.stream(score_fields.toArray(new JTextField[score_fields.size()])).forEach((JTextField field) -> {
            remove(field);
            revalidate();
            repaint();
        });
        Arrays.stream(score_panels.toArray(new JPanel[score_panels.size()])).forEach((JPanel panel) -> panel.setVisible(false));
        Arrays.stream(score_panels.toArray(new JPanel[score_panels.size()])).forEach((JPanel panel) -> {
            remove(panel);
            revalidate();
            repaint();
        });
        score_fields.clear();
        score_panels.clear();

        // Renderizar los componentes del formulario
        for (int i = 0; i < scores.max_score_quantity; i++) {
            JPanel form_field_panel = new JPanel(new BorderLayout());

            form_field_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Nota " + (i + 1)));
            JTextField score_field = new JTextField(scores.getScores()[i].toString());
            form_field_panel.add(score_field);
            score_fields.add(score_field);

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = i;
            constraints.weightx = 1.0;
            constraints.fill = GridBagConstraints.HORIZONTAL;

            add(form_field_panel, constraints);
            score_panels.add(form_field_panel);
        }
    }

    public void initializeButtons() {
        // Limpiar los componentes de los botones
        Arrays.stream(buttons.toArray(new JButton[buttons.size()])).forEach((JButton button) -> button.setVisible(false));
        Arrays.stream(buttons.toArray(new JButton[buttons.size()])).forEach((JButton button) -> {
            remove(button);
            revalidate();
            repaint();
        });
        buttons.clear();

        // Renderizar los componentes de los botones
        JPanel form_button_panel = new JPanel(new FlowLayout());

        JButton calculate_btn = new JButton("Calcular");
        calculate_btn.setActionCommand("calculate");
        calculate_btn.addActionListener(this);
        form_button_panel.add(calculate_btn);
        buttons.add(calculate_btn);

        JButton add_field_btn = new JButton("Añadir campo");
        add_field_btn.setActionCommand("add_field");
        add_field_btn.addActionListener(this);
        form_button_panel.add(add_field_btn);
        buttons.add(add_field_btn);
        
        JButton reset_fields_btn = new JButton("Resetear campos");
        reset_fields_btn.setActionCommand("reset_fields");
        reset_fields_btn.addActionListener(this);
        form_button_panel.add(reset_fields_btn);
        buttons.add(reset_fields_btn);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = scores.max_score_quantity;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        add(form_button_panel, constraints);
    }

    public void initializeComponents() {
        initializeForm();
        initializeButtons();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "add_field":
                this.scores.resize_scores(this.scores.max_score_quantity + 1);
                initializeComponents();
                break;
            case "calculate":
                Double[] scores = Arrays.stream(score_fields.toArray(new JTextField[score_fields.size()])).map((JTextField field) -> Double.parseDouble(field.getText())).toArray(Double[]::new);
                this.scores.setScores(scores);
                break;
            case "reset_fields":
                this.scores.setScores(Main.default_values);
                initializeComponents();
                break;
            default:
                break;
        }
    }
}

class MainWindow extends JFrame {
    private Scores scores;
    private FlowLayout layout;
    private ScoreForm score_form;
    private ScoreMetrics score_metrics;

    public MainWindow(Scores scores) {
        this.scores = scores;

        layout = new FlowLayout();
        
        setLayout(layout);
        setBounds(0, 0, 500, 500);

        initializeComponents();
    }

    public void initializeComponents() {
        // Componente de formulario
        score_form = new ScoreForm(scores);
        score_form.setBorder(javax.swing.BorderFactory.createTitledBorder("Formulario de notas"));

        // Componente de metricas de notas 
        score_metrics = new ScoreMetrics(scores);
        score_metrics.setBorder(javax.swing.BorderFactory.createTitledBorder("Metricas de notas"));
        
        add(score_form);
        add(score_metrics);
    }
}

class Main {
    private static MainWindow mainWindow;
    private static Scores scores;
    public static Double[] default_values = { 0.0 };

    public static void main(String[] args) {
        scores = new Scores(default_values);
        mainWindow = new MainWindow(scores);
        mainWindow.setVisible(true);
    }
}