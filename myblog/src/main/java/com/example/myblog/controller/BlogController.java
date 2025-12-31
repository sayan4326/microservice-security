package com.example.myblog.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlogController {

	@GetMapping("/view-blog")
	@ResponseBody
	public String viewBlog() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
	    String name = oauth2User.getAttribute("name");
	    return "<h1>Welcome to My Blogs, " + name + "!</h1>";
	}
}
