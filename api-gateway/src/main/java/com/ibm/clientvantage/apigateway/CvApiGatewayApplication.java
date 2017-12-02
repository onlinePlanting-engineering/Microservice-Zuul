package com.ibm.clientvantage.apigateway;

import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class CvApiGatewayApplication {
	
    @Autowired
    private static RefreshRouteService refreshRouteService;
    
	private static boolean mClusterActive = true;
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(CvApiGatewayApplication.class, args);
		
		Timer timer = new Timer(); 
        timer.schedule(new RetrieveClusterServices(), 15000, 5000);//start the task after 5 seconds, and execute every 5 seconds
	}
	
	static class RetrieveClusterServices extends java.util.TimerTask {
        public void run(){     
        		refreshRouteService.refreshRoute();
        }     
    }   
}
