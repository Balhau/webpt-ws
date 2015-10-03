package org.pt.pub.data.ws;

public class ServiceInfo {
	public String version;
	public String author;
	public String copyright;
	public String description;
	
	public ServiceInfo(
			String version,String author,String copyright,String description
	){
		this.version=version;this.author=author;
		this.copyright=copyright;this.description=description;
	}
}
