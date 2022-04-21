package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import consola.ArchivoUsuarios;
import modelo.CoordinadorProyecto;
import modelo.Participante;


public class VentanaAplicacion extends JFrame implements ActionListener
{
	private CoordinadorProyecto coordinadorProyecto = new CoordinadorProyecto();
	private ArchivoUsuarios archivoUsuarios = new ArchivoUsuarios();
	
	private String loginEnUso;
	private Participante usuarioEnUso;
	private String FECHA;
	private String HORA_ACTUAL;
	
	private Menu menuActual;
	private JPanel panelSuperior;
	private JPanel panelInferior;
	private JButton botonContinuar;
	private JButton botonAtras;
	
	
	
	public VentanaAplicacion()
	{
		setLayout(new BorderLayout());
		initPanelInferior();
		add(panelInferior, BorderLayout.SOUTH);
		
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy"); 
		FECHA = formatter1.format(Calendar.getInstance().getTime());
		
		SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm"); 
		HORA_ACTUAL = formatter2.format(Calendar.getInstance().getTime());
		
		menuActual = new MenuLogin(this);
		updateMenu();
		
		setSize(550, 600);
		setTitle("Proyecto 2");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	
	//AUXILIARES
	private void initPanelInferior()
	{
		panelInferior = new JPanel();
		panelInferior.setBackground(Color.white);
		
		panelInferior.setLayout(new BorderLayout());
		
		botonContinuar = new JButton("Continuar");
		botonContinuar.addActionListener(this);
		panelInferior.add(botonContinuar, BorderLayout.EAST);
		
		botonAtras = new JButton("Atras");
		botonAtras.addActionListener(this);
		botonAtras.setEnabled(false);
		panelInferior.add(botonAtras, BorderLayout.WEST);		
	}
	
	
	private void updateMenu()
	{
		if (panelSuperior!=null)
		{
			panelSuperior.setVisible(false);
		}
		
		String header = menuActual.getHeader();
		JLabel titulo = new JLabel(header);
		titulo.setFont(new Font("Bold", Font.BOLD, 15));
		
		panelSuperior = new JPanel();
		panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		panelSuperior.add(titulo);
		
		this.add(panelSuperior, BorderLayout.NORTH);
		this.add(menuActual, BorderLayout.CENTER);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==botonContinuar)
        {
            Integer menuID = menuActual.getID();
            
            if (menuID.equals(1))
            {
            	System.out.println("SIGUIENTE 1");
            	botonAtras.setEnabled(true);
            	menuActual.setVisible(false);
            	menuActual = new MenuEleccionProyecto(this);
            	updateMenu();
            }
            
            else if (menuID.equals(2))
            {
            	System.out.println("SIGUIENTE 2");
            }
            
        }
		
		else if (e.getSource()==botonAtras)
        {
            Integer menuID = menuActual.getID();
            
            if (menuID.equals(2))
            {
            	System.out.println("ATRAS 2");
            	botonAtras.setEnabled(false);
            	menuActual.setVisible(false);
            	menuActual = new MenuLogin(this);
            	updateMenu();
            }
            
        }
    }
	
	
	//GETTERS y SETTERS
	public ArchivoUsuarios getArchivoUsuarios()
	{
		return archivoUsuarios;
	}
	
	public CoordinadorProyecto getArchivoProyectos()
	{
		return coordinadorProyecto;
	}
	
	public void setLoginEnUso(String login)
	{
		this.loginEnUso = login;
	}
	
	public void setUsuarioEnUso(Participante usuario)
	{
		this.usuarioEnUso = usuario;
	}
	
	public void enableBotonContinuar(boolean bool)
	{
		botonContinuar.setEnabled(bool);
	}
	
	
	
	//EJECUCION
	public static void main(String[] args)
	{
		new VentanaAplicacion();
	}
	
}
