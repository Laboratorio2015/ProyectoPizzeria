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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class pedidoMenu extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private  String[] nombreColumnas = {"Nombre","Precio"};

	public pedidoMenu()
	{
		setModal(true);
		setMinimumSize(new Dimension(500, 500));
		setBounds(100, 100, 517, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setMinimumSize(new Dimension(500, 500));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 76, 481, 328);
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
		lblNewLabel.setBounds(0, 0, 501, 461);
		contentPanel.add(lblNewLabel);
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0)
				{
					dispose();
				}
			});
			cancelButton.setOpaque(false);
			cancelButton.setBounds(151, 415, 173, 35);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
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
}
