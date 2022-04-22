package interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import consola.ArchivoUsuarios;
import modelo.CoordinadorProyecto;
import modelo.Participante;


public class MenuEleccionProyecto extends Menu
{
	
	private VentanaAplicacion ventana;
	private PanelEleccion1 p1;
	private PanelEleccion2 p2;
	
	public MenuEleccionProyecto(VentanaAplicacion padre)
	{
		super(2, "ELECCION DEL PROYECTO");
		this.ventana = padre;
		ventana.enableBotonContinuar(false);
		setLayout(new GridLayout(2, 1));
		
		p1 = new PanelEleccion1(this);
		addProyectosParticipante();
		add(p1);
		
		p2 = new PanelEleccion2(this);
		add(p2);
		
	}
	
	
	public void addProyectosParticipante()
	{
		String loginEnUso = ventana.getLoginEnUso();
		ArchivoUsuarios archivoUsuarios = ventana.getArchivoUsuarios();
		ArrayList<String> proyectosDelUsuario = archivoUsuarios.getProyectosUsuario(loginEnUso);
		
		if (proyectosDelUsuario == null)
		{
			p1.disableFields();
		}
			
		else
		{
			int numProyectos = proyectosDelUsuario.size();
			
			for (int i=1; i<=numProyectos; i++)
			{
				int index = i-1;
				String opcionProyecto = proyectosDelUsuario.get(index);
				p1.addProyectoDesplegable(opcionProyecto);
			}
			
		}
	}
	
	
	public void setProyectoEnUso(String nombre)
	{
		p2.disableFields();
		CoordinadorProyecto coordinadorProyecto = ventana.getCoordinadorProyecto();
		coordinadorProyecto.seleccionarProyecto(nombre);
		ventana.enableBotonContinuar(true);
	}
	
	
	public void crearProyecto(String nombre, String descripcion, String fechaInicio,
			  				  String fechaFin, ArrayList<String> tiposActividades)
	{
		Participante autor = ventana.getUsuarioEnUso();
		CoordinadorProyecto coordinadorProyecto = ventana.getCoordinadorProyecto();			
		coordinadorProyecto.crearProyecto(nombre, descripcion, fechaInicio,
										  fechaFin, tiposActividades, autor);
	}
	
}
