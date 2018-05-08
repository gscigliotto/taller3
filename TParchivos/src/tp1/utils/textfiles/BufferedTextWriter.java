package tp1.utils.textfiles;


import java.io.BufferedWriter;


import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class BufferedTextWriter implements TextWriter {
	
	private BufferedWriter bw=null;
	
	
	public BufferedTextWriter(String path) throws IOException {
		
		try {
			setBw(new BufferedWriter(new FileWriter(path)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			throw e;
		}
	}
	
	private BufferedWriter getBw() {
		return bw;
	}

	private void setBw(BufferedWriter bw) {
		this.bw = bw;
	}

	@Override
	public void writeLine(String txt) {
		try {
			getBw().write(txt);
			getBw().newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	

	}

	@Override
	public void close() {
		try {
			getBw().close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean isReady() {
		return getBw()!=null;
	}

}
