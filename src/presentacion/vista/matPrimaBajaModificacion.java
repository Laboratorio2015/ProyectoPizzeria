package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Font;

public class matPrimaBajaModificacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfDenominacion;
	private JTable table;
	private JTextField tfPrecio;
	private JComboBox comboBoxCategoriasTabla;
	private JComboBox comboBoxCategoriaUnObjeto;
	private JButton btnGuardarUnCambio;
	private JButton btnGuardar;
	private JButton btnEditar;
	private JButton btnEliminar;

	public matPrimaBajaModificacion() {
		setBounds(100, 100, 745, 653);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			comboBoxCategoriasTabla = new JComboBox();
			comboBoxCategoriasTabla.setBounds(31, 175, 239, 22);
			contentPanel.add(comboBoxCategoriasTabla);
		}
		{
			comboBoxCategoriaUnObjeto = new JComboBox();
			comboBoxCategoriaUnObjeto.setBounds(466, 279, 174, 22);
			contentPanel.add(comboBoxCategoriaUnObjeto);
		}
		{
			tfDenominacion = new JTextField();
			tfDenominacion.setBounds(466, 239, 174, 22);
			contentPanel.add(tfDenominacion);
			tfDenominacion.setColumns(10);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(31, 227, 239, 257);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
			}
		}
		{
			tfPrecio = new JTextField();
			tfPrecio.setColumns(10);
			tfPrecio.setBounds(466, 312, 174, 22);
			contentPanel.add(tfPrecio);
		}
		{
			JLabel lblPrecio = new JLabel("Precio");
			lblPrecio.setFont(new Font("Calibri", Font.BOLD, 15));
			lblPrecio.setForeground(Color.WHITE);
			lblPrecio.setBounds(349, 315, 46, 14);
			contentPanel.add(lblPrecio);
		}
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(matPrimaBajaModificacion.class.getResource("/prototipos/baja-modif de Materia Prima.png")));
		label.setBounds(0, 0, 735, 615);
		contentPanel.add(label);
		{
			btnGuardarUnCambio= new JButton("Cancel");
			btnGuardarUnCambio.setOpaque(false);
			btnGuardarUnCambio.setBounds(334, 348, 38, 40);
			contentPanel.add(btnGuardarUnCambio);
			btnGuardarUnCambio.setActionCommand("Cancel");
		}
		{
			btnGuardar= new JButton("OK");
			btnGuardar.setOpaque(false);
			btnGuardar.setBounds(341, 536, 154, 40);
			contentPanel.add(btnGuardar);
			btnGuardar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnGuardar);
		}
		{
			JButton btnCancelar = new JButton("OK");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					dispose();
				}
			});
			btnCancelar.setOpaque(false);
			btnCancelar.setActionCommand("OK");
			btnCancelar.setBounds(536, 536, 154, 40);
			contentPanel.add(btnCancelar);
		}
		{
			btnEditar= new JButton("Cancel");
			btnEditar.setOpaque(false);
			btnEditar.setActionCommand("Cancel");
			btnEditar.setBounds(78, 512, 33, 34);
			contentPanel.add(btnEditar);
		}
		{
			btnEliminar= new JButton("Cancel");
			btnEliminar.setOpaque(false);
			btnEliminar.setActionCommand("Cancel");
			btnEliminar.setBounds(180, 512, 38, 34);
			contentPanel.add(btnEliminar);
		}
	}

	public JTextField getTfDenominacion() {
		return tfDenominacion;
	}

	public void setTfDenominacion(JTextField tfDenominacion) {
		this.tfDenominacion = tfDenominacion;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JTextField getTfPrecio() {
		return tfPrecio;
	}

	public void setTfPrecio(JTextField tfPrecio) {
		this.tfPrecio = tfPrecio;
	}

	public JComboBox getComboBoxCategoriasTabla() {
		return comboBoxCategoriasTabla;
	}

	public void setComboBoxCategoriasTabla(JComboBox comboBoxCategoriasTabla) {
		this.comboBoxCategoriasTabla = comboBoxCategoriasTabla;
	}

	public JComboBox getComboBoxCategoriaUnObjeto() {
		return comboBoxCategoriaUnObjeto;
	}

	public void setComboBoxCategoriaUnObjeto(JComboBox comboBoxCategoriaUnObjeto) {
		this.comboBoxCategoriaUnObjeto = comboBoxCategoriaUnObjeto;
	}

	public JButton getBtnGuardarUnCambio() {
		return btnGuardarUnCambio;
	}

	public void setBtnGuardarUnCambio(JButton btnGuardarUnCambio) {
		this.btnGuardarUnCambio = btnGuardarUnCambio;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public void setBtnEditar(JButton btnEditar) {
		this.btnEditar = btnEditar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}
	
}
