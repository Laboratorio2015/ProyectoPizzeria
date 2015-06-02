package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.Component;
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
import dto.ItemDTO;
import dto.PedidoDTO;
import dto.ProductoDTO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import presentacion.controlador.Controlador;
import javax.swing.JCheckBox;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class ordenDePedido extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private VentanaPrincipal _padre;
	private ordenDePedido _this;
	private JTextField tfAgregarEmpanada;
	private JTextField tfAgregarPizza;
	private JTextField tfAgregarOtroProducto;
	private JTextField tfPrecioUniEmpanada;
	private JTextField tfUnidadEmpanada;
	private JTextField tfSubTotalEmpanada;
	private JTextField tfPrecioUniPizza;
	private JTextField tfUnidadPizza;
	private JTextField tfSubTotalPizza;
	private JTextField tfPrecioUniOtro;
	private JTextField tfUnidadOtro;
	private JTextField tfSubTotalOtro;
	private JTextField tfTotal;
	private ProductoDTO producto;
	private Integer cantidad;
	private JTable tablaItems;
	private DefaultTableModel model;
	private JScrollPane scrollPane_1;
	private Controlador control;
	private JButton btnOrdenar ;
	private JButton btnOrdenar1;
	private PedidoDTO pedidoCambiar;
	private JButton btnVerOtros;
	private JButton btnVerEmpanadas;
	private JButton btnVerPizzas ;
	private JButton btnVerPromociones;
	private JButton btnAgregarComentario;
	private JButton btnEditar;
	private JCheckBox checkBoxDelivery;
	private ArrayList<ItemDTO> listaItem=new ArrayList<>();
	
	
	
	
	public VentanaPrincipal get_padre() 
	{
		return _padre;
	}


	/**
	 * @wbp.parser.constructor
	 */
	public ordenDePedido(VentanaPrincipal padre,final Controlador control) 
	{
		setModal(true);
		padre=_padre;
		this.control=control;
		_this=this;
		setMinimumSize(new Dimension(700, 680));
		setBounds(300, -30, 716, 750);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		tfAgregarEmpanada = new JTextField();
		tfAgregarEmpanada.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyTyped(KeyEvent evt) 
			{
				validarTexto(evt,tfAgregarEmpanada);
			}
			@Override
			public void keyReleased(KeyEvent e) 
			{
				producto=control.getProducto().buscarProductoPorNombre(tfAgregarEmpanada.getText());
				if(tfAgregarEmpanada.getText().length()>4)
					tfPrecioUniEmpanada.setText(Integer.toString(producto.getPrecio()));
			}
			});	
		
		tfAgregarEmpanada.setBounds(168, 81, 254, 25);
		contentPanel.add(tfAgregarEmpanada);
		tfAgregarEmpanada.setColumns(10);
		
		{
			tfAgregarPizza = new JTextField();
			tfAgregarPizza.addKeyListener(new KeyAdapter() 
			{
				@Override
				public void keyTyped(KeyEvent evt) 
				{
					validarTexto(evt, tfAgregarPizza);
				}
			
			@Override
			public void keyReleased(KeyEvent e) 
			{
				producto=control.getProducto().buscarProductoPorNombre(tfAgregarPizza.getText());
				if(tfAgregarPizza.getText().length()>4)
				tfPrecioUniPizza.setText(Integer.toString(producto.getPrecio()));
			}
			});
					
			
			tfAgregarPizza.setColumns(10);
			tfAgregarPizza.setBounds(166, 225, 254, 25);
			contentPanel.add(tfAgregarPizza);
		}
		{
			tfAgregarOtroProducto = new JTextField();
			tfAgregarOtroProducto.addKeyListener(new KeyAdapter() 
			{
				@Override
				public void keyTyped(KeyEvent evt) 
				{
					validarTexto(evt,tfAgregarOtroProducto);
				}
				
				@Override
				public void keyReleased(KeyEvent e) 
				{
					producto=control.getProducto().buscarProductoPorNombre(tfAgregarOtroProducto.getText());
					if(tfAgregarOtroProducto.getText().length()>4)
					tfPrecioUniOtro.setText(Integer.toString(producto.getPrecio()));
				}
			});
			/*tfAgregarOtroProducto.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					producto=control.getProducto().buscarProductoPorNombre(tfAgregarOtroProducto.getText());
					tfPrecioUniOtro.setText(Integer.toString(producto.getPrecio()));
				}
			});*/
			tfAgregarOtroProducto.setColumns(10);
			tfAgregarOtroProducto.setBounds(168, 361, 254, 25);
			contentPanel.add(tfAgregarOtroProducto);
		}
		{
			tfPrecioUniEmpanada = new JTextField();
			tfPrecioUniEmpanada.setEditable(false);
			tfPrecioUniEmpanada.setColumns(10);
			tfPrecioUniEmpanada.setBounds(432, 81, 50, 25);
			contentPanel.add(tfPrecioUniEmpanada);
		}
		{
			tfUnidadEmpanada = new JTextField();
			tfUnidadEmpanada.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent evt)
				{
					validarNumeros(evt,tfUnidadEmpanada);
				}
			});
			tfUnidadEmpanada.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					cantidad=Integer.parseInt(tfUnidadEmpanada.getText());
					tfSubTotalEmpanada.setText(Integer.toString(producto.getPrecio()*cantidad));
				}
			});
			tfUnidadEmpanada.setColumns(10);
			tfUnidadEmpanada.setBounds(488, 81, 49, 25);
			contentPanel.add(tfUnidadEmpanada);
		}
		{
			tfSubTotalEmpanada = new JTextField();
			tfSubTotalEmpanada.setEditable(false);
			tfSubTotalEmpanada.setColumns(10);
			tfSubTotalEmpanada.setBounds(552, 81, 59, 25);
			contentPanel.add(tfSubTotalEmpanada);
		}
		{
			tfPrecioUniPizza = new JTextField();
			tfPrecioUniPizza.setEditable(false);
			tfPrecioUniPizza.setColumns(10);
			tfPrecioUniPizza.setBounds(430, 225, 48, 25);
			contentPanel.add(tfPrecioUniPizza);
		}
		{
			tfUnidadPizza = new JTextField();
			tfUnidadPizza.addKeyListener(new KeyAdapter() 
			{
				@Override
				public void keyTyped(KeyEvent evt) 
				{
					validarNumeros(evt, tfUnidadPizza);
				}
			});
			tfUnidadPizza.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					cantidad=Integer.parseInt(tfUnidadPizza.getText());
					tfSubTotalPizza.setText(Integer.toString(producto.getPrecio()*cantidad));
				}
			});
			tfUnidadPizza.setColumns(10);
			tfUnidadPizza.setBounds(486, 225, 47, 25);
			contentPanel.add(tfUnidadPizza);
		}
		{
			tfSubTotalPizza = new JTextField();
			tfSubTotalPizza.setEditable(false);
			tfSubTotalPizza.setColumns(10);
			tfSubTotalPizza.setBounds(550, 225, 56, 25);
			contentPanel.add(tfSubTotalPizza);
		}
		{
			tfPrecioUniOtro = new JTextField();
			tfPrecioUniOtro.setEditable(false);
			tfPrecioUniOtro.setColumns(10);
			tfPrecioUniOtro.setBounds(432, 361, 48, 25);
			contentPanel.add(tfPrecioUniOtro);
		}
		{
			tfUnidadOtro = new JTextField();
			tfUnidadOtro.addKeyListener(new KeyAdapter()
			{
				@Override
				public void keyTyped(KeyEvent evt)
				{
					validarNumeros(evt,tfUnidadOtro);
				}
			});
			tfUnidadOtro.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					cantidad=Integer.parseInt(tfUnidadOtro.getText());
					tfSubTotalOtro.setText(Integer.toString(producto.getPrecio()*cantidad));
				}
			});
			tfUnidadOtro.setColumns(10);
			tfUnidadOtro.setBounds(488, 361, 48, 25);
			contentPanel.add(tfUnidadOtro);
		}
		{
			tfSubTotalOtro = new JTextField();
			tfSubTotalOtro.setEditable(false);
			tfSubTotalOtro.setColumns(10);
			tfSubTotalOtro.setBounds(552, 361, 57, 25);
			contentPanel.add(tfSubTotalOtro);
		}
		{
			tfTotal = new JTextField();
			tfTotal.setEditable(false);
			tfTotal.setBorder(null);
			tfTotal.setBackground(new Color(153, 0, 0));
			tfTotal.setBounds(556, 667, 75, 25);
			contentPanel.add(tfTotal);
			tfTotal.setColumns(10);
		}
		
		model=crearModelo();
		
		checkBoxDelivery= new JCheckBox("");
		checkBoxDelivery.setBackground(new Color(204, 204, 0));
		checkBoxDelivery.setBounds(620, 600, 27, 33);
		contentPanel.add(checkBoxDelivery);
		{
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(114, 556, 421, 101);
			contentPanel.add(scrollPane_1);
			tablaItems = new JTable()
			{
			    public boolean isCellEditable(int rowIndex, int colIndex) {
			    	if (colIndex==1 || colIndex==3) {
			            return true;  //La columna 1 y 3 son editables.
			        }
			        return false;  //El resto de celdas no son editables.
			    }
			};
			tablaItems.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) 
				{
					if(e.getKeyCode() == KeyEvent.VK_ENTER)
						actualizarPrecio();
				}
			});
			 
			
			tablaItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
			scrollPane_1.setViewportView(tablaItems);
		}
		{
			btnEditar = new JButton("editar");
			btnEditar.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0)
				{
					tablaItems.editCellAt(tablaItems.getSelectedRow(),1);
				    Component aComp=tablaItems.getEditorComponent();
				    aComp.requestFocus();
					tablaItems.isCellEditable(tablaItems.getSelectedRow(), 1);
				}
			});
			btnEditar.setBounds(552, 553, 124, 23);
			contentPanel.add(btnEditar);
		}
		
		btnAgregarComentario = new JButton("agregar coment");
		btnAgregarComentario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				tablaItems.editCellAt(tablaItems.getSelectedRow(),3);
			    Component aComp=tablaItems.getEditorComponent();
			    aComp.requestFocus();
				tablaItems.isCellEditable(tablaItems.getSelectedRow(), 3);
			}
		});
		btnAgregarComentario.setBounds(552, 576, 124, 23);
		contentPanel.add(btnAgregarComentario);
		{
			JLabel label = new JLabel("");
			label.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
			label.setIcon(new ImageIcon(ordenDePedido.class.getResource("/prototipos/orden de pedido.png")));
			label.setBounds(0, 0, 700, 710);
			contentPanel.add(label);
		}
		
		{
			btnOrdenar= new JButton("OK");
			
			
			JButton btnAgregarEmpanada = new JButton("New button");
			btnAgregarEmpanada.setOpaque(false);
			btnAgregarEmpanada.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					Integer subtotal=Integer.parseInt(tfPrecioUniEmpanada.getText())* Integer.parseInt(tfUnidadEmpanada.getText());
					model.addRow(new String[] {tfAgregarEmpanada.getText(),""+tfUnidadEmpanada.getText(),""+subtotal});
					tablaItems.setModel(model);
					tfTotal.setText(actualizarTotal(tfSubTotalEmpanada,"suma"));
					vaciarFormulario();					
				}
			});
			btnAgregarEmpanada.setBounds(640, 81, 36, 35);
			contentPanel.add(btnAgregarEmpanada);
			btnOrdenar.setOpaque(false);
			btnOrdenar.setBounds(110, 665, 163, 34);
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
					vaciarFormulario();
					dispose();
				}
			});
			btnCancelar.setBounds(325, 665, 163, 34);
			contentPanel.add(btnCancelar);
			btnCancelar.setActionCommand("Cancel");
		}
				
				JButton btnQuitar = new JButton("New button");
				btnQuitar.addMouseListener(new MouseAdapter() 
				{
					@Override
					public void mouseClicked(MouseEvent e) 
					{											
						int numFilaSeleccionada=tablaItems.getSelectedRow();
						tfTotal.setText(Integer.toString((Integer.parseInt(tfTotal.getText())-Integer.parseInt((String)model.getValueAt(numFilaSeleccionada, 2)))));
						getModel().removeRow(numFilaSeleccionada);	
					}
				});
				btnQuitar.setOpaque(false);
				btnQuitar.setBounds(42, 600, 36, 35);
				contentPanel.add(btnQuitar);
				
				JButton btnAgregarOtro = new JButton("New button");
				btnAgregarOtro.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{					
						Integer subtotal=Integer.parseInt(tfPrecioUniOtro.getText())* Integer.parseInt(tfUnidadOtro.getText());
						model.addRow(new String[] {tfAgregarOtroProducto.getText(),""+tfUnidadOtro.getText(),""+subtotal});
						tablaItems.setModel(model);
						tfTotal.setText(actualizarTotal(tfSubTotalOtro,"suma"));
						vaciarFormulario();
					}

					
				});
				
				btnAgregarOtro.setOpaque(false);
				btnAgregarOtro.setBounds(640, 361, 36, 35);
				contentPanel.add(btnAgregarOtro);
				
				JButton btnAgregarPizza = new JButton("New button");
				btnAgregarPizza.addMouseListener(new MouseAdapter() 
				{
					@Override
					public void mouseClicked(MouseEvent arg0) 
					{
						Integer subtotal=Integer.parseInt(tfPrecioUniPizza.getText())* Integer.parseInt(tfUnidadPizza.getText());
						model.addRow(new String[] {tfAgregarPizza.getText(),""+tfUnidadPizza.getText(),""+subtotal});
						tablaItems.setModel(model);
						tfTotal.setText(actualizarTotal(tfSubTotalPizza,"suma"));
						vaciarFormulario();
					}
				});
				btnAgregarPizza.setOpaque(false);
				btnAgregarPizza.setBounds(636, 226, 36, 35);
				contentPanel.add(btnAgregarPizza);
				
				TextAutoCompleter AutoCompletar = new TextAutoCompleter(tfAgregarEmpanada);
				AutoCompletar.setCaseSensitive(false); //No sensible a mayúsculas
				AutoCompletar.addItems(control.getProducto().buscaNombresProductos("empanada"));
				
				TextAutoCompleter autoCompletar2=new TextAutoCompleter(tfAgregarPizza);
				autoCompletar2.setCaseSensitive(false);
				autoCompletar2.addItems(control.getProducto().buscaNombresProductos("pizza"));
				
				TextAutoCompleter autoCompletar3=new TextAutoCompleter(tfAgregarOtroProducto);
				autoCompletar3.setCaseSensitive(false);
				autoCompletar3.addItems(control.getProducto().buscaNombresProductos("otros"));
				
				btnVerPromociones= new JButton("");
				btnVerPromociones.setOpaque(false);
				btnVerPromociones.setBounds(32, 472, 60, 23);
				contentPanel.add(btnVerPromociones);
				
				btnVerPizzas= new JButton("");
				btnVerPizzas.setOpaque(false);
				btnVerPizzas.setBounds(44, 224, 48, 23);
				contentPanel.add(btnVerPizzas);
				
				btnVerEmpanadas= new JButton("");
				btnVerEmpanadas.setOpaque(false);
				btnVerEmpanadas.setBounds(32, 82, 48, 23);
				contentPanel.add(btnVerEmpanadas);
				
				btnVerOtros = new JButton("");
				btnVerOtros.setOpaque(false);
				btnVerOtros.setBounds(37, 362, 68, 23);
				contentPanel.add(btnVerOtros);
		}
	
	//se agrega el siguiente constructor para la modificacion de pedidos
	public ordenDePedido(VentanaPrincipal padre,final PedidoDTO pedidoCambiar,final Controlador control) 
	{
		setModal(true);
		padre=_padre;
		_this=this;
		this.pedidoCambiar=pedidoCambiar;
		this.control=control;
		setMinimumSize(new Dimension(700, 700));
		setBounds(300, 0, 716, 740);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		tfAgregarEmpanada = new JTextField();
		tfAgregarEmpanada.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyTyped(KeyEvent evt) 
			{
				validarTexto(evt,tfAgregarEmpanada);
			}
		});
		tfAgregarEmpanada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				producto=control.getProducto().buscarProductoPorNombre(tfAgregarEmpanada.getText());
				tfPrecioUniEmpanada.setText(Integer.toString(producto.getPrecio()));
			}
		});
	
		
		tfAgregarEmpanada.setBounds(170, 161, 254, 22);
		contentPanel.add(tfAgregarEmpanada);
		tfAgregarEmpanada.setColumns(10);
		
		{
			tfAgregarPizza = new JTextField();
			tfAgregarPizza.addKeyListener(new KeyAdapter() 
			{
				@Override
				public void keyTyped(KeyEvent evt) 
				{
					validarTexto(evt,tfAgregarPizza);
				}
			});
			tfAgregarPizza.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					producto=control.getProducto().buscarProductoPorNombre(tfAgregarPizza.getText());
					tfPrecioUniPizza.setText(Integer.toString(producto.getPrecio()));
				}
			});
			tfAgregarPizza.setColumns(10);
			tfAgregarPizza.setBounds(168, 305, 254, 25);
			contentPanel.add(tfAgregarPizza);
		}
		{
			tfAgregarOtroProducto = new JTextField();
			tfAgregarOtroProducto.addKeyListener(new KeyAdapter() 
			{
				@Override
				public void keyTyped(KeyEvent evt) 
				{
					validarTexto(evt,tfAgregarOtroProducto);
				}
			});
			tfAgregarOtroProducto.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					producto=control.getProducto().buscarProductoPorNombre(tfAgregarOtroProducto.getText());
					tfPrecioUniOtro.setText(Integer.toString(producto.getPrecio()));
				}
			});
			tfAgregarOtroProducto.setColumns(10);
			tfAgregarOtroProducto.setBounds(169, 440, 254, 25);
			contentPanel.add(tfAgregarOtroProducto);
		}
		{
			tfPrecioUniEmpanada = new JTextField();
			tfPrecioUniEmpanada.setEditable(false);
			tfPrecioUniEmpanada.setColumns(10);
			tfPrecioUniEmpanada.setBounds(433, 161, 50, 22);
			contentPanel.add(tfPrecioUniEmpanada);
		}
		{
			tfUnidadEmpanada = new JTextField();
			tfUnidadEmpanada.addKeyListener(new KeyAdapter() 
			{
				@Override
				public void keyTyped(KeyEvent evt) 
				{
					validarNumeros(evt, tfUnidadEmpanada);
				}
			});
			tfUnidadEmpanada.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					cantidad=Integer.parseInt(tfUnidadEmpanada.getText());
					tfSubTotalEmpanada.setText(Integer.toString(producto.getPrecio()*cantidad));
				}
			});
			tfUnidadEmpanada.setColumns(10);
			tfUnidadEmpanada.setBounds(491, 161, 49, 24);
			contentPanel.add(tfUnidadEmpanada);
		}
		{
			tfSubTotalEmpanada = new JTextField();
			tfSubTotalEmpanada.setEditable(false);
			tfSubTotalEmpanada.setColumns(10);
			tfSubTotalEmpanada.setBounds(555, 161, 57, 23);
			contentPanel.add(tfSubTotalEmpanada);
		}
		{
			tfPrecioUniPizza = new JTextField();
			tfPrecioUniPizza.setEditable(false);
			tfPrecioUniPizza.setColumns(10);
			tfPrecioUniPizza.setBounds(433, 305, 48, 22);
			contentPanel.add(tfPrecioUniPizza);
		}
		{
			tfUnidadPizza = new JTextField();
			tfUnidadPizza.addKeyListener(new KeyAdapter() 
			{
				@Override
				public void keyTyped(KeyEvent evt) 
				{
					validarNumeros(evt,tfUnidadPizza);
				}
			});
			tfUnidadPizza.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					cantidad=Integer.parseInt(tfUnidadPizza.getText());
					tfSubTotalPizza.setText(Integer.toString(producto.getPrecio()*cantidad));
				}
			});
			tfUnidadPizza.setColumns(10);
			tfUnidadPizza.setBounds(488, 305, 47, 22);
			contentPanel.add(tfUnidadPizza);
		}
		{
			tfSubTotalPizza = new JTextField();
			tfSubTotalPizza.setEditable(false);
			tfSubTotalPizza.setColumns(10);
			tfSubTotalPizza.setBounds(553, 305, 56, 22);
			contentPanel.add(tfSubTotalPizza);
		}
		{
			tfPrecioUniOtro = new JTextField();
			tfPrecioUniOtro.setEditable(false);
			tfPrecioUniOtro.setColumns(10);
			tfPrecioUniOtro.setBounds(435, 441, 48, 22);
			contentPanel.add(tfPrecioUniOtro);
		}
		{
			tfUnidadOtro = new JTextField();
			tfUnidadOtro.addKeyListener(new KeyAdapter() 
			{
				@Override
				public void keyTyped(KeyEvent evt) 
				{
					validarNumeros(evt, tfUnidadOtro);
				}
			});
			tfUnidadOtro.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					cantidad=Integer.parseInt(tfUnidadOtro.getText());
					tfSubTotalOtro.setText(Integer.toString(producto.getPrecio()*cantidad));
				}
			});
			tfUnidadOtro.setColumns(10);
			tfUnidadOtro.setBounds(491, 441, 48, 22);
			contentPanel.add(tfUnidadOtro);
		}
		{
			tfSubTotalOtro = new JTextField();
			tfSubTotalOtro.setEditable(false);
			tfSubTotalOtro.setColumns(10);
			tfSubTotalOtro.setBounds(555, 441, 57, 22);
			contentPanel.add(tfSubTotalOtro);
		}
		{
			tfTotal = new JTextField();
			tfTotal.setEditable(false);
			tfTotal.setBorder(null);
			tfTotal.setBackground(new Color(153, 0, 0));
			tfTotal.setBounds(561, 652, 75, 25);
			contentPanel.add(tfTotal);
			tfTotal.setColumns(10);
		}
		
		model=crearModelo();
		{
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(99, 533, 421, 101);
			contentPanel.add(scrollPane_1);
			tablaItems = new JTable();
			scrollPane_1.setViewportView(tablaItems);
			
		}
		
		{
			JLabel label = new JLabel("");
			label.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
			label.setIcon(new ImageIcon(ordenDePedido.class.getResource("/prototipos/orden de pedido.png")));
			label.setBounds(0, 0, 700, 700);
			contentPanel.add(label);
		}
		
		{
			btnOrdenar1= new JButton("OKi");
			btnOrdenar1.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0)
				{
					PedidoDTO nuevoPedido=new PedidoDTO();
					nuevoPedido.setIdpedido(ordenDePedido.this.pedidoCambiar.getIdpedido());
					nuevoPedido.set_estado("solicitado");
					nuevoPedido.setTotal(Integer.parseInt(tfTotal.getText()));
					nuevoPedido.set_comanda(nuevoPedido.getIdpedido());
					nuevoPedido.set_ticket(nuevoPedido.getIdpedido());
					nuevoPedido.setProductos(generarListaItem());
					nuevoPedido.setCliente(ordenDePedido.this.pedidoCambiar.getCliente());
					control.getPedido().quitarPedido(pedidoCambiar);
					control.getPedido().agregarPedido(nuevoPedido);
					control.getMonitorCocina().nuevoPedido(nuevoPedido);
					vaciarFormulario();
					dispose();
				}
			});
			
			JButton btnAgregarEmpanada = new JButton("New button");
			btnAgregarEmpanada.setOpaque(false);
			btnAgregarEmpanada.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					Integer subtotal=Integer.parseInt(tfPrecioUniEmpanada.getText())* Integer.parseInt(tfUnidadEmpanada.getText());
					model.addRow(new String[] {tfAgregarEmpanada.getText(),""+tfUnidadEmpanada.getText(),""+subtotal});
					tablaItems.setModel(model);
					tfTotal.setText(actualizarTotal(tfSubTotalEmpanada,"suma"));
					vaciarFormulario();									
				}
			});
			btnAgregarEmpanada.setBounds(642, 158, 36, 35);
			contentPanel.add(btnAgregarEmpanada);
			btnOrdenar1.setOpaque(false);
			btnOrdenar1.setBounds(114, 653, 163, 34);
			contentPanel.add(btnOrdenar1);
			btnOrdenar1.setActionCommand("OK");
			getRootPane().setDefaultButton(btnOrdenar1);
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
			btnCancelar.setBounds(329, 652, 163, 34);
			contentPanel.add(btnCancelar);
			btnCancelar.setActionCommand("Cancel");
		}
				
				JButton btnQuitar = new JButton("New button");
				btnQuitar.addMouseListener(new MouseAdapter() 
				{
					@Override
					public void mouseClicked(MouseEvent e) 
					{											
						int numFilaSeleccionada=tablaItems.getSelectedRow();
						tfTotal.setText(Integer.toString((Integer.parseInt(tfTotal.getText())-Integer.parseInt((String)model.getValueAt(numFilaSeleccionada, 2)))));
						getModel().removeRow(numFilaSeleccionada);	
					}
				});
				btnQuitar.setOpaque(false);
				btnQuitar.setBounds(44, 565, 36, 35);
				contentPanel.add(btnQuitar);
				
				JButton btnAgregarOtro = new JButton("New button");
				btnAgregarOtro.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						Integer subtotal=Integer.parseInt(tfPrecioUniOtro.getText())* Integer.parseInt(tfUnidadOtro.getText());
						model.addRow(new String[] {tfAgregarOtroProducto.getText(),""+tfUnidadOtro.getText(),""+subtotal});
						tablaItems.setModel(model);
						tfTotal.setText(actualizarTotal(tfSubTotalOtro,"suma"));
						vaciarFormulario();
					}
				});
				
				btnAgregarOtro.setOpaque(false);
				btnAgregarOtro.setBounds(642, 435, 36, 35);
				contentPanel.add(btnAgregarOtro);
				
				JButton btnAgregarPizza = new JButton("New button");
				btnAgregarPizza.addMouseListener(new MouseAdapter() 
				{
					@Override
					public void mouseClicked(MouseEvent arg0) 
					{
						Integer subtotal=Integer.parseInt(tfSubTotalPizza.getText())* Integer.parseInt(tfUnidadPizza.getText());
						model.addRow(new String[] {tfAgregarPizza.getText(),""+tfUnidadPizza.getText(),""+subtotal});
						tablaItems.setModel(model);
						tfTotal.setText(actualizarTotal(tfSubTotalPizza, "suma"));
						vaciarFormulario();
					}
				});
				btnAgregarPizza.setOpaque(false);
				btnAgregarPizza.setBounds(642, 300, 36, 35);
				contentPanel.add(btnAgregarPizza);
				
				TextAutoCompleter AutoCompletar = new TextAutoCompleter(tfAgregarEmpanada);
				AutoCompletar.setCaseSensitive(false); //No sensible a mayúsculas
				AutoCompletar.addItems(control.getProducto().buscaNombresProductos("empanada"));
				
				TextAutoCompleter autoCompletar2=new TextAutoCompleter(tfAgregarPizza);
				autoCompletar2.setCaseSensitive(false);
				autoCompletar2.addItems(control.getProducto().buscaNombresProductos("pizza"));
				
				TextAutoCompleter autoCompletar3=new TextAutoCompleter(tfAgregarOtroProducto);
				autoCompletar3.setCaseSensitive(false);
				autoCompletar3.addItems(control.getProducto().buscaNombresProductos("otros"));
				
				btnVerPromociones= new JButton("");
				btnVerPromociones.setBounds(29, 362, 89, 23);
				contentPanel.add(btnVerPromociones);
				
				btnVerPizzas= new JButton("");
				btnVerPizzas.setBounds(44, 224, 48, 23);
				contentPanel.add(btnVerPizzas);
				
				btnVerEmpanadas= new JButton("");
				btnVerEmpanadas.setBounds(32, 82, 48, 23);
				contentPanel.add(btnVerEmpanadas);
				
				btnVerOtros = new JButton("");
				btnVerOtros.setBounds(37, 362, 68, 23);
				contentPanel.add(btnVerOtros);
		}


	public JTable getTablaItems()
	{
		return tablaItems;
	}

	public DefaultTableModel getModel()
	{
		return model;
	}

	public JButton getBtnOrdenar()
	{
		return btnOrdenar;
	}
	
	public JButton getBtnOrdenar1() 
	{
		return btnOrdenar1;
	}

	public DefaultTableModel crearModelo()
	{ 
		 DefaultTableModel modelo= new DefaultTableModel();
			modelo.addColumn("Producto");
			modelo.addColumn("Cantidad");
			modelo.addColumn("Precio");
			modelo.addColumn("Comentario");
		return modelo;
	}

	public void llenarTabla(PedidoDTO pedido)
	{
		Iterator<ItemDTO> Iterador = pedido.getProductos().iterator();
		while(Iterador.hasNext())
		{
			ItemDTO elemento = Iterador.next();
			model.addRow(new String[] {elemento.getProducto().getNombre(),""+elemento.getCantidad(),""+(elemento.getProducto().getPrecio()* elemento.getCantidad())});
		}
		tablaItems.setModel(model);
	}

	public JTextField getTfTotal() 
	{
		return tfTotal;
	}
	
	
	public JTextField getTfAgregarEmpanada() {
		return tfAgregarEmpanada;
	}


	public void setTfAgregarEmpanada(JTextField tfAgregarEmpanada) {
		this.tfAgregarEmpanada = tfAgregarEmpanada;
	}


	public JTextField getTfAgregarPizza() {
		return tfAgregarPizza;
	}


	public void setTfAgregarPizza(JTextField tfAgregarPizza) {
		this.tfAgregarPizza = tfAgregarPizza;
	}


	public JTextField getTfAgregarOtroProducto() {
		return tfAgregarOtroProducto;
	}


	public void setTfAgregarOtroProducto(JTextField tfAgregarOtroProducto) {
		this.tfAgregarOtroProducto = tfAgregarOtroProducto;
	}


	public JTextField getTfPrecioUniEmpanada() {
		return tfPrecioUniEmpanada;
	}


	public void setTfPrecioUniEmpanada(JTextField tfPrecioUniEmpanada) {
		this.tfPrecioUniEmpanada = tfPrecioUniEmpanada;
	}


	public JTextField getTfUnidadEmpanada() {
		return tfUnidadEmpanada;
	}


	public void setTfUnidadEmpanada(JTextField tfUnidadEmpanada) {
		this.tfUnidadEmpanada = tfUnidadEmpanada;
	}


	public JTextField getTfSubTotalEmpanada() {
		return tfSubTotalEmpanada;
	}


	public void setTfSubTotalEmpanada(JTextField tfSubTotalEmpanada) {
		this.tfSubTotalEmpanada = tfSubTotalEmpanada;
	}


	public JTextField getTfPrecioUniPizza() {
		return tfPrecioUniPizza;
	}


	public void setTfPrecioUniPizza(JTextField tfPrecioUniPizza) {
		this.tfPrecioUniPizza = tfPrecioUniPizza;
	}


	public JTextField getTfUnidadPizza() {
		return tfUnidadPizza;
	}


	public void setTfUnidadPizza(JTextField tfUnidadPizza) {
		this.tfUnidadPizza = tfUnidadPizza;
	}


	public JTextField getTfSubTotalPizza() {
		return tfSubTotalPizza;
	}


	public void setTfSubTotalPizza(JTextField tfSubTotalPizza) {
		this.tfSubTotalPizza = tfSubTotalPizza;
	}


	public JTextField getTfPrecioUniOtro() {
		return tfPrecioUniOtro;
	}


	public void setTfPrecioUniOtro(JTextField tfPrecioUniOtro) {
		this.tfPrecioUniOtro = tfPrecioUniOtro;
	}


	public JTextField getTfUnidadOtro() {
		return tfUnidadOtro;
	}


	public void setTfUnidadOtro(JTextField tfUnidadOtro) {
		this.tfUnidadOtro = tfUnidadOtro;
	}


	public JTextField getTfSubTotalOtro() {
		return tfSubTotalOtro;
	}
	
	
	public PedidoDTO getPedidoCambiar() {
		return pedidoCambiar;
	}


	public void setTfSubTotalOtro(JTextField tfSubTotalOtro) {
		this.tfSubTotalOtro = tfSubTotalOtro;
	}


	public void setTfTotal(JTextField tfTotal) {
		this.tfTotal = tfTotal;
	}

	public JButton getBtnVerOtros()	{
		return btnVerOtros;
	}


	public void setBtnVerOtros(JButton btnVerOtros) {
		this.btnVerOtros = btnVerOtros;
	}


	public JButton getBtnVerEmpanadas() {
		return btnVerEmpanadas;
	}


	public void setBtnVerEmpanadas(JButton btnVerEmpanadas) {
		this.btnVerEmpanadas = btnVerEmpanadas;
	}


	public JButton getBtnVerPizzas() {
		return btnVerPizzas;
	}


	public void setBtnVerPizzas(JButton btnVerPizzas) {
		this.btnVerPizzas = btnVerPizzas;
	}

	public JCheckBox getCheckBoxDelivery() {
		return checkBoxDelivery;
	}


	public JButton getBtnVerPromociones() {
		return btnVerPromociones;
	}


	public void setBtnVerPromociones(JButton btnVerPromociones) {
		this.btnVerPromociones = btnVerPromociones;
	}


	public void validarTexto(KeyEvent evt, JTextField a)
	{
		char car = evt.getKeyChar();
		if(a.getText().length()>=20) evt.consume();
		if(!(car<'0' || car>'9')) evt.consume();
	}
	
	public void validarNumeros(KeyEvent evt, JTextField a)
	{
		char car = evt.getKeyChar();
		if((car<'0' || car>'9')) evt.consume();
	}
	
	private String actualizarTotal(JTextField tf, String operacion)
	{
		Integer total=0;
		if (operacion=="suma")
			if(tfTotal.getText().equals(""))
				total=0+Integer.parseInt(tf.getText());
			else
			total=Integer.parseInt(tfTotal.getText())+Integer.parseInt(tf.getText());
		else
			total=Integer.parseInt(tfTotal.getText())-Integer.parseInt(tf.getText());
	
		return total.toString();
	}
	
	private void vaciarFormulario() 
	{
		tfAgregarEmpanada.setText("");
		tfPrecioUniEmpanada.setText("");
		tfSubTotalEmpanada.setText("");
		tfUnidadEmpanada.setText("");
		tfAgregarPizza.setText("");
		tfPrecioUniPizza.setText("");
		tfSubTotalPizza.setText("");
		tfUnidadPizza.setText("");
		tfAgregarOtroProducto.setText("");
		tfPrecioUniOtro.setText("");
		tfSubTotalOtro.setText("");
		tfUnidadOtro.setText("");
	}
	
	public ArrayList<ItemDTO> generarListaItem() 
	{
		ArrayList<ItemDTO> listaAux= new ArrayList<ItemDTO>();
		Integer empieza=control.getItem().obtenerItems().size()+1;
		System.out.println(this.control);
		for(int i=0; i<this.tablaItems.getRowCount(); i++)
		{
			ItemDTO aux=new ItemDTO(empieza+i,control.getProducto().buscarProductoPorNombre(model.getValueAt(i, 0).toString()), Integer.parseInt((String)model.getValueAt(i, 1)), (String)model.getValueAt(i, 3));
			listaAux.add(aux);
			control.getItem().agregarItem(aux);
		}
		return listaAux;
	}
	
	public boolean isCellEditable(int rowIndex, int colIndex)
	{
		if (colIndex==1) 
		{
            return true;  //La columna 4 es editable.
        }
        return false;  //El resto de celdas no son editables.
	}
	
	public void actualizarPrecio()
	{
		producto=control.getProducto().buscarProductoPorNombre(model.getValueAt(tablaItems.getSelectedRow(), 0).toString());
		int cantidad=Integer.parseInt(model.getValueAt(tablaItems.getSelectedRow(), 1).toString());
		model.setValueAt(cantidad*producto.getPrecio(), tablaItems.getSelectedRow(), 2);
		
		Integer total=0;
		for(int a=0; a<tablaItems.getRowCount(); a++) //recorro las columnas
		   {
		    total=total+ Integer.parseInt(model.getValueAt(a ,2).toString()); 
		   }
		tfTotal.setText(total.toString());
		   
	}
}
