package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import consola.ArchivoUsuarios;
import modelo.Participante;


public class MenuLogin extends Menu
{
	private VentanaAplicacion ventana;	
	private PanelLogin1 p1;
	private PanelLogin2 p2;
	
	
	public MenuLogin(VentanaAplicacion padre)
	{
		super(1, "INGRESO A LA APLICACION");
		this.ventana = padre;
		ventana.enableBotonContinuar(false);
		setLayout(new GridLayout(2, 1, 20, 20));
		
		p1 = new PanelLogin1(this);
		add(p1);
		
		p2 = new PanelLogin2(this);
		add(p2);
	}
	
	
	public void ingresarLogin(String loginEnUso)
	{
		ArchivoUsuarios archivoUsuarios = ventana.getArchivoUsuarios();
		Participante usuarioEnUso = archivoUsuarios.getParticipante(loginEnUso);
		
		if (usuarioEnUso == null)
		{
			p1.userNotFound();				
		}
		
		else
		{
			p1.userFound();
			p2.disableFields();
			ventana.setLoginEnUso(loginEnUso);
			ventana.setUsuarioEnUso(usuarioEnUso);
			ventana.enableBotonContinuar(true);
		}
	}
	
	
	public void ingresarLogin(String login, String nombre)
	{
		ArchivoUsuarios archivoUsuarios = ventana.getArchivoUsuarios();
		Participante usuarioEnUso = archivoUsuarios.getParticipante(login);
		
		if (usuarioEnUso == null)
		{
			p2.userNonExistent();
			p1.disableFields();
			usuarioEnUso = new Participante(login, nombre);
			ventana.setUsuarioEnUso(usuarioEnUso);
			ventana.setLoginEnUso(login);
			ventana.enableBotonContinuar(true);				
		}
		
		else
		{
			p2.userExistent();
		}
	}

}
