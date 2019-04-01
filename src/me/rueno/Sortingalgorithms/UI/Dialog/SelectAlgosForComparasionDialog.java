package me.rueno.Sortingalgorithms.UI.Dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import me.rueno.Sortingalgorithms.Logic.ISortingAlgorithm;
import me.rueno.Sortingalgorithms.Logic.Algorithms.*;

import javax.swing.JCheckBox;

import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.Toolkit;

public class SelectAlgosForComparasionDialog extends JDialog{
	
	private static final long serialVersionUID = 908792894799713037L;
	
	private ArrayList<ISortingAlgorithm> resultAsList;
	private boolean canceled;
	
	public SelectAlgosForComparasionDialog(JFrame parent){
		setIconImage(Toolkit.getDefaultToolkit().getImage(SelectAlgosForComparasionDialog.class.getResource("/resources/images/icons/iconDiagramm.png")));
		this.canceled = true;
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		resultAsList = new ArrayList<ISortingAlgorithm>();
		setResizable(false);
		setTitle("Algorithmenauswahl");
		setSize(201, 271);
		setLocationRelativeTo(parent);
		getContentPane().setLayout(null);
		
		JTextPane textpane = new JTextPane();
		textpane.setEditable(false);
		textpane.setText("Wählen Sie hier die Algorithmen aus, deren Laufzeit bei x Elementen in einer zufällig verteilten Liste aus Integern vergleichen wollen:");
		textpane.setBackground(SystemColor.menu);
		textpane.setHighlighter(null);
		textpane.setBounds(10, 10, 174, 76);
		getContentPane().add(textpane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Verfügbare Algorithmen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 98, 175, 100);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JCheckBox chckbxBubblesort = new JCheckBox("Bubblesort");
		chckbxBubblesort.setFocusPainted(false);
		chckbxBubblesort.setBounds(6, 20, 77, 23);
		chckbxBubblesort.addActionListener(a -> {
			addOrRemove(BubbleSort.class, ((JCheckBox)a.getSource()).isSelected());
		});
		panel.add(chckbxBubblesort);
		
		JCheckBox chckbxSelectionsort = new JCheckBox("Selectionsort");
		chckbxSelectionsort.setFocusPainted(false);
		chckbxSelectionsort.setBounds(6, 46, 87, 23);
		chckbxSelectionsort.addActionListener(a -> {
			addOrRemove(SelectionSort.class, ((JCheckBox)a.getSource()).isSelected());
		});
		panel.add(chckbxSelectionsort);
		
		JCheckBox chckbxInsertionsort = new JCheckBox("Insertionsort");
		chckbxInsertionsort.setFocusPainted(false);
		chckbxInsertionsort.setBounds(6, 72, 87, 23);
		chckbxInsertionsort.addActionListener(a -> {
			addOrRemove(InsertionSort.class, ((JCheckBox)a.getSource()).isSelected());
		});
		panel.add(chckbxInsertionsort);
		
		JCheckBox chckbxBogosort = new JCheckBox("Bogosort");
		chckbxBogosort.setEnabled(false);
		chckbxBogosort.setFocusPainted(false);
		chckbxBogosort.setBounds(95, 72, 69, 23);
		chckbxBogosort.addActionListener(a -> {
			addOrRemove(BogoSort.class, ((JCheckBox)a.getSource()).isSelected());
		});
		panel.add(chckbxBogosort);
		
		JCheckBox chckbxShellsort = new JCheckBox("Shellsort");
		chckbxShellsort.setFocusPainted(false);
		chckbxShellsort.setBounds(95, 20, 69, 23);
		chckbxShellsort.addActionListener(a -> {
			addOrRemove(ShellSort.class, ((JCheckBox)a.getSource()).isSelected());
		});
		panel.add(chckbxShellsort);
		
		JCheckBox chckbxQuicksort = new JCheckBox("Quicksort");
		chckbxQuicksort.setFocusPainted(false);
		chckbxQuicksort.setBounds(95, 46, 77, 23);
		chckbxQuicksort.addActionListener(a -> {
			addOrRemove(QuickSort.class, ((JCheckBox)a.getSource()).isSelected());
		});
		panel.add(chckbxQuicksort);
		
		JButton btnConfirm = new JButton("Bestätigen");
		btnConfirm.setFocusPainted(false);
		btnConfirm.setBounds(102, 209, 83, 23);
		btnConfirm.addActionListener(a -> {
			this.canceled = false;
			setVisible(false);
		});
		getContentPane().add(btnConfirm);
	}
	
	private void addOrRemove(Class<? extends ISortingAlgorithm> c, boolean add){
		try{
			if(add){
				resultAsList.add((ISortingAlgorithm) c.newInstance());
			}else{
				resultAsList.removeIf(type -> {
					return (c.isInstance(type));
				});
			}
		}catch(Exception e){
			new ExceptionDialog(e);
		}
	}
	
	public ISortingAlgorithm[] getResult(){
		if(!canceled){
			return resultAsList.toArray(new ISortingAlgorithm[resultAsList.size()]);
		}else{
			return null;
		}
	}
	
}
