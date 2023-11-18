import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

interface Shape {
    public Double calculate_volume();
    public Double calculate_surface();
}

// Se implementa el patron de diseño subscriber para que los componentes de ui de la aplicacion que dependan
// del estado de las notas puedan ser notificados cuando estas cambien y actualicen su estado en consecuencia.
interface Subscriber {
     void Notify();
}

interface Suscriptable {
     void registerSubscriber(Subscriber subscriber);
     void notifySubscribers();
}

class Cylinder implements Suscriptable, Shape {
    private Double radius;
    private Double height;
    private ArrayList<Subscriber> subscribers;

    public Cylinder(Double radius, Double height) {
        this.radius = radius;
        this.height = height;
        subscribers = new ArrayList<Subscriber>();
    }

    // Calcula el volumen de la forma
    @Override
    public Double calculate_volume() {
        return Math.PI * height * Math.pow(radius, 2.0);
    }

    // Calcula la superficie de la forma
    @Override
    public Double calculate_surface() {
        return (2.0 * Math.PI * radius * height) + (2.0 * Math.PI * Math.pow(radius, 2.0));
    }

    // Registra un suscriptor a la forma
    @Override
    public void registerSubscriber(Subscriber subscriber) {
        if (Arrays.stream(subscribers.toArray(new Subscriber[subscribers.size()])).filter((Subscriber sub) -> sub == subscriber).count() > 0) return;
        subscribers.add(subscriber);
    }

    // Notifica a todos los suscriptores de la forma que esta ha cambiado su estado
    @Override
    public void notifySubscribers() {
        Arrays.stream(subscribers.toArray(new Subscriber[subscribers.size()])).forEach((Subscriber sub) -> sub.Notify());
    }

    // Getters y setters de los atributos de la forma, se implementa este patron para que los componentes de UI
    // no puedan cambiar el estado directamente y que esta accion pueda ser delegada a la clase, que en consecuencia debe notificar
    // a los suscriptores de que ha cambiado su estado
    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
        notifySubscribers();
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
        notifySubscribers();
    }
}

class CylinderWindow extends JFrame implements Subscriber, ActionListener {
    private Cylinder cylinder;
    private JTextField radius_field;
    private JTextField height_field;
    private JLabel volume_label;
    private JLabel surface_label;
    private GridBagLayout layout;

    public CylinderWindow(Cylinder cylinder) {
        // Definimos el layout del contenedor de la ventana (creado en initializeComponents)
        layout = new GridBagLayout();
        this.cylinder = cylinder;

        // Nos registramos a la forma para que esta nos notifique cuando cambie su estado
        cylinder.registerSubscriber(this);

        // Delegamos el layout al container que se crea en initializeComponents para que este se expanda por toda la ventana
        setLayout(new BorderLayout());
        setBounds(0, 0, 500, 250);

        // Inicializamos los componentes de la ventana
        initializeComponents();
        // Inicializamos los valores de las metricas con el valor por defecto que tiene la forma
        this.Notify();
    }

    private void initializeComponents() {
        // Creamos un contenedor que tendra todos los componentes de la ventana
        JPanel container = new JPanel(layout);
        container.setBorder(javax.swing.BorderFactory.createTitledBorder("Cilindro"));

        // Contenedor de todo lo relacionado al input de los parametros de la forma
        JPanel form = new JPanel();

        form.setLayout(new GridBagLayout());

        JPanel radius_form_field_panel = new JPanel(new BorderLayout());
        radius_form_field_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Radio"));
        radius_field = new JTextField(cylinder.getRadius().toString());
        radius_form_field_panel.add(radius_field);

        JPanel height_form_field_panel = new JPanel(new BorderLayout());
        height_form_field_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Altura"));
        height_field = new JTextField(cylinder.getHeight().toString());
        height_form_field_panel.add(height_field);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        form.add(radius_form_field_panel, constraints);
        constraints.gridy += 1;
        form.add(height_form_field_panel, constraints);

        constraints.gridy = 0;
        container.add(form, constraints);

        // Contenedor para botones en caso de que se desee añadir multiples botones
        JPanel buttons = new JPanel();

        buttons.setLayout(new GridBagLayout());

        JButton calculate_button = new JButton("Calcular");
        calculate_button.setActionCommand("calculate");
        calculate_button.addActionListener(this);
        
        constraints.weighty = 0.8;
        constraints.gridy = 0;
        constraints.gridx = 0;
        buttons.add(calculate_button, constraints);

        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        container.add(buttons, constraints);

        // Contiene las metricas de la forma (volumen y superficie)
        JPanel metrics = new JPanel();
        metrics.setBorder(javax.swing.BorderFactory.createTitledBorder("Metricas"));

        metrics.setLayout(new GridBagLayout());

        volume_label = new JLabel("");
        surface_label = new JLabel("");

        constraints.gridy = 0;
        constraints.gridx = 0;
        metrics.add(volume_label, constraints);
        constraints.gridy += 1;
        metrics.add(surface_label, constraints);

        constraints.gridy = 2;
        container.add(metrics, constraints);
        add(container);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "calculate":
                // Actualizamos el estado de la forma con los valores que se encuentran en los campos de texto, 
                // no es necesario manejar ningun cambio en la UI ya que esto se hara desde Notify cuando la forma notifique
                // sobre el cambio en su estado
                cylinder.setRadius(Double.parseDouble(radius_field.getText()));
                cylinder.setHeight(Double.parseDouble(height_field.getText()));
                break;
            default:
                break;
        }
    }

    @Override
    public void Notify() {
        // Actualizamos el texto de las metricas con el valor que retorna la forma al calcularlas
        volume_label.setText("Volumen: " + cylinder.calculate_volume().toString());
        surface_label.setText("Superficie: " + cylinder.calculate_surface().toString());
    }

}

