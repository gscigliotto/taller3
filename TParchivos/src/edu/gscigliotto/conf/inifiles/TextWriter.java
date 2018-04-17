package edu.gscigliotto.conf.inifiles;

public interface TextWriter {
	public boolean isReady();
	public String writeLine();
	public void close();
}
