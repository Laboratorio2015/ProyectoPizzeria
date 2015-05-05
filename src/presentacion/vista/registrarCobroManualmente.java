package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class registrarCobroManualmente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private pedidosPendientes _padre;
	private registrarCobroManualmente _this;
	
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
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(registrarCobroManualmente.class.getResource("/prototipos/registrarCobroManualmete.png")));
			label.setBounds(0, 0, 484, 261);
			contentPanel.add(label);
		}
		{
			JButton okButton = new JButton("OK");
			okButton.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent e) 
				{
					dispose();
				}
			});
			okButton.setOpaque(false);
			okButton.setBounds(123, 212, 113, 29);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
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
