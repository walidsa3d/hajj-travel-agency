package tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Company;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Plane;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.airTransportManagement.CompanyManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.airTransportManagement.PlaneManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.util.ServiceLocator;

public class PlaneManGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private PlaneManRemote remote;
	private List<Plane> planes;
	private JTextField pid;
	private JTextField pname;
	private JTextField ptype;
	private JComboBox pcompany;
	private CompanyManRemote remote2;
	private List<Company> companies;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlaneManGUI frame = new PlaneManGUI();
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
	public PlaneManGUI() {
		setResizable(false);
		remote = (PlaneManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/PlaneMan!"
								+ PlaneManRemote.class.getCanonicalName());
		planes=remote.getAllPlanes();
		remote2 = (CompanyManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/CompanyMan!"
								+ CompanyManRemote.class.getCanonicalName());
		companies=remote2.getAllCompanies();
		setType(Type.UTILITY);
		setTitle("Aircraft Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 535, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Aircraft Management", TitledBorder.CENTER, TitledBorder.TOP, null, Color.RED));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(37)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table = new JTable();
		table.setBackground(Color.YELLOW);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"ID", "Name", "Type", "Company"
			}
		));
		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 473, GroupLayout.PREFERRED_SIZE)
					.addGap(24))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblNewLabel = new JLabel("ID");
		
		JLabel lblName = new JLabel("Name");
		
		JLabel lblType = new JLabel("Type");
		
		JLabel lblCompany = new JLabel("Company");
		
		pid = new JTextField();
		pid.setColumns(10);
		
		pname = new JTextField();
		pname.setColumns(10);
		
		ptype = new JTextField();
		ptype.setColumns(10);
		
		pcompany = new JComboBox();
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPlaneGUI apg=new AddPlaneGUI();
				apg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                apg.setLocationRelativeTo(null);
                apg.setVisible(true);
                apg.pack();
			}
		});
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			Plane selectedPlane=new Plane();
				selectedPlane.setIdPlane(Integer.parseInt(pid.getText()));
				selectedPlane.setNamePlane(pname.getText());
				selectedPlane.setTypePlane(ptype.getText());
				selectedPlane.setPlaneCompany((Company) pcompany.getSelectedItem());
				remote.updatePlane(selectedPlane);
				planes=remote.getAllPlanes();
				initDataBindings();
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Plane selectedPlane=new Plane();
				selectedPlane.setIdPlane(Integer.parseInt(pid.getText()));
				remote.removePlane(selectedPlane);
				planes=remote.getAllPlanes();
				initDataBindings();
			}
		});
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				planes=remote.getAllPlanes();
				initDataBindings();
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(lblName)
						.addComponent(lblType)
						.addComponent(lblCompany))
					.addGap(55)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(pcompany, 0, 117, Short.MAX_VALUE)
						.addComponent(ptype)
						.addComponent(pname)
						.addComponent(pid))
					.addGap(53)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnRefresh)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(btnDelete, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNew, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnUpdate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(102, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(pid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNew))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(pname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUpdate))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(11)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblType)
								.addComponent(ptype, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblCompany)
								.addComponent(pcompany, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(6)
							.addComponent(btnDelete)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRefresh)))
					.addContainerGap(39, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		contentPane.setLayout(gl_contentPane);
		initDataBindings();
	}
	protected void initDataBindings() {
		JTableBinding<Plane, List<Plane>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ_WRITE, planes, table);
		//
		BeanProperty<Plane, Integer> planeBeanProperty = BeanProperty.create("idPlane");
		jTableBinding.addColumnBinding(planeBeanProperty).setColumnName("ID");
		//
		BeanProperty<Plane, String> planeBeanProperty_2 = BeanProperty.create("namePlane");
		jTableBinding.addColumnBinding(planeBeanProperty_2).setColumnName("Name");
		//
		BeanProperty<Plane, String> planeBeanProperty_3 = BeanProperty.create("typePlane");
		jTableBinding.addColumnBinding(planeBeanProperty_3).setColumnName("Type");
		//
		BeanProperty<Plane, String> planeBeanProperty_1 = BeanProperty.create("planeCompany.nameCompany");
		jTableBinding.addColumnBinding(planeBeanProperty_1).setColumnName("Company");
		//
		jTableBinding.bind();
		//
		JComboBoxBinding<Company, List<Company>, JComboBox> jComboBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, companies, pcompany);
		jComboBinding.bind();
		//
		BeanProperty<JTable, Integer> jTableBeanProperty = BeanProperty.create("selectedElement.idPlane");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<JTable, Integer, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, table, jTableBeanProperty, pid, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<JTable, String> jTableBeanProperty_1 = BeanProperty.create("selectedElement.namePlane");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty.create("text");
		AutoBinding<JTable, String, JTextField, String> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, table, jTableBeanProperty_1, pname, jTextFieldBeanProperty_1);
		autoBinding_1.bind();
		//
		BeanProperty<JTable, String> jTableBeanProperty_2 = BeanProperty.create("selectedElement.typePlane");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_2 = BeanProperty.create("text");
		AutoBinding<JTable, String, JTextField, String> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, table, jTableBeanProperty_2, ptype, jTextFieldBeanProperty_2);
		autoBinding_2.bind();
		//
		BeanProperty<JTable, Company> jTableBeanProperty_3 = BeanProperty.create("selectedElement.planeCompany");
		BeanProperty<JComboBox, Company> jComboBoxBeanProperty = BeanProperty.create("selectedItem");
		AutoBinding<JTable, Company, JComboBox, Company> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, table, jTableBeanProperty_3, pcompany, jComboBoxBeanProperty);
		autoBinding_3.bind();
	}
}
