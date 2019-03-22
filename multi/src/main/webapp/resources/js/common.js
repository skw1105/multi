/**
 * 
 */

//deletePanel 플러그인
$.fn.deletePanel = function(opt) {
	
	var templ = `
<form class="my-3">
비밀번호 :
<input type="password" name="password" required>
<button type="submit" class="btn btn-primary btn-sm">
<i class="fas fa-times"></i> 삭제
</button>
<button type="button" class="btn btn-primary btn-sm cancel">
<i class="fas fa-undo"></i> 취소
</button>
</form>`;
	
	var self = this;
	self.hide();
	
	self.append(templ);
	var password = self.find(':password');
	// 이벤트 핸들러 등록
	//$(opt.triger).click(()=>self.show());
	var triger = $(opt.triger);
	
	var url;
	
	triger.click(function(){
		var userId = $(this).data('user-id');
		if (userId) {
			url = opt.url.replace('\{user-id\}', userId);
		} else {
			if ($(this).data('mode') == 'multiple') { // 선택 삭제
				var items = [];
				$(opt.multiple).each(function(){
					items.push($(this).val()); // 선택된 사용자 ID 배열에 추가
				});
			} else { // view 페이지에서 삭제
				url = opt.url;
			}
		}
	});
	
	self.on('click', '.cancel', ()=>{
		password.val('');
		self.hide();
	})
	
	self.on('submit', 'form', function(e) {
		e.preventDefault();
		if(!confirm("삭제할까요?")) return;
		// Ajax로 delete 요청
		axios.delete(opt.url + '?password=' + password.val())
			.then(function(obj) {
				if(obj.result == 'success') {
					location = opt.moveUrl;
				} else {
					alert(data.result);
				}
			}).catch(console.log);
	});
}



