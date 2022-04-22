package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class PanelLogin2 extends JPanel implements ActionListener
{
	private MenuLogin padre;
	
	private JTextField cuadroLogin;
	private JTextField cuadroNombre;
	private JButton botonEntrar;
	private JLabel textLabel;
	
	
	public PanelLogin2(MenuLogin padre)
	{
		this.padre = padre;
		setLayout(null);
		setBorder(new EtchedBorder());
		
		JLabel titulo = new JLabel("Si desea registrarse como un usuario nuevo:");
		titulo.setBounds(40, 10, 600, 30);
		titulo.setFont(new Font("Bold", Font.BOLD, 13));
		add(titulo);
		
		JLabel mensajeLogin = new JLabel("Ingrese su login:");
		mensajeLogin.setBounds(145, 55, 100, 30);
		mensajeLogin.setFont(new Font("Bold", Font.PLAIN, 13));
		add(mensajeLogin);
		
		cuadroLogin = new JTextField();
		cuadroLogin.setBounds(275, 59, 150, 23);
		add(cuadroLogin);
		
		JLabel mensajeNombre = new JLabel("Ingrese su nombre:");
		mensajeNombre.setBounds(145, 85, 120, 30);
		mensajeNombre.setFont(new Font("Bold", Font.PLAIN, 13));
		add(mensajeNombre);
		
		cuadroNombre = new JTextField();
		cuadroNombre.setBounds(275, 89, 150, 23);
		add(cuadroNombre);
		
		botonEntrar = new JButton("Registrar");
		botonEntrar.setBounds(220, 127, 100, 25);
		botonEntrar.addActionListener(this);
		add(botonEntrar);
		
		textLabel = new JLabel("");
		textLabel.setBounds(40, 170, 600, 23);
		textLabel.setForeground(new Color(105, 105, 105));
		this.add(textLabel);

	}
	
	
	public void userNonExistent()
	{
		disableFields();
		String texto = "Por favor, haga click en el boton 'Continuar'";
		textLabel.setText(texto);
	}
	
	
	public void userExistent()
	{
		String texto = "El usuario ya se encuentra registrado"
			    + " en el sistema. Por favor intente con otro";
	
		textLabel.setText(texto);
	}
	
	
	public void disableFields()
	{
		cuadroLogin.setEditable(false);
		cuadroNombre.setEditable(false);
		botonEntrar.setEnabled(false);
	}
	

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==botonEntrar)
        {
			String login = cuadroLogin.getText();
            String nombre = cuadroNombre.getText();
            
			if (login.equals("") || nombre.equals(""))
			{
				String texto = "Por favor complete los campos";
				textLabel.setText(texto);
			}
			else
			{
				padre.ingresarLogin(login, nombre);
			}
        }
    }

	
}
