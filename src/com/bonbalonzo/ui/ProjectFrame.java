package com.bonbalonzo.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.objdetect.CascadeClassifier;

import com.bonbalonzo.opencv.OpenCVLoader;
import com.bonbalonzo.opencv.detector.Detector;
import com.bonbalonzo.opencv.detector.HumanFaceDetector;
import com.bonbalonzo.opencv.face.Face;
import com.bonbalonzo.opencv.face.HumanFace;
import com.bonbalonzo.opencv.helper.MatConverter;
import com.bonbalonzo.opencv.helper.MatConverterImpl;

public abstract class ProjectFrame extends JFrame implements ActionListener,Runnable  {
	private JPanel contentPane;
	private JButton chooseButton;
	private JButton processButton;
	private File selectedFile;

	/**
	 * Create the frame.
	 */
	public ProjectFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.SOUTH);
		
		chooseButton = new JButton(getChooseButtonName());
		chooseButton.addActionListener(this);
		toolBar.add(chooseButton);
		
		processButton = new JButton(getProcessButtonName());
		processButton.addActionListener(this);
		toolBar.add(processButton);
		
		contentPane.add(getPanel(), BorderLayout.CENTER);		
		
		selectedFile = null;
	}
	
	abstract void processFile(File file) throws IOException;
	abstract String getChooseButtonName();
	abstract String getProcessButtonName();
	abstract JPanel getPanel();
	
	abstract File chooseFile() throws IOException;

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if(getChooseButtonName().equals(actionCommand)){
			try {
				selectedFile = chooseFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if(getProcessButtonName().equals(actionCommand)){
			if(selectedFile != null){
				try {
					processFile(selectedFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	@Override
	public void run() {
		try {
			ImageJFrame frame = new ImageJFrame();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
