package interfaz;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import modelo.CoordinadorProyecto;
import modelo.Participante;


@SuppressWarnings("serial")
public class MenuProyecto extends Menu
{
	private VentanaAplicacion ventana;	
	private PanelProyecto1 p1;
	private PanelProyecto2 p2;	
	
	
	public MenuProyecto(VentanaAplicacion padre)
	{
		super(3, "PROYECTO");
		this.ventana = padre;
		ventana.enableBotonContinuar(false);
		setLayout(new GridLayout(2, 1));
		
		CoordinadorProyecto coordinadorProyecto = padre.getCoordinadorProyecto();
		String nombre = coordinadorProyecto.getNombreProyecto();
		String descripcion = coordinadorProyecto.getDescripcionProyecto();
		String fechaInicio = coordinadorProyecto.getFechaInicio();
		String fechaFin = coordinadorProyecto.getFechaFin();
		HashMap<String, Participante> participantesProyecto = coordinadorProyecto.getParticipantes();
		ArrayList<String> nombres = new ArrayList<String>(participantesProyecto.keySet());
		
		p1 = new PanelProyecto1(nombre, descripcion, fechaInicio,
								fechaFin, nombres);
		add(p1);
		
		p2 = new PanelProyecto2(this);
		add(p2);
	}
	
	
	//AGREGAR PARTICIPANTE
	public void newParticipantSettings()
	{
		DialogAgregarParticipante settingsPart = new DialogAgregarParticipante(this);
	}
	
	public void agregarParticipante(String login, String nombre)
	{
		CoordinadorProyecto coordinadorProyecto = ventana.getCoordinadorProyecto();
		coordinadorProyecto.agregarParticipante(new Participante(login, nombre));
		coordinadorProyecto.guardarArchivo(); //REVISAR
		
		HashMap<String, Participante> participantesProyecto = coordinadorProyecto.getParticipantes();
		ArrayList<String> nombres = new ArrayList<String>(participantesProyecto.keySet());
		
		p1.setListaParticipantes(nombres);
	}
}
