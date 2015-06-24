package presentacion.vista;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import presentacion.controlador.Controlador;
import org.freixas.jcalendar.JCalendar;
import javax.swing.border.BevelBorder;
import java.awt.Cursor;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.chrono.JapaneseDate;
import java.util.Calendar;
import java.util.Date;
import java.awt.Color;

//public class calendarioSelectFecha {
//
//}
public class calendarioSelectFecha extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Controlador controlador;
	private VentanaPrincipal ventanaPrincipal;
	private JCalendar calendar;
	private JButton btnSeleccionar;
	private String indicadorFecha;
	
	public calendarioSelectFecha(VentanaPrincipal ventanaPrincipal, Controlador controlador, String indicadorFecha) {
		setBackground(new Color(153, 204, 153));
		setTitle("Seleccione una fecha");
		this.ventanaPrincipal = ventanaPrincipal;
		this.controlador = controlador;
		this.indicadorFecha = indicadorFecha;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 322, 250);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		calendar = new JCalendar();
		calendar.setBackground(new Color(153, 204, 153));
		calendar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		calendar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		calendar.setBounds(0, 23, 301, 139);
		contentPane.add(calendar);
		
		btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setBounds(108, 177, 89, 23);
		contentPane.add(btnSeleccionar);
		Date fecha  = calendar.getDate();
		
	}
	
	public String getFechaSeleccionada(){
		Date date = calendar.getDate();
		String dia = "";
		String año = "";
		String mes = "";
		SimpleDateFormat dateformatter = new SimpleDateFormat("d-M-yyyy");
		String dateFormateado = dateformatter.format(date.getTime());
		
		for (int i=dateFormateado.length()-1; i >= 0; i--){
			if (i > dateFormateado.length()-5){ // 4 espaciosdelaño, un espacio del guion, menos uno del largo q comienza en 1 y array en 0
				año = dateFormateado.charAt(i) + año;
			}
			else if (i == dateFormateado.length()-5){ // 4 espaciosdelaño, un espacio del guion, menos uno del largo q comienza en 1 y array en 0
			}
			else if ( (i != 0 && i !=1) && dateFormateado.charAt(i)!='-'){
				mes = dateFormateado.charAt(i) + mes;
			}
			else{
				if (dateFormateado.charAt(i)!='-'){
					dia = dateFormateado.charAt(i) + dia;
				}
			}
		} 
		return dia + "-" + mes + "-" + año;
	}

	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}

	public void setBtnSeleccionar(JButton btnSeleccionar) {
		this.btnSeleccionar = btnSeleccionar;
	}

	public String getIndicadorFecha() {
		return indicadorFecha;
	}

	public void setIndicadorFecha(String indicadorFecha) {
		this.indicadorFecha = indicadorFecha;
	}

	public Date getFechaDate() {
		return calendar.getDate();
	}
}
