package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class registroDeCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private seleccionDeCliente _padre;
	private registroDeCliente _this;
	private JTextField tfdni;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfCalle;
	private JTextField tfPiso;
	private JTextField tfDepartamento;
	private JTextField tfNumeracion;
	private JTextField tfTelefono;
	
	public registroDeCliente(seleccionDeCliente padre)
	{
		setModal(true);
		padre=_padre;
		_this=this;
		setMinimumSize(new Dimension(700, 600));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		tfdni = new JTextField();
		tfdni.setBounds(127, 162, 173, 22);
		contentPanel.add(tfdni);
		tfdni.setColumns(10);
		
		tfNombre = new JTextField();
		tfNombre.setColumns(10);
		tfNombre.setBounds(126, 198, 173, 22);
		contentPanel.add(tfNombre);
		
		tfApellido = new JTextField();
		tfApellido.setColumns(10);
		tfApellido.setBounds(126, 234, 173, 22);
		contentPanel.add(tfApellido);
		
		tfCalle = new JTextField();
		tfCalle.setColumns(10);
		tfCalle.setBounds(152, 347, 173, 22);
		contentPanel.add(tfCalle);
		
		tfPiso = new JTextField();
		tfPiso.setColumns(10);
		tfPiso.setBounds(153, 387, 173, 22);
		contentPanel.add(tfPiso);
		
		tfDepartamento = new JTextField();
		tfDepartamento.setColumns(10);
		tfDepartamento.setBounds(154, 426, 173, 22);
		contentPanel.add(tfDepartamento);
		
		tfNumeracion = new JTextField();
		tfNumeracion.setColumns(10);
		tfNumeracion.setBounds(470, 346, 173, 22);
		contentPanel.add(tfNumeracion);
		
		tfTelefono = new JTextField();
		tfTelefono.setColumns(10);
		tfTelefono.setBounds(472, 388, 173, 22);
		contentPanel.add(tfTelefono);
		{
			JLabel label = new JLabel("");
			label.setBounds(0, 0, 684, 600);
			contentPanel.add(label);
			label.setIcon(new ImageIcon(registroDeCliente.class.getResource("/prototipos/registro de cliente.png")));
		}
		{
			JButton btnRegistrar = new JButton("Registrar");
			btnRegistrar.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					dispose();
				}
			});
			btnRegistrar.setOpaque(false);
			btnRegistrar.setBounds(181, 520, 198, 34);
			contentPanel.add(btnRegistrar);
			btnRegistrar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnRegistrar);
		}
		{
			JButton btnCancelar = new JButton("Cancel");
			btnCancelar.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					dispose();
				}
			});
			btnCancelar.setOpaque(false);
			btnCancelar.setBounds(401, 520, 164, 34);
			contentPanel.add(btnCancelar);
			btnCancelar.setActionCommand("Cancel");
		}
	}
}
