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

import presentacion.controlador.Controlador;

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


	public consultoEstadistica(VentanaPrincipal ventana, Controlador control) {
		setBounds(100, 100, 715, 537);
		this.control=control;
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		cbEstadisticas= new JComboBox();
		cbEstadisticas.setBounds(76, 131, 250, 22);
		contentPanel.add(cbEstadisticas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(398, 134, 244, 280);
		contentPanel.add(scrollPane);
		
		model = new DefaultTableModel(null,nombreColumnas);
		table = new JTable(model){
		    @Override
			public boolean isCellEditable(int rowIndex, int colIndex) {
		        return false; //desabilita la edicion de las celdas
		    }
		};
		scrollPane.setViewportView(table);
		
		tfFechaInicio = new JTextField();
		tfFechaInicio.setEditable(false);
		tfFechaInicio.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		tfFechaInicio.setBackground(new Color(153, 153, 0));
		tfFechaInicio.setBounds(83, 184, 117, 19);
		contentPanel.add(tfFechaInicio);
		tfFechaInicio.setColumns(10);
		
		tfFechaFin = new JTextField();
		tfFechaFin.setEditable(false);
		tfFechaFin.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		tfFechaFin.setBackground(new Color(153, 153, 0));
		tfFechaFin.setColumns(10);
		tfFechaFin.setBounds(84, 239, 117, 19);
		contentPanel.add(tfFechaFin);
		
		tfOcultaRangoFechas = new JTextField();
		tfOcultaRangoFechas.setEnabled(false);
		tfOcultaRangoFechas.setEditable(false);
		tfOcultaRangoFechas.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		tfOcultaRangoFechas.setBackground(new Color(153, 153, 0));
		tfOcultaRangoFechas.setBounds(62, 160, 294, 107);
		contentPanel.add(tfOcultaRangoFechas);
		tfOcultaRangoFechas.setColumns(10);
		
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


	public JButton getBtnBuscar() {
		return btnBuscar;
	}


	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}
	
}
