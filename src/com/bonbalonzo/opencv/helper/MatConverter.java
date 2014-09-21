package com.bonbalonzo.opencv.helper;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

import org.opencv.core.Mat;

public interface MatConverter {

	BufferedImage convertMatToBufferedImage(Mat mat);
	
}
