/*
 * 
 */

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

/**
 * Formulario con los datos del arrendador.
 * Permite introducir nombre, apellidos, DNI y teléfono,
 * realizando validaciones básicas de formato.
 */
public class FormularioArrendador extends JPanel{
	
	private JLabel nombre, apellidos, dni, telefono;
	private JTextField nombreField, apellidosField, telefonoField;
	private JFormattedTextField dniField;
	
	/**
     * Crea el panel del formulario del arrendador
     * e inicializa sus componentes gráficos.
     * 
     * Inicializa etiquetas y campos de texto, los añade y configura mnemonics y tooltips.
     */
	public FormularioArrendador() {
		setLayout(new GridLayout(4,2,5,5));
		setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.GREEN), "Datos arrendador"));
		getAccessibleContext().setAccessibleDescription("Formulario para introducir los datos del arrendador: nombre, apellidos, DNI y teléfono.");
		
		nombre = new JLabel("Nombre");
		nombreField = new JTextField();
		nombre.setLabelFor(nombreField);
		nombre.setDisplayedMnemonic('N');
		nombreField.setToolTipText("Introduce el nombre del arrendador.");
        nombreField.getAccessibleContext().setAccessibleDescription("Campo de texto para el nombre del arrendador.");
		add(nombre);
        add(nombreField);
        
		apellidos = new JLabel("Apellidos");
		apellidosField = new JTextField();
		apellidos.setLabelFor(apellidosField);
		apellidos.setDisplayedMnemonic('A');
		apellidosField.setToolTipText("Introduce los apellidos del arrendador.");
        apellidosField.getAccessibleContext().setAccessibleDescription("Campo de texto para los apellidos del arrendador.");
		add(apellidos);
        add(apellidosField);
        
		dni = new JLabel("Dni");
		dniField = new JFormattedTextField();
		dni.setLabelFor(dniField);
		dni.setDisplayedMnemonic('D');
		dniField.setToolTipText("Introduce un DNI válido con 8 dígitos y letra final.");
        dniField.getAccessibleContext().setAccessibleDescription("Campo de texto formateado para el DNI del arrendador.");
		dniField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evento) {
				String texto = dniField.getText().replace("_", "").trim();
				String expresionRegular = "^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKET]$";
                if (texto.length() != 9 || !texto.matches(expresionRegular)) {
                    dniField.setBorder(BorderFactory.createLineBorder(Color.RED));
                    dniField.setToolTipText("El DNI debe tener un formato válido.");
                } else {
                    dniField.setBorder(UIManager.getBorder("TextField.border"));
                    dniField.setToolTipText(null);
                }
			}
		});
		add(dni);
        add(dniField);
        
		telefono = new JLabel("Teléfono");
		telefonoField = new JTextField();
		telefono.setLabelFor(telefonoField);
		telefono.setDisplayedMnemonic('T');
		telefonoField.setToolTipText("Introduce un teléfono móvil de 9 dígitos.");
        telefonoField.getAccessibleContext().setAccessibleDescription("Campo de texto para el teléfono del arrendador.");
		telefonoField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evento) {
				String expresionRegular = "^[0-9]{9}$";
				String texto = telefonoField.getText().replace("_", "").trim();
                if (texto.length() != 9 || !texto.matches(expresionRegular)) {
                    telefonoField.setBorder(BorderFactory.createLineBorder(Color.RED));
                    telefonoField.setToolTipText("Debe introducir exactamente 9 dígitos.");
                } else {
                    telefonoField.setBorder(UIManager.getBorder("TextField.border"));
                    telefonoField.setToolTipText(null);
                }
			}
		});
        add(telefono);
        add(telefonoField);
	}
	
	/**
     * Hace las validaciones de nombre, apellidos, dni, telefono,
     */
	public boolean validar() {
	    boolean ok = true;

	    if (nombreField.getText().trim().isEmpty()) {
	        nombreField.setBorder(BorderFactory.createLineBorder(Color.RED));
	        ok = false;
	    } else {
	        nombreField.setBorder(UIManager.getBorder("TextField.border"));
	    }

	    if (apellidosField.getText().trim().isEmpty()) {
	        apellidosField.setBorder(BorderFactory.createLineBorder(Color.RED));
	        ok = false;
	    } else {
	        apellidosField.setBorder(UIManager.getBorder("TextField.border"));
	    }

	    String textoDni = dniField.getText().replace("_", "").trim();
	    String expDni = "^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKET]$";
	    if (textoDni.length() != 9 || !textoDni.matches(expDni)) {
	        dniField.setBorder(BorderFactory.createLineBorder(Color.RED));
	        ok = false;
	    } else {
	        dniField.setBorder(UIManager.getBorder("TextField.border"));
	    }

	    String textoTel = telefonoField.getText().trim();
	    String expTel = "^[0-9]{9}$";
	    if (textoTel.length() != 9 || !textoTel.matches(expTel)) {
	        telefonoField.setBorder(BorderFactory.createLineBorder(Color.RED));
	        ok = false;
	    } else {
	        telefonoField.setBorder(UIManager.getBorder("TextField.border"));
	    }

	    return ok;
	}
	
	/**
     * Limpia los campos y restaura el estado inicial del formulario.
     */
	public void limpiar() {
	    nombreField.setText("");
	    apellidosField.setText("");
	    dniField.setText("");
	    telefonoField.setText("");

	    nombreField.setBorder(UIManager.getBorder("TextField.border"));
	    apellidosField.setBorder(UIManager.getBorder("TextField.border"));
	    dniField.setBorder(UIManager.getBorder("TextField.border"));
	    telefonoField.setBorder(UIManager.getBorder("TextField.border"));

	    dniField.setToolTipText(null);
	    telefonoField.setToolTipText(null);
	}

	/**
     * Solicita el foco para el campo nombre.
     */
	public void pedirFocoNombre() {
	    nombreField.requestFocusInWindow();
	}


	/** @return nombre del arrendador sin espacios a los lados. */
	public String getNombre()   { return nombreField.getText().trim(); }
	
	/** @return apellidos del arrendador sin espacios a los lados. */
	public String getApellidos(){ return apellidosField.getText().trim(); }
	
	/** @return DNI del arrendador tal como se ha introducido. */
	public String getDni()      { return dniField.getText().trim(); }
	
	/** @return teléfono del arrendador sin espacios a los lados. */
	public String getTelefono() { return telefonoField.getText().trim(); }
}
