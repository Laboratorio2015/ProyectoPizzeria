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

public class seleccionarRepartidor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnCancelar;
	private pedidosPendientes _padre;
	private seleccionarRepartidor _this;
	
	
	public seleccionarRepartidor(pedidosPendientes padre)
	{
		padre=_padre;
		_this=this;
		setModal(true);
		setMinimumSize(new Dimension(500, 280));
		setBounds(500, 300, 515, 319);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("");
			label.setBounds(0, 0, 499, 280);
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
			btnSeleccionar.setBounds(127, 226, 114, 30);
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
			btnCancelar.setBounds(277, 226, 108, 30);
			contentPanel.add(btnCancelar);
			btnCancelar.setActionCommand("Cancel");
		}
	}

}
