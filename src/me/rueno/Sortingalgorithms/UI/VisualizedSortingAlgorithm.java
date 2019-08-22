package me.rueno.Sortingalgorithms.UI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import me.rueno.Sortingalgorithms.Logic.DefaultVisualizedSortingAlgorithm;
import me.rueno.Sortingalgorithms.Logic.ISortingAlgorithm;
import me.rueno.Sortingalgorithms.Logic.Algorithms.*;
import me.rueno.Sortingalgorithms.Misc.GlobalVars;
import me.rueno.Sortingalgorithms.UI.Components.AnimatedImageLabel;
import me.rueno.Sortingalgorithms.UI.Components.PepePls;
import me.rueno.Sortingalgorithms.UI.Components.PikaRun;
import me.rueno.Sortingalgorithms.UI.Dialog.AboutProgramDialog;
import me.rueno.Sortingalgorithms.UI.Dialog.AboutSortingAlgorithmDialog;
import me.rueno.Sortingalgorithms.UI.Dialog.CompareAlgosDialog;
import me.rueno.Sortingalgorithms.UI.Dialog.SelectAlgosForComparasionDialog;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextPane;
import javax.swing.JSeparator;

import me.rueno.Sortingalgorithms.Lists.ListGenerator;
import me.rueno.Sortingalgorithms.Lists.ListType;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.Toolkit;

public class VisualizedSortingAlgorithm extends JFrame{
	
	private static final long serialVersionUID = 1307277800133861093L;
	
	@SuppressWarnings("rawtypes")
	private Comparable[] list;
	private ListGenerator gen;
	private final Color defaultBackground;
	private JLabel[] labels;
	private DefaultVisualizedSortingAlgorithm algo;
	private JPanel panel_AlgorithmRdbtns;
	private JButton btnStart;
	private JPanel panelSettings;
	private JComboBox<String> cbListSortMethod;
	private JComboBox<String> cbListContent;
	private AnimatedImageLabel animation;
	
	private NumberFormat nf;
	private JLabel lblComparations;
	private JLabel lblResaves;
	private JLabel lblRuntime;
	private AnimatedImageLabel pikaRun;
	private JTable table;
	
