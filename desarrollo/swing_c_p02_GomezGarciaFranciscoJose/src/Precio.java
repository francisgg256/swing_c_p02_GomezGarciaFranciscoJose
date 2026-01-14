/*
* Proyecto: swing_c_p02_GomezGarciaFranciscoJose
* Paquete: 
* Archivo: ImagenesPiso.java
* Autor/a: Francisco José Gómez García
* Fecha: 28 nov 2025 19:26:05
*
* Descripción:
* [Resumen del propósito del archivo/clase.]
*
* Licencia:
* [Condiciones de uso/licencia.]
*/

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 * Panel que muestra el precio mínimo calculado por día para el apartamento.
 * El precio se calcula a partir del tipo y número de camas, número de baños,
 * posibles extras para niños y un porcentaje de descuento.
 */
public class Precio extends JPanel{
	private JTextField precioField;
	
	/**
     * Crea el panel de precio, inicializando el campo de texto y
     * configurando su accesibilidad y valor por defecto.
     */
	public Precio() {
		setLayout(new GridLayout(3, 2, 5, 5));
		setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Precio"));
		getAccessibleContext().setAccessibleDescription("Panel que muestra el precio mínimo por día calculado para el apartamento.");
		
		precioField = new JTextField();
		precioField.setEditable(false);
		precioField.setText("0.00");
		precioField.setToolTipText("Precio mínimo por día en euros, calculado automáticamente.");
        precioField.getAccessibleContext().setAccessibleDescription("Campo de solo lectura que muestra el precio mínimo por día en euros.");
		add(precioField);
	}
	
	/**
     * Calcula y actualiza el precio mínimo por día en función de:
     * tipo y número de camas, número de baños, extras para niños
     * y porcentaje de descuento.
     *
     * @param tipoCama           tipo de cama seleccionada
     * @param numCamas           número de camas de ese tipo
     * @param numBanos           número de baños
     * @param hayExtrasNinos     true si hay extras por niños
     * @param descuentoPorcentaje porcentaje de descuento a aplicar (0–100)
     */
	public void actualizarPrecioMinimo(String tipoCama,int numCamas,int numBanos,boolean hayExtrasNinos,int descuentoPorcentaje) {
		double precio = 0.0;

		if ("Cama doble".equals(tipoCama)) {
			precio += numCamas * 20;
		} else if ("Sofá cama".equals(tipoCama)) {
			precio += numCamas * 15;
		} else if ("Cama simple".equals(tipoCama)) {
			precio += numCamas * 15;
		}

		precio += numBanos * 25;

		if (hayExtrasNinos) {
			precio += 12;
		}

		if (descuentoPorcentaje > 0) {
			double factor = 1.0 - (descuentoPorcentaje / 100.0);
			precio *= factor;
		}

		precioField.setText(String.format("%.2f", precio));
	}

	/**
     * Restaura el precio mostrado al valor inicial 0.00.
     */
	public void limpiar() {
	    precioField.setText("0.00");
	}
}
