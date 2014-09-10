package com.bonbalonzo.opencv;

import org.opencv.core.Core;

public class OpenCVLoader {
	public void loadOpenCV(){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		System.out.println("OpenCV loaded");
	}
}
