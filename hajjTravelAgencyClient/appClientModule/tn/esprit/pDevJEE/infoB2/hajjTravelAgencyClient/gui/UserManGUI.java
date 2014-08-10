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
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Role;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.User;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.roleManagement.RoleManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.userManagement.UserManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.util.ServiceLocator;

public class UserManGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private UserManRemote remote;
	private List<User> users;
	private JTextField uemail;
	private JTextField uusername;
	private JTextField upassword;
	private RoleManRemote remote2;
	private List<Role> roles;
	private JComboBox urole;
	private JTextField uname;

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
					UserManGUI frame = new UserManGUI();
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
	public UserManGUI() {
		//TODO add name jtextfield
		remote = (UserManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/UserMan!"
								+ UserManRemote.class.getCanonicalName());		
		users=remote.getAllUsers();
		remote2 = (RoleManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/RoleMan!"
								+ RoleManRemote.class.getCanonicalName());
		roles=remote2.getAllRoles();
		setTitle("User Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "User Management", TitledBorder.CENTER, TitledBorder.TOP, null, Color.RED));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblEmail = new JLabel("Email");
		
		JLabel lblUsername = new JLabel("Username");
		
		JLabel lblPassword = new JLabel("Password");
		
		JLabel lblRole = new JLabel("Role");
		
		uemail = new JTextField();
		uemail.setColumns(10);
		
		uusername = new JTextField();
		uusername.setColumns(10);
		
		upassword = new JTextField();
		upassword.setColumns(10);
		
		urole = new JComboBox();
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddUserGUI aug=new AddUserGUI();
				aug.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				aug.setLocationRelativeTo(null);
				aug.setVisible(true);
				aug.pack();
			}
		});
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User user=new User();
				user.setUserEmail(uemail.getText());
				user.setUserName(uusername.getText());
				user.setUserPassword(upassword.getText());
				user.setUserRole((Role) urole.getSelectedItem());
				remote.updateUser(user);
				users=remote.getAllUsers();
				initDataBindings();
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				remote.deleteUser(uemail.getText());
				users=remote.getAllUsers();
				initDataBindings();
			}
		});
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				users=remote.getAllUsers();
				initDataBindings();
			}
			
		});
		
		JLabel lblName = new JLabel("Name");
		
		uname = new JTextField();
		uname.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUsername)
								.addComponent(lblEmail)
								.addComponent(lblName)
								.addComponent(lblRole)
								.addComponent(lblPassword))
							.addGap(35)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(upassword)
								.addComponent(urole, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(uname)
								.addComponent(uemail)
								.addComponent(uusername))
							.addGap(48)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnRefresh, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNew, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnUpdate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 308, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
					.addGap(47)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNew)
						.addComponent(lblName)
						.addComponent(uname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(uemail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmail))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUsername)
								.addComponent(uusername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(upassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPassword))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(urole, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRole)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnUpdate)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDelete)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRefresh)))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setAutoCreateRowSorter(true);
		table.setBackground(Color.YELLOW);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Email", "Username", "Password", "Role"
			}
		));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		initDataBindings();
	}
	protected void initDataBindings() {
		JTableBinding<User, List<User>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ_WRITE, users, table);
		//
		BeanProperty<User, String> userBeanProperty_1 = BeanProperty.create("nameUser");
		jTableBinding.addColumnBinding(userBeanProperty_1).setColumnName("Name");
		//
		BeanProperty<User, String> userBeanProperty = BeanProperty.create("userEmail");
		jTableBinding.addColumnBinding(userBeanProperty).setColumnName("Email");
		//
		BeanProperty<User, String> userBeanProperty_2 = BeanProperty.create("userName");
		jTableBinding.addColumnBinding(userBeanProperty_2).setColumnName("Username");
		//
		BeanProperty<User, String> userBeanProperty_3 = BeanProperty.create("userPassword");
		jTableBinding.addColumnBinding(userBeanProperty_3).setColumnName("Password");
		//
		BeanProperty<User, Role> userBeanProperty_4 = BeanProperty.create("userRole");
		jTableBinding.addColumnBinding(userBeanProperty_4).setColumnName("Role");
		//
		jTableBinding.bind();
		//
		JComboBoxBinding<Role, List<Role>, JComboBox> jComboBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, roles, urole);
		jComboBinding.bind();
		//
		BeanProperty<JTable, String> jTableBeanProperty = BeanProperty.create("selectedElement.userEmail");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<JTable, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, table, jTableBeanProperty, uemail, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<JTable, String> jTableBeanProperty_1 = BeanProperty.create("selectedElement.userName");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty.create("text");
		AutoBinding<JTable, String, JTextField, String> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, table, jTableBeanProperty_1, uusername, jTextFieldBeanProperty_1);
		autoBinding_1.bind();
		//
		BeanProperty<JTable, String> jTableBeanProperty_2 = BeanProperty.create("selectedElement.userPassword");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_2 = BeanProperty.create("text");
		AutoBinding<JTable, String, JTextField, String> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, table, jTableBeanProperty_2, upassword, jTextFieldBeanProperty_2);
		autoBinding_2.bind();
		//
		BeanProperty<JTable, Role> jTableBeanProperty_3 = BeanProperty.create("selectedElement.userRole");
		BeanProperty<JComboBox, Role> jComboBoxBeanProperty = BeanProperty.create("selectedItem");
		AutoBinding<JTable, Role, JComboBox, Role> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, table, jTableBeanProperty_3, urole, jComboBoxBeanProperty);
		autoBinding_3.bind();
	}
}
