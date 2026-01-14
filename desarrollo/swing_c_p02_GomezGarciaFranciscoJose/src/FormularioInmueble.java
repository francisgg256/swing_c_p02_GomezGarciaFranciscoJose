/*
* Proyecto: swing_c_p02_GomezGarciaFranciscoJose
* Paquete: 
* Archivo: FormularioInmueble.java
* Autor/a: Francisco José Gómez García
* Fecha: 26 nov 2025 12:40:05
*
* Descripción:
* [Resumen del propósito del archivo/clase.]
*
* Licencia:
* [Condiciones de uso/licencia.]
*/

import java.awt.Color;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.text.DateFormatter;

/**
 * FormularioInmueble representa el formulario donde se introducen
 * los datos principales del apartamento/inmueble: dirección, provincia,
 * fechas, número de huéspedes, dormitorios y baños.
 * 
 * Permite obtener estos datos y realizar la validación de campos obligatorios.
 */
public class FormularioInmueble extends JPanel{
	
	private JLabel direccion, provincia, fechaAlta, fechaFinal, numHuesped, numDorm, numBanos;
	private JTextField direccionField;
	private JFormattedTextField fechaAltaField, fechaFinalField;
	private JComboBox<String> provinciaBox;
	private JSpinner numHuespedJSpinner, numDormSpinner, numBanoSpinner;
	private Runnable listenerPrecio;
	
