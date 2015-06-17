package presentacion.vista;

import java.awt.BorderLayout;
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

import dto.ClienteDTO;

import presentacion.controlador.Controlador;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class registroDeCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private seleccionDeCliente _padre;
	private registroDeCliente _this;
	private JTextField tfdni;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfCalle;
	private JTextField tfEntreCalle1;
	private JTextField tfEntreCalle2;
	private JTextField tfNumeracion;
	private JTextField tfCodPostal;
	private Controlador control;
	private JTextField tfEmail;
	private JButton btnRegistrar;
	private JTextField tfTelefono;
	private JTextField tfComentario;
	
	public registroDeCliente(final seleccionDeCliente padre, final Controlador control)
	{
		setModal(true);
		_padre=padre;
		this.control=control;
		_this=this;
		setMinimumSize(new Dimension(700, 600));
		setBounds(100, 100, 700, 639);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		tfdni = new JTextField();
		tfdni.setBounds(127, 160, 173, 25);
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
		tfCalle.setBounds(152, 344, 173, 22);
		contentPanel.add(tfCalle);
		
		tfEntreCalle1 = new JTextField();
		tfEntreCalle1.setColumns(10);
		tfEntreCalle1.setBounds(151, 380, 173, 22);
		contentPanel.add(tfEntreCalle1);
		
		tfEntreCalle2 = new JTextField();
		tfEntreCalle2.setColumns(10);
		tfEntreCalle2.setBounds(151, 416, 173, 22);
		contentPanel.add(tfEntreCalle2);
		
		tfNumeracion = new JTextField();
		tfNumeracion.setColumns(10);
		tfNumeracion.setBounds(464, 343, 173, 22);
		contentPanel.add(tfNumeracion);
		
		tfCodPostal = new JTextField();
		tfCodPostal.setColumns(10);
		tfCodPostal.setBounds(463, 379, 173, 22);
		contentPanel.add(tfCodPostal);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(463, 415, 173, 22);
		contentPanel.add(tfEmail);
		
		tfTelefono = new JTextField();
		tfTelefono.setColumns(10);
		tfTelefono.setBounds(460, 160, 175, 25);
		contentPanel.add(tfTelefono);
		
		tfComentario = new JTextField();
		tfComentario.setBounds(152, 457, 485, 50);
		contentPanel.add(tfComentario);
		tfComentario.setColumns(10);
		{
			JLabel label = new JLabel("");
			label.setBounds(0, 0, 684, 600);
			contentPanel.add(label);
			label.setIcon(new ImageIcon(registroDeCliente.class.getResource("/prototipos/Alta de cliente.png")));
		}
		{
			btnRegistrar= new JButton("Registrar");
			btnRegistrar.setOpaque(false);
			btnRegistrar.setBounds(165, 534, 198, 34);
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
			btnCancelar.setBounds(382, 534, 164, 34);
			contentPanel.add(btnCancelar);
			btnCancelar.setActionCommand("Cancel");
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	////////////////// se agrega constructor para el cliente 0 ////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	public registroDeCliente(final seleccionDeCliente padre, final Controlador control,String otro)
	{
		setModal(true);
		_padre=padre;
		this.control=control;
		_this=this;
		setMinimumSize(new Dimension(700, 600));
		setBounds(100, 100, 700, 639);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		tfdni = new JTextField();
		tfdni.setBounds(127, 160, 173, 25);
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
		tfCalle.setBounds(152, 344, 173, 22);
		contentPanel.add(tfCalle);
		
		tfEntreCalle1 = new JTextField();
		tfEntreCalle1.setColumns(10);
		tfEntreCalle1.setBounds(151, 380, 173, 22);
		contentPanel.add(tfEntreCalle1);
		
		tfEntreCalle2 = new JTextField();
		tfEntreCalle2.setColumns(10);
		tfEntreCalle2.setBounds(151, 416, 173, 22);
		contentPanel.add(tfEntreCalle2);
		
		tfNumeracion = new JTextField();
		tfNumeracion.setColumns(10);
		tfNumeracion.setBounds(464, 343, 173, 22);
		contentPanel.add(tfNumeracion);
		
		tfCodPostal = new JTextField();
		tfCodPostal.setColumns(10);
		tfCodPostal.setBounds(463, 379, 173, 22);
		contentPanel.add(tfCodPostal);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(463, 415, 173, 22);
		contentPanel.add(tfEmail);
		
		tfTelefono = new JTextField();
		tfTelefono.setColumns(10);
		tfTelefono.setBounds(460, 160, 175, 25);
		contentPanel.add(tfTelefono);
		
		tfComentario = new JTextField();
		tfComentario.setBounds(152, 457, 485, 50);
		contentPanel.add(tfComentario);
		tfComentario.setColumns(10);
		{
			JLabel label = new JLabel("");
			label.setBounds(0, 0, 684, 600);
			contentPanel.add(label);
			label.setIcon(new ImageIcon(registroDeCliente.class.getResource("/prototipos/Alta de cliente.png")));
		}
		{
			btnRegistrar= new JButton("Registrar");
			btnRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					ClienteDTO nuevo= new ClienteDTO();
					nuevo.setIdcliente(control.getCliente().obtenerTodoClientes().size()+1);
					nuevo.setDni(Integer.parseInt(tfdni.getText().toString()));
					nuevo.setApellido(tfApellido.getText().toString());
					nuevo.setNombre(tfNombre.getText().toString());
					nuevo.setCalle(tfCalle.getText().toString());
					nuevo.setNumeracion(tfNumeracion.getText().toString());
					nuevo.setTelefono(tfTelefono.getText().toString());
					nuevo.setComentario(tfComentario.getText().toString());
					nuevo.setFueeliminado(false);
					nuevo.setEntrecalle1(tfEntreCalle1.getText().toString());
					nuevo.setEntrecalle2(tfEntreCalle2.getText().toString());
					nuevo.setCodPostal(tfCodPostal.getText().toString());
					nuevo.setEmail(tfEmail.getText().toString());
					control.getCliente().agregarCliente(nuevo);
					padre.getTfAgregarDNI().setText(nuevo.getDni().toString());
					padre.llenarTablaCliente();
					padre.getTfNombrApellido().setText("Apellido y Nombre: "+nuevo.getNombre()+" "+ nuevo.getApellido());
					padre.getTfDireccionTelefono().setText("Direccion: "+nuevo.getCalle()+" "+nuevo.getNumeracion());
					dispose();
				}
			});
			btnRegistrar.setOpaque(false);
			btnRegistrar.setBounds(165, 534, 198, 34);
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
			btnCancelar.setBounds(382, 534, 164, 34);
			contentPanel.add(btnCancelar);
			btnCancelar.setActionCommand("Cancel");
		}
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

	public JTextField getTfCalle() {
		return tfCalle;
	}

	public void setTfCalle(JTextField tfCalle) {
		this.tfCalle = tfCalle;
	}

	public JTextField getTfEntreCalle1() {
		return tfEntreCalle1;
	}

	public void setTfEntreCalle1(JTextField tfEntreCalle1) {
		this.tfEntreCalle1 = tfEntreCalle1;
	}

	public JTextField getTfEntreCalle2() {
		return tfEntreCalle2;
	}

	public void setTfEntreCalle2(JTextField tfEntreCalle2) {
		this.tfEntreCalle2 = tfEntreCalle2;
	}

	public JTextField getTfNumeracion() {
		return tfNumeracion;
	}

	public void setTfNumeracion(JTextField tfNumeracion) {
		this.tfNumeracion = tfNumeracion;
	}
	
	public seleccionDeCliente get_padre() {
		return _padre;
	}

	public void set_padre(seleccionDeCliente _padre) {
		this._padre = _padre;
	}

	public Controlador getControl() {
		return control;
	}

	public void setControl(Controlador control) {
		this.control = control;
	}

	public JTextField getTfTelefono() {
		return tfTelefono;
	}

	public void setTfTelefono(JTextField tfTelefono) {
		this.tfTelefono = tfTelefono;
	}

	public JTextField getTfComentario() {
		return tfComentario;
	}

	public void setTfComentario(JTextField tfComentario) {
		this.tfComentario = tfComentario;
	}

	public JTextField getTfCodPostal() {
		return tfCodPostal;
	}

	public void setTfCodPostal(JTextField tfCodPostal) {
		this.tfCodPostal = tfCodPostal;
	}

	public JTextField getTfEmail() {
		return tfEmail;
	}

	public void setTfEmail(JTextField tfEmail) {
		this.tfEmail = tfEmail;
	}

	public JButton getBtnRegistrar() {
		return btnRegistrar;
	}

	public void setBtnRegistrar(JButton btnRegistrar) {
		this.btnRegistrar = btnRegistrar;
	}
}
