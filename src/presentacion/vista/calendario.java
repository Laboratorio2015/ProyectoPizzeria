package presentacion.vista;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

import org.freixas.jcalendar.JCalendar;
import java.awt.Color;
import javax.swing.border.MatteBorder;

public class calendario extends JDialog {

	private calendario _this;
	private final JPanel contentPanel = new JPanel();
	private JCalendar _calendar;
	private repartidorAlta _padre;
	private consultoEstadistica _ventanaEstadistica;

	
	public calendario(repartidorAlta padre) 
	{
		setResizable(false);
		setModal(true);
		_padre=padre;
		_this=this;
		setTitle("Calendario");
		setBounds(100, 100, 319, 239);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(204, 204, 0));
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			 _calendar= new JCalendar();
			 _calendar.setBorder(new MatteBorder(0, 0, 0, 0, new Color(0, 0, 0)));
			 _calendar.setBackground(new Color(204, 204, 0));
			 contentPanel.add(_calendar);
		}
		{
			JButton _aceptar = new JButton("Aceptar");
			_aceptar.setBounds(138, 151, 74, 23);
			contentPanel.add(_aceptar);
			_aceptar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					Date fech=_calendar.getDate();
					_padre.getTfFechaNacimiento().setText(fech.getDate()+"-"+(fech.getMonth()+1)+"-"+(fech.getYear()+1900));
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
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					dispose();
				}
			});
			cancelButton.setActionCommand("Cancel");
		}
	}


	public calendario(consultoEstadistica ventanaEstadistica)
	{
		setResizable(false);
		setModal(true);
		_this=this;
		_ventanaEstadistica=ventanaEstadistica;
		setTitle("Calendario");
		setBounds(100, 100, 319, 239);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(204, 204, 0));
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			 _calendar= new JCalendar();
			 _calendar.setBorder(new MatteBorder(0, 0, 0, 0, new Color(0, 0, 0)));
			 _calendar.setBackground(new Color(204, 204, 0));
			 contentPanel.add(_calendar);
		}
		{
			JButton _aceptar = new JButton("Aceptar");
			_aceptar.setBounds(138, 151, 74, 23);
			contentPanel.add(_aceptar);
			_aceptar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					Date fech=_calendar.getDate();
					if(_this.getTitle().compareTo("Fecha de Inicio")==0)
					{
						_ventanaEstadistica.setFechaInicio(fech.getDate()+"-"+(fech.getMonth()+1)+"-"+(fech.getYear()+1900));
						_ventanaEstadistica.getTfFechaInicio().setText(fech.getDate()+"-"+(fech.getMonth()+1)+"-"+(fech.getYear()+1900));
						dispose();
					}
					else if(_this.getTitle().compareTo("Fecha de Fin")==0)
					{
						_ventanaEstadistica.setFechaFin(fech.getDate()+"-"+(fech.getMonth()+1)+"-"+(fech.getYear()+1900));
						_ventanaEstadistica.getTfFechaFin().setText(fech.getDate()+"-"+(fech.getMonth()+1)+"-"+(fech.getYear()+1900));
						dispose();
					}
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
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					dispose();
				}
			});
			cancelButton.setActionCommand("Cancel");
		}
	}

}
