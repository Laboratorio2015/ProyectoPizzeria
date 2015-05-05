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
import javax.swing.JTextField;
import com.mxrck.autocompleter.TextAutoCompleter;
import main.Main;
import modelo.Clientes;
import modelo.Productos;
import dto.ItemDTO;
import dto.PedidoDTO;
import dto.ProductoDTO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.HierarchyBoundsAdapter;
import java.awt.event.HierarchyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import org.eclipse.jface.text.source.DefaultAnnotationHover;

import presentacion.controlador.Controlador;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ordenDePedido extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private VentanaPrincipal _padre;
	private ordenDePedido _this;
	public JTextField tfAgregarEmpanada;
	public JTextField tfAgregarPizza;
	public JTextField tfAgregarOtroProducto;
	public JTextField tfBorrarItem;
	public JTextField tfPrecioUniEmpanada;
	public JTextField tfUnidadEmpanada;
	public JTextField tfSubTotalEmpanada;
	public JTextField tfPrecioUniPizza;
	public JTextField tfUnidadPizza;
	public JTextField tfSubTotalPizza;
	public JTextField tfPrecioUniOtro;
	public JTextField tfUnidadOtro;
	public JTextField tfSubTotalOtro;
	public JTextField tfSubTotalQuitar;
	private JTextField tfTotal;
	private ProductoDTO producto;
	private Integer cantidad;
	private JTable tablaItems;
	private DefaultTableModel model;
	private JScrollPane scrollPane_1;
	private Controlador control;
	private JButton btnOrdenar ;
	public ArrayList<ItemDTO> listaItem=new ArrayList<>();
	
	
	public VentanaPrincipal get_padre() 
	{
		return _padre;
	}


	public ordenDePedido(VentanaPrincipal padre, Controlador control) 
	{
		setModal(true);
		padre=_padre;
		_this=this;
		this.control=control;
		setMinimumSize(new Dimension(700, 800));
		setBounds(300, -100, 716, 800);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		tfAgregarEmpanada = new JTextField();
		tfAgregarEmpanada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				producto=new ProductoDTO(tfAgregarEmpanada.getText(),ProductoDTO.buscarPrecio(Main.nuevo, tfAgregarEmpanada.getText()));
				tfPrecioUniEmpanada.setText(Integer.toString(producto.getPrecio()));
			}
		});
	
		
		tfAgregarEmpanada.setBounds(171, 141, 254, 22);
		contentPanel.add(tfAgregarEmpanada);
		tfAgregarEmpanada.setColumns(10);
		
		{
			tfAgregarPizza = new JTextField();
			tfAgregarPizza.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					producto=new ProductoDTO(tfAgregarPizza.getText(),ProductoDTO.buscarPrecio(Main.nuevo, tfAgregarPizza.getText()));
					tfPrecioUniPizza.setText(Integer.toString(producto.getPrecio()));
				}
			});
			tfAgregarPizza.setColumns(10);
			tfAgregarPizza.setBounds(168, 284, 254, 25);
			contentPanel.add(tfAgregarPizza);
		}
		{
			tfAgregarOtroProducto = new JTextField();
			tfAgregarOtroProducto.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					producto=new ProductoDTO(tfAgregarOtroProducto.getText(),ProductoDTO.buscarPrecio(Main.nuevo, tfAgregarOtroProducto.getText()));
					tfPrecioUniOtro.setText(Integer.toString(producto.getPrecio()));
				}
			});
			tfAgregarOtroProducto.setColumns(10);
			tfAgregarOtroProducto.setBounds(171, 420, 254, 25);
			contentPanel.add(tfAgregarOtroProducto);
		}
		{
			tfBorrarItem = new JTextField();
			tfBorrarItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					producto=new ProductoDTO(tfBorrarItem.getText(),ProductoDTO.buscarPrecio(Main.nuevo, tfBorrarItem.getText()));
					int filas=model.getRowCount();
					for(int i=0; i<filas;i++)
					{
						if(tfBorrarItem.getText().equals(model.getValueAt(i, 0)))
							tfSubTotalQuitar.setText((String) model.getValueAt(i, 2));
						else
							tfSubTotalQuitar.setText(Integer.toString(1));
					}
					
				}
			});
			tfBorrarItem.setColumns(10);
			tfBorrarItem.setBounds(113, 538, 254, 25);
			contentPanel.add(tfBorrarItem);
		}
		{
			tfPrecioUniEmpanada = new JTextField();
			tfPrecioUniEmpanada.setEditable(false);
			tfPrecioUniEmpanada.setColumns(10);
			tfPrecioUniEmpanada.setBounds(435, 141, 50, 22);
			contentPanel.add(tfPrecioUniEmpanada);
		}
		{
			tfUnidadEmpanada = new JTextField();
			tfUnidadEmpanada.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					cantidad=Integer.parseInt(tfUnidadEmpanada.getText());
					tfSubTotalEmpanada.setText(Integer.toString(producto.getPrecio()*cantidad));
				}
			});
			tfUnidadEmpanada.setColumns(10);
			tfUnidadEmpanada.setBounds(490, 140, 49, 24);
			contentPanel.add(tfUnidadEmpanada);
		}
		{
			tfSubTotalEmpanada = new JTextField();
			tfSubTotalEmpanada.setEditable(false);
			tfSubTotalEmpanada.setColumns(10);
			tfSubTotalEmpanada.setBounds(555, 140, 57, 23);
			contentPanel.add(tfSubTotalEmpanada);
		}
		{
			tfPrecioUniPizza = new JTextField();
			tfPrecioUniPizza.setEditable(false);
			tfPrecioUniPizza.setColumns(10);
			tfPrecioUniPizza.setBounds(432, 285, 48, 22);
			contentPanel.add(tfPrecioUniPizza);
		}
		{
			tfUnidadPizza = new JTextField();
			tfUnidadPizza.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					cantidad=Integer.parseInt(tfUnidadPizza.getText());
					tfSubTotalPizza.setText(Integer.toString(producto.getPrecio()*cantidad));
				}
			});
			tfUnidadPizza.setColumns(10);
			tfUnidadPizza.setBounds(488, 285, 47, 22);
			contentPanel.add(tfUnidadPizza);
		}
		{
			tfSubTotalPizza = new JTextField();
			tfSubTotalPizza.setEditable(false);
			tfSubTotalPizza.setColumns(10);
			tfSubTotalPizza.setBounds(553, 285, 56, 22);
			contentPanel.add(tfSubTotalPizza);
		}
		{
			tfPrecioUniOtro = new JTextField();
			tfPrecioUniOtro.setEditable(false);
			tfPrecioUniOtro.setColumns(10);
			tfPrecioUniOtro.setBounds(435, 421, 48, 22);
			contentPanel.add(tfPrecioUniOtro);
		}
		{
			tfUnidadOtro = new JTextField();
			tfUnidadOtro.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					cantidad=Integer.parseInt(tfUnidadOtro.getText());
					tfSubTotalOtro.setText(Integer.toString(producto.getPrecio()*cantidad));
				}
			});
			tfUnidadOtro.setColumns(10);
			tfUnidadOtro.setBounds(490, 421, 48, 22);
			contentPanel.add(tfUnidadOtro);
		}
		{
			tfSubTotalOtro = new JTextField();
			tfSubTotalOtro.setEditable(false);
			tfSubTotalOtro.setColumns(10);
			tfSubTotalOtro.setBounds(555, 421, 57, 22);
			contentPanel.add(tfSubTotalOtro);
		}
		{
			tfSubTotalQuitar = new JTextField();
			tfSubTotalQuitar.setEditable(false);
			tfSubTotalQuitar.setColumns(10);
			tfSubTotalQuitar.setBounds(378, 539, 49, 22);
			contentPanel.add(tfSubTotalQuitar);
		}
		{
			tfTotal = new JTextField();
			tfTotal.setEditable(false);
			tfTotal.setBorder(null);
			tfTotal.setBackground(new Color(153, 0, 0));
			tfTotal.setBounds(560, 705, 75, 25);
			contentPanel.add(tfTotal);
			tfTotal.setColumns(10);
		}
		model= new DefaultTableModel();
		model.addColumn("Producto");
		model.addColumn("Cantidad");
		model.addColumn("Precio");
		{
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(130, 619, 370, 71);
			contentPanel.add(scrollPane_1);
			
			tablaItems = new JTable();
		
			scrollPane_1.setViewportView(tablaItems);
		}
		{
			JLabel label = new JLabel("");
			label.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
			label.setIcon(new ImageIcon(ordenDePedido.class.getResource("/prototipos/orden de pedido.png")));
			label.setBounds(0, 0, 700, 761);
			contentPanel.add(label);
		}
		
		{
			btnOrdenar= new JButton("OK");
			btnOrdenar.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent e) 
				{
					PedidoDTO nuevoPedido=new PedidoDTO();
					nuevoPedido.setIdpedido(Main.listaPedidos.pedidos.size()+1);
					nuevoPedido.set_estado("solicitado");
					nuevoPedido.setTotal(Integer.parseInt(tfTotal.getText()));
					nuevoPedido.set_comanda(nuevoPedido.getIdpedido());
					nuevoPedido.set_ticket(nuevoPedido.getIdpedido());
					nuevoPedido.setProductos(listaItem);
					seleccionDeCliente selecCliente=new seleccionDeCliente(_this,nuevoPedido);
					selecCliente.setVisible(true);
					_this.dispose();
				}
			});
			JButton btnAgregarEmpanada = new JButton("New button");
			btnAgregarEmpanada.setOpaque(false);
			btnAgregarEmpanada.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					ItemDTO item=new ItemDTO(producto, cantidad);
					model.addRow(new String[] {item.getProducto().getNombre(),""+item.getCantidad(),""+(item.getProducto().getPrecio()* item.getCantidad())});
					tablaItems.setModel(model);
					int filas=model.getRowCount();
					int total=0;
					for(int i=0; i<filas;i++)
					{
						total=total+(Integer.parseInt((String) model.getValueAt(i,2)));
						
					}
					tfTotal.setText(Integer.toString(total));
					listaItem.add(item);
					
				}
			});
			btnAgregarEmpanada.setBounds(642, 140, 36, 35);
			contentPanel.add(btnAgregarEmpanada);
			btnOrdenar.setOpaque(false);
			btnOrdenar.setBounds(113, 705, 164, 34);
			contentPanel.add(btnOrdenar);
			btnOrdenar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnOrdenar);
		}
		{
			JButton btnCancelar = new JButton("Cancel");
			btnCancelar.setOpaque(false);
			btnCancelar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) 
				{
					dispose();
				}
			});
			btnCancelar.setBounds(328, 705, 164, 34);
			contentPanel.add(btnCancelar);
			btnCancelar.setActionCommand("Cancel");
		}
				
				JButton btnQuitar = new JButton("New button");
				btnQuitar.addMouseListener(new MouseAdapter() 
				{
					@Override
					public void mouseClicked(MouseEvent e) 
					{
						int filas=model.getRowCount();
						int filaEliminar=0;
						for(int i=0; i<filas;i++)
						{
							if(tfBorrarItem.getText().equals(model.getValueAt(i, 0)))
							{
								filaEliminar=filaEliminar+i;
								tfTotal.setText(Integer.toString((Integer.parseInt(tfTotal.getText())-Integer.parseInt((String)model.getValueAt(i, 2)))));
							}
						}
						model.removeRow(filaEliminar);
					}
				});
				btnQuitar.setOpaque(false);
				btnQuitar.setBounds(459, 533, 36, 35);
				contentPanel.add(btnQuitar);
				
				JButton btnAgregarOtro = new JButton("New button");
				btnAgregarOtro.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						ItemDTO item=new ItemDTO(producto, cantidad);
						model.addRow(new String[] {item.getProducto().getNombre(),""+item.getCantidad(),""+(item.getProducto().getPrecio()* item.getCantidad())});
						tablaItems.setModel(model);
						int filas=model.getRowCount();

						int total=0;
						for(int i=0; i<filas;i++)
						{
							total=total+(Integer.parseInt((String) model.getValueAt(i,2)));
						}
						tfTotal.setText(Integer.toString(total));
						listaItem.add(item);
					}
				});
				
				btnAgregarOtro.setOpaque(false);
				btnAgregarOtro.setBounds(642, 421, 36, 35);
				contentPanel.add(btnAgregarOtro);
				
				JButton btnAgregarPizza = new JButton("New button");
				btnAgregarPizza.addMouseListener(new MouseAdapter() 
				{
					@Override
					public void mouseClicked(MouseEvent arg0) 
					{
						ItemDTO item=new ItemDTO(producto, cantidad);
						model.addRow(new String[] {item.getProducto().getNombre(),""+item.getCantidad(),""+(item.getProducto().getPrecio()* item.getCantidad())});
						tablaItems.setModel(model);
						int filas=model.getRowCount();

						int total=0;
						for(int i=0; i<filas;i++)
						{
							total=total+(Integer.parseInt((String) model.getValueAt(i,2)));
						}
						tfTotal.setText(Integer.toString(total));
						listaItem.add(item);
					}
				});
				btnAgregarPizza.setOpaque(false);
				btnAgregarPizza.setBounds(640, 284, 36, 35);
				contentPanel.add(btnAgregarPizza);
				
				TextAutoCompleter AutoCompletar = new TextAutoCompleter(tfAgregarEmpanada);
				AutoCompletar.setCaseSensitive(false); //No sensible a mayúsculas
				AutoCompletar.addItem("carne");
				AutoCompletar.addItem("verdura");
				AutoCompletar.addItem("pollo");
				AutoCompletar.addItem("humita");
				
				TextAutoCompleter autoCompletar2=new TextAutoCompleter(tfAgregarPizza);
				autoCompletar2.setCaseSensitive(false);
				autoCompletar2.addItem("muzzarella");
				autoCompletar2.addItem("jamon y morrones");
				autoCompletar2.addItem("mixta");
				autoCompletar2.addItem("fugazzeta");
				autoCompletar2.addItem("faena");
				autoCompletar2.addItem("con salchicas");
				
				TextAutoCompleter autoCompletar3=new TextAutoCompleter(tfAgregarOtroProducto);
				autoCompletar3.setCaseSensitive(false);
				autoCompletar3.addItem("coca-cola 1.5 lts");
				autoCompletar3.addItem("pepsi 1.5 lts");
				autoCompletar3.addItem("sprite 1.5 lts");
				autoCompletar3.addItem("seven-up 1.5 lts");
				autoCompletar3.addItem("mirinda 1.5 lts");
				
				TextAutoCompleter autoCompletar4=new TextAutoCompleter(tfBorrarItem);
				autoCompletar4.setCaseSensitive(false);
				autoCompletar4.addItem("carne");
				autoCompletar4.addItem("verdura");
				autoCompletar4.addItem("pollo");
				autoCompletar4.addItem("humita");
				autoCompletar4.addItem("muzzarella");
				autoCompletar4.addItem("jamon y morrones");
				autoCompletar4.addItem("mixta");
				autoCompletar4.addItem("fugazzeta");
				autoCompletar4.addItem("faena");
				autoCompletar4.addItem("con salchicas");
				autoCompletar4.addItem("coca-cola 1.5 lts");
				autoCompletar4.addItem("pepsi 1.5 lts");
				autoCompletar4.addItem("sprite 1.5 lts");
				autoCompletar4.addItem("seven-up 1.5 lts");
				autoCompletar4.addItem("mirinda 1.5 lts");
				
				
	}


	public JButton getBtnOrdenar()
	{
		return btnOrdenar;
	}
}
