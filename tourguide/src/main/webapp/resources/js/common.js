// deletePanel 플러그인
$.fn.deletePanel = function(opt) {
	var self = this;
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
	self.append(templ);
	var password = self.find(':password');
	
	$(opt.triger).click(()=>self.show()); 
	self.on('click', '.cancel', ()=>{ 
		password.val(''); self.hide(); 
	}) 
	self.on('submit', 'form', function(e){
		e.preventDefault(); 
		if(!confirm("삭제할까요?")) return;
		
		axious.delete(opt.url + '?password=' + password.val()).then(function(obj){
			//obj : axious의 결과값
			//obj.data : 서버 리턴 결과 값
			if(obj.data.result == 'success'){
				location = opt.moveUrl;
			}else{
				alert(obj.data.message);
			}
		}).catch(console.log);
	}); 
}
