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

import presentacion.controlador.Controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ordenarMatPrima extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private VentanaPrincipal _padre;
	private ordenarMatPrima _matPrima;


	public ordenarMatPrima(VentanaPrincipal padre, final Controlador control) {
		setModal(true);
		padre=_padre;
		_matPrima=this;
		setMinimumSize(new Dimension(700, 600));
		setBounds(300, 50, 700, 641);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
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
			
			JLabel label = new JLabel("");
			label.setBounds(0, 0, 684, 600);
			contentPanel.add(label);
			label.setIcon(new ImageIcon(ordenarMatPrima.class.getResource("/prototipos/orden de materia prima.png")));
			okButton.setOpaque(false);
			okButton.setBounds(163, 530, 116, 38);
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
			cancelButton.setBounds(429, 530, 125, 38);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		
		JButton button = new JButton("Guardar");
		button.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				dispose();
			}
		});
		button.setOpaque(false);
		button.setActionCommand("OK");
		button.setBounds(303, 530, 116, 38);
		contentPanel.add(button);
	}
}
