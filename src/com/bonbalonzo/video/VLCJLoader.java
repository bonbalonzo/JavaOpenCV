package com.bonbalonzo.video;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.x.LibXUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class VLCJLoader {
	public void loadVLCJ(){
		NativeLibrary.addSearchPath(
                RuntimeUtil.getLibVlcLibraryName(), "c:/Program Files/VideoLAN/VLC/");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        LibXUtil.initialise();
        System.out.println("video player loaded");
	}
}
