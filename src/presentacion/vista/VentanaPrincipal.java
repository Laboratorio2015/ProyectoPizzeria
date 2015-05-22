package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Monitor.Cocina.PadreMonitor;
import main.Main;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal 
{

	private JFrame frame;
	private VentanaPrincipal _this;
	private JButton btnPedidosPendientes;
	private PadreMonitor monitorCocina;

	
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
		
		JButton btnIngresarPedido = new JButton("");
		btnIngresarPedido.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				ordenDePedido pedido=new ordenDePedido(_this, null);
				pedido.setVisible(true);
			}
		});
		btnIngresarPedido.setOpaque(false);
		btnIngresarPedido.setToolTipText("Ingresar un Nuevo Pedido");
		btnIngresarPedido.setBounds(58, 216, 117, 156);
		frame.getContentPane().add(btnIngresarPedido);
		
		btnPedidosPendientes = new JButton("New button");
		btnPedidosPendientes.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				pedidosPendientes pedidoPendiente=new pedidosPendientes(_this);
				pedidoPendiente.llenarTabla();
				pedidoPendiente.setVisible(true);
			}
		});
		btnPedidosPendientes.setOpaque(false);
		btnPedidosPendientes.setBounds(403, 237, 174, 135);
		
		
		
		frame.getContentPane().add(btnPedidosPendientes);
		
		JButton btnPedMatPrima = new JButton("New button");
		btnPedMatPrima.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				ordenarMatPrima matPrima= new ordenarMatPrima(_this);
				matPrima.setVisible(true);
			}
		});
		btnPedMatPrima.setOpaque(false);
		btnPedMatPrima.setBounds(792, 237, 141, 165);
		frame.getContentPane().add(btnPedMatPrima);
	}
	
	public void show()
	{
		this.frame.setVisible(true);
	}

	public void actualizarPedidoPend() 
	{
		//tfPedidosEnCurso.setText(Integer.toString(Main.listaPedidos.pedidos.size()));
	}
	
	public JButton getBtnPedidosPendientes() 
	{
		return btnPedidosPendientes;
	}
}
