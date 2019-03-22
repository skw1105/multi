package edu.autocar.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.autocar.domain.Membership;
import edu.autocar.domain.ResultMsg;
import edu.autocar.service.MembershipService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MembershipController {
	
	@Autowired
	MembershipService service;
	
	@GetMapping("/id-check/{userId}")
	@ResponseBody
	public ResponseEntity<ResultMsg> checkId(@PathVariable String userId) throws Exception {
		if (service.getMembership(userId) == null) {
			return ResultMsg.response("ok", "사용가능한 ID 입니다.");
		} else {
			return ResultMsg.response("duplicate", "이미 사용중인 ID 입니다.");
		}
	}
	
	@GetMapping("/join")
	public String getJoin(Membership membership) throws Exception {
		return "member/join";
	}
	
	@PostMapping("/join")
	public String postJoin(@Valid Membership membership, BindingResult result, RedirectAttributes ra)
			throws Exception {
		if (result.hasErrors()) {
			return "member/join";
		}
		
		service.create(membership);
		
		// 리다이렉트 페이지에 정보 전달 - Flash Attribute 운영
		ra.addFlashAttribute("membership", membership);

		return "redirect:/join_success"; // redirect하면 클라이언트가 다시 요청함
	}
	
	@GetMapping("/join_success")
	public String joinSuccess() {
		return "member/join_success"; // tile-layout에 {1}/{2}
	}
	
//	@GetMapping("/member/view")
//	public void view(Model model, HttpSession session) throws Exception {
//		Membership user = (Membership) session.getAttribute("USER");
//		
//		Membership membership = service.getMembership(user.getUserId());
//		model.addAttribute("membership", membership);
//	}
//	
//	@GetMapping("/member/edit")
//	public void getEdit(Model model, HttpSession session) throws Exception {
//		view(model, session);
//	}
//	
//	@PostMapping("/member/edit")
//	public String postEdit(@Valid Membership membership, BindingResult result, HttpSession session) throws Exception {
//		if (result.hasErrors())
//			return "member/edit";
//		
//		if (service.update(membership)) {
//			// 수정 성공
//			// 수정된 회원 정보로 세션 수정
//			membership = service.getMembership(membership.getUserId());
//			session.setAttribute("USER", membership);
//			
//			return "redirect:/member/view";
//		} else {
//			// 수정 실패
//			FieldError fieldError = new FieldError("member", "password", "비밀번호가 일치하지 않습니다");
//			result.addError(fieldError);
//			
//			return "member/edit";
//		}
//	}
}
