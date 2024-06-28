// 로그아웃
function logout(){
	
	if(confirm("로그아웃 하시겠습니까?")) {
		location.href = "./login.do"
	} 
}

// 단일 삭제
function del(event){
	event.preventDefault();
	
	var frm = document.forms[0];
	
	if(confirm("글을 삭제하시겠습니까?")){
		frm.action = "./deleteBoard.do";
		frm.method = "post";
		frm.submit();
	} else{
		alert("삭제가 취소되었습니다");
	}
	
}

// 다중 삭제
function adminDel(event){
	
	var frm = document.forms[0];
	
	if(chCheckedCnt() == 0) {
		event.preventDefault();
		alert("아무것도 선택되지 않았습니다.");
	} else{
		if(confirm("삭제 하시겠습니까?")){
			frm.action = "./multiDeleteBoard.do";
			frm.method = "post";
			frm.submit();
		} else{
			alert("삭제가 취소되었습니다.");
		}
	}
}

// 수정
function modify(event){
	event.preventDefault();
	
	var frm = document.forms[0];
	
	
	var seq = document.querySelector("input[name=seq]").value;
	
	if(confirm("글을 수정하시겠습니까?")){
		frm.action = "./updateBoard.do?seq=" + seq;
		frm.submit();
	}
}

// 전체 체크
function chkAll(bool){
	var chks = document.getElementsByName("chk");
	for(let i=0; i<chks.length; i++){
		chks[i].checked = bool;
	}
}

// 하위 체크 박스 갯수 확인
var chCheckedCnt = function(){
	var chks = document.getElementsByName("chk");
	let cnt = 0;
	for(let i=0; i<chks.length; i++){
		if(chks[i].checked){
			cnt++;
		}
	}
	return cnt;
}

// 체크 상태에 따른 최상단 체크박스 상태 변경
window.onload = function(){
	var chks = document.getElementsByName("chk");
	var allchk = document.getElementById("allchk");
	
	for(let i=0; i<chks.length; i++){
		chks[i].onclick = function(){
			if(chks.length == chCheckedCnt()){
				allchk.checked = true;
			} else{
				allchk.checked = false;
			}
		}
	}
}

// 글 작성
function insert(event){
	event.preventDefault();
	var frm = document.forms[0];
	
	var title = document.getElementById("title");
	var content = document.getElementById("content");
	
	if(title.value == "" || content.value == ""){
		alert("필수값을 모두 입력해주세요");
	} else{
		alert("글 작성 완료");
		frm.action = "./insertBoard.do"
		frm.method = "post";
		frm.submit();
	}
}

// 글 수정
function update(event){
	event.preventDefault();
	var frm = document.forms[0];
	
	var content = document.getElementById("content");
	
	if(content.value == ""){
		alert("내용을 필수로 입력해주세요");
	} else{
		alert("수정 완료");
		frm.action = "./updateBoard.do"
		frm.method = "post";
		frm.submit();
	}
}

function idChk(event){
	event.preventDefault();
	
	var frm = document.forms[0];
	
	var id = document.getElementById("id");
	
	if(id.value == "" || id.value.trim() == "") {
		document.getElementById("notMember").innerHTML = "<span style='color:red;'>아이디는 필수값입니다</span>";
		id.value = "";
		id.focus();
	} else{
		$.ajax({
			url:"./idCheck.do",
			data:{"id":id.value},
			dataType:"json",
			success:function(msg){
				
			},
			error:function(){
				alert("잘못된 요청 처리");
			}
		});
	}
}