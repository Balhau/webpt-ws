package org.pt.pub.data.ws;

import org.pt.pub.data.ws.service.ServiceDescription;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(Application.class, args);
    	//ServiceDescription sd=new ServiceDescription();
    	//sd.getControllers();
    }
}
