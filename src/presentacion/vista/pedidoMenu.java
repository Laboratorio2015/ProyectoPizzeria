package presentacion.vista;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import presentacion.controlador.Controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class pedidoMenu extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private  String[] nombreColumnas = {"Nombre","Precio"};
	private JButton btnSeleccionar;
	private Controlador control;
	private ordenDePedido padre;

	public pedidoMenu(Controlador control, ordenDePedido padre)
	{
		setModal(true);
		setMinimumSize(new Dimension(200, 200));
		setBounds(100, 100, 347, 540);
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
		
		model = new DefaultTableModel(null,nombreColumnas);
		table = new JTable(model){
		    public boolean isCellEditable(int rowIndex, int colIndex) {
		        return false; //desabilita la edicion de las celdas
		    }
		};
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(pedidoMenu.class.getResource("/prototipos/verMenu.png")));
		lblNewLabel.setBounds(0, 0, 333, 501);
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
	
}
