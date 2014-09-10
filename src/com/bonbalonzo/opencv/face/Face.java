package com.bonbalonzo.opencv.face;

import org.opencv.core.Mat;
import org.opencv.objdetect.CascadeClassifier;

public interface Face {
	Mat imageFace(String pathImage);

	CascadeClassifier faceClassifier(String classifierPath);
	
	void saveImage(Mat image, String savePath);
}
