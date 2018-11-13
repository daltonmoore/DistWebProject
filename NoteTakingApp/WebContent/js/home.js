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
	var temp = $('<div class=\"note\" onclick=\"noteClick(this)\" class=\"note\">\n'
		+'<div class=\"noteTitle\">'+ title +'</div>\n'
		+'<div class=\"noteContent\">'+ body +'</div>\n'
		+'</div>');
	$('div.grid.'+category).append($(temp));
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