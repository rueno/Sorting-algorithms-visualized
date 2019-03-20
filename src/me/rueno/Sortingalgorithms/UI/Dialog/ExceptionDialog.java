package me.rueno.Sortingalgorithms.UI.Dialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ExceptionDialog extends JDialog{
	
	private static final long serialVersionUID = -8479553276597292535L;
	private JTextArea textAreaException;
	
	public ExceptionDialog(Throwable error){
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		JPanel content = new JPanel();
		content.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(content);
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Upps, ein Fehler ist aufgetreten");
		setSize(550, 500);
		content.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 172, 524, 252);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollPane);
		
		textAreaException = new JTextArea();
		textAreaException.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		textAreaException.setWrapStyleWord(true);
		textAreaException.setLineWrap(true);
		textAreaException.setEditable(false);
		textAreaException.setForeground(new Color(255, 0, 0));
		textAreaException.setHighlighter(null);
		scrollPane.setViewportView(textAreaException);
		
		textAreaException.setText(error.toString() +  "\n");
		
		JButton btnTerminate = new JButton("Beenden");
		btnTerminate.addActionListener(e -> System.exit(0));
		btnTerminate.setFocusPainted(false);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 424, 524, 32);
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel_1);
		panel_1.add(btnTerminate);
		
		JLabel lblPikachu = new JLabel();
		lblPikachu.setBounds(379, 11, 150, 150);
//		lblPikachu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		lblPikachu.setIcon(new ImageIcon(new ImageIcon(ExceptionDialog.class.getResource("/resources/images/pikachuO.png")).getImage().getScaledInstance(lblPikachu.getWidth(),
				lblPikachu.getHeight(), Image.SCALE_SMOOTH)));
		content.add(lblPikachu);
		
		JTextArea txtrGeneralInformation = new JTextArea();
		txtrGeneralInformation.setBounds(5, 11, 364, 150);
		content.add(txtrGeneralInformation);
		txtrGeneralInformation.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtrGeneralInformation.setEditable(false);
		txtrGeneralInformation.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		txtrGeneralInformation.setText("Wie man durch Pikachu's Gesichtsausdruck vielleicht bereits erahnen kann, ist es während der Ausführung dieser Software zu einem unerwarteten und schwerwiegenden Fehler gekommen. Falls dies häufiger Auftritt, setze dich mit dem Entwicker dieser Software auseinander. Diesen erreichen Sie unter der Githubseite https://www.github.com/rueno/\r\n\r\nFolgender Fehler ist aufgetreten:");
		txtrGeneralInformation.setBackground(SystemColor.menu);
		txtrGeneralInformation.setWrapStyleWord(true);
		txtrGeneralInformation.setLineWrap(true);
		txtrGeneralInformation.setHighlighter(null);
		
		for(StackTraceElement element: error.getStackTrace()){
			textAreaException.append("at " + element.toString() + "\n");
		}
		
	}
}