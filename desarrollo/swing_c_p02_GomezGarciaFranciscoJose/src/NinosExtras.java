/*
* Proyecto: swing_c_p02_GomezGarciaFranciscoJose
* Paquete: 
* Archivo: NiñosExtras.java
* Autor/a: Francisco José Gómez García
* Fecha: 28 nov 2025 13:40:05
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
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

/**
 * Panel que permite indicar si viajan niños y qué extras necesitan
 * (cuna supletoria o cama supletoria) en función de su edad.
 * También notifica cambios para que se pueda recalcular el precio.
 */
public class NinosExtras extends JPanel{
	private JLabel ninosJLabel, edadJLabel, extrasJLabel;
	private JCheckBox ninosBox;
	private JSpinner edadJSpinner;
	private JTextField extrasField;
	private Runnable listenerPrecio;
	
	/**
     * Construye el panel de “Niños y extras”, inicializando sus componentes
     * y configurando accesibilidad, tooltips y eventos.
     */
	public NinosExtras() {
		setLayout(new GridLayout(3, 2, 5, 5));
		setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.PINK), "Niños y extras"));
		getAccessibleContext().setAccessibleDescription("Panel para indicar si viajan niños y qué extras necesitan, como cuna o cama supletoria.");
		
		ninosJLabel = new JLabel("¿Niños?");
		ninosBox = new JCheckBox();
		ninosJLabel.setLabelFor(ninosBox);
		ninosBox.setToolTipText("Marca esta casilla si viaja al menos un niño.");
        ninosBox.getAccessibleContext().setAccessibleDescription("Casilla de verificación para indicar si viajan niños.");
		ninosBox.addItemListener(e -> ninosActivado());
		add(ninosJLabel);
		add(ninosBox);
		
		edadJLabel = new JLabel("Edad");
		edadJSpinner = new JSpinner(new SpinnerNumberModel(0,0,10,1));
		edadJLabel.setLabelFor(edadJSpinner);
		edadJSpinner.setToolTipText("Indica la edad del niño para calcular el tipo de extra.");
        edadJSpinner.getAccessibleContext().setAccessibleDescription("Selector numérico para la edad del niño.");
		edadJSpinner.addChangeListener(e -> actualizarExtras());
		add(edadJLabel);
		add(edadJSpinner);
		
		extrasJLabel = new JLabel("Extras");
		extrasField = new JTextField();
		extrasJLabel.setLabelFor(extrasField);
		extrasField.setEditable(false);
		extrasField.setToolTipText("Muestra automáticamente el tipo de extra según la edad del niño.");
        extrasField.getAccessibleContext().setAccessibleDescription("Campo de solo lectura que indica el tipo de extra para el niño.");
		add(extrasJLabel);
		add(extrasField);
		
		ninosActivado();
	}
	
	/**
     * Registra una acción que se ejecutará cuando cambien datos
     * que influyen en el precio (por ejemplo, activar niños o cambiar la edad).
     *
     * @param r acción a ejecutar cuando haya cambios relevantes.
     */
	public void setListenerPrecio(Runnable r) {
        this.listenerPrecio = r;
    }

	/**
     * Notifica al listener registrado que se ha producido un cambio.
     */
    private void avisarCambio() {
        if (listenerPrecio != null) {
            listenerPrecio.run();
        }
    }

    /**
     * Activa o desactiva los controles de edad y extras según el estado
     * de la casilla “¿Niños?”. Si se desactiva, se limpian los valores.
     */
    private void ninosActivado() {
        boolean activo = ninosBox.isSelected();
        edadJLabel.setEnabled(activo);
        edadJSpinner.setEnabled(activo);
        extrasJLabel.setEnabled(activo);
        extrasField.setEnabled(activo);
        if (!activo) {
            edadJSpinner.setValue(0);
            extrasField.setText("");
        } else {
            actualizarExtras();
        }
        avisarCambio();
    }

    /**
     * Actualiza el texto del campo de extras en función de la edad del niño:
     * 0–3 años: cuna supletoria pequeña.
     * 4–10 años: cama supletoria.
     * >10 años: sin extra.
     */
    private void actualizarExtras() {
        if (!ninosBox.isSelected()) {
            extrasField.setText("");
        } else {
            int edad = (Integer) edadJSpinner.getValue();
            if (edad >= 0 && edad <= 3) {
                extrasField.setText("cuna supletoria pequeña");
            } else if (edad >= 4 && edad <= 10) {
                extrasField.setText("cama supletoria");
            } else {
                extrasField.setText("");
            }
        }
        avisarCambio();
    }

    /**
     * Indica si actualmente hay algún extra por niños que afecte al precio.
     *
     * @return true si la casilla de niños está marcada y el campo de extras no está vacío.
     */
    public boolean hayExtrasNinos() {
        return ninosBox.isSelected() && !extrasField.getText().isEmpty();
    }
    
    /**
     * Restaura el panel a su estado inicial:
     * casilla de niños desmarcada, edad 0 y sin extras.
     */
    public void limpiar() {
        ninosBox.setSelected(false);
        edadJSpinner.setValue(0);
        extrasField.setText("");
        ninosActivado();
    }
}
