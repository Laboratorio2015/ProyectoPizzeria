package presentacion.vista;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import modelo.Itinerarios;

import presentacion.controlador.Controlador;

import com.mxrck.autocompleter.TextAutoCompleter;

import dto.HojaItinerarioDTO;
import dto.PedidoDTO;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class registrarCobroManualmente extends JDialog implements ItemListener{

	private final JPanel contentPanel = new JPanel();
	private pedidosPendientes _padre;
	private registrarCobroManualmente _this;
	private JTextField tfSeleccionItinerario;
	private JTextField tfMuestraRepartidor;
	private JTextField tfMontoPedido;
	private JButton btnRegistrar;
	private TextAutoCompleter AutoCompletar;
	private JComboBox<String> comboBox;
	private Controlador control;
	
	public registrarCobroManualmente(pedidosPendientes padre, final Controlador control) 
	{
		setModal(true);
		padre=_padre;
		_this=this;
		this.control=control;
		setMaximumSize(new Dimension(500, 280));
		setMinimumSize(new Dimension(500, 280));
		setBounds(500, 300, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		comboBox= new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"(Filtrar busqueda por Itinerario o Pedido)", "Itinerario", "Pedido"}));
		comboBox.setBounds(27, 61, 261, 20);
		comboBox.addItemListener(this);
		contentPanel.add(comboBox);
		
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
		
		tfSeleccionItinerario = new JTextField();
		tfSeleccionItinerario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) 
			{
				String itinerario=tfSeleccionItinerario.getText();
				String seleccionado=(String)comboBox.getSelectedItem();
				if(itinerario.compareTo("")!=0 && itinerario.compareTo("(Filtrar busqueda por Itinerario o Pedido)")!=0)
				{
					if(seleccionado.compareTo("Itinerario")==0)
						{
							HojaItinerarioDTO hoja=control.getItinerario().buscarItinerario(Integer.parseInt(itinerario),fechaActual());
							if(hoja!= null)
							{
								tfMuestraRepartidor.setText(hoja.getRepartidor().getNombre()+" "+hoja.getRepartidor().getApellido());
								tfMontoPedido.setText(costoItinerario(hoja).toString());
							}
						}
					else if(seleccionado.compareTo("Pedido")==0)
					{
						PedidoDTO pedido=control.getPedido().buscarPedidoNumeroFecha(Integer.parseInt(itinerario), fechaActual());
						Integer aux=control.getItinerario().buscarItinerarioPorPedido(pedido.getIdpedido(), fechaActual());
						if(aux!=0)
						{
							HojaItinerarioDTO hoja=control.getItinerario().buscarItinerario1(aux);
							if(hoja!= null)
							{
								tfMuestraRepartidor.setText(hoja.getRepartidor().getNombre()+" "+hoja.getRepartidor().getApellido());
								tfMontoPedido.setText(pedido.getTotal().toString());
							}
						}
					}
				}
			}
		});
		tfSeleccionItinerario.setBounds(294, 61, 148, 20);
		contentPanel.add(tfSeleccionItinerario);
		tfSeleccionItinerario.setColumns(10);
		//autocompletar
		AutoCompletar= new TextAutoCompleter(tfSeleccionItinerario);
		AutoCompletar.setCaseSensitive(false); //No sensible a mayúsculas
		
		
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(registrarCobroManualmente.class.getResource("/prototipos/registrarCobroManualmete.png")));
			label.setBounds(0, 0, 484, 261);
			contentPanel.add(label);
		}		
		{
			btnRegistrar= new JButton("OK");
			btnRegistrar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0)
				{
					//pasar los pedidos a estado cobrado
					String itinerario=tfSeleccionItinerario.getText();
					String seleccionado=(String)comboBox.getSelectedItem();
					if(seleccionado.compareTo("Itinerario")==0)
					{
						HojaItinerarioDTO hoja=control.getItinerario().buscarItinerario(Integer.parseInt(itinerario),fechaActual());
						if(hoja.getPedidos().get(0).getEstado().equals("endelivery"))
						{	
							//recorro los pedidos y los paso de estado
							Iterator<PedidoDTO> Iterador=hoja.getPedidos().iterator();
							while(Iterador.hasNext())
							{
								PedidoDTO elemento = Iterador.next();
								control.getPedido().actualizarPedido(elemento, "cobrado");
							}
						}
						else
							JOptionPane.showMessageDialog(null, "¡¡ERROR!! el pedido seleccionado es incorrecto", "Confirmación",JOptionPane.WARNING_MESSAGE);
					}
					else if(seleccionado.compareTo("Pedido")==0)
					{
						PedidoDTO pedido=control.getPedido().buscarPedidoNumeroFecha(Integer.parseInt(itinerario), fechaActual());
						control.getPedido().actualizarPedido(pedido, "cobrado");
					}
					dispose();
				}
			});
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
	private ArrayList<Object> buscarItinerarios()
	{
		ArrayList<Object> result=new ArrayList<Object>();
		Iterator<HojaItinerarioDTO> Iterador=control.getItinerario().obtenerItinerarios().iterator();
		while(Iterador.hasNext())
		{
			HojaItinerarioDTO elemento = Iterador.next();
			result.add(elemento.getIdHojaItinerario());
		}
		return result;
	}
	
	private ArrayList<Object> buscarPedidos()
	{
		ArrayList<Object> result=new ArrayList<Object>();
		Iterator<PedidoDTO> Iterador=control.getPedido().obtenerPedidosDeFecha(fechaActual()).iterator();
		while(Iterador.hasNext())
		{
			PedidoDTO elemento = Iterador.next();
			if(elemento.getEstado().compareTo("endelivery")==0)
				result.add(elemento.getNumPedido());
		}
		return result;
	}
	private Integer costoItinerario(HojaItinerarioDTO hoja)
	{
		Iterator<PedidoDTO> Iterador=hoja.getPedidos().iterator();
		Integer costo=0;
		while(Iterador.hasNext())
		{
			PedidoDTO elemento = Iterador.next();
			costo=costo+elemento.getTotal();
		}
		return costo;
	}
	
	// evento asociado a seleccionar un combo box
	@Override
	public void itemStateChanged(ItemEvent e)
	{
        if (e.getSource()==comboBox)
        {
            String seleccionado=(String)comboBox.getSelectedItem();
            //completar autocomplete dependiendo del combox que aya seleccionado
            if(seleccionado.compareTo("Itinerario")==0)
            	AutoCompletar.addItems(buscarItinerarios());
            else if(seleccionado.compareTo("Pedido")==0)
            	AutoCompletar.addItems(buscarPedidos());
        }
	}
	
	public String fechaActual()
	{
		Calendar c1 = GregorianCalendar.getInstance();
		String fecha=(c1.getTime().getDate()+"-"+(c1.getTime().getMonth()+1)+"-"+(c1.getTime().getYear()+1900));
		return fecha;
	}
}
