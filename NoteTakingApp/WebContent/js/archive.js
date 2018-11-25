//on document load
$(function(){
	$('.searchbar').keyup(searchBar);
	$('.note').click(noteClick);
	$("#savenote").click(saveNote);
	$("#closenote").click(closeNote);
	$('#trash').click(deleteNote);
	$('#archive').click(unArchiveNote);

	$('.header').each( function(i, header){
		if($(header).children().children().length == 0)
			$(header).hide();
	});
});

//Hide clicked note and populate modal values with relevant info
function noteClick(){
	var inputs = $(this).find('input');			//get hidden inputs for this note
	$("#noteId").val(inputs.eq(0).val());         //set modal hidden ids 
	$("#categoryId").val(inputs.eq(1).val());
	$("#color").val(inputs.eq(2).val());
	$("#statusId").val(inputs.eq(3).val());
	
	$(this).hide();   //hide this note
	$('#modal').show();    //show modal note
	
	$('#modal-title').html($(this).find('.noteTitle').html());   //Add this note content to modal and set editable
	$('#modal-title').attr('contenteditable',true);
	
	$('#modal-text').html($(this).find('.noteContent').html());   //Add this note content to modal and set editable
	$('#modal-text').attr('contenteditable',true);
	
	$('#modal-content').css('background-color',inputs.eq(2).val());

	$('#changecolor').css('background-color',inputs.eq(2).val());
	
}

//Send note changes to Servlet
function saveNote(){
	//Get modal data
	var modalTitle = $('#modal-title').html();
	var modalContent = $('#modal-text').html();
	var accountid = $('#accountId').val();
	var modalNoteId = $('#noteId').val();
	var modalCategoryId = $('#categoryId').val();
	var modalColor = $('#changecolor').val();
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
        	console.log(data);
        }
	});
	
	//update hidden note fields
	var note = $('.note:hidden');
	note.find('.noteTitle').html(modalTitle);
	note.find('.noteContent').html(modalContent);
	note.find('.color').val(modalColor);
	note.css('background-color',modalColor);
	console.log(modalColor);

	//close modal note
	closeNote();
}

$.expr[":"].contains = $.expr.createPseudo(function(arg){
	return function(elem){
		return $(elem).text().toUpperCase().indexOf(arg.toUpperCase()) >= 0;
	};
});

//Close note without saving changes
function closeNote(){
	$('.note:hidden').show(); //Show hidden note
	$('#modal').hide();		//Hide modal 
}

//Search bar logic
function searchBar(){
	$('.note:not(:contains('+ $('.searchbar').val() +'))').css('display', 'none');
	$('.note:contains('+ $('.searchbar').val() +')').css('display', 'block');
}
//Delete selected note
function deleteNote(){
	var noteId = $('#noteId').val();

	$.ajax({
		url: "NotesServlet",
		method: "get",
		data: { deleteId: noteId},
		success: function(data){
			console.log(data);
		}
	});
	var header = $('.note:hidden').closest('.header');
	$('.note:hidden').remove();
	$('#modal').hide();	//Hide modal
}

function unArchiveNote(){
	var modalTitle = $('#modal-title').html();
	var modalContent = $('#modal-text').html();
	var accountid = $('#accountId').val();
	var modalNoteId = $('#noteId').val();
	var modalCategoryId = $('#categoryId').val();
	var modalColor = $('#color').val();
	var modalStatusId = $('#statusId').val();

	var archivednote = {
		NoteID: modalNoteId,
		NoteTitle: modalTitle,
		NoteContent: modalContent,
		Color: modalColor,   
		AccountID: accountid,
		CategoryID: modalCategoryId,
		StatusID: modalStatusId       
	}

	$.ajax({
		url: "NotesServlet",
		method: "get",
		data: { archivedNote: JSON.stringify(archivednote)},
		success: function(data){
			var header = $('.note:hidden').closest('.header');
			$('.note:hidden').remove();
			$('#modal').hide();	//Hide modal
			$('#archivedNoteAlert').show();
			checkIfHeaderHasNotes(header);
			//setTimeout(function(){$('#archivedNoteAlert').hide();}, 1000);
		},
		error: function(){
			console.log("failed");
		}
	});
}

function update(jscolor) {
    //$('#color').val('#' + jscolor);
    $('#modal-content').css('background-color','#'+jscolor);
}