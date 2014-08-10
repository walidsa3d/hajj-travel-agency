package tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.SwingBindings;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Role;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.User;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.roleManagement.RoleManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.traceManagement.TraceManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.userManagement.UserManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.util.ServiceLocator;

public class AddUserGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3976215222903148552L;
	private JPanel contentPane;
	private JTextField uemail;
	private UserManRemote remote;
	private RoleManRemote remote2;
	private List<Role> roles;
	@SuppressWarnings("rawtypes")
	private JComboBox urole;
	private JTextField uusername;
	private JTextField upassword;
	private JPanel panel;
	private TraceManRemote remote3;
	private JTextField uname;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUserGUI frame = new AddUserGUI();
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
	public AddUserGUI() {
		setTitle("New User");
		remote = (UserManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/UserMan!"
								+ UserManRemote.class.getCanonicalName());
		
		remote2 = (RoleManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/RoleMan!"
								+ RoleManRemote.class.getCanonicalName());
		
		roles=remote2.getAllRoles();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "New User", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));
		
		JLabel lblRole = new JLabel("Role");
		
		urole = new JComboBox<Role>();
		
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User user=new User();
				user.setUserEmail(uemail.getText());
				user.setUserRole((Role) urole.getSelectedItem());
				user.setUserName(uusername.getText());
				user.setUserPassword(upassword.getText());
				user.setNameUser(uname.getText());
				remote.createUser(user);
				dispose();
			}
		});
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearPanelTextBoxes(contentPane);
			}
		});
		
		JLabel lblEmail = new JLabel("Email");
		
		uemail = new JTextField();
		uemail.setColumns(10);
		
		uusername = new JTextField();
		uusername.setColumns(10);
		
		upassword = new JTextField();
		upassword.setColumns(10);
		
		JLabel lblUsename = new JLabel("Usename");
		
		JLabel lblPassword = new JLabel("Password");
		
		uname = new JTextField();
		uname.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(240, Short.MAX_VALUE)
					.addComponent(btnClear)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAdd)
					.addGap(58))
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblUsename)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRole)
								.addComponent(lblPassword)))
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblName)
							.addComponent(lblEmail)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(urole, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(uname, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
								.addComponent(uusername, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
								.addComponent(uemail, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
								.addComponent(upassword, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
							.addGap(48))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(uname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(uemail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(uusername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsename))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(upassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(5)
							.addComponent(urole, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAdd)
								.addComponent(btnClear)))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblRole, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(105, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 428, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(22, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
		initDataBindings();
	}

	protected void initDataBindings() {
		@SuppressWarnings("rawtypes")
		JComboBoxBinding<Role, List<Role>, JComboBox> jComboBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, roles, urole);
		jComboBinding.bind();
	}
	private void clearPanelTextBoxes(Container co)
	{

	Component[] components = co.getComponents();
	JTextField t = new JTextField();
	for ( Component c : components )
	{
	if (c instanceof JTextField )
	{
	t = ( JTextField ) c ;
	t.setText("");//cleat the fields 
	}
	if (c instanceof Container ) clearPanelTextBoxes((Container) c);
	
	}

	}
}
