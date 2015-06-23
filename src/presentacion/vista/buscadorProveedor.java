package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import presentacion.controlador.Controlador;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mxrck.autocompleter.TextAutoCompleter;

import dto.ProveedorDTO;

import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.util.ArrayList;

import javax.swing.ListSelectionModel;
import javax.swing.JTextPane;

public class buscadorProveedor extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Controlador controlador;
	@SuppressWarnings("unused")
	private VentanaPrincipal ventanaPrincipal;
	//TABLA
	private JTable resultadoBusquedaProv;
	private DefaultTableModel modeloResultados;
	//BOTONES Y TXT FIELD
	private JComboBox<String> comboFiltroBusqueda;
	private JComboBox<String> comboBoxCategorias;
	private JTextField textFieldBuscadorProv;
	private JLabel labelMuestraNomContacto;
	private JButton btnSeleccionarproveedor;
	private JButton btnDetallesproveedor;
	private TextAutoCompleter textAutoAcompleter;
	private JTextPane textPaneComentario;
	private JTextPane textPanecategorias;

	@SuppressWarnings("serial")
	public buscadorProveedor(VentanaPrincipal ventanaPrincipal, Controlador controlador) {
		this.ventanaPrincipal = ventanaPrincipal;
		this.controlador = controlador;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 615, 748);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		comboFiltroBusqueda = new JComboBox<String>();
		comboFiltroBusqueda.setModel(new DefaultComboBoxModel<String>(new String[] {"Todos", "Categor\u00EDa", "Nombre"}));
		comboFiltroBusqueda.setBounds(60, 156, 217, 20);
		contentPane.add(comboFiltroBusqueda);
		
		comboBoxCategorias = new JComboBox<String>();
		comboBoxCategorias.setModel(new DefaultComboBoxModel<String>(new String[] {"Seleccione una categoria..."}));
		comboBoxCategorias.setVisible(false);
		comboBoxCategorias.setBounds(66, 225, 211, 20);
		contentPane.add(comboBoxCategorias);

		resultadoBusquedaProv = new JTable();
		resultadoBusquedaProv.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		resultadoBusquedaProv.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		resultadoBusquedaProv.setShowGrid(false);
		resultadoBusquedaProv.setSelectionForeground(new Color(0, 0, 0));
		resultadoBusquedaProv.setSelectionBackground(new Color(204, 255, 153));
		resultadoBusquedaProv.setGridColor(new Color(51, 102, 153));
		resultadoBusquedaProv.setBorder(null);
		resultadoBusquedaProv.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 12));
		resultadoBusquedaProv.setForeground(Color.WHITE);
		resultadoBusquedaProv.setBackground(new Color(51, 102, 153));
		
		modeloResultados = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Proveedor", "Direcci\u00F3n", "Tel\u00E9fono", "E-mail"
				}
			) {
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class
				};
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
		resultadoBusquedaProv.setModel(modeloResultados);
		resultadoBusquedaProv.getColumnModel().getColumn(0).setMaxWidth(200);
		resultadoBusquedaProv.getColumnModel().getColumn(1).setMinWidth(160);
		resultadoBusquedaProv.getColumnModel().getColumn(1).setWidth(160);
		resultadoBusquedaProv.getColumnModel().getColumn(1).setMaxWidth(160);
		resultadoBusquedaProv.getColumnModel().getColumn(2).setMaxWidth(55);
		resultadoBusquedaProv.getColumnModel().getColumn(3).setMaxWidth(300);

		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(20, 303, 542, 130);
		contentPane.add(scrollPane);


		scrollPane.setViewportView(resultadoBusquedaProv);	
		
		textFieldBuscadorProv = new JTextField();
		textFieldBuscadorProv.setVisible(false);
		
		textPaneComentario = new JTextPane();
		textPaneComentario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPaneComentario.setForeground(new Color(250, 235, 215));
		textPaneComentario.setBackground(new Color(0, 51, 102));
		textPaneComentario.setBounds(31, 565, 512, 48);
		contentPane.add(textPaneComentario);
		textFieldBuscadorProv.setBounds(66, 225, 211, 20);
		contentPane.add(textFieldBuscadorProv);
		textFieldBuscadorProv.setColumns(10);
		
		textAutoAcompleter = new TextAutoCompleter( textFieldBuscadorProv );
		
		textPanecategorias = new JTextPane();
		textPanecategorias.setEditable(false);
		textPanecategorias.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPanecategorias.setForeground(new Color(250, 235, 215));
		textPanecategorias.setBackground(new Color(0, 51, 102));
		textPanecategorias.setBounds(31, 493, 512, 48);
		contentPane.add(textPanecategorias);
		
		labelMuestraNomContacto = new JLabel("");
		labelMuestraNomContacto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelMuestraNomContacto.setForeground(new Color(250, 235, 215));
		labelMuestraNomContacto.setOpaque(true);
		labelMuestraNomContacto.setBackground(new Color(0, 51, 102));
		labelMuestraNomContacto.setBounds(186, 444, 262, 20);
		contentPane.add(labelMuestraNomContacto);

		JLabel fondo = new JLabel("");
		fondo.setHorizontalAlignment(SwingConstants.CENTER);
		fondo.setIcon(new ImageIcon(buscadorProveedor.class.getResource("/prototipos/Buscador de proveedor.png")));
		fondo.setBounds(0, 0, 600, 686);
		contentPane.add(fondo);
		textAutoAcompleter.setMode(0);
		textAutoAcompleter.setCaseSensitive(false);
		cargarBuscadorProvXnom();


		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		btnFinalizar.setOpaque(false);
		btnFinalizar.setBounds(325, 631, 150, 32);
		contentPane.add(btnFinalizar);
		
		btnSeleccionarproveedor = new JButton("SeleccionarProveedor");
		btnSeleccionarproveedor.setOpaque(false);
		btnSeleccionarproveedor.setBounds(115, 631, 184, 32);
		contentPane.add(btnSeleccionarproveedor);
		
		btnDetallesproveedor = new JButton("detallesProveedor");
		btnDetallesproveedor.setOpaque(false);
		btnDetallesproveedor.setBounds(315, 212, 110, 80);
		contentPane.add(btnDetallesproveedor);
	}
	
	@SuppressWarnings("serial")
	public void resetearModeloTablaResultados(){
		this.modeloResultados = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Proveedor", "Direcci\u00F3n", "Tel\u00E9fono", "E-mail"
				}
			) {
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class
				};
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
		resultadoBusquedaProv.setModel(modeloResultados);
		resultadoBusquedaProv.getColumnModel().getColumn(0).setMaxWidth(200);
		resultadoBusquedaProv.getColumnModel().getColumn(1).setMinWidth(160);
		resultadoBusquedaProv.getColumnModel().getColumn(1).setWidth(160);
		resultadoBusquedaProv.getColumnModel().getColumn(1).setMaxWidth(160);
		resultadoBusquedaProv.getColumnModel().getColumn(2).setMaxWidth(55);
		resultadoBusquedaProv.getColumnModel().getColumn(3).setMaxWidth(300);
	}
	
	public void borrarDetalles(){
		this.textPanecategorias.setText("");
		this.textPaneComentario.setText("");
		this.labelMuestraNomContacto.setText("");
	}
	private void cargarBuscadorProvXnom() {
		ArrayList<ProveedorDTO> proveedores = (ArrayList<ProveedorDTO>) controlador.getProveedor().obtenerProveedor();
		for (int i=0; i < proveedores.size(); i++){
			textAutoAcompleter.addItem(proveedores.get(i).getNombre());
		}
	}

	public JTable getResultadoBusquedaProv() {
		return resultadoBusquedaProv;
	}

	public void setResultadoBusquedaProv(JTable resultadoBusquedaProv) {
		this.resultadoBusquedaProv = resultadoBusquedaProv;
	}

	public DefaultTableModel getModeloResultados() {
		return modeloResultados;
	}

	public void setModeloResultados(DefaultTableModel modeloResultados) {
		this.modeloResultados = modeloResultados;
	}

	public JComboBox<String> getComboFiltroBusqueda() {
		return comboFiltroBusqueda;
	}

	public void setComboFiltroBusqueda(JComboBox<String> comboFiltroBusqueda) {
		this.comboFiltroBusqueda = comboFiltroBusqueda;
	}

	public JTextField getTextFieldBuscadorProv() {
		return textFieldBuscadorProv;
	}

	public void setTextFieldBuscadorProv(JTextField textFieldBuscadorProv) {
		this.textFieldBuscadorProv = textFieldBuscadorProv;
	}

	public JLabel getLabelMuestraNomContacto() {
		return labelMuestraNomContacto;
	}

	public void setLabelMuestraNomContacto(JLabel labelMuestraNomContacto) {
		this.labelMuestraNomContacto = labelMuestraNomContacto;
	}

	public JButton getBtnSeleccionarproveedor() {
		return btnSeleccionarproveedor;
	}

	public void setBtnSeleccionarproveedor(JButton btnSeleccionarproveedor) {
		this.btnSeleccionarproveedor = btnSeleccionarproveedor;
	}

	public JComboBox<String> getComboBoxCategorias() {
		return comboBoxCategorias;
	}

	public void setComboBoxCategorias(JComboBox<String> comboBoxCategorias) {
		this.comboBoxCategorias = comboBoxCategorias;
	}

	public TextAutoCompleter getTextAutoAcompleter() {
		return textAutoAcompleter;
	}

	public void setTextAutoAcompleter(TextAutoCompleter textAutoAcompleter) {
		this.textAutoAcompleter = textAutoAcompleter;
	}

	public JButton getBtnDetallesproveedor() {
		return btnDetallesproveedor;
	}

	public void setBtnDetallesproveedor(JButton btnDetallesproveedor) {
		this.btnDetallesproveedor = btnDetallesproveedor;
	}

	public JTextPane getTextPaneComentario() {
		return textPaneComentario;
	}

	public void setTextPaneComentario(JTextPane textPaneComentario) {
		this.textPaneComentario = textPaneComentario;
	}

	public JTextPane getTextPanecategorias() {
		return textPanecategorias;
	}

	public void setTextPanecategorias(JTextPane textPanecategorias) {
		this.textPanecategorias = textPanecategorias;
	}
}
