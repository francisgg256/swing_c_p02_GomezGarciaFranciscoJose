/*
* Proyecto: swing_c_p02_GomezGarciaFranciscoJose
* Paquete: 
* Archivo: PanelTipoCamas.java
* Autor/a: Francisco José Gómez García
* Fecha: 28 nov 2025 13:21:44
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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

/**
 * PanelTipoCamas permite seleccionar el número de camas y el tipo de cama
 * principal del apartamento (simple, doble o sofá cama). Además, notifica
 * cambios para que otros componentes (como el precio) puedan actualizarse.
 */
public class PanelTipoCamas extends JPanel{
	
	private JComboBox<String> tipoCamaBox;
	private JLabel numCamasJLabel, tipoCamasJLabel;
	private JSpinner numCamaSpinner;
	private Runnable listenerPrecio;
	
	/**
     * Construye el panel de tipo de camas, configurando etiquetas,
     * JComboBox, JSpinner, tooltips y accesibilidad.
     */
	public PanelTipoCamas() {
		setLayout(new GridLayout(3, 2, 5, 5));
		setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.ORANGE), "Tipo de cama"));
		getAccessibleContext().setAccessibleDescription("Panel para seleccionar el tipo de cama principal y el número de camas.");
		
		numCamasJLabel = new JLabel("Número de camas");
		numCamaSpinner = new JSpinner(new SpinnerNumberModel(1,1,4,1));
		numCamasJLabel.setDisplayedMnemonic('C');
		numCamasJLabel.setLabelFor(numCamaSpinner);
		numCamaSpinner.setToolTipText("Selecciona cuántas camas del tipo elegido tiene el apartamento.");
        numCamaSpinner.getAccessibleContext().setAccessibleDescription("Selector numérico para indicar el número de camas.");
		numCamaSpinner.addChangeListener(e -> avisarCambio());
		add(numCamasJLabel);
		add(numCamaSpinner);
		
		tipoCamasJLabel = new JLabel("Tipo de camas");
		String [] tipoCamaStrings = {"Cama simple", "Cama doble", "Sofá cama"};
		tipoCamaBox = new JComboBox<String>(tipoCamaStrings);
		tipoCamasJLabel.setLabelFor(tipoCamaBox);
		tipoCamaBox.setToolTipText("Elige si las camas son simples, dobles o sofá cama.");
        tipoCamaBox.getAccessibleContext().setAccessibleDescription("Lista desplegable para seleccionar el tipo de cama.");
		tipoCamaBox.addActionListener(e -> avisarCambio());
		add(tipoCamasJLabel);
		add(tipoCamaBox);
	}
	
	/**
     * Registra una acción que se ejecutará cuando cambien el tipo o número de camas.
     * @param r runnable que se llamará al cambiar spinner o combo.
     */
	public void setListenerPrecio(Runnable r) {
        this.listenerPrecio = r;
    }

	/**
     * Notifica al listener registrado que se ha producido un cambio
     * en el tipo o en el número de camas.
     */
    private void avisarCambio() {
        if (listenerPrecio != null) {
            listenerPrecio.run();
        }
    }

    /**
     * Devuelve el tipo de cama actualmente seleccionado.
     * @return texto del tipo de cama (por ejemplo, "Cama doble").
     */
    public String getTipoCama() {
        return (String) tipoCamaBox.getSelectedItem();
    }

    /**
     * Devuelve el número de camas seleccionadas.
     * @return número de camas (entre 1 y 4).
     */
    public int getNumCamas() {
        return (Integer) numCamaSpinner.getValue();
    }
    
    /**
     * Restaura el panel a su estado inicial:
     * primer tipo de cama y una sola cama.
     */
    public void limpiar() {
        tipoCamaBox.setSelectedIndex(0);
        numCamaSpinner.setValue(1);
    }
}
