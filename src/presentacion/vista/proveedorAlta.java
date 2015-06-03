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

public class proveedorAlta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfDenominacion;
	private JTextField tfCategoria;
	private JTextField tfTelefono;
	private JTextField tfEmail;
	private JTextField tfDireccion;
	private JButton btnCancelar;


	public proveedorAlta() {
		setBounds(100, 100, 563, 536);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			tfDenominacion = new JTextField();
			tfDenominacion.setBounds(189, 201, 304, 22);
			contentPanel.add(tfDenominacion);
			tfDenominacion.setColumns(10);
		}
		{
			tfCategoria = new JTextField();
			tfCategoria.setColumns(10);
			tfCategoria.setBounds(189, 237, 304, 22);
			contentPanel.add(tfCategoria);
		}
		{
			tfTelefono = new JTextField();
			tfTelefono.setColumns(10);
			tfTelefono.setBounds(189, 274, 304, 22);
			contentPanel.add(tfTelefono);
		}
		{
			tfEmail = new JTextField();
			tfEmail.setColumns(10);
			tfEmail.setBounds(189, 311, 304, 22);
			contentPanel.add(tfEmail);
		}
		
		tfDireccion = new JTextField();
		tfDireccion.setBounds(189, 342, 304, 20);
		contentPanel.add(tfDireccion);
		tfDireccion.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Direccion");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(75, 343, 93, 14);
		contentPanel.add(lblNewLabel);
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(proveedorAlta.class.getResource("/prototipos/alta de Proveedor.png")));
			label.setBounds(0, 0, 550, 500);
			contentPanel.add(label);
		}
		{
			JButton btnRegistrar = new JButton("OK");
			btnRegistrar.setOpaque(false);
			btnRegistrar.setBounds(98, 409, 165, 35);
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
			btnCancelar.setBounds(328, 409, 108, 35);
			contentPanel.add(btnCancelar);
		}
	}
}
