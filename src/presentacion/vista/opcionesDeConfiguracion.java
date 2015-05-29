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
	private JButton btnAgregarOferta;
	private JButton btnAgregarProveedor;
	private JButton btnEditarProveedor;


	public opcionesDeConfiguracion() {
		setMinimumSize(new Dimension(1000, 650));
		setBounds(150, 50, 1000, 689);
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
		{
			btnAgregarRepartidor = new JButton("OK");
			btnAgregarRepartidor.setOpaque(false);
			btnAgregarRepartidor.setToolTipText("Agregar Repartidor");
			btnAgregarRepartidor.setBounds(136, 199, 141, 136);
			contentPanel.add(btnAgregarRepartidor);
			btnAgregarRepartidor.setActionCommand("OK");
			getRootPane().setDefaultButton(btnAgregarRepartidor);
		}
		
		btnEditarRepartidor= new JButton("OK");
		btnEditarRepartidor.setOpaque(false);
		btnEditarRepartidor.setToolTipText("Editar Repartidor");
		btnEditarRepartidor.setActionCommand("OK");
		btnEditarRepartidor.setBounds(20, 346, 141, 136);
		contentPanel.add(btnEditarRepartidor);
		
		btnAgregarProducto= new JButton("OK");
		btnAgregarProducto.setOpaque(false);
		btnAgregarProducto.setToolTipText("Agregar Producto");
		btnAgregarProducto.setActionCommand("OK");
		btnAgregarProducto.setBounds(427, 188, 141, 136);
		contentPanel.add(btnAgregarProducto);
		
		btnEditarProducto= new JButton("OK");
		btnEditarProducto.setOpaque(false);
		btnEditarProducto.setToolTipText("Editar Oferta o Producto");
		btnEditarProducto.setActionCommand("OK");
		btnEditarProducto.setBounds(325, 335, 141, 136);
		contentPanel.add(btnEditarProducto);
		
		btnAgregarOferta= new JButton("OK");
		btnAgregarOferta.setOpaque(false);
		btnAgregarOferta.setToolTipText("Agregar Oferta");
		btnAgregarOferta.setActionCommand("OK");
		btnAgregarOferta.setBounds(529, 335, 141, 136);
		contentPanel.add(btnAgregarOferta);
		
		btnAgregarProveedor= new JButton("OK");
		btnAgregarProveedor.setOpaque(false);
		btnAgregarProveedor.setToolTipText("Agregar Proveedor");
		btnAgregarProveedor.setActionCommand("OK");
		btnAgregarProveedor.setBounds(697, 199, 141, 136);
		contentPanel.add(btnAgregarProveedor);
		
		btnEditarProveedor= new JButton("OK");
		btnEditarProveedor.setOpaque(false);
		btnEditarProveedor.setToolTipText("Modificar o Eliminar Proveedores");
		btnEditarProveedor.setActionCommand("OK");
		btnEditarProveedor.setBounds(821, 346, 141, 136);
		contentPanel.add(btnEditarProveedor);
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
		return btnAgregarOferta;
	}


	public void setBtnAgregarOferta(JButton btnAgregarOferta) {
		this.btnAgregarOferta = btnAgregarOferta;
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
	
}
