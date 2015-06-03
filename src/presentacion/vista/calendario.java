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
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			 _calendar= new JCalendar();
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
					_padre.getTfFechaNacimiento().setText(fech.getYear()+1900+"-"+(fech.getMonth()+1)+"-"+fech.getDay());
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
