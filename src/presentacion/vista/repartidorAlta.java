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

public class repartidorAlta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField tfCelular;
	private JTextField tfFechaNacimiento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			repartidorAlta dialog = new repartidorAlta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public repartidorAlta() {
		setBounds(100, 100, 746, 537);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textField = new JTextField();
			textField.setBounds(154, 218, 173, 22);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(153, 254, 173, 22);
			contentPanel.add(textField_1);
		}
		{
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			textField_2.setBounds(153, 290, 173, 22);
			contentPanel.add(textField_2);
		}
		{
			tfCelular = new JTextField();
			tfCelular.setColumns(10);
			tfCelular.setBounds(486, 218, 173, 22);
			contentPanel.add(tfCelular);
		}
		{
			tfFechaNacimiento = new JTextField();
			tfFechaNacimiento.setEditable(false);
			tfFechaNacimiento.setColumns(10);
			tfFechaNacimiento.setBounds(486, 255, 117, 22);
			contentPanel.add(tfFechaNacimiento);
		}
		{
			JButton btnCalendario = new JButton("");
			btnCalendario.setBounds(619, 252, 40, 34);
			contentPanel.add(btnCalendario);
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(repartidorAlta.class.getResource("/prototipos/registro de repartidor.png")));
			label.setBounds(0, 0, 730, 500);
			contentPanel.add(label);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setOpaque(false);
			cancelButton.setBounds(401, 421, 164, 34);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		{
			JButton okButton = new JButton("OK");
			okButton.setOpaque(false);
			okButton.setBounds(168, 420, 164, 34);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
	}

}
