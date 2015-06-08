package presentacion.vista;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

import javax.swing.JScrollPane;

import org.freixas.jcalendar.JCalendar;
import java.awt.Color;
import javax.swing.border.MatteBorder;

public class calendario extends JDialog {

	private calendario _this;
	private final JPanel contentPanel = new JPanel();
	private JCalendar _calendar;
	private repartidorAlta _padre;

	
	public calendario(repartidorAlta padre) {
		setResizable(false);
		setModal(true);
		_padre=padre;
		setTitle("Calendario");
		setBounds(100, 100, 319, 239);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(204, 204, 0));
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			 _calendar= new JCalendar();
			 _calendar.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
			 _calendar.setBackground(new Color(204, 204, 0));
			 contentPanel.add(_calendar);
		}
		{
			JButton _aceptar = new JButton("Aceptar");
			_aceptar.setBounds(138, 151, 74, 23);
			contentPanel.add(_aceptar);
			_aceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					Date fech=_calendar.getDate();
					_padre.getTfFechaNacimiento().setText(fech.getDay()+"-"+(fech.getMonth()+1)+"-"+(fech.getYear()+1900));
					dispose();
				}
			});
			_aceptar.setActionCommand("OK");
			getRootPane().setDefaultButton(_aceptar);
		}
		{
			JButton cancelButton = new JButton("Cancelar");
			cancelButton.setBounds(219, 151, 74, 23);
			contentPanel.add(cancelButton);
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					dispose();
				}
			});
			cancelButton.setActionCommand("Cancel");
		}
	}

}
