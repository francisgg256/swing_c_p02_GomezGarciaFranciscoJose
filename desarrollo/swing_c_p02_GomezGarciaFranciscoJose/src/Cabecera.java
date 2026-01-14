/*
* Proyecto: swing_c_p02_GomezGarciaFranciscoJose
* Paquete: 
* Archivo: Cabecera.java
* Autor/a: Francisco José Gómez García
* Fecha: 26 nov 2025 12:26:15
*
* Descripción:
* [Resumen del propósito del archivo/clase.]
*
* Licencia:
* [Condiciones de uso/licencia.]
*/

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Panel de cabecera de la aplicación.
 * Muestra el nombre de la empresa en la parte superior de la ventana.
 */
public class Cabecera extends JPanel{
	
	private JLabel nombreEmpresaJLabel;
	
	/**
     * Crea el panel de cabecera y coloca el nombre de la empresa.
     */
	public Cabecera() {
		nombreEmpresaJLabel = new JLabel("Nombre Empresa");
		add(nombreEmpresaJLabel, BorderLayout.NORTH);
		setBorder(new LineBorder(Color.RED));		
	}
}
