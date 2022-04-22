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

public class PanelEleccion2 extends JPanel implements ActionListener
{
	private MenuEleccionProyecto padre;
	
	private JTextField cuadroNombre;
	private JTextField cuadroDescripcion;
	private JTextField cuadroNumTipos;
	private JButton botonCrear;
	private JLabel textLabel;
	
	
	public PanelEleccion2(MenuEleccionProyecto padre)
	{
		this.padre = padre;
		setLayout(null);
		setBorder(new EtchedBorder());
		
		JLabel titulo = new JLabel("Si desea crear un nuevo proyecto:");
		titulo.setBounds(40, 10, 600, 30);
		titulo.setFont(new Font("Bold", Font.BOLD, 13));
		add(titulo);
		
		JLabel mensajeNombre = new JLabel("Ingrese el nombre:");
		mensajeNombre.setBounds(85, 55, 150, 30);
		mensajeNombre.setFont(new Font("Bold", Font.PLAIN, 13));
		add(mensajeNombre);
		
		cuadroNombre = new JTextField();
		cuadroNombre.setBounds(295, 59, 150, 23);
		add(cuadroNombre);
		
		JLabel mensajeDescripcion = new JLabel("Ingrese una corta descripcion:");
		mensajeDescripcion.setBounds(85, 90, 200, 30);
		mensajeDescripcion.setFont(new Font("Bold", Font.PLAIN, 13));
		add(mensajeDescripcion);
		
		cuadroDescripcion = new JTextField();
		cuadroDescripcion.setBounds(295, 94, 150, 23);
		add(cuadroDescripcion);
		
		JLabel mensajeNumTipos = new JLabel("Ingrese el # de tipos de actividad:");
		mensajeNumTipos.setBounds(85, 125, 200, 30);
		mensajeNumTipos.setFont(new Font("Bold", Font.PLAIN, 13));
		add(mensajeNumTipos);
		
		cuadroNumTipos = new JTextField();
		cuadroNumTipos.setBounds(295, 129, 150, 23);
		add(cuadroNumTipos);
		
		botonCrear = new JButton("Crear");
		botonCrear.setBounds(220, 172, 90, 25);
		botonCrear.addActionListener(this);
		add(botonCrear);
		
		textLabel = new JLabel("");
		textLabel.setBounds(40, 160, 600, 20);
		textLabel.setForeground(new Color(105, 105, 105));
		this.add(textLabel);

	}
	
	
	public void setTiposActividades(int numTipos)
	{
		
	}
	
	
	public void disableFields()
	{
		cuadroNombre.setEditable(false);
		cuadroDescripcion.setEditable(false);
		cuadroNumTipos.setEditable(false);
		botonCrear.setEnabled(false);
	}
	

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==botonCrear)
        {
			String nombre = cuadroNombre.getText();
			String descripcion = cuadroDescripcion.getText();
            int numTipos = Integer.parseInt(cuadroNumTipos.getText());
            
			if (descripcion.equals("") || nombre.equals(""))
			{
				String texto = "Por favor complete los campos";
				textLabel.setText(texto);
			}
			else
			{
				setTiposActividades(numTipos);
				System.out.println("FALTA CREARLO");
			}
        }
    }
}
