package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import Cocina.PadreMonitor;
import modelo.Categorias;
import modelo.Clientes;
import modelo.Items;
import modelo.MatPrimas;
import modelo.Ofertas;
import modelo.OrdenesMateriaPrimas;
import modelo.Pedidos;
import modelo.Productos;
import modelo.Proveedores;
import modelo.Repartidores;
import dto.ClienteDTO;
import dto.ItemDTO;
import dto.ItemMateriaPrimaDTO;
import dto.MateriaPrimaDTO;
import dto.OfertaDTO;
import dto.OrdenPedidoMatPrimaDTO;
import dto.PedidoDTO;
import dto.ProductoDTO;
import dto.ProveedorDTO;
import dto.RepartidorDTO;
import presentacion.vista.VentanaPrincipal;
import presentacion.vista.calendario;
import presentacion.vista.clienteBajaModificacion;
import presentacion.vista.gestionCategoria;
import presentacion.vista.matPrimaAlta;
import presentacion.vista.matPrimaBajaModificacion;
import presentacion.vista.opcionesDeConfiguracion;
import presentacion.vista.ordenDePedido;
import presentacion.vista.ordenarMatPrima;
import presentacion.vista.pedidoMenu;
import presentacion.vista.pedidosPendientes;
import presentacion.vista.productoAlta;
import presentacion.vista.productoBajaModificacion;
import presentacion.vista.promocionAlta;
import presentacion.vista.promocionBajaModificacion;
import presentacion.vista.proveedorAlta;
import presentacion.vista.proveedorBajaModificacion;
import presentacion.vista.registrarCobroDePedido;
import presentacion.vista.registrarCobroManualmente;
import presentacion.vista.registroDeCliente;
import presentacion.vista.repartidorAlta;
import presentacion.vista.repartidorBajaModificacion;
import presentacion.vista.seleccionDeCliente;
import presentacion.vista.seleccionarRepartidor;
import presentacion.reportes.solicitudDeMateriaPrima;

public class Controlador implements ActionListener
{
	//ventanas
	private VentanaPrincipal ventana;
	private ordenDePedido ventanaPedido;
	private pedidosPendientes ventanaPedPendiente;
	private seleccionDeCliente ventanaCliente;
	private ordenarMatPrima ventanaOrdenMatPrima;
	private opcionesDeConfiguracion ventanaConfiguraciones;
	private productoAlta ventanaAgregarProducto;
	private productoBajaModificacion ventanaEditarProducto;
	private proveedorAlta ventanaAgregarProveedor;
	private proveedorBajaModificacion ventanaEditarProveedor;
	private repartidorAlta ventanaAgregarRepartidor;
	private repartidorBajaModificacion ventanaEditarRepartidor;
	private matPrimaAlta ventanaAgregarMatPrima;
	private matPrimaBajaModificacion ventanaEditarMatPrima;
	private promocionAlta ventanaAgregarPromocion;
	private promocionBajaModificacion ventanaEditarPromocion;
	private gestionCategoria ventanaGestionCategoria;
	private clienteBajaModificacion ventanaModificacionCliente;
	private calendario ventanaCalendario;
	private registrarCobroDePedido ventanaRegCobroPedido;
	private registrarCobroManualmente ventanaRegCobroManual;
	private seleccionarRepartidor ventanaSeleccionRepartidor;
	private PadreMonitor monitorCocina;
	private pedidoMenu ventanamenu;
	private registroDeCliente ventanaRegistrarCliente;
	
