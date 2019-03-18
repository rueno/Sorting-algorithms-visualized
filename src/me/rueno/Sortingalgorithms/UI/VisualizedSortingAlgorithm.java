package me.rueno.Sortingalgorithms.UI;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.util.Random;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import me.rueno.Sortingalgorithms.Logic.Algorithms.*;
import me.rueno.Sortingalgorithms.Logic.Algorithms.SelectionSort;
import me.rueno.Sortingalgorithms.UI.Components.AnimatedImageLabel;
import me.rueno.Sortingalgorithms.UI.Components.PepePls;
import me.rueno.Sortingalgorithms.UI.Components.PepoDance;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.JSeparator;

public class VisualizedSortingAlgorithm extends JFrame{
	
	private static final long serialVersionUID = 1307277800133861093L;
	
	private Random r;
	
	public VisualizedSortingAlgorithm(){
		setResizable(false);
		this.r = new Random();
		
		setTitle("Sortieralgorithmen (visualisiert)");
		setSize(720, 606);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Einstellungen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setToolTipText("");
		panel.setBounds(10, 11, 684, 149);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Sortieralgorithmus", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 21, 200, 117);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JRadioButton rdbtnBubblesort = new JRadioButton("Bubblesort");
		rdbtnBubblesort.setFocusPainted(false);
		rdbtnBubblesort.setSelected(true);
		rdbtnBubblesort.setBounds(6, 21, 87, 23);
		panel_2.add(rdbtnBubblesort);
		
		JRadioButton rdbtnInsertionsort = new JRadioButton("Insertionsort");
		rdbtnInsertionsort.setFocusPainted(false);
		rdbtnInsertionsort.setBounds(6, 47, 87, 23);
		panel_2.add(rdbtnInsertionsort);
		
		JRadioButton rdbtnSelectionsort = new JRadioButton("Selectionsort");
		rdbtnSelectionsort.setFocusPainted(false);
		rdbtnSelectionsort.setBounds(6, 73, 87, 23);
		panel_2.add(rdbtnSelectionsort);
		
		JRadioButton rdbtnQuicksort = new JRadioButton("Quicksort");
		rdbtnQuicksort.setFocusPainted(false);
		rdbtnQuicksort.setBounds(95, 21, 71, 23);
		panel_2.add(rdbtnQuicksort);
		
		JRadioButton rdbtnShellsort = new JRadioButton("Shellsort");
		rdbtnShellsort.setEnabled(false);
		rdbtnShellsort.setFocusPainted(false);
		rdbtnShellsort.setBounds(95, 47, 71, 23);
		panel_2.add(rdbtnShellsort);
		
		JRadioButton rdbtnMergesort = new JRadioButton("Mergesort");
		rdbtnMergesort.setEnabled(false);
		rdbtnMergesort.setFocusPainted(false);
		rdbtnMergesort.setBounds(95, 73, 75, 23);
		panel_2.add(rdbtnMergesort);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnMergesort);
		group.add(rdbtnShellsort);
		group.add(rdbtnQuicksort);
		group.add(rdbtnSelectionsort);
		group.add(rdbtnInsertionsort);
		group.add(rdbtnBubblesort);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Listeneinstellungen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(220, 21, 454, 117);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblSortierung = new JLabel("Sortierung:");
		lblSortierung.setBounds(10, 21, 54, 14);
		panel_3.add(lblSortierung);
		
		JComboBox<String> cbListSortMethod = new JComboBox<>();
		cbListSortMethod.setFocusable(false);
		cbListSortMethod.setModel(new DefaultComboBoxModel<>(new String[] {"Zufällig", "Aufsteigend", "Absteigend"}));
		cbListSortMethod.setSelectedIndex(0);
		cbListSortMethod.setBounds(74, 18, 150, 20);
		panel_3.add(cbListSortMethod);
		
		JLabel lblTyp = new JLabel("Typ:");
		lblTyp.setBounds(10, 46, 54, 14);
		panel_3.add(lblTyp);
		
		JComboBox<String> cbListContent = new JComboBox<>();
		cbListContent.setFocusable(false);
		cbListContent.setModel(new DefaultComboBoxModel<>(new String[] {"Integer", "Long", "Float", "Double", "String"}));
		cbListContent.setSelectedIndex(0);
		cbListContent.setBounds(74, 43, 150, 20);
		panel_3.add(cbListContent);
		
		JButton btnListTypeInfo = new JButton("?");
		btnListTypeInfo.setFocusPainted(false);
		btnListTypeInfo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnListTypeInfo.setBounds(234, 42, 23, 23);
		panel_3.add(btnListTypeInfo);
		
		JButton btnListSortTypeInfo = new JButton("?");
		btnListSortTypeInfo.setFocusPainted(false);
		btnListSortTypeInfo.setBounds(234, 17, 23, 23);
		panel_3.add(btnListSortTypeInfo);
		
		JButton btnOwnList = new JButton("Liste manuell eingeben");
		btnOwnList.setFocusPainted(false);
		btnOwnList.setBounds(303, 17, 141, 23);
		panel_3.add(btnOwnList);
		
		JButton btnConfirm = new JButton("Übernehmen");
		btnConfirm.setFocusPainted(false);
		btnConfirm.setBounds(303, 42, 141, 23);
		panel_3.add(btnConfirm);
		
