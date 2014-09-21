package com.bonbalonzo.opencv.face;

import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

public class HumanFace implements Face {

	@Override
	public Mat imageFace(String pathImage) {
		return Highgui.imread(pathImage);
	}
	
	@Override
	public CascadeClassifier faceClassifier(String classifierPath) {
		return new CascadeClassifier(classifierPath);
	}

	@Override
	public void saveImage(Mat image, String savePath) {
		Highgui.imwrite(savePath, image);
		System.out.println("Image saved Location: " + savePath);
	}

}
