package presentacion.vista;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import presentacion.controlador.Controlador;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.awt.Color;
import java.awt.Font;

public class consultorContabilidad extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Controlador controlador;
	private VentanaPrincipal ventanaPrincipal;
	private final JComboBox<String> cbTipoConsulta = new JComboBox<String>();
	
	//BOTONES Y LABELS:
	private JLabel lblFechaInicio;
	private JLabel lblVentas;
	private JLabel lblCompras;
	private JLabel lblGanancias;
	private JLabel lblFechaFin;
	private JLabel fondo;
	private JButton btnBtnbuscarrangofecha;
	private JButton btnCalendfinicio;
	private JButton btnCalendarffin;
	private JButton btnFinalizarconsulta;
	private JButton btnEnviarxmail;
	private JButton btnImprimircons;
	//Datos
	private String fechaInicio="";
	private String fechaFin;
	private Date dateFechaInicio;
	private Date dateFechaFin;
	private JLabel ocultarRangoFecha;
	
	private Integer diaInicio;
	private Integer diaFin;
	private Integer mesInicio;
	private Integer mesFin;
	private Integer añoFin;
	private Integer añoInicio;


	public consultorContabilidad(VentanaPrincipal ventanaPrincipal, Controlador controlador) {
		this.ventanaPrincipal = ventanaPrincipal;
		this.controlador = controlador;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 706, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cbTipoConsulta.setModel(new DefaultComboBoxModel<String>(new String[] {"Seleccione el tipo de consulta", "Dia de hoy", "Elegir un rango de fechas"}));
		cbTipoConsulta.setBounds(76, 125, 252, 22);
		contentPane.add(cbTipoConsulta);
		
		lblFechaInicio = new JLabel("");
		lblFechaInicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaInicio.setFont(new Font("Calibri", Font.BOLD, 16));
		lblFechaInicio.setForeground(Color.WHITE);
		lblFechaInicio.setBounds(60, 295, 119, 20);
		contentPane.add(lblFechaInicio);
		
		lblVentas = new JLabel("");
		lblVentas.setHorizontalAlignment(SwingConstants.CENTER);
		lblVentas.setForeground(new Color(0, 102, 51));
		lblVentas.setFont(new Font("Browallia New", Font.BOLD, 31));
		lblVentas.setBounds(496, 159, 138, 33);
		contentPane.add(lblVentas);
		
		lblCompras = new JLabel("");
		lblCompras.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompras.setForeground(new Color(0, 102, 51));
		lblCompras.setFont(new Font("Browallia New", Font.BOLD, 31));
		lblCompras.setBounds(496, 217, 138, 33);
		contentPane.add(lblCompras);
		
		lblGanancias = new JLabel("");
		lblGanancias.setHorizontalAlignment(SwingConstants.CENTER);
		lblGanancias.setForeground(new Color(0, 102, 51));
		lblGanancias.setFont(new Font("Browallia New", Font.BOLD, 31));
		lblGanancias.setBounds(496, 277, 138, 33);
		contentPane.add(lblGanancias);
		
		lblFechaFin = new JLabel("");
		lblFechaFin.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaFin.setFont(new Font("Calibri", Font.BOLD, 16));
		lblFechaFin.setForeground(Color.WHITE);
		lblFechaFin.setBounds(60, 350, 119, 20);
		contentPane.add(lblFechaFin);
		
		ocultarRangoFecha = new JLabel("");
		ocultarRangoFecha.setBackground(new Color(153, 153, 00));
		ocultarRangoFecha.setOpaque(true);
		ocultarRangoFecha.setBounds(10, 173, 305, 214);
		contentPane.add(ocultarRangoFecha);
		
		fondo = new JLabel("");
		fondo.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		fondo.setIcon(new ImageIcon(consultorContabilidad.class.getResource("/prototipos/reportesContabilidad.png")));
		fondo.setHorizontalTextPosition(SwingConstants.CENTER);
		fondo.setBounds(0, 0, 690, 486);
		contentPane.add(fondo);
		
		btnBtnbuscarrangofecha = new JButton("btnBuscarRangoFecha");
		btnBtnbuscarrangofecha.setOpaque(false);
		btnBtnbuscarrangofecha.setBounds(253, 312, 58, 45);
		contentPane.add(btnBtnbuscarrangofecha);
		
		btnCalendfinicio = new JButton("calendFInicio");
		btnCalendfinicio.setOpaque(false);
		btnCalendfinicio.setBounds(194, 295, 29, 20);
		contentPane.add(btnCalendfinicio);
		
		btnCalendarffin = new JButton("calendarFFin");
		btnCalendarffin.setOpaque(false);
		btnCalendarffin.setBounds(194, 348, 29, 33);
		contentPane.add(btnCalendarffin);
		
		btnFinalizarconsulta = new JButton("finalizarconsulta");
		btnFinalizarconsulta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		btnFinalizarconsulta.setOpaque(false);
		btnFinalizarconsulta.setBounds(267, 426, 149, 39);
		contentPane.add(btnFinalizarconsulta);
		
		btnEnviarxmail = new JButton("enviarXmail");
		btnEnviarxmail.setOpaque(false);
		btnEnviarxmail.setBounds(410, 348, 41, 39);
		contentPane.add(btnEnviarxmail);
		
		btnImprimircons = new JButton("imprimirCons");
		btnImprimircons.setOpaque(false);
		btnImprimircons.setBounds(548, 348, 58, 45);
		contentPane.add(btnImprimircons);
	}
	
	public void resetearCampos(){
		lblCompras.setText("");
		lblFechaFin.setText("");
		lblFechaInicio.setText("");
		lblGanancias.setText("");
		lblVentas.setText("");
	}

	public void ocultarRango(boolean ocultar){
		ocultarRangoFecha.setVisible(ocultar);
		resetearCampos();
	}
	
	public boolean controlFechas(){
		return dateFechaInicio.before(dateFechaFin);
	}
	
	public JLabel getLblFechaInicio() {
		return lblFechaInicio;
	}

	public void setLblFechaInicio(JLabel lblFechaInicio) {
		this.lblFechaInicio = lblFechaInicio;
	}

	public JLabel getLblVentas() {
		return lblVentas;
	}

	public void setLblVentas(JLabel lblVentas) {
		this.lblVentas = lblVentas;
	}

	public JLabel getLblCompras() {
		return lblCompras;
	}

	public void setLblCompras(JLabel lblCompras) {
		this.lblCompras = lblCompras;
	}

	public JLabel getLblGanancias() {
		return lblGanancias;
	}

	public void setLblGanancias(JLabel lblGanancias) {
		this.lblGanancias = lblGanancias;
	}

	public JLabel getLblFechaFin() {
		return lblFechaFin;
	}

	public void setLblFechaFin(JLabel lblFechaFin) {
		this.lblFechaFin = lblFechaFin;
	}

	public JButton getBtnBtnbuscarrangofecha() {
		return btnBtnbuscarrangofecha;
	}

	public void setBtnBtnbuscarrangofecha(JButton btnBtnbuscarrangofecha) {
		this.btnBtnbuscarrangofecha = btnBtnbuscarrangofecha;
	}

	public JButton getBtnCalendfinicio() {
		return btnCalendfinicio;
	}

	public void setBtnCalendfinicio(JButton btnCalendfinicio) {
		this.btnCalendfinicio = btnCalendfinicio;
	}

	public JButton getBtnCalendarffin() {
		return btnCalendarffin;
	}

	public void setBtnCalendarffin(JButton btnCalendarffin) {
		this.btnCalendarffin = btnCalendarffin;
	}

	public JButton getBtnFinalizarconsulta() {
		return btnFinalizarconsulta;
	}

	public void setBtnFinalizarconsulta(JButton btnFinalizarconsulta) {
		this.btnFinalizarconsulta = btnFinalizarconsulta;
	}

	public JButton getBtnEnviarxmail() {
		return btnEnviarxmail;
	}

	public void setBtnEnviarxmail(JButton btnEnviarxmail) {
		this.btnEnviarxmail = btnEnviarxmail;
	}

	public JButton getBtnImprimircons() {
		return btnImprimircons;
	}

	public void setBtnImprimircons(JButton btnImprimircons) {
		this.btnImprimircons = btnImprimircons;
	}

	public JComboBox<String> getCbTipoConsulta() {
		return cbTipoConsulta;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
		//guardo dia, mes año por separado.
		
		String dia = "";
		String año = "";
		String mes = "";
		
		for (int i=fechaInicio.length()-1; i >= 0; i--){
			if (i > fechaInicio.length()-5){ // 4 espaciosdelaño, un espacio del guion, menos uno del largo q comienza en 1 y array en 0
				año = fechaInicio.charAt(i) + año;
			}
			else if (i == fechaInicio.length()-5){ // 4 espaciosdelaño, un espacio del guion, menos uno del largo q comienza en 1 y array en 0
			}
			else if ( (i != 0 && i !=1) && fechaInicio.charAt(i)!='-'){
				mes = fechaInicio.charAt(i) + mes;
			}
			else{
				if (fechaInicio.charAt(i)!='-'){
					dia = fechaInicio.charAt(i) + dia;
				}
			}
		}
		añoInicio = Integer.parseInt(año);
		mesInicio = Integer.parseInt(mes);
		diaInicio = Integer.parseInt(dia);
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
		//guardo dia, mes año por separado.
		String dia = "";
		String año = "";
		String mes = "";
		
		for (int i=fechaFin.length()-1; i >= 0; i--){
			if (i > fechaFin.length()-5){ // 4 espaciosdelaño, un espacio del guion, menos uno del largo q comienza en 1 y array en 0
				año = fechaFin.charAt(i) + año;
			}
			else if (i == fechaFin.length()-5){ // 4 espaciosdelaño, un espacio del guion, menos uno del largo q comienza en 1 y array en 0
			}
			else if ( (i != 0 && i !=1) && fechaFin.charAt(i)!='-'){
				mes = fechaFin.charAt(i) + mes;
			}
			else{
				if (fechaFin.charAt(i)!='-'){
					dia = fechaFin.charAt(i) + dia;
				}
			}
		}
		añoFin = Integer.parseInt(año);
		mesFin = Integer.parseInt(mes);
		diaFin = Integer.parseInt(dia);
	}


	public Date getDateFechaInicio() {
		return dateFechaInicio;
	}

	public void setDateFechaInicio(Date dateFechaInicio) {
		this.dateFechaInicio = dateFechaInicio;
	}

	public Date getDateFechaFin() {
		return dateFechaFin;
	}

	public void setDateFechaFin(Date dateFechaFin) {
		this.dateFechaFin = dateFechaFin;
	}

	public void mostrarResultados(Integer totalCompras, Integer totalPedidos,Integer ganancia) {
		this.lblCompras.setText(totalCompras.toString());
		this.lblVentas.setText(totalPedidos.toString());
		this.lblGanancias.setText(ganancia.toString());
	}

	public Integer getFechaInicio(String dma) {
		switch (dma) {
		case "dia":
			return this.diaInicio;
		case "mes":
			return this.mesInicio;
		case "año":
			return this.añoInicio;
		}
		return null;
	}

	public Integer getFechaFin(String dma) {
		switch (dma) {
		case "dia":
			return this.diaFin;
		case "mes":
			return this.mesFin;
		case "año":
			return this.añoFin;
		}
		return null;
	}
	
	
	
}