class Sphere implements Suscriptable, Shape {
    private Double radius;
    private ArrayList<Subscriber> subscribers;

    public Sphere(Double radius) {
        this.radius = radius;
        this.subscribers = new ArrayList<Subscriber>();
    }

    // Calcula el volumen de la forma
    @Override
    public Double calculate_volume() {
        return (4/3) * Math.PI * Math.pow(this.radius, 3.0);
    }

    // Calcula la superficie de la forma
    @Override
    public Double calculate_surface() {
        return 4.0 * Math.PI * Math.pow(this.radius, 2.0);
    }

    // Registra un suscriptor a la forma
    @Override
    public void registerSubscriber(Subscriber subscriber) {
        if (Arrays.stream(subscribers.toArray(new Subscriber[subscribers.size()])).filter((Subscriber sub) -> sub == subscriber).count() > 0) return;
        subscribers.add(subscriber);
    }

    // Notifica a todos los suscriptores de la forma que esta ha cambiado su estado
    @Override
    public void notifySubscribers() {
        Arrays.stream(subscribers.toArray(new Subscriber[subscribers.size()])).forEach((Subscriber sub) -> sub.Notify());
    }

    // Getters y setters de los atributos de la forma, se implementa este patron para que los componentes de UI
    // no puedan cambiar el estado directamente y que esta accion pueda ser delegada a la clase, que en consecuencia debe notificar
    // a los suscriptores de que ha cambiado su estado
    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
        notifySubscribers();
    }
}

class SphereWindow extends JFrame implements Subscriber, ActionListener {
    private Sphere sphere;
    private JTextField radius_field;
    private JLabel volume_label;
    private JLabel surface_label;
    private GridBagLayout layout;

    public SphereWindow(Sphere sphere) {
        // Definimos el layout del contenedor de la ventana (creado en initializeComponents)
        layout = new GridBagLayout();
        this.sphere = sphere;

        // Nos registramos a la forma para que esta nos notifique cuando cambie su estado
        sphere.registerSubscriber(this);

        // Delegamos el layout al container que se crea en initializeComponents para que este se expanda por toda la ventana
        setLayout(new BorderLayout());
        setBounds(0, 0, 500, 200);

        // Inicializamos los componentes de la ventana
        initializeComponents();
        // Inicializamos los valores de las metricas con el valor por defecto que tiene la forma
        this.Notify();
    }

    private void initializeComponents() {
        // Creamos un contenedor que tendra todos los componentes de la ventana
        JPanel container = new JPanel(layout);
        container.setBorder(javax.swing.BorderFactory.createTitledBorder("Esfera"));

        // Contenedor de todo lo relacionado al input de los parametros de la forma
        JPanel form = new JPanel();

        form.setLayout(new GridBagLayout());

        JPanel radius_form_field_panel = new JPanel(new BorderLayout());
        radius_form_field_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Radio"));
        radius_field = new JTextField(sphere.getRadius().toString());
        radius_form_field_panel.add(radius_field);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        form.add(radius_form_field_panel, constraints);

        constraints.gridy = 0;
        container.add(form, constraints);

        // Contenedor para botones en caso de que se desee añadir multiples botones
        JPanel buttons = new JPanel();

        buttons.setLayout(new GridBagLayout());

        JButton calculate_button = new JButton("Calcular");
        calculate_button.setActionCommand("calculate");
        calculate_button.addActionListener(this);
        
        constraints.weighty = 0.8;
        constraints.gridy = 0;
        constraints.gridx = 0;
        buttons.add(calculate_button, constraints);

        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        container.add(buttons, constraints);

        // Contiene las metricas de la forma (volumen y superficie)
        JPanel metrics = new JPanel();
        metrics.setBorder(javax.swing.BorderFactory.createTitledBorder("Metricas"));

        metrics.setLayout(new GridBagLayout());

        volume_label = new JLabel("");
        surface_label = new JLabel("");

        constraints.gridy = 0;
        constraints.gridx = 0;
        metrics.add(volume_label, constraints);
        constraints.gridy += 1;
        metrics.add(surface_label, constraints);

        constraints.gridy = 2;
        container.add(metrics, constraints);
        add(container);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "calculate":
                // Actualizamos el estado de la forma con los valores que se encuentran en los campos de texto, 
                // no es necesario manejar ningun cambio en la UI ya que esto se hara desde Notify cuando la forma notifique
                // sobre el cambio en su estado
                sphere.setRadius(Double.parseDouble(radius_field.getText()));
                break;
            default:
                break;
        }
    }

    @Override
    public void Notify() {
        // Actualizamos el texto de las metricas con el valor que retorna la forma al calcularlas
        volume_label.setText("Volumen: " + sphere.calculate_volume().toString());
        surface_label.setText("Superficie: " + sphere.calculate_surface().toString());
    }
}

