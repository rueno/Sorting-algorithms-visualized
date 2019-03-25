package me.rueno.Sortingalgorithms.UI.Dialog;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

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
import me.rueno.Sortingalgorithms.Logic.Algorithms.BubbleSort;
import me.rueno.Sortingalgorithms.Logic.Algorithms.InsertionSort;
import me.rueno.Sortingalgorithms.Logic.Algorithms.QuickSort;
import me.rueno.Sortingalgorithms.Logic.Algorithms.SelectionSort;
import me.rueno.Sortingalgorithms.Logic.Algorithms.ShellSort;

public class CompareAlgosDialog extends JDialog{
	
	private static final long serialVersionUID = -5642464823449347982L;
	
	private JFreeChart chart;
	
	public CompareAlgosDialog(JFrame parent){
		setLocationRelativeTo(null);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Algorithmen im Vergleich");
		setSize(657, 436);
		
		XYDataset set = createSet(new BubbleSort(null), new SelectionSort(null), new InsertionSort(null),
				new QuickSort(null), new ShellSort(null));
		
		chart = ChartFactory.createXYLineChart("Sortieralgorithmen im Vergleich (Integer mit zufälliger Anordnung)", "Anzahl Elemente",
				"Benötigte Zeit in Sekunden", set, PlotOrientation.VERTICAL, true, true, false);
		ChartPanel panel = new ChartPanel(chart);
		panel.setDisplayToolTips(true);
		
		XYPlot plot = chart.getXYPlot();
		XYLineAndShapeRenderer render = new XYLineAndShapeRenderer();
		render.setSeriesPaint(0, Color.RED);
		render.setSeriesPaint(1, Color.BLUE);
		render.setSeriesPaint(2, Color.YELLOW);
		render.setSeriesPaint(3, Color.GREEN);
		render.setSeriesPaint(4, Color.ORANGE);
		render.setSeriesStroke(0, new BasicStroke(0.75F));
		render.setSeriesStroke(1, new BasicStroke(0.75F));
		render.setSeriesStroke(2, new BasicStroke(0.75F));
		render.setSeriesStroke(3, new BasicStroke(0.75F));
		render.setSeriesStroke(4, new BasicStroke(0.75F));
		plot.setRenderer(render);
		setContentPane(panel);
	}
	
	private XYDataset createSet(ISortingAlgorithm... algos){
		XYSeriesCollection collection = new XYSeriesCollection();
		
		HashMap<ISortingAlgorithm, XYSeries> map = new HashMap<ISortingAlgorithm, XYSeries>();
		
		for(ISortingAlgorithm algo: algos){
			XYSeries series = new XYSeries(algo.getName());
			series.add(0, 0);
			map.put(algo, series);
			collection.addSeries(series);
		}
		
		ListGenerator gen = new ListGenerator();
		for(int i = 5000; i <= 50000; i += 5000){
			Integer[] array = gen.generateIntegerList(i, ListType.RANDOM);
			for(Entry<ISortingAlgorithm, XYSeries> entry: map.entrySet()){
				entry.getValue().add(i, entry.getKey().measureAlgorithm(copyArray(array))[0] / 1000.0F);
			}
		}
		
		return collection;
	}
	
	private Integer[] copyArray(Integer[] toCopy){
		return Arrays.copyOf(toCopy, toCopy.length);
	}
	
}