package presentacion.vista;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dto.MateriaPrimaDTO;


import presentacion.controlador.Controlador;

public class selectorMatPrima extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controlador controlador;
	@SuppressWarnings("unused")
	private VentanaPrincipal ventanaPrincipal;
	private JButton btnSeleccionarMateriaPrima;
	//TABLA
	private JTable tablaMateriasPrimas;
	private DefaultTableModel modeloMateriasPrimas;


	@SuppressWarnings({ "serial", "static-access" })
	public selectorMatPrima(VentanaPrincipal ventanaPrincipal, Controlador controlador) {
		this.ventanaPrincipal = ventanaPrincipal;
		this.controlador = controlador;


		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 345, 534);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 103, 264, 318);
		contentPane.add(scrollPane);
		tablaMateriasPrimas = new JTable();
		tablaMateriasPrimas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaMateriasPrimas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tablaMateriasPrimas.setShowGrid(false);
		tablaMateriasPrimas.setSelectionForeground(new Color(0, 0, 0));
		tablaMateriasPrimas.setSelectionBackground(new Color(204, 255, 153));
		tablaMateriasPrimas.setGridColor(new Color(51, 102, 153));
		tablaMateriasPrimas.setBorder(null);
		tablaMateriasPrimas.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 12));
		tablaMateriasPrimas.setForeground(Color.WHITE);
		tablaMateriasPrimas.setBackground(new Color(51, 102, 153));
		scrollPane.setViewportView(tablaMateriasPrimas);	

		JLabel fondo = new JLabel("");
		fondo.setHorizontalAlignment(SwingConstants.CENTER);
		fondo.setIcon(new ImageIcon(buscadorProveedor.class.getResource("/prototipos/verMateriasPrimas.png")));
		fondo.setBounds(0, 0, 334, 501);
		contentPane.add(fondo);

		this.modeloMateriasPrimas = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Producto"
				}
				) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		JTableHeader header = tablaMateriasPrimas.getTableHeader();
		header.setFont(new Font("Tahoma", Font.BOLD, 13));
		header.setForeground(new Color(0).GRAY);
		this.tablaMateriasPrimas.setModel(modeloMateriasPrimas);
		//

		//
		DefaultTableCellRenderer alinearCentro = new DefaultTableCellRenderer();
		alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
		tablaMateriasPrimas.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);

		btnSeleccionarMateriaPrima = new JButton("SeleccionarProveedor");
		btnSeleccionarMateriaPrima.setOpaque(false);
		btnSeleccionarMateriaPrima.setBounds(98, 432, 137, 23);
		contentPane.add(btnSeleccionarMateriaPrima);
	}


	public JButton getBtnSeleccionarMateriaPrima() {
		return btnSeleccionarMateriaPrima;
	}


	public void setBtnSeleccionarMateriaPrima(JButton btnSeleccionarMateriaPrima) {
		this.btnSeleccionarMateriaPrima = btnSeleccionarMateriaPrima;
	}


	public void cargarMatPrima(String nomCatSolicitada) {
		Iterator<MateriaPrimaDTO> Iterador = controlador.getMateriasPrimas().obtenerMatPrimas().iterator();
		while(Iterador.hasNext())
		{
			MateriaPrimaDTO elemento = Iterador.next();
			System.out.println("materia prima: " + elemento.getNombre());
			if ( elemento.getCategoria().getDenominacion().compareTo(nomCatSolicitada) == 0){
				String nombreMatPrima = elemento.getNombre().replace("_", " ");
				modeloMateriasPrimas.addRow(new String[] {nombreMatPrima.trim()});
			}
		}
		tablaMateriasPrimas.setModel(modeloMateriasPrimas);
	}


	public JTable getTablaMateriasPrimas() {
		return tablaMateriasPrimas;
	}


	public void setTablaMateriasPrimas(JTable tablaMateriasPrimas) {
		this.tablaMateriasPrimas = tablaMateriasPrimas;
	}


	public DefaultTableModel getModeloMateriasPrimas() {
		return modeloMateriasPrimas;
	}


	public void setModeloMateriasPrimas(DefaultTableModel modeloMateriasPrimas) {
		this.modeloMateriasPrimas = modeloMateriasPrimas;
	}
}