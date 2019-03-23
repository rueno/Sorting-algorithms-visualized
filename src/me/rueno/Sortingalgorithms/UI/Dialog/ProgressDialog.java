package me.rueno.Sortingalgorithms.UI.Dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JProgressBar;
import java.awt.SystemColor;

public class ProgressDialog extends JDialog{
	
	private static final long serialVersionUID = -5579114102025310234L;
	
	public ProgressDialog(JFrame parent){
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setTitle("Diagramm wird erstellt...");
		setSize(448, 112);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JTextPane txtpnDasDiagrammWird = new JTextPane();
		txtpnDasDiagrammWird.setEditable(false);
		txtpnDasDiagrammWird.setText("Das Diagramm wird gerade erzeugt. Dieser Vorgang kann einige Zeit in Anspruch nehmen. Auch wenn es scheint als würde dieses Programm hängen, arbeitet es noch.");
		txtpnDasDiagrammWird.setBackground(SystemColor.menu);
		txtpnDasDiagrammWird.setHighlighter(null);
		txtpnDasDiagrammWird.setBounds(10, 11, 422, 34);
		getContentPane().add(txtpnDasDiagrammWird);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setBounds(10, 56, 422, 19);
		getContentPane().add(progressBar);
	}
}