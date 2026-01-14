/*
* Proyecto: swing_c_p02_GomezGarciaFranciscoJose
* Paquete: 
* Archivo: VentanaPrincipal.java
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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * VentanaPrincipal es la ventana inicial de la aplicación.
 * Ofrece acceso a las opciones de alta y baja de apartamentos turísticos
 * mediante un menú y dos botones grandes en el centro.
 */
public class VentanaPrincipal extends JFrame implements ActionListener {

    private JMenuBar barraMenu;
    private JMenu archivo, registro, ayuda;
    private JMenuItem salir, altaPisos, bajaPisos, acercaDe;
    private JButton altaPisosButton, bajaPisosButton;
    private ImageIcon nuevoGrande, eliminarGrande;
    private Image icono;
    private JPanel centro;

    /**
     * Construye la ventana principal, configura el menú,
     * los botones centrales y la accesibilidad básica.
     */
    public VentanaPrincipal() {
        setTitle("Gestión Apartamentos Turísticos Costa&Stay");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(900, 700));
        setLocationRelativeTo(null);

        icono = new ImageIcon(getClass().getResource("/recursos/icono.png")).getImage();
        setIconImage(icono);

        anadirBarraMenu();
        componentes();

        getAccessibleContext().setAccessibleDescription("Ventana principal con acceso al alta y baja de apartamentos turísticos.");

        setVisible(true);
    }

    /**
     * Crea y configura la barra de menús: Archivo, Registro y Ayuda,
     * con sus correspondientes elementos de menú y atajos de teclado.
     */
    private void anadirBarraMenu() {
        barraMenu = new JMenuBar();

        archivo = new JMenu("Archivo");
        archivo.setMnemonic(KeyEvent.VK_A);
        archivo.getAccessibleContext().setAccessibleDescription("Opciones generales de la aplicación.");
        barraMenu.add(archivo);

        salir = new JMenuItem("Salir");
        salir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
        salir.setMnemonic(KeyEvent.VK_S);
        salir.setToolTipText("Cerrar la aplicación (Ctrl+Q)");
        salir.addActionListener(e -> dispose());
        archivo.add(salir);

        registro = new JMenu("Registro");
        registro.setMnemonic(KeyEvent.VK_R);
        registro.getAccessibleContext().setAccessibleDescription("Gestión de registros de apartamentos.");
        barraMenu.add(registro);

        altaPisos = new JMenuItem("Alta pisos");
        altaPisos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        altaPisos.setMnemonic(KeyEvent.VK_L); 
        altaPisos.setToolTipText("Dar de alta un nuevo apartamento (Ctrl+N)");
        altaPisos.addActionListener(this);
        registro.add(altaPisos);

        bajaPisos = new JMenuItem("Baja pisos");
        bajaPisos.setMnemonic(KeyEvent.VK_B);
        bajaPisos.setToolTipText("Dar de baja un apartamento existente");
        bajaPisos.addActionListener(e ->JOptionPane.showMessageDialog(this,"Opción no implementada todavía.","Información",JOptionPane.INFORMATION_MESSAGE));
        registro.add(bajaPisos);

        ayuda = new JMenu("Ayuda");
        ayuda.setMnemonic(KeyEvent.VK_Y);
        ayuda.getAccessibleContext().setAccessibleDescription("Información sobre la aplicación.");
        barraMenu.add(ayuda);

        acercaDe = new JMenuItem("Acerca de");
        acercaDe.setMnemonic(KeyEvent.VK_C);
        acercaDe.setToolTipText("Información sobre la aplicación y el autor");
        acercaDe.addActionListener(e ->JOptionPane.showMessageDialog(this,"Gestión Apartamentos Turísticos Costa&Stay\n" +"Versión 1.0\n" +"Autor: Francisco José Gómez García","Acerca de",JOptionPane.INFORMATION_MESSAGE));
        ayuda.add(acercaDe);

        setJMenuBar(barraMenu);
    }

    /**
     * Crea el panel central con dos botones grandes:
     * Alta pisos y Baja pisos, cada uno con su icono.
     */
    private void componentes() {
        centro = new JPanel(new GridLayout(1, 2, 40, 0));
        centro.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        ImageIcon iconNuevo = new ImageIcon(getClass().getResource("/recursos/nuevo.png"));
        Image altaEscalada = iconNuevo.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        nuevoGrande = new ImageIcon(altaEscalada);

        ImageIcon iconEliminar = new ImageIcon(getClass().getResource("/recursos/eliminar.png"));
        Image bajaEscalada = iconEliminar.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        eliminarGrande = new ImageIcon(bajaEscalada);

        Font fuenteBoton = new Font("SansSerif", Font.PLAIN, 18);

        altaPisosButton = new JButton("Alta pisos", nuevoGrande);
        altaPisosButton.setFont(fuenteBoton);
        altaPisosButton.setHorizontalTextPosition(SwingConstants.CENTER);
        altaPisosButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        altaPisosButton.setPreferredSize(new Dimension(220, 180));
        altaPisosButton.setMargin(new Insets(10, 10, 10, 10));
        altaPisosButton.setMnemonic(KeyEvent.VK_L);
        altaPisosButton.setToolTipText("Crear un nuevo registro de apartamento turístico");
        altaPisosButton.getAccessibleContext().setAccessibleDescription("Botón para dar de alta un nuevo apartamento turístico.");
        altaPisosButton.addActionListener(this);
        centro.add(altaPisosButton);

        bajaPisosButton = new JButton("Baja pisos", eliminarGrande);
        bajaPisosButton.setFont(fuenteBoton);
        bajaPisosButton.setHorizontalTextPosition(SwingConstants.CENTER);
        bajaPisosButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        bajaPisosButton.setPreferredSize(new Dimension(220, 180));
        bajaPisosButton.setMargin(new Insets(10, 10, 10, 10));
        bajaPisosButton.setMnemonic(KeyEvent.VK_B);
        bajaPisosButton.setToolTipText("Dar de baja un apartamento existente");
        bajaPisosButton.getAccessibleContext().setAccessibleDescription("Botón para dar de baja un apartamento turístico existente.");
        bajaPisosButton.addActionListener(e -> JOptionPane.showMessageDialog(this,"Opción no implementada todavía.","Información",JOptionPane.INFORMATION_MESSAGE));
        centro.add(bajaPisosButton);

        getRootPane().setDefaultButton(altaPisosButton);

        add(centro, BorderLayout.CENTER);
    }

    /**
     * Gestiona los eventos de los elementos que usan esta ventana
     * como ActionListener (menú "Alta pisos" y botón "Alta pisos").
     *
     * @param evento acción producida (clic en botón o menú).
     */
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == altaPisosButton || evento.getSource() == altaPisos) {
            new VentanaDialogo(this);
        }
    }
}

