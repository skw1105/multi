/**
 * 
 */

$.fn.checkUserId = function(){
	var self = this;
	// this :
	// $(선택자).checkUserId()에서 호출되므로 button 생성자
	// this로 이벤트 헨들러 핸들 못하므로 self로
	
	var idInput = this.closest('.form-group').find('input');
	// user id 입력
	var msgSpan = this.next() // 처리결과 출력 msg
	var submitBtn = $(':submit');
	
	self.click(function(){
		var userId = idInput.val();
		
		// empty check!
		if(!userId) return alert("pleaes input user id");
		$.get('admin/member/id-check/' + userId, function(data){
			if(data.result == 'ok'){
				// 성공시 : 중복 없다.
				msgSpan.html(data.message).removeClass('error');
				submitBtn.prop("disabled", false); // submit 버튼 활성화
				self.prop('disabled', true)// ID check button 비활성화
			} else {
				msgSpan.html(data.message).addClass('error');
				submitBtn.prop('disabled', true);
			}
		});
	});
	
	// 검사 성공 후에도 만약 id를 변경하여 입력한다면..
	idInput.change(function(){
		msgSpan.html('ID 중복 체크 해야 합니다.').removeClass('error');
		submitBtn.prop('disabled', true);
		self.prop('disabled',false); // ID check btn 활성화
	});
}

// 비밀번호 체크 플러그인
// this : form
$.fn.checkPassword = function(pass1='#password', pass2='#password2') {
	this.submit(function(e) {
		e.preventDefault();
		var password1 = $(pass1).val();
		var password2 = $(pass2).val();
		if(password1 == password2) {
			this.submit();
		} else {
			alert('비밀번호가 일치하지 않습니다.');
		}
	});
}