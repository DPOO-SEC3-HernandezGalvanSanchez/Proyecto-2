package interfaz;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;


@SuppressWarnings("serial")
public class PanelProyecto1 extends JPanel
{
	
	private JScrollPane scrollPane = new JScrollPane();
	
	public PanelProyecto1(String nombre, String descripcion, String fechaInicio,
						  String fechaFin, ArrayList<String> participantes)
	{
		setLayout(null);
		setBorder(new EtchedBorder());
		
		JLabel titulo = new JLabel("Informacion del proyecto");
		titulo.setBounds(40, 10, 600, 30);
		titulo.setFont(new Font("Bold", Font.BOLD, 13));
		add(titulo);
		
		JLabel mensajeNombre = new JLabel("Nombre: " + nombre);
		mensajeNombre.setBounds(40, 50, 400, 30);
		mensajeNombre.setFont(new Font("Bold", Font.PLAIN, 13));
		add(mensajeNombre);
		
		JLabel mensajeDescripcion = new JLabel("Descripcion: " + descripcion);
		mensajeDescripcion.setBounds(40, 75, 400, 30);
		mensajeDescripcion.setFont(new Font("Bold", Font.PLAIN, 13));
		add(mensajeDescripcion);
		
		JLabel mensajeFechaI = new JLabel("Fecha de inicio: " + fechaInicio);
		mensajeFechaI.setBounds(40, 100, 400, 30);
		mensajeFechaI.setFont(new Font("Bold", Font.PLAIN, 13));
		add(mensajeFechaI);
		
		JLabel mensajeFechaF = new JLabel("Fecha estimada de finalizacion: " + fechaFin);
		mensajeFechaF.setBounds(40, 125, 400, 30);
		mensajeFechaF.setFont(new Font("Bold", Font.PLAIN, 13));
		add(mensajeFechaF);
		
		JLabel mensajeParticipantes = new JLabel("Participantes: ");
		mensajeParticipantes.setBounds(40, 150, 400, 30);
		mensajeParticipantes.setFont(new Font("Bold", Font.PLAIN, 13));
		add(mensajeParticipantes);
		
		setListaParticipantes(participantes);
	    scrollPane.setBounds(130, 155, 200, 60);
	    add(scrollPane);
	}
	
	
	public void setListaParticipantes(ArrayList<String> participantes)
	{
		JList<String> listaParticipantes = new JList<String>(participantes.toArray(new String[participantes.size()]));
		listaParticipantes.setLayoutOrientation(JList.VERTICAL);
	    scrollPane.setViewportView(listaParticipantes);
	}
	
}
