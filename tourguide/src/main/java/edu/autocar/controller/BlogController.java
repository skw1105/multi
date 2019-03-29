package edu.autocar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.autocar.domain.BlogBoard;
import edu.autocar.domain.Membership;
import edu.autocar.service.BlogService;
import edu.autocar.service.MembershipService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	MembershipService service;
	
	@Autowired
	BlogService blogService;
	
	// 전체 블로그 목록
	@GetMapping("/list")
	public void list(Model model) throws Exception {
		System.out.println("blog list");
		List<Membership> ml = service.getMembershipList();
		model.addAttribute("ml", ml);
	}
	
	// 해당 블로그 보기
	@GetMapping("/{userId}/list")
	public String view(@PathVariable String userId, Model model) throws Exception {
		System.out.println("post/list.jsp를 연다");
//		Membership membership = service.getMembership(userId);
//		model.addAttribute("membership", membership);
		
		List<BlogBoard> blogBoardList = blogService.getBlogBoardList(userId);
		model.addAttribute("blogBoardList", blogBoardList);
		return "post/list";
	}
}
