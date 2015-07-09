package presentacion.vista;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import presentacion.controlador.Controlador;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import dto.ItemDTO;
import dto.PromocionDTO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

@SuppressWarnings("serial")
public class pedidoMenu extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private  String[] nombreColumnas = {"Nombre","Precio"};
	private  String[] nombreColumnasOfertas = {"Producto","Cantidad"};
	private JButton btnSeleccionar;
	private Controlador control;
	private ordenDePedido padre;
	private JTable table_1;
	private DefaultTableModel model1;
	private pedidoMenu _this;
	private JLabel label;

	public pedidoMenu(final Controlador control, ordenDePedido padre)
	{
		setModal(true);
		setMinimumSize(new Dimension(200, 200));
		setBounds(100, 100, 670, 540);
		_this=this;
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setMinimumSize(new Dimension(500, 500));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.control=control;
		this.padre=padre;
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 102, 244, 302);
		contentPanel.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(368, 102, 251, 302);
		contentPanel.add(scrollPane_1);
		
		model = new DefaultTableModel(null,nombreColumnas);
		table = new JTable(model){
		    @Override
			public boolean isCellEditable(int rowIndex, int colIndex) {
		        return false; //desabilita la edicion de las celdas
		    }
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0)
			{
				if(_this.getTitle().compareTo("promocion")==0)
					{
					PromocionDTO aux=control.getPromocion().buscarOfertaPorNombre(model.getValueAt(table.getSelectedRow(), 0).toString());
					llenarTablaProdPromocion(aux);
					}
				
			}
		});
		scrollPane.setViewportView(table);
		table.setForeground(new Color(255, 255, 255));
		table.setBackground(new Color(153, 153, 0));
		scrollPane.getViewport().setBackground(new Color(153, 153, 0));
		
		
		
		model1 = new DefaultTableModel(null,nombreColumnasOfertas);
		table_1 = new JTable(model1){
		    @Override
			public boolean isCellEditable(int rowIndex, int colIndex) {
		        return false; //desabilita la edicion de las celdas
		    }
		};
		scrollPane_1.setViewportView(table_1);
		table_1.setForeground(new Color(255, 255, 255));
		table_1.setBackground(new Color(153, 153, 0));
		scrollPane_1.getViewport().setBackground(new Color(153, 153, 0));
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(pedidoMenu.class.getResource("/prototipos/verMenu1.png")));
		label.setBackground(new Color(204, 204, 0));
		label.setBounds(334, 0, 320, 501);
		contentPanel.add(label);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(pedidoMenu.class.getResource("/prototipos/verMenu.png")));
		lblNewLabel.setBounds(0, 0, 343, 501);
		contentPanel.add(lblNewLabel);
		{
			btnSeleccionar= new JButton("Cancel");
			btnSeleccionar.setOpaque(false);
			btnSeleccionar.setBounds(96, 431, 143, 23);
			contentPanel.add(btnSeleccionar);
			btnSeleccionar.setActionCommand("Cancel");
		}
	}
	
	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JTable getTable() {
		return table;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}

	public void setBtnSeleccionar(JButton btnSeleccionar) {
		this.btnSeleccionar = btnSeleccionar;
	}
	private void llenarTablaProdPromocion(PromocionDTO promo) 
	{
		model1.setRowCount(0);
		model1.setColumnCount(0);
		model1.setColumnIdentifiers(nombreColumnasOfertas);
		Iterator<ItemDTO> Iterador = promo.getProductosOfertados().iterator();
		while(Iterador.hasNext())
		{
			ItemDTO elemento = Iterador.next();
			Object[] fila = {elemento.getProducto().getNombre(), elemento.getCantidad()};
			model1.addRow(fila);
		}
	}
}
