package me.rueno.Sortingalgorithms.UI.Dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Cursor;
import java.awt.Desktop;

import javax.swing.JEditorPane;
import java.awt.Toolkit;
import java.net.URL;

public class AboutProgramDialog extends JDialog{
	
	private static final long serialVersionUID = 264776817309156477L;
	
	public AboutProgramDialog(JFrame parent){
		Desktop desktop = Desktop.getDesktop();
		setIconImage(Toolkit.getDefaultToolkit().getImage(AboutProgramDialog.class.getResource("/resources/images/icons/iconAbout.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setTitle("Über diese Software");
		setSize(372, 503);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnSchlieen = new JButton("Schließen");
		btnSchlieen.setFocusPainted(false);
		btnSchlieen.addActionListener(a -> {
			AboutProgramDialog.this.setVisible(false);
		});
		panel.add(btnSchlieen);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JEditorPane dtrpnberDieseSoftware = new JEditorPane();
		dtrpnberDieseSoftware.setContentType("text/html");
		dtrpnberDieseSoftware.setText("<html>\r\n<h2 style=\"text-align:center;margin:2px;font-family:sans-serif;\">Über diese Software</h2>\r\n<h3 style=\"text-align:center;font-family:sans-serif;\">Entwickler:</h3>\r\n<p style=\"margin:2px;font-family:sans-serif;\">Diese Software wurde entwickelt von Simon Rüsweg, BG68I (Abiturjahrgang 2019)</p><br>\r\n<h3 style=\"text-align:center;margin:2px;font-family:sans-serif;\">Rechtliches:</h3>\r\n<p style=\"margin:2px;font-family:sans-serif;\">Alle Icons dieser Software sind zu Verfügung gestellt von <a href=\"https://www.icons8.de/\">icons8.de</a>.</p>\r\n<h3 style=\"text-align:center;font-family:sans-serif;\">Abhängigkeiten:</h3>\r\n<ul>\r\n<li><p style=\"font-family:sans-serif;\"> <a href=\"http://www.jfree.org/jcommon/\">JCommon</a></p></li>\r\n<li><p style=\"font-family:sans-serif;\"><a href=\"http://www.jfree.org/jfreechart/\">JFreeChart</a></p></li>\r\n</ul><br>\r\n</html>");
		dtrpnberDieseSoftware.setEditable(false);
		dtrpnberDieseSoftware.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		dtrpnberDieseSoftware.setHighlighter(null);
		dtrpnberDieseSoftware.setBorder(new LineBorder(Color.BLACK, 1));
		dtrpnberDieseSoftware.addHyperlinkListener(new HyperlinkListener(){
			
			@Override
			public void hyperlinkUpdate(HyperlinkEvent e){
				if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED){
					try{
						desktop.browse(new URL(e.getDescription()).toURI());
					}catch(Exception ignore){
						new ExceptionDialog(ignore);
					}
				}
			}
		});
		scrollPane.setViewportView(dtrpnberDieseSoftware);
	}
}