class Pyramid implements Suscriptable, Shape {
    private Double base;
    private Double height;
    private Double apothem;
    private ArrayList<Subscriber> subscribers;

    public Pyramid(Double base, Double height, Double apothem) {
        this.base = base;
        this.height = height;
        this.apothem = apothem;
        this.subscribers = new ArrayList<Subscriber>();
    }

    // Calcula el volumen de la forma
    @Override
    public Double calculate_volume() {
        return (Math.pow(base, 2.0) * height) / 3.0;
    }

    // Calcula la superficie de la forma
    @Override
    public Double calculate_surface() {
        return Math.pow(base, 2.0) + (base * apothem * 2.0);
    }

    // Registra un suscriptor a la forma
    @Override
    public void registerSubscriber(Subscriber subscriber) {
        if (Arrays.stream(subscribers.toArray(new Subscriber[subscribers.size()])).filter((Subscriber sub) -> sub == subscriber).count() > 0) return;
        subscribers.add(subscriber);
    }

    // Notifica a todos los suscriptores de la forma que esta ha cambiado su estado
    @Override
    public void notifySubscribers() {
        Arrays.stream(subscribers.toArray(new Subscriber[subscribers.size()])).forEach((Subscriber sub) -> sub.Notify());
    }

    // Getters y setters de los atributos de la forma, se implementa este patron para que los componentes de UI
    // no puedan cambiar el estado directamente y que esta accion pueda ser delegada a la clase, que en consecuencia debe notificar
    // a los suscriptores de que ha cambiado su estado
    public Double getBase() {
        return base;
    }

    public void setBase(Double base) {
        this.base = base;
        notifySubscribers();
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
        notifySubscribers();
    }

    public Double getApothem() {
        return apothem;
    }

    public void setApothem(Double apothem) {
        this.apothem = apothem;
        notifySubscribers();
    }
}

class PyramidWindow extends JFrame implements Subscriber, ActionListener {
    private Pyramid pyramid;
    private JTextField base_field;
    private JTextField height_field;
    private JTextField apothem_field;
    private JLabel volume_label;
    private JLabel surface_label;
    private GridBagLayout layout;

    public PyramidWindow(Pyramid pyramid) {
        // Definimos el layout del contenedor de la ventana (creado en initializeComponents)
        layout = new GridBagLayout();
        this.pyramid = pyramid;

        // Nos registramos a la forma para que esta nos notifique cuando cambie su estado
        pyramid.registerSubscriber(this);

        // Delegamos el layout al container que se crea en initializeComponents para que este se expanda por toda la ventana
        setLayout(new BorderLayout());
        setBounds(0, 0, 500, 275);
        
        // Inicializamos los componentes de la ventana
        initializeComponents();
        // Inicializamos los valores de las metricas con el valor por defecto que tiene la forma
        this.Notify();
    }

