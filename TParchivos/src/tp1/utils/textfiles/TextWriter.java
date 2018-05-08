package tp1.utils.textfiles;

public interface TextWriter {


	public void close();
	public boolean isReady();
	void writeLine(String txt);
}
