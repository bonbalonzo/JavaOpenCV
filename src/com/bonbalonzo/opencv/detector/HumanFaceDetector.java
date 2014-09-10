package com.bonbalonzo.opencv.detector;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.objdetect.CascadeClassifier;

public class HumanFaceDetector implements Detector {
	
	public MatOfRect detectMultiScale(Mat image, CascadeClassifier faceClassifier) {
		MatOfRect detections = new MatOfRect();
		faceClassifier.detectMultiScale(image, detections);
		System.out.println(String.format("Detected %s faces", detections.toArray().length));
		return detections;
	}

	public Mat markDetected(Mat image, MatOfRect detections) {
		for(Rect rect : detections.toArray()){
			Core.rectangle(image, 
						   new Point(rect.x, rect.y), 
						   new Point(rect.x+rect.width, rect.y+rect.height),
						   new Scalar(0, 255,0));
		}
		return image;
	}

}
