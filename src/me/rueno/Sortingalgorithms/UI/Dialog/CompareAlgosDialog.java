package me.rueno.Sortingalgorithms.UI.Dialog;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.Arrays;

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
import me.rueno.Sortingalgorithms.Logic.Algorithms.BubbleSort;
import me.rueno.Sortingalgorithms.Logic.Algorithms.InsertionSort;
import me.rueno.Sortingalgorithms.Logic.Algorithms.QuickSort;
import me.rueno.Sortingalgorithms.Logic.Algorithms.SelectionSort;

public class CompareAlgosDialog extends JDialog{
	
	private static final long serialVersionUID = -5642464823449347982L;
	
	public CompareAlgosDialog(JFrame parent){
		setLocationRelativeTo(null);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Algorithmen im Vergleich");
		setSize(657, 436);
		
		XYDataset set = createSet();
		
		JFreeChart chart = ChartFactory.createXYLineChart("Sortieralgorithmen im Vergleich (Liste mit zufälliger Anordnung)", "Anzahl Elemente",
				"Benötigte Zeit in Sekunden", set, PlotOrientation.VERTICAL, true, true, false);
		ChartPanel panel = new ChartPanel(chart);
		XYPlot plot = chart.getXYPlot();
		XYLineAndShapeRenderer render = new XYLineAndShapeRenderer();
		render.setSeriesPaint(0, Color.RED);
		render.setSeriesPaint(1, Color.BLUE);
		render.setSeriesPaint(2, Color.PINK);
		render.setSeriesPaint(3, Color.GREEN);
		render.setSeriesStroke(0, new BasicStroke(0.75F));
		render.setSeriesStroke(1, new BasicStroke(0.75F));
		render.setSeriesStroke(2, new BasicStroke(0.75F));
		render.setSeriesStroke(3, new BasicStroke(0.75F));
		plot.setRenderer(render);
		setContentPane(panel);
	}
	
	private XYDataset createSet(){
		XYSeries bubble = new XYSeries("Bubblesort");
		bubble.add(0, 0);
		
		XYSeries insertion = new XYSeries("Insertionsort");
		insertion.add(0, 0);
		
		XYSeries selection = new XYSeries("Selectionsort");
		selection.add(0, 0);
		
		XYSeries quick = new XYSeries("Quicksort");
		quick.add(0, 0);
		
		ListGenerator gen = new ListGenerator();
		
		BubbleSort bubbleSort = new BubbleSort(null);
		InsertionSort insertionSort = new InsertionSort(null);
		SelectionSort selectionSort = new SelectionSort(null);
		QuickSort quickSort = new QuickSort(null);
		
		for(int i = 5000; i <= 50000; i += 5000){
			Integer[] array = gen.generateIntegerList(i, ListType.RANDOM);
			
			bubble.add(i, bubbleSort.measureAlgorithm(copyArray(array))[0] / 1000.0F);
			insertion.add(i, insertionSort.measureAlgorithm(copyArray(array))[0] / 1000.0F);
			selection.add(i, selectionSort.measureAlgorithm(copyArray(array))[0] / 1000.0F);
			quick.add(i, quickSort.measureAlgorithm(copyArray(array))[0] / 1000.0F);
		}
		
		XYSeriesCollection collection = new XYSeriesCollection();
		collection.addSeries(bubble);
		collection.addSeries(insertion);
		collection.addSeries(selection);
		collection.addSeries(quick);
		
		return collection;
	}
	
	private Integer[] copyArray(Integer[] toCopy){
		return Arrays.copyOf(toCopy, toCopy.length);
	}
	
}