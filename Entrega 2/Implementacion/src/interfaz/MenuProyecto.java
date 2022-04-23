package interfaz;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;

import modelo.CoordinadorProyecto;
import modelo.Participante;
import procesamiento.ArchivoUsuarios;


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
		
		p1 = new PanelProyecto1(this, nombre, descripcion,
				                fechaInicio, fechaFin, nombres);
		add(p1);
		
		p2 = new PanelProyecto2(this);
		add(p2);
	}
	
	//PRIMER PANEL
	public String getNombreParticipante(String login)
	{
		ArchivoUsuarios archivoUsuarios = ventana.getArchivoUsuarios();
		Participante participante = archivoUsuarios.getParticipante(login);
		
		return participante.getNombre();
	}
	
	
	//AGREGAR PARTICIPANTE
	public void newParticipantSettings()
	{
		new DialogAgregarParticipante(this);
	}
	
	public void agregarParticipante(String login, String nombre)
	{
		CoordinadorProyecto coordinadorProyecto = ventana.getCoordinadorProyecto();
		coordinadorProyecto.agregarParticipante(new Participante(login, nombre));
		coordinadorProyecto.guardarArchivo(); //REVISAR
		ventana.refresh(); //REVISAR
		
		HashMap<String, Participante> participantesProyecto = coordinadorProyecto.getParticipantes();
		ArrayList<String> nombres = new ArrayList<String>(participantesProyecto.keySet());
		
		p1.setListaParticipantes(nombres);
	}
	
	
	//AGREGAR ACTIVIDAD
	public void newActivitySettings()
	{
		DialogRegistrarActividad settingsAct = new DialogRegistrarActividad(this);
		CoordinadorProyecto coordinadorProyecto = ventana.getCoordinadorProyecto();
		
		//Menu desplegable de tipos
		ArrayList<String> tipos = coordinadorProyecto.getTiposActividades();
		
		for (String tipo : tipos)
		{
			settingsAct.addTipoDesplegable(tipo);
		}
		
		//Menu desplegable de participantes
		String loginAutor = ventana.getUsuarioEnUso().getLogin();
		
		HashMap<String, Participante> participantesProyecto = coordinadorProyecto.getParticipantes();
		ArrayList<String> logins = new ArrayList<String>(participantesProyecto.keySet());
		settingsAct.addParticipanteDesplegable(loginAutor);
		
		for (String login : logins)
		{
			if (!login.equals(loginAutor))
			{
				settingsAct.addParticipanteDesplegable(login);
			}
		}
		
		//Mostrar cuadro de dialogo
		settingsAct.setVisible(true);
	}
	
	public void agregarActividad(String tipoActividad, String titulo, String descripcion,
								 String horaInicio, String loginAutor)
	{
		String fecha = ventana.getFecha();
		String horaActual = ventana.getHora();

		ArchivoUsuarios archivoUsuarios = ventana.getArchivoUsuarios();
		Participante autor = archivoUsuarios.getParticipante(loginAutor);
		
		CoordinadorProyecto coordinadorProyecto = ventana.getCoordinadorProyecto();
		coordinadorProyecto.registrarActividad(tipoActividad, titulo, descripcion,
											   fecha, horaInicio, horaActual, autor);
		coordinadorProyecto.guardarArchivo();
	}


}
