package tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Trace;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.traceManagement.TraceManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.util.ServiceLocator;

public class TraceManGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private TraceManRemote remote;
	private List<Trace> traces;
	private Trace selectedTrace=new Trace();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TraceManGUI frame = new TraceManGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TraceManGUI() {
		setResizable(false);
		remote = (TraceManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/TraceMan!"
								+ TraceManRemote.class.getCanonicalName());
		traces=remote.getAllTraces();
		setTitle("History");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "History", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnClearAll = new JButton("Clear All");
		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				remote.clearAllTraces();
				traces=remote.getAllTraces();
				initDataBindings();
			}
		});
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				remote.removeTrace(selectedTrace);
				traces=remote.getAllTraces();
				initDataBindings();
			
			}
		});
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				traces=remote.getAllTraces();
				initDataBindings();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(295, Short.MAX_VALUE)
					.addComponent(btnRefresh)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnRemove)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnClearAll)
					.addGap(20))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnClearAll)
						.addComponent(btnRemove)
						.addComponent(btnRefresh))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setAutoCreateRowSorter(true);
		table.setBackground(Color.GREEN);
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
	      {
	         @Override
	         public Component getTableCellRendererComponent(JTable table,
	               Object value, boolean isSelected, boolean hasFocus, int row,
	               int column) {
	            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
	                  row, column);
	            if (value instanceof String&&value.equals("ADD")) {
	               c.setBackground(Color.ORANGE);
	            }
	            else if (value instanceof String&&value.equals("DELETE")) {
		               c.setBackground(Color.PINK);
		            }
	            
	            else if (value instanceof String&&value.equals("UPDATE")) {
		               c.setBackground(Color.YELLOW);
		            }
	            else {
	               c.setBackground(null);
	            }
	            return c;
	         }
	      });
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"ID", "Entity", "Operation", "Date"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setMinWidth(50);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		initDataBindings();
	}
	protected void initDataBindings() {
		JTableBinding<Trace, List<Trace>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ_WRITE, traces, table);
		//
		BeanProperty<Trace, Integer> traceBeanProperty = BeanProperty.create("operationId");
		jTableBinding.addColumnBinding(traceBeanProperty).setColumnName("ID");
		//
		BeanProperty<Trace, String> traceBeanProperty_3 = BeanProperty.create("operationType");
		jTableBinding.addColumnBinding(traceBeanProperty_3).setColumnName("Type");
		//
		BeanProperty<Trace, String> traceBeanProperty_2 = BeanProperty.create("operationEntity");
		jTableBinding.addColumnBinding(traceBeanProperty_2).setColumnName("Entity");
		//
		BeanProperty<Trace, Date> traceBeanProperty_4 = BeanProperty.create("operationDate");
		jTableBinding.addColumnBinding(traceBeanProperty_4).setColumnName("Date");
		//
		jTableBinding.bind();
		//
		BeanProperty<JTable, Integer> jTableBeanProperty = BeanProperty.create("selectedElement.operationId");
		BeanProperty<Trace, Integer> traceBeanProperty_1 = BeanProperty.create("operationId");
		AutoBinding<JTable, Integer, Trace, Integer> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, table, jTableBeanProperty, selectedTrace, traceBeanProperty_1);
		autoBinding.bind();
	}
}
