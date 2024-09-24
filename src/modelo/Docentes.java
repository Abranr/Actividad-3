package modelo;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 * Clase Docentes que extiende de Persona
 */
public class Docentes extends Persona {
    private String nit;
    private int id;
    private String codigo_docentes;
    private double salario;
    private String fecha_ingreso_laborar;
    private String fecha_ingreso_registro;
    Conexion cn;

    // Constructor vacío
    public Docentes() {}

    // Constructor con parámetros
    public Docentes(int id, String nit, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento,
                    String codigo_docentes, double salario, String fecha_ingreso_laborar, String fecha_ingreso_registro) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);  // Heredando los datos de Persona
        this.nit = nit;
        this.id = id;
        this.codigo_docentes = codigo_docentes;
        this.salario = salario;
        this.fecha_ingreso_laborar = fecha_ingreso_laborar;
        this.fecha_ingreso_registro = fecha_ingreso_registro;
    }

    // Getters y setters para los nuevos campos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCodigo_docentes() {
        return codigo_docentes;
    }

    public void setCodigo_docentes(String codigo_docentes) {
        this.codigo_docentes = codigo_docentes;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getFecha_ingreso_laborar() {
        return fecha_ingreso_laborar;
    }

    public void setFecha_ingreso_laborar(String fecha_ingreso_laborar) {
        this.fecha_ingreso_laborar = fecha_ingreso_laborar;
    }

    public String getFecha_ingreso_registro() {
        return fecha_ingreso_registro;
    }

    public void setFecha_ingreso_registro(String fecha_ingreso_registro) {
        this.fecha_ingreso_registro = fecha_ingreso_registro;
    }

    // Método para leer los docentes desde la base de datos
    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT idDocentes, nit, nombres, apellidos, direccion, telefono, fecha_nacimiento, " +
                           "codigo_docentes, salario, fecha_ingreso_laborar, fecha_ingreso_registro FROM docentes;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);

            // Encabezado de la tabla
            String encabezado[] = {"IdDocentes", "Nit", "Nombres", "Apellidos", "Dirección", "Teléfono", "Nacimiento", "Código", "Salario", "Ingreso Laboral", "Ingreso Registro"};
            tabla.setColumnIdentifiers(encabezado);

            // Llenar la tabla con los resultados de la consulta
            String datos[] = new String[11];
            while (consulta.next()) {
                datos[0] = consulta.getString("idDocentes");
                datos[1] = consulta.getString("nit");
                datos[2] = consulta.getString("nombres");
                datos[3] = consulta.getString("apellidos");
                datos[4] = consulta.getString("direccion");
                datos[5] = consulta.getString("telefono");
                datos[6] = consulta.getString("fecha_nacimiento");
                datos[7] = consulta.getString("codigo_docentes");
                datos[8] = consulta.getString("salario");
                datos[9] = consulta.getString("fecha_ingreso_laborar");
                datos[10] = consulta.getString("fecha_ingreso_registro");
                tabla.addRow(datos);
            }

            cn.cerrar_conexion();

        } catch (SQLException ex) {
            cn.cerrar_conexion();
            System.out.println("Error: " + ex.getMessage());
        }
        return tabla;
    }

    // Método para agregar un docente
    @Override
    public void agregar() {
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "INSERT INTO docentes (nit, nombres, apellidos, direccion, telefono, fecha_nacimiento, codigo_docentes, salario, fecha_ingreso_laborar, fecha_ingreso_registro) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNit());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFecha_nacimiento());
            parametro.setString(7, getCodigo_docentes());
            parametro.setDouble(8, getSalario());
            parametro.setString(9, getFecha_ingreso_laborar());
            parametro.setString(10, getFecha_ingreso_registro());

            int ejecutar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null, Integer.toString(ejecutar) + " Registro Ingresado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    // Método para modificar un docente
    @Override
    public void modificar() {
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "UPDATE docentes SET nit = ?, nombres = ?, apellidos = ?, direccion = ?, telefono = ?, fecha_nacimiento = ?, " +
                           "codigo_docentes = ?, salario = ?, fecha_ingreso_laborar = ?, fecha_ingreso_registro = ? WHERE idDocentes = ?;";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNit());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFecha_nacimiento());
            parametro.setString(7, getCodigo_docentes());
            parametro.setDouble(8, getSalario());
            parametro.setString(9, getFecha_ingreso_laborar());
            parametro.setString(10, getFecha_ingreso_registro());
            parametro.setInt(11, getId());

            int ejecutar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null, Integer.toString(ejecutar) + " Registro Actualizado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    // Método para eliminar un docente
    @Override
    public void eliminar() {
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "DELETE FROM docentes WHERE idDocentes = ?;";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId());

            int ejecutar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null, Integer.toString(ejecutar) + " Registro Eliminado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
