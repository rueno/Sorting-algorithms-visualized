package me.rueno.Sortingalgorithms.UI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.Random;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import me.rueno.Sortingalgorithms.Logic.Algorithms.BubbleSort;
import me.rueno.Sortingalgorithms.Logic.Algorithms.QuickSort;

import javax.swing.JButton;

public class VisualizedSortingAlgorithm extends JFrame{
	
	private static final long serialVersionUID = 1307277800133861093L;
	
	private Random r;
	
	public VisualizedSortingAlgorithm(){
		this.r = new Random();
		
		setTitle("Sortieralgorithmen (visualisiert)");
		setSize(720, 606);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Einstellungen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setToolTipText("");
		panel.setBounds(10, 11, 684, 95);
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Sortierung", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 117, 684, 405);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_0 = new JLabel();
		label_0.setHorizontalAlignment(SwingConstants.CENTER);
		label_0.setBounds(10, 60, 40, 40);
		panel_1.add(label_0);
		
		JLabel label_1 = new JLabel();
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(60, 60, 40, 40);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel();
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(110, 60, 40, 40);
		panel_1.add(label_2);
		
		JLabel label_3 = new JLabel();
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(160, 60, 40, 40);
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel();
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(210, 60, 40, 40);
		panel_1.add(label_4);
		
		JLabel label_5 = new JLabel();
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(260, 60, 40, 40);
		panel_1.add(label_5);
		
		JLabel label_6 = new JLabel();
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(310, 60, 40, 40);
		panel_1.add(label_6);
		
		JLabel label_7 = new JLabel();
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(360, 60, 40, 40);
		panel_1.add(label_7);
		
		JLabel label_8 = new JLabel();
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setBounds(410, 60, 40, 40);
		panel_1.add(label_8);
		
		JLabel label_9 = new JLabel();
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setBounds(460, 60, 40, 40);
		panel_1.add(label_9);
		
		Integer[] list = genList(10000);
		
		JLabel[] labels = new JLabel[] {label_0, label_1, label_2, label_3, label_4, label_5, label_6, label_7, label_8, label_9};
		
		for(int i = 0; i < labels.length; i++) {
			labels[i].setOpaque(true);
			labels[i].setText(list[i] + "");
		}
		
		JButton btnStarten = new JButton("Starten");
		btnStarten.setBounds(605, 533, 89, 23);
		btnStarten.addActionListener(a -> {
			Thread worker = new Thread(() -> {
//				BubbleSort s = new BubbleSort(labels);
				for(int i = 0; i < 1000; i++) {
					Integer[] liste = genList(10000);
					
					for(int j = 0; j < labels.length; j++){
						labels[j].setText(liste[j] + "");
					}
					
					QuickSort s = new QuickSort(labels);
					s.sortVisualized(liste);
					
					for(int k = 0; k < liste.length-1; k++) {
						if(liste[k] > liste[k+1]) System.out.println("Fehler: Indexe=[" + k + "," + (k+1) + "], " + liste[k] + " > " + liste[k+1]);
					}
					
				}
			});
			worker.start();
		});
		getContentPane().add(btnStarten);
		
	}
	
	private Integer[] genList(int max){
		Integer[] result = new Integer[10];
		
		for(int i = 0; i < result.length; i++){
			result[i] = genNumber(0, max);
		}
		return result;
	}
	
	private int genNumber(int min, int max){
		return r.nextInt((max - min) + 1) + min;
	}
	
}