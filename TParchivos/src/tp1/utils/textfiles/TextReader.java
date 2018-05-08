package tp1.utils.textfiles;

import java.io.IOException;

public interface TextReader {
	
	public boolean isReady();
	public String readLine();
	public void close();

}
