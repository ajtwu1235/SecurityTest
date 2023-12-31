package com.example.SecurityTest.controller;

import com.example.SecurityTest.domain.User;
import com.example.SecurityTest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
// @CrossOrigin  // CORS 허용 
public class RestApiController {
	
	private final UserRepository userRepository;

	
	// 모든 사람이 접근 가능
	@GetMapping()
	public String home() {
		return "index";
	}
	
	// Tip : JWT를 사용하면 UserDetailsService를 호출하지 않기 때문에 @AuthenticationPrincipal 사용 불가능.
	// 왜냐하면 @AuthenticationPrincipal은 UserDetailsService에서 리턴될 때 만들어지기 때문이다.
	
	// 유저 혹은 매니저 혹은 어드민이 접근 가능

	@GetMapping("/user")
	@ResponseBody
	public String user() {

		return "<h1>user</h1>";
	}

	// 매니저 접근 가능
	@GetMapping("/manager")
	public String reports() {
		return "<h1>reports</h1>";
	}
	
	// 어드민이 접근 가능
	@ResponseBody
	@GetMapping("/admin")
	public List<User> users(){
		return userRepository.findAll();
	}
	
	@PostMapping("join")
	public String join(@RequestBody User user) {

		user.setRoles("ROLE_ADMIN");
		userRepository.save(user);
		return "회원가입완료";
	}

	
}











