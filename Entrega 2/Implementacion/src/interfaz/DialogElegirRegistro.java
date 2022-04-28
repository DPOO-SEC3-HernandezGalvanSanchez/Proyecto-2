package interfaz;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DialogElegirRegistro extends JDialog implements ActionListener
{
	private MenuProyecto padre;
	private String tituloAct;
	
	private JPanel selectRegistro;
	private JComboBox<String> desplegableT;
	private JComboBox<String> desplegableR;
	private JButton botonContinuar;
	private JButton botonAceptar;
	
	
	public DialogElegirRegistro(MenuProyecto padre)
	{
		this.padre = padre;
		
		selectRegistro = new JPanel();
		selectRegistro.setLayout(null);
		
		int y = 25;
		
		//Seleccion del titulo
		JLabel mensajeT = new JLabel("Seleccione el titulo de la actividad:");
		mensajeT.setBounds(20, y, 210, 30);
		mensajeT.setFont(new Font("Bold", Font.PLAIN, 13));
		selectRegistro.add(mensajeT);
		
		desplegableT = new JComboBox<String>();
		desplegableT.setBounds(290, y + 4, 170, 23);
		selectRegistro.add(desplegableT);
		y += 40;
		
		//Seleccion del registro segun su fecha
		JLabel mensajeR = new JLabel("Seleccione la fecha del registro a modificar:");
		mensajeR.setBounds(20, y, 250, 30);
		mensajeR.setFont(new Font("Bold", Font.PLAIN, 13));
		selectRegistro.add(mensajeR);
		
		desplegableR = new JComboBox<String>();
		desplegableR.setBounds(290, y + 4, 170, 23);
		desplegableR.setEnabled(false);
		selectRegistro.add(desplegableR);
		y += 70;
		
		//Boton de continuar
		botonContinuar = new JButton("Continuar");
		botonContinuar.setBounds(197, y, 100, 25);
		botonContinuar.addActionListener(this);
		add(botonContinuar);
		
		//Boton de aceptar
		botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(197, y, 100, 25);
		botonAceptar.addActionListener(this);
		botonAceptar.setVisible(false);
		add(botonAceptar);
		
		//Settings del JDialog
		add(selectRegistro);
		setTitle("Seleccionar registro");
		setModal(true);
		setSize(500, 220);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	
	public void addTituloDesplegable(String titulo)
	{
		desplegableT.addItem(titulo);
	}
	
	
	public void addFechaDesplegable(String fecha)
	{
		desplegableR.addItem(fecha);
	}
	
	
	//METODOS DEL LISTENER
	private void continuar()
	{
		
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==botonContinuar)
		{
			System.out.println("Continuar");
			tituloAct = desplegableT.getSelectedItem().toString();
			padre.addRegistros(this, tituloAct);
			
			desplegableT.setEnabled(false);
			desplegableR.setEnabled(true);
			botonContinuar.setVisible(false);
			botonAceptar.setVisible(true);
		}
		
		else if (e.getSource()==botonAceptar)
		{
			System.out.println("Aceptar");
			int index = desplegableR.getSelectedIndex();
			padre.modificarRegistro(tituloAct, index);
			//this.dispose();
		}
	}
	
	
}
