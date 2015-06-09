package presentacion.vista;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
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
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import java.awt.Font;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ordenDePedido extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private VentanaPrincipal _padre;
	private ordenDePedido _this;
	private ProductoDTO producto;
	private Integer cantidad;
	private Integer precio;
	private JTable tablaItems;
	private DefaultTableModel model;
	private JScrollPane scrollPane_1;
	private Controlador control;
	private PedidoDTO pedidoCambiar;
	private pedidoMenu ventanamenu;
	
	//check box
	private JCheckBox checkBoxDelivery;
	private JCheckBox checkBoxRepartidor;
	
	//botones
	private JButton btnOrdenar ;
	private JButton btnOrdenar1;
	private JButton btnVerOtros;
	private JButton btnVerEmpanadas;
	private JButton btnVerPizzas ;
	private JButton btnVerPromociones;
	private JButton btnAgregarComentario;
	private JButton btnEditar;
	private JButton btnEditarPreOrden;
	private JButton btnAgregarComentarioPre;
	
	
	//text field
	private JTextField tfUnidadPromocion;
	private JTextField tfBusquedaPromocion;
	private JTextField tfSubTotalPromocion;
	private JTextField tfPrecioUniPromocion;
	private JTextField tfPrecioUniPromo;
	private JTextField tfSubTotalPromo;
	private JTextField tfAgregarPromo;
	private JTextField tfUnidadPromo;
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
	
	

	public ordenDePedido(VentanaPrincipal padre,final Controlador control) 
	{
		setModal(true);
		padre=_padre;
		this.control=control;
		_this=this;
		setMinimumSize(new Dimension(700, 680));
		setBounds(300, 10, 876, 718);
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
		
		tfAgregarEmpanada.setBounds(68, 170, 222, 25);
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
		tfAgregarPizza.setBounds(494, 174, 224, 25);
		contentPanel.add(tfAgregarPizza);

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
			tfAgregarOtroProducto.setColumns(10);
			tfAgregarOtroProducto.setBounds(64, 333, 224, 25);
			contentPanel.add(tfAgregarOtroProducto);
		}
		{
			tfPrecioUniEmpanada = new JTextField();
			tfPrecioUniEmpanada.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
			tfPrecioUniEmpanada.setBackground(new Color(204, 204, 0));
			tfPrecioUniEmpanada.setEditable(false);
			tfPrecioUniEmpanada.setColumns(10);
			tfPrecioUniEmpanada.setBounds(74, 225, 47, 25);
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
				precio=Integer.parseInt(tfPrecioUniEmpanada.getText());
				tfSubTotalEmpanada.setText(Integer.toString(precio*cantidad));
			}
			});
			tfUnidadEmpanada.setColumns(10);
			tfUnidadEmpanada.setBounds(296, 170, 49, 25);
			contentPanel.add(tfUnidadEmpanada);
		}
		{
			tfSubTotalEmpanada = new JTextField();
			tfSubTotalEmpanada.setBackground(new Color(204, 204, 0));
			tfSubTotalEmpanada.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
			tfSubTotalEmpanada.setEditable(false);
			tfSubTotalEmpanada.setColumns(10);
			tfSubTotalEmpanada.setBounds(143, 225, 59, 25);
			contentPanel.add(tfSubTotalEmpanada);
		}
		{
			tfPrecioUniPizza = new JTextField();
			tfPrecioUniPizza.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
			tfPrecioUniPizza.setBackground(new Color(204, 204, 0));
			tfPrecioUniPizza.setEditable(false);
			tfPrecioUniPizza.setColumns(10);
			tfPrecioUniPizza.setBounds(499, 225, 48, 25);
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
					precio=Integer.parseInt(tfPrecioUniPizza.getText());
					tfSubTotalPizza.setText(Integer.toString(precio*cantidad));
				}
			});
			tfUnidadPizza.setColumns(10);
			tfUnidadPizza.setBounds(724, 174, 47, 25);
			contentPanel.add(tfUnidadPizza);
		}
		{
			tfSubTotalPizza = new JTextField();
			tfSubTotalPizza.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
			tfSubTotalPizza.setBackground(new Color(204, 204, 0));
			tfSubTotalPizza.setEditable(false);
			tfSubTotalPizza.setColumns(10);
			tfSubTotalPizza.setBounds(567, 225, 56, 25);
			contentPanel.add(tfSubTotalPizza);
		}
		{
			tfPrecioUniOtro = new JTextField();
			tfPrecioUniOtro.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
			tfPrecioUniOtro.setBackground(new Color(204, 204, 0));
			tfPrecioUniOtro.setEditable(false);
			tfPrecioUniOtro.setColumns(10);
			tfPrecioUniOtro.setBounds(68, 383, 48, 25);
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
					precio=Integer.parseInt(tfPrecioUniOtro.getText());
					tfSubTotalOtro.setText(Integer.toString(precio*cantidad));
				}
			});
			tfUnidadOtro.setColumns(10);
			tfUnidadOtro.setBounds(296, 333, 48, 25);
			contentPanel.add(tfUnidadOtro);
		}
		{
			tfSubTotalOtro = new JTextField();
			tfSubTotalOtro.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
			tfSubTotalOtro.setBackground(new Color(204, 204, 0));
			tfSubTotalOtro.setEditable(false);
			tfSubTotalOtro.setColumns(10);
			tfSubTotalOtro.setBounds(132, 383, 57, 25);
			contentPanel.add(tfSubTotalOtro);
		}
		{
			tfTotal = new JTextField();
			tfTotal.setHorizontalAlignment(SwingConstants.CENTER);
			tfTotal.setForeground(Color.WHITE);
			tfTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
			tfTotal.setEditable(false);
			tfTotal.setBorder(null);
			tfTotal.setBackground(new Color(153, 0, 0));
			tfTotal.setBounds(724, 539, 75, 25);
			contentPanel.add(tfTotal);
			tfTotal.setColumns(10);
		}
		
		this.model=crearModelo();
		tfUnidadPromocion = new JTextField();
		tfUnidadPromocion.setColumns(10);
		tfUnidadPromocion.setBounds(731, 319, 47, 25);
		contentPanel.add(tfUnidadPromocion);
		
		tfBusquedaPromocion = new JTextField();
		tfBusquedaPromocion.setColumns(10);
		tfBusquedaPromocion.setBounds(492, 319, 229, 25);
		contentPanel.add(tfBusquedaPromocion);
		
		tfSubTotalPromocion = new JTextField();
		tfSubTotalPromocion.setEditable(false);
		tfSubTotalPromocion.setColumns(10);
		tfSubTotalPromocion.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		tfSubTotalPromocion.setBackground(new Color(204, 204, 0));
		tfSubTotalPromocion.setBounds(568, 370, 56, 25);
		contentPanel.add(tfSubTotalPromocion);
		
		tfPrecioUniPromocion = new JTextField();
		tfPrecioUniPromocion.setEditable(false);
		tfPrecioUniPromocion.setColumns(10);
		tfPrecioUniPromocion.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		tfPrecioUniPromocion.setBackground(new Color(204, 204, 0));
		tfPrecioUniPromocion.setBounds(502, 370, 48, 25);
		contentPanel.add(tfPrecioUniPromocion);
		
		checkBoxDelivery= new JCheckBox("");
		checkBoxDelivery.setBackground(new Color(204, 204, 0));
		checkBoxDelivery.setBounds(789, 477, 27, 33);
		contentPanel.add(checkBoxDelivery);
		{
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(112, 472, 561, 109);
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
			//accion asociada a hacer un enter sobre la tabla Items
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
			JLabel label = new JLabel("");
			label.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
			label.setIcon(new ImageIcon(ordenDePedido.class.getResource("/prototipos/orden de pedido.png")));
			label.setBounds(0, 0, 860, 680);
			contentPanel.add(label);
		}
		
		{
			btnOrdenar= new JButton("OK");
			btnOrdenar.setOpaque(false);
			btnOrdenar.setBounds(245, 618, 163, 34);
			contentPanel.add(btnOrdenar);
			btnOrdenar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnOrdenar);
			
		{
			btnEditar = new JButton("editar");
			btnEditar.setOpaque(false);
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
			
			btnAgregarComentario = new JButton("agregar coment");
			btnAgregarComentario.setOpaque(false);
			btnAgregarComentario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					tablaItems.editCellAt(tablaItems.getSelectedRow(),3);
				    Component aComp=tablaItems.getEditorComponent();
				    aComp.requestFocus();
					tablaItems.isCellEditable(tablaItems.getSelectedRow(), 3);
				}
			});
			btnAgregarComentario.setBounds(34, 551, 40, 33);
			contentPanel.add(btnAgregarComentario);
			btnEditar.setBounds(42, 470, 27, 29);
			contentPanel.add(btnEditar);
		}
			
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
			btnCancelar.setBounds(460, 620, 163, 34);
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
				btnQuitar.setBounds(42, 512, 28, 30);
				contentPanel.add(btnQuitar);
				
				JButton btnAgregarOtro = new JButton("New button");
				btnAgregarOtro.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{ if(tfSubTotalOtro.getText()!=" ")
					{
						Integer subtotal=Integer.parseInt(tfPrecioUniOtro.getText())* Integer.parseInt(tfUnidadOtro.getText());
						model.addRow(new String[] {tfAgregarOtroProducto.getText(),""+tfUnidadOtro.getText(),""+subtotal});
						tablaItems.setModel(model);
						tfTotal.setText(actualizarTotal(tfSubTotalOtro,"suma"));
						vaciarFormulario();
					}
					else
						JOptionPane.showMessageDialog(null, "Error, presione ENTER para que se genere el subtotal");
					}
				});
				
				
				JButton btnAgregarEmpanada = new JButton("New button");
				btnAgregarEmpanada.setOpaque(false);
				btnAgregarEmpanada.addMouseListener(new MouseAdapter()
				{
					@Override
					public void mouseClicked(MouseEvent arg0) 
					{
						if(tfSubTotalEmpanada.getText()!=" ")
						{
						Integer subtotal=Integer.parseInt(tfPrecioUniEmpanada.getText())* Integer.parseInt(tfUnidadEmpanada.getText());
						model.addRow(new String[] {tfAgregarEmpanada.getText(),""+tfUnidadEmpanada.getText(),""+subtotal});
						tablaItems.setModel(model);
						tfTotal.setText(actualizarTotal(tfSubTotalEmpanada,"suma"));
						vaciarFormulario();	
						}
					else
						JOptionPane.showMessageDialog(null, "Error, presione ENTER para que se genere el subtotal");
					}
				});
				btnAgregarEmpanada.setBounds(372, 150, 36, 35);
				contentPanel.add(btnAgregarEmpanada);
				
				btnAgregarOtro.setOpaque(false);
				btnAgregarOtro.setBounds(364, 312, 36, 35);
				contentPanel.add(btnAgregarOtro);
				
				JButton btnAgregarPizza = new JButton("New button");
				btnAgregarPizza.addMouseListener(new MouseAdapter() 
				{
					@Override
					public void mouseClicked(MouseEvent arg0) 
					{
						if(tfSubTotalPizza.getText()!=" ")
						{
						Integer subtotal=Integer.parseInt(tfPrecioUniPizza.getText())* Integer.parseInt(tfUnidadPizza.getText());
						model.addRow(new String[] {tfAgregarPizza.getText(),""+tfUnidadPizza.getText(),""+subtotal});
						tablaItems.setModel(model);
						tfTotal.setText(actualizarTotal(tfSubTotalPizza,"suma"));
						vaciarFormulario();
						}
						else
							JOptionPane.showMessageDialog(null, "Error, presione ENTER para que se genere el subtotal");
					}
				});
				btnAgregarPizza.setOpaque(false);
				btnAgregarPizza.setBounds(797, 153, 36, 35);
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
				
				JButton btnAgregarPromocion = new JButton("New button");
				btnAgregarPromocion.setOpaque(false);
				btnAgregarPromocion.setBounds(797, 298, 36, 35);
				contentPanel.add(btnAgregarPromocion);
				
				btnVerPromociones= new JButton("");
				btnVerPromociones.setOpaque(false);
				btnVerPromociones.setBounds(442, 321, 47, 23);
				contentPanel.add(btnVerPromociones);
				
				btnVerPizzas= new JButton("");
				btnVerPizzas.setOpaque(false);
				btnVerPizzas.setBounds(441, 172, 48, 23);
				contentPanel.add(btnVerPizzas);
				
				btnVerEmpanadas= new JButton("");
				btnVerEmpanadas.setOpaque(false);
				btnVerEmpanadas.setBounds(17, 170, 44, 23);
				contentPanel.add(btnVerEmpanadas);
				
				btnVerOtros = new JButton("");
				btnVerOtros.setOpaque(false);
				btnVerOtros.setBounds(17, 333, 40, 23);
				contentPanel.add(btnVerOtros);
		}

	/////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////
	////////////se agrega el siguiente constructor para la modificacion de pedidos///////////
	/////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * @wbp.parser.constructor
	 */
	public ordenDePedido(VentanaPrincipal padre,final PedidoDTO pedidoCambiar,final Controlador control) 
	{
		setModal(true);
		padre=_padre;
		this.control=control;
		_this=this;
		setMinimumSize(new Dimension(700, 680));
		setBounds(300, 10, 876, 716);
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
		
		tfAgregarEmpanada.setBounds(68, 170, 222, 25);
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
			tfAgregarPizza.setBounds(494, 174, 224, 25);
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
			tfAgregarOtroProducto.setColumns(10);
			tfAgregarOtroProducto.setBounds(64, 333, 224, 25);
			contentPanel.add(tfAgregarOtroProducto);
		}
		{
			tfPrecioUniEmpanada = new JTextField();
			tfPrecioUniEmpanada.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
			tfPrecioUniEmpanada.setBackground(new Color(204, 204, 0));
			tfPrecioUniEmpanada.setEditable(false);
			tfPrecioUniEmpanada.setColumns(10);
			tfPrecioUniEmpanada.setBounds(74, 225, 50, 22);
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
			tfUnidadEmpanada.setBounds(297, 170, 49, 25);
			contentPanel.add(tfUnidadEmpanada);
		}
		{
			tfSubTotalEmpanada = new JTextField();
			tfSubTotalEmpanada.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
			tfSubTotalEmpanada.setBackground(new Color(204, 204, 0));
			tfSubTotalEmpanada.setEditable(false);
			tfSubTotalEmpanada.setColumns(10);
			tfSubTotalEmpanada.setBounds(138, 225, 57, 23);
			contentPanel.add(tfSubTotalEmpanada);
		}
		{
			tfPrecioUniPizza = new JTextField();
			tfPrecioUniPizza.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
			tfPrecioUniPizza.setBackground(new Color(204, 204, 0));
			tfPrecioUniPizza.setEditable(false);
			tfPrecioUniPizza.setColumns(10);
			tfPrecioUniPizza.setBounds(502, 225, 48, 22);
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
					precio=Integer.parseInt(tfPrecioUniPizza.getText());
					tfSubTotalPizza.setText(Integer.toString(precio*cantidad));
				}
			});
			tfUnidadPizza.setColumns(10);
			tfUnidadPizza.setBounds(724, 174, 47, 25);
			contentPanel.add(tfUnidadPizza);
		}
		{
			tfSubTotalPizza = new JTextField();
			tfSubTotalPizza.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
			tfSubTotalPizza.setBackground(new Color(204, 204, 0));
			tfSubTotalPizza.setEditable(false);
			tfSubTotalPizza.setColumns(10);
			tfSubTotalPizza.setBounds(570, 225, 56, 22);
			contentPanel.add(tfSubTotalPizza);
		}
		{
			tfPrecioUniOtro = new JTextField();
			tfPrecioUniOtro.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
			tfPrecioUniOtro.setBackground(new Color(204, 204, 0));
			tfPrecioUniOtro.setEditable(false);
			tfPrecioUniOtro.setColumns(10);
			tfPrecioUniOtro.setBounds(69, 384, 48, 22);
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
					precio=Integer.parseInt(tfPrecioUniOtro.getText());
					tfSubTotalOtro.setText(Integer.toString(precio*cantidad));
				}
			});
			tfUnidadOtro.setColumns(10);
			tfUnidadOtro.setBounds(296, 333, 48, 25);
			contentPanel.add(tfUnidadOtro);
		}
		{
			tfSubTotalOtro = new JTextField();
			tfSubTotalOtro.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
			tfSubTotalOtro.setBackground(new Color(204, 204, 0));
			tfSubTotalOtro.setEditable(false);
			tfSubTotalOtro.setColumns(10);
			tfSubTotalOtro.setBounds(134, 384, 57, 22);
			contentPanel.add(tfSubTotalOtro);
		}
		{
			tfTotal = new JTextField();
			tfTotal.setEditable(false);
			tfTotal.setBorder(null);
			tfTotal.setBackground(new Color(153, 0, 0));
			tfTotal.setBounds(724, 538, 75, 25);
			contentPanel.add(tfTotal);
			tfTotal.setColumns(10);
		}
		
		model=crearModelo();
		
		tfPrecioUniPromo = new JTextField();
		tfPrecioUniPromo.setEditable(false);
		tfPrecioUniPromo.setColumns(10);
		tfPrecioUniPromo.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		tfPrecioUniPromo.setBackground(new Color(204, 204, 0));
		tfPrecioUniPromo.setBounds(505, 370, 48, 22);
		contentPanel.add(tfPrecioUniPromo);
		
		tfSubTotalPromo = new JTextField();
		tfSubTotalPromo.setEditable(false);
		tfSubTotalPromo.setColumns(10);
		tfSubTotalPromo.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		tfSubTotalPromo.setBackground(new Color(204, 204, 0));
		tfSubTotalPromo.setBounds(572, 370, 57, 22);
		contentPanel.add(tfSubTotalPromo);
		
		tfAgregarPromo = new JTextField();
		tfAgregarPromo.setColumns(10);
		tfAgregarPromo.setBounds(498, 319, 224, 25);
		contentPanel.add(tfAgregarPromo);
		
		tfUnidadPromo = new JTextField();
		tfUnidadPromo.setColumns(10);
		tfUnidadPromo.setBounds(730, 319, 48, 25);
		contentPanel.add(tfUnidadPromo);
		{
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(112, 472, 561, 109);
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
			//accion asociada a hacer un enter sobre la tabla Items
			tablaItems.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) 
				{
					if(e.getKeyCode() == KeyEvent.VK_ENTER)
						actualizarPrecio();
				}
			});
			scrollPane_1.setViewportView(tablaItems);
			
		}
		
		checkBoxRepartidor= new JCheckBox("");
		checkBoxRepartidor.setBackground(new Color(204, 204, 0));
		checkBoxRepartidor.setBounds(789, 474, 30, 33);
		contentPanel.add(checkBoxRepartidor);
		
		{
			JLabel label = new JLabel("");
			label.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
			label.setIcon(new ImageIcon(ordenDePedido.class.getResource("/prototipos/orden de pedido.png")));
			label.setBounds(0, 0, 860, 680);
			contentPanel.add(label);
		}
		
		{
			btnOrdenar1= new JButton("OKi");
			btnOrdenar1.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0)
				{
					Calendar c1 = GregorianCalendar.getInstance();
					String fecha=(c1.getTime().getDate()+"-"+(c1.getTime().getMonth()+1)+"-"+(c1.getTime().getYear()+1900));
					String hora=c1.getTime().getHours()+":"+c1.getTime().getMinutes();
					PedidoDTO nuevoPedido=new PedidoDTO();
					nuevoPedido.setIdpedido(pedidoCambiar.getIdpedido());
					nuevoPedido.set_estado("solicitado");
					nuevoPedido.setTotal(Integer.parseInt(tfTotal.getText()));
					nuevoPedido.set_comanda(nuevoPedido.getIdpedido());
					nuevoPedido.set_ticket(nuevoPedido.getIdpedido());
					nuevoPedido.setProductos(generarListaItem());
					nuevoPedido.setHora(hora);
					nuevoPedido.setFecha(fecha);
					nuevoPedido.setFueeliminado(false);
					nuevoPedido.setCliente(pedidoCambiar.getCliente());
					if(checkBoxRepartidor.isSelected())
						nuevoPedido.setLlevaDelivery(true);
					else
						nuevoPedido.setLlevaDelivery(false);
					control.getPedido().quitarPedido(pedidoCambiar);
					control.getPedido().agregarPedido(nuevoPedido);
					//control.getMonitorCocina().nuevoPedido(nuevoPedido);
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
			btnAgregarEmpanada.setBounds(371, 150, 36, 35);
			contentPanel.add(btnAgregarEmpanada);
			btnOrdenar1.setOpaque(false);
			btnOrdenar1.setBounds(246, 619, 163, 34);
			contentPanel.add(btnOrdenar1);
			btnOrdenar1.setActionCommand("OK");
			getRootPane().setDefaultButton(btnOrdenar1);
		}
		{
			JButton btnCancelar = new JButton("Cancel");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnCancelar.setOpaque(false);
			btnCancelar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) 
				{
					dispose();
				}
			});
			btnCancelar.setBounds(459, 619, 163, 34);
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
				btnQuitar.setBounds(39, 510, 30, 32);
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
				btnAgregarOtro.setBounds(364, 312, 36, 35);
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
				btnAgregarPizza.setBounds(797, 153, 36, 35);
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
				btnVerPromociones.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ventanamenu=new pedidoMenu(control,_this);
						ventanamenu.setTitle("promocion");
						ventanamenu.getBtnSeleccionar().addActionListener(control);
						llenarTablaMenu("promocion");
						ventanamenu.setVisible(true);
					}
				});
				btnVerPromociones.setOpaque(false);
				btnVerPromociones.setBounds(442, 321, 50, 23);
				contentPanel.add(btnVerPromociones);
				
				btnVerPizzas= new JButton("");
				btnVerPizzas.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ventanamenu=new pedidoMenu(control,_this);
						ventanamenu.setTitle("pizza");
						ventanamenu.getBtnSeleccionar().addActionListener(control);
						llenarTablaMenu("pizza");
						ventanamenu.setVisible(true);
					}
				});
				btnVerPizzas.setOpaque(false);
				btnVerPizzas.setBounds(442, 172, 48, 23);
				contentPanel.add(btnVerPizzas);
				
				btnVerEmpanadas= new JButton("");
				btnVerEmpanadas.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ventanamenu=new pedidoMenu(control,_this);
						ventanamenu.setTitle("empanada");
						ventanamenu.getBtnSeleccionar().addActionListener(control);
						llenarTablaMenu("empanada");
						ventanamenu.setVisible(true);
					}
				});
				btnVerEmpanadas.setOpaque(false);
				btnVerEmpanadas.setBounds(18, 170, 46, 23);
				contentPanel.add(btnVerEmpanadas);
				
				btnVerOtros = new JButton("");
				btnVerOtros.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ventanamenu=new pedidoMenu(control,_this);
						ventanamenu.setTitle("otros");
						ventanamenu.getBtnSeleccionar().addActionListener(control);
						llenarTablaMenu("otros");
						ventanamenu.setVisible(true);
					}
				});
				btnVerOtros.setOpaque(false);
				btnVerOtros.setBounds(16, 335, 44, 23);
				contentPanel.add(btnVerOtros);
				
				
				btnEditarPreOrden= new JButton("");
				btnEditarPreOrden.setOpaque(false);
				btnEditarPreOrden.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0)
					{
						tablaItems.editCellAt(tablaItems.getSelectedRow(),1);
					    Component aComp=tablaItems.getEditorComponent();
					    aComp.requestFocus();
						tablaItems.isCellEditable(tablaItems.getSelectedRow(), 1);
					}
				});
				btnEditarPreOrden.setBounds(39, 469, 36, 30);
				contentPanel.add(btnEditarPreOrden);
				
				btnAgregarComentarioPre= new JButton("");
				btnAgregarComentarioPre.setOpaque(false);
				btnAgregarComentarioPre.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) 
					{
						tablaItems.editCellAt(tablaItems.getSelectedRow(),3);
					    Component aComp=tablaItems.getEditorComponent();
					    aComp.requestFocus();
						tablaItems.isCellEditable(tablaItems.getSelectedRow(), 3);
					}
				});
				btnAgregarComentarioPre.setBounds(36, 553, 36, 30);
				contentPanel.add(btnAgregarComentarioPre);
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
			
			model.addRow(new String[] {elemento.getProducto().getNombre(),""+elemento.getCantidad(),""+(elemento.getProducto().getPrecio()* elemento.getCantidad()),elemento.getComentario()});
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
			ItemDTO aux=new ItemDTO(empieza+i,control.getProducto().buscarProductoPorNombre(model.getValueAt(i, 0).toString()), Integer.parseInt((String)model.getValueAt(i, 1)), (String)model.getValueAt(i, 3),false);
			listaAux.add(aux);
			control.getItem().agregarItem(aux);
		}
		return listaAux;
	}
	
	public JTextField getTfUnidadPromocion() {
		return tfUnidadPromocion;
	}


	public void setTfUnidadPromocion(JTextField tfUnidadPromocion) {
		this.tfUnidadPromocion = tfUnidadPromocion;
	}


	public JTextField getTfAgregarPromocion() {
		return tfBusquedaPromocion;
	}


	public void setTfAgregarPromocion(JTextField tfAgregarPromocion) {
		this.tfBusquedaPromocion = tfAgregarPromocion;
	}


	public JTextField getTfSubTotalPromocion() {
		return tfSubTotalPromocion;
	}


	public void setTfSubTotalPromocion(JTextField tfSubTotalPromocion) {
		this.tfSubTotalPromocion = tfSubTotalPromocion;
	}


	public JTextField getTfPrecioUniPromocion() {
		return tfPrecioUniPromocion;
	}


	public void setTfPrecioUniPromocion(JTextField tfPrecioUniPromocion) {
		this.tfPrecioUniPromocion = tfPrecioUniPromocion;
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
	private void llenarTablaMenu(String tipo) 
	{
		this.ventanamenu.getModel().setRowCount(0); //Para vaciar la tabla
		this.ventanamenu.getModel().setColumnCount(0);
		this.ventanamenu.getModel().setColumnIdentifiers(ventanamenu.getNombreColumnas());
		
	 	Iterator<ProductoDTO> Iterador = control.getProducto().obtenerProducto().iterator();
		while(Iterador.hasNext())
		{
			ProductoDTO elemento = Iterador.next();
			if(elemento.getTipo().compareTo(tipo)==0)
			{
				Object[] fila = {elemento.getNombre(), elemento.getPrecio()};
				ventanamenu.getModel().addRow(fila);			
			}
		}
	}
}
