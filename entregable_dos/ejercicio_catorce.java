package entregable_dos;

enum TipoTriangulo {
    ISOSCELES("isosceles"),
    ESCALENO("escaleno"),
    EQUILATERO("equilatero");

    public String nombre_tipo;

    private TipoTriangulo(String nombre_tipo) {
        this.nombre_tipo = nombre_tipo;
    }
}

interface Figura {
    public double calcular_area();
    public double calcular_perimetro();
}

class Circulo implements Figura {
    public double radio;

    public Circulo(double radio) {
        this.radio = radio;
    }

    public double calcular_area() {
        return Math.PI * Math.pow(radio, 2);
    }

    public double calcular_perimetro() {
        return 2 * Math.PI * radio;
    }
}

class TrianguloRectangulo implements Figura {
    public double base;
    public double altura;

    public TrianguloRectangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    public double calcular_area() {
        return base * altura / 2;
    }

    public double calcular_perimetro() {
        return base + altura + this.calcular_hipotenusa();
    }

    public TipoTriangulo determinar_tipo() {
        return base == altura ? (altura == this.calcular_hipotenusa() ? TipoTriangulo.EQUILATERO : TipoTriangulo.ISOSCELES) : TipoTriangulo.ESCALENO;
    }

   public double calcular_hipotenusa() {
        return Math.sqrt(Math.pow(base, 2) + Math.pow(altura, 2));
   }
}

class Rectangulo implements Figura {
    protected double base;
    protected double altura;

    public Rectangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    public double calcular_area() {
        return this.base * this.altura;
    }

    public double calcular_perimetro() {
        return this.base * 2 + this.altura * 2;
    }

    // Se opto por usar getters y setters para delegar la definicion
    // de los valores de base y altura, de esta manera podemos definir cuadrado
    // a partir de rectangulo
    // Getters y setters
    public double get_base() {
        return base;
    }
    public void set_base(double base) {
        this.base = base;
    }
    public double get_altura() {
        return altura;
    }
    public void set_altura(double altura) {
        this.altura = altura;
    }
}

class Cuadrado extends Rectangulo {
    private double lado;

    public Cuadrado(double lado) {
        super(lado, lado);
        this.lado = lado;
    }

    // Getters y setters
    public double get_lado() {
        return lado;
    }
    public void set_lado(double lado) {
        this.altura = lado;
        this.base = lado;
        this.lado = lado;
    }
}

public class ejercicio_catorce {
    public static void main(String[] args) {
        Circulo figura1 = new Circulo(2);
        Rectangulo figura2 = new Rectangulo(1,2);
        Cuadrado figura3 = new Cuadrado(3);
        TrianguloRectangulo figura4 = new TrianguloRectangulo(3,5);
        System.out.printf("El área del círculo es = %.2f\n", figura1.calcular_area());
        System.out.printf("El perímetro del círculo es = %.2f\n\n", figura1.calcular_perimetro());
        System.out.printf("El área del rectángulo es = %.2f\n", figura2.calcular_area()); 
        System.out.printf("El perímetro del rectángulo es = %.2f\n\n", figura2.calcular_perimetro()); 
        System.out.printf("El área del cuadrado es = %.2f\n", figura3.calcular_area()); 
        System.out.printf("El perímetro del cuadrado es = %.2f\n\n", figura3.calcular_perimetro());
        System.out.printf("El área del triángulo es = %.2f\n", figura4.calcular_area());
        System.out.printf("El perímetro del triángulo es = %.2f\n", figura4.calcular_perimetro());
        System.out.printf("El triangulo es de tipo = %s", figura4.determinar_tipo().nombre_tipo);
    }
}