		JTextPane txtpnGanzeZahlenWerden = new JTextPane();
		txtpnGanzeZahlenWerden.setFocusable(false);
		txtpnGanzeZahlenWerden.setBackground(UIManager.getColor("panel.background"));
		txtpnGanzeZahlenWerden.setEditable(false);
		txtpnGanzeZahlenWerden.setText("Ganze Zahlen werden maximal bis 10.000 unterstützt. Strings haben maximal 6 Zeichen. Kommazahlen haben maximal 2 Vorkommastellen und maximal 2 Nachkommastellen.");
		txtpnGanzeZahlenWerden.setHighlighter(null);
		txtpnGanzeZahlenWerden.setBounds(10, 71, 434, 35);
		panel_3.add(txtpnGanzeZahlenWerden);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sortierungsvorgang", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 172, 684, 394);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_0 = new JLabel();
		label_0.setHorizontalAlignment(SwingConstants.CENTER);
		label_0.setBounds(40, 40, 40, 40);
		panel_1.add(label_0);
		
		JLabel label_1 = new JLabel();
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(90, 40, 40, 40);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel();
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(140, 40, 40, 40);
		panel_1.add(label_2);
		
		JLabel label_3 = new JLabel();
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(190, 40, 40, 40);
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel();
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(240, 40, 40, 40);
		panel_1.add(label_4);
		
		JLabel label_5 = new JLabel();
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(290, 40, 40, 40);
		panel_1.add(label_5);
		
		JLabel label_6 = new JLabel();
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(340, 40, 40, 40);
		panel_1.add(label_6);
		
		JLabel label_7 = new JLabel();
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(390, 40, 40, 40);
		panel_1.add(label_7);
		
		JLabel label_8 = new JLabel();
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setBounds(440, 40, 40, 40);
		panel_1.add(label_8);
		
		JLabel label_9 = new JLabel();
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setBounds(490, 40, 40, 40);
		panel_1.add(label_9);
		
		JLabel label_10 = new JLabel();
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setBounds(540, 40, 40, 40);
		panel_1.add(label_10);
		
		JLabel label_11 = new JLabel();
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setBounds(590, 40, 40, 40);
		panel_1.add(label_11);
		
		JSlider sliderSpeedMultiplier = new JSlider();
		sliderSpeedMultiplier.setFocusable(false);
		sliderSpeedMultiplier.setMinimum(1);
		sliderSpeedMultiplier.setSnapToTicks(true);
		sliderSpeedMultiplier.setMinorTickSpacing(5);
		sliderSpeedMultiplier.setMajorTickSpacing(10);
		sliderSpeedMultiplier.setPaintTicks(true);
		sliderSpeedMultiplier.setBounds(380, 351, 200, 32);
		panel_1.add(sliderSpeedMultiplier);
		
		JLabel lblSimulationsgeschwindigkeit = new JLabel("Simulationsgeschwindigkeit:");
		lblSimulationsgeschwindigkeit.setBounds(248, 364, 132, 14);
		panel_1.add(lblSimulationsgeschwindigkeit);
		
		AnimatedImageLabel lblNewLabel = new PepePls(89, 78);
		lblNewLabel.setBounds(-20, 318, 89, 78);
		panel_1.add(lblNewLabel);
		
		JButton btnStart = new JButton("Starten");
		btnStart.setBounds(585, 360, 89, 23);
		panel_1.add(btnStart);
		btnStart.setFocusPainted(false);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(54, 338, 620, 2);
		panel_1.add(separator);
		
		JButton btnIntelAboutProcedure = new JButton("Infos zum Verfahren");
		btnIntelAboutProcedure.setBounds(540, 304, 134, 23);
		panel_1.add(btnIntelAboutProcedure);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 145, 664, 2);
		panel_1.add(separator_1);
		
		JLabel lblLegende = new JLabel("Legende:");
		lblLegende.setBounds(10, 158, 46, 14);
		panel_1.add(lblLegende);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setFocusable(false);
		textPane.setBackground(UIManager.getColor("panel.background"));
		textPane.setHighlighter(null);
		textPane.setBounds(10, 175, 664, 118);
		panel_1.add(textPane);
		
		Integer[] list = genList(10000);
		JLabel[] labels = new JLabel[] {label_0, label_1, label_2, label_3, label_4, label_5, label_6, label_7, label_8, label_9, label_10, label_11};
		
		btnStart.addActionListener(a -> {
			btnStart.setEnabled(false);
			Thread worker = new Thread(() -> {
//				BubbleSort s = new BubbleSort(labels);
				
				SelectionSort s = new SelectionSort(labels);
				s.sortVisualized(list);
				
//				for(int i = 0; i < 1000; i++) {
//					Integer[] liste = genList(10000);
//					
//					for(int j = 0; j < labels.length; j++){
//						labels[j].setText(liste[j] + "");
//					}
//					
//					SelectionSort s = new SelectionSort(labels);
//					s.sortVisualized(liste);
//					
//					for(int k = 0; k < liste.length-1; k++) {
//						if(liste[k] > liste[k+1]) System.out.println("Fehler: Indizes=[" + k + "," + (k+1) + "], " + liste[k] + " > " + liste[k+1]);
//					}
//					
//				}
				btnStart.setEnabled(true);
			});
			worker.start();
		});
		
		for(int i = 0; i < labels.length; i++){
			labels[i].setOpaque(true);
			labels[i].setText(list[i] + "");
			labels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		}
		
		Thread th = new Thread(() -> {
			lblNewLabel.revalidate();
			lblNewLabel.repaint();
			try{
				Thread.sleep(20);
			}catch(Exception e) {}
		});
		th.start();
		
	}
	
	private Integer[] genList(int max){
		Integer[] result = new Integer[12];
		
		for(int i = 0; i < result.length; i++){
			result[i] = genNumber(0, max);
		}
		return result;
	}
	
	private int genNumber(int min, int max){
		return r.nextInt((max - min) + 1) + min;
	}
}