package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class seleccionarRepartidor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnCancelar;
	private pedidosPendientes _padre;
	private seleccionarRepartidor _this;
	private JTable table;
	
	
	public seleccionarRepartidor(pedidosPendientes padre)
	{
		padre=_padre;
		_this=this;
		setModal(true);
		setMinimumSize(new Dimension(500, 280));
		setBounds(500, 300, 515, 387);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 134, 411, 128);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		{
			JLabel label = new JLabel("");
			label.setBounds(0, 0, 499, 350);
			contentPanel.add(label);
			label.setIcon(new ImageIcon(seleccionarRepartidor.class.getResource("/prototipos/seleccionarRepartidor.png")));
		}
		{
			JButton btnSeleccionar = new JButton("OK");
			btnSeleccionar.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					dispose();
				}
			});
			btnSeleccionar.setOpaque(false);
			btnSeleccionar.setBounds(124, 288, 114, 30);
			contentPanel.add(btnSeleccionar);
			btnSeleccionar.setActionCommand("Asignar");
			getRootPane().setDefaultButton(btnSeleccionar);
		}
		{
			btnCancelar = new JButton("Cancel");
			btnCancelar.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					dispose();
				}
			});
			btnCancelar.setOpaque(false);
			btnCancelar.setBounds(275, 288, 108, 30);
			contentPanel.add(btnCancelar);
			btnCancelar.setActionCommand("Cancel");
		}
	}
}
