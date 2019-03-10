package com.hit.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CacheUnitView extends Observable implements View{

	private JFrame frame;
	private JButton openFileButton, cacheMemory,clearLogButton;
	private JTextArea textArea;
		
	public CacheUnitView() {
		createGUI();
	}
	
	@Override
	public void start() {
		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public <T> void updateUIData(T t) {	
		textArea.setText(t.toString() + "\n" + this.textArea.getText());
	}
	
	private void createGUI() {		
		//Create and set up the window.
        frame = new JFrame("ButtonExample");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create and set up the content pane.
        openFileButton = new JButton("Load a Request");
        openFileButton.setIcon(new ImageIcon("images\\iconOpen.png"));
        openFileButton.setPreferredSize(new Dimension(400, 50));
        
        ActionListener openButtonActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setCurrentDirectory(new File("resources"));
				int result = jFileChooser.showOpenDialog(new JFrame());
				
				if (result == JFileChooser.APPROVE_OPTION) {
			            File selectedFile = jFileChooser.getSelectedFile();
			            textArea.setText("Selected request: " + selectedFile.getAbsolutePath() + "\n" + textArea.getText()); 
			            setChanged();
			            notifyObservers("fileSelected,"+selectedFile.getAbsolutePath());
				}	
			}
		};
		this.openFileButton.addActionListener(openButtonActionListener);
        
        cacheMemory = new JButton("cacheMemory");
        cacheMemory.setIcon(new ImageIcon("images\\cachingIcon.png"));
        cacheMemory.setPreferredSize(new Dimension(400, 50));
        ActionListener statisticsButtonActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				setChanged();
	            notifyObservers("serverStatus");		
			}
		};
		cacheMemory.addActionListener(statisticsButtonActionListener);
        
        clearLogButton = new JButton("CLEAR LOG");
        clearLogButton.setIcon(new ImageIcon("images\\iconClear.png"));
		ActionListener clearLogButtonActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				URL soundbyte;
				try {
					soundbyte = new File("sound\\ButtonPress.wav").toURI().toURL();
					java.applet.AudioClip clip = java.applet.Applet.newAudioClip(soundbyte);
					clip.play();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				textArea.setText("");	
			}
		};
		clearLogButton.addActionListener(clearLogButtonActionListener);
        
        textArea = new JTextArea();
		textArea.setPreferredSize(new Dimension(800,500));
		textArea.setEditable(false);
		
		frame.getContentPane().add(openFileButton,BorderLayout.LINE_START);
		frame.getContentPane().add(cacheMemory,BorderLayout.CENTER);
		frame.getContentPane().add(textArea,BorderLayout.PAGE_END);
		frame.getContentPane().add(clearLogButton, BorderLayout.LINE_END);
	}

}
