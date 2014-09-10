import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.objdetect.CascadeClassifier;

import com.bonbalonzo.opencv.OpenCVLoader;
import com.bonbalonzo.opencv.detector.Detector;
import com.bonbalonzo.opencv.detector.HumanFaceDetector;
import com.bonbalonzo.opencv.face.Face;
import com.bonbalonzo.opencv.face.HumanFace;


public class Main {
	public static void main(String... args){
		OpenCVLoader openCVLoader = new OpenCVLoader();
		openCVLoader.loadOpenCV();
		Face humanFace = new HumanFace();
		Detector humanFaceDetector = new HumanFaceDetector();
		
		CascadeClassifier classifier = humanFace.faceClassifier("haarcascade_frontalface_alt.xml");
		Mat image = humanFace.imageFace("C:\\Users\\infiniteLoop\\Downloads\\picture.jpg");
		
		MatOfRect faceDetected = humanFaceDetector.detectMultiScale(image, classifier);
		image = humanFaceDetector.markDetected(image, faceDetected);
		
		humanFace.saveImage(image, "C:\\detectedFace.jpg");
	}
}
