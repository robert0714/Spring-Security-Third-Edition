package com.packtpub.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.packtpub.springsecurity.domain.CalendarUser;
import com.packtpub.springsecurity.domain.Event;
import com.packtpub.springsecurity.service.CalendarService;
import com.packtpub.springsecurity.service.UserContext;
import com.packtpub.springsecurity.service.UserContextStub;

import javax.servlet.Filter;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import java.util.List;

@Configuration
public class TestConfig {

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Bean
    public MockMvc mockMvc() {
        return MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }
    
    @Bean
    public UserContext getUserContextStub() {
    	UserContext result =new UserContext() {

			@Override
			public CalendarUser getCurrentUser() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void setCurrentUser(CalendarUser user) {
				// TODO Auto-generated method stub
				
			}};
    	return result; 
    }
    @Bean
    public CalendarService getCalendarService() {
    	CalendarService result = new CalendarService() {

			@Override
			public CalendarUser getUser(int id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public CalendarUser findUserByEmail(String email) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<CalendarUser> findUsersByEmail(String partialEmail) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int createUser(CalendarUser user) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Event getEvent(int eventId) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int createEvent(Event event) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public List<Event> findForUser(int userId) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<Event> getEvents() {
				// TODO Auto-generated method stub
				return null;
			}};
    	return result;
    }

} // The End...
