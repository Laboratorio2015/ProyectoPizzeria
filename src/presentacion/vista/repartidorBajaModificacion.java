package presentacion.vista;

import java.awt.BorderLayout;
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
	private JButton btnGuardar;
	private JButton btnQuitar ;
	private JButton btnFinalizar;
	private JTextField tfComentario;
	private JTextField tfPatente;
	private JTextField tfDescripcion;

	public repartidorBajaModificacion(final Controlador control) {
		setBounds(100, 100, 695, 737);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		model = new DefaultTableModel(null,nombreColumnasRepartidor);
		this.control=control;
		contentPanel.setLayout(null);
		{
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 194, 234, 318);
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
		tfdni.setBounds(464, 173, 178, 22);
		contentPanel.add(tfdni);
		tfdni.setColumns(10);
		
		tfNombre = new JTextField();
		tfNombre.setColumns(10);
		tfNombre.setBounds(464, 210, 178, 22);
		contentPanel.add(tfNombre);
		
		tfApellido = new JTextField();
		tfApellido.setColumns(10);
		tfApellido.setBounds(464, 247, 178, 22);
		contentPanel.add(tfApellido);
		
		tfCelular = new JTextField();
		tfCelular.setColumns(10);
		tfCelular.setBounds(464, 286, 178, 22);
		contentPanel.add(tfCelular);
		
		tfFechaNacimiento = new JTextField();
		tfFechaNacimiento.setEditable(false);
		tfFechaNacimiento.setColumns(10);
		tfFechaNacimiento.setBounds(464, 319, 113, 22);
		contentPanel.add(tfFechaNacimiento);
		
		JButton btnCalendario = new JButton("");
		btnCalendario.setIcon(new ImageIcon(repartidorBajaModificacion.class.getResource("/Botones/calendario.gif")));
		btnCalendario.setBounds(596, 314, 45, 31);
		contentPanel.add(btnCalendario);
		
		tfComentario = new JTextField();
		tfComentario.setBounds(464, 350, 178, 53);
		contentPanel.add(tfComentario);
		tfComentario.setColumns(10);
		
		tfPatente = new JTextField();
		tfPatente.setColumns(10);
		tfPatente.setBounds(358, 466, 86, 22);
		contentPanel.add(tfPatente);
		
		tfDescripcion = new JTextField();
		tfDescripcion.setColumns(10);
		tfDescripcion.setBounds(462, 466, 182, 22);
		contentPanel.add(tfDescripcion);
		{
			JLabel label = new JLabel("");
			label.setBounds(0, 0, 680, 699);
			contentPanel.add(label);
			label.setIcon(new ImageIcon(repartidorBajaModificacion.class.getResource("/prototipos/modificar-baja repartidor.png")));
		}
		{
			btnGuardar= new JButton("OK");
			btnGuardar.setOpaque(false);
			btnGuardar.setBounds(373, 544, 61, 46);
			contentPanel.add(btnGuardar);
			btnGuardar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnGuardar);
		}
		{
			btnQuitar= new JButton("Cancel");
			btnQuitar.setOpaque(false);
			btnQuitar.setBounds(46, 538, 52, 46);
			contentPanel.add(btnQuitar);
			btnQuitar.setActionCommand("Cancel");
		}
		{
			btnFinalizar= new JButton("OK");
			btnFinalizar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnFinalizar.setOpaque(false);
			btnFinalizar.setActionCommand("OK");
			btnFinalizar.setBounds(244, 629, 150, 36);
			contentPanel.add(btnFinalizar);
		}
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	
	public JButton getBtnQuitar() {
		return btnQuitar;
	}

	public void setBtnQuitar(JButton btnQuitar) {
		this.btnQuitar = btnQuitar;
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
	
	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JButton getBtnFinalizar() {
		return btnFinalizar;
	}

	public void setBtnFinalizar(JButton btnFinalizar) {
		this.btnFinalizar = btnFinalizar;
	}
	
	public JTextField getTfComentario() {
		return tfComentario;
	}

	public void setTfComentario(JTextField tfComentario) {
		this.tfComentario = tfComentario;
	}

	public JTextField getTfPatente() {
		return tfPatente;
	}

	public void setTfPatente(JTextField tfPatente) {
		this.tfPatente = tfPatente;
	}

	public JTextField getTfDescripcion() {
		return tfDescripcion;
	}

	public void setTfDescripcion(JTextField tfDescripcion) {
		this.tfDescripcion = tfDescripcion;
	}

	public void agregarDatos(RepartidorDTO aux)
	{
		tfdni.setText(aux.getDni().toString());
		tfNombre.setText(aux.getNombre());
		tfApellido.setText(aux.getApellido());
		tfFechaNacimiento.setText(aux.getFechaNacimiento());
		tfCelular.setText(aux.getTelefono());
		tfComentario.setText(aux.sinEspacio(aux.getComentario()));
		tfDescripcion.setText(aux.getVehiculo());
		tfPatente.setText(aux.getVehiculo());
	}
}
