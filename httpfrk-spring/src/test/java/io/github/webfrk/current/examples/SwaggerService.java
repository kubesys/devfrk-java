package io.github.webfrk.current.examples;


import org.springframework.web.bind.annotation.RequestBody;

import io.github.kubesys.devfrk.spring.cores.AbstractHttpHandler;
import io.github.kubesys.devfrk.tools.annotations.ServiceDefinition;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;


@ServiceDefinition
public class SwaggerService extends AbstractHttpHandler {
	
	public String echoString(String name) {
		return "Hello " + name + "!";
	}
	
	public User echoObject(@RequestBody @Valid User user) {
		return user;
	}
	
	public static class User {
		
		@Size(min = 5, max = 20)
		protected String name;
		
		@Min(0)
	    @Max(100)
		protected int age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println(User.class.getSimpleName());
	}
}