	//modelo
	private Productos producto;
	private Proveedores proveedor;
	private Pedidos pedido;
	private Clientes cliente;
	private Items item;
	private Repartidores repartidor;
	private Ofertas oferta;
	private Categorias categoria;
	private OrdenesMateriaPrimas ordenesMatPrimas;
	private MatPrimas materiasPrimas;
	
	
	public Controlador(VentanaPrincipal ventana, Pedidos pedido, Clientes cliente,Productos producto, Items item, 
			Proveedores proveedor, Repartidores repartidor,Ofertas oferta, Categorias categoria) 
	{
		this.ventana=ventana;
		this.pedido=pedido;
		this.cliente=cliente;
		this.item=item;
		this.producto=producto;
		this.proveedor=proveedor;
		this.repartidor=repartidor;
		this.oferta=oferta;
		this.categoria=categoria;
		this.ventana.getBtnIngresarPedido().addActionListener(this);
		this.ventana.getBtnPedidosPendientes().addActionListener(this);
		this.ventana.getBtnConfiguraciones().addActionListener(this);
		this.ventana.getBtnPedMatPrima().addActionListener(this);
		this.ventana.getBtnReportes().addActionListener(this);
		
	}
	//ESTE CONSTRUCTOR RECIBE DOS PARAMETROS MAS QUE EL OTRO> ORDENES DE PEDIDO Y MATERIAS PRIMAS
	public Controlador(VentanaPrincipal ventana, Pedidos pedido, Clientes cliente,Productos producto, Items item, Proveedores proveedor,
			Repartidores repartidor,Ofertas oferta, Categorias categoria,OrdenesMateriaPrimas ordenesMatPrimas, MatPrimas matPrimas) 
	{
		this.ventana=ventana;
		this.pedido=pedido;
		this.cliente=cliente;
		this.item=item;
		this.producto=producto;
		this.proveedor=proveedor;
		this.repartidor=repartidor;
		this.oferta=oferta;
		this.categoria=categoria;
		this.ordenesMatPrimas = ordenesMatPrimas;
		this.materiasPrimas = matPrimas;
		this.ventana.getBtnIngresarPedido().addActionListener(this);
		this.ventana.getBtnPedidosPendientes().addActionListener(this);
		this.ventana.getBtnConfiguraciones().addActionListener(this);
		this.ventana.getBtnPedMatPrima().addActionListener(this);
		this.ventana.getBtnReportes().addActionListener(this);
		
	}
	
	
	
	
	public void inicializar()
	{
		//this.monitorCocina=new PadreMonitor(pedido);
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
		
		///FIN CONTROL ORDENAR MAT PRIMA
		else if(e.getSource()== this.ventana.getBtnPedMatPrima())
		{
			ventanaOrdenMatPrima= new ordenarMatPrima(ventana, this);
			ventanaOrdenMatPrima.setVisible(true);
			
			this.ventanaOrdenMatPrima.getButtonAgregarMateriaPrima().addActionListener(this);
			this.ventanaOrdenMatPrima.getButtonQuitarMatPrima().addActionListener(this);
			this.ventanaOrdenMatPrima.getButtonLimpiarBuscador().addActionListener(this);
			this.ventanaOrdenMatPrima.getBtnVermatprima().addActionListener(this);
			this.ventanaOrdenMatPrima.getBtnCancelar().addActionListener(this);
			this.ventanaOrdenMatPrima.getBtnEnviarform().addActionListener(this);
			this.ventanaOrdenMatPrima.getBtnGuardarform().addActionListener(this);
			this.ventanaOrdenMatPrima.getBtnVermatprima().addActionListener(this);
			this.ventanaOrdenMatPrima.getBtnVerproveedores().addActionListener(this);
			this.ventanaOrdenMatPrima.getComboListaCategorias().addActionListener(this);
			this.ventanaOrdenMatPrima.getComboListaProveedores().addActionListener(this);
		
			//CARGA PROVEEDORES, CATEGORIA Y MAT PRIMA FILTRADA
			cargarProveedores();
			try {
				getProveedorSeleccionado();
			} catch (Exception e1) {
				//exepcion> "El proveedor recibido en nulo O no existe en el listado de proveedores." 
				e1.printStackTrace();
			}
			cargarCategorias();
			try {
				generarArrayMatPrimaProveed();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			try {
				generarArrayMatPrimaProveed();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			prepararMatPrimaParaBusqXCategoria();
			//FIN CARGA PROVEEDORES, CATEGORIA Y MAT PRIMA FILTRADA
		
		}
		else if(this.ventanaOrdenMatPrima!= null && e.getSource()==this.ventanaOrdenMatPrima.getButtonAgregarMateriaPrima())
		//accion para Agregar item Mat Prima
		{
			if (ventanaOrdenMatPrima.getTextFieldCantMatPrima().getText().toString().compareTo("")!=0 && ventanaOrdenMatPrima.getTextFieldBuscadorMatPrima().getText().toString().compareTo("")!=0){
				if (ventanaOrdenMatPrima.getProvBloqueado() == false){
					ventanaOrdenMatPrima.getComboListaProveedores().setEnabled(false);
					ventanaOrdenMatPrima.setProvBloqueado(true);
				}
				ventanaOrdenMatPrima.setItemSolicitado(new ItemMateriaPrimaDTO(getMatPrimaSeleccionada(ventanaOrdenMatPrima.getTextFieldBuscadorMatPrima().getText().toString()),
						Integer.parseInt(ventanaOrdenMatPrima.getTextFieldCantMatPrima().getText().toString()))); 
				
				agregarItemTabla(ventanaOrdenMatPrima.getItemSolicitado());
				ventanaOrdenMatPrima.getTextFieldCantMatPrima().setText("");
				ventanaOrdenMatPrima.getTextFieldBuscadorMatPrima().setText("");
				ventanaOrdenMatPrima.getButtonAgregarMateriaPrima().setEnabled(false);
			}
			else{
				ventanaOrdenMatPrima.getButtonAgregarMateriaPrima().setEnabled(false);
			}
		}
		else if(this.ventanaOrdenMatPrima!= null && e.getSource()==this.ventanaOrdenMatPrima.getButtonQuitarMatPrima())
		//accion para BORRAR ITEM AGREGADO
		{
			if (ventanaOrdenMatPrima.getTablaItemsMateriaPrima().getSelectedRow() >= 0){
				ventanaOrdenMatPrima.getModeloItemsSolicitados().removeRow(ventanaOrdenMatPrima.getTablaItemsMateriaPrima().getSelectedRow());
				ventanaOrdenMatPrima.getTablaItemsMateriaPrima().setModel(ventanaOrdenMatPrima.getModeloItemsSolicitados());
			}
		
		}
		else if(this.ventanaOrdenMatPrima!= null && e.getSource()==this.ventanaOrdenMatPrima.getBtnGuardarform())
		//accion para GENERAR PDF Y GUARDAR ORDEN DE PEDIDO
		{
			ventanaOrdenMatPrima.setListadoItemsOrdenados(generarListadoCompra());
			ArrayList<OrdenPedidoMatPrimaDTO> listadoOrdenesMatPrima = (ArrayList<OrdenPedidoMatPrimaDTO>) this.ordenesMatPrimas.obtenerOrdenPedidoMatPrima();
			int id = listadoOrdenesMatPrima.size();
			ventanaOrdenMatPrima.setNuevaOrden(new OrdenPedidoMatPrimaDTO(id,ventanaOrdenMatPrima.getProvSeleccionado(),
					ventanaOrdenMatPrima.getListadoItemsOrdenados()));
			try {
				generarPDFordenMatPrima();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//borrarFormulario();
			//dispose();
		}
		else if(this.ventanaOrdenMatPrima!= null && e.getSource()==this.ventanaOrdenMatPrima.getComboListaProveedores())
		//accion para CARGAR INFORMACION TRAS CAMBIO DE PROV SELECCIONADO.
		{
			cargarCategorias();
			try {
				generarArrayMatPrimaProveed();
			} catch (Exception e1) {
				System.out.println("Proveedor no hayado");		
				e1.printStackTrace();
			}
			prepararMatPrimaParaBusqXCategoria();
		}
		
		
		///FIN CONTROL ORDENAR MAT PRIMA

		
		else if(e.getSource()==this.ventana.getBtnConfiguraciones())
		{
			ventanaConfiguraciones=new opcionesDeConfiguracion();
			ventanaConfiguraciones.setVisible(true);
			ventanaConfiguraciones.getBtnAgregarProducto().addActionListener(this);
			ventanaConfiguraciones.getBtnAgregarProveedor().addActionListener(this);
			ventanaConfiguraciones.getBtnAgregarRepartidor().addActionListener(this);
			ventanaConfiguraciones.getBtnEditarProducto().addActionListener(this);
			ventanaConfiguraciones.getBtnEditarProveedor().addActionListener(this);
			ventanaConfiguraciones.getBtnEditarRepartidor().addActionListener(this);
			ventanaConfiguraciones.getBtnAgregarMatPrima().addActionListener(this);
			ventanaConfiguraciones.getBtnEditarMatPrima().addActionListener(this);
			ventanaConfiguraciones.getBtnAgregarPromocion().addActionListener(this);
			ventanaConfiguraciones.getBtnEditarPromocion().addActionListener(this);
			ventanaConfiguraciones.getBtnEditarCliente().addActionListener(this);
			ventanaConfiguraciones.getBtnGestionarCategorias().addActionListener(this);
		}
		//al ordenar.... crea un pedido y llama a la ventana para seleccionar un cliente
		else if(this.ventanaPedido!= null && e.getSource()==this.ventanaPedido.getBtnOrdenar())
		{
			Calendar c1 = GregorianCalendar.getInstance();
			String fecha=(c1.getTime().getDate()+"-"+(c1.getTime().getMonth()+1)+"-"+(c1.getTime().getYear()+1900));
			String hora=c1.getTime().getHours()+":"+c1.getTime().getMinutes();
			PedidoDTO nuevoPedido=new PedidoDTO();
			nuevoPedido.setIdpedido(this.pedido.ultimoPedido()+1);
			nuevoPedido.set_estado("solicitado");
			nuevoPedido.setTotal(Integer.parseInt(this.ventanaPedido.getTfTotal().getText()));
			if(ventanaPedido.getCheckBoxDelivery().isSelected())
				nuevoPedido.setLlevaDelivery(true);
			else
				nuevoPedido.setLlevaDelivery(false);
			nuevoPedido.set_comanda(nuevoPedido.getIdpedido());
			nuevoPedido.set_ticket(nuevoPedido.getIdpedido());
			nuevoPedido.setProductos(generarListaItems());
			nuevoPedido.setOfertas(generarListaOfertas());
			nuevoPedido.setFecha(fecha);
			nuevoPedido.setHora(hora);
			nuevoPedido.setFueeliminado(false);
			this.ventanaCliente=new seleccionDeCliente(this,nuevoPedido);
			this.ventanaCliente.getBtnAgregarCliente().addActionListener(this);
			this.ventanaCliente.getBtnEditarCliente().addActionListener(this);
			this.ventanaCliente.setVisible(true);
			vaciarFormulario();
			this.ventanaPedido.dispose();
		}
		else if (this.ventanaPedido!= null && e.getSource()==this.ventanaPedido.getBtnVerEmpanadas())
		{
			ventanamenu=new pedidoMenu(this,ventanaPedido);
			ventanamenu.setTitle("empanada");
			ventanamenu.getBtnSeleccionar().addActionListener(this);
			llenarTablaMenu("empanada");
			ventanamenu.setVisible(true);
			
		}
		else if (this.ventanaPedido!= null && e.getSource()==this.ventanaPedido.getBtnVerPizzas())
		{
			ventanamenu=new pedidoMenu(this,ventanaPedido);
			ventanamenu.setTitle("pizza");
			ventanamenu.getBtnSeleccionar().addActionListener(this);
			llenarTablaMenu("pizza");
			ventanamenu.setVisible(true);
			
		}
		else if (this.ventanaPedido!= null && e.getSource()==this.ventanaPedido.getBtnVerOtros())
		{
			ventanamenu=new pedidoMenu(this,ventanaPedido);
			ventanamenu.setTitle("otros");
			ventanamenu.getBtnSeleccionar().addActionListener(this);
			llenarTablaMenu("otros");
			ventanamenu.setVisible(true);
			
		}
		else if (this.ventanaPedido!= null && e.getSource()==this.ventanaPedido.getBtnVerPromociones())
		{
			ventanamenu=new pedidoMenu(this,ventanaPedido);
			ventanamenu.setTitle("promocion");
			ventanamenu.getBtnSeleccionar().addActionListener(this);
			llenarTablaMenu("promocion");
			ventanamenu.setVisible(true);
			
		}
		else if (this.ventanamenu!= null && e.getSource()==this.ventanamenu.getBtnSeleccionar())
		{
			switch(ventanamenu.getTitle())
			{
			case("empanada"):
			{
				ventanaPedido.getTfAgregarEmpanada().setText(ventanamenu.getTable().getValueAt(ventanamenu.getTable().getSelectedRow(), 0).toString());
				ventanaPedido.getTfPrecioUniEmpanada().setText(ventanamenu.getTable().getValueAt(ventanamenu.getTable().getSelectedRow(), 1).toString());
				break;
			}
			case("pizza"):
			{
				ventanaPedido.getTfAgregarPizza().setText(ventanamenu.getTable().getValueAt(ventanamenu.getTable().getSelectedRow(), 0).toString());
				ventanaPedido.getTfPrecioUniPizza().setText(ventanamenu.getTable().getValueAt(ventanamenu.getTable().getSelectedRow(), 1).toString());
				break;
			}
			case("otros"):
			{
				ventanaPedido.getTfAgregarOtroProducto().setText(ventanamenu.getTable().getValueAt(ventanamenu.getTable().getSelectedRow(), 0).toString());
				ventanaPedido.getTfPrecioUniOtro().setText(ventanamenu.getTable().getValueAt(ventanamenu.getTable().getSelectedRow(), 1).toString());
				break;
			}
			case("promocion"):
			{
				ventanaPedido.getTfAgregarPromocion().setText(ventanamenu.getTable().getValueAt(ventanamenu.getTable().getSelectedRow(), 0).toString());
				ventanaPedido.getTfPrecioUniPromocion().setText(ventanamenu.getTable().getValueAt(ventanamenu.getTable().getSelectedRow(), 1).toString());
				break;
			}
			}
			ventanamenu.dispose();
		}
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnAgregarProducto())
		{
			ventanaAgregarProducto=new productoAlta();
			llenarTablaProductos();
			ventanaAgregarProducto.getBtnAgregarProducto().addActionListener(this);
			ventanaAgregarProducto.setVisible(true);
		}
		
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnAgregarProveedor())
		{
			ventanaAgregarProveedor=new proveedorAlta();
			ventanaAgregarProveedor.getBtnRegistrar().addActionListener(this);
			ventanaAgregarProveedor.setVisible(true);
		}
		

		
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnAgregarRepartidor())
		{
			ventanaAgregarRepartidor=new repartidorAlta();
			ventanaAgregarRepartidor.getBtnCalendario().addActionListener(this);
			ventanaAgregarRepartidor.getBtnRegistrar().addActionListener(this);
			ventanaAgregarRepartidor.setVisible(true);
		}
		
		//dar de alta a un producto
		else if (this.ventanaAgregarProducto!= null && e.getSource()==this.ventanaAgregarProducto.getBtnAgregarProducto())
		{
			ProductoDTO nuevo=new ProductoDTO();
			nuevo.setIdproducto(this.producto.ultimoProducto()+1);
			nuevo.setNombre(ventanaAgregarProducto.getTfDenominacion().getText().toString());
			nuevo.setPrecio(Integer.parseInt(ventanaAgregarProducto.getTfPrecio().getText().toString()));
			System.out.println(ventanaAgregarProducto.getCbTipo().getSelectedItem().toString());
			nuevo.setTipo(ventanaAgregarProducto.getCbTipo().getSelectedItem().toString());
			producto.agregarProducto(nuevo);
			llenarTablaProductos();
		}
		
		//acciones asocidas a editar productos
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnEditarProducto())
		{
			ventanaEditarProducto=new productoBajaModificacion(this);
			ventanaEditarProducto=new productoBajaModificacion(this);
			ventanaEditarProducto.getBtnGuardar().addActionListener(this);
			ventanaEditarProducto.getBtnQuitar().addActionListener(this);
			llenarTablaProductosEditados();
			ventanaEditarProducto.setVisible(true);
		}
		//quitar productos
		else if(this.ventanaEditarProducto!= null && e.getSource()==this.ventanaEditarProducto.getBtnQuitar())
		{
			ProductoDTO nuevo= this.producto.buscarProductoPorNombre(ventanaEditarProducto.getTable().getValueAt(this.ventanaEditarProducto.getTable().getSelectedRow(), 0).toString());
			this.producto.quitarProducto(nuevo);
			llenarTablaProductosEditados();
			ventanaEditarProducto.getTfNombre().setText("");
			ventanaEditarProducto.getTfPrecio().setText("");
		}
		//modificar producto
		else if(this.ventanaEditarProducto!= null && e.getSource()==this.ventanaEditarProducto.getBtnGuardar())
		{
			ProductoDTO nuevo= this.producto.buscarProductoPorNombre(ventanaEditarProducto.getTable().getValueAt(this.ventanaEditarProducto.getTable().getSelectedRow(), 0).toString());
			nuevo.setNombre(ventanaEditarProducto.getTfNombre().getText().toString());
			nuevo.setPrecio(Integer.parseInt(ventanaEditarProducto.getTfPrecio().getText().toString()));
			nuevo.setTipo(ventanaEditarProducto.getCbTipo().getSelectedItem().toString());
			this.producto.quitarProducto(nuevo);
			this.producto.agregarProducto(nuevo);
			llenarTablaProductosEditados();
			ventanaEditarProducto.getTfNombre().setText("");
			ventanaEditarProducto.getTfPrecio().setText("");			
		}
		//crear un proveedor
		else if (this.ventanaAgregarProveedor!= null && e.getSource()==this.ventanaAgregarProveedor.getBtnRegistrar())
		{
			ProveedorDTO nuevo= new ProveedorDTO();
			nuevo.setId(proveedor.ultimoProveedor()+1);
			nuevo.setNombre(ventanaAgregarProveedor.getTfDenominacion().getText());
			nuevo.setNombrecontacto(ventanaAgregarProveedor.getTfNombreContacto().getText());
			nuevo.setComentario(ventanaAgregarProveedor.getTfComentario().getText());
			nuevo.setFueeliminado(false);
			//agregar la lista de categorias
			//nuevo.setCategoria(categoria.pasarDeStringAArray(ventanaAgregarProveedor.getTfCategoria().getText()));
			nuevo.setDireccion(ventanaAgregarProveedor.getTfDireccion().getText());
			nuevo.setEmail(ventanaAgregarProveedor.getTfEmail().getText());
			nuevo.setTelefono(ventanaAgregarProveedor.getTfTelefono().getText());
			proveedor.agregarProveedor(nuevo);
			ventanaAgregarProveedor.dispose();
		}
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnEditarProveedor())
		{
			ventanaEditarProveedor=new proveedorBajaModificacion(this);
			llenarTablaProveedor();
			ventanaEditarProveedor.getBtnQuitar().addActionListener(this);
			ventanaEditarProveedor.getBtnGuardar().addActionListener(this);
			ventanaEditarProveedor.setVisible(true);
		}
		//quitar proveedor
		else if (this.ventanaEditarProveedor!= null && e.getSource()==this.ventanaEditarProveedor.getBtnQuitar())
		{
			ProveedorDTO aux= this.proveedor.buscarProveedor(ventanaEditarProveedor.getTfDenominacion().getText());
			this.proveedor.quitarProveedor(aux);
			llenarTablaProveedor();
			ventanaEditarProveedor.getTfDenominacion().setText("");
			ventanaEditarProveedor.getTfDireccion().setText("");
			ventanaEditarProveedor.getTfEmail().setText("");
			ventanaEditarProveedor.getTfTelefono().setText("");
		}
		//modificar un proveedor
		else if (this.ventanaEditarProveedor!= null && e.getSource()==this.ventanaEditarProveedor.getBtnGuardar())
		{
			ProveedorDTO aux= this.proveedor.buscarProveedor(ventanaEditarProveedor.getTfDenominacion().getText());
			aux.setDireccion(ventanaEditarProveedor.getTfDireccion().getText());
			aux.setEmail(ventanaEditarProveedor.getTfEmail().getText());
			aux.setTelefono(ventanaEditarProveedor.getTfTelefono().getText());
			this.proveedor.quitarProveedor(aux);
			this.proveedor.agregarProveedor(aux);
			llenarTablaProveedor();
			ventanaEditarProveedor.getTfDenominacion().setText("");
			ventanaEditarProveedor.getTfDireccion().setText("");
			ventanaEditarProveedor.getTfEmail().setText("");
			ventanaEditarProveedor.getTfTelefono().setText("");
		}
		//acciones relacionadas a la baja modificacion de repartidores
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnEditarRepartidor())
		{
			ventanaEditarRepartidor=new repartidorBajaModificacion(this);
			llenarTablaRepartidor();
			ventanaEditarRepartidor.getBtnQuitar().addActionListener(this);
			ventanaEditarRepartidor.getBtnGuardar().addActionListener(this);
			ventanaEditarRepartidor.setVisible(true);
		}
		//quitar un repartidor
		else if (this.ventanaEditarRepartidor!= null && e.getSource()==this.ventanaEditarRepartidor.getBtnQuitar())
		{
			RepartidorDTO nuevo= this.repartidor.buscarRepartidor(Integer.parseInt(ventanaEditarRepartidor.getTable().getValueAt(this.ventanaEditarRepartidor.getTable().getSelectedRow(), 0).toString()));
			this.repartidor.quitarRepartidor(nuevo);
			llenarTablaRepartidor();
			ventanaEditarRepartidor.getTfdni().setText("");
			ventanaEditarRepartidor.getTfApellido().setText("");
			ventanaEditarRepartidor.getTfCelular().setText("");
			ventanaEditarRepartidor.getTfdni().setText("");
			ventanaEditarRepartidor.getTfFechaNacimiento().setText("");
			ventanaEditarRepartidor.getTfNombre().setText("");
			ventanaEditarRepartidor.getTfComentario().setText("");
			ventanaEditarRepartidor.getTfPatente().setText("");
			ventanaEditarRepartidor.getTfDescripcion().setText("");
			
		}
		//modificar un repartidor
		else if (this.ventanaEditarRepartidor!= null && e.getSource()==this.ventanaEditarRepartidor.getBtnGuardar())
		{
			RepartidorDTO rep= this.repartidor.buscarRepartidor(Integer.parseInt(ventanaEditarRepartidor.getTable().getValueAt(this.ventanaEditarRepartidor.getTable().getSelectedRow(), 0).toString()));
			rep.setId(rep.getId());
			rep.setNombre(ventanaEditarRepartidor.getTfNombre().getText().toString());
			rep.setDni(rep.getDni());
			rep.setApellido(ventanaEditarRepartidor.getTfApellido().getText().toString());
			rep.setFechaNacimiento(ventanaEditarRepartidor.getTfFechaNacimiento().getText().toString());
			rep.setTelefono(ventanaEditarRepartidor.getTfCelular().getText().toString());
			rep.setEstado(null);
			repartidor.quitarRepartidor(rep);
			repartidor.agregarRepartidor(rep);
			llenarTablaRepartidor();
			ventanaEditarRepartidor.getTfdni().setText("");
			ventanaEditarRepartidor.getTfApellido().setText("");
			ventanaEditarRepartidor.getTfCelular().setText("");
			ventanaEditarRepartidor.getTfdni().setText("");
			ventanaEditarRepartidor.getTfFechaNacimiento().setText("");
			ventanaEditarRepartidor.getTfNombre().setText("");
		}
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnAgregarMatPrima())
		{
			ventanaAgregarMatPrima=new matPrimaAlta();
			ventanaAgregarMatPrima.setVisible(true);
		}
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnEditarMatPrima())
		{
			ventanaEditarMatPrima=new matPrimaBajaModificacion();
			ventanaEditarMatPrima.setVisible(true);
		}
		
		//acciones asociadas el alta de repartidores
		else if (this.ventanaAgregarRepartidor!= null && e.getSource()==this.ventanaAgregarRepartidor.getBtnCalendario())
		{
			ventanaCalendario=new calendario(ventanaAgregarRepartidor);
			ventanaCalendario.setVisible(true);
		}
		//registra un repartidor nuevo
		else if (this.ventanaAgregarRepartidor!= null && e.getSource()==this.ventanaAgregarRepartidor.getBtnRegistrar())
		{
			RepartidorDTO rep= new RepartidorDTO();
			rep.setId(repartidor.obtenerRepartidores().size()+1);
			rep.setNombre(ventanaAgregarRepartidor.getTfNombre().getText().toString());
			rep.setDni(Integer.parseInt((ventanaAgregarRepartidor.getTfDni().getText().toString())));
			rep.setApellido(ventanaAgregarRepartidor.getTfApellido().getText().toString());
			rep.setFechaNacimiento(ventanaAgregarRepartidor.getTfFechaNacimiento().getText().toString());
			rep.setTelefono(ventanaAgregarRepartidor.getTfCelular().getText().toString());
			rep.setEstado(null);
			rep.setComentario(ventanaAgregarRepartidor.getTfComentario().getText().toString());
			rep.setVehiculo(ventanaAgregarRepartidor.getTfVehiculo().getText().toString());
			rep.setPatente(ventanaAgregarRepartidor.getTfPatente().getText().toString());
			rep.setFueeliminado(false);
			repartidor.agregarRepartidor(rep);
			ventanaAgregarRepartidor.dispose();
		}
		//ventana de configuracion agregar una promocion
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnAgregarPromocion())
		{
			ventanaAgregarPromocion=new promocionAlta();
			ventanaAgregarPromocion.setVisible(true);
		}
		
		//ventana de configuracion editar una promocion
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnEditarPromocion())
		{
			ventanaEditarPromocion=new promocionBajaModificacion();
			ventanaEditarPromocion.setVisible(true);
		}
		
		//ventana de configuracion para editar un cliente
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnEditarCliente())
		{
			ventanaModificacionCliente=new clienteBajaModificacion();
			ventanaModificacionCliente.setVisible(true);
		}
		
		//ventana de configuraciones para gestionar una categoria
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnGestionarCategorias())
		{
			ventanaGestionCategoria=new gestionCategoria();
			ventanaGestionCategoria.setVisible(true);
		}
		//generar ventana para dar de alta a cliente
		else if (this.ventanaCliente!= null && e.getSource()==this.ventanaCliente.getBtnAgregarCliente())
		{
			ventanaRegistrarCliente= new registroDeCliente(ventanaCliente, this);
			ventanaRegistrarCliente.getBtnRegistrar().addActionListener(this);
			ventanaRegistrarCliente.setVisible(true);
		}
		//dar de alta a cliente
				else if (this.ventanaRegistrarCliente!= null && e.getSource()==this.ventanaRegistrarCliente.getBtnRegistrar())
				{
					ClienteDTO nuevo= new ClienteDTO();
					nuevo.setDni(Integer.parseInt(ventanaRegistrarCliente.getTfdni().getText().toString()));
					nuevo.setApellido(ventanaRegistrarCliente.getTfApellido().getText().toString());
					nuevo.setNombre(ventanaRegistrarCliente.getTfNombre().getText().toString());
					nuevo.setCalle(ventanaRegistrarCliente.getTfCalle().getText().toString());
					nuevo.setNumeracion(ventanaRegistrarCliente.getTfNumeracion().getText().toString());
					nuevo.setEntrecalle1(ventanaRegistrarCliente.getTfEntreCalle1().getText().toString());
					nuevo.setEntrecalle2(ventanaRegistrarCliente.getTfEntreCalle2().getText().toString());
					nuevo.setCodPostal(ventanaRegistrarCliente.getTfCodPostal().getText().toString());
					nuevo.setEmail(ventanaRegistrarCliente.getTfEmail().getText().toString());
					cliente.agregarCliente(nuevo);
					ventanaCliente.getTfAgregarDNI().setText(nuevo.getDni().toString());
					ventanaCliente.getTfNombrApellido().setText("Apellido y Nombre: "+nuevo.getNombre()+" "+ nuevo.getApellido());
					ventanaCliente.getTfDireccionTelefono().setText("Direccion: "+nuevo.getCalle()+" "+nuevo.getNumeracion());
					ventanaRegistrarCliente.dispose();
				}
		
		//editar cliente
		else if(this.ventanaCliente!= null && e.getSource()==this.ventanaCliente.getBtnEditarCliente())
		{
			if(ventanaCliente.getTfAgregarDNI().getText().length()>7)
			{
				ventanaRegistrarCliente=new registroDeCliente(ventanaCliente, this);
				ClienteDTO aux=cliente.buscarCliente(Integer.parseInt(ventanaCliente.getTfAgregarDNI().getText().toString()));
				ventanaRegistrarCliente.getTfdni().setText(aux.getDni().toString());
				ventanaRegistrarCliente.getTfNombre().setText(aux.getNombre());
				ventanaRegistrarCliente.getTfApellido().setText(aux.getApellido());
				ventanaRegistrarCliente.getTfCalle().setText(aux.getCalle());
				ventanaRegistrarCliente.getTfNumeracion().setText(aux.getNumeracion());
				ventanaRegistrarCliente.getTfEntreCalle1().setText(aux.getEntrecalle1());
				ventanaRegistrarCliente.getTfEntreCalle2().setText(aux.getEntrecalle2());
				ventanaRegistrarCliente.getTfCodPostal().setText(aux.getCodPostal());
				ventanaRegistrarCliente.getTfEmail().setText(aux.getEmail());
				ventanaRegistrarCliente.setVisible(true);
			}
			else
				JOptionPane.showMessageDialog(null, "Error el debe haber un dni registrado para poder editarlo");
		}
		
		
	}
	
	private void generarPDFordenMatPrima() {
		solicitudDeMateriaPrima ordenPDF = new solicitudDeMateriaPrima(ventanaOrdenMatPrima.getNuevaOrden());
		
	}

	private ArrayList<ItemMateriaPrimaDTO> generarListadoCompra() {
		ArrayList<ItemMateriaPrimaDTO> listadoCompra = new ArrayList<ItemMateriaPrimaDTO>();
		for (int i=0; i < ventanaOrdenMatPrima.getModeloItemsSolicitados().getRowCount(); i++){
			//MateriaPrimaDTO MatPrima= getMatPrimaSeleccionada(modeloMatPrima.getValueAt(i, 0).toString());
			ItemMateriaPrimaDTO itemMatPrima = new ItemMateriaPrimaDTO(getMatPrimaSeleccionada(ventanaOrdenMatPrima.getModeloItemsSolicitados().getValueAt(i, 0).toString()), 
					Integer.parseInt(ventanaOrdenMatPrima.getModeloItemsSolicitados().getValueAt(i, 1).toString()));
			listadoCompra.add( itemMatPrima);
		}
		return listadoCompra;
	}

	private void agregarItemTabla(ItemMateriaPrimaDTO itemSolicitado) {
		Integer indice = getIntFilaItem(itemSolicitado);
		if (indice==-1){
			ventanaOrdenMatPrima.getModeloItemsSolicitados().addRow(new Object[] {itemSolicitado.getItemMatPrima().getNombre(), String.valueOf(itemSolicitado.getCantidad())});
		}
		else{
			Integer cantTotal = Integer.parseInt(ventanaOrdenMatPrima.getModeloItemsSolicitados().getValueAt(indice, 1).toString()) + itemSolicitado.getCantidad();
			ventanaOrdenMatPrima.getModeloItemsSolicitados().setValueAt(cantTotal, indice, 1);
		}
		ventanaOrdenMatPrima.getTablaItemsMateriaPrima().setModel(ventanaOrdenMatPrima.getModeloItemsSolicitados());		
	}

	private Integer getIntFilaItem(ItemMateriaPrimaDTO itemSolicitado) {
		for (int i = 0; i < ventanaOrdenMatPrima.getModeloItemsSolicitados().getRowCount();i++){
			if (((String) ventanaOrdenMatPrima.getModeloItemsSolicitados().getValueAt(i, 0)).compareTo(itemSolicitado.getItemMatPrima().getNombre())==0 ){
				return i;
			}
		}
		return -1;
	}

	private MateriaPrimaDTO getMatPrimaSeleccionada(String text) {
		for (int  i= 0; i < ventanaOrdenMatPrima.getMateriasPrimasFiltradas().size();i++){
			if ( text.compareTo(ventanaOrdenMatPrima.getMateriasPrimasFiltradas().get(i).getNombre())==0 ){
				return ventanaOrdenMatPrima.getMateriasPrimasFiltradas().get(i);
			}
		}
		return null;
	}

	public ArrayList<ItemDTO> generarListaItems() 
	{
		ArrayList<ItemDTO> listaAux= new ArrayList<ItemDTO>();
		
		for(int i=0; i<this.ventanaPedido.getTablaItems().getRowCount(); i++)
		{
			if(producto.buscaNombresProductos(this.ventanaPedido.getModel().getValueAt(i, 0).toString())!=null)
			{
				ItemDTO aux=new ItemDTO(this.item.ultimoItem()+1,this.getProducto().buscarProductoPorNombre(this.ventanaPedido.getModel().getValueAt(i, 0).toString()), Integer.parseInt((String)this.ventanaPedido.getModel().getValueAt(i, 1)), (String)this.ventanaPedido.getModel().getValueAt(i, 3),false);
				item.agregarItem(aux);
				listaAux.add(aux);
			}
		}
		return listaAux;
	}
	private ArrayList<OfertaDTO> generarListaOfertas() 
	{
		ArrayList<OfertaDTO> listaAux= new ArrayList<OfertaDTO>();
		for(int i=0; i<this.ventanaPedido.getTablaItems().getRowCount(); i++)
		{
			if((oferta.buscarOfertaPorNombre(this.ventanaPedido.getModel().getValueAt(i, 0).toString()))!=null)
			{
				OfertaDTO aux=oferta.buscarOfertaPorNombre(this.ventanaPedido.getModel().getValueAt(i, 1).toString());
				aux.setIdOferta(this.oferta.ultimaOferta()+1);
				aux.setNombre(aux.getNombre());
				aux.setPrecio(aux.getPrecio());
				aux.setProductosOfertados(aux.getProductosOfertados());
				oferta.agregarOferta(aux);
				listaAux.add(aux);
			}
		}
	return listaAux;
	}

	public Repartidores getRepartidor() {
		return repartidor;
	}

	public void setRepartidor(Repartidores repartidor) {
		this.repartidor = repartidor;
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
		this.ventanamenu.getModel().setRowCount(0); //Para vaciar la tabla
		this.ventanamenu.getModel().setColumnCount(0);
		this.ventanamenu.getModel().setColumnIdentifiers(this.ventanamenu.getNombreColumnas());
		
	 	Iterator<ProductoDTO> Iterador = this.producto.obtenerProducto().iterator();
		while(Iterador.hasNext())
		{
			ProductoDTO elemento = Iterador.next();
			if(elemento.getTipo().compareTo(tipo)==0)
			{
				Object[] fila = {elemento.getNombre(), elemento.getPrecio()};
				this.ventanamenu.getModel().addRow(fila);			
			}
		}
	}
	private void llenarTablaProveedor()
	{
		this.ventanaEditarProveedor.getModel().setRowCount(0);
		this.ventanaEditarProveedor.getModel().setColumnCount(0);
		this.ventanaEditarProveedor.getModel().setColumnIdentifiers(this.ventanaEditarProveedor.getNombreColumnasProveedor());
		Iterator<ProveedorDTO> Iterador = this.proveedor.obtenerProveedor().iterator();
		while(Iterador.hasNext())
		{
			ProveedorDTO elemento = Iterador.next();
			Object[] fila = {elemento.getNombre()};
			this.ventanaEditarProveedor.getModel().addRow(fila);			
		}
	}
	
	private void llenarTablaRepartidor()
	{
		this.ventanaEditarRepartidor.getModel().setRowCount(0);
		this.ventanaEditarRepartidor.getModel().setColumnCount(0);
		this.ventanaEditarRepartidor.getModel().setColumnIdentifiers(this.ventanaEditarRepartidor.getNombreColumnasRepartidor());
		Iterator<RepartidorDTO> Iterador = this.repartidor.obtenerRepartidores().iterator();
		while(Iterador.hasNext())
		{
			RepartidorDTO elemento = Iterador.next();
			Object[] fila = {elemento.getDni(), elemento.getNombre(), elemento.getApellido()};
			this.ventanaEditarRepartidor.getModel().addRow(fila);			
		}
	}
	
	private void llenarTablaProductos()
	{
		this.ventanaAgregarProducto.getModel().setRowCount(0);
		this.ventanaAgregarProducto.getModel().setColumnCount(0);
		this.ventanaAgregarProducto.getModel().setColumnIdentifiers(this.ventanaAgregarProducto.getNombreColumnasProducto());
		Iterator<ProductoDTO> Iterador = this.producto.obtenerProducto().iterator();
		while(Iterador.hasNext())
		{
			ProductoDTO elemento = Iterador.next();
			Object[] fila = {elemento.getNombre(), elemento.getPrecio(), elemento.getTipo()};
			this.ventanaAgregarProducto.getModel().addRow(fila);			
		}
	}
	
	private void llenarTablaProductosEditados()
	{
		this.ventanaEditarProducto.getModel().setRowCount(0);
		this.ventanaEditarProducto.getModel().setColumnCount(0);
		this.ventanaEditarProducto.getModel().setColumnIdentifiers(this.ventanaEditarProducto.getNombreColumnasProducto());
		Iterator<ProductoDTO> Iterador = this.producto.obtenerProducto().iterator();
		while(Iterador.hasNext())
		{
			ProductoDTO elemento = Iterador.next();
			Object[] fila = {elemento.getNombre(), elemento.getPrecio(), elemento.getTipo()};
			this.ventanaEditarProducto.getModel().addRow(fila);			
		}
	}
	///  METODOS PARA VENTANA ORDEN DE MATERIA PRIMA
	private void cargarProveedores(){
		ArrayList<ProveedorDTO> listaProveedores = (ArrayList<ProveedorDTO>) this.proveedor.obtenerProveedor();
		for (int i=0; i< listaProveedores.size();i++){
			ventanaOrdenMatPrima.getComboListaProveedores().addItem(listaProveedores.get(i).getNombre());
		}
	}
	
	private void cargarCategorias() {
		ventanaOrdenMatPrima.getComboListaCategorias().removeAllItems();
		for (int i = 0; i < ventanaOrdenMatPrima.getProvSeleccionado().getCategoria().size();i++){
			ventanaOrdenMatPrima.getComboListaCategorias().addItem(ventanaOrdenMatPrima.getProvSeleccionado().getCategoria().get(i).getDenominacion());
		 }
	}
	
	private ProveedorDTO getProveedorSeleccionado() throws Exception{
		String nomProveedor = (String) ventanaOrdenMatPrima.getComboListaProveedores().getSelectedItem();
		if (getProveedor(nomProveedor)!= null){
			return getProveedor(nomProveedor);
		}
		else{
			throw new Exception("El proveedor recibido en nulo O no existe en el listado de proveedores.");
		}
	}
	
	private ProveedorDTO getProveedor(String nomProveedor){
		ArrayList<ProveedorDTO> listaProveedores = (ArrayList<ProveedorDTO>) this.proveedor.obtenerProveedor();
		for (int i= 0; i < listaProveedores.size();i++){
			if (nomProveedor.compareTo(listaProveedores.get(i).getNombre()) == 0){
				return listaProveedores.get(i);
			}
		}
		return null;
	}
	
	private void prepararMatPrimaParaBusqXCategoria() {
		ventanaOrdenMatPrima.getTextAutoAcompleter().removeAllItems();
		for (int i=0; i < ventanaOrdenMatPrima.getMateriasPrimasFiltradas().size(); i++){
			if(ventanaOrdenMatPrima.getMateriasPrimasFiltradas().get(i).getCategoria().getDenominacion().compareTo
					((String)ventanaOrdenMatPrima.getComboListaCategorias().getSelectedItem()) == 0){
				ventanaOrdenMatPrima.getTextAutoAcompleter().addItem(ventanaOrdenMatPrima.getMateriasPrimasFiltradas().get(i).getNombre());
			}
		}
	}
	
	private void generarArrayMatPrimaProveed() throws Exception {
		/** Tomo cada elemento q compone el comboBox categorias, con cada nombre de categoria pregunto por cada item*/
		ventanaOrdenMatPrima.getMateriasPrimasFiltradas().clear();
		ventanaOrdenMatPrima.getTextAutoAcompleter().removeAllItems();
		ArrayList<MateriaPrimaDTO> materiasPrimas = (ArrayList<MateriaPrimaDTO>) this.materiasPrimas.obtenerMatPrimas();
		for (int i = 0; i < materiasPrimas.size();i++){
			if ( contieneCategoria(materiasPrimas.get(i).getCategoria().getDenominacion())){
				ventanaOrdenMatPrima.getMateriasPrimasFiltradas().add(materiasPrimas.get(i));
			}
		}
		
	}
	
	private boolean contieneCategoria(String nomCategoria) throws Exception {
		for (int  i= 0; i < ventanaOrdenMatPrima.getProveedorSeleccionado().getCategoria().size();i++){
			if (ventanaOrdenMatPrima.getProveedorSeleccionado().getCategoria().get(i).getDenominacion().compareTo(nomCategoria)== 0){
				return true;
			}
		}
		return false;
	}
	
}
