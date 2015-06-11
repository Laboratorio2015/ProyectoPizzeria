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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class registrarCobroManualmente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private pedidosPendientes _padre;
	private registrarCobroManualmente _this;
	private JTextField tfSeleccionItinerario;
	private JTextField tfMuestraRepartidor;
	private JTextField tfMontoPedido;
	private JButton btnRegistrar;
	
	public registrarCobroManualmente(pedidosPendientes padre) 
	{
		setModal(true);
		padre=_padre;
		_this=this;
		setMaximumSize(new Dimension(500, 280));
		setMinimumSize(new Dimension(500, 280));
		setBounds(500, 300, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		tfSeleccionItinerario = new JTextField();
		tfSeleccionItinerario.setBounds(294, 61, 148, 20);
		contentPanel.add(tfSeleccionItinerario);
		tfSeleccionItinerario.setColumns(10);
		
		tfMuestraRepartidor = new JTextField();
		tfMuestraRepartidor.setEditable(false);
		tfMuestraRepartidor.setBounds(136, 122, 274, 20);
		contentPanel.add(tfMuestraRepartidor);
		tfMuestraRepartidor.setColumns(10);
		
		tfMontoPedido = new JTextField();
		tfMontoPedido.setEditable(false);
		tfMontoPedido.setColumns(10);
		tfMontoPedido.setBounds(175, 154, 235, 20);
		contentPanel.add(tfMontoPedido);
		
		JComboBox<String> comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"(Filtrar busqueda por Itinerario o Pedido)", "Itinerario", "Pedido"}));
		comboBox.setBounds(27, 61, 261, 20);
		contentPanel.add(comboBox);
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(registrarCobroManualmente.class.getResource("/prototipos/registrarCobroManualmete.png")));
			label.setBounds(0, 0, 484, 261);
			contentPanel.add(label);
		}
		{
			btnRegistrar= new JButton("OK");
			btnRegistrar.setOpaque(false);
			btnRegistrar.setBounds(123, 212, 113, 29);
			contentPanel.add(btnRegistrar);
			btnRegistrar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnRegistrar);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent e) 
				{
					dispose();
				}
			});
			cancelButton.setOpaque(false);
			cancelButton.setBounds(271, 212, 107, 29);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
	}
}
