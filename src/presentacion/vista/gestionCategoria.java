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
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class gestionCategoria extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfDenominacion;
	private JButton btnAgregarCategoria;
	private JButton btnQuitarCategoria;
	private JButton btnEditarCategoria;
	private JScrollPane scrollPane;
	private JTable table;
	private  String[] nombreColumnas = {"Numero","Categoria"};
	private DefaultTableModel model;

	public gestionCategoria() {
		setBounds(100, 100, 667, 537);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			tfDenominacion = new JTextField();
			tfDenominacion.setBounds(168, 241, 176, 22);
			contentPanel.add(tfDenominacion);
			tfDenominacion.setColumns(10);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(370, 153, 186, 246);
			contentPanel.add(scrollPane);
			{
				model = new DefaultTableModel(null,nombreColumnas);
				table = new JTable(model)
				{
				    public boolean isCellEditable(int rowIndex, int colIndex) {
				    	if (colIndex==1) {
				            return true;  //La columna 1 y 3 son editables.
				        }
				        return false;  //El resto de celdas no son editables.
				    }
				};
				scrollPane.setViewportView(table);
			}
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(gestionCategoria.class.getResource("/prototipos/Gestor de categorias.png")));
			label.setBounds(0, 0, 650, 500);
			contentPanel.add(label);
		}
		{
			JButton btnFinalizar = new JButton("OK");
			btnFinalizar.setOpaque(false);
			btnFinalizar.addActionListener(new ActionListener() {
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
			btnAgregarCategoria= new JButton("Cancel");
			btnAgregarCategoria.setOpaque(false);
			btnAgregarCategoria.setBounds(112, 324, 39, 36);
			contentPanel.add(btnAgregarCategoria);
			btnAgregarCategoria.setActionCommand("Cancel");
		}
		{
			btnQuitarCategoria= new JButton("Cancel");
			btnQuitarCategoria.setOpaque(false);
			btnQuitarCategoria.setActionCommand("Cancel");
			btnQuitarCategoria.setBounds(584, 287, 39, 36);
			contentPanel.add(btnQuitarCategoria);
		}
		{
			btnEditarCategoria= new JButton("Cancel");
			btnEditarCategoria.setOpaque(false);
			btnEditarCategoria.setActionCommand("Cancel");
			btnEditarCategoria.setBounds(584, 217, 31, 36);
			contentPanel.add(btnEditarCategoria);
		}
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

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public void setNombreColumnas(String[] nombreColumnas) {
		this.nombreColumnas = nombreColumnas;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}	
}
