package tc3.tp3.utils.db;

public class DBEnvironment {

	private static final String CONN_STRING_TEMPLATE="jdbc:%s://%s:%s;database=%s;user=%s;password=%s";
	private static final String K_PASSWORD="password";
	private static final String K_USERNAME="username";
	private static final String K_DATABASE="database";
	private static final String K_PORT="port";
	private static final String K_SERVER="server";
	private static final String K_DRIVER="driver";
	private static final String K_ENVIRONMENTNAME="environmentName";
	private static final String ERR_INVALID_ATTRIBUTE="Nombre del Atributo invalido";
	private static final String ERR_INVALID_ATT_VALUE="Valor Invalido para el atributo %s ('%s')";
	private static final String UNDEFINED="[UNDEFINED]";
	
	
	
	
	
	private String environmentName;
	private String driver;
	private String server;
	private String port;
	private String database;
	private String username;
	private String password;
	
	
	public String getEnvironmentName() {
		return environmentName;
	}
	private void setEnvironmentName(String environmentName) throws IllegalArgumentException{
		this.checkValue(K_ENVIRONMENTNAME, environmentName);
	
		this.environmentName = environmentName;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver)  throws IllegalArgumentException  {
		this.checkValue(K_DRIVER, driver);

		this.driver = driver;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) throws IllegalArgumentException {
		this.checkValue(K_SERVER, server);
		
		this.server = server;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) throws IllegalArgumentException {
		this.checkValue(K_PORT, port);
		
		this.port = port;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database)throws IllegalArgumentException {
		this.checkValue(K_DATABASE, database);

		this.database = database;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		if(username.isEmpty()||username==null) username=UNDEFINED;
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		if(password.isEmpty()||password==null) password=UNDEFINED;
		
		this.password = password;
	}
	public String getURL(){
		//"jdbc:%s://%s:%s;database=%s;user=%s;password=%s"
		return String.format(CONN_STRING_TEMPLATE,getDriver(),getServer(),getPort(),getDatabase(),getUsername(),getPassword());
	}
	public DBEnvironment(String environmentName){
		this.setEnvironmentName(environmentName);
	}
	
	public DBEnvironment(String environmentName, String driver, String server, String
			port, String database, String username, String password)
	{
		this.setEnvironmentName(environmentName);
		this.setValues(driver, server, port, database, username, password);

		
	}
	public void setValueOf(String attribute, String value) throws IllegalArgumentException
	{
		switch (attribute){
		case K_ENVIRONMENTNAME: this.setEnvironmentName(value);
			break;
		case K_DRIVER:this.setDriver(value);
			break;
		case K_SERVER:this.setServer(value);
			break;
		case K_DATABASE:this.setDatabase(value);
			break;
		case K_USERNAME: this.setUsername(value);
			break;
		case K_PASSWORD:this.setPassword(value);
			break;			
			
		} 
		
			
		
	}
	
	protected void setValues(String driver, String server, String port, String database,String username, String password){
		this.setDriver(driver);
		this.setServer(server);
		this.setPort(port);
		this.setDatabase(database);
		this.setUsername(username);
		this.setPassword(password);
	}
	private void checkValue( String fieldName, String fieldValue)throws IllegalArgumentException{
		if(fieldValue.isEmpty()||fieldValue==null) throw new IllegalArgumentException(String.format(ERR_INVALID_ATT_VALUE, fieldName,fieldValue));
		
	}
		
}
