package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class establecerConeccion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfIpMonitor;
	private JTextField tfPuertoMonitor;
	private JTextField tfIPBaseDeDatos;
	private JTextField tfNombreBase;
	private JTextField tfUsusarioBase;
	private JPasswordField pContraseñaBase;
	private JButton btnAceptar;
	private JButton btnDefault;
	

	public establecerConeccion() {
		setTitle("Selector de Servidores");
		setBounds(100, 100, 450, 166);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			btnAceptar= new JButton("Aceptar");
			btnAceptar.setBounds(315, 91, 71, 23);
			contentPanel.add(btnAceptar);
			btnAceptar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnAceptar);
		}
		{
			btnDefault = new JButton("Valores Predeterminados");
			btnDefault.setBounds(273, 57, 151, 23);
			contentPanel.add(btnDefault);
			btnDefault.setActionCommand("Cancel");
		}
		{
			JLabel lblMonitorDeCocina = new JLabel("Monitor de Cocina");
			lblMonitorDeCocina.setBounds(124, 11, 200, 35);
			contentPanel.add(lblMonitorDeCocina);
		}
		{
			JLabel lblIpmonitor = new JLabel("IP del Monitor:");
			lblIpmonitor.setBounds(10, 43, 71, 50);
			contentPanel.add(lblIpmonitor);
		}
		{
			JLabel lblPuerto = new JLabel("Puerto:");
			lblPuerto.setBounds(45, 91, 36, 35);
			contentPanel.add(lblPuerto);
		}
		{
			JLabel lblBaseDeDatos = new JLabel("Base de Datos");
			lblBaseDeDatos.setBounds(137, 132, 88, 23);
			contentPanel.add(lblBaseDeDatos);
		}
		
		tfIpMonitor = new JTextField();
		tfIpMonitor.setBounds(91, 58, 157, 20);
		contentPanel.add(tfIpMonitor);
		tfIpMonitor.setColumns(10);
		
		tfPuertoMonitor = new JTextField();
		tfPuertoMonitor.setColumns(10);
		tfPuertoMonitor.setBounds(91, 98, 157, 20);
		contentPanel.add(tfPuertoMonitor);
		{
			tfIPBaseDeDatos = new JTextField();
			tfIPBaseDeDatos.setColumns(10);
			tfIPBaseDeDatos.setBounds(167, 166, 157, 20);
			contentPanel.add(tfIPBaseDeDatos);
		}
		{
			tfNombreBase = new JTextField();
			tfNombreBase.setColumns(10);
			tfNombreBase.setBounds(167, 197, 157, 20);
			contentPanel.add(tfNombreBase);
		}
		{
			tfUsusarioBase = new JTextField();
			tfUsusarioBase.setColumns(10);
			tfUsusarioBase.setBounds(167, 228, 157, 20);
			contentPanel.add(tfUsusarioBase);
		}
		{
			JLabel lblNewLabel = new JLabel("IP de la Base de Datos:");
			lblNewLabel.setBounds(38, 165, 114, 23);
			contentPanel.add(lblNewLabel);
		}
		{
			pContraseñaBase = new JPasswordField();
			pContraseñaBase.setBounds(167, 259, 157, 20);
			contentPanel.add(pContraseñaBase);
		}
		
		JLabel lblNombreDeLa = new JLabel("Nombre de la Base de Datos:");
		lblNombreDeLa.setBounds(10, 200, 142, 14);
		contentPanel.add(lblNombreDeLa);
		
		JLabel lblUsuarioDeBase = new JLabel("Usuario de Base de Datos:");
		lblUsuarioDeBase.setBounds(20, 231, 132, 14);
		contentPanel.add(lblUsuarioDeBase);
		
		JLabel lblContraseaBaseDe = new JLabel("Contrase\u00F1a Base de Datos:");
		lblContraseaBaseDe.setBounds(15, 262, 132, 14);
		contentPanel.add(lblContraseaBaseDe);
	}


	public JTextField getTfIpMonitor() {
		return tfIpMonitor;
	}


	public void setTfIpMonitor(JTextField tfIpMonitor) {
		this.tfIpMonitor = tfIpMonitor;
	}


	public JTextField getTfPuertoMonitor() {
		return tfPuertoMonitor;
	}


	public void setTfPuertoMonitor(JTextField tfPuertoMonitor) {
		this.tfPuertoMonitor = tfPuertoMonitor;
	}


	public JTextField getTfIPBaseDeDatos() {
		return tfIPBaseDeDatos;
	}


	public void setTfIPBaseDeDatos(JTextField tfIPBaseDeDatos) {
		this.tfIPBaseDeDatos = tfIPBaseDeDatos;
	}


	public JTextField getTfNombreBase() {
		return tfNombreBase;
	}


	public void setTfNombreBase(JTextField tfNombreBase) {
		this.tfNombreBase = tfNombreBase;
	}


	public JTextField getTfUsusarioBase() {
		return tfUsusarioBase;
	}


	public void setTfUsusarioBase(JTextField tfUsusarioBase) {
		this.tfUsusarioBase = tfUsusarioBase;
	}


	public JPasswordField getpContraseñaBase() {
		return pContraseñaBase;
	}


	public void setpContraseñaBase(JPasswordField pContraseñaBase) {
		this.pContraseñaBase = pContraseñaBase;
	}


	public JButton getBtnAceptar() {
		return btnAceptar;
	}


	public void setBtnAceptar(JButton btnAceptar) {
		this.btnAceptar = btnAceptar;
	}


	public JButton getBtnDefault() {
		return btnDefault;
	}


	public void setBtnDefault(JButton btnDefault) {
		this.btnDefault = btnDefault;
	}
	
}
