package com.bonbalonzo.opencv.face;

import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

public class HumanFace implements Face {

	public Mat imageFace(String pathImage) {
		return Highgui.imread(pathImage);
	}

	public CascadeClassifier faceClassifier(String classifierPath) {
		return new CascadeClassifier(classifierPath);
	}

	public void saveImage(Mat image, String savePath) {
		Highgui.imwrite(savePath, image);
		System.out.println("Image saved Location: " + savePath);
	}

}
