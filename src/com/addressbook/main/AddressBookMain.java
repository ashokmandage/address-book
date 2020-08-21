package com.addressbook.main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class AddressBookMain extends javax.swing.JPanel {

	private static JTable jTable;
	private JScrollPane jScrollPane;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JButton jButton1;
	private JButton jButton2;
	private JButton jButton3;
	private JButton jButton4;
	private JButton sortByName;
	private JButton sortByZip;
	private static int rowCnt = 0;
	private static int selectedRow;

	public AddressBookMain() {
		jTable = new JTable(new AbstractTable());

		// Table Column size
		TableColumn column = null;
		for (int i = 0; i < 7; i++) {
			column = jTable.getColumnModel().getColumn(i);
			if (i == 0) {
				column.setPreferredWidth(100);
			} else if (i == 1) {
				column.setPreferredWidth(150);
			} else if (i == 3) {
				column.setPreferredWidth(100);
			} else {
				column.setPreferredWidth(10);
			}
		}

		jScrollPane = new JScrollPane(jTable);

		jPanel1 = new JPanel(new java.awt.BorderLayout());

		jPanel1.add(jScrollPane, java.awt.BorderLayout.CENTER);

		jButton1 = new JButton("Add Entry");
		jButton2 = new JButton("Update");
		jButton3 = new JButton("Delete");
		jButton4 = new JButton("Close");
		sortByName = new JButton("Sort by Name");
		sortByZip = new JButton("Sort by Zip");

		jPanel2 = new JPanel(new java.awt.FlowLayout());

		jPanel2.add(jButton1);
		jPanel2.add(jButton2);
		jPanel2.add(jButton3);
		jPanel2.add(jButton4);
		jPanel2.add(sortByName);
		jPanel2.add(sortByZip);

		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				new AddEntry().setVisible(true);
			}
		});

		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				new UpdateEntry(jTable.getValueAt(getSelectedRow(), 0).toString(),
						jTable.getValueAt(getSelectedRow(), 1).toString(),
						jTable.getValueAt(getSelectedRow(), 2).toString(),
						jTable.getValueAt(getSelectedRow(), 3).toString(),
						jTable.getValueAt(getSelectedRow(), 4).toString(),
						jTable.getValueAt(getSelectedRow(), 5).toString(),
						jTable.getValueAt(getSelectedRow(), 6).toString()).setVisible(true);
			}
		});

		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				removeEntry();
			}
		});

		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				setVisible(true);
				System.exit(0);
			}
		});

		sortByName.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
			}
		});

		sortByZip.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
			}
		});

		jPanel1.add(jPanel2, java.awt.BorderLayout.SOUTH);
		jPanel1.setPreferredSize(new java.awt.Dimension(750, 300));

		add(jPanel1);

		AddressBookMain.updateTable();
	}

	public static void addEntry(AddressBook addressBook) {

		AddressBookMain.addOneAddressBook(addressBook);
		rowCnt++;
	}

	public static void editEntry(AddressBook addressBook) {
		try {
			int row = getSelectedRow();
			File file = new File("addressbook.csv");
			CSVReader reader = new CSVReader(new FileReader(file));
			List<String[]> allElements = reader.readAll();
			allElements.remove(row);
			String data[] = { addressBook.getFname(), addressBook.getLname(), addressBook.getAddress(),
					addressBook.getCity(), addressBook.getState(), addressBook.getZip(), addressBook.getTelNo() };
			allElements.add(row, data);
			reader.close();
			
			FileWriter sw = new FileWriter(file);
			CSVWriter writer = new CSVWriter(sw);
			writer.writeAll(allElements);
			writer.close();
			updateTable();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void sortByName() {
		try {
			File file = new File("addressbook.csv");
			CSVReader reader = new CSVReader(new FileReader(file));
			List<String[]> allElements = reader.readAll();
			reader.close();
		
			
			
			FileWriter sw = new FileWriter(file);
			CSVWriter writer = new CSVWriter(sw);
			writer.writeAll(allElements);
			writer.close();
			updateTable();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	class SortbyStr implements Comparator<String> 
	{ 
	    // Used for sorting in ascending order of 
	    // roll number 
	    public int compare(String a, String b) 
	    { 
	        return a.charAt(0) - b.charAt(0); 
	    } 
	}
	
	public void removeEntry() {
		try {
			int row = getSelectedRow();
			File file = new File("addressbook.csv");
			CSVReader reader = new CSVReader(new FileReader(file));
			List<String[]> allElements = reader.readAll();
			allElements.remove(row);
			reader.close();
			FileWriter sw = new FileWriter(file);
			CSVWriter writer = new CSVWriter(sw);
			writer.writeAll(allElements);
			writer.close();
			updateTable();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static int getSelectedRow() {
		jTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

		javax.swing.ListSelectionModel rowSel = jTable.getSelectionModel();
		rowSel.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
			public void valueChanged(javax.swing.event.ListSelectionEvent e) {
				if (e.getValueIsAdjusting())
					return;

				javax.swing.ListSelectionModel sel = (javax.swing.ListSelectionModel) e.getSource();
				if (!sel.isSelectionEmpty()) {
					selectedRow = sel.getMinSelectionIndex();
				}
			}
		});

		return selectedRow;
	}

	public static void updateTable() {

		for (int i = 0; i < jTable.getRowCount(); i++) {
			jTable.setValueAt("", i, 0);
			jTable.setValueAt("", i, 1);
			jTable.setValueAt("", i, 2);
			jTable.setValueAt("", i, 3);
			jTable.setValueAt("", i, 4);
			jTable.setValueAt("", i, 5);
			jTable.setValueAt("", i, 6);
		}
		rowCnt = 0;

		List<AddressBook> allAddressBooks = AddressBookMain.getAllAddressBooks();
		for (AddressBook addressBook : allAddressBooks) {

			jTable.setValueAt(addressBook.getFname(), rowCnt, 0);
			jTable.setValueAt(addressBook.getLname(), rowCnt, 1);
			jTable.setValueAt(addressBook.getAddress(), rowCnt, 2);
			jTable.setValueAt(addressBook.getCity(), rowCnt, 3);
			jTable.setValueAt(addressBook.getState(), rowCnt, 4);
			jTable.setValueAt(addressBook.getZip(), rowCnt, 5);
			jTable.setValueAt(addressBook.getTelNo(), rowCnt, 6);

			rowCnt++;
		}
	}

	private static List<AddressBook> getAllAddressBooks() {

		List<AddressBook> list = new ArrayList<AddressBook>();
		try {
			File file = new File("addressbook.csv");
			FileReader filereader = new FileReader(file);

			CSVReader reader = new CSVReader(filereader);

			// read line by line
			String[] record = null;

			while ((record = reader.readNext()) != null) {
				AddressBook book = new AddressBook(record[0], record[1], record[2], record[3], record[4], record[5],
						record[6]);
				list.add(book);
			}

			reader.close();
		} catch (CsvValidationException | IOException e) {
			System.out.println(e.getMessage());
		}

		return list;
	}

	private static void addOneAddressBook(AddressBook addressBook) {

		try {
			File file = new File("addressbook.csv");

			CSVWriter csvOutput = new CSVWriter(new FileWriter(file, true));

			if (!file.exists()) {
				String[] columnNames = { "First Name", "Last Name", "Address", "City", "State", "Zip", "Tel No." };
				csvOutput.writeNext(columnNames);
			}

			String data[] = { addressBook.getFname(), addressBook.getLname(), addressBook.getAddress(),
					addressBook.getCity(), addressBook.getState(), addressBook.getZip(), addressBook.getTelNo() };
			csvOutput.writeNext(data);
			csvOutput.close();
			AddressBookMain.updateTable();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	class AbstractTable extends javax.swing.table.AbstractTableModel {
		private String[] columnNames = { "First Name", "Last Name", "Address", "City", "State", "Zip", "Tel No." };
		private Object[][] data = new Object[100][7];

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.length;
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			return data[row][col];
		}

		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}
	}

	public static void main(String args[]) {
		JFrame.setDefaultLookAndFeelDecorated(true);

		JFrame jFrame = new JFrame("Address Book");

		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jFrame.setResizable(false);

		jFrame.getContentPane().add(new AddressBookMain());

		jFrame.pack();
		jFrame.setVisible(true);

	}

}