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


public class PanelLogin1 extends JPanel implements ActionListener
{
	private MenuLogin padre;
	
	private JTextField cuadroTexto;
	private JButton botonEntrar;
	private JLabel textLabel;
	
	
	public PanelLogin1(MenuLogin padre)
	{
		this.padre = padre;
		setLayout(null);
		setBorder(new EtchedBorder());
		
		JLabel titulo = new JLabel("Si ya se encuentra registrado en la aplicacion:");
		titulo.setBounds(40, 10, 600, 30);
		titulo.setFont(new Font("Bold", Font.BOLD, 13));
		add(titulo);
		
		JLabel mensaje = new JLabel("Ingrese su login:");
		mensaje.setBounds(175, 65, 100, 30);
		mensaje.setFont(new Font("Bold", Font.PLAIN, 13));
		add(mensaje);
		
		cuadroTexto = new JTextField();
		cuadroTexto.setBounds(275, 72, 100, 20);
		add(cuadroTexto);
		
		botonEntrar = new JButton("entrar");
		botonEntrar.setBounds(220, 105, 100, 20);
		botonEntrar.addActionListener(this);
		add(botonEntrar);
		
		textLabel = new JLabel("");
		textLabel.setBounds(40, 160, 600, 20);
		textLabel.setForeground(new Color(105, 105, 105));
		this.add(textLabel);

	}
	
	
	public void userFound()
	{
		disableFields();
		String texto = "Por favor, haga click en el boton 'Continuar'";
	
		textLabel.setText(texto);
	}
	
	
	public void userNotFound()
	{
		String texto = "Su usuario no se encuentra registrado"
			     + " en el sistema. Por favor intente de nuevo";
	
		textLabel.setText(texto);
	}
	
	
	public void disableFields()
	{
		cuadroTexto.setEditable(false);
		botonEntrar.setEnabled(false);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
        if (e.getSource()==botonEntrar)
        {
            String login = cuadroTexto.getText();
            
            if (login.equals(""))
			{
				String texto = "Por favor complete el campo";
				textLabel.setText(texto);
			}         
            else
            {
            	padre.ingresarLogin(login);
            }            
        }
    }

}
