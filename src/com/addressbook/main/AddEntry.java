package com.addressbook.main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddEntry extends JFrame {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JTextField jTextField1;
	private JTextField jTextField2;
	private JTextField jTextField3;
	private JTextField jTextField4;
	private JTextField jTextField5;
	private JTextField jTextField6;
	private JTextField jTextField7;
	private JButton jButton1;
	private JButton jButton2;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JPanel jPanel3;
	private JPanel jPanel4;
	private JPanel jPanel5;

	public AddEntry() {
		super("Add Entry");

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		setResizable(true);

		jLabel1 = new JLabel("First Name: ");
		jLabel2 = new JLabel("Last Name: ");
		jLabel3 = new JLabel("Address: ");
		jLabel4 = new JLabel("City: ");
		jLabel5 = new JLabel("State: ");
		jLabel6 = new JLabel("Zip: ");
		jLabel7 = new JLabel("Ph No: ");

		jTextField1 = new JTextField(15);
		jTextField2 = new JTextField(30);
		jTextField3 = new JTextField(10);
		jTextField4 = new JTextField(15);
		jTextField5 = new JTextField(15);
		jTextField6 = new JTextField(15);
		jTextField7 = new JTextField(15);

		jButton1 = new JButton("Add");
		jButton2 = new JButton("Cancel");

		jPanel1 = new JPanel(new java.awt.GridLayout(7, 1, 1, 5));

		jPanel1.add(jLabel1);
		jPanel1.add(jLabel2);
		jPanel1.add(jLabel3);
		jPanel1.add(jLabel4);
		jPanel1.add(jLabel5);
		jPanel1.add(jLabel6);
		jPanel1.add(jLabel7);

		jPanel2 = new JPanel(new java.awt.GridLayout(7, 1));

		jPanel2.add(jTextField1);
		jPanel2.add(jTextField2);
		jPanel2.add(jTextField3);
		jPanel2.add(jTextField4);
		jPanel2.add(jTextField5);
		jPanel2.add(jTextField6);
		jPanel2.add(jTextField7);

		jPanel3 = new JPanel(new java.awt.FlowLayout());

		jPanel3.add(jPanel1);
		jPanel3.add(jPanel2);

		jPanel4 = new JPanel(new java.awt.FlowLayout());

		jPanel4.add(jButton1);
		jPanel4.add(jButton2);

		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				AddressBookMain.addEntry(new AddressBook(jTextField1.getText(), jTextField2.getText(),
						jTextField3.getText(), jTextField4.getText(), jTextField5.getText(), jTextField6.getText(),
						jTextField7.getText()));
			}
		});

		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				setVisible(true);
				dispose();
			}
		});

		jPanel5 = new JPanel(new java.awt.BorderLayout());

		jPanel5.add(jPanel3, java.awt.BorderLayout.CENTER);
		jPanel5.add(jPanel4, java.awt.BorderLayout.SOUTH);

		getContentPane().add(jPanel5);

		pack();
		setVisible(true);
	}

	public static void main(String args[]) {
		JFrame.setDefaultLookAndFeelDecorated(true);

		new AddEntry().setVisible(true);
	}
}