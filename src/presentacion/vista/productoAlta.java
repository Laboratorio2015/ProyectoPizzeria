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
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;

public class productoAlta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfDenominacion;
	private JTextField tfPrecio;
	private JTable tableProductos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			productoAlta dialog = new productoAlta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public productoAlta() {
		setBounds(100, 100, 746, 587);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JComboBox cbTipo = new JComboBox();
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"(elija un tipo)", "empanada", "pizza", "otros"}));
		cbTipo.setBounds(175, 193, 174, 22);
		contentPanel.add(cbTipo);
		
		tfDenominacion = new JTextField();
		tfDenominacion.setBounds(175, 155, 174, 22);
		contentPanel.add(tfDenominacion);
		tfDenominacion.setColumns(10);
		
		tfPrecio = new JTextField();
		tfPrecio.setColumns(10);
		tfPrecio.setBounds(175, 227, 91, 22);
		contentPanel.add(tfPrecio);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(447, 145, 241, 294);
		contentPanel.add(scrollPane);
		
		tableProductos = new JTable();
		scrollPane.setViewportView(tableProductos);
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(productoAlta.class.getResource("/prototipos/Alta de Producto.png")));
			label.setBounds(0, 0, 730, 550);
			contentPanel.add(label);
		}
		{
			JButton okButton = new JButton("OK");
			okButton.setOpaque(false);
			okButton.setBounds(126, 309, 38, 40);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setOpaque(false);
			cancelButton.setBounds(299, 478, 150, 34);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
	}
}
