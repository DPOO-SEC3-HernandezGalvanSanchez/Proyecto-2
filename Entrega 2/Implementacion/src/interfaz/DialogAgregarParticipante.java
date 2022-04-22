package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class DialogAgregarParticipante extends JDialog implements ActionListener
{
	private MenuProyecto padre;
	
	private JPanel settingsPart;
	private JTextField cuadroLogin;
	private JTextField cuadroNombre;
	private JButton botonAceptar;
	private JLabel textLabel;
	
	
	public DialogAgregarParticipante(MenuProyecto padre)
	{
		this.padre = padre;
		
		settingsPart = new JPanel();
		settingsPart.setLayout(null);
		
		//Login
		JLabel mensajeLogin = new JLabel("Ingrese el login:");
		mensajeLogin.setBounds(20, 25, 150, 30);
		mensajeLogin.setFont(new Font("Bold", Font.PLAIN, 13));
		settingsPart.add(mensajeLogin);
		
		cuadroLogin = new JTextField();
		cuadroLogin.setBounds(220, 29, 150, 23);
		settingsPart.add(cuadroLogin);
		
		//Nombre
		JLabel mensajeNombre = new JLabel("Ingrese el nombre:");
		mensajeNombre.setBounds(20, 60, 200, 30);
		mensajeNombre.setFont(new Font("Bold", Font.PLAIN, 13));
		settingsPart.add(mensajeNombre);
		
		cuadroNombre = new JTextField();
		//cuadroNombre.setText("31/12/2022"); //TEMPORAL
		cuadroNombre.setBounds(220, 64, 150, 23);
		settingsPart.add(cuadroNombre);
		
		//Boton de aceptar
		botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(147, 110, 100, 25);
		botonAceptar.addActionListener(this);
		add(botonAceptar);
		
		//Mensaje de advertencia
		textLabel = new JLabel("");
		textLabel.setBounds(20, 150, 600, 23);
		textLabel.setForeground(Color.RED);
		this.add(textLabel);
		
		//Settings del JDialog
		add(settingsPart);
		setTitle("Datos del participante");
		setModal(true);
		setSize(400, 220);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		String login = cuadroLogin.getText();
		String nombre = cuadroNombre.getText();
		
		if (login.equals("") || nombre.equals(""))
		{
			String texto = "Por favor complete todos los campos";
			textLabel.setText(texto);
		}
		
		else
		{
			padre.agregarParticipante(login, nombre);
			this.dispose();
		}
	}
	
}
