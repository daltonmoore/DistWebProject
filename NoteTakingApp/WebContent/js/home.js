function sideBarClick(){
	
}

var note;

function noteClick(item){
	var modal = document.getElementById("modal");
	var modalText = document.getElementById("modal-text");
	var modalTitle = document.getElementById("modal-title");
	note = item;
	item.style.display = "none";
	modal.style.display = "block";
	var noteChildren = note.childNodes;
	modalText.innerHTML = noteChildren[3].textContent;
	modalTitle.innerHTML = noteChildren[1].textContent;
	modalText.contentEditable = "true";
	modalTitle.contentEditable = "true";
}
var visible = false;

document.addEventListener("click", function(event){
	var box = document.getElementById("options");
	var out = document.getElementById("out");
	out.innerHTML = event.target.closest(".options");
	if(event.target.closest(".options") || event.target.closest(".optionbutton")){
		visible = true;
		return;
	}
	box.style.display = "none";
	visible = false;
});

$(function(){
	$('.searchbar').keyup(searchBar);
});

$.expr[":"].contains = $.expr.createPseudo(function(arg){
	return function(elem){
		return $(elem).text().toUpperCase().indexOf(arg.toUpperCase()) >= 0;
	};
});


function changeColor(){
	
}

function noteOptions(){
	var optionMenu = document.getElementById("options");
	
	if(!visible){
		optionMenu.style.display = "block";
		visible = true;
	}
	else if(visible){
		visible = false;
		optionMenu.style.display = "none";
	}
}

function closeNote(){
	var modal = document.getElementById("modal");
	var modalText = document.getElementById("modal-text");
	var modalTitle = document.getElementById("modal-title");
	var noteChildren = note.childNodes;
	
	noteChildren[3].innerHTML = modalText.textContent;
	noteChildren[1].innerHTML = modalTitle.textContent;
	
	modal.style.display = "none";
	note.style.display = "block";
	
}

function showNewNoteFields(){
	$('#newnotebtn').css('display', 'none');
	$('#newnotefields').css('display', 'block');
	$('#createnote').css('display', 'block');
	$('#cancelnote').css('display', 'block');
}

function createNewNote(){
	var category = $('#newnotecategory').val();
	var title = $('#newnotetitle').val();
	var body = $('#newnotebody').val();
	var temp = $('<td><div class=\"note\">'
		+'<div class=\"noteTitle\">'+ title +'</div>'
		+'<div class=\"noteContent\">'+ body +'</div>'
		+'</div></td>');
	$('#test tr:last').append($(temp));
}

function cancelNewNote(){
	$('#newnotebtn').css('display', 'block');
	$('#newnotefields').css('display', 'none');
	$('#createnote').css('display', 'none');
	$('#cancelnote').css('display', 'none');
}

function searchBar(){
	$('.note:not(:contains('+ $('.searchbar').val() +'))').css('display', 'none');
	$('.note:contains('+ $('.searchbar').val() +')').css('display', 'block');
}