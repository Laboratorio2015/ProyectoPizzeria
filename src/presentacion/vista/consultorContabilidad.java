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

public class consultorContabilidad extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Controlador controlador;
	private VentanaPrincipal ventanaPrincipal;
	private final JComboBox<String> cbTipoConsulta = new JComboBox();
	
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

	public consultorContabilidad(VentanaPrincipal ventanaPrincipal, Controlador controlador) {
		this.ventanaPrincipal = ventanaPrincipal;
		this.controlador = controlador;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 706, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cbTipoConsulta.setModel(new DefaultComboBoxModel<String>(new String[] {"Selecciona una opci\u00F3n", "Dia de hoy", "Elegir un rango de fechas"}));
		cbTipoConsulta.setBounds(76, 144, 252, 20);
		contentPane.add(cbTipoConsulta);
		
		lblFechaInicio = new JLabel("");
		lblFechaInicio.setBounds(60, 314, 119, 20);
		contentPane.add(lblFechaInicio);
		
		lblVentas = new JLabel("");
		lblVentas.setBounds(497, 178, 138, 33);
		contentPane.add(lblVentas);
		
		lblCompras = new JLabel("");
		lblCompras.setBounds(497, 235, 138, 33);
		contentPane.add(lblCompras);
		
		lblGanancias = new JLabel("");
		lblGanancias.setBounds(496, 295, 138, 33);
		contentPane.add(lblGanancias);
		
		lblFechaFin = new JLabel("");
		lblFechaFin.setBounds(0, 0, 119, 20);
		contentPane.add(lblFechaFin);
		
		fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(consultorContabilidad.class.getResource("/prototipos/reportesContabilidad.png")));
		fondo.setHorizontalTextPosition(SwingConstants.CENTER);
		fondo.setBounds(0, 11, 690, 500);
		contentPane.add(fondo);
		
		btnBtnbuscarrangofecha = new JButton("btnBuscarRangoFecha");
		btnBtnbuscarrangofecha.setOpaque(false);
		btnBtnbuscarrangofecha.setBounds(256, 328, 58, 45);
		contentPane.add(btnBtnbuscarrangofecha);
		
		btnCalendfinicio = new JButton("calendFInicio");
		btnCalendfinicio.setOpaque(false);
		btnCalendfinicio.setBounds(194, 302, 29, 33);
		contentPane.add(btnCalendfinicio);
		
		btnCalendarffin = new JButton("calendarFFin");
		btnCalendarffin.setBounds(194, 362, 29, 33);
		contentPane.add(btnCalendarffin);
		
		btnFinalizarconsulta = new JButton("finalizarconsulta");
		btnFinalizarconsulta.setOpaque(false);
		btnFinalizarconsulta.setBounds(267, 447, 149, 39);
		contentPane.add(btnFinalizarconsulta);
		
		btnEnviarxmail = new JButton("enviarXmail");
		btnEnviarxmail.setOpaque(false);
		btnEnviarxmail.setBounds(410, 367, 41, 39);
		contentPane.add(btnEnviarxmail);
		
		btnImprimircons = new JButton("imprimirCons");
		btnImprimircons.setOpaque(false);
		btnImprimircons.setBounds(549, 367, 58, 45);
		contentPane.add(btnImprimircons);
	}
	
	public void resetearCampos(){
		lblCompras.setText("");
		lblFechaFin.setText("");
		lblFechaInicio.setText("");
		lblGanancias.setText("");
		lblVentas.setText("");
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
	
	
	
}