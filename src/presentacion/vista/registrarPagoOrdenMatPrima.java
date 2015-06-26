package presentacion.vista;


import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import presentacion.controlador.Controlador;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;

import dto.OrdenPedidoMatPrimaDTO;

import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class registrarPagoOrdenMatPrima extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Controlador controlador;
	@SuppressWarnings("unused")
	private VentanaPrincipal ventanaPrincipal;
	private JButton btnRegistrarcobro;
	private final JLabel fondo = new JLabel("");
	private final JTextField textFieldCosto = new JTextField();
	private final JLabel lableNroOrden = new JLabel("");
	private final JLabel labelNomProveedor = new JLabel("");
	private JButton btnNorecibido;

	
	@SuppressWarnings("serial")
	public registrarPagoOrdenMatPrima (VentanaPrincipal ventanaPrincipal, Controlador controlador){
		this.ventanaPrincipal = ventanaPrincipal;
		this.controlador = controlador;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 521, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		textFieldCosto.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldCosto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(!Character.isDigit(arg0.getKeyChar()) && !Character.isISOControl(arg0.getKeyChar()))
				{
				     Toolkit.getDefaultToolkit().beep();
				     arg0.consume();
				 }
			}
		});
		textFieldCosto.setBounds(311, 168, 67, 23);
		contentPane.add(textFieldCosto);
		textFieldCosto.setColumns(10);
		lableNroOrden.setFont(new Font("Tahoma", Font.BOLD, 14));
		lableNroOrden.setForeground(new Color(51, 102, 102));
		lableNroOrden.setBounds(147, 114, 217, 23);
		contentPane.add(lableNroOrden);
		labelNomProveedor.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelNomProveedor.setForeground(new Color(51, 102, 102));
		labelNomProveedor.setBounds(128, 143, 217, 21);
		contentPane.add(labelNomProveedor);

		fondo.setIcon(new ImageIcon(registrarPagoOrdenMatPrima.class.getResource("/prototipos/registrarRecepci\u00F2nyPagoOrdenMatPrima.png")));
		fondo.setBounds(0, 0, 505, 306);
		contentPane.add(fondo);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setOpaque(false);
		btnCancelar.setBounds(323, 223, 111, 35);
		contentPane.add(btnCancelar);
		
		btnRegistrarcobro = new JButton("Registrarcobro");
		btnRegistrarcobro.setOpaque(false);
		btnRegistrarcobro.setBounds(79, 223, 111, 35);
		contentPane.add(btnRegistrarcobro);
		
		btnNorecibido = new JButton("noRecibido");
		btnNorecibido.setOpaque(false);
		btnNorecibido.setBounds(200, 223, 105, 35);
		contentPane.add(btnNorecibido);
	}


	public JTextField getTextFieldCosto() {
		return textFieldCosto;
	}

	public JLabel getLableNroOrden() {
		return lableNroOrden;
	}


	public JLabel getLabelNomProveedor() {
		return labelNomProveedor;
	}


	public void cargarDatosOrden(OrdenPedidoMatPrimaDTO ordenSeleccionada) {
		this.labelNomProveedor.setText(ordenSeleccionada.getProveedor().getNombre().trim());
		this.lableNroOrden.setText(ordenSeleccionada.getIdCompra().toString());
	}


	public JButton getBtnRegistrarcobro() {
		return btnRegistrarcobro;
	}


	public void setBtnRegistrarcobro(JButton btnRegistrarcobro) {
		this.btnRegistrarcobro = btnRegistrarcobro;
	}
}