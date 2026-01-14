/*
* Proyecto: swing_c_p02_GomezGarciaFranciscoJose
* Paquete: 
* Archivo: ImagenesPiso.java
* Autor/a: Francisco José Gómez García
* Fecha: 28 nov 2025 19:38:05
*
* Descripción:
* [Resumen del propósito del archivo/clase.]
*
* Licencia:
* [Condiciones de uso/licencia.]
*/

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

/**
 * Panel de resumen que muestra, en pestañas, la información del arrendador
 * y del inmueble introducida en el formulario principal.
 * Utiliza un JTabbedPane con dos JTextArea de solo lectura.
 */
public class Resumen extends JPanel{
	private JTabbedPane panelResumenJTabbedPane;
	private JTextArea arrendadorArea, inmuebleArea;
	
	/**
     * Construye el panel de resumen, creando el JTabbedPane
     * y las áreas de texto asociadas a cada pestaña.
     */
	public Resumen() {
		setLayout(new BorderLayout());
		getAccessibleContext().setAccessibleDescription("Panel de resumen con la información del arrendador y del inmueble.");
		
        panelResumenJTabbedPane = new JTabbedPane();
        panelResumenJTabbedPane.setToolTipText("Consulta el resumen de los datos introducidos.");
        panelResumenJTabbedPane.getAccessibleContext().setAccessibleDescription("Pestañas con el resumen de datos del arrendador y del inmueble.");
        
        arrendadorArea = new JTextArea();
        arrendadorArea.setEditable(false);
        arrendadorArea.setToolTipText("Resumen de los datos del arrendador.");
        arrendadorArea.getAccessibleContext().setAccessibleDescription("Área de texto de solo lectura con los datos del arrendador.");
        
        inmuebleArea = new JTextArea();
        inmuebleArea.setEditable(false);
        inmuebleArea.setToolTipText("Resumen de los datos del inmueble.");
        inmuebleArea.getAccessibleContext().setAccessibleDescription("Área de texto de solo lectura con los datos del inmueble.");

        panelResumenJTabbedPane.addTab("Arrendador", new JScrollPane(arrendadorArea));
        panelResumenJTabbedPane.addTab("Inmueble", new JScrollPane(inmuebleArea));

        add(panelResumenJTabbedPane, BorderLayout.CENTER);
	}
	
	/**
     * Muestra en la pestaña "Arrendador" los datos del arrendador
     * en un formato de varias líneas.
     *
     * @param nombre   nombre del arrendador
     * @param apellidos apellidos del arrendador
     * @param dni      DNI del arrendador
     * @param telefono teléfono del arrendador
     */
	public void mostrarArrendador(String nombre, String apellidos,
			String dni, String telefono) {
				arrendadorArea.setText("Nombre: " + nombre + "\n" +"Apellidos: " + apellidos + "\n" +"DNI: " + dni + "\n" +"Teléfono: " + telefono + "\n");
	}

	/**
     * Muestra en la pestaña "Inmueble" el resumen de los datos del apartamento,
     * incluyendo dirección, capacidad, número de estancias y precio mínimo.
     *
     * @param direccion   dirección del inmueble
     * @param provincia   provincia donde se ubica
     * @param huespedes   número de huéspedes
     * @param dormitorios número de dormitorios
     * @param banos       número de baños
     * @param camas       número de camas
     * @param tipoCama    tipo de cama principal
     * @param precioMinimo precio mínimo por día
     */
	public void mostrarInmueble(String direccion, String provincia,int huespedes, int dormitorios,int banos, int camas,String tipoCama, double precioMinimo) {
				inmuebleArea.setText("Dirección: " + direccion + "\n" +	"Provincia: " + provincia + "\n" +"Huéspedes: " + huespedes + "\n" + "Dormitorios: " + dormitorios + "\n" +	"Baños: " + banos + "\n" +"Camas: " + camas + " (" + tipoCama + ")\n" +	"Precio mínimo: " + String.format("%.2f", precioMinimo) + " €/día\n");
	}
	
	/**
     * Limpia el contenido de ambas áreas de texto del resumen.
     */
	public void limpiar() {
	    arrendadorArea.setText("");
	    inmuebleArea.setText("");
	}
}
