$(function() {
		$('.decided , .end').click(function(){
				$('.notdevision').hide();
				$('.devisionend').show();
		});
});

$(function() {
		$('.undecided').click(function(){
				$('.devisionend').hide();
				$('.notdevision').show();
		});
});


function setName() {
	if ($('#hostNum option:selected').text() == ("選択してください")){
		$('#hostName').val("");
	}else{
		$('#hostName').val($('#hostNum option:selected').text());
	}
}

function setCategory() {
	if ($('#categoryNum option:selected').text() == ("選択してください")){
		$('#hostName').val("");
	}else{
		$('#categoryName').val($('#categoryNum option:selected').text());
	}
}


function newUser(){
		myRet = confirm("登録します。よろしいですか？");
		if ( myRet == true ){
				location.href = '/userregisted';
				alert("登録完了しました。");
		}
}

function eventRegist(){
		myRet = confirm("登録します。よろしいですか？");
		if ( myRet == true ){
			location.href= 'eventlist';
				alert("登録完了しました。");
		}
}

function voteRegist(){
	myRet = confirm("開催日を決定します。よろしいですか？");
		if ( myRet == true ){
				alert("決定しました。");
		}
}

function newVote() {
	myRet = confirm("投票します。よろしいですか？");
		if ( myRet == true ){
				alert("投票しました。");
		}
}

function updateEvent(){
	myRet = confirm("イベントの内容を変更します。よろしいですか？");
		if ( myRet == true ){
				document.getElementById('eventform').action = 'eventditailsupdate';
				alert("変更完了しました。");
		}
}

function deleteEvent(){
	myRet = confirm("イベントを削除します。よろしいですか？");
		if ( myRet == true ){
				document.getElementById('eventform').action = 'eventlistdelieted';
				alert("削除しました。");
		}
}




$(function(){

//モーダルウィンドウを出現させるクリックイベント
$("#modal-open").click( function(){

	//キーボード操作などにより、オーバーレイが多重起動するのを防止する
	$( this ).blur() ;	//ボタンからフォーカスを外す
	if( $( "#modal-overlay" )[0] ) return false ;		//新しくモーダルウィンドウを起動しない (防止策1)
	//if($("#modal-overlay")[0]) $("#modal-overlay").remove() ;		//現在のモーダルウィンドウを削除して新しく起動する (防止策2)

	//オーバーレイを出現させる
	$( "body" ).append( '<div id="modal-overlay"></div>' ) ;
	$( "#modal-overlay" ).fadeIn( "slow" ) ;

	//コンテンツをセンタリングする
	centeringModalSyncer() ;

	//コンテンツをフェードインする
	$( "#modal-content" ).fadeIn( "slow" ) ;

	//[#modal-overlay]、または[#modal-close]をクリックしたら…
	$( "#modal-overlay,#modal-close" ).unbind().click( function(){

		//[#modal-content]と[#modal-overlay]をフェードアウトした後に…
		$( "#modal-content,#modal-overlay" ).fadeOut( "slow" , function(){

			//[#modal-overlay]を削除する
			$('#modal-overlay').remove() ;

		} ) ;

	} ) ;

} ) ;

//リサイズされたら、センタリングをする関数[centeringModalSyncer()]を実行する
$( window ).resize( centeringModalSyncer ) ;

	//センタリングを実行する関数
	function centeringModalSyncer() {

		//画面(ウィンドウ)の幅、高さを取得
		var w = $( window ).width() ;
		var h = $( window ).height() ;

		// コンテンツ(#modal-content)の幅、高さを取得
		// jQueryのバージョンによっては、引数[{margin:true}]を指定した時、不具合を起こします。
//		var cw = $( "#modal-content" ).outerWidth( {margin:true} );
//		var ch = $( "#modal-content" ).outerHeight( {margin:true} );
		var cw = $( "#modal-content" ).outerWidth();
		var ch = $( "#modal-content" ).outerHeight();

		//センタリングを実行する
		$( "#modal-content" ).css( {"left": ((w - cw)/2) + "px","top": ((h - ch)/2) + "px"} ) ;

	}

} ) ;



