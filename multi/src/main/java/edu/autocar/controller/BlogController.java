package edu.autocar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/blogs")
public class BlogController {

	@GetMapping("/")
	public String list() throws Exception {
		System.out.println("test body sysout");
		return "blog/list";
		  
	}
}
