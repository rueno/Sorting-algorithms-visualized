package me.rueno.Sortingalgorithms.UI.Dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import me.rueno.Sortingalgorithms.Logic.ISortingAlgorithm;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.SystemColor;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.ScrollPaneConstants;
import java.awt.Toolkit;

public class AboutSortingAlgorithmDialog extends JDialog{
	
	private static final long serialVersionUID = 8754615513631930628L;
	
	public AboutSortingAlgorithmDialog(JFrame parent, ISortingAlgorithm algo){
		setIconImage(Toolkit.getDefaultToolkit().getImage(AboutSortingAlgorithmDialog.class.getResource("/resources/images/icons/icon_Info.png")));
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Über den " + algo.getName());
		setResizable(false);
		setSize(350, 500);
		setLocationRelativeTo(parent);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel buttonPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttonPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		JButton btnSchlieen = new JButton("Schließen");
		btnSchlieen.setFocusPainted(false);
		btnSchlieen.addActionListener(a -> {
			AboutSortingAlgorithmDialog.this.setVisible(false);
		});
		buttonPanel.add(btnSchlieen);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		
		JTextPane textPane = new JTextPane();
		textPane.setContentType("text/html");
		textPane.setEditable(false);
		textPane.setHighlighter(null);
		textPane.setText(algo.getAlgorithmInfoText());
//		textPane.setBackground(SystemColor.menu);
		scrollPane.setViewportView(textPane);
		
	}

}
