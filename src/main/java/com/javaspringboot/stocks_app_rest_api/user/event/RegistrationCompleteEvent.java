package com.javaspringboot.stocks_app_rest_api.user.event;

import com.javaspringboot.stocks_app_rest_api.user.entity.UserTbl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
// is a custom event class that inherits from the ApplicationEvent class,
// which is the base class for all application events in Spring
//By extending this class,
// you can create your own event types that can be published and listened by other components in the Spring context.
public class RegistrationCompleteEvent extends ApplicationEvent {
    private UserTbl user;
    private String applicationUrl;

    //constructor for instantiating objects
    public RegistrationCompleteEvent(UserTbl user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }


}
