package com.api;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}



}

/*
i can use this class as a configuration class.
@Bean annotation is very useful, when dependency injection does not happen.we can use @Bean.
educate ioc which object to create for which object variable.As the class name match here
e.g; I want to copy a data from an Entity to Dto and Dto to Entity.So,in my project I had use modelMapper Library.
when I try to dependency injection for ModelMapper library I got a message error creating a @Bean.
this message I got it because Ioc is unable to create an Object for this particular class .
so,I had to configure,stating that which object to be created for this reference variable.
I use this annotation @Bean,because of which now the dependency Injection was performed.and IOC was aware of which object to create.

@configuration files are special file where the project is started,the Config files will first loaded in springBoot memory.
Even before any parts of Code run.that how default it is designed.
@SpringBootApplication also runs first because its a Configuration file.
 */