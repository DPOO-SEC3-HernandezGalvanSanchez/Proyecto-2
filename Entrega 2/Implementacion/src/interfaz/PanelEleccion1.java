package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class PanelEleccion1 extends JPanel implements ActionListener
{

		public PanelEleccion1()
		{
			setLayout(null);
			setBorder(new EtchedBorder());
			
			String mensaje = "Proyectos en los que usted participa:";
			JLabel mensajeL = new JLabel(mensaje);
			mensajeL.setBounds(100, 50, 300, 30);
			add(mensajeL);
			
			JButton bPrueba1 = new JButton("Seleccionar");
			bPrueba1.setBounds(140, 150, 250, 30);
			bPrueba1.addActionListener(this);
			add(bPrueba1);
		}
		
		
		public void actionPerformed(ActionEvent e)
		{
	        
	    }
	
	
}