	private ScheduledFuture<?> sorter, measure;
	private JButton btnCancel;
	private JButton btnConfirm;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public VisualizedSortingAlgorithm(){
		setIconImage(Toolkit.getDefaultToolkit().getImage(VisualizedSortingAlgorithm.class.getResource("/resources/images/icons/iconAlgo.png")));
		this.defaultBackground = UIManager.getColor("panel.background");
		addWindowListener(new WindowAdapter(){
			
			@Override
			public void windowIconified(WindowEvent e){
				animation.setInterrupted(true);
			}
			
			@Override
			public void windowDeiconified(WindowEvent e){
				animation.setInterrupted(false);
			}
			
		});
		
		setResizable(false);
		this.gen = new ListGenerator();
		this.nf = NumberFormat.getInstance(Locale.GERMANY);
		
		setTitle("Sortieralgorithmen (visualisiert)");
		setSize(720, 620);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Einstellungen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setToolTipText("");
		panel.setBounds(10, 11, 684, 149);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		panel_AlgorithmRdbtns = new JPanel();
		panel_AlgorithmRdbtns.setBorder(new TitledBorder(null, "Sortieralgorithmus", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_AlgorithmRdbtns.setBounds(10, 21, 200, 117);
		panel.add(panel_AlgorithmRdbtns);
		panel_AlgorithmRdbtns.setLayout(null);
		
		JRadioButton rdbtnBubblesort = new JRadioButton("Bubblesort");
		rdbtnBubblesort.setFocusPainted(false);
		rdbtnBubblesort.setSelected(true);
		rdbtnBubblesort.setBounds(6, 21, 87, 23);
		rdbtnBubblesort.addActionListener(a -> {
			algo = new BubbleSort(labels);
		});
		panel_AlgorithmRdbtns.add(rdbtnBubblesort);
		
		JRadioButton rdbtnInsertionsort = new JRadioButton("Insertionsort");
		rdbtnInsertionsort.setFocusPainted(false);
		rdbtnInsertionsort.setBounds(6, 47, 87, 23);
		rdbtnInsertionsort.addActionListener(a -> {
			algo = new InsertionSort(labels);
		});
		panel_AlgorithmRdbtns.add(rdbtnInsertionsort);
		
		JRadioButton rdbtnSelectionsort = new JRadioButton("Selectionsort");
		rdbtnSelectionsort.setFocusPainted(false);
		rdbtnSelectionsort.setBounds(6, 73, 87, 23);
		rdbtnSelectionsort.addActionListener(a -> {
			algo = new SelectionSort(labels);
		});
		panel_AlgorithmRdbtns.add(rdbtnSelectionsort);
		
		JRadioButton rdbtnQuicksort = new JRadioButton("Quicksort");
		rdbtnQuicksort.setFocusPainted(false);
		rdbtnQuicksort.setBounds(95, 47, 71, 23);
		rdbtnQuicksort.addActionListener(a -> {
			algo = new QuickSort(labels);
		});
		panel_AlgorithmRdbtns.add(rdbtnQuicksort);
		
		JRadioButton rdbtnShellsort = new JRadioButton("Shellsort");
		rdbtnShellsort.setFocusPainted(false);
		rdbtnShellsort.setBounds(95, 21, 71, 23);
		rdbtnShellsort.addActionListener(a -> {
			algo = new ShellSort(labels);
		});
		panel_AlgorithmRdbtns.add(rdbtnShellsort);
		
		JRadioButton rdbtnBogosort = new JRadioButton("Bogosort");
		rdbtnBogosort.setFocusPainted(false);
		rdbtnBogosort.setBounds(95, 73, 71, 23);
		rdbtnBogosort.addActionListener(a -> {
			algo = new BogoSort(labels);
		});
		panel_AlgorithmRdbtns.add(rdbtnBogosort);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnShellsort);
		group.add(rdbtnQuicksort);
		group.add(rdbtnSelectionsort);
		group.add(rdbtnInsertionsort);
		group.add(rdbtnBubblesort);
		group.add(rdbtnBogosort);
		
		panelSettings = new JPanel();
		panelSettings.setBorder(new TitledBorder(null, "Listeneinstellungen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSettings.setBounds(220, 21, 454, 117);
		panel.add(panelSettings);
		panelSettings.setLayout(null);
		
		JLabel lblSortierung = new JLabel("Sortierung:");
		lblSortierung.setBounds(10, 21, 54, 14);
		panelSettings.add(lblSortierung);
		
		cbListSortMethod = new JComboBox<>();
		cbListSortMethod.setFocusable(false);
		cbListSortMethod.setModel(new DefaultComboBoxModel(ListType.values()));
		cbListSortMethod.setSelectedIndex(0);
		cbListSortMethod.setBounds(74, 18, 150, 20);
		cbListSortMethod.addActionListener(a -> {
			btnConfirm.doClick();
		});
		panelSettings.add(cbListSortMethod);
		
		JLabel lblTyp = new JLabel("Typ:");
		lblTyp.setBounds(10, 46, 54, 14);
		panelSettings.add(lblTyp);
		
		cbListContent = new JComboBox<>();
		cbListContent.setFocusable(false);
		cbListContent.setModel(new DefaultComboBoxModel<>(new String[] {"Integer", "Long", "Float", "Double", "String"}));
		cbListContent.setSelectedIndex(0);
		cbListContent.setBounds(74, 43, 150, 20);
		cbListContent.addActionListener(a -> {
			btnConfirm.doClick();
		});
		panelSettings.add(cbListContent);
		
		JButton btnListTypeInfo = new JButton("?");
		btnListTypeInfo.setFocusPainted(false);
		btnListTypeInfo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnListTypeInfo.setBounds(234, 42, 23, 23);
		btnListTypeInfo.addActionListener(a -> {
			showDialog("Information", "Es gibt mehrere Objekttypen, die mithilfe dieser Software sortiert werden können:\n"
					+ "Integer:  Eine normale ganze Zahl. In diesem Projekt hat der Integer eine Range von -9999 bis 10.000\n"
					+ "Long: Ähnlich des Integers. Gleiche Zahlenrange. Kann theoretisch größere Zahlen annehmen, in dieser Software ist die Range jedoch beschränkt.\n"
					+ "Float: Eine Kommazahl. In dieser Software mit maximal 2 Vorkomma und maximal 2 Nachkommastellen.\n"
					+ "Double: Funktion in dieser Software ist gleich des Floats. \n"
					+ "String: Eine Zeichenkette mit einer Länge von maximal 5 Zeichen, zufällig zusammengesetzt. Existiert um eine Besonderheit der java'schen Strings zu zeigen:\n"
					+ "Strings werden sortiert, indem zuerst alle Großbuchstaben und danach alle Kleinbuchstaben sortiert in eine Reihe gebracht werden.");
		});
		panelSettings.add(btnListTypeInfo);
		
		JButton btnListSortTypeInfo = new JButton("?");
		btnListSortTypeInfo.setFocusPainted(false);
		btnListSortTypeInfo.setBounds(234, 17, 23, 23);
		btnListSortTypeInfo.addActionListener(a -> {
			showDialog("Information", "Es gibt 3 Listtypen:\n"
					+ "Zufällig sortierte Liste, die Elemente sind zufällig sortiert angeordnet.\n"
					+ "Aufsteigend sortierte Liste, die Elemente sind von klein nach groß angeordnet.\n"
					+ "Absteigend sortierte Liste, die Elemente sind von groß nach klein angeordnet.");
		});
		panelSettings.add(btnListSortTypeInfo);
		
		JButton btnCompareAll = new JButton("Alle vergleichen");
		btnCompareAll.setFocusPainted(false);
		btnCompareAll.setBounds(303, 17, 141, 23);
		btnCompareAll.addActionListener(a -> {
			
			SelectAlgosForComparasionDialog dialog = new SelectAlgosForComparasionDialog(VisualizedSortingAlgorithm.this);
			dialog.setVisible(true);
			
			ISortingAlgorithm[] result = dialog.getResult();
			
			if(result != null && result.length > 0){
				Thread worker = new Thread(() -> {
					setComponentsEnabled(false);
					CompareAlgosDialog dia = new CompareAlgosDialog(VisualizedSortingAlgorithm.this, result);
					dia.setVisible(true);
					setComponentsEnabled(true);
				});
				worker.start();
			}
		});
		panelSettings.add(btnCompareAll);
		
		btnConfirm = new JButton("Neu generieren");
		btnConfirm.setFocusPainted(false);
		btnConfirm.setBounds(303, 42, 141, 23);
		btnConfirm.addActionListener(action -> {
			list = generateListWithSelectedSettings(12);
			
			for(int i = 0; i < 12; i++){
				labels[i].setBackground(defaultBackground);
				labels[i].setText(list[i] + "");
			}
			
		});
		panelSettings.add(btnConfirm);
		
		JTextPane txtpnGanzeZahlenWerden = new JTextPane();
		txtpnGanzeZahlenWerden.setFocusable(false);
		txtpnGanzeZahlenWerden.setBackground(UIManager.getColor("panel.background"));
		txtpnGanzeZahlenWerden.setEditable(false);
		txtpnGanzeZahlenWerden.setText("Ganze Zahlen werden maximal bis 10.000 unterstützt. Strings haben maximal 6 Zeichen. Kommazahlen haben maximal 2 Vorkommastellen und maximal 2 Nachkommastellen.");
		txtpnGanzeZahlenWerden.setHighlighter(null);
		txtpnGanzeZahlenWerden.setBounds(10, 71, 434, 35);
		panelSettings.add(txtpnGanzeZahlenWerden);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sortierungsvorgang", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 171, 684, 394);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		this.labels = new JLabel[12];
		list = gen.generateIntegerList(labels.length, ListType.RANDOM);
		
		for(int i = 0; i < 12; i++){
			JLabel label = new JLabel("");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			label.setOpaque(true);
			label.setBounds(40 + (i * 40) + (i * 10), 40, 40, 40);
			panel_1.add(label);
			label.setText(String.valueOf(list[i]));
			labels[i] = label;
		}
		
		JSlider sliderSpeedMultiplier = new JSlider();
		sliderSpeedMultiplier.setSnapToTicks(true);
		sliderSpeedMultiplier.setToolTipText("");
		sliderSpeedMultiplier.setValue(100);
		sliderSpeedMultiplier.setMaximum(200);
		sliderSpeedMultiplier.setFocusable(false);
		sliderSpeedMultiplier.setMinorTickSpacing(10);
		sliderSpeedMultiplier.setMajorTickSpacing(20);
		sliderSpeedMultiplier.setPaintTicks(true);
		sliderSpeedMultiplier.setBounds(190, 351, 390, 32);
		sliderSpeedMultiplier.addChangeListener(change -> {
			GlobalVars.SPEED_MULTIPLIER = sliderSpeedMultiplier.getValue() / 100.0F;
			if(GlobalVars.SPEED_MULTIPLIER < 0.05F){
				GlobalVars.SPEED_MULTIPLIER = 0.05F;
			}
		});
		panel_1.add(sliderSpeedMultiplier);
		
		JLabel lblSimulationsgeschwindigkeit = new JLabel("Simulationsgeschwindigkeit:");
		lblSimulationsgeschwindigkeit.setBounds(54, 360, 132, 14);
		panel_1.add(lblSimulationsgeschwindigkeit);
		
		animation = new PepePls(89, 78);
		animation.setBounds(-20, 318, 89, 78);
		panel_1.add(animation);
		
		btnStart = new JButton("Starten");
		btnStart.setBounds(585, 360, 89, 23);
		panel_1.add(btnStart);
		btnStart.setFocusPainted(false);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(54, 338, 620, 2);
		panel_1.add(separator);
		
		JButton btnIntelAboutProcedure = new JButton("Infos zum Verfahren");
		btnIntelAboutProcedure.setFocusPainted(false);
		btnIntelAboutProcedure.setBounds(540, 11, 134, 23);
		btnIntelAboutProcedure.addActionListener(a -> {
			AboutSortingAlgorithmDialog dialog = new AboutSortingAlgorithmDialog(VisualizedSortingAlgorithm.this, algo);
			dialog.setVisible(true);
		});
		panel_1.add(btnIntelAboutProcedure);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 145, 664, 2);
		panel_1.add(separator_1);
		
		JLabel lblLegende = new JLabel("Legende:");
		lblLegende.setBounds(10, 158, 46, 14);
		panel_1.add(lblLegende);
		
		JLabel lblStatistiken = new JLabel("Statistiken für Liste mit 10.000 Elementen:");
		lblStatistiken.setBounds(462, 184, 212, 14);
		panel_1.add(lblStatistiken);
		
		JLabel lblUmspeicherungen = new JLabel("Umspeicherungen:");
		lblUmspeicherungen.setBounds(490, 209, 90, 14);
		panel_1.add(lblUmspeicherungen);
		
		JLabel lblVergleiche = new JLabel("Vergleiche:");
		lblVergleiche.setBounds(490, 234, 90, 14);
		panel_1.add(lblVergleiche);
		
		JLabel lblLaufzeit = new JLabel("Laufzeit:");
		lblLaufzeit.setToolTipText("");
		lblLaufzeit.setBounds(490, 259, 90, 14);
		panel_1.add(lblLaufzeit);
		
		lblResaves = new JLabel("0");
		lblResaves.setHorizontalAlignment(SwingConstants.RIGHT);
		lblResaves.setBounds(590, 209, 84, 14);
		panel_1.add(lblResaves);
		
		lblComparations = new JLabel("0");
		lblComparations.setHorizontalAlignment(SwingConstants.RIGHT);
		lblComparations.setBounds(590, 234, 84, 14);
		panel_1.add(lblComparations);
		
		lblRuntime = new JLabel("0 ms");
		lblRuntime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRuntime.setBounds(590, 259, 84, 14);
		panel_1.add(lblRuntime);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 183, 446, 124);
		panel_1.add(scrollPane);
		
		pikaRun = new PikaRun(65, 50);
		pikaRun.setSize(65, 50);
		pikaRun.setLocation(609, 96);
		panel_1.add(pikaRun);
		pikaRun.setInterrupted(true);
		
		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel() {
			
			private static final long serialVersionUID = -7970629728981438850L;
			
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
			
		});
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addColumn("Feldfarbe/Aktion");
		model.addColumn("Bedeutung");
		model.addRow(new String[] {" Grün",				" Feld ist an der korrekten Stelle in der Liste*"});
		model.addRow(new String[] {" Rot",				" Felder werden miteinander verglichen"});
		model.addRow(new String[] {" Cyan",				" Felder werden vertauscht"});
		model.addRow(new String[] {" Pikachu rennt",	" Es wird aktuell eine Liste sortiert"});
		model.addRow(new String[] {" Pepe tanzt",		" Aus Meme"});
		model.addRow(new String[] {" *",				" Nicht Bogosort"});
		
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(92);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(266);
		
		table.setRowSelectionAllowed(false);
		
		btnCancel = new JButton("Abbrechen");
		btnCancel.setEnabled(false);
		btnCancel.setFocusPainted(false);
		btnCancel.setBounds(590, 284, 89, 23);
		btnCancel.addActionListener(a -> {
			if(sorter != null && !sorter.isDone()){
				sorter.cancel(true);
			}
			if(measure != null && !measure.isDone()){
				measure.cancel(true);
			}
			algo.normalizeDisplay();
			setComponentsEnabled(true);
		});
		panel_1.add(btnCancel);
		
		btnStart.addActionListener(a -> {
			pikaRun.setInterrupted(false);
			resetListLabels();
			setComponentsEnabled(false);
			lblRuntime.setText("0 ms");
			lblResaves.setText("0");
			lblComparations.setText("0");
			
			sorter = GlobalVars.scheduler.schedule(() -> {
				try{
					algo.sortVisualized(list);
				}catch(InterruptedException ignore){}
				if(measure.isDone()){
					setComponentsEnabled(true);
					pikaRun.setInterrupted(true);
				}
			}, 0, TimeUnit.MILLISECONDS);
			
			measure = GlobalVars.scheduler.schedule(() -> {
				Comparable[] array = generateListWithSelectedSettings(10000);
				long[] data = algo.measureAlgorithm(array);
				setStatistics(data[1], data[2], data[0]);
				if(sorter.isDone()){
					setComponentsEnabled(true);
					pikaRun.setInterrupted(true);
				}
			}, 0, TimeUnit.MILLISECONDS);
		});
		
		this.algo = new BubbleSort(labels);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuFile = new JMenu("Datei");
		menuBar.add(menuFile);
		
		JMenuItem menuItemQuit = new JMenuItem("Beenden");
		menuItemQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
		menuItemQuit.addActionListener(a -> {
			System.exit(0);
		});
		menuFile.add(menuItemQuit);
		
		JMenu menuHelp = new JMenu("Hilfe");
		menuBar.add(menuHelp);
		
		JMenuItem menuItemAbout = new JMenuItem("Über diese Software...");
		menuItemAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		menuItemAbout.addActionListener(a -> {
			AboutProgramDialog dialog = new AboutProgramDialog(VisualizedSortingAlgorithm.this);
			dialog.setVisible(true);
		});
		menuHelp.add(menuItemAbout);
	}
	
