/*
* Proyecto: swing_c_p02_GomezGarciaFranciscoJose
* Paquete: 
* Archivo: VentanaDialogo.java
* Autor/a: Francisco José Gómez García
* Fecha: 25 nov 2025 9:38:23
*
* Descripción:
* [Resumen del propósito del archivo/clase.]
*
* Licencia:
* [Condiciones de uso/licencia.]
*/

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.FlowLayout;

/**
 * Diálogo principal para el alta de apartamentos turísticos.
 * Reúne los formularios de arrendador e inmueble, así como
 * la configuración de camas, niños, imágenes, precio y descuento.
 *
 */
public class VentanaDialogo extends JDialog implements ActionListener {

    private Image icono;
    private FormularioInmueble formularioInmueble;
    private Cabecera cabecera;
    private FormularioArrendador formularioArrendador;
    private JPanel centroJPanel, oesteJPanel, surJPanel, surArribaJPanel, panelBotonesJPanel;
    private PanelTipoCamas panelTipoCamas;
    private NinosExtras ninosExtras;
    private Resumen resumen;
    private ImagenesPiso imagenesPiso;
    private Precio precio;
    private JButton imprimirButton, nuevoButton, guardarButton;
    private DescuentoPanel descuentoPanel;

    /**
     * Crea el diálogo modal de alta de pisos e inicializa todos los paneles.
     *
     * @param parent ventana principal que actúa como padre del diálogo.
     */
    public VentanaDialogo(JFrame parent) {
        super(parent, "Alta de apartamentos turísticos Costa&Stay", true);

        // Ajusta el tamaño al área útil máxima de la pantalla
        setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Icono de la ventana
        icono = new ImageIcon(getClass().getResource("/recursos/icono.png")).getImage();
        setIconImage(icono);

        // Descripción accesible para lectores de pantalla
        getAccessibleContext().setAccessibleDescription("Diálogo para dar de alta apartamentos turísticos: datos de arrendador, inmueble, niños, tipo de cama, imágenes y precio.");

        // Cabecera superior
        cabecera = new Cabecera();
        add(cabecera, BorderLayout.NORTH);

        // Panel central: formularios de arrendador e inmueble
        centroJPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        formularioArrendador = new FormularioArrendador();
        formularioArrendador.getAccessibleContext().setAccessibleDescription("Formulario con los datos del arrendador: nombre, apellidos, DNI y teléfono.");

        formularioInmueble = new FormularioInmueble();
        formularioInmueble.getAccessibleContext().setAccessibleDescription("Formulario con los datos del inmueble: dirección, provincia, fechas, número de huéspedes, dormitorios, baños y camas.");

        centroJPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centroJPanel.add(formularioArrendador);
        centroJPanel.add(formularioInmueble);
        add(centroJPanel, BorderLayout.CENTER);

        // Panel izquierdo: tipo de camas + niños y extras
        oesteJPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        panelTipoCamas = new PanelTipoCamas();
        panelTipoCamas.setToolTipText("Selecciona el tipo y número de camas del apartamento.");
        panelTipoCamas.getAccessibleContext().setAccessibleDescription("Panel para seleccionar el tipo de cama y el número de camas.");

        ninosExtras = new NinosExtras();
        ninosExtras.setToolTipText("Indica si viajan niños y si necesitan cuna o cama supletoria.");
        ninosExtras.getAccessibleContext().setAccessibleDescription("Panel para indicar si hay niños y los extras necesarios.");

        oesteJPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5));
        oesteJPanel.add(panelTipoCamas);
        oesteJPanel.add(ninosExtras);
        add(oesteJPanel, BorderLayout.WEST);

        // Panel derecho: resumen
        resumen = new Resumen();
        resumen.setToolTipText("Muestra un resumen de los datos del arrendador y del inmueble.");
        resumen.getAccessibleContext().setAccessibleDescription("Panel de resumen con pestañas para arrendador e inmueble.");
        add(resumen, BorderLayout.EAST);

        // Panel inferior: imágenes + precio + descuento + botones	
        surJPanel = new JPanel(new BorderLayout());
        surArribaJPanel = new JPanel(new GridLayout(1, 3, 5, 5));

        imagenesPiso = new ImagenesPiso();
        imagenesPiso.setToolTipText("Muestra las imágenes del apartamento en función del número seleccionado.");
        imagenesPiso.getAccessibleContext().setAccessibleDescription("Panel para visualizar imágenes del apartamento.");

        precio = new Precio();
        precio.setToolTipText("Muestra el precio mínimo por día calculado según camas, baños, extras y descuento.");
        precio.getAccessibleContext().setAccessibleDescription("Panel que muestra el precio mínimo calculado por día.");

        descuentoPanel = new DescuentoPanel();
        descuentoPanel.setToolTipText("Ajusta el porcentaje de descuento aplicado al precio mínimo.");
        descuentoPanel.getAccessibleContext().setAccessibleDescription("Panel con un control deslizante para indicar el porcentaje de descuento.");

        surArribaJPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        surArribaJPanel.add(imagenesPiso);
        surArribaJPanel.add(precio);
        surArribaJPanel.add(descuentoPanel);

        surJPanel.add(surArribaJPanel, BorderLayout.CENTER);

        // Panel de botones (Imprimir, Nuevo, Guardar)
        panelBotonesJPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        ImageIcon iconImprimir = new ImageIcon(getClass().getResource("/recursos/imprimir.png"));
        imprimirButton = new JButton("Imprimir", iconImprimir);
        imprimirButton.setMnemonic(KeyEvent.VK_M);
        imprimirButton.setToolTipText("Imprime el resumen en pantalla si los datos son correctos.");
        imprimirButton.getAccessibleContext().setAccessibleDescription("Botón para volcar los datos al resumen si todos los campos son válidos.");
        imprimirButton.addActionListener(this);
        panelBotonesJPanel.add(imprimirButton);

        ImageIcon iconNuevo = new ImageIcon(getClass().getResource("/recursos/nuevo.png"));
        nuevoButton = new JButton("Nuevo", iconNuevo);
        nuevoButton.setMnemonic(KeyEvent.VK_U);
        nuevoButton.setToolTipText("Limpia todos los datos y devuelve el formulario a su estado inicial.");
        nuevoButton.getAccessibleContext().setAccessibleDescription("Botón para limpiar todos los formularios y volver al estado inicial.");
        nuevoButton.addActionListener(this);
        panelBotonesJPanel.add(nuevoButton);

        ImageIcon iconGuardar = new ImageIcon(getClass().getResource("/recursos/guardar.png"));
        guardarButton = new JButton("Guardar", iconGuardar);
        guardarButton.setMnemonic(KeyEvent.VK_G);
        guardarButton.setToolTipText("Guarda el registro si todos los datos son correctos.");
        guardarButton.getAccessibleContext().setAccessibleDescription("Botón por defecto que guarda el registro cuando todos los campos son válidos.");
        guardarButton.addActionListener(this);
        panelBotonesJPanel.add(guardarButton);

        // Botón por defecto al pulsar Enter
        getRootPane().setDefaultButton(guardarButton);

        surJPanel.add(panelBotonesJPanel, BorderLayout.SOUTH);
        add(surJPanel, BorderLayout.SOUTH);

        // Conecta todos los paneles que influyen en el precio
        Runnable r = this::recalcularPrecio;
        panelTipoCamas.setListenerPrecio(r);
        ninosExtras.setListenerPrecio(r);
        formularioInmueble.setListenerPrecio(r);
        descuentoPanel.setListenerCambio(r);

        // Cálculo inicial del precio
        recalcularPrecio();

        setVisible(true);
    }
	
    /**
     * Gestiona los eventos de los botones Imprimir, Nuevo y Guardar.
     *
     * @param e evento de acción generado por los botones.
     */
	@Override
	public void actionPerformed(ActionEvent e) {
	    Object src = e.getSource();

	    if (src == imprimirButton) {
	        boolean okArr = formularioArrendador.validar();
	        boolean okInm = formularioInmueble.validar();

	        if (!okArr || !okInm) {
	            JOptionPane.showMessageDialog(this,"Hay errores en el formulario.\n" +"Revisa los campos marcados en rojo y completa los obligatorios.","Error en datos",JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        recalcularPrecio();

	        resumen.mostrarArrendador(formularioArrendador.getNombre(),formularioArrendador.getApellidos(),formularioArrendador.getDni(),formularioArrendador.getTelefono());

	        resumen.mostrarInmueble(
	                formularioInmueble.getDireccion(),
	                formularioInmueble.getProvincia(),
	                formularioInmueble.getNumHuespedes(),
	                formularioInmueble.getNumDormitorios(),
	                formularioInmueble.getNumBanos(),
	                panelTipoCamas.getNumCamas(),
	                panelTipoCamas.getTipoCama(),
	                Double.parseDouble(((JTextField) precio.getComponent(0)).getText().replace(',', '.'))
	        );
	    } else if (src == nuevoButton) {
	        formularioArrendador.limpiar();
	        formularioInmueble.limpiar();
	        panelTipoCamas.limpiar();
	        ninosExtras.limpiar();
	        precio.limpiar();
	        resumen.limpiar();
	        descuentoPanel.limpiar();
	        
	        recalcularPrecio();

	        formularioArrendador.pedirFocoNombre();
	    }else if (src == guardarButton) {
	        boolean okArr = formularioArrendador.validar();
	        boolean okInm = formularioInmueble.validar();

	        if (!okArr || !okInm) {
	            JOptionPane.showMessageDialog(this,"Hay errores en el formulario.\n" +"Revisa los campos marcados en rojo y completa los obligatorios.","Error en datos",JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        JOptionPane.showMessageDialog(this,"Registro guardado","Guardar",JOptionPane.INFORMATION_MESSAGE);
	    }
	}
	
	/**
     * Recalcula el precio mínimo en función del tipo y número de camas,
     * número de baños, extras para niños y descuento seleccionado.
     */
	private void recalcularPrecio() {
        String tipo = panelTipoCamas.getTipoCama();
        int numCamas = panelTipoCamas.getNumCamas();
        int numBanos = formularioInmueble.getNumBanos();
        boolean hayExtras = ninosExtras.hayExtrasNinos();
        int descuento = descuentoPanel.getDescuentoPorcentaje();

        // Llama al panel Precio para que actualice el valor mostrado
        precio.actualizarPrecioMinimo(tipo, numCamas, numBanos, hayExtras,descuento);
    }
	
}
