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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class productoBajaModificacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNombre;
	private JTextField tfPrecio;
	private JTable table;

	public productoBajaModificacion() {
		setBounds(100, 100, 746, 586);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			tfNombre = new JTextField();
			tfNombre.setBounds(491, 180, 180, 23);
			contentPanel.add(tfNombre);
			tfNombre.setColumns(10);
		}
		{
			tfPrecio = new JTextField();
			tfPrecio.setColumns(10);
			tfPrecio.setBounds(507, 253, 91, 22);
			contentPanel.add(tfPrecio);
		}
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"(Seleccione un tipo de Producto)", "empanada", "pizza", "otros"}));
		comboBox.setBounds(491, 216, 180, 23);
		contentPanel.add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 146, 186, 355);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(productoBajaModificacion.class.getResource("/prototipos/baja-modif de Producto.png")));
			label.setBounds(0, 0, 730, 550);
			contentPanel.add(label);
		}
		{
			JButton btnQuitar = new JButton("OK");
			btnQuitar.setBounds(243, 284, 52, 52);
			contentPanel.add(btnQuitar);
			btnQuitar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnQuitar);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setBounds(440, 459, 151, 38);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		
		JButton btnGuardar = new JButton("New button");
		btnGuardar.setBounds(418, 326, 48, 52);
		contentPanel.add(btnGuardar);
	}
}
