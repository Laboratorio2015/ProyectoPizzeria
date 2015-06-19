package Cocina;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import dto.ItemDTO;
import dto.PedidoDTO;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class MonitorThread extends Thread {

	private JFrame frame;
	//private PadreMonitor padre;
	//TABLAS
	private JTable tablaPedidos;
	private JTable tablaPizzas;
	private JTable tablaEmpanadas;
	//Modelo
	private DefaultTableModel modeloPedidos;
	private DefaultTableModel modeloPizzasFaltantes;
	private DefaultTableModel modeloEmpanadasFaltantes;
	private final JLabel fondo = new JLabel("");
	//private DefaultTableCellRenderer alinearCentro;
	@SuppressWarnings({ "serial", "static-access" })
	public MonitorThread() {

		//this.padre = padre;
		/// GENERO FRAME Y LABEL CONTENIDO
				frame = new JFrame();
				frame.getContentPane().setBackground(Color.LIGHT_GRAY);
				frame.setBounds(250, 150, 1221, 630);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setLayout(null);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBackground(new Color(204, 153, 102));
				scrollPane.setBounds(47, 99, 536, 468);
				frame.getContentPane().add(scrollPane);
				
				//TABLA Y MODELO DE PEDIDOS
				tablaPedidos = new JTable();
				tablaPedidos.setShowHorizontalLines(false);
				tablaPedidos.setRowSelectionAllowed(false);
				tablaPedidos.setForeground(new Color(0, 0, 0));
				tablaPedidos.setBackground(Color.LIGHT_GRAY);
				modeloPedidos = new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Nº Pedido", "Producto", "Cantidad", "Comentario"
						}
					) {
						@SuppressWarnings("rawtypes")
						Class[] columnTypes = new Class[] {
							String.class, String.class, Integer.class, String.class
						};
						@SuppressWarnings({ "unchecked", "rawtypes" })
						public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
						boolean[] columnEditables = new boolean[] {
							false, false, false, false
						};
						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
				};

				 JTableHeader headerPedidos = tablaPedidos.getTableHeader();
				 headerPedidos.setFont(new Font("Tahoma", Font.BOLD, 13));
				 headerPedidos.setForeground(new Color(0).RED);
				 headerPedidos.setBackground(new Color(0).gray);
				//
				tablaPedidos.setModel(modeloPedidos);
				tablaPedidos.getColumnModel().getColumn(0).setMaxWidth(68);
				tablaPedidos.getColumnModel().getColumn(1).setMaxWidth(200);
				tablaPedidos.getColumnModel().getColumn(2).setMaxWidth(55);
				tablaPedidos.getColumnModel().getColumn(3).setMaxWidth(300);
				tablaPedidos.setFont(new Font("Verdana", Font.BOLD, 17));
				//Centrat texto
				DefaultTableCellRenderer alinearCentro = new DefaultTableCellRenderer();
		         alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
		         tablaPedidos.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);
		         tablaPedidos.getColumnModel().getColumn(2).setCellRenderer(alinearCentro);
				//	
				scrollPane.setViewportView(tablaPedidos);
				////
				
				//// TABLA Y MODELO DE PIZZA
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBackground(new Color(204, 153, 102));
				scrollPane_1.setBounds(886, 140, 284, 415);
				frame.getContentPane().add(scrollPane_1);
				tablaPizzas = new JTable();
				tablaPizzas.setShowHorizontalLines(false);
				tablaPizzas.setRowSelectionAllowed(false);
				tablaPizzas.setForeground(new Color(0, 0, 0));
				tablaPizzas.setBackground(Color.LIGHT_GRAY);
				modeloPizzasFaltantes = new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Producto", "Cantidad", "Coment.?", "#Coment."
						}
						) {
					@SuppressWarnings("rawtypes")
					Class[] columnTypes = new Class[] {
						String.class, Integer.class, String.class, Integer.class
					};
					@SuppressWarnings({ "unchecked", "rawtypes" })
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				};
				
				 JTableHeader headerPizzas = tablaPizzas.getTableHeader();
				 headerPizzas.setFont(new Font("Tahoma", Font.BOLD, 13));
				 headerPizzas.setForeground(new Color(0).RED);
				 headerPizzas.setBackground(new Color(0).gray);
				
				tablaPizzas.setModel(modeloPizzasFaltantes);
				tablaPizzas.getColumnModel().getColumn(0).setMaxWidth(200);
				tablaPizzas.getColumnModel().getColumn(1).setMaxWidth(55);
				tablaPizzas.getColumnModel().getColumn(2).setMaxWidth(60);
				tablaPizzas.getColumnModel().getColumn(3).setWidth(0);
				tablaPizzas.getColumnModel().getColumn(3).setMinWidth(0);
				tablaPizzas.getColumnModel().getColumn(3).setMaxWidth(2);
				tablaPizzas.setFont(new Font("Verdana", Font.BOLD, 17));
				//Centrar texto
				alinearCentro = new DefaultTableCellRenderer();
				alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
				tablaPizzas.getColumnModel().getColumn(1).setCellRenderer(alinearCentro);
				tablaPizzas.getColumnModel().getColumn(2).setCellRenderer(alinearCentro);
				tablaPizzas.getColumnModel().getColumn(3).setCellRenderer(alinearCentro);
				//	
				scrollPane_1.setViewportView(tablaPizzas);
	
				//// TABLA Y MODELO DE EMPANADAS	
				JScrollPane scrollPane_21 = new JScrollPane();
				scrollPane_21.setBackground(new Color(204, 153, 102));
				scrollPane_21.setBounds(598, 140, 284, 415);
				frame.getContentPane().add(scrollPane_21);
				tablaEmpanadas = new JTable();
				tablaEmpanadas.setShowHorizontalLines(false);
				tablaEmpanadas.setRowSelectionAllowed(false);
				tablaEmpanadas.setFont(new Font("Verdana", Font.BOLD, 17));
				tablaEmpanadas.setForeground(new Color(0, 0, 0));
				tablaEmpanadas.setBackground(Color.LIGHT_GRAY);
				modeloEmpanadasFaltantes = new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Producto", "Cantidad", "Coment.?", "cantComentarios"
					}
				) {
					@SuppressWarnings("rawtypes")
					Class[] columnTypes = new Class[] {
						String.class, Integer.class, String.class, Integer.class
					};
					@SuppressWarnings({ "unchecked", "rawtypes" })
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				};
				
				 JTableHeader headerEmpanada = tablaEmpanadas.getTableHeader();
				 headerEmpanada.setFont(new Font("Tahoma", Font.BOLD, 13));
				 headerEmpanada.setForeground(new Color(0).RED);
				 headerEmpanada.setBackground(new Color(0).gray);
				
				tablaEmpanadas.setModel(modeloEmpanadasFaltantes);
				tablaEmpanadas.getColumnModel().getColumn(0).setMaxWidth(200);
				tablaEmpanadas.getColumnModel().getColumn(1).setMaxWidth(55);
				tablaEmpanadas.getColumnModel().getColumn(2).setMaxWidth(60);
				tablaEmpanadas.getColumnModel().getColumn(3).setWidth(0);
				tablaEmpanadas.getColumnModel().getColumn(3).setMinWidth(0);
				tablaEmpanadas.getColumnModel().getColumn(3).setMaxWidth(2);
				//Centrar texto
				alinearCentro = new DefaultTableCellRenderer();
				alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
				tablaEmpanadas.getColumnModel().getColumn(1).setCellRenderer(alinearCentro);
				tablaEmpanadas.getColumnModel().getColumn(2).setCellRenderer(alinearCentro);
				tablaEmpanadas.getColumnModel().getColumn(3).setCellRenderer(alinearCentro);
				scrollPane_21.setViewportView(tablaEmpanadas);
				
				fondo.setIcon(new ImageIcon("C:\\Users\\Yanina\\Documents\\Elab de constr de Soft\\tp pizzeria\\Gui\\Recursos\\Editados\\Ventanas\\fondoMonitor.png"));
				fondo.setBounds(0, 0, 1205, 591);
				frame.getContentPane().add(fondo);
				//FIN/// 		
		}

	@Override
	public void run() {
		frame.setVisible(true);
	}
	public void agregarPedido(PedidoDTO pedido){
		/**	Toma el atributo listadoItems, y por cada item llama al metodo agregarItemPedido(Item),
		 * y luego invoca al metodo agregarFaltante(item) con el mismo item ya seleccionado.
		 * Luego toma el atributo listadoPromociones, y examina cada objeto Promocion.
		 * De dicho objeto obtiene un listadoItems, que representan los productos que componen la promocion.
		 * Nuevamente con cada item componente, llama al metodo:  agregarItemPedido(Item). 
		 * Si tiene productos, el primer item del primer pedido, usara el método : 
		agregarItemPedido(nroPedido, Item). Para indicar que a partir de ahi, los elementos corresponden a dicho nro de pedido. 
		Si no tiene productos, el primer item de la primera promocion cargada será quien use el método:
		agregarItemPedido(nroPedido, Item)- para indicar que a partir de alli, los items mostrados corresponden a dicho nro de pedido.
		y luego invoca al metodo agregarFaltante(item) con el mismo item ya seleccionado.
		 */
		if (pedido.getProductos() == null && pedido.getOfertas() == null){
			//throw new Exception("EL pedido recibido no tiene productos ni promociones cargadas.");
			System.out.println("EL pedido recibido no tiene productos ni promociones cargadas.");
		}
		else{
			if (pedido.getEstado().compareTo("solicitado")==0){
				boolean bandera = false;
				if (pedido.getProductos()!=null){
					correguirComentarios(pedido);
					for (int i = 0; i < pedido.getProductos().size(); i++){
						if (pedido.getProductos().get(i).getProducto().getTipo().compareTo("otros")!= 0){
							if (bandera == false){
								agregarItemPedido(pedido.getIdpedido(), pedido.getProductos().get(i));
								bandera=true;
							}
							else{
								agregarItemPedido(pedido.getProductos().get(i));
							}
							agregarItemFaltante( pedido.getProductos().get(i));
						}
					}
				}
				if (pedido.getOfertas()!=null){
					for (int i = 0; i < pedido.getOfertas().size(); i++){
						for (int x = 0; x < pedido.getOfertas().get(i).getProductosOfertados().size(); x++){				
							if (pedido.getOfertas().get(i).getProductosOfertados().get(x).getProducto().getTipo().compareTo("otros")!= 0){
								if (bandera == false){
									agregarItemPedido(pedido.getIdpedido(), pedido.getOfertas().get(i).getProductosOfertados().get(x));
									bandera=true;
								}
								else{
									agregarItemPedido(pedido.getOfertas().get(i).getProductosOfertados().get(x));
								}
								agregarItemFaltante(pedido.getOfertas().get(i).getProductosOfertados().get(x));
							}	
						}
					}

				}
			}
		}
	}
	
	private void correguirComentarios(PedidoDTO pedido) {
		for (int i = 0; i < pedido.getItems().size(); i++){	
			if(pedido.getItems().get(i).getComentario()!=null)
				pedido.getItems().get(i).setComentario(pedido.getItems().get(i).getComentario().trim());
			else
				pedido.getItems().get(i).setComentario(" ");
		}
	}

	public void quitarPedido(PedidoDTO pedido){
		/**Toma el atributo listadoItems, y por cada item llama al metodo quitarItemFalntante(Item)
		y luego invoca al metodo modeloPedidos.removeRow(getIntFilaProducto(Item,modeloPedidos)).
		Luego toma el atributo listadoPromociones, y examina cada objeto Promocion. 
		De dicho objeto obtiene un listadoItems, que representan los productos que componen la promocion.
		Nuevamente con cada item componente, llama al metodo:  modeloPedidos.removeRow(getIntFilaProducto(Item,modeloPedidos)) 
		y al metodo quitarItemFalntante(Item).*/
			if (pedido.getProductos() == null && pedido.getOfertas() == null || !estaContenido(pedido)){
				//throw new Exception("EL pedido recibido no tiene productos ni promociones cargadas.");
				System.out.println("EL pedido recibido no tiene productos ni promociones cargadas o ya fue quitado del listado");
			}
			else{
				if (pedido.getProductos()!=null){
					for (int i = 0; i < pedido.getProductos().size(); i++){
						if (pedido.getProductos().get(i).getProducto().getTipo().compareTo("otros")!= 0){
							quitarItemFaltante(pedido.getProductos().get(i));
							modeloPedidos.removeRow(getIntFilaProducto(pedido.getProductos().get(i)));
						}
					}
				}
				if (pedido.getOfertas()!=null){
					for (int i = 0; i < pedido.getOfertas().size(); i++){
						for (int x = 0; x < pedido.getOfertas().get(i).getProductosOfertados().size(); x++){				
							if (pedido.getOfertas().get(i).getProductosOfertados().get(x).getProducto().getTipo().compareTo("otros")!= 0){
								quitarItemFaltante(pedido.getOfertas().get(i).getProductosOfertados().get(x));
								modeloPedidos.removeRow(getIntFilaProducto(pedido.getOfertas().get(i).getProductosOfertados().get(x)));
							}	
						}
					}
				}
			}
		}		
	
	private boolean estaContenido(PedidoDTO pedido) {
		for (Integer i = 0; i < modeloPedidos.getRowCount(); i++){
			if ( modeloPedidos.getValueAt(i, 0).toString().compareTo(pedido.getIdpedido().toString()) == 0){
				return true;
			}
		}
		return false;
	}

	private	void agregarItemPedido(ItemDTO item ){
		/**Recibe el elemento item y agrega al modeloPedidos, los atributos del item : "",nombreProducto,Cantidad,Comentario. */
		modeloPedidos.addRow(new Object[] {"", item.getProducto().getNombre(), item.getCantidad(), item.getComentario()});
	}
	
	private	void agregarItemPedido(Integer nroPedido, ItemDTO item ){
		/**Recibe el elemento item y agrega al modeloPedidos, los atributos del item : nroPedido, nombreProducto,Cantidad,Comentario.  */
		modeloPedidos.addRow(new Object[] {String.valueOf(nroPedido), item.getProducto().getNombre(), item.getCantidad(), item.getComentario()});
	}	
	
	public void agregarItemFaltante(ItemDTO item){
		/**SOLO RECIBE ITEM DE TIPO EMPANADA O PIZZA
		 * Primero ubica el modelo al que corresponde. Luego chekea si el producto ya existe en el modelo.
		 *  Sino existe lo agrega, y en el campo comentario pone un 0, si el producto no tiene comentarios, 
		 *  y pone un 1 si el producto tiene comentarios. Este valor se guardaria en un campo de tipo String, por lo cual, 
		 *  hay que usar un parseo para guardarlo.
		Si ya existe, averigua mediante metodo: getIntFilaProducto(Item)> INT, en que fila esta 
		y luego suma al campo setValueAt(getIntFilaProducto,(col)1) el valor ya existente mas el valor del campo Cantidad del Item recibido. 
		Luego revisa si el Item tiene comentarios, si tiene comentarios realiza la suma +1 Y lo guarda setValueAt(getIntFilaProducto(Item), (col)3)*/
		if (item != null){
			DefaultTableModel modeloCorrespondiente = modeloPizzasFaltantes;
			if (item.getProducto().getTipo().compareTo("empanada") == 0){
				modeloCorrespondiente = modeloEmpanadasFaltantes;
			}
			Integer indiceFaltante =  getIntFilaProducto(item, modeloCorrespondiente);
			if (indiceFaltante == -1){ // NO ESTA EN EL MODELO DE FALTANTE 
				Integer cantComentario =0;
				String charComentario = "No";
				if (item.getComentario().trim().compareTo("")!= 0){
					cantComentario++;
					charComentario = "Si";
				}
				modeloCorrespondiente.addRow(new Object [] {item.getProducto().getNombre(), item.getCantidad(), charComentario, cantComentario});
			}
			else{
				//Reemplazo la cantidad de faltante
				modeloCorrespondiente.setValueAt((Integer) modeloCorrespondiente.getValueAt(indiceFaltante, 1) + item.getCantidad(), indiceFaltante, 1);
				if (item.getComentario().trim().compareTo("")!= 0){
					//CHEQUEO Y CONTABILIZO COMENTARIOS
					if ( (Integer) modeloCorrespondiente.getValueAt(indiceFaltante, 3) == 0){
						modeloCorrespondiente.setValueAt("Si", indiceFaltante, 2);
					}
					modeloCorrespondiente.setValueAt((Integer) modeloCorrespondiente.getValueAt(indiceFaltante, 3) + 1, indiceFaltante, 3);
				}
			}
		}
		else{
			//throw new Exception("El item recibido es nulo.");
			System.out.println("El item recibido es nulo.");
		}
	}
	
	private void quitarItemFaltante(ItemDTO item){
		/**Primero ubica el modelo al que corresponde. 
		 * Luego chekea si el producto ya existe en el modelo. 
		 * Sino existe arroja una exepcion o syso. Si ya existe, averigua mediante metodo: getIntFilaProducto(Item)> INT, 
		 * en que fila esta y luego resta al campo setValueAt(getIntFilaProducto,(col)1) el valor ya existente 
		 * menos el valor del campo Cantidad del Item recibido. 
		 * Luego revisa si el Item tiene comentarios, si tiene comentarios realiza la resta 1 y guarda el string retornante en>
		 *  setValueAt(getIntFilaProducto(Item), (col)2).*/
		if (item != null){	
			DefaultTableModel modeloCorrespondiente = modeloPizzasFaltantes;
			if (item.getProducto().getTipo().compareTo("empanada") == 0){
				modeloCorrespondiente = modeloEmpanadasFaltantes;
			}
			Integer indiceFaltante =  getIntFilaProducto(item, modeloCorrespondiente);
			if (indiceFaltante == -1){ // NO ESTA EN EL MODELO DE FALTANTE 
				//throw new Exception("El item que se pretende descontar no existe en el modelo correspondiente.");
				System.out.println("El item que se pretende descontar no existe en el modelo correspondiente.");
			}
			else{
				Integer faltanteDescontado = (Integer) modeloCorrespondiente.getValueAt(indiceFaltante, 1) - item.getCantidad();
				if (faltanteDescontado == 0){
					modeloCorrespondiente.removeRow(indiceFaltante);
				}
				else{
					modeloCorrespondiente.setValueAt(faltanteDescontado, indiceFaltante, 1);
					if (item.getComentario()!=null&&item.getComentario().trim().compareTo("")!= 0){ // si tiene comentarios
						modeloCorrespondiente.setValueAt((Integer)modeloCorrespondiente.getValueAt(indiceFaltante, 3) - 1, indiceFaltante, 3);
						if ( (Integer) modeloCorrespondiente.getValueAt(indiceFaltante, 3) == 0){
							modeloCorrespondiente.setValueAt("No", indiceFaltante, 2);
						}		
					}
				}
			}
		}
		else{
			//throw new Exception("El item que se pretende quitar es nulo.");
			System.out.println("El item que se pretende quitar es nulo.");
		}
	}
	
	private Integer getIntFilaProducto(ItemDTO item, DefaultTableModel modeloCorrespondiente){
		/**Busca con un for de 0 a getRowCount() con la funcion de compare to el valor de getValueAt(fila i,col 0).
		 * columna fija, ya que siempre busca en el nombre del producto.
		 *  Cuando el compare to da 0, devuelve el i correspondiente. Sino esta devuelve -1.
		 */
		for (Integer i = 0; i < modeloCorrespondiente.getRowCount(); i++){
			if ( modeloCorrespondiente.getValueAt(i, 0).toString().compareTo(item.getProducto().getNombre()) == 0){
				return i;
			}
		}
		return -1;
	}
	
	private Integer getIntFilaProducto(ItemDTO item) {
		for (Integer i = 0; i < modeloPedidos.getRowCount(); i++){
			if ( modeloPedidos.getValueAt(i, 1).toString().compareTo(item.getProducto().getNombre()) == 0){
				return i;
			}
		}
		return -1;
	}
	
	public void actualizar(){
		/**Setea los tres modelos en las tres tablas correspondientes.*/
		tablaPedidos.setModel(modeloPedidos);
		tablaEmpanadas.setModel(modeloEmpanadasFaltantes);
		tablaPizzas.setModel(modeloPizzasFaltantes);
		}
	
}
