package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class repartidorBajaModificacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField tfdni;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfCelular;
	private JTextField tfFechaNacimiento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			repartidorBajaModificacion dialog = new repartidorBajaModificacion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public repartidorBajaModificacion() {
		setBounds(100, 100, 746, 638);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(67, 188, 200, 254);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
			}
		}
		
		tfdni = new JTextField();
		tfdni.setBounds(464, 172, 200, 22);
		contentPanel.add(tfdni);
		tfdni.setColumns(10);
		
		tfNombre = new JTextField();
		tfNombre.setColumns(10);
		tfNombre.setBounds(464, 210, 200, 22);
		contentPanel.add(tfNombre);
		
		tfApellido = new JTextField();
		tfApellido.setColumns(10);
		tfApellido.setBounds(464, 250, 200, 22);
		contentPanel.add(tfApellido);
		
		tfCelular = new JTextField();
		tfCelular.setColumns(10);
		tfCelular.setBounds(464, 288, 200, 22);
		contentPanel.add(tfCelular);
		
		tfFechaNacimiento = new JTextField();
		tfFechaNacimiento.setColumns(10);
		tfFechaNacimiento.setBounds(464, 329, 200, 22);
		contentPanel.add(tfFechaNacimiento);
		{
			JLabel label = new JLabel("");
			label.setBounds(0, 0, 730, 600);
			contentPanel.add(label);
			label.setIcon(new ImageIcon(repartidorBajaModificacion.class.getResource("/prototipos/modificar-baja repartidor.png")));
		}
		{
			JButton okButton = new JButton("OK");
			okButton.setOpaque(false);
			okButton.setBounds(395, 406, 61, 46);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setOpaque(false);
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			cancelButton.setBounds(40, 478, 52, 46);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		{
			JButton button = new JButton("OK");
			button.setOpaque(false);
			button.setActionCommand("OK");
			button.setBounds(475, 534, 150, 36);
			contentPanel.add(button);
		}
	}
}
