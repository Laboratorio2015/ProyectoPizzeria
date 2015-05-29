package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import Monitor.Cocina.PadreMonitor;
import modelo.Clientes;
import modelo.Items;
import modelo.Pedidos;
import modelo.Productos;
import modelo.Proveedores;
import dto.ClienteDTO;
import dto.ItemDTO;
import dto.PedidoDTO;
import dto.ProductoDTO;
import dto.ProveedorDTO;
import presentacion.vista.VentanaPrincipal;
import presentacion.vista.opcionesDeConfiguracion;
import presentacion.vista.ordenDePedido;
import presentacion.vista.ordenarMatPrima;
import presentacion.vista.pedidoMenu;
import presentacion.vista.pedidosPendientes;
import presentacion.vista.productoAlta;
import presentacion.vista.productoBajaModificacion;
import presentacion.vista.proveedorAlta;
import presentacion.vista.proveedorBajaModificacion;
import presentacion.vista.registrarCobroDePedido;
import presentacion.vista.registrarCobroManualmente;
import presentacion.vista.registroDeCliente;
import presentacion.vista.repartidorAlta;
import presentacion.vista.seleccionDeCliente;
import presentacion.vista.seleccionarRepartidor;

public class Controlador implements ActionListener
{
	//ventanas
	private VentanaPrincipal ventana;
	private ordenDePedido ventanaPedido;
	private pedidosPendientes ventanaPedPendiente;
	private seleccionDeCliente ventanaCliente;
	private ordenarMatPrima ventanaMatPrima;
	private opcionesDeConfiguracion ventanaConfiguraciones;
	private productoAlta ventanaAgregarProducto;
	private productoBajaModificacion ventanaEditarProducto;
	private proveedorAlta ventanaAgregarProveedor;
	private proveedorBajaModificacion ventanaEditarProveedor;
	private repartidorAlta ventanaAgregarRepartidor;
	private registrarCobroDePedido ventanaRegCobroPedido;
	private registrarCobroManualmente ventanaRegCobroManual;
	private seleccionarRepartidor ventanaSeleccionRepartidor;
	private registroDeCliente ventanaRegCliente;
	private PadreMonitor monitorCocina;
	private pedidoMenu menu;
	
	//dto
	private List<PedidoDTO> listaPedidos;
	private List<ProductoDTO> listaProductos;
	private List<ClienteDTO> listaClientes;
	private List<ItemDTO> listaItems;
	
	//modelo
	private Productos producto;
	private Proveedores proveedor;
	private Pedidos pedido;
	private Clientes cliente;
	private Items item;
	
	
	public Controlador(VentanaPrincipal ventana, Pedidos pedido, Clientes cliente,Productos producto, Items item, Proveedores proveedor) 
	{
		this.ventana=ventana;
		this.pedido=pedido;
		this.cliente=cliente;
		this.item=item;
		this.producto=producto;
		this.proveedor=proveedor;
		
		this.listaClientes=this.cliente.obtenerClientes();
		this.listaProductos=this.producto.obtenerProducto();
		this.listaItems=this.item.obtenerItems();
		this.listaPedidos=this.pedido.obtenerPedidos();
		
		this.ventana.getBtnIngresarPedido().addActionListener(this);
		this.ventana.getBtnPedidosPendientes().addActionListener(this);
		this.ventana.getBtnConfiguraciones().addActionListener(this);
		this.ventana.getBtnPedMatPrima().addActionListener(this);
		this.ventana.getBtnReportes().addActionListener(this);
		
	}
	
