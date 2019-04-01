package me.rueno.Sortingalgorithms.UI.Dialog;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.JDialog;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import me.rueno.Sortingalgorithms.Lists.ListGenerator;
import me.rueno.Sortingalgorithms.Lists.ListType;
import me.rueno.Sortingalgorithms.Logic.ISortingAlgorithm;
import me.rueno.Sortingalgorithms.Misc.GlobalVars;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CompareAlgosDialog extends JDialog{
	
	private static final long serialVersionUID = -5642464823449347982L;
	
	private JFreeChart chart;
	private ScheduledFuture<?> dataMaker;
	
	private final Map<String, Color> algoColorMap;
	private JButton btnCancel;
	
	public CompareAlgosDialog(JFrame parent, ISortingAlgorithm... algorithms){
		addWindowListener(new WindowAdapter(){
			
			@Override
			public void windowClosing(WindowEvent e){
				if(dataMaker != null && !dataMaker.isDone()){
					dataMaker.cancel(true);
				}
			}
			
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(CompareAlgosDialog.class.getResource("/resources/images/icons/iconDiagramm.png")));
		this.algoColorMap = new HashMap<String, Color>();
		
		this.algoColorMap.put("QuickSort", Color.RED);
		this.algoColorMap.put("BubbleSort", Color.GREEN);
		this.algoColorMap.put("InsertionSort", Color.BLUE);
		this.algoColorMap.put("SelectionSort", Color.YELLOW);
		this.algoColorMap.put("ShellSort", Color.ORANGE);
		this.algoColorMap.put("BogoSort", Color.BLACK);
		
		setSize(657, 436);
		setLocationRelativeTo(null);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Algorithmen im Vergleich");
		
		JPanel contentPanel = new JPanel();
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel southPanel = new JPanel();
		FlowLayout fl_southPanel = (FlowLayout) southPanel.getLayout();
		fl_southPanel.setAlignment(FlowLayout.RIGHT);
		contentPanel.add(southPanel, BorderLayout.SOUTH);
		
		btnCancel = new JButton("Abbrechen");
		btnCancel.setFocusPainted(false);
		btnCancel.addActionListener(a -> {
			if(dataMaker != null && !dataMaker.isDone()){
				dataMaker.cancel(true);
			}
		});
		southPanel.add(btnCancel);
		
		HashMap<ISortingAlgorithm, XYSeries> map = createMap(algorithms);
		XYSeriesCollection set = (XYSeriesCollection) createDefaultDataset(map);
		
		chart = ChartFactory.createXYLineChart("Sortieralgorithmen im Vergleich (Integer mit zufälliger Anordnung)", "Anzahl Elemente",
				"Benötigte Zeit in Sekunden", set, PlotOrientation.VERTICAL, true, true, false);
		ChartPanel Cpanel = new ChartPanel(chart);
		Cpanel.setDisplayToolTips(true);
		
		XYPlot plot = chart.getXYPlot();
		XYLineAndShapeRenderer render = new XYLineAndShapeRenderer();
		
		for(int i = 0; i < set.getSeriesCount(); i++){
			XYSeries s = set.getSeries(i);
			render.setSeriesPaint(i, algoColorMap.get(s.getKey()));
			render.setSeriesStroke(i, new BasicStroke(0.75F));
		}
		
		plot.setRenderer(render);
		contentPanel.add(Cpanel, BorderLayout.CENTER);
		
		createDataAsynch(map);
	}
	
	private HashMap<ISortingAlgorithm, XYSeries> createMap(ISortingAlgorithm... algos){
		HashMap<ISortingAlgorithm, XYSeries> result = new HashMap<ISortingAlgorithm, XYSeries>();
		for(ISortingAlgorithm algo: algos){
			result.put(algo, new XYSeries(algo.getName()));
		}
		
		return result;
	}
	
	private XYDataset createDefaultDataset(HashMap<ISortingAlgorithm, XYSeries> map){
		XYSeriesCollection collection = new XYSeriesCollection();
		
		for(Entry<ISortingAlgorithm, XYSeries> entry: map.entrySet()){
			entry.getValue().add(0, 0);
			collection.addSeries(entry.getValue());
		}
		
		return collection;
	}
	
	private void createDataAsynch(HashMap<ISortingAlgorithm, XYSeries> map){
		this.dataMaker = GlobalVars.scheduler.schedule(() -> {
			ListGenerator gen = new ListGenerator();
			HashMap<Integer, Integer[]> lists = new HashMap<>();
			
			for(int i = 5000; i <= 50000; i += 5000){
				lists.put(i, gen.generateIntegerList(i, ListType.RANDOM));
			}
			XYSeries series;
			float data;
			for(Entry<ISortingAlgorithm, XYSeries> entry: map.entrySet()){
				series = entry.getValue();
				
				for(int i = 5000; i <= 50000; i += 5000){
					if(dataMaker.isCancelled() || dataMaker.isDone()){
						btnCancel.setEnabled(false);
						return;
					} /*Necessary for whatever reason. Without this code, the Task will be executed, even if the dataMaker-future knows, that it is done!*/
					
					data = entry.getKey().measureAlgorithm(copyArray(lists.get(i)))[0] / 1000.0F;
					synchronized(series){
						series.add(i, data);
					}
				}
			}
			btnCancel.setEnabled(false);
		}, 0, TimeUnit.MILLISECONDS);
	}
	
	private Integer[] copyArray(Integer[] toCopy){
		return Arrays.copyOf(toCopy, toCopy.length);
	}
	
}