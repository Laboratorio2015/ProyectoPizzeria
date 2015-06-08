package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class proveedorAlta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfDenominacion;
	private JTextField tfTelefono;
	private JTextField tfEmail;
	private JTextField tfDireccion;
	private JButton btnCancelar;
	private JButton btnRegistrar;
	private JTextField tfComentario;
	private JTable table;
	private JTextField tfNombreContacto;
	private JComboBox comboBoxCategorias;


	public proveedorAlta() {
		setBounds(100, 100, 563, 688);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			tfDenominacion = new JTextField();
			tfDenominacion.setBounds(187, 171, 289, 22);
			contentPanel.add(tfDenominacion);
			tfDenominacion.setColumns(10);
		}
		{
			tfTelefono = new JTextField();
			tfTelefono.setColumns(10);
			tfTelefono.setBounds(187, 283, 289, 22);
			contentPanel.add(tfTelefono);
		}
		{
			tfEmail = new JTextField();
			tfEmail.setColumns(10);
			tfEmail.setBounds(185, 356, 291, 22);
			contentPanel.add(tfEmail);
		}
		
		tfDireccion = new JTextField();
		tfDireccion.setBounds(187, 320, 289, 22);
		contentPanel.add(tfDireccion);
		tfDireccion.setColumns(10);
		
		comboBoxCategorias= new JComboBox();
		comboBoxCategorias.setModel(new DefaultComboBoxModel(new String[] {"(Agregar Categorias)"}));
		comboBoxCategorias.setBounds(186, 209, 249, 22);
		contentPanel.add(comboBoxCategorias);
		
		tfComentario = new JTextField();
		tfComentario.setBounds(184, 393, 292, 54);
		contentPanel.add(tfComentario);
		tfComentario.setColumns(10);
		
		tfNombreContacto = new JTextField();
		tfNombreContacto.setColumns(10);
		tfNombreContacto.setBounds(186, 246, 289, 22);
		contentPanel.add(tfNombreContacto);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 483, 434, 62);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(proveedorAlta.class.getResource("/prototipos/alta de Proveedor.png")));
			label.setBounds(0, 0, 550, 650);
			contentPanel.add(label);
		}
		{
			btnRegistrar= new JButton("OK");
			btnRegistrar.setOpaque(false);
			btnRegistrar.setBounds(100, 575, 165, 35);
			contentPanel.add(btnRegistrar);
			btnRegistrar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnRegistrar);
		}
		{
			btnCancelar = new JButton("OK");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0)
				{
					dispose();
				}
			});
			btnCancelar.setOpaque(false);
			btnCancelar.setActionCommand("OK");
			btnCancelar.setBounds(330, 575, 108, 35);
			contentPanel.add(btnCancelar);
		}
	}


	public JTextField getTfDenominacion() {
		return tfDenominacion;
	}


	public void setTfDenominacion(JTextField tfDenominacion) {
		this.tfDenominacion = tfDenominacion;
	}
	
	public JTextField getTfComentario() {
		return tfComentario;
	}

	public void setTfComentario(JTextField tfComentario) {
		this.tfComentario = tfComentario;
	}

	public JTextField getTfNombreContacto() {
		return tfNombreContacto;
	}

	public void setTfNombreContacto(JTextField tfNombreContacto) {
		this.tfNombreContacto = tfNombreContacto;
	}


	public JTextField getTfTelefono() {
		return tfTelefono;
	}


	public void setTfTelefono(JTextField tfTelefono) {
		this.tfTelefono = tfTelefono;
	}


	public JTextField getTfEmail() {
		return tfEmail;
	}


	public void setTfEmail(JTextField tfEmail) {
		this.tfEmail = tfEmail;
	}


	public JTextField getTfDireccion() {
		return tfDireccion;
	}


	public void setTfDireccion(JTextField tfDireccion) {
		this.tfDireccion = tfDireccion;
	}


	public JButton getBtnRegistrar() {
		return btnRegistrar;
	}


	public void setBtnRegistrar(JButton btnRegistrar) {
		this.btnRegistrar = btnRegistrar;
	}
}
