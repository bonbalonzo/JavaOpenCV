package com.bonbalonzo.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileCacheImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.codec.binary.Base64;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.objdetect.CascadeClassifier;

import com.bonbalonzo.opencv.OpenCVLoader;
import com.bonbalonzo.opencv.helper.MatConverter;
import com.bonbalonzo.opencv.detector.Detector;
import com.bonbalonzo.opencv.detector.HumanFaceDetector;
import com.bonbalonzo.opencv.face.Face;
import com.bonbalonzo.opencv.face.HumanFace;
import com.bonbalonzo.opencv.helper.MatConverterImpl;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JToolBar;

import java.awt.Button;

public class ImageJFrame extends ProjectFrame {

	private JPanel panel;
	private JLabel imageLabel;
	
	private final String CHOOSE_IMAGE = "Open Image";
	private final String PROCESS_IMAGE = "Detect Face";

	/**
	 * Create the frame.
	 */
	public ImageJFrame() {
		super();
		imageLabel = new JLabel();
		panel.add(imageLabel);
	}
	
	@Override
	void processFile(File file) throws IOException {
		OpenCVLoader openCVLoader = new OpenCVLoader();
		openCVLoader.loadOpenCV();
		Face humanFace = new HumanFace();
		Detector humanFaceDetector = new HumanFaceDetector();
		
		CascadeClassifier classifier = humanFace.faceClassifier("haarcascade_frontalface_alt.xml");
		String imagePath = file.toPath().toString();
		System.out.println(imagePath);
		Mat image = humanFace.imageFace(imagePath);
		
		MatOfRect faceDetected = humanFaceDetector.detectMultiScale(image, classifier);
		image = humanFaceDetector.markDetected(image, faceDetected);
		
		loadImage(getMatConverter().convertMatToBufferedImage(image));
	}

	@Override
	String getChooseButtonName() {
		return CHOOSE_IMAGE;
	}

	@Override
	String getProcessButtonName() {
		return PROCESS_IMAGE;
	}

	@Override
	File chooseFile() throws IOException {
		final JFileChooser imageChooser = new JFileChooser();
		File selectedFile = null;
		int returnValue = imageChooser.showOpenDialog(this);
		if(JFileChooser.APPROVE_OPTION == returnValue){
			selectedFile = imageChooser.getSelectedFile();
			loadFileImage(selectedFile);
		}
		return selectedFile;
	}
	
	private void loadFileImage(File fileImage) throws IOException{
		BufferedImage imageBufferReader = ImageIO.read(fileImage);
		loadImage(imageBufferReader);
	}
	
	private void loadImage(BufferedImage imageBufferReader) throws IOException{
		imageLabel.setIcon(new ImageIcon(imageBufferReader));
		this.pack();
	}
	
	private MatConverter getMatConverter(){
		return  new MatConverterImpl();
	}

	@Override
	JPanel getPanel() {
		if(panel == null){
			panel = new JPanel();
		}
		return panel;
	}

}
