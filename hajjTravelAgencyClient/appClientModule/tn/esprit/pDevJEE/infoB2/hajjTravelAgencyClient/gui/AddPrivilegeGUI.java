package tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Privilege;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.roleManagement.PrivilegeManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.util.ServiceLocator;

public class AddPrivilegeGUI extends JFrame {

	private JPanel contentPane;
	private JTextField prname;
	private PrivilegeManRemote remote;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPrivilegeGUI frame = new AddPrivilegeGUI();
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
	public AddPrivilegeGUI() {
		remote = (PrivilegeManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/PrivilegeMan!"
								+ PrivilegeManRemote.class.getCanonicalName());
		setTitle("New Privilege");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "New Privilege", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLUE));
		setContentPane(contentPane);
		
		JLabel lblName = new JLabel("Name");
		
		JLabel lblDescription = new JLabel("Description");
		
		prname = new JTextField();
		prname.setBorder(new LineBorder(new Color(0, 0, 0)));
		prname.setColumns(10);
		
		final JTextArea prdescription = new JTextArea();
		prdescription.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Privilege privilege=new Privilege();
				privilege.setNamePrivilege(prname.getText());
				privilege.setDescriptionPrivilege(prdescription.getText());
				remote.createPrivilege(privilege);
				dispose();
			}
		});
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearPanelTextBoxes(contentPane);

			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(335, Short.MAX_VALUE)
					.addComponent(btnAdd)
					.addGap(34))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(53)
							.addComponent(prdescription))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(51)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDescription)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblName)
									.addGap(59)
									.addComponent(prname, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(162, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(250, Short.MAX_VALUE)
					.addComponent(btnClear)
					.addGap(113))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(45)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(prname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(lblDescription)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(prdescription, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
							.addGap(42)
							.addComponent(btnClear)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAdd)
							.addContainerGap())))
		);
		contentPane.setLayout(gl_contentPane);
	}
	private void clearPanelTextBoxes(Container co)
	{

	Component[] components = co.getComponents();
	JTextField t = new JTextField();
	JTextArea tx = new JTextArea();

	for ( Component c : components )
	{
	if (c instanceof JTextField )
	{
	t = ( JTextField ) c ;
	t.setText("");//cleat the fields 
	
	}
	if (c instanceof JTextArea ) 
	{tx = ( JTextArea ) c ;
	tx.setText("");//cleat the fields 
	}
	if (c instanceof Container ) clearPanelTextBoxes((Container) c);
	
	}
	}
}
