package consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import modelo.Actividad;
import modelo.CoordinadorProyecto;
import modelo.Participante;


public class Aplicacion
{
	private CoordinadorProyecto coordinadorProyecto = new CoordinadorProyecto();
	private ArchivoUsuarios archivoUsuarios = new ArchivoUsuarios();
	
	private String loginEnUso;
	private Participante usuarioEnUso;
	private String FECHA;
	private String HORA_ACTUAL;
	
	
	
	//EJECUCION GENERAL DE LA APLICACION
	public void ejecutarAplicacion()
	{
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy"); 
		FECHA = formatter1.format(Calendar.getInstance().getTime());
		
		SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm"); 
		HORA_ACTUAL = formatter2.format(Calendar.getInstance().getTime());
		
		System.out.println("BIENVENIDO A LA APLICACION");
		System.out.println("\nAntes de empezar, por favor tenga en cuenta que se utilizaran");
		System.out.println("los siguientes como parametros temporales:");
		System.out.println("Fecha: " + FECHA);
		System.out.println("Hora: " + HORA_ACTUAL);
		System.out.println("\n-------------------------------------------------------------");
		ingresarLogin();
		ejecutarEleccionProyecto();
		ejecutarManipularProyecto();
	}
	
	
	private void ingresarLogin() {
		this.loginEnUso = input("Ingrese su login uniandes");
		usuarioEnUso = archivoUsuarios.getParticipante(loginEnUso);
		
		if (usuarioEnUso == null)
		{
			System.out.println("\nUsted no se encuentra registrado en el sistema\n");
			String nombreUsuario = input("Por favor ingrese su nombre");
			System.out.println("\nEl correo a usar sera: " + loginEnUso + "@uniandes.edu.co");
			usuarioEnUso = new Participante(loginEnUso, nombreUsuario);
		}
		
		else
		{
			System.out.println("\nSu informacion de sesion es la siguiente");
			System.out.println("Nombre: " + usuarioEnUso.getNombre());
			System.out.println("Correo: " + usuarioEnUso.getCorreo());
		}
	}
	
	
	private void ejecutarEleccionProyecto() 
	{
		boolean continuar = true;
		
		while (continuar)
		{
			try
			{
				mostrarMenuParticipante();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opcion"));
				if (opcion_seleccionada == 1)
				{
					ejecutarCrearProyecto();
					continuar = false;
				}
				
				else if (opcion_seleccionada == 2)
				{
					continuar = ejecutarSeleccionarProyecto();
				}
				
				else if (opcion_seleccionada == 3)
				{
					coordinadorProyecto.guardarArchivo();
					System.exit(0);
				}
				
				else
				{
					System.out.println("\nPor favor seleccione una opcion valida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("\nDebe seleccionar uno de los numeros de las opciones.");
			}
		}
	}
	
	
	private void ejecutarManipularProyecto() 
	{
		boolean continuar = true;
		
		while (continuar)
		{
			try
			{
				mostrarMenuProyecto();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opcion"));
				if (opcion_seleccionada == 1)
				{
					ejecutarAgregarParticipante();
				}
				
				else if (opcion_seleccionada == 2)
				{
					ejecutarRegistrarActividad();
				}
				
				else if (opcion_seleccionada == 3)
				{
					ejecutarModificarRegistro();
				}
				
				else if (opcion_seleccionada == 4)
				{
					ejecutarMostrarReporte();
				}
				
				else if (opcion_seleccionada == 5)
				{
					ejecutarEleccionProyecto();
				}
							
				else if (opcion_seleccionada == 6)
				{
					coordinadorProyecto.guardarArchivo();
					System.exit(0);
				}
				
				else
				{
					System.out.println("\nPor favor seleccione una opcion valida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("\nDebe seleccionar uno de los numeros de las opciones.");
			}
		}
	}
	
	
	private void ejecutarModificarRegistro()
	{
		HashMap<String, ArrayList<Actividad>> actividades = coordinadorProyecto.getActividades();
		String titulo = seleccionarTitulo(actividades);
		int index = seleccionarRegistro(actividades, titulo);
		
		boolean continuar = true;
		
		while (continuar)
		{
			try
			{				
				mostrarMenuRegistro(actividades, titulo, index);
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opcion"));
				if (opcion_seleccionada == 1)
				{
					modificarFechaActividad(titulo, index);
				}
				
				else if (opcion_seleccionada == 2)
				{
					modificarHoraInicio(titulo, index);
				}
				
				else if (opcion_seleccionada == 3)
				{
					modificarHoraFin(titulo, index);
				}
				
				else if (opcion_seleccionada == 4)
				{
					ejecutarManipularProyecto();
				}
				
				else if (opcion_seleccionada == 5)
				{
					coordinadorProyecto.guardarArchivo();
					System.exit(0);
				}
				
				else
				{
					System.out.println("\nPor favor seleccione una opcion valida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("\nDebe seleccionar uno de los numeros de las opciones.");
			}
		}
	}
	
	
	
	//ELECCION DEL PROYECTO
	private void mostrarMenuParticipante() {
		System.out.println("\n\n----------------------------------");
		System.out.println("MENU DE ELECCION DEL PROYECTO");
		System.out.println("----------------------------------");
		
		System.out.println("\nUsted puede realizar las siguientes acciones:");
		System.out.println("1. Crear un nuevo proyecto");
		System.out.println("2. Seleccionar un proyecto existente");
		System.out.println("3. Cerrar la aplicacion\n");
	}
	
	
	private void ejecutarCrearProyecto()
	{
		String nombreProyecto = input("\nIngrese el nombre del nuevo proyecto");
		String descripcion = input("Ingrese la descripcion");
		String fechaInicio = input("Ingrese la fecha de inicio (si es hoy, digite 0)");
		String fechaFin = input("Ingrese la fecha de finalizacion");
		
		if (fechaInicio.equals("0"))
			fechaInicio = FECHA;
		
		ArrayList<String> tiposActividades = new ArrayList<String>();
		
		int numTipos = Integer.parseInt(input("Ingrese cuantos tipos de actividades desea tener"));
		
		for (int i = 1; i <= numTipos; i++)
		{
			String tipo = input("Ingrese el tipo " + i);
			tiposActividades.add(tipo);
		}
		
		archivoUsuarios.guardarProyecto(loginEnUso, nombreProyecto);
		coordinadorProyecto.crearProyecto(nombreProyecto, descripcion, fechaInicio,
										  fechaFin, tiposActividades, usuarioEnUso);
		
		System.out.println("\nEl proyecto fue creado con exito");
		
	}
	
	
	private boolean ejecutarSeleccionarProyecto()
	{
		boolean continuar = true;
		ArrayList<String> proyectosDelUsuario = archivoUsuarios.getProyectosUsuario(loginEnUso);
		
		if (proyectosDelUsuario == null)
			System.out.println("\nUsted no tiene ningun proyecto registrado en el sistema");
		
		else
		{
			int numProyectos = proyectosDelUsuario.size();
			System.out.println("\nSe encontraron los siguientes proyectos en los que usted participa:");
			
			for (int i=1; i<=numProyectos; i++)
			{
				int index = i-1;
				String opcionProyecto = proyectosDelUsuario.get(index);
				System.out.println(i + ". " + opcionProyecto);
			}
			
			int seleccionProyecto = Integer.parseInt(input("\nIngrese la opcion del proyecto que desea seleccionar"));
			int index = seleccionProyecto - 1;
			String nombreProyecto = proyectosDelUsuario.get(index);
			coordinadorProyecto.seleccionarProyecto(nombreProyecto);
			continuar = false;
		}
		
		return continuar;
	}
	
	
	
	//MANIPULACION DEL PROYECTO
	private void mostrarMenuProyecto() {
		
		System.out.println("\n\n----------------------------------");
		System.out.println("INFORMACION DEL PROYECTO\n");
		System.out.println("Nombre: " + coordinadorProyecto.getNombreProyecto());
		System.out.println("Descripcion: " + coordinadorProyecto.getDescripcionProyecto());
		System.out.println("Fecha de inicio: " + coordinadorProyecto.getFechaInicio());
		System.out.println("Fecha estimada de finalizacion: " + coordinadorProyecto.getFechaFin());
		
		
		System.out.println("\n----------------------------------");
		System.out.println("MENU DEL PROYECTO");
		System.out.println("----------------------------------");
		
		System.out.println("\nUsted puede realizar las siguientes acciones:");
		System.out.println("1. Agregar un participante");
		System.out.println("2. Registrar una actividad");
		System.out.println("3. Modificar el registro de actividades");
		System.out.println("4. Mostrar el reporte de cada uno de los participantes");
		System.out.println("5. Volver al menu de eleccion del proyecto");
		System.out.println("6. Cerrar la aplicacion\n");
	}
	
	
	private void ejecutarAgregarParticipante()
	{
		String nombreParticipante = input("\nIngrese el nombre del participante a agregar");
		String loginParticipante = input("Ingrese el login del participante a agregar");
		
		Participante nuevoParticipante = new Participante(loginParticipante, nombreParticipante);
		coordinadorProyecto.agregarParticipante(nuevoParticipante);
		
		System.out.println("\n" + nombreParticipante + " fue agregado con exito al proyecto");
	}
	
	
	private void ejecutarRegistrarActividad()
	{
		//Autor de la actividad
		Participante autor = usuarioEnUso;
		int preguntaParticipante = Integer.parseInt(input("\nSi el autor de la actividad es usted, digite 0. De lo contrario, digite 1"));
		
		if (preguntaParticipante != 0)
		{
			HashMap<String, Participante> participantesProyecto = coordinadorProyecto.getParticipantes();
			ArrayList<String> nombres = new ArrayList<String>(participantesProyecto.keySet());
			int numParticipantes = nombres.size();
				
			System.out.println("\nEl proyecto cuenta con los siguientes participantes:");
				
			for (int i = 1; i <= numParticipantes; i++)
			{
				int index = i - 1;
				String nombreParticipante = nombres.get(index);
				System.out.println("(" + i + ") " + nombreParticipante);
			}
				
			int opcionParticipante = Integer.parseInt(input("\nSeleccione el autor de la actividad")) - 1;
			String nombreParticipante = nombres.get(opcionParticipante);
			autor = participantesProyecto.get(nombreParticipante);
		}		
				
		
		//Tipo de actividad
		ArrayList<String> tiposActividades = coordinadorProyecto.getTiposActividades();
		int numTipos = tiposActividades.size();
		
		System.out.println("\nEl proyecto cuenta con los siguientes tipos de actividades:");
		
		for (int i = 1; i <= numTipos; i++)
		{
			int index = i - 1;
			String tipo = tiposActividades.get(index);
			System.out.println("(" + i + ") " + tipo);
		}
		
		int opcionTipo = Integer.parseInt(input("\nSeleccione el tipo es la actividad a registrar")) - 1;
		String tipoActividad = tiposActividades.get(opcionTipo);
		
		
		//Informacion de la actividad
		String titulo = input("\nIngrese el titulo de la actividad");
		String descripcion = input("Ingrese una descripcion");
		String horaInicio = input("Ingrese la hora de inicio");
		
		coordinadorProyecto.registrarActividad(tipoActividad, titulo, descripcion,
											   FECHA, horaInicio, HORA_ACTUAL, autor);
	}
	
	
	private void ejecutarMostrarReporte()
	{
		HashMap<String, Participante> participantes = coordinadorProyecto.getParticipantes();
		
		// TIEMPO TOTAL INVERTIDO
		for(String nombreParticipante:participantes.keySet())
		{
			System.out.println("\n\n-----------------------------------------");
			System.out.println("REPORTE DE: " + nombreParticipante);
			String login = participantes.get(nombreParticipante).getLogin();
			ArrayList<Actividad> actividadesMiembro = coordinadorProyecto.actividadesMiembro(login);
			System.out.println("\nActividades realizadas:");
			
			for(Actividad actividad:actividadesMiembro)
			{
				System.out.println("- Titulo: " + actividad.getTitulo());
				System.out.println("  Tipo: " + actividad.getTipoActividad());
				System.out.println("  Fecha: " + actividad.getFecha());
				System.out.println("  Duracion: " + actividad.getTiempo() + " minutos\n");
			}
			
			int total = coordinadorProyecto.tiempoTotal(actividadesMiembro);
			System.out.println("TIEMPO TOTAL INVERTIDO: " + total + " minutos\n");
			
			
			// TIEMPO PROMEDIO POR ACTIVIDAD
			System.out.println("TIEMPO PROMEDIO POR TIPO DE ACTIVIDAD: ");
			HashMap<String, Double> promedios = coordinadorProyecto.tiempoPorActividad(actividadesMiembro);
			
			for(String tipo: promedios.keySet())
			{
				double promedio = promedios.get(tipo);
				System.out.println("- " + tipo + ": " + promedio + " minutos");
			}
		}
	}
	
	
	
	//MODIFICAR REGISTRO DE ACTIVIDADES
	private String seleccionarTitulo(HashMap<String, ArrayList<Actividad>> actividades)
	{
		ArrayList<String> titulos = new ArrayList<String>(actividades.keySet());
		int numTitulos = titulos.size();
		
		System.out.println("\nSe tiene registro de actividades con los siguientes titulos: ");
		
		for (int i=1; i<=numTitulos; i++)
		{
			int index = i - 1;
			String tituloIndex = titulos.get(index);
			System.out.println(i + ". " + tituloIndex);
		}
		
		int index = Integer.parseInt(input("\nSeleccione el titulo de la actividad que busca")) - 1;
		String titulo = titulos.get(index);
		
		return titulo;
	}
	
	
	private int seleccionarRegistro(HashMap<String, ArrayList<Actividad>> actividades,
									String titulo)
	{
		ArrayList<Actividad> registros = actividades.get(titulo);
		int numRegistros = registros.size();
		
		System.out.println("\nLa actividad seleccionada presenta registros en las siguientes fechas: ");
		
		for (int i=1; i<=numRegistros; i++)
		{
			int index = i - 1;
			Actividad registro = registros.get(index);
			String fecha = registro.getFecha();
			String horaInicio = registro.getHoraInicio();
			String horaFin = registro.getHoraFin();
			System.out.println(i + ". " + fecha + " de " + horaInicio + " a " + horaFin);
		}
		
		int index = Integer.parseInt(input("\nSeleccione el registro que desea modificar")) - 1;
		
		return index;
	}
	
	
	private void mostrarMenuRegistro(HashMap<String, ArrayList<Actividad>> actividades,
									 String titulo, int index)
	{
		ArrayList<Actividad> homonimas = actividades.get(titulo);
		Actividad registro = homonimas.get(index);
		String nombreAutor = registro.getAutor().getNombre();
		
		
		System.out.println("\n\n----------------------------------");
		System.out.println("INFORMACION DE LA ACTIVIDAD\n");
		System.out.println("Titulo: " + registro.getTitulo());
		System.out.println("Descripcion: " + registro.getDescripcion());
		System.out.println("Autor: " + nombreAutor);
		System.out.println("Fecha: " + registro.getFecha());
		System.out.println("Hora de inicio: " + registro.getHoraInicio());
		System.out.println("Hora de terminacion: " + registro.getHoraFin());
		
		
		System.out.println("\n----------------------------------");
		System.out.println("MENU DE LA ACTIVIDAD");
		System.out.println("----------------------------------");
		
		System.out.println("\nUsted puede realizar las siguientes acciones:");
		System.out.println("1. Modificar fecha");
		System.out.println("2. Modificar hora de inicio");
		System.out.println("3. Modificar hora de terminacion");
		System.out.println("4. Volver al menu del proyecto");
		System.out.println("5. Cerrar la aplicacion\n");
	}
	
	
	private void modificarFechaActividad(String titulo, int index)
	{
		String nuevaFecha = input("\nPor favor ingrese la nueva fecha en formato dd/MM/yyyy");
		coordinadorProyecto.modificarFechaActividad(titulo, index, nuevaFecha);
		System.out.println("Se cambio con exito la fecha de la actividad");
	}
	
	
	private void modificarHoraInicio(String titulo, int index)
	{
		String nuevaHoraInicio = input("\nPor favor ingrese la hora de inicio en formato HH:mm");
		coordinadorProyecto.modificarHoraInicio(titulo, index, nuevaHoraInicio);
		System.out.println("Se cambio con exito la hora inicial de la actividad");
	}
	
	
	private void modificarHoraFin(String titulo, int index)
	{
		String nuevaHoraFin = input("\nPor favor ingrese la hora de terminacion en formato HH:mm");
		coordinadorProyecto.modificarHoraFin(titulo, index, nuevaHoraFin);
		System.out.println("Se cambio con exito la hora de terminacion de la actividad");
	}
	
	
	
	//METODOS AUXILIARES
	private String input(String mensaje)
	{
		/*
		 * METODO PARA INGRESAR INFORMACION POR CONSOLA
		 */
		
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	//INICIO DE LA APLICACION
	public static void main(String[] args)
	{
		Aplicacion consola = new Aplicacion();		
		consola.ejecutarAplicacion();
	}


	
}
