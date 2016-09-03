package org.pub.pt.data.ws.service;

import java.util.List;

/**
 * Domain object with the representation of the service
 * @author balhau
 *
 */
public class ServiceDescriptionDomain {
	private String className;
	private String controllerName;
	private List<String> arguments;
	private String controllPath;
	
	public ServiceDescriptionDomain(){
		
	}
	
		

	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}


	public String getControllerName() {
		return controllerName;
	}

	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}

	public List<String> getArguments() {
		return arguments;
	}

	public void setArguments(List<String> arguments) {
		this.arguments = arguments;
	}

	public String getControllPath() {
		return controllPath;
	}

	public void setControllPath(String controllPath) {
		this.controllPath = controllPath;
	}
	
	
}
