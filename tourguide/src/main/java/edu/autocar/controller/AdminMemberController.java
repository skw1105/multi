package edu.autocar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import edu.autocar.domain.ResultMsg;
import edu.autocar.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin/member")
@Slf4j
public class AdminMemberController {
	@Autowired
	MemberService service;

	@GetMapping("/id-check/{userId}")
	@ResponseBody
	//view를 통한 응답이 아닌, 내가 직접 내보내겠다
	public ResponseEntity<ResultMsg> checkId(@PathVariable String userId) throws Exception {
		//한글이 포함된 경우 responseentity 활용
		System.out.println("check-id : " + userId);
		if (service.getMember(userId) == null) {
			return ResultMsg.response("ok", "사용가능한 ID 입니다.");
		} else {
			return ResultMsg.response("duplicate", "이미 사용중인 ID 입니다.");
		}
	}
}