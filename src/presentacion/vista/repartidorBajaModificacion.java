package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import presentacion.controlador.Controlador;

import dto.RepartidorDTO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class repartidorBajaModificacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField tfdni;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfCelular;
	private JTextField tfFechaNacimiento;
	private  String[] nombreColumnasRepartidor = {"DNI","Nombre","Apellido"};
	private DefaultTableModel model;
	private Controlador control;

	public repartidorBajaModificacion(final Controlador control) {
		setBounds(100, 100, 697, 638);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		model = new DefaultTableModel(null,nombreColumnasRepartidor);
		this.control=control;
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(67, 188, 200, 254);
			contentPanel.add(scrollPane);
			{
				table = new JTable(model);
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						RepartidorDTO auxi=control.getRepartidor().buscarRepartidor(Integer.parseInt(model.getValueAt(table.getSelectedRow(), 0).toString()));
						agregarDatos(auxi);
					}
				});
				scrollPane.setViewportView(table);
			}
		}
		
		tfdni = new JTextField();
		tfdni.setBounds(464, 174, 200, 22);
		contentPanel.add(tfdni);
		tfdni.setColumns(10);
		
		tfNombre = new JTextField();
		tfNombre.setColumns(10);
		tfNombre.setBounds(464, 211, 200, 22);
		contentPanel.add(tfNombre);
		
		tfApellido = new JTextField();
		tfApellido.setColumns(10);
		tfApellido.setBounds(464, 249, 200, 22);
		contentPanel.add(tfApellido);
		
		tfCelular = new JTextField();
		tfCelular.setColumns(10);
		tfCelular.setBounds(464, 287, 200, 22);
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
			JButton btnGuardar = new JButton("OK");
			btnGuardar.setOpaque(false);
			btnGuardar.setBounds(395, 406, 61, 46);
			contentPanel.add(btnGuardar);
			btnGuardar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnGuardar);
		}
		{
			JButton btnCancelar = new JButton("Cancel");
			btnCancelar.setOpaque(false);
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnCancelar.setBounds(40, 478, 52, 46);
			contentPanel.add(btnCancelar);
			btnCancelar.setActionCommand("Cancel");
		}
		{
			JButton btnFinalizar = new JButton("OK");
			btnFinalizar.setOpaque(false);
			btnFinalizar.setActionCommand("OK");
			btnFinalizar.setBounds(475, 534, 150, 36);
			contentPanel.add(btnFinalizar);
		}
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JTextField getTfdni() {
		return tfdni;
	}

	public void setTfdni(JTextField tfdni) {
		this.tfdni = tfdni;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(JTextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public JTextField getTfApellido() {
		return tfApellido;
	}

	public void setTfApellido(JTextField tfApellido) {
		this.tfApellido = tfApellido;
	}

	public JTextField getTfCelular() {
		return tfCelular;
	}

	public void setTfCelular(JTextField tfCelular) {
		this.tfCelular = tfCelular;
	}

	public JTextField getTfFechaNacimiento() {
		return tfFechaNacimiento;
	}

	public void setTfFechaNacimiento(JTextField tfFechaNacimiento) {
		this.tfFechaNacimiento = tfFechaNacimiento;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public String[] getNombreColumnasRepartidor() {
		return nombreColumnasRepartidor;
	}

	public void setNombreColumnasRepartidor(String[] nombreColumnasRepartidor) {
		this.nombreColumnasRepartidor = nombreColumnasRepartidor;
	}
	public void agregarDatos(RepartidorDTO aux)
	{
		tfdni.setText(aux.getDni().toString());
		tfNombre.setText(aux.getNombre());
		tfApellido.setText(aux.getApellido());
		tfFechaNacimiento.setText(aux.getFechaNacimiento());
		tfCelular.setText(aux.getTelefono());
	}
}
