package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Dimension;

public class opcionesDeConfiguracion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnAgregarRepartidor;
	private JButton btnEditarRepartidor;
	private JButton btnAgregarProducto;
	private JButton btnEditarProducto;
	private JButton btnAgregarPromocion;
	private JButton btnAgregarProveedor;
	private JButton btnEditarProveedor;
	private JButton btnAgregarMatPrima;
	private JButton btnEditarMatPrima;
	private JButton btnEditarPromocion;
	private JButton btnGestionarCategorias;
	private JButton btnEditarCliente;


	public opcionesDeConfiguracion() {
		setMinimumSize(new Dimension(1000, 650));
		setBounds(150, 10, 1000, 689);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("");
			label.setMinimumSize(new Dimension(1000, 650));
			label.setIcon(new ImageIcon(opcionesDeConfiguracion.class.getResource("/prototipos/opciones de Configuracion.png")));
			label.setBounds(0, 0, 984, 650);
			contentPanel.add(label);
		}
		
		btnAgregarMatPrima = new JButton("agregarMatPrima");
		btnAgregarMatPrima.setOpaque(false);
		btnAgregarMatPrima.setBounds(691, 154, 123, 91);
		contentPanel.add(btnAgregarMatPrima);
		
		btnEditarMatPrima = new JButton("EditarMatPrima");
		btnEditarMatPrima.setOpaque(false);
		btnEditarMatPrima.setBounds(840, 144, 123, 101);
		contentPanel.add(btnEditarMatPrima);
		{
			btnAgregarRepartidor = new JButton("OK");
			btnAgregarRepartidor.setOpaque(false);
			btnAgregarRepartidor.setToolTipText("Agregar Repartidor");
			btnAgregarRepartidor.setBounds(61, 144, 100, 111);
			contentPanel.add(btnAgregarRepartidor);
			btnAgregarRepartidor.setActionCommand("OK");
			getRootPane().setDefaultButton(btnAgregarRepartidor);
		}
		
		btnEditarRepartidor= new JButton("OK");
		btnEditarRepartidor.setOpaque(false);
		btnEditarRepartidor.setToolTipText("Editar Repartidor");
		btnEditarRepartidor.setActionCommand("OK");
		btnEditarRepartidor.setBounds(205, 144, 100, 111);
		contentPanel.add(btnEditarRepartidor);
		
		btnAgregarProducto= new JButton("OK");
		btnAgregarProducto.setOpaque(false);
		btnAgregarProducto.setToolTipText("Agregar Producto");
		btnAgregarProducto.setActionCommand("OK");
		btnAgregarProducto.setBounds(57, 346, 93, 101);
		contentPanel.add(btnAgregarProducto);
		
		btnEditarProducto= new JButton("OK");
		btnEditarProducto.setOpaque(false);
		btnEditarProducto.setToolTipText("Editar Oferta o Producto");
		btnEditarProducto.setActionCommand("OK");
		btnEditarProducto.setBounds(186, 357, 100, 90);
		contentPanel.add(btnEditarProducto);
		
		btnAgregarProveedor= new JButton("OK");
		btnAgregarProveedor.setOpaque(false);
		btnAgregarProveedor.setToolTipText("Agregar Proveedor");
		btnAgregarProveedor.setActionCommand("OK");
		btnAgregarProveedor.setBounds(380, 124, 100, 131);
		contentPanel.add(btnAgregarProveedor);
		
		btnEditarProveedor= new JButton("OK");
		btnEditarProveedor.setOpaque(false);
		btnEditarProveedor.setToolTipText("Modificar o Eliminar Proveedores");
		btnEditarProveedor.setActionCommand("OK");
		btnEditarProveedor.setBounds(519, 131, 100, 136);
		contentPanel.add(btnEditarProveedor);
		
		btnAgregarPromocion= new JButton("OK");
		btnAgregarPromocion.setOpaque(false);
		btnAgregarPromocion.setToolTipText("Agregar Promocion");
		btnAgregarPromocion.setActionCommand("OK");
		btnAgregarPromocion.setBounds(380, 346, 100, 108);
		contentPanel.add(btnAgregarPromocion);
		
		btnEditarPromocion= new JButton("OK");
		btnEditarPromocion.setToolTipText("Editar Promocion");
		btnEditarPromocion.setOpaque(false);
		btnEditarPromocion.setActionCommand("OK");
		btnEditarPromocion.setBounds(533, 346, 109, 108);
		contentPanel.add(btnEditarPromocion);
		
		btnGestionarCategorias = new JButton("OK");
		btnGestionarCategorias.setToolTipText("Gestionar Categorias");
		btnGestionarCategorias.setOpaque(false);
		btnGestionarCategorias.setActionCommand("OK");
		btnGestionarCategorias.setBounds(716, 357, 100, 108);
		contentPanel.add(btnGestionarCategorias);
		
		btnEditarCliente= new JButton("OK");
		btnEditarCliente.setToolTipText("Editar un Cliente ya ingresado en el Sistema");
		btnEditarCliente.setOpaque(false);
		btnEditarCliente.setActionCommand("OK");
		btnEditarCliente.setBounds(851, 357, 112, 108);
		contentPanel.add(btnEditarCliente);
	}


	public JButton getBtnAgregarRepartidor() {
		return btnAgregarRepartidor;
	}


	public void setBtnAgregarRepartidor(JButton btnAgregarRepartidor) {
		this.btnAgregarRepartidor = btnAgregarRepartidor;
	}


	public JButton getBtnEditarRepartidor() {
		return btnEditarRepartidor;
	}


	public void setBtnEditarRepartidor(JButton btnEditarRepartidor) {
		this.btnEditarRepartidor = btnEditarRepartidor;
	}


	public JButton getBtnAgregarProducto() {
		return btnAgregarProducto;
	}


	public void setBtnAgregarProducto(JButton btnAgregarProducto) {
		this.btnAgregarProducto = btnAgregarProducto;
	}


	public JButton getBtnEditarProducto() {
		return btnEditarProducto;
	}


	public void setBtnEditarProducto(JButton btnEditarProducto) {
		this.btnEditarProducto = btnEditarProducto;
	}


	public JButton getBtnAgregarOferta() {
		return btnAgregarPromocion;
	}


	public void setBtnAgregarOferta(JButton btnAgregarOferta) {
		this.btnAgregarPromocion = btnAgregarOferta;
	}


	public JButton getBtnAgregarProveedor() {
		return btnAgregarProveedor;
	}


	public void setBtnAgregarProveedor(JButton btnAgregarProveedor) {
		this.btnAgregarProveedor = btnAgregarProveedor;
	}


	public JButton getBtnEditarProveedor() {
		return btnEditarProveedor;
	}


	public void setBtnEditarProveedor(JButton btnEditarProveedor) {
		this.btnEditarProveedor = btnEditarProveedor;
	}


	public JButton getBtnAgregarMatPrima() {
		return btnAgregarMatPrima;
	}


	public void setBtnAgregarMatPrima(JButton btnAgregarMatPrima) {
		this.btnAgregarMatPrima = btnAgregarMatPrima;
	}


	public JButton getBtnEditarMatPrima() {
		return btnEditarMatPrima;
	}


	public void setBtnEditarMatPrima(JButton btnEditarMatPrima) {
		this.btnEditarMatPrima = btnEditarMatPrima;
	}


	public JButton getBtnAgregarPromocion() {
		return btnAgregarPromocion;
	}


	public void setBtnAgregarPromocion(JButton btnAgregarPromocion) {
		this.btnAgregarPromocion = btnAgregarPromocion;
	}


	public JButton getBtnEditarPromocion() {
		return btnEditarPromocion;
	}


	public void setBtnEditarPromocion(JButton btnEditarPromocion) {
		this.btnEditarPromocion = btnEditarPromocion;
	}


	public JButton getBtnGestionarCategorias() {
		return btnGestionarCategorias;
	}


	public void setBtnGestionarCategorias(JButton btnGestionarCategorias) {
		this.btnGestionarCategorias = btnGestionarCategorias;
	}


	public JButton getBtnEditarCliente() {
		return btnEditarCliente;
	}


	public void setBtnEditarCliente(JButton btnEditarCliente) {
		this.btnEditarCliente = btnEditarCliente;
	}
	
}
