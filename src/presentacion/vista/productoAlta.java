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

	public productoAlta() {
		setBounds(100, 100, 664, 536);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JComboBox cbTipo = new JComboBox();
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"(Seleccione un tipo de Producto)", "empanada", "pizza", "otros"}));
		cbTipo.setBounds(175, 200, 180, 22);
		contentPanel.add(cbTipo);
		
		tfDenominacion = new JTextField();
		tfDenominacion.setBounds(175, 164, 180, 22);
		contentPanel.add(tfDenominacion);
		tfDenominacion.setColumns(10);
		
		tfPrecio = new JTextField();
		tfPrecio.setColumns(10);
		tfPrecio.setBounds(175, 237, 91, 22);
		contentPanel.add(tfPrecio);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(384, 153, 180, 246);
		contentPanel.add(scrollPane);
		
		tableProductos = new JTable();
		scrollPane.setViewportView(tableProductos);
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(productoAlta.class.getResource("/prototipos/Alta de Producto.png")));
			label.setBounds(0, 0, 730, 500);
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
			cancelButton.setBounds(239, 434, 150, 34);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
	}
}
