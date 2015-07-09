package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import presentacion.controlador.Controlador;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import dto.ClienteDTO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import javax.swing.border.MatteBorder;

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
	private DefaultTableModel model;
	private  String[] nombreColumnas = {"DNI","Apellido","Nombre"};
	private JTable table;
	private Controlador control;
	private seleccionDeCliente ventanaPadre;
	private JComboBox cbClientes;
	private JButton btnGuardar;
	private JButton btnBorrarCliente;
	
	public clienteBajaModificacion(final Controlador control) {
		setModal(true);
		setBounds(100, 100, 857, 640);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		this.control=control;
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
			scrollPane = new JScrollPane();
			scrollPane.setBounds(21, 128, 217, 394);
			contentPanel.add(scrollPane);
			{
				model = new DefaultTableModel(null,nombreColumnas);
				table = new JTable(model)
				{
				    @Override
					public boolean isCellEditable(int rowIndex, int colIndex) 
				    {
				        return false; //desabilita la edicion de las celdas
				    }
				};
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e)
					{
						ClienteDTO auxi=control.getCliente().buscarClientePorDNI(Integer.parseInt(model.getValueAt(table.getSelectedRow(), 0).toString()));
						agregarDatos(auxi);
					}
				});
				scrollPane.setViewportView(table);				
				table.setForeground(new Color(255, 255, 255));
				table.setBackground(new Color(153, 153, 0));
				scrollPane.getViewport().setBackground(new Color(153, 153, 0));
				//encabezado
				JTableHeader headerPedidos = table.getTableHeader();
				headerPedidos.setFont(new Font("Tahoma", Font.BOLD, 13));
				headerPedidos.setForeground(new Color(153, 153, 0));
				headerPedidos.setBackground(new Color(0).GRAY);
			}
		}
		{
			JLabel lblNewLabel = new JLabel("New label");
			lblNewLabel.setIcon(new ImageIcon(clienteBajaModificacion.class.getResource("/prototipos/baja-modif de cliente.png")));
			lblNewLabel.setBounds(0, 0, 842, 600);
			contentPanel.add(lblNewLabel);
		}
		{
			tfBusquedaCliente = new JTextField();
			tfBusquedaCliente.setOpaque(false);
			tfBusquedaCliente.setEditable(false);
			tfBusquedaCliente.setEnabled(false);
			tfBusquedaCliente.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) 
				{
					if(tfBusquedaCliente.getText().length()>2)
					{
					try
					{	
						ClienteDTO aux=control.getCliente().buscarClientePorDNI(Integer.parseInt(tfBusquedaCliente.getText().toString()));
						agregarDatos(aux);
					}
					catch(Exception e)
					{
						ClienteDTO aux=control.getCliente().buscarClientePorNombre((tfBusquedaCliente.getText().toString()));
						if(aux!=null)
						agregarDatos(aux);
						else
						{
						aux=control.getCliente().buscarClientePorApellido((tfBusquedaCliente.getText().toString()));
						agregarDatos(aux);
						}
					}
					tfBusquedaCliente.setText("");
					}
				}
			});
			tfBusquedaCliente.setBounds(31, 178, 202, 22);
			contentPanel.add(tfBusquedaCliente);
			tfBusquedaCliente.setColumns(10);
		}
		{
			cbClientes = new JComboBox();
			cbClientes.setOpaque(false);
			cbClientes.setEnabled(false);
			cbClientes.setModel(new DefaultComboBoxModel(new String[] {"(Ingrese Filtro para Busqueda)", "DNI", "Apellido", "Nombre"}));
			cbClientes.setBounds(31, 150, 202, 22);
			contentPanel.add(cbClientes);
		}
		{
			btnGuardar= new JButton("Cancel");
			btnGuardar.setOpaque(false);
			btnGuardar.setBounds(331, 489, 54, 45);
			contentPanel.add(btnGuardar);
			btnGuardar.setActionCommand("Cancel");
		}
		{
			btnBorrarCliente= new JButton("OK");
			btnBorrarCliente.setOpaque(false);
			btnBorrarCliente.setBounds(52, 532, 41, 40);
			contentPanel.add(btnBorrarCliente);
			btnBorrarCliente.setActionCommand("OK");
			getRootPane().setDefaultButton(btnBorrarCliente);
		}
		{
			JButton btnFinalizar = new JButton("Cancel");
			btnFinalizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0)
				{
					dispose();
				}
			});
			btnFinalizar.setOpaque(false);
			btnFinalizar.setActionCommand("Cancel");
			btnFinalizar.setBounds(661, 546, 151, 34);
			contentPanel.add(btnFinalizar);
		}
	}
	
	//constructor para la modificacion dentro de un pedido
	/**
	 * @wbp.parser.constructor
	 */
	public clienteBajaModificacion(final seleccionDeCliente padre, final Controlador control) 
	{
		setBounds(100, 100, 857, 640);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		this.control=control;
		contentPanel.setLayout(null);
		{
			this.control=control;
			this.ventanaPadre=padre;
			tfDni = new JTextField();
			tfDni.setEditable(false);
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
			tfBusquedaCliente.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
			tfBusquedaCliente.setBackground(new Color(204, 204, 0));
			tfBusquedaCliente.setEnabled(false);
			tfBusquedaCliente.setEditable(false);
			tfBusquedaCliente.setBounds(10, 82, 245, 498);
			contentPanel.add(tfBusquedaCliente);
			tfBusquedaCliente.setColumns(10);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(31, 216, 207, 306);
			contentPanel.add(scrollPane);
			{
				model = new DefaultTableModel(null,nombreColumnas);
				table = new JTable(model)
				{
				    @Override
					public boolean isCellEditable(int rowIndex, int colIndex) 
				    {
				        return false; //desabilita la edicion de las celdas
				    }
				};

				scrollPane.setViewportView(table);
			}
			scrollPane.setVisible(false);
		}
		{
			JLabel lblNewLabel = new JLabel("New label");
			lblNewLabel.setIcon(new ImageIcon(clienteBajaModificacion.class.getResource("/prototipos/baja-modif de cliente.png")));
			lblNewLabel.setBounds(0, 0, 842, 600);
			contentPanel.add(lblNewLabel);
		}
		{
			JButton btnGuardar = new JButton("Cancel");
			btnGuardar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					Component a= new Component() {};
					int opcion = JOptionPane.showConfirmDialog(a, "¿Desea guardar los cambios realizados?", "Seleccione una opción", JOptionPane.YES_NO_OPTION);
					if( opcion==0)
						{
							ClienteDTO actu=control.getCliente().buscarClientePorDNI(Integer.parseInt(tfDni.getText().toString()));
							actu.setDni(Integer.parseInt(tfDni.getText().toString()));
							actu.setNombre(tfNombre.getText().toString());
							actu.setApellido(tfApellido.getText().toString());
							actu.setCalle(tfCalle.getText().toString());
							actu.setNumeracion(tfNumeracion.getText().toString());
							actu.setEntrecalle1(tfEntreCalle1.getText().toString());
							actu.setEntrecalle2(tfEntreCalle2.getText().toString());
							actu.setCodPostal(tfCodPostal.getText().toString());
							actu.setTelefono(tfTelefono.getText().toString());
							actu.setComentario(tfComentario.getText().toString());
							actu.setEmail(tfEmail.getText().toString());
							actu.setFueeliminado(false);
							control.getCliente().actualizarCliente(actu);
						}
				}
			});
			btnGuardar.setOpaque(false);
			btnGuardar.setBounds(331, 489, 54, 45);
			contentPanel.add(btnGuardar);
			btnGuardar.setActionCommand("Cancel");
		}
		{
			JButton btnBorrarCliente = new JButton("OK");
			btnBorrarCliente.setEnabled(false);
			btnBorrarCliente.setOpaque(false);
			btnBorrarCliente.setBounds(52, 532, 41, 40);
			contentPanel.add(btnBorrarCliente);
			btnBorrarCliente.setActionCommand("OK");
			getRootPane().setDefaultButton(btnBorrarCliente);
		}
		{
			JButton btnFinalizar = new JButton("Cancel");
			btnFinalizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					padre.getTfNombrApellido().setText(tfApellido.getText().toString()+" "+tfNombre.getText().toString());
					padre.getTfDireccionTelefono().setText(tfCalle.getText().toString()+" "+tfNumeracion.getText().toString());
					padre.llenarTablaCliente();
					dispose();
				}
			});
			btnFinalizar.setOpaque(false);
			btnFinalizar.setActionCommand("Cancel");
			btnFinalizar.setBounds(661, 546, 151, 34);
			contentPanel.add(btnFinalizar);
		}
	}

	public JTextField getTfDni() {
		return tfDni;
	}

	public void setTfDni(JTextField tfDni) {
		this.tfDni = tfDni;
	}

	public JTextField getTfApellido() {
		return tfApellido;
	}

	public void setTfApellido(JTextField tfApellido) {
		this.tfApellido = tfApellido;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(JTextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public JTextField getTfTelefono() {
		return tfTelefono;
	}

	public void setTfTelefono(JTextField tfTelefono) {
		this.tfTelefono = tfTelefono;
	}

	public JTextField getTfCalle() {
		return tfCalle;
	}

	public void setTfCalle(JTextField tfCalle) {
		this.tfCalle = tfCalle;
	}

	public JTextField getTfNumeracion() {
		return tfNumeracion;
	}

	public void setTfNumeracion(JTextField tfNumeracion) {
		this.tfNumeracion = tfNumeracion;
	}

	public JTextField getTfCodPostal() {
		return tfCodPostal;
	}

	public void setTfCodPostal(JTextField tfCodPostal) {
		this.tfCodPostal = tfCodPostal;
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
	
	public DefaultTableModel getModel()
	{
		return model;
	}

	public void setModel(DefaultTableModel model)
	{
		this.model = model;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public void setNombreColumnas(String[] nombreColumnas) {
		this.nombreColumnas = nombreColumnas;
	}

	public JTextField getTfEmail() {
		return tfEmail;
	}

	public void setTfEmail(JTextField tfEmail) {
		this.tfEmail = tfEmail;
	}

	public JTextField getTfComentario() {
		return tfComentario;
	}

	public void setTfComentario(JTextField tfComentario) {
		this.tfComentario = tfComentario;
	}

	public JTextField getTfBusquedaCliente() {
		return tfBusquedaCliente;
	}

	public void setTfBusquedaCliente(JTextField tfBusquedaCliente) {
		this.tfBusquedaCliente = tfBusquedaCliente;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public Controlador getControl() {
		return control;
	}

	public void setControl(Controlador control) {
		this.control = control;
	}

	public seleccionDeCliente getVentanaPadre() {
		return ventanaPadre;
	}

	public void setVentanaPadre(seleccionDeCliente ventanaPadre) {
		this.ventanaPadre = ventanaPadre;
	}

	public JComboBox getCbClientes() {
		return cbClientes;
	}

	public void setCbClientes(JComboBox cbClientes) {
		this.cbClientes = cbClientes;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JButton getBtnBorrarCliente() {
		return btnBorrarCliente;
	}

	public void setBtnBorrarCliente(JButton btnBorrarCliente) {
		this.btnBorrarCliente = btnBorrarCliente;
	}
	public void agregarDatos(ClienteDTO aux)
	{
		tfDni.setText(aux.getDni().toString());
		tfNombre.setText(aux.getNombre());
		tfApellido.setText(aux.getApellido());
		tfCalle.setText(aux.getCalle());
		tfNumeracion.setText(aux.getNumeracion());
		tfComentario.setText(aux.sinEspacio(aux.getComentario()));
		tfCodPostal.setText(aux.getCodPostal());
		tfEmail.setText(aux.getEmail());
		tfEntreCalle1.setText(aux.getEntrecalle1());
		tfEntreCalle2.setText(aux.getEntrecalle2());
		tfTelefono.setText(aux.getTelefono());
	}
}