	/**
     * Constructor: inicializa los componentes gráficos y los ordena en el panel.
     */
	public FormularioInmueble() {
		setLayout(new GridLayout(4,2,5,5));
		setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLUE), "Datos inmueble"));
		getAccessibleContext().setAccessibleDescription("Formulario para introducir los datos del inmueble: dirección, provincia, fechas, número de huéspedes, dormitorios y baños.");
		
		direccion = new JLabel("Direccíon");
		direccionField = new JTextField();
		direccion.setLabelFor(direccionField);
		direccion.setDisplayedMnemonic('I');
		direccionField.setToolTipText("Introduce la dirección completa del apartamento.");
        direccionField.getAccessibleContext().setAccessibleDescription("Campo de texto para la dirección del inmueble.");
		add(direccion);
		add(direccionField);
		
		provincia = new JLabel("Provincia");
		String[] listaProvincias = {"Álava", "Albacete", "Alicante", "Almería", "Asturias",
			    					"Ávila", "Badajoz", "Barcelona", "Burgos", "Cáceres",
			    					"Cádiz", "Cantabria", "Castellón", "Ciudad Real", "Córdoba",
								    "Cuenca", "Gerona", "Granada", "Guadalajara", "Guipúzcoa",
								    "Huelva", "Huesca", "Islas Baleares", "Jaén", "La Coruña",
								    "La Rioja", "Las Palmas", "León", "Lérida", "Lugo",
								    "Madrid", "Málaga", "Murcia", "Navarra", "Orense",
								    "Palencia", "Pontevedra", "Salamanca", "Santa Cruz de Tenerife",
								    "Segovia", "Sevilla", "Soria", "Tarragona", "Teruel",
								    "Toledo", "Valencia", "Valladolid", "Vizcaya", "Zamora",
								    "Zaragoza"
								   }; 
		provinciaBox = new JComboBox<String>(listaProvincias);
		provincia.setLabelFor(provinciaBox);
		provincia.setDisplayedMnemonic('P');
		provinciaBox.setToolTipText("Selecciona la provincia donde se encuentra el apartamento.");
        provinciaBox.getAccessibleContext().setAccessibleDescription("Lista desplegable con las provincias disponibles.");
		add(provincia);
		add(provinciaBox);
		
		fechaAlta = new JLabel("Fecha Alta");
		DateFormat dFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
		fechaAltaField = new JFormattedTextField(new DateFormatter(dFormat));
		fechaAltaField.setValue(new Date());
		fechaAlta.setDisplayedMnemonic('F');
		fechaAlta.setLabelFor(fechaAltaField);
		fechaAltaField.setToolTipText("Fecha en la que el apartamento se da de alta en la aplicación.");
        fechaAltaField.getAccessibleContext().setAccessibleDescription("Campo de fecha para la fecha de alta del inmueble.");
		add(fechaAlta);
		add(fechaAltaField);
		
		fechaFinal = new JLabel("Fecha Final");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, 1);
		fechaFinalField = new JFormattedTextField(new DateFormatter(dFormat));
		fechaFinalField.setValue(cal.getTime());
		fechaFinal.setDisplayedMnemonic('E');
		fechaFinal.setLabelFor(fechaFinalField);
		fechaFinalField.setToolTipText("Fecha hasta la que el apartamento estará disponible (por defecto, un año).");
        fechaFinalField.getAccessibleContext().setAccessibleDescription("Campo de fecha para la fecha final de disponibilidad del inmueble.");
		add(fechaFinal);
		add(fechaFinalField);
		
		numHuesped = new JLabel("Número de huespedes");
		numHuespedJSpinner = new JSpinner(new SpinnerNumberModel(1,1,8,1));
		numHuesped.setDisplayedMnemonic('H');
		numHuesped.setLabelFor(numHuespedJSpinner);
		numHuespedJSpinner.setToolTipText("Número máximo de huéspedes permitidos en el apartamento.");
        numHuespedJSpinner.getAccessibleContext().setAccessibleDescription("Selector numérico para el número de huéspedes.");
		add(numHuesped);
		add(numHuespedJSpinner);
		
		numDorm = new JLabel("Número de dormitorios");
		numDormSpinner = new JSpinner(new SpinnerNumberModel(1,1,4,1));
		numDorm.setDisplayedMnemonic('O');
		numDorm.setLabelFor(numDormSpinner);
		numDormSpinner.setToolTipText("Número de dormitorios disponibles en el apartamento.");
        numDormSpinner.getAccessibleContext().setAccessibleDescription("Selector numérico para el número de dormitorios.");
		add(numDorm);
		add(numDormSpinner);
		
		numBanos = new JLabel("Número de baños");
		numBanoSpinner = new JSpinner(new SpinnerNumberModel(1,1,3,1));
		numBanos.setDisplayedMnemonic('B');
		numBanos.setLabelFor(numBanoSpinner);
		numBanoSpinner.addChangeListener(e -> avisarCambio());
		numBanoSpinner.setToolTipText("Número de baños disponibles en el apartamento.");
        numBanoSpinner.getAccessibleContext().setAccessibleDescription("Selector numérico para el número de baños.");
		add(numBanos);
		add(numBanoSpinner);
	}
	
	/**
     * @return Número de baños (entero)
     */
	public int getNumBanos() {
        return (Integer) numBanoSpinner.getValue();
    }
	
	/**
     * Permite registrar un listener que será avisado al cambiar el número de baños.
     * @param listenerPrecio Runnable a ejecutar
     */
	public void setListenerPrecio(Runnable listenerPrecio) {
	    this.listenerPrecio = listenerPrecio;
	}
	
	/**
     * Llama al listener registrado para avisar de un cambio relevante.
     */
	private void avisarCambio() {
        if (listenerPrecio != null) {
            listenerPrecio.run();
        }
    }
	
	/**
     * Valida los campos obligatorios (dirección, etc.). Marca errores visuales.
     * @return true si todo es correcto, false si falta información
     */
	public boolean validar() {
	    boolean ok = true;

	    if (direccionField.getText().trim().isEmpty()) {
	        direccionField.setBorder(BorderFactory.createLineBorder(Color.RED));
	        ok = false;
	    } else {
	        direccionField.setBorder(UIManager.getBorder("TextField.border"));
	        direccionField.setToolTipText("Introduce la dirección completa del apartamento.");
	    }

	    return ok;
	}
	
	/**
     * Restaura todos los campos del formulario a sus valores por defecto.
     */
	public void limpiar() {
	    direccionField.setText("");
	    provinciaBox.setSelectedIndex(0);

	    DateFormat dFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
	    fechaAltaField.setValue(new Date());
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(new Date());
	    cal.add(Calendar.YEAR, 1);
	    fechaFinalField.setValue(cal.getTime());

	    direccionField.setBorder(UIManager.getBorder("TextField.border"));
	    fechaAltaField.setBorder(UIManager.getBorder("TextField.border"));
	    fechaFinalField.setBorder(UIManager.getBorder("TextField.border"));

	    direccionField.setToolTipText("Introduce la dirección completa del apartamento.");
	    
	    numHuespedJSpinner.setValue(1);
	    numDormSpinner.setValue(1);
	    numBanoSpinner.setValue(1);
	}

	/**
     * @return Dirección del inmueble
     */
	public String getDireccion() { return direccionField.getText().trim(); }
	
	/**
     * @return Provincia seleccionada
     */
	public String getProvincia() { return (String) provinciaBox.getSelectedItem(); }
	
	/**
     * @return Número de huéspedes máximo permitido
     */
	public int getNumHuespedes() { return (Integer) numHuespedJSpinner.getValue(); }
	
	/**
     * @return Número de dormitorios
     */
	public int getNumDormitorios(){ return (Integer) numDormSpinner.getValue(); }
}
