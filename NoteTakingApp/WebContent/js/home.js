$(function(){
	$('.searchbar').keyup(searchBar);
	$('.note').click(noteClick);
	$("#savenote").click(saveNote);
	$("#closenote").click(closeNote);
	
	$('#createnote').click(function(){
		var accountid = $('#accountId').val();
		var category = $('#newnotecategory').val();
		var title = $('#newnotetitle').val();
		var body = $('#newnotebody').val();
		
		//Json object
		var newnote = {
			NoteTitle: title,
			NoteContent: body,
			Color: '#ffffff',   //New note set to white by default
			AccountID: accountid,
    		CategoryID: category,
    		StatusID: '1'       //New note automatically added to notes page, not archive or trash
		}
		
		$.ajax({
            url: "NotesServlet",
            type: "get",
            data: {newnote: JSON.stringify(newnote)},
            contentType: "application/json; charset=utf-8",
            dataType: "JSON",
            success: function(obj){
            	$('div.grid.'+category).append(
            			'<div class=\"note\" onclick=\"noteClick(this)\" class=\"note\">\n'
        				+'<div class=\"noteTitle\">'+ obj.NoteTitle +'</div>\n'
        				+'<div class=\"noteContent\">'+ obj.NoteContent +'</div>\n'
        				+'<input type="hidden" class="noteId" value="'+ obj.NoteID +'"/>\n'
        				+'<input type="hidden" class="categoryId" value="'+ obj.CategoryID +'"/>\n'
        				+'<input type="hidden" class="color" value="'+ obj.Color +'"/>\n'
        				+'<input type="hidden" class="statusid" value="'+ obj.StatusID+'"/>\n'
  						+'</div>');
            }
		});
		
		cancelNewNote(); //hide new note view
	});
});


function sideBarClick(){
	
}


//Hide clicked note and populate modal values with relevant info
function noteClick(){
	
	var inputs = $(this).find('input');			//get hidden inputs for this note
	$("#noteId").val(inputs.eq(0).val());         //set modal hidden ids 
	$("#categoryId").val(inputs.eq(1).val());
	$("#color").val(inputs.eq(2).val());
	$("#statusId").val(inputs.eq(3).val());
	
	$(this).hide();   //hide this note
	$('#modal').show();    //show modal note
	
	$('#modal-title').text($(this).find('.noteTitle').text());   //Add this note content to modal and set editable
	$('#modal-title').attr('contenteditable',true);
	
	$('#modal-text').text($(this).find('.noteContent').text());   //Add this note content to modal and set editable
	$('#modal-text').attr('contenteditable',true);

}

function saveNote(){
	//Get modal data
	var modalTitle = $('#modal-title').text();
	var modalContent = $('#modal-text').text();
	var accountid = $('#accountId').val();
	var modalNoteId = $('#noteId').val();
	var modalCategoryId = $('#categoryId').val();
	var modalColor = $('#color').val();
	var modalStatusId = $('#statusId').val();

	
	//Create JSON Obj
	var updatenote = {
			NoteID: modalNoteId,
			NoteTitle: modalTitle,
			NoteContent: modalContent,
			Color: modalColor,   
			AccountID: accountid,
    		CategoryID: modalCategoryId,
    		StatusID: modalStatusId       
		}
	
	//Ajax call
	$.ajax({
        url: "NotesServlet",
        type: "get",
        data: {updatenote: JSON.stringify(updatenote)},
        contentType: "application/json; charset=utf-8",
        success: function(data){
        	//add Successful save custom alert
        }
	});
	
	//update hidden note fields
	$('.note .noteTitle:hidden').text(modalTitle);
	$('.note .noteContent:hidden').text(modalContent);
	
	//close modal note
	closeNote();
	
}

var visible = false;

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

//Close note without saving changes
function closeNote(){
	$('.note:hidden').show(); //Show hidden note
	$('#modal').hide();		//Hide modal 
}


//Show fields for creating a new note
function showNewNoteFields(){
	$('#newnotebtn').css('display', 'none');
	$('#newnotefields').css('display', 'block');
	$('#createnote').css('display', 'block');
	$('#cancelnote').css('display', 'block');
}

//Hide new note view
function cancelNewNote(){
	$('#newnotetitle').val("");
	$('#newnotebody').val("");
	$('#newnotebtn').css('display', 'block');
	$('#newnotefields').css('display', 'none');
	$('#createnote').css('display', 'none');
	$('#cancelnote').css('display', 'none');
}

//Search bar logic
function searchBar(){
	$('.note:not(:contains('+ $('.searchbar').val() +'))').css('display', 'none');
	$('.note:contains('+ $('.searchbar').val() +')').css('display', 'block');
}