package io;

import java.io.IOException;
import java.io.InputStream;
/**
* <h1>MyDecompressorInputStream</h1>
* This class is an adapter class that converts
* Maze3d to Searchable<Position>.
* 
* @author  Leibovitz Edan
* @version 1.0
* @since   2014-09-14
*/
public class MyDecompressorInputStream extends InputStream {
	private InputStream in;
	/**
	 * this method is a constructor that constructs a new input stream wrapped by
	 * a decompressor 
	 * 
	 * @param in - an input stream
	 * @return nothing
	 */	
	public MyDecompressorInputStream(InputStream in) {
		this.in = in;
	}
	/**
	 * this method reads a byte from the input stream after it got decompressed
	 * 
	 * @return a byte after it got decompressed
	 */	
	@Override
	public int read() throws IOException {		
		return in.read();
	}

	/**
	 * this method reads an array of bytes to the input stream after they got decompressed
	 * 
	 * @param b - a buffer array of bytes
	 * @return the amount of bytes that were read
	 */	
	@Override
	public int read(byte[] b) throws IOException
	{
		int curr = in.read(),cnt=0,j=0;
		while(curr!= -1) {
			cnt = in.read();
			while(cnt>0)
			{
				--cnt;
				b[j] = (byte)curr;
				++j;
			}
			curr = in.read();
		}	
		return 0;
	}
	
}
