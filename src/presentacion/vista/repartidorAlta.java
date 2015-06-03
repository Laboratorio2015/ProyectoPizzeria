package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class repartidorAlta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfDni;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfCelular;
	private JTextField tfFechaNacimiento;
	private JButton btnRegistrar;
	private JButton btnCalendario;
	

	public repartidorAlta() {
		setBounds(100, 100, 715, 436);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			tfDni = new JTextField();
			tfDni.setBounds(145, 179, 174, 22);
			contentPanel.add(tfDni);
			tfDni.setColumns(10);
		}
		{
			tfNombre = new JTextField();
			tfNombre.setColumns(10);
			tfNombre.setBounds(145, 215, 173, 22);
			contentPanel.add(tfNombre);
		}
		{
			tfApellido = new JTextField();
			tfApellido.setColumns(10);
			tfApellido.setBounds(145, 251, 173, 22);
			contentPanel.add(tfApellido);
		}
		{
			tfCelular = new JTextField();
			tfCelular.setColumns(10);
			tfCelular.setBounds(474, 179, 177, 22);
			contentPanel.add(tfCelular);
		}
		{
			tfFechaNacimiento = new JTextField();
			tfFechaNacimiento.setEditable(false);
			tfFechaNacimiento.setColumns(10);
			tfFechaNacimiento.setBounds(478, 215, 117, 22);
			contentPanel.add(tfFechaNacimiento);
		}
		{
			btnCalendario= new JButton("");
			btnCalendario.setIcon(new ImageIcon(repartidorAlta.class.getResource("/prototipos/Botones/calendario.gif")));
			btnCalendario.setBounds(605, 209, 40, 34);
			contentPanel.add(btnCalendario);
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(repartidorAlta.class.getResource("/prototipos/registro de repartidor.png")));
			label.setBounds(0, 0, 730, 399);
			contentPanel.add(label);
		}
		{
			JButton btnCancelar = new JButton("Cancel");
			btnCancelar.setOpaque(false);
			btnCancelar.setBounds(382, 329, 164, 34);
			contentPanel.add(btnCancelar);
			btnCancelar.setActionCommand("Cancel");
		}
		{
			btnRegistrar= new JButton("OK");
			btnRegistrar.setOpaque(false);
			btnRegistrar.setBounds(187, 329, 164, 34);
			contentPanel.add(btnRegistrar);
			btnRegistrar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnRegistrar);
		}
	}

	public JTextField getTfDni() {
		return tfDni;
	}

	public void setTfDni(JTextField tfDni) {
		this.tfDni = tfDni;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(JTextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public JTextField getTfApellido() {
		return tfApellido;
	}

	public void setTfApellido(JTextField tfApellido) {
		this.tfApellido = tfApellido;
	}

	public JTextField getTfCelular() {
		return tfCelular;
	}

	public void setTfCelular(JTextField tfCelular) {
		this.tfCelular = tfCelular;
	}

	public JTextField getTfFechaNacimiento() {
		return tfFechaNacimiento;
	}

	public void setTfFechaNacimiento(JTextField tfFechaNacimiento) {
		this.tfFechaNacimiento = tfFechaNacimiento;
	}
	
	public void escribirFech(Date a) 
	{
		tfFechaNacimiento.setText(a.getDate()+"-"+(a.getMonth()+1)+"-"+(a.getYear()+1900));
	}

	public JButton getBtnRegistrar() {
		return btnRegistrar;
	}

	public void setBtnRegistrar(JButton btnRegistrar) {
		this.btnRegistrar = btnRegistrar;
	}

	public JButton getBtnCalendario() {
		return btnCalendario;
	}

	public void setBtnCalendario(JButton btnCalendario) {
		this.btnCalendario = btnCalendario;
	}
}
