package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;



public class VentanaPrincipal 
{

	private JFrame frame;
	private VentanaPrincipal _this;
	private JButton btnPedidosPendientes;
	private JButton btnIngresarPedido;
	private JButton btnReportes;
	private JButton btnConfiguraciones;
	private JButton btnPedMatPrima;

	
	public VentanaPrincipal() 
	{
		super();
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e){
			System.out.println("error de setting nativo LAF: "+ e +"\n"+"es decir, no encuentro el look and feel.");
		}
		initialize();
	}


	private void initialize() 
{
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(1000, 650));
		frame.setBounds(150, 10, 1016, 689);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		_this=this;
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/prototipos/fondoPincipal v2.png")));
		lblNewLabel.setBounds(0, 0, 1000, 650);
		frame.getContentPane().add(lblNewLabel);
		
		btnIngresarPedido= new JButton("");
		btnIngresarPedido.setOpaque(false);
		btnIngresarPedido.setToolTipText("Ingresar un Nuevo Pedido");
		btnIngresarPedido.setBounds(58, 216, 117, 156);
		frame.getContentPane().add(btnIngresarPedido);
		
		btnPedidosPendientes = new JButton("New button");
		btnPedidosPendientes.setToolTipText("verifica el estado de los pedidos realizados");
		btnPedidosPendientes.setOpaque(false);
		btnPedidosPendientes.setBounds(403, 237, 174, 135);
		
		
		
		frame.getContentPane().add(btnPedidosPendientes);
		
		btnPedMatPrima= new JButton("New button");
		btnPedMatPrima.setToolTipText("generar una orden de materia prima");
		btnPedMatPrima.setOpaque(false);
		btnPedMatPrima.setBounds(792, 237, 141, 165);
		frame.getContentPane().add(btnPedMatPrima);
		
		btnReportes = new JButton("New button");
		btnReportes.setOpaque(false);
		btnReportes.setBounds(219, 360, 89, 117);
		frame.getContentPane().add(btnReportes);
		
		btnConfiguraciones = new JButton("New button");
		btnConfiguraciones.setOpaque(false);
		btnConfiguraciones.setBounds(585, 399, 102, 86);
		frame.getContentPane().add(btnConfiguraciones);
	}
	
	public void show()
	{
		this.frame.setVisible(true);
	}

	
	public JButton getBtnPedidosPendientes() 
	{
		return btnPedidosPendientes;
	}


	public JButton getBtnIngresarPedido()
	{
		return btnIngresarPedido;
	}

	public void setBtnIngresarPedido(JButton btnIngresarPedido)
	{
		this.btnIngresarPedido = btnIngresarPedido;
	}


	public JButton getBtnReportes() {
		return btnReportes;
	}


	public void setBtnReportes(JButton btnReportes) {
		this.btnReportes = btnReportes;
	}


	public JButton getBtnConfiguraciones() {
		return btnConfiguraciones;
	}

	public void setBtnConfiguraciones(JButton btnConfiguraciones) {
		this.btnConfiguraciones = btnConfiguraciones;
	}

	public void setBtnPedidosPendientes(JButton btnPedidosPendientes) {
		this.btnPedidosPendientes = btnPedidosPendientes;
	}


	public JButton getBtnPedMatPrima() {
		return btnPedMatPrima;
	}


	public void setBtnPedMatPrima(JButton btnPedMatPrima) {
		this.btnPedMatPrima = btnPedMatPrima;
	}
	
	
}