    private void initializeComponents() {
        // Creamos un contenedor que tendra todos los componentes de la ventana
        JPanel container = new JPanel(layout);
        container.setBorder(javax.swing.BorderFactory.createTitledBorder("Piramide"));

        // Contenedor de todo lo relacionado al input de los parametros de la forma
        JPanel form = new JPanel();

        form.setLayout(new GridBagLayout());

        JPanel base_form_field_panel = new JPanel(new BorderLayout());
        base_form_field_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Base"));
        base_field = new JTextField(pyramid.getBase().toString());
        base_form_field_panel.add(base_field);

        JPanel height_form_field_panel = new JPanel(new BorderLayout());
        height_form_field_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Altura"));
        height_field = new JTextField(pyramid.getHeight().toString());
        height_form_field_panel.add(height_field);

        JPanel apothem_form_field_panel = new JPanel(new BorderLayout());
        apothem_form_field_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Apotema"));
        apothem_field = new JTextField(pyramid.getApothem().toString());
        apothem_form_field_panel.add(apothem_field);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;

        constraints.gridx = 0;
        constraints.gridy = 0;
        form.add(base_form_field_panel, constraints);
        constraints.gridy += 1;
        form.add(height_form_field_panel, constraints);
        constraints.gridy += 1;
        form.add(apothem_form_field_panel, constraints);

        constraints.gridy = 0;
        container.add(form, constraints);

        // Contenedor para botones en caso de que se desee añadir multiples botones
        JPanel buttons = new JPanel();

        buttons.setLayout(new GridBagLayout());

        JButton calculate_button = new JButton("Calcular");
        calculate_button.setActionCommand("calculate");
        calculate_button.addActionListener(this);
        
        constraints.weighty = 0.8;
        constraints.gridy = 0;
        constraints.gridx = 0;
        buttons.add(calculate_button, constraints);

        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        container.add(buttons, constraints);

        // Contiene las metricas de la forma (volumen y superficie)
        JPanel metrics = new JPanel();
        metrics.setBorder(javax.swing.BorderFactory.createTitledBorder("Metricas"));

        metrics.setLayout(new GridBagLayout());

        volume_label = new JLabel("");
        surface_label = new JLabel("");

        constraints.gridy = 0;
        constraints.gridx = 0;
        metrics.add(volume_label, constraints);
        constraints.gridy += 1;
        metrics.add(surface_label, constraints);

        constraints.gridy = 2;
        container.add(metrics, constraints);
        add(container);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "calculate":
                // Actualizamos el estado de la forma con los valores que se encuentran en los campos de texto, 
                // no es necesario manejar ningun cambio en la UI ya que esto se hara desde Notify cuando la forma notifique
                // sobre el cambio en su estado
                pyramid.setBase(Double.parseDouble(base_field.getText()));
                pyramid.setHeight(Double.parseDouble(height_field.getText()));
                pyramid.setApothem(Double.parseDouble(apothem_field.getText()));
                break;
            default:
                break;
        }
    }

    @Override
    public void Notify() {
        // Actualizamos el texto de las metricas con el valor que retorna la forma al calcularlas
        volume_label.setText("Volumen: " + pyramid.calculate_volume().toString());
        surface_label.setText("Superficie: " + pyramid.calculate_surface().toString());
    }
}


class MenuWindow extends JFrame implements ActionListener {
    private Cylinder cylinder;
    private CylinderWindow cylinderWindow;
    private Sphere sphere;
    private SphereWindow sphereWindow;
    private Pyramid pyramid;
    private PyramidWindow pyramidWindow;

    private GridBagLayout layout;

    public MenuWindow() {
        // Se definen las formas que se van a utilizar en la aplicacion y sus respectivas ventanas,
        // se prefiere este metodo sobre su creacion en el constructor ya que se puede centralizar el manejo 
        // del estado de la aplicacion 
        // Esto es util en caso de que se desee serializar, tambien permite la persistencia de los datos de
        // las formas entre ventanas y modificacion de las mismas independiente del contexto (ventana) 
        // en el que se encuentre el usuario, ademas de la ventaja mas significativa que es la de 
        // desvincular por completo el manejo de estado de la aplicacion de la capa del cliente (UI)
        cylinder = new Cylinder(0.0, 0.0);
        cylinderWindow = new CylinderWindow(cylinder);
        sphere = new Sphere(0.0);
        sphereWindow = new SphereWindow(sphere);
        pyramid = new Pyramid(0.0, 0.0, 0.0);
        pyramidWindow = new PyramidWindow(pyramid);

        this.layout = new GridBagLayout();

        setBounds(0, 0, 500, 200);

        setLayout(layout);

        initializeComponents();
    }

    private void initializeComponents() {
        JButton cylinderButton = new JButton("Cilindro");
        cylinderButton.setActionCommand("cylinder");
        JButton sphereButton = new JButton("Esfera");
        sphereButton.setActionCommand("sphere");
        JButton pyramidButton = new JButton("Piramide");
        pyramidButton.setActionCommand("pyramid");

        cylinderButton.addActionListener(this);
        sphereButton.addActionListener(this);
        pyramidButton.addActionListener(this);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;

        this.add(cylinderButton, constraints);
        constraints.gridx += 1;
        this.add(sphereButton, constraints);
        constraints.gridx += 1;
        this.add(pyramidButton, constraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "cylinder":
                this.cylinderWindow.setVisible(true);
                break;
            case "sphere":
                this.sphereWindow.setVisible(true);
                break;
            case "pyramid":
                this.pyramidWindow.setVisible(true);
                break;
            default:
                break;
        }
    }
}

class Main {
    public static MenuWindow menuWindow;

    public static void main(String[] args) {
        menuWindow = new MenuWindow();
        menuWindow.setVisible(true);
    }
}
