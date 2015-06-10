package Cocina;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import dto.ItemDTO;
import dto.PedidoDTO;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;


public class MonitorThread extends Thread {

	private JFrame frame;
	private PadreMonitor padre;
	//TABLAS
	private JTable tablaPedidos;
	private JTable tablaPizzas;
	private JTable tablaEmpanadas;
	//Modelo
	private DefaultTableModel modeloPedidos;
	private DefaultTableModel modeloPizzasFaltantes;
	private DefaultTableModel modeloEmpanadasFaltantes;
	private final JLabel fondo = new JLabel("");
	
	@SuppressWarnings("serial")
	public MonitorThread() {

		this.padre = padre;
		
		/// GENERO FRAME Y LABEL CONTENIDO
				frame = new JFrame();
				frame.getContentPane().setBackground(Color.LIGHT_GRAY);
				frame.setBounds(250, 150, 1100, 630);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setLayout(null);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBackground(new Color(204, 153, 102));
				scrollPane.setBounds(47, 78, 973, 164);
				frame.getContentPane().add(scrollPane);
				
				//TABLA Y MODELO DE PEDIDOS
				tablaPedidos = new JTable();
				tablaPedidos.setShowGrid(false);
				tablaPedidos.setRowSelectionAllowed(false);
				//tablaPedidos.setFont(new Font("Verdana", Font.PLAIN, 14));
				tablaPedidos.setForeground(new Color(0, 0, 0));
				tablaPedidos.setBackground(new Color(204, 153, 102));
				modeloPedidos = new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Nro. Pedido", "Producto", "Cantidad", "Comentario"
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
				tablaPedidos.setModel(modeloPedidos);
				tablaPedidos.getColumnModel().getColumn(0).setPreferredWidth(69);
				tablaPedidos.getColumnModel().getColumn(1).setResizable(false);
				tablaPedidos.getColumnModel().getColumn(1).setPreferredWidth(82);
				tablaPedidos.getColumnModel().getColumn(2).setResizable(false);
				tablaPedidos.getColumnModel().getColumn(3).setResizable(false);
				scrollPane.setViewportView(tablaPedidos);
				////
				
				//// TABLA Y MODELO DE PIZZA
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBackground(new Color(204, 153, 102));
				scrollPane_1.setBounds(55, 352, 430, 215);
				frame.getContentPane().add(scrollPane_1);
				tablaPizzas = new JTable();
				tablaPizzas.setShowGrid(false);
				tablaPizzas.setRowSelectionAllowed(false);
				//tablaPizzas.setFont(new Font("Verdana", Font.PLAIN, 14));
				tablaPizzas.setForeground(new Color(0, 0, 0));
				tablaPizzas.setBackground(new Color(204, 153, 102));
				modeloPizzasFaltantes = new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Producto", "Cantidad", "Comentario", "cantComentarios"
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
				//tablaPizzas.getColumnModel().getColumn(3).setPreferredWidth(123);
				scrollPane_1.setViewportView(tablaPizzas);
//				JScrollPane scrollPane_2 = new JScrollPane();
//				scrollPane_2.setBounds(462, 250, 378, 195);
//				frame.getContentPane().add(scrollPane_2);
				tablaPizzas.setModel(modeloPizzasFaltantes);
				//
				//// TABLA Y MODELO DE EMPANADAS	
				JScrollPane scrollPane_21 = new JScrollPane();
				scrollPane_21.setBackground(new Color(204, 153, 102));
				scrollPane_21.setBounds(590, 352, 430, 215);
				frame.getContentPane().add(scrollPane_21);
				tablaEmpanadas = new JTable();
				tablaEmpanadas.setShowGrid(false);
				tablaEmpanadas.setRowSelectionAllowed(false);
				//tablaEmpanadas.setFont(new Font("Verdana", Font.PLAIN, 14));
				tablaEmpanadas.setForeground(new Color(0, 0, 0));
				tablaEmpanadas.setBackground(new Color(204, 153, 102));
				modeloEmpanadasFaltantes = new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Producto", "Cantidad", "Comentario", "cantComentarios"
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
				//tablaEmpanadas.getColumnModel().getColumn(3).setPreferredWidth(15);
				scrollPane_21.setViewportView(tablaEmpanadas);
				tablaEmpanadas.setModel(modeloEmpanadasFaltantes);
				fondo.setIcon(new ImageIcon("C:\\Users\\Yanina\\Documents\\Elab de constr de Soft\\tp pizzeria\\Gui\\Recursos\\Editados\\Ventanas\\fondoMonitor.png"));
				fondo.setBounds(0, 0, 1084, 591);
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
			pedido.getItems().get(i).setComentario(pedido.getItems().get(i).getComentario().trim());
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
				if (item.getComentario().compareTo("")!= 0){
					cantComentario++;
					charComentario = "Si";
				}
				modeloCorrespondiente.addRow(new Object [] {item.getProducto().getNombre(), item.getCantidad(), charComentario, cantComentario});
			}
			else{
				//Reemplazo la cantidad de faltante
				modeloCorrespondiente.setValueAt((Integer) modeloCorrespondiente.getValueAt(indiceFaltante, 1) + item.getCantidad(), indiceFaltante, 1);
				if (item.getComentario().compareTo("")!= 0){
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
					if (item.getComentario().compareTo("")!= 0){ // si tiene comentarios
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
