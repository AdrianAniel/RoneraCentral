import java.util.ArrayList;
import java.util.List;

// Clase base Producto
class Producto {
    private String nombre;
    private boolean cumpleEstándares;

    public Producto(String nombre, boolean cumpleEstándares) {
        this.nombre = nombre;
        this.cumpleEstándares = cumpleEstándares;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isCumpleEstándares() {
        return cumpleEstándares;
    }

    public void inspeccionar() {
        if (!cumpleEstándares) {
            System.out.println("Producto " + nombre + " no cumple con los estándares de calidad.");
        } else {
            System.out.println("Producto " + nombre + " cumple con los estándares de calidad.");
        }
    }
}

// Clase derivada ControlMateriaPrima
class ControlMateriaPrima extends Producto {
    public ControlMateriaPrima(String nombre, boolean cumpleEstándares) {
        super(nombre, cumpleEstándares);
    }

    @Override
    public void inspeccionar() {
        System.out.println("Inspeccionando materia prima: " + getNombre());
        super.inspeccionar();
    }
}

// Clase derivada CondicionesFermentacion
class CondicionesFermentacion extends Producto {
    public CondicionesFermentacion(String nombre, boolean cumpleEstándares) {
        super(nombre, cumpleEstándares);
    }

    @Override
    public void inspeccionar() {
        System.out.println("Inspeccionando condiciones de fermentación: " + getNombre());
        super.inspeccionar();
    }
}

// Clase derivada ParametrosDestilacion
class ParametrosDestilacion extends Producto {
    public ParametrosDestilacion(String nombre, boolean cumpleEstándares) {
        super(nombre, cumpleEstándares);
    }

    @Override
    public void inspeccionar() {
        System.out.println("Inspeccionando parámetros de destilación: " + getNombre());
        super.inspeccionar();
    }
}

// Clase ControlCalidad
class ControlCalidad {
    private List<Producto> productos;

    public ControlCalidad() {
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void inspeccionarProductos() {
        for (Producto producto : productos) {
            producto.inspeccionar();
        }
    }

    public void registrarDatos() {
        System.out.println("Registro de datos de inspección:");
        for (Producto producto : productos) {
            System.out.println("Producto: " + producto.getNombre() + " - Cumple estándares: " + producto.isCumpleEstándares());
        }
    }

    public void accionesCorrectivas() {
        System.out.println("Implementando acciones correctivas...");
        for (Producto producto : productos) {
            if (!producto.isCumpleEstándares()) {
                System.out.println("Tomando medidas correctivas para el producto: " + producto.getNombre());
                // Implementar medidas correctivas aquí
            }
        }
    }

    public void revisarYMejorar() {
        System.out.println("Revisando y mejorando el proceso de control de calidad...");
        // Implementar lógica de mejora continua aquí
    }

    public void comunicar() {
        System.out.println("Comunicando estándares de calidad a todos los involucrados...");
        // Implementar lógica de comunicación aquí
    }
}

// Clase principal Main
public class Main {
    public static void main(String[] args) {
        ControlCalidad controlCalidad = new ControlCalidad();

        Producto materiaPrima = new ControlMateriaPrima("Caña de Azúcar", true);
        Producto fermentacion = new CondicionesFermentacion("Fermentación Lote 1", false);
        Producto destilacion = new ParametrosDestilacion("Destilación Lote 1", true);

        controlCalidad.agregarProducto(materiaPrima);
        controlCalidad.agregarProducto(fermentacion);
        controlCalidad.agregarProducto(destilacion);

        controlCalidad.inspeccionarProductos();
        controlCalidad.registrarDatos();
        controlCalidad.accionesCorrectivas();
        controlCalidad.revisarYMejorar();
        controlCalidad.comunicar();
    }
}
