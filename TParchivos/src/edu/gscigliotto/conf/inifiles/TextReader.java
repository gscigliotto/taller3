package edu.gscigliotto.conf.inifiles;

public interface TextReader {
	
	public boolean isReady();
	public String readLine();
	public void close();

}
