package presentacion.vista;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class clienteBajaModificacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfDni;
	private JTextField tfApellido;
	private JTextField tfNombre;
	private JTextField tfTelefono;
	private JTextField tfCalle;
	private JTextField tfNumeracion;
	private JTextField tfCodPostal;
	private JTextField tfEntreCalle1;
	private JTextField tfEntreCalle2;
	private JTextField tfEmail;
	private JTextField tfComentario;
	private JTextField tfBusquedaCliente;
	private JScrollPane scrollPane;
	private JTable table;

	public clienteBajaModificacion() {
		setBounds(100, 100, 857, 640);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			tfDni = new JTextField();
			tfDni.setBounds(333, 174, 176, 22);
			contentPanel.add(tfDni);
			tfDni.setColumns(10);
		}
		{
			tfApellido = new JTextField();
			tfApellido.setColumns(10);
			tfApellido.setBounds(554, 175, 176, 22);
			contentPanel.add(tfApellido);
		}
		{
			tfNombre = new JTextField();
			tfNombre.setColumns(10);
			tfNombre.setBounds(333, 221, 176, 22);
			contentPanel.add(tfNombre);
		}
		{
			tfTelefono = new JTextField();
			tfTelefono.setColumns(10);
			tfTelefono.setBounds(554, 221, 176, 22);
			contentPanel.add(tfTelefono);
		}
		{
			tfCalle = new JTextField();
			tfCalle.setColumns(10);
			tfCalle.setBounds(331, 323, 176, 22);
			contentPanel.add(tfCalle);
		}
		{
			tfNumeracion = new JTextField();
			tfNumeracion.setColumns(10);
			tfNumeracion.setBounds(523, 322, 79, 22);
			contentPanel.add(tfNumeracion);
		}
		{
			tfCodPostal = new JTextField();
			tfCodPostal.setColumns(10);
			tfCodPostal.setBounds(626, 331, 86, 22);
			contentPanel.add(tfCodPostal);
		}
		{
			tfEntreCalle1 = new JTextField();
			tfEntreCalle1.setColumns(10);
			tfEntreCalle1.setBounds(331, 372, 142, 22);
			contentPanel.add(tfEntreCalle1);
		}
		{
			tfEntreCalle2 = new JTextField();
			tfEntreCalle2.setColumns(10);
			tfEntreCalle2.setBounds(483, 373, 136, 22);
			contentPanel.add(tfEntreCalle2);
		}
		{
			tfEmail = new JTextField();
			tfEmail.setColumns(10);
			tfEmail.setBounds(627, 384, 176, 22);
			contentPanel.add(tfEmail);
		}
		{
			tfComentario = new JTextField();
			tfComentario.setColumns(10);
			tfComentario.setBounds(331, 420, 482, 50);
			contentPanel.add(tfComentario);
		}
		{
			tfBusquedaCliente = new JTextField();
			tfBusquedaCliente.setBounds(31, 150, 202, 22);
			contentPanel.add(tfBusquedaCliente);
			tfBusquedaCliente.setColumns(10);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(31, 216, 207, 306);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
			}
		}
		{
			JLabel lblNewLabel = new JLabel("New label");
			lblNewLabel.setIcon(new ImageIcon(clienteBajaModificacion.class.getResource("/prototipos/baja-modif de cliente.png")));
			lblNewLabel.setBounds(0, 0, 842, 600);
			contentPanel.add(lblNewLabel);
		}
		{
			JButton btnGuardar = new JButton("Cancel");
			btnGuardar.setOpaque(false);
			btnGuardar.setBounds(331, 489, 54, 45);
			contentPanel.add(btnGuardar);
			btnGuardar.setActionCommand("Cancel");
		}
		{
			JButton btnBorrarCliente = new JButton("OK");
			btnBorrarCliente.setOpaque(false);
			btnBorrarCliente.setBounds(52, 532, 41, 40);
			contentPanel.add(btnBorrarCliente);
			btnBorrarCliente.setActionCommand("OK");
			getRootPane().setDefaultButton(btnBorrarCliente);
		}
		{
			JButton btnFinalizar = new JButton("Cancel");
			btnFinalizar.setOpaque(false);
			btnFinalizar.setActionCommand("Cancel");
			btnFinalizar.setBounds(661, 546, 151, 34);
			contentPanel.add(btnFinalizar);
		}
	}

}
