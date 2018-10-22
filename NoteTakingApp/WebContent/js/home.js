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
})


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