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
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class matPrimaAlta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNombre;
	private JTextField tfPrecio;
	private JTable table;
	private JButton btnGuardar;
	private JButton btnAgregarMatPrima;
	private JComboBox comboBoxCategoria;
	
	public matPrimaAlta() {
		setBounds(100, 100, 734, 588);
		getContentPane().setLayout(null);
		contentPanel.setBounds(597, 0, 1, 482);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JButton okButton = new JButton("OK");
			okButton.setBounds(378, 448, 47, 23);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setBounds(430, 448, 65, 23);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		{
			tfNombre = new JTextField();
			tfNombre.setBounds(125, 244, 222, 22);
			getContentPane().add(tfNombre);
			tfNombre.setColumns(10);
		}
		
		tfPrecio = new JTextField();
		tfPrecio.setColumns(10);
		tfPrecio.setBounds(125, 318, 222, 22);
		getContentPane().add(tfPrecio);
		{
		comboBoxCategoria= new JComboBox();
		comboBoxCategoria.setBounds(125, 285, 222, 22);
		getContentPane().add(comboBoxCategoria);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(385, 167, 243, 259);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(new Font("Calibri", Font.BOLD, 15));
		lblPrecio.setForeground(Color.WHITE);
		lblPrecio.setBackground(Color.WHITE);
		lblPrecio.setBounds(45, 327, 46, 14);
		getContentPane().add(lblPrecio);
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(matPrimaAlta.class.getResource("/prototipos/Alta de Materia Prima.png")));
			label.setBounds(0, 0, 719, 550);
			getContentPane().add(label);
		}
		
		btnAgregarMatPrima= new JButton("");
		btnAgregarMatPrima.setBounds(92, 352, 51, 30);
		getContentPane().add(btnAgregarMatPrima);
		
		btnGuardar= new JButton("");
		btnGuardar.setBounds(392, 481, 109, 36);
		getContentPane().add(btnGuardar);
		
		JButton btnCancelar = new JButton("");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				dispose();
			}
		});
		btnCancelar.setBounds(548, 481, 109, 36);
		getContentPane().add(btnCancelar);
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(JTextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public JTextField getTfPrecio() {
		return tfPrecio;
	}

	public void setTfPrecio(JTextField tfPrecio) {
		this.tfPrecio = tfPrecio;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JButton getBtnAgregarMatPrima() {
		return btnAgregarMatPrima;
	}

	public void setBtnAgregarMatPrima(JButton btnAgregarMatPrima) {
		this.btnAgregarMatPrima = btnAgregarMatPrima;
	}

	public JComboBox getComboBoxCategoria() {
		return comboBoxCategoria;
	}

	public void setComboBoxCategoria(JComboBox comboBoxCategoria) {
		this.comboBoxCategoria = comboBoxCategoria;
	}
}
