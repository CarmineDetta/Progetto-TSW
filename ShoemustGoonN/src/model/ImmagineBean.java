package model;

public class ImmagineBean {
	
	private String nomeImmagine;
	private String path;
	
	public ImmagineBean(){
		this.nomeImmagine ="";
		this.path = "";
	}
	
	public String getnomeImmagine() {
		return nomeImmagine;
	}
	
	public void setnomeImmagine(String n) {
		this.nomeImmagine = n;
	}
	
	public String getPath(){
		return path;
	}
	
	public void setPath(String p) {
		this.path = p;
	}
}
