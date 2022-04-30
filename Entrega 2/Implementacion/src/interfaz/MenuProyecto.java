package interfaz;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;

import modelo.Actividad;
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
		ArrayList<String> logins = new ArrayList<String>(participantesProyecto.keySet());
		
		p1 = new PanelProyecto1(this, nombre, descripcion,
				                fechaInicio, fechaFin, logins);
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


	//MODIFICAR REGISTRO
	public void elegirTituloRegistro()
	{
		CoordinadorProyecto coordinadorProyecto = ventana.getCoordinadorProyecto();
		HashMap<String, ArrayList<Actividad>> actividades = coordinadorProyecto.getActividades();
		ArrayList<String> titulos = new ArrayList<String>(actividades.keySet());
		
		DialogElegirRegistro selectReg = new DialogElegirRegistro(this); 
		
		for (String titulo : titulos)
		{
			selectReg.addTituloDesplegable(titulo);
		}
		
		selectReg.setVisible(true);
	}
	
	
	public void addRegistros(DialogElegirRegistro selectReg, String titulo)
	{
		CoordinadorProyecto coordinadorProyecto = ventana.getCoordinadorProyecto();
		HashMap<String, ArrayList<Actividad>> actividades = coordinadorProyecto.getActividades();
		ArrayList<Actividad> registros = actividades.get(titulo);
	
		for (Actividad registro : registros)
		{
			String fechaAct = registro.getFecha();
			selectReg.addFechaDesplegable(fechaAct);
		}
	}
	
	
	public void modificarRegistro(String titulo, int index)
	{
		CoordinadorProyecto coordinadorProyecto = ventana.getCoordinadorProyecto();
		HashMap<String, ArrayList<Actividad>> actividades = coordinadorProyecto.getActividades();
		Actividad registro = actividades.get(titulo).get(index);
		
		String descripcion = registro.getDescripcion();
		String autor = registro.getAutor().getNombre();
		String fecha = registro.getFecha();
		String horaInicio = registro.getHoraInicio();
		String horaFin = registro.getHoraFin();
		
		DialogModificarRegistro settingsReg = new DialogModificarRegistro(this,
									  titulo, index, fecha, horaInicio, horaFin);
	}
	
	
	public void actualizarRegistro(String titulo, int index,
			String fecha, String horaInicio, String horaFin)
	{
		CoordinadorProyecto coordinadorProyecto = ventana.getCoordinadorProyecto();
		coordinadorProyecto.modificarFechaActividad(titulo, index, fecha);
		coordinadorProyecto.modificarHoraInicio(titulo, index, horaInicio);
		coordinadorProyecto.modificarHoraFin(titulo, index, horaFin);
		coordinadorProyecto.guardarArchivo();
	}
	
	
	
	//GENERAR REPORTE
	public void elegirParticipante()
	{
		CoordinadorProyecto coordinadorProyecto = ventana.getCoordinadorProyecto();
		HashMap<String, Participante> participantesProyecto = coordinadorProyecto.getParticipantes();
		ArrayList<String> logins = new ArrayList<String>(participantesProyecto.keySet());
		
		DialogElegirParticipante selectPart = new DialogElegirParticipante(this);
		
		for (String login : logins)
		{
			selectPart.addParticipanteDesplegable(login);
		}
		
		selectPart.setVisible(true);
	}
	
	
	public void generarReporte(String login)
	{
		ArchivoUsuarios archivoUsuarios = ventana.getArchivoUsuarios();
		CoordinadorProyecto coordinadorProyecto = ventana.getCoordinadorProyecto();
		
		Participante participante = archivoUsuarios.getParticipante(login);
		ArrayList<Actividad> actividadesMiembro = coordinadorProyecto.actividadesMiembro(login);
		int total = coordinadorProyecto.tiempoTotal(actividadesMiembro);
		HashMap<String, Double> promedios = coordinadorProyecto.tiempoPorActividad(actividadesMiembro);
		
		System.out.println("\nNombre: " + participante.getNombre());
		System.out.println("TIEMPO TOTAL: " + total + " minutos\n");
		
		System.out.println("TIEMPO PROMEDIO POR TIPO DE ACTIVIDAD: ");
		
		for(String tipo: promedios.keySet())
		{
			double promedio = promedios.get(tipo);
			System.out.println("- " + tipo + ": " + promedio + " minutos");
		}
		
		System.out.println("\n\n");
		
	}



}
