package tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.gui;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.JListBinding;
import org.jdesktop.swingbinding.SwingBindings;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Privilege;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Role;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.roleManagement.PrivilegeManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.roleManagement.RoleManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.util.ServiceLocator;

public class AddRoleGUI extends JFrame {
	

	private JPanel contentPane;
	private JTextField roname;
	private JTextField rodescription;
	private RoleManRemote remote;
	private PrivilegeManRemote remote2;
	private List<Privilege> privileges;
	private JList roprivlist;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("de.muntjak.tinylookandfeel.TinyLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddRoleGUI frame = new AddRoleGUI();
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
	public AddRoleGUI() {
		setResizable(false);
		remote = (RoleManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/RoleMan!"
								+ RoleManRemote.class.getCanonicalName());
		remote2 = (PrivilegeManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/PrivilegeMan!"
								+ PrivilegeManRemote.class.getCanonicalName());
		privileges=remote2.getAllPrivileges();
		setTitle("New Role");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "New Role", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLUE));
		setContentPane(contentPane);
		
		JLabel lblName = new JLabel("Name");
		
		roname = new JTextField();
		roname.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description");
		
		JLabel lblPrivileges = new JLabel("Privileges");
		
		rodescription = new JTextField();
		rodescription.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Role role=new Role();
				role.setNameRole(roname.getText());
				role.setDescriptionRole(rodescription.getText());
				role.setPrivileges((List<Privilege>) roprivlist.getSelectedValuesList());
				remote.createRole(role);
				dispose();
				
			}
		});
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearPanelTextBoxes(contentPane);

			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnClear)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAdd))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblName)
								.addComponent(lblDescription))
							.addGap(84)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(rodescription)
								.addComponent(roname, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)))
						.addComponent(lblPrivileges)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(48)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(roname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rodescription, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescription))
					.addGap(18)
					.addComponent(lblPrivileges)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd)
						.addComponent(btnClear))
					.addContainerGap())
		);
		
		roprivlist = new JList();
		roprivlist.setBackground(Color.GREEN);
		roprivlist.addMouseListener(new MouseAdapter() {
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
		scrollPane.setViewportView(roprivlist);
		contentPane.setLayout(gl_contentPane);
		initDataBindings();
	}
	protected void initDataBindings() {
		JListBinding<Privilege, List<Privilege>, JList> jListBinding = SwingBindings.createJListBinding(UpdateStrategy.READ_WRITE, privileges, roprivlist);
		jListBinding.bind();
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
