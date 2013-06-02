package tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.gui;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.swingbinding.JListBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Privilege;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Role;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.roleManagement.PrivilegeManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.roleManagement.RoleManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.util.ServiceLocator;

public class RoleManGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField roname;
	private JTextField rodescription;
	private RoleManRemote remote;
	private List<Role> roles;
	private JList selectprivslist;
	private PrivilegeManRemote remote2;
	private List<Privilege> privs;
	private JList allprivslist;
	private DefaultListModel privsmodel;
	private DefaultListModel selectprivsmodel;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoleManGUI frame = new RoleManGUI();
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
	public RoleManGUI() {
		setResizable(false);
		remote = (RoleManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/RoleMan!"
								+ RoleManRemote.class.getCanonicalName());
		remote2 = (PrivilegeManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/PrivilegeMan!"
								+ PrivilegeManRemote.class.getCanonicalName());
		roles=remote.getAllRoles();
		privs=remote2.getAllPrivileges();
		setTitle("Role Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Role Management", TitledBorder.CENTER, TitledBorder.TOP, null, Color.RED));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(26)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(32)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 185, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roles=remote.getAllRoles();
				initDataBindings();
			}
		});
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Role role=new Role();
				role.setNameRole(roname.getText());
				role.setDescriptionRole(rodescription.getText());
				role.setPrivileges(allprivslist.getSelectedValuesList());
				remote.updateRole(role);
				roles=remote.getAllRoles();
				initDataBindings();
				
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remote.deleteRole(roname.getText());
				roles=remote.getAllRoles();
				initDataBindings();

			}
		});
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddRoleGUI arg=new AddRoleGUI();
				arg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				arg.setLocationRelativeTo(null);
				arg.setVisible(true);
				arg.pack();
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnNew, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnUpdate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnDelete, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
					.addContainerGap(123, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNew)
					.addGap(10)
					.addComponent(btnUpdate)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnDelete)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton)
					.addContainerGap(61, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblName = new JLabel("Name");
		
		roname = new JTextField();
		roname.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description");
		
		rodescription = new JTextField();
		rodescription.setColumns(10);
		
		JLabel lblPrivileges = new JLabel("Privileges");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JButton btnNewButton_1 = new JButton(">");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Privilege privo=(Privilege) allprivslist.getSelectedValue();
				DefaultListModel selectprivsmodel=(DefaultListModel) selectprivslist.getModel();
				selectprivsmodel.addElement(privo);
				selectprivslist=new JList(selectprivsmodel);

			
			}
		});
		
		JButton btnNewButton_2 = new JButton("<");
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnNewButton_2, 0, 0, Short.MAX_VALUE)
								.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
							.addGap(22)
							.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblName)
							.addGap(47)
							.addComponent(roname, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblDescription)
							.addGap(21)
							.addComponent(rodescription, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
						.addComponent(lblPrivileges))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(roname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDescription)
						.addComponent(rodescription, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblPrivileges)
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(24)
							.addComponent(btnNewButton_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_2))
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addComponent(scrollPane_2))
					.addContainerGap(79, Short.MAX_VALUE))
		);
		
		selectprivslist = new JList();
		selectprivslist.setBackground(Color.GREEN);
		scrollPane_2.setViewportView(selectprivslist);
		
		allprivslist = new JList();
		allprivslist.addMouseListener(new MouseAdapter() {
			Robot robot;
			{
				try {
					robot = new Robot();
				} catch (AWTException ex) {
					ex.printStackTrace();
				}
			}
	 
			@Override
			public void mouseEntered(MouseEvent e) {
				if (robot != null)
					robot.keyPress(KeyEvent.VK_CONTROL);
			}
	 
			@Override
			public void mouseExited(MouseEvent e) {
				if (robot != null)
					robot.keyRelease(KeyEvent.VK_CONTROL);
			}
		});
		allprivslist.setBackground(Color.GREEN);

		scrollPane_1.setViewportView(allprivslist);
		panel_1.setLayout(gl_panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		
		table = new JTable();
		table.setAutoCreateRowSorter(true);
		table.setBackground(Color.YELLOW);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Name", "Description", "Privileges"
			}
		));
		scrollPane.setViewportView(table);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addGap(108))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		initDataBindings();
	}
	protected void initDataBindings() {
		JTableBinding<Role, List<Role>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ_WRITE, roles, table);
		//
		BeanProperty<Role, String> roleBeanProperty = BeanProperty.create("nameRole");
		jTableBinding.addColumnBinding(roleBeanProperty).setColumnName("Name");
		//
		BeanProperty<Role, String> roleBeanProperty_1 = BeanProperty.create("descriptionRole");
		jTableBinding.addColumnBinding(roleBeanProperty_1).setColumnName("Description");
		//
		jTableBinding.bind();
		//
		BeanProperty<JTable, String> jTableBeanProperty = BeanProperty.create("selectedElement.nameRole");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<JTable, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, table, jTableBeanProperty, roname, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<JTable, String> jTableBeanProperty_1 = BeanProperty.create("selectedElement.descriptionRole");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty.create("text");
		AutoBinding<JTable, String, JTextField, String> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, table, jTableBeanProperty_1, rodescription, jTextFieldBeanProperty_1);
		autoBinding_1.bind();
		//
		BeanProperty<JTable, List<Privilege>> jTableBeanProperty_2 = BeanProperty.create("selectedElement.privileges");
		JListBinding<Privilege, JTable, JList> jListBinding = SwingBindings.createJListBinding(UpdateStrategy.READ, table, jTableBeanProperty_2, selectprivslist);
		jListBinding.bind();
		//
		JListBinding<Privilege, List<Privilege>, JList> jListBinding_1 = SwingBindings.createJListBinding(UpdateStrategy.READ_WRITE, privs, allprivslist);
		jListBinding_1.bind();
	}
}
