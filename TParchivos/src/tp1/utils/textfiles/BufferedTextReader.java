package tp1.utils.textfiles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferedTextReader implements TextReader {

	private BufferedReader br = null;
	
	

	private BufferedReader getBr() {
		return br;
	}

	private void setBr(BufferedReader br) {
		this.br = br;
	}

	public BufferedTextReader(String path) throws FileNotFoundException {
		
		try {
			setBr(new BufferedReader(new FileReader(path)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}

	@Override
	public boolean isReady() {
		return getBr()!=null;


	}

	@Override
	public String readLine()  {
		String ret;
		
		try {
			ret= getBr().readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ret=null;
		}
		return ret;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		try {
			getBr().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}

	}

}
