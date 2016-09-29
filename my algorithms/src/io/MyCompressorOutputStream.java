package io;

import java.io.IOException;
import java.io.OutputStream;
/**
* <h1>MyCompressorOutputStream</h1>
* This class is of type OutputStream, implemented the decorator
* design pattern.
* 
* @author  Leibovitz Edan
* @version 1.0
* @since   2014-09-14
*/
public class MyCompressorOutputStream extends OutputStream {
	private OutputStream out;
	/**
	 * this method is a constructor that constructs a new output stream wrapped by
	 * a compressor 
	 * 
	 * @param out - a output stream
	 * @return nothing
	 */	
	public MyCompressorOutputStream(OutputStream out) {
		this.out = out;
	}
	/**
	 * this method writes a byte to the output stream after it got compressed
	 * 
	 * @param b - a byte
	 * @return nothing
	 */	
	@Override
	public void write(int b) throws IOException {
		out.write(b);
	}
	/**
	 * this method writes an array of bytes to the output stream after they got compressed
	 * 
	 * @param b - an array of bytes
	 * @return nothing
	 */	
	@Override
	public void write(byte[] b) throws IOException{
		int len = b.length, count = 0;
		byte prev = 0;

		for(int i=0; i<4; i++){
			out.write(len%255);
			len=len/255;
		}
		int i=0;
		while (i < b.length){
			if(b[i] != prev){
				if(count > 0){
					out.write(prev);
					out.write(count);
				}
				count = 1;
				prev = b[i];
	
			}else{
				++count;
				if(count == 255){
					out.write(prev);
					out.write(count);
					count = 0;
				}
			}
			i++;
		}	
		if(count>0){	
			out.write(prev);
			out.write(count);
		}
	}

}