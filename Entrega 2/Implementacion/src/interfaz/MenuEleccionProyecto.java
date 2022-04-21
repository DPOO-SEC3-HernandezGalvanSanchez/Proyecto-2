package interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuEleccionProyecto extends Menu implements ActionListener
{
	
	private VentanaAplicacion ventana;
	private PanelEleccion1 p1;
	
	public MenuEleccionProyecto(VentanaAplicacion padre)
	{
		super(2, "ELECCION DEL PROYECTO");
		this.ventana = padre;
		ventana.enableBotonContinuar(false);
		setLayout(new GridLayout(2, 1));
		
		p1 = new PanelEleccion1();
		add(p1);
		
		//PRUEBA
		JPanel prueba2 = new JPanel();
		JButton bPrueba2 = new JButton("Prueba2");
		bPrueba2.addActionListener(this);
		prueba2.add(bPrueba2);
		add(prueba2);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		ventana.enableBotonContinuar(true);
    }
	
}