	public void inicializar()
	{
		this.monitorCocina=new PadreMonitor(pedido);
		this.ventana.show();
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		if(e.getSource() == this.ventana.getBtnIngresarPedido())
		{
			ventanaPedido=new ordenDePedido(ventana,this);
			this.ventanaPedido.getBtnOrdenar().addActionListener(this);
			this.ventanaPedido.getBtnVerEmpanadas().addActionListener(this);
			this.ventanaPedido.getBtnVerOtros().addActionListener(this);
			this.ventanaPedido.getBtnVerPizzas().addActionListener(this);
			this.ventanaPedido.getBtnVerPromociones().addActionListener(this);
			this.ventanaPedido.setVisible(true);
		}
		else if(e.getSource() == this.ventana.getBtnPedidosPendientes())
		{
			ventanaPedPendiente=new pedidosPendientes(ventana, this);
			ventanaPedPendiente.llenarTabla();
			this.ventanaPedPendiente.setVisible(true);
		}
		else if(e.getSource()== this.ventana.getBtnPedMatPrima())
		{
			ventanaMatPrima= new ordenarMatPrima(ventana, this);
			ventanaMatPrima.setVisible(true);
		}
		else if(e.getSource()==this.ventana.getBtnConfiguraciones())
		{
			ventanaConfiguraciones=new opcionesDeConfiguracion();
			ventanaConfiguraciones.setVisible(true);
			ventanaConfiguraciones.getBtnAgregarOferta().addActionListener(this);
			ventanaConfiguraciones.getBtnAgregarProducto().addActionListener(this);
			ventanaConfiguraciones.getBtnAgregarProveedor().addActionListener(this);
			ventanaConfiguraciones.getBtnAgregarRepartidor().addActionListener(this);
			ventanaConfiguraciones.getBtnEditarProducto().addActionListener(this);
			ventanaConfiguraciones.getBtnEditarProveedor().addActionListener(this);
			ventanaConfiguraciones.getBtnEditarRepartidor().addActionListener(this);
		}
		else if(this.ventanaPedido!= null && e.getSource()==this.ventanaPedido.getBtnOrdenar())
		{
			PedidoDTO nuevoPedido=new PedidoDTO();
			nuevoPedido.setIdpedido(this.getListaPedidos().size()+1);
			nuevoPedido.set_estado("solicitado");
			nuevoPedido.setTotal(Integer.parseInt(this.ventanaPedido.getTfTotal().getText()));
			if(ventanaPedido.getCheckBoxDelivery().isSelected())
				nuevoPedido.setLlevaDelivery(true);
			else
				nuevoPedido.setLlevaDelivery(false);
			nuevoPedido.set_comanda(nuevoPedido.getIdpedido());
			nuevoPedido.set_ticket(nuevoPedido.getIdpedido());
			nuevoPedido.setProductos(generarListaItems());
			this.ventanaCliente=new seleccionDeCliente(this,nuevoPedido);
			this.ventanaCliente.setVisible(true);
			vaciarFormulario();
			this.ventanaPedido.dispose();
		}
		else if (this.ventanaPedido!= null && e.getSource()==this.ventanaPedido.getBtnVerEmpanadas())
		{
			menu=new pedidoMenu();
			llenarTablaMenu("empanada");
			menu.setVisible(true);
		}
		else if (this.ventanaPedido!= null && e.getSource()==this.ventanaPedido.getBtnVerPizzas())
		{
			menu=new pedidoMenu();
			llenarTablaMenu("pizza");
			menu.setVisible(true);
		}
		else if (this.ventanaPedido!= null && e.getSource()==this.ventanaPedido.getBtnVerOtros())
		{
			menu=new pedidoMenu();
			llenarTablaMenu("otros");
			menu.setVisible(true);
		}
		else if (this.ventanaPedido!= null && e.getSource()==this.ventanaPedido.getBtnVerPromociones())
		{
			menu=new pedidoMenu();
			llenarTablaMenu("promocion");
			menu.setVisible(true);
		}
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnAgregarOferta())
		{
			//nose que hace
		}
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnAgregarProducto())
		{
			ventanaAgregarProducto=new productoAlta();
			ventanaAgregarProducto.setVisible(true);
		}
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnAgregarProveedor())
		{
			ventanaAgregarProveedor=new proveedorAlta();
			ventanaAgregarProveedor.setVisible(true);
		}
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnAgregarRepartidor())
		{
			ventanaAgregarRepartidor=new repartidorAlta();
			ventanaAgregarRepartidor.setVisible(true);
		}
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnEditarProducto())
		{
			ventanaEditarProducto=new productoBajaModificacion();
			ventanaEditarProducto.setVisible(true);
		}
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnEditarProveedor())
		{
			ventanaEditarProveedor=new proveedorBajaModificacion(this);
			llenarTablaProveedor();
			ventanaEditarProveedor.setVisible(true);
		}
	}
	

	public List<PedidoDTO> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(List<PedidoDTO> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	public List<ProductoDTO> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<ProductoDTO> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public List<ClienteDTO> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<ClienteDTO> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<ItemDTO> getListaItems() {
		return listaItems;
	}

	public void setListaItems(List<ItemDTO> listaItems) {
		this.listaItems = listaItems;
	}

	public Productos getProducto() {
		return producto;
	}

	public void setProducto(Productos producto) {
		this.producto = producto;
	}

	public Pedidos getPedido() {
		return pedido;
	}

	public void setPedido(Pedidos pedido) {
		this.pedido = pedido;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public Items getItem() {
		return item;
	}

	public void setItem(Items item) {
		this.item = item;
	}

	public PadreMonitor getMonitorCocina()
	{
		return monitorCocina;
	}

	public void setMonitorCocina(PadreMonitor monitorCocina)
	{
		this.monitorCocina = monitorCocina;
	}
	
	public Proveedores getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedores proveedor) {
		this.proveedor = proveedor;
	}

	public ordenDePedido getVentanaPedido() {
		return ventanaPedido;
	}

	public void setVentanaPedido(ordenDePedido ventanaPedido) {
		this.ventanaPedido = ventanaPedido;
	}
	
	public ArrayList<ItemDTO> generarListaItems() 
	{
		ArrayList<ItemDTO> listaAux= new ArrayList<ItemDTO>();
		for(int i=0; i<this.ventanaPedido.getTablaItems().getRowCount(); i++)
		{
			int a=1;
			boolean insertar=false;
			while(!insertar)
			{
				System.out.println((String)this.ventanaPedido.getModel().getValueAt(i, 3));
			ItemDTO aux=new ItemDTO(this.getListaItems().size()+a+i,this.getProducto().buscarProductoPorNombre(this.ventanaPedido.getModel().getValueAt(i, 0).toString()), Integer.parseInt((String)this.ventanaPedido.getModel().getValueAt(i, 1)), (String)this.ventanaPedido.getModel().getValueAt(i, 3));
			if(item.agregarItem(aux))
				{
				listaAux.add(aux);
				insertar=true;
				}
			else
				a++;
			}
		}
		return listaAux;
	}
	
	private void vaciarFormulario() 
	{
		this.ventanaPedido.getTfAgregarEmpanada().setText("");
		this.ventanaPedido.getTfPrecioUniEmpanada().setText("");
		this.ventanaPedido.getTfSubTotalEmpanada().setText("");
		this.ventanaPedido.getTfUnidadEmpanada().setText("");
		this.ventanaPedido.getTfAgregarPizza().setText("");
		this.ventanaPedido.getTfPrecioUniPizza().setText("");
		this.ventanaPedido.getTfSubTotalPizza().setText("");
		this.ventanaPedido.getTfUnidadPizza().setText("");
		this.ventanaPedido.getTfAgregarOtroProducto().setText("");
		this.ventanaPedido.getTfPrecioUniOtro().setText("");
		this.ventanaPedido.getTfSubTotalOtro().setText("");
		this.ventanaPedido.getTfUnidadOtro().setText("");
	}
	
	private void llenarTablaMenu(String tipo) 
	{
		this.menu.getModel().setRowCount(0); //Para vaciar la tabla
		this.menu.getModel().setColumnCount(0);
		this.menu.getModel().setColumnIdentifiers(this.menu.getNombreColumnas());
		
	 	Iterator<ProductoDTO> Iterador = this.producto.obtenerProducto().iterator();
		while(Iterador.hasNext())
		{
			ProductoDTO elemento = Iterador.next();
			if(elemento.getTipo().compareTo(tipo)==0)
			{
				Object[] fila = {elemento.getNombre(), elemento.getPrecio()};
				this.menu.getModel().addRow(fila);			
			}
		}
	}
	private void llenarTablaProveedor()
	{
		this.ventanaEditarProveedor.getModel().setRowCount(0);
		this.ventanaEditarProveedor.getModel().setColumnCount(0);
		this.ventanaEditarProveedor.getModel().setColumnIdentifiers(this.ventanaEditarProveedor.getNombreColumnasProveedor());
		//System.out.println(proveedor.obtenerProveedor().toString());
		Iterator<ProveedorDTO> Iterador = this.proveedor.obtenerProveedor().iterator();
		while(Iterador.hasNext())
		{
			ProveedorDTO elemento = Iterador.next();
			Object[] fila = {elemento.getNombre()};
			this.ventanaEditarProveedor.getModel().addRow(fila);			
		}
	}
}
