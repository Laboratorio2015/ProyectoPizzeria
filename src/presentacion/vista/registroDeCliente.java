package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class registroDeCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private seleccionDeCliente _padre;
	private registroDeCliente _this;
	
	public registroDeCliente(seleccionDeCliente padre)
	{
		setModal(true);
		padre=_padre;
		_this=this;
		setMinimumSize(new Dimension(700, 600));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("");
			label.setBounds(0, 0, 684, 600);
			contentPanel.add(label);
			label.setIcon(new ImageIcon(registroDeCliente.class.getResource("/prototipos/registro de cliente.png")));
		}
		{
			JButton btnRegistrar = new JButton("Registrar");
			btnRegistrar.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					dispose();
				}
			});
			btnRegistrar.setOpaque(false);
			btnRegistrar.setBounds(181, 520, 198, 34);
			contentPanel.add(btnRegistrar);
			btnRegistrar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnRegistrar);
		}
		{
			JButton btnCancelar = new JButton("Cancel");
			btnCancelar.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					dispose();
				}
			});
			btnCancelar.setOpaque(false);
			btnCancelar.setBounds(401, 520, 164, 34);
			contentPanel.add(btnCancelar);
			btnCancelar.setActionCommand("Cancel");
		}
	}

}
