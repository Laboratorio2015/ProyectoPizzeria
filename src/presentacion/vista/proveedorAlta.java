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

public class proveedorAlta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfDenominacion;
	private JTextField tfCategoria;
	private JTextField tfTelefono;
	private JTextField tfEmail;
	private JTextField tfDireccion;


	public proveedorAlta() {
		setBounds(100, 100, 743, 638);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			tfDenominacion = new JTextField();
			tfDenominacion.setBounds(157, 192, 174, 22);
			contentPanel.add(tfDenominacion);
			tfDenominacion.setColumns(10);
		}
		{
			tfCategoria = new JTextField();
			tfCategoria.setColumns(10);
			tfCategoria.setBounds(156, 227, 174, 22);
			contentPanel.add(tfCategoria);
		}
		{
			tfTelefono = new JTextField();
			tfTelefono.setColumns(10);
			tfTelefono.setBounds(156, 263, 174, 22);
			contentPanel.add(tfTelefono);
		}
		{
			tfEmail = new JTextField();
			tfEmail.setColumns(10);
			tfEmail.setBounds(156, 302, 174, 22);
			contentPanel.add(tfEmail);
		}
		
		tfDireccion = new JTextField();
		tfDireccion.setBounds(157, 335, 174, 20);
		contentPanel.add(tfDireccion);
		tfDireccion.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Direccion");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(45, 338, 93, 14);
		contentPanel.add(lblNewLabel);
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(proveedorAlta.class.getResource("/prototipos/alta de Proveedor.png")));
			label.setBounds(0, 0, 730, 600);
			contentPanel.add(label);
		}
		{
			JButton okButton = new JButton("OK");
			okButton.setOpaque(false);
			okButton.setBounds(299, 541, 149, 35);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
	}
}
