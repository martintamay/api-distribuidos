package com.sma.delivery.scheduling;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sma.delivery.mailers.PromotionsSender;
import com.sma.delivery.service.users.IUserService;
import com.sma.delivery.utils.ProyectProperties;

@Service("promotionsSchedulerService")
public class PromotionsScheduler {
	private static final Logger LOGGER = Logger.getLogger( ProyectProperties.class.getName() );
	@Autowired
	private IUserService userService;

    public void scheduleTest() {
        LOGGER.log(Level.INFO, "Tarea Ejecutada");
    }
    
    public void sendPromotions() {
    	new PromotionsSender().send(userService);
    }
}
