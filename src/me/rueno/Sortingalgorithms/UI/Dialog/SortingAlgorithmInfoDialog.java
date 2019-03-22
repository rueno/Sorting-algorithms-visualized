package me.rueno.Sortingalgorithms.UI.Dialog;

import javax.swing.JDialog;

import me.rueno.Sortingalgorithms.Logic.DefaultVisualizedSortingAlgorithm;
import me.rueno.Sortingalgorithms.UI.VisualizedSortingAlgorithm;
import me.rueno.Sortingalgorithms.UI.Components.AnimatedImageLabel;
import me.rueno.Sortingalgorithms.UI.Components.FeelsOhWait;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JLabel;

public class SortingAlgorithmInfoDialog extends JDialog{
	
	private static final long serialVersionUID = 7417430049870357011L;

	public SortingAlgorithmInfoDialog(VisualizedSortingAlgorithm parent, DefaultVisualizedSortingAlgorithm algo){
		setTitle("Über " + algo.getName());
		setLocationRelativeTo(parent);
		setSize(450, 545);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 430);
		getContentPane().add(scrollPane);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setContentType("text/html");
		editorPane.setHighlighter(null);
		scrollPane.setViewportView(editorPane);
		
		AnimatedImageLabel lblNewLabel = new FeelsOhWait(50, 50);
		lblNewLabel.setBounds(49, 445, 50, 50);
		getContentPane().add(lblNewLabel);
		
		JLabel lblSchnellzusammenfassungDiesesAlgorithmus = new JLabel("TLDR;");
		lblSchnellzusammenfassungDiesesAlgorithmus.setBounds(10, 463, 29, 23);
		getContentPane().add(lblSchnellzusammenfassungDiesesAlgorithmus);
		
		JButton btnSchlieen = new JButton("Schließen");
		btnSchlieen.setBounds(347, 472, 77, 23);
		getContentPane().add(btnSchlieen);
	}
}