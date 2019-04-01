package me.rueno.Sortingalgorithms.UI.Dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import me.rueno.Sortingalgorithms.Logic.ISortingAlgorithm;
import me.rueno.Sortingalgorithms.UI.Components.AnimatedImageLabel;

import java.awt.BorderLayout;
import java.awt.Desktop;

import javax.swing.JButton;
import java.awt.FlowLayout;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.ScrollPaneConstants;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

public class AboutSortingAlgorithmDialog extends JDialog{
	
	private static final long serialVersionUID = 8754615513631930628L;
	
	private JLabel lblShortAni;
	
	public AboutSortingAlgorithmDialog(JFrame parent, ISortingAlgorithm algo){
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				if(lblShortAni instanceof AnimatedImageLabel){
					((AnimatedImageLabel) lblShortAni).terminate();
				}
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(AboutSortingAlgorithmDialog.class.getResource("/resources/images/icons/iconInfo.png")));
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
		textPane.setCaretPosition(0);
		scrollPane.setViewportView(textPane);
		
		textPane.addHyperlinkListener(e -> {
			if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED){
				try{
					Desktop.getDesktop().browse(new URL(e.getDescription()).toURI());
				}catch(Exception ignore){
					new ExceptionDialog(ignore);
				}
			}
		});
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		contentPanel.add(panel, BorderLayout.NORTH);
		
		JLabel lblShort = new JLabel("Kurzzusammenfassung:");
		panel.add(lblShort);
		
		lblShortAni = algo.getGif();
		
		if(lblShortAni != null){
			panel.add(lblShortAni);
		}else{
			panel.add(new JLabel("Nicht angegeben!"));
		}
		
	}

}