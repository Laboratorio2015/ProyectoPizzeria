package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.itextpdf.text.Document;

import modelo.Itinerarios;

import presentacion.controlador.Controlador;
import presentacion.reportes.GenerarPDF;
import presentacion.reportes.Itinerario;

import dto.HojaItinerarioDTO;
import dto.PedidoDTO;
import dto.RepartidorDTO;

public class seleccionarRepartidor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnCancelar;
	private pedidosPendientes _padre;
	private seleccionarRepartidor _this;
	private JTable table;
	private DefaultTableModel model;
	private Controlador control;
	private int[] numfila;
	private Itinerario iti;
	private ArrayList<PedidoDTO> listaPedidos;
	private HojaItinerarioDTO hojaItinerario;
	private  String[] nombreColumnasRepartidor = {"DNI","Nombre","Apellido"};
	
	
	public seleccionarRepartidor(final pedidosPendientes padre, final Controlador control,final int[] numfila, final ArrayList<PedidoDTO> pedidos)
	{
		_padre=padre;
		_this=this;
		this.numfila=numfila;
		setModal(true);
		this.control=control;
		setMinimumSize(new Dimension(500, 280));
		setBounds(500, 300, 515, 387);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		model = new DefaultTableModel(null,nombreColumnasRepartidor);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 134, 411, 128);
		contentPanel.add(scrollPane);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		table.setForeground(new Color(255, 255, 255));
		table.setBackground(new Color(153, 153, 0));
		scrollPane.getViewport().setBackground(new Color(153, 153, 0));	
		//encabezado
		JTableHeader headerPedidos = table.getTableHeader();
		headerPedidos.setFont(new Font("Tahoma", Font.BOLD, 13));
		headerPedidos.setForeground(new Color(153, 153, 0));
		headerPedidos.setBackground(new Color(0).GRAY);
		
		llenarTablaRepartidor();
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
					//crea la hoja de itinerario con los pedidos seleccionados
					hojaItinerario=new HojaItinerarioDTO();
					hojaItinerario.setIdHojaItinerario(control.getItinerario().obtenerUltimoItinerario()+1);
					hojaItinerario.setNumItinerario(control.getItinerario().obtenerUltimoItinerario(fechaActual())+1);
					hojaItinerario.setFueeliminado(false);
					hojaItinerario.setFecha(fechaActual());
					hojaItinerario.setRepartidor(control.getRepartidor().buscarRepartidorPorDni(Integer.parseInt(model.getValueAt(table.getSelectedRow(), 0).toString())));
					hojaItinerario.setPedidos(pedidos);
					//iti=new Itinerario(new Document(), hojaItinerario);
					//iti.generarItinerario();
					
					//genera el itinerario
					GenerarPDF.GenerarItinerario(hojaItinerario);
					control.getItinerario().agregarItinerario(hojaItinerario);
					for(int i=0; i<numfila.length;i++)
					{
					//ingresar el nuevo estado en la tabla a los pedidos
					//padre.getTable().setValueAt(hojaItinerario.getIdHojaItinerario(), i, 4);
					//padre.getTable().setValueAt("en delivery", i, 2);
					}
					Iterator<PedidoDTO> Iterador = pedidos.iterator();
					//cambia el estado de los pedidos en la base
					while(Iterador.hasNext())
					{
						PedidoDTO elemento = Iterador.next();
						control.getPedido().actualizarPedido(elemento, "en delivery");
					}
					JOptionPane.showMessageDialog(null, "Se genero Hoja de Itinerario con el número: "+hojaItinerario.getNumItinerario());
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
	private void llenarTablaRepartidor()
	{
		model.setRowCount(0);
		model.setColumnCount(0);
		model.setColumnIdentifiers(nombreColumnasRepartidor);
		Iterator<RepartidorDTO> Iterador = control.getRepartidor().obtenerRepartidores().iterator();
		while(Iterador.hasNext())
		{
			RepartidorDTO elemento = Iterador.next();
			Object[] fila = {elemento.getDni(), elemento.getNombre(), elemento.getApellido()};
			model.addRow(fila);			
		}
	}
	
	public String fechaActual()
	{
		Calendar c1 = GregorianCalendar.getInstance();
		String fecha=(c1.getTime().getDate()+"-"+(c1.getTime().getMonth()+1)+"-"+(c1.getTime().getYear()+1900));
		return fecha;
	}
}