	private void resetListLabels(){
		for(int i = 0; i < 12; i++){
			labels[i].setBackground(defaultBackground);
		}
	}
	
	private void setStatistics(long resaves, long comparations, long runtime){
		lblResaves.setText(nf.format(resaves));
		lblComparations.setText(nf.format(comparations));
		lblRuntime.setText(nf.format(runtime) + " ms");
	}
	
	@SuppressWarnings("rawtypes")
	private Comparable[] generateListWithSelectedSettings(int length){
		ListType type = (ListType) cbListSortMethod.getSelectedItem();
		switch(cbListContent.getSelectedIndex()) {
			case 0:
				return gen.generateIntegerList(length, type);
			case 1:
				return gen.generateLongList(length, type);
			case 2:
				return gen.generateFloatList(length, type);
			case 3:
				return gen.generateDoubleList(length, type);
			case 4:
				return gen.generateStringList(length);
			default:
				break;
		}
		return null;
	}
	
	private void showDialog(String title, String text){
		JOptionPane.showMessageDialog(this, text, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void setComponentsEnabled(boolean enabled){
		btnStart.setEnabled(enabled);
		btnCancel.setEnabled(!enabled);
		
		for(Component comp: panel_AlgorithmRdbtns.getComponents()){
			comp.setEnabled(enabled);
		}
		for(Component c: panelSettings.getComponents()){
			if(!(c instanceof JTextPane)){
				c.setEnabled(enabled);
			}
		}
	}
}