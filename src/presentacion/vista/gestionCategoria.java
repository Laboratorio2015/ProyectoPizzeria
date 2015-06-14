package presentacion.vista;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dto.CategoriaDTO;

public class gestionCategoria extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfDenominacion;
	private JButton btnAgregarCategoria;
	private JButton btnQuitarCategoria;
	private JButton btnEditarCategoria;
	private JButton btnGuardarModificacion;
	private JScrollPane scrollPane;
	private String[] nombreColumnas = {"IdCategoria","Categoria"};
	private JTable tablacategorias;
	private DefaultTableModel modeloCategorias;


	@SuppressWarnings("serial")
	public gestionCategoria() {
		setBounds(100, 100, 667, 544);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			tfDenominacion = new JTextField();
			tfDenominacion.setBounds(168, 246, 176, 22);
			contentPanel.add(tfDenominacion);
			tfDenominacion.setColumns(10);
		}
		
		btnGuardarModificacion = new JButton("guardar Modificacion");
		btnGuardarModificacion.setBounds(104, 284, 198, 42);
		contentPanel.add(btnGuardarModificacion);
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(372, 161, 186, 246);
			contentPanel.add(scrollPane);
		}
		tablacategorias = new JTable();
		modeloCategorias = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Categor\u00EDa", "IdCategoria"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, Integer.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
		tablacategorias.setModel(modeloCategorias);
				
		
		tablacategorias.getColumnModel().getColumn(1).setPreferredWidth(15);
		tablacategorias.getColumnModel().getColumn(1).setMaxWidth(15);
		scrollPane.setViewportView(tablacategorias);
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(gestionCategoria.class.getResource("/prototipos/Gestor de categorias.png")));
			label.setBounds(0, 0, 650, 510);
			contentPanel.add(label);
		}
		{
			JButton btnFinalizar = new JButton("OK");
			btnFinalizar.setOpaque(false);
			btnFinalizar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					dispose();
				}
			});
			btnFinalizar.setBounds(240, 434, 150, 36);
			contentPanel.add(btnFinalizar);
			btnFinalizar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnFinalizar);
		}
		{
			btnAgregarCategoria= new JButton("agregarCategoria");
			btnAgregarCategoria.setOpaque(false);
			btnAgregarCategoria.setBounds(112, 332, 39, 36);
			contentPanel.add(btnAgregarCategoria);
			btnAgregarCategoria.setActionCommand("Cancel");
		}
		{
			btnQuitarCategoria= new JButton("borrarCat");
			btnQuitarCategoria.setOpaque(false);
			btnQuitarCategoria.setActionCommand("Cancel");
			btnQuitarCategoria.setBounds(584, 287, 39, 36);
			contentPanel.add(btnQuitarCategoria);
		}
		{
			btnEditarCategoria= new JButton("editarCat");
			btnEditarCategoria.setOpaque(false);
			btnEditarCategoria.setActionCommand("Cancel");
			btnEditarCategoria.setBounds(584, 217, 31, 36);
			contentPanel.add(btnEditarCategoria);
		}
	}

	public void addCategoriaTabla(CategoriaDTO categoria){
		modeloCategorias.addRow(new Object [] {categoria.getDenominacion().trim(), categoria.getIdCategoria()});
	}
	public JTextField getTfDenominacion() {
		return tfDenominacion;
	}

	public void setTfDenominacion(JTextField tfDenominacion) {
		this.tfDenominacion = tfDenominacion;
	}

	public JButton getBtnAgregarCategoria() {
		return btnAgregarCategoria;
	}

	public void setBtnAgregarCategoria(JButton btnAgregarCategoria) {
		this.btnAgregarCategoria = btnAgregarCategoria;
	}

	public JButton getBtnQuitarCategoria() {
		return btnQuitarCategoria;
	}

	public void setBtnQuitarCategoria(JButton btnQuitarCategoria) {
		this.btnQuitarCategoria = btnQuitarCategoria;
	}

	public JButton getBtnEditarCategoria() {
		return btnEditarCategoria;
	}

	public void setBtnEditarCategoria(JButton btnEditarCategoria) {
		this.btnEditarCategoria = btnEditarCategoria;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public void setNombreColumnas(String[] nombreColumnas) {
		this.nombreColumnas = nombreColumnas;
	}

	public JTable getTablacategorias() {
		return tablacategorias;
	}

	public void setTablacategorias(JTable tablacategorias) {
		this.tablacategorias = tablacategorias;
	}

	public DefaultTableModel getModeloCategorias() {
		return modeloCategorias;
	}

	@SuppressWarnings("serial")
	public void resetearModelo() {
		modeloCategorias = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Categor\u00EDa", "IdCategoria"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, Integer.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
		tablacategorias.setModel(modeloCategorias);
	}

	public JButton getBtnGuardarModificacion() {
		return btnGuardarModificacion;
	}

	public void setBtnGuardarModificacion(JButton btnGuardarModificacion) {
		this.btnGuardarModificacion = btnGuardarModificacion;
	}
}
