package io.github.webfrk.nat.exampls;


import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/swagger")
@Controller
@Tag(name = "SwaggerService", description = "Swagger服务")
public class SwaggerService {
	
	@PostMapping("/echoHello")
	@Operation(summary = "打印Hello",description = "post请求")
	@ResponseBody
	public String echoHello( /* 参数注解 */
			@Size(min = 5) String name) {
		return "Hello " + name + "!";
	}
	
	@GetMapping("/echoHello1")
	@Operation(summary = "打印Hello1",description = "get请求")
	@ResponseBody
	public String echoHello1( /* 参数注解 */
			@Size(min = 5) String name,
			@Size(min = 5) String habit) throws Exception {
		throw new Exception("assddd");
	}
	
	@PostMapping("/echoHello2")
	@Operation(summary = "打印Hello2", description = "post对象请求")
	@ResponseBody
	public User echoHello2(@RequestBody @Valid User user) {
		return user;
	}
	
	public static class User {
		
		@Size(min = 1, max = 20)
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