package edu.autocar.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import edu.autocar.domain.BlogBoard;
import edu.autocar.domain.Comment;
import edu.autocar.domain.FileInfo;
import edu.autocar.domain.Member;
import edu.autocar.domain.Membership;
import edu.autocar.domain.PageInfo;
import edu.autocar.service.BlogService;
import edu.autocar.service.CommentService;
import edu.autocar.service.ImageService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@SessionAttributes("USER")
@RequestMapping("/gallery")

public class GalleryController {

	@Autowired
	BlogService blogService;
	@Autowired
	ImageService imageService;
	@Autowired
	CommentService commentService;

	@ModelAttribute("user")
	// 모든 URL 처리에 다 호출된다
	// 테스트를 위한 로그인 자동화
	public Member testUser(HttpSession session) {
		Member m = (Member) session.getAttribute("user");
		if (m == null) {
			m = new Member();
			m.setUserId("hong");
			session.setAttribute("user", m);
		}
		return m;
	}

	@GetMapping("/search")
	public void search(@RequestParam("keyword") String keyword, Model model) throws Exception {

		// List<Image> list = service.search(keyword);
		// model.addAttribute("list", list);
	}

	@GetMapping("/create")
	public String create(BlogBoard blogBoard, HttpSession session) throws Exception {
		if (session.getAttribute("USER") == null) {
			System.out.println("user log out / session out");
			return "redirect:list";
		}else {
			return "gallery/create";
		}
	}

	@PostMapping("/create")
	public String postCreate(@Valid BlogBoard blogBoard, BindingResult result, HttpSession session, MultipartHttpServletRequest request) throws Exception {
		log.info(blogBoard.toString());

		if (result.hasErrors()) {
			return "gallery/create";
		}

		Membership member = (Membership)session.getAttribute("USER");
		blogBoard.setBlogHost(member.getUserId());
		blogService.create(blogBoard, request.getFiles("files"));
		return "redirect:list?userId="+member.getUserId();
	}

	@GetMapping("/view/{boardId}")
	public String getBoard(@PathVariable int boardId, Model model,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "cmtPage", defaultValue = "1") int cmtPage) throws Exception {
		BlogBoard blogBoard = blogService.getBlog(boardId);
		model.addAttribute("blogBoard", blogBoard);

		// comments
		PageInfo<Comment> pi = commentService.getPage(boardId, page);
		model.addAttribute("pi", pi);
		return "gallery/view";
	}

	@PostMapping("/replyCreate/{boardId}/{page}")
	public String replyCreate(@PathVariable int boardId, HttpSession session, @PathVariable int page, Comment comment) throws Exception {
		Membership member = (Membership)session.getAttribute("USER");
		comment.setWriter(member.getUserId());
		comment.setPostId(boardId);
		commentService.insert(comment);
		return "redirect:../../view/" + boardId + "?page=" + page;
	}

	@GetMapping("/image/{imageId}")
	public String getImage(@PathVariable int imageId, Model model) throws Exception {
		FileInfo fi = imageService.getFileInfo(imageId);
		model.addAttribute("fileInfo", fi);
		return "image";
	}

	@GetMapping("/thumb/{imageId}")
	public String getThumb(@PathVariable int imageId, Model model) throws Exception {
		FileInfo fi = imageService.getThumbFileInfo(imageId);
		model.addAttribute("fileInfo", fi);
		return "image";
	}

	@GetMapping("/list")
	public void list(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "userId", defaultValue = "") String userId, Model model) throws Exception {

		PageInfo<BlogBoard> pi = blogService.getPage(userId, page);
		model.addAttribute("name", userId);
		model.addAttribute("pi", pi);
	}

	@GetMapping("/download/{imageId}")
	public String download(@PathVariable int imageId, Model model) throws Exception {
		FileInfo fi = imageService.getFileInfo(imageId);
		model.addAttribute("fileInfo", fi);
		return "download";
	}

	@DeleteMapping("/delete/{boardId}")
	@ResponseBody
	public ResponseEntity<Map<String, String>> delete(@PathVariable int boardId,
			@RequestParam(value = "password") String password) throws Exception {
		Map<String, String> map = new HashMap<>();

		if (imageService.deleteByGalleryId(boardId) > -1 && blogService.delete(boardId, password)) {
			map.put("result", "success");
		} else {
			map.put("result", "비밀번호가 일치하지 않습니다.");
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		return new ResponseEntity<Map<String, String>>(map, headers, HttpStatus.OK);
	}
}
