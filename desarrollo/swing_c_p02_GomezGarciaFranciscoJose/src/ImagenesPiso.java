/*
* Proyecto: swing_c_p02_GomezGarciaFranciscoJose
* Paquete: 
* Archivo: ImagenesPiso.java
* Autor/a: Francisco José Gómez García
* Fecha: 28 nov 2025 14:05:05
*
* Descripción:
* [Resumen del propósito del archivo/clase.]
*
* Licencia:
* [Condiciones de uso/licencia.]
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeListener;

/**
 * Panel que permite seleccionar y visualizar imágenes del apartamento.
 * El usuario indica cuántas imágenes quiere ver mediante un JSpinner
 * y el panel muestra hasta ese número de fotos, escaladas al mismo tamaño.
 */
public class ImagenesPiso extends JPanel {
    private JSpinner imagenSpinner;
    private JPanel panelImagenesJPanel;
    private String[] rutaStrings = {"/recursos/fotoHabitacion1.jpg","/recursos/fotoHabitacion2.jpg","/recursos/fotoHabitacion3.jpg"};

    private static final int IMG_WIDTH = 150;
    private static final int IMG_HEIGHT = 100;

    /**
     * Crea el panel de imágenes, con un spinner para elegir la cantidad
     * y un área donde se mostrarán las fotos del apartamento.
     */
    public ImagenesPiso() {
    	setLayout(new GridLayout(3, 2, 5, 5));
		setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.CYAN), "Imagenes del apartamento"));

        imagenSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 3, 1));
        panelImagenesJPanel = new JPanel(new FlowLayout());
        panelImagenesJPanel.add(imagenSpinner);
        add(panelImagenesJPanel, BorderLayout.NORTH);

        ChangeListener listener = e -> cambiarImagenes();
        imagenSpinner.addChangeListener(listener);
    }

    /**
     * Actualiza el panel interno con tantas imágenes como indique el spinner.
     * Escala las imágenes a un tamaño fijo antes de mostrarlas.
     */
    private void cambiarImagenes() {
        int cantidad = (Integer) imagenSpinner.getValue();
        panelImagenesJPanel.removeAll();
        panelImagenesJPanel.add(imagenSpinner);

        for (int i = 0; i < cantidad && i < rutaStrings.length; i++) {
            ImageIcon iconoOriginal = new ImageIcon(
                    getClass().getResource(rutaStrings[i])
            );

            Image imgEscalada = iconoOriginal.getImage().getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_SMOOTH);

            ImageIcon iconoEscalado = new ImageIcon(imgEscalada);
            JLabel lblImagen = new JLabel(iconoEscalado);
            panelImagenesJPanel.add(lblImagen);
        }

        panelImagenesJPanel.revalidate();
        panelImagenesJPanel.repaint();
    }
}
