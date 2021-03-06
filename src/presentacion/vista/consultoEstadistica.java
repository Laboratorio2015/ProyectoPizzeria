package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import presentacion.controlador.Controlador;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class consultoEstadistica extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private  String[] nombreColumnas = {};
	private JTextField tfFechaInicio;
	private JTextField tfFechaFin;
	private JTextField tfOcultaRangoFechas;
	private JComboBox cbEstadisticas;
	private JButton btnImprimir;
	private JButton btnEnviarPorEmail;
	private JButton btnCalendarioInicio;
	private JButton btnCalendarioFin;
	private JButton btnBuscar;
	private Controlador control;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField tfOcultarTop;
	private JCheckBox Top5;
	private JCheckBox verTodo;
	private String fechaInicio;
	private Integer a�oInicio;
	private Integer mesInicio;
	private String fechaFin;
	private Integer a�oFin;
	private Integer mesFin;
	private Integer diaFin;
	private Integer diaInicio;


	public consultoEstadistica(VentanaPrincipal ventana, Controlador control) {
		setBounds(100, 100, 715, 537);
		this.control=control;
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		cbEstadisticas= new JComboBox();
		cbEstadisticas.setModel(new DefaultComboBoxModel(new String[] {"(Seleccione una estadistica)", "Empanadas mas compradas", "Empanadas menos compradas","Pizzas mas compradas", "Pizzas menos compradas", "Ofertas mas compradas", "Ofertas menos compradas"}));
		cbEstadisticas.setBounds(76, 131, 250, 22);
		contentPanel.add(cbEstadisticas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(398, 134, 244, 240);
		scrollPane.getViewport().setBackground(new Color(153, 153, 0));
		scrollPane.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		contentPanel.add(scrollPane);
		
		model = new DefaultTableModel(null,nombreColumnas);
		table = new JTable(model){
		    @Override
			public boolean isCellEditable(int rowIndex, int colIndex) {
		        return false; //desabilita la edicion de las celdas
		    }
		};
		scrollPane.setViewportView(table);
		table.setForeground(new Color(255, 255, 255));
		table.setBackground(new Color(153, 153, 0));
		scrollPane.getViewport().setBackground(new Color(153, 153, 0));
		//encabezado
		JTableHeader headerPedidos = table.getTableHeader();
		headerPedidos.setFont(new Font("Tahoma", Font.BOLD, 13));
		headerPedidos.setForeground(new Color(153, 153, 0));
		headerPedidos.setBackground(new Color(0).GRAY);
		
		tfFechaInicio = new JTextField();
		tfFechaInicio.setHorizontalAlignment(SwingConstants.CENTER);
		tfFechaInicio.setFont(new Font("Calibri", Font.BOLD, 14));
		tfFechaInicio.setForeground(Color.LIGHT_GRAY);
		tfFechaInicio.setEditable(false);
		tfFechaInicio.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		tfFechaInicio.setBackground(new Color(153, 153, 0));
		tfFechaInicio.setBounds(80, 182, 115, 19);
		contentPanel.add(tfFechaInicio);
		tfFechaInicio.setColumns(10);
		
		tfFechaFin = new JTextField();
		tfFechaFin.setHorizontalAlignment(SwingConstants.CENTER);
		tfFechaFin.setFont(new Font("Calibri", Font.BOLD, 14));
		tfFechaFin.setForeground(Color.LIGHT_GRAY);
		tfFechaFin.setEditable(false);
		tfFechaFin.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		tfFechaFin.setBackground(new Color(153, 153, 0));
		tfFechaFin.setColumns(10);
		tfFechaFin.setBounds(80, 237, 117, 19);
		contentPanel.add(tfFechaFin);
		
		tfOcultaRangoFechas = new JTextField();
		tfOcultaRangoFechas.setEnabled(false);
		tfOcultaRangoFechas.setEditable(false);
		tfOcultaRangoFechas.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		tfOcultaRangoFechas.setBackground(new Color(153, 153, 0));
		tfOcultaRangoFechas.setBounds(34, 160, 325, 273);
		contentPanel.add(tfOcultaRangoFechas);
		tfOcultaRangoFechas.setColumns(10);
		
		tfOcultarTop = new JTextField();
		tfOcultarTop.setEnabled(false);
		tfOcultarTop.setEditable(false);
		tfOcultarTop.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		tfOcultarTop.setBackground(new Color(153, 153, 0));
		tfOcultarTop.setBounds(408, 391, 234, 35);
		contentPanel.add(tfOcultarTop);
		tfOcultarTop.setColumns(10);
		
		Top5= new JCheckBox("Top 5");
		buttonGroup.add(Top5);
		Top5.setFont(new Font("Calibri", Font.BOLD, 14));
		Top5.setForeground(Color.LIGHT_GRAY);
		Top5.setBackground(new Color(153, 153, 0));
		Top5.setBounds(437, 393, 73, 23);
		contentPanel.add(Top5);
		
		verTodo= new JCheckBox("Ver Todos");
		buttonGroup.add(verTodo);
		verTodo.setFont(new Font("Calibri", Font.BOLD, 14));
		verTodo.setForeground(Color.LIGHT_GRAY);
		verTodo.setBackground(new Color(153, 153, 0));
		verTodo.setBounds(521, 393, 85, 23);
		contentPanel.add(verTodo);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(consultoEstadistica.class.getResource("/prototipos/reportesEstadistica.png")));
		lblNewLabel.setBounds(0, 0, 700, 500);
		contentPanel.add(lblNewLabel);
		{
			JButton btnFinalizar = new JButton("Cancel");
			btnFinalizar.setOpaque(false);
			btnFinalizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0)
				{
					dispose();
				}
			});
			{
				btnImprimir= new JButton("OK");
				btnImprimir.setOpaque(false);
				btnImprimir.setBounds(69, 325, 53, 48);
				contentPanel.add(btnImprimir);
				btnImprimir.setActionCommand("OK");
				getRootPane().setDefaultButton(btnImprimir);
			}
			btnFinalizar.setBounds(278, 448, 149, 35);
			contentPanel.add(btnFinalizar);
			btnFinalizar.setActionCommand("Cancel");
		}
		
		btnEnviarPorEmail= new JButton("OK");
		btnEnviarPorEmail.setOpaque(false);
		btnEnviarPorEmail.setActionCommand("OK");
		btnEnviarPorEmail.setBounds(69, 384, 41, 41);
		contentPanel.add(btnEnviarPorEmail);
		
		btnCalendarioInicio= new JButton("New button");
		btnCalendarioInicio.setOpaque(false);
		btnCalendarioInicio.setBounds(217, 176, 25, 29);
		contentPanel.add(btnCalendarioInicio);
		
		btnCalendarioFin= new JButton("New button");
		btnCalendarioFin.setOpaque(false);
		btnCalendarioFin.setBounds(217, 238, 25, 29);
		contentPanel.add(btnCalendarioFin);
		
		btnBuscar= new JButton("New button");
		btnBuscar.setOpaque(false);
		btnBuscar.setBounds(278, 201, 54, 29);
		contentPanel.add(btnBuscar);
	}


	public JTable getTable() {
		return table;
	}


	public void setTable(JTable table) {
		this.table = table;
	}


	public DefaultTableModel getModel() {
		return model;
	}


	public void setModel(DefaultTableModel model) {
		this.model = model;
	}


	public String[] getNombreColumnas() {
		return nombreColumnas;
	}
	
	public JCheckBox getTop5() {
		return Top5;
	}


	public void setTop5(JCheckBox top5) {
		Top5 = top5;
	}


	public JCheckBox getVerTodo() {
		return verTodo;
	}


	public void setVerTodo(JCheckBox verTodo) {
		this.verTodo = verTodo;
	}


	public void setNombreColumnas(String[] nombreColumnas) {
		this.nombreColumnas = nombreColumnas;
	}


	public JTextField getTfFechaInicio() {
		return tfFechaInicio;
	}


	public void setTfFechaInicio(JTextField tfFechaInicio) {
		this.tfFechaInicio = tfFechaInicio;
	}


	public JTextField getTfFechaFin() {
		return tfFechaFin;
	}


	public void setTfFechaFin(JTextField tfFechaFin) {
		this.tfFechaFin = tfFechaFin;
	}


	public JTextField getTfOcultaRangoFechas() {
		return tfOcultaRangoFechas;
	}


	public void setTfOcultaRangoFechas(JTextField tfOcultaRangoFechas) {
		this.tfOcultaRangoFechas = tfOcultaRangoFechas;
	}


	public JComboBox getCbEstadisticas() {
		return cbEstadisticas;
	}


	public void setCbEstadisticas(JComboBox cbEstadisticas) {
		this.cbEstadisticas = cbEstadisticas;
	}


	public JButton getBtnImprimir() {
		return btnImprimir;
	}


	public void setBtnImprimir(JButton btnImprimir) {
		this.btnImprimir = btnImprimir;
	}


	public JButton getBtnEnviarPorEmail() {
		return btnEnviarPorEmail;
	}


	public void setBtnEnviarPorEmail(JButton btnEnviarPorEmail) {
		this.btnEnviarPorEmail = btnEnviarPorEmail;
	}


	public JButton getBtnCalendarioInicio() {
		return btnCalendarioInicio;
	}


	public void setBtnCalendarioInicio(JButton btnCalendarioInicio) {
		this.btnCalendarioInicio = btnCalendarioInicio;
	}


	public JButton getBtnCalendarioFin() {
		return btnCalendarioFin;
	}


	public void setBtnCalendarioFin(JButton btnCalendarioFin) {
		this.btnCalendarioFin = btnCalendarioFin;
	}

	public JTextField getTfOcultarTop() {
		return tfOcultarTop;
	}

	public void setTfOcultarTop(JTextField tfOcultarTop) {
		this.tfOcultarTop = tfOcultarTop;
	}


	public JButton getBtnBuscar() {
		return btnBuscar;
	}
		
	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
		//guardo dia, mes a�o por separado.
		
		String dia = "";
		String a�o = "";
		String mes = "";
		
		for (int i=fechaInicio.length()-1; i >= 0; i--){
			if (i > fechaInicio.length()-5){ // 4 espaciosdela�o, un espacio del guion, menos uno del largo q comienza en 1 y array en 0
				a�o = fechaInicio.charAt(i) + a�o;
			}
			else if (i == fechaInicio.length()-5){ // 4 espaciosdela�o, un espacio del guion, menos uno del largo q comienza en 1 y array en 0
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
		a�oInicio = Integer.parseInt(a�o);
		mesInicio = Integer.parseInt(mes);
		diaInicio = Integer.parseInt(dia);
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
		//guardo dia, mes a�o por separado.
		String dia = "";
		String a�o = "";
		String mes = "";
		
		for (int i=fechaFin.length()-1; i >= 0; i--){
			if (i > fechaFin.length()-5){ // 4 espaciosdela�o, un espacio del guion, menos uno del largo q comienza en 1 y array en 0
				a�o = fechaFin.charAt(i) + a�o;
			}
			else if (i == fechaFin.length()-5){ // 4 espaciosdela�o, un espacio del guion, menos uno del largo q comienza en 1 y array en 0
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
		a�oFin = Integer.parseInt(a�o);
		mesFin = Integer.parseInt(mes);
		diaFin = Integer.parseInt(dia);
	}


	public Integer getA�oInicio() {
		return a�oInicio;
	}


	public void setA�oInicio(Integer a�oInicio) {
		this.a�oInicio = a�oInicio;
	}


	public Integer getMesInicio() {
		return mesInicio;
	}


	public void setMesInicio(Integer mesInicio) {
		this.mesInicio = mesInicio;
	}


	public Integer getA�oFin() {
		return a�oFin;
	}


	public void setA�oFin(Integer a�oFin) {
		this.a�oFin = a�oFin;
	}


	public Integer getMesFin() {
		return mesFin;
	}


	public void setMesFin(Integer mesFin) {
		this.mesFin = mesFin;
	}


	public Integer getDiaFin() {
		return diaFin;
	}


	public void setDiaFin(Integer diaFin) {
		this.diaFin = diaFin;
	}


	public Integer getDiaInicio() {
		return diaInicio;
	}


	public void setDiaInicio(Integer diaInicio) {
		this.diaInicio = diaInicio;
	}


	public String getFechaInicio() {
		return fechaInicio;
	}


	public String getFechaFin() {
		return fechaFin;
	}


		
}
