package com.bonbalonzo.opencv.detector;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.objdetect.CascadeClassifier;

public interface Detector {
	MatOfRect detectMultiScale(Mat image, CascadeClassifier faceClassifier);
	Mat markDetected(Mat image, MatOfRect detections);
}
