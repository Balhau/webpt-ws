package org.pt.pub.data.ws.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.pt.pub.data.ws.RestDescription;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class uses reflection to iterate over all loaded classes, identify the controller classes and then 
 * it builds a service overview. This is usefull to give a quick and clear presentation about all the services
 * presented to the user
 * @author balhau
 *
 */
public class ServiceDescription {
	
	private static final String PACKAGE_SERVICE_NAME=RestDescription.class.getPackage().getName();
	
	public ServiceDescription(){
		
	}
	
	public List<ServiceDescriptionDomain> getControllers() throws Exception{
		List<ServiceDescriptionDomain> services=new ArrayList<ServiceDescriptionDomain>();
		Class<?> cls=SpringApplication.class.getClassLoader().getClass();
		while(cls!=ClassLoader.class){
			cls=cls.getSuperclass();
		}
		Field f = cls.getDeclaredField("classes");
		f.setAccessible(true);
		Vector<Class<?>> classes =  (Vector<Class<?>>) f.get(SpringApplication.class.getClassLoader());
		for(Class<?> lclass : classes){
			if(lclass.getName().contains(PACKAGE_SERVICE_NAME)){
				describeClass(lclass,services);
			}
		}
		return services;
	}
	
	private void describeClass(Class<?> loadedClass,List<ServiceDescriptionDomain> list){
		Method[] methods=loadedClass.getMethods();
		RequestMapping aux;
		if(loadedClass.getAnnotation(RestController.class)!=null){
			aux=(RequestMapping)loadedClass.getAnnotation(RequestMapping.class);
			String cpath=aux!=null?aux.toString():"";
			for(Method method:methods){
				if(isController(method)){
					ServiceDescriptionDomain d=new ServiceDescriptionDomain();
					d.setArguments(extractMethodVariable(method));
					d.setClassName(loadedClass.getName());
					d.setControllerName(method.getName());
					d.setControllPath(cpath);
					list.add(d);
				}
			}
		}
	}
	
	private List<String> extractMethodVariable(Method method){
		Parameter[] params=method.getParameters();
		List<String> nparams=new ArrayList<String>();
		for(Parameter param : params){
			nparams.add(param.getName()+":"+param.getType().getName());
		}
		return nparams;
	}
	
	private boolean isController(Method method){
		return method.getAnnotation(RequestMapping.class)!=null;
	}
}
