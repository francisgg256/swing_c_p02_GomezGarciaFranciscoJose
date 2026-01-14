/*
* Proyecto: swing_c_p02_GomezGarciaFranciscoJose
* Paquete: 
* Archivo: DescuentoPanel.java
* Autor/a: Francisco José Gómez García
* Fecha: 29 nov 2025 19:17:23
*
* Descripción:
* [Resumen del propósito del archivo/clase.]
*
* Licencia:
* [Condiciones de uso/licencia.]
*/

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

/**
 * Panel que permite ajustar un porcentaje de descuento
 * sobre el precio mínimo del apartamento mediante un JSlider.
 * Muestra también la cantidad de descuento seleccionada en una etiqueta.
 */
public class DescuentoPanel extends JPanel {

    private JSlider sliderDescuento;
    private JLabel lblValor;
    private Runnable listenerCambio; 

    /**
     * Crea el panel de descuento, configura el slider y la etiqueta
     * y registra el listener para reaccionar a los cambios.
     */
    public DescuentoPanel() {
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.MAGENTA),"Descuento"));
        getAccessibleContext().setAccessibleDescription("Panel para ajustar el porcentaje de descuento aplicado al precio mínimo del apartamento.");

        sliderDescuento = new JSlider(0, 30, 0); 
        sliderDescuento.setMajorTickSpacing(10);
        sliderDescuento.setMinorTickSpacing(5);
        sliderDescuento.setPaintTicks(true);
        sliderDescuento.setPaintLabels(true);
        sliderDescuento.setToolTipText("Ajusta el porcentaje de descuento sobre el precio mínimo.");
        sliderDescuento.getAccessibleContext().setAccessibleDescription("Control deslizante para indicar el porcentaje de descuento, de 0 a 30 por ciento.");
        add(sliderDescuento, BorderLayout.CENTER);

        lblValor = new JLabel("Descuento: 0%");
        lblValor.setHorizontalAlignment(SwingConstants.CENTER);
        lblValor.setToolTipText("Muestra el porcentaje de descuento seleccionado.");
        lblValor.getAccessibleContext().setAccessibleDescription("Etiqueta que muestra el porcentaje de descuento actual.");
        add(lblValor, BorderLayout.SOUTH);
        
        

        sliderDescuento.addChangeListener(e -> {
            int valor = sliderDescuento.getValue();
            lblValor.setText("Descuento: " + valor + " %");
            if (listenerCambio != null) {
                listenerCambio.run();
            }
        });
    }

    /**
     * Establece la acción a ejecutar cuando cambie el porcentaje de descuento.
     *
     * @param r acción (Runnable) que se llamará al modificar el slider.
     */
    public void setListenerCambio(Runnable r) {
        this.listenerCambio = r;
    }

    /**
     * Devuelve el porcentaje de descuento actualmente seleccionado.
     *
     * @return porcentaje de descuento (0–30).
     */
    public int getDescuentoPorcentaje() {
        return sliderDescuento.getValue();
    }

    /**
     * Restaura el slider a su valor inicial (0% de descuento).
     */
    public void limpiar() {
        sliderDescuento.setValue(0);
    }
}

