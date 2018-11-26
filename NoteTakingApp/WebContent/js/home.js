//on document load
$(function(){
	$('.quickTrash').hide();
	$('.quickArchive').hide();
	$('.searchbar').keyup(searchBar);
	$('.note').click(noteClick);
	$('.note').hover(noteHover);
	$('.note').mouseleave(noteLeave);
	$('.quickTrash').click(quickDeleteNote);
	$('.quickArchive').click(quickArchiveNote);
	$("#savenote").click(saveNote);
	$("#closenote").click(closeNote);
	$('#createnote').click(createNote);
	$('#newnotebtn').click(showNewNoteFields);
	$('#cancelnote').click(cancelNewNote);
	$('#modal-text').keyup(countCharacters);
	$('#trash').click(deleteNote);
	$('#archive').click(archiveNote);

	$('.header').each( function(i, header){
		if($(header).children().children().length == 0)
			$(header).hide();
	});
});

function noteHover(){
	$(this).children('.quickTrash').show();
	$(this).children('.quickArchive').show();
}

function noteLeave(){
	$(this).children('.quickTrash').hide();
	$(this).children('.quickArchive').hide();
}

function quickArchiveNote(){
	$('#modal').hide();	//Hide modal
	var archivednote = {
		NoteID: $(this).parent().children('.noteId').val(),
		NoteTitle: $(this).parent().children('.noteTitle').text(),
		NoteContent: $(this).parent().children('.noteContent').text(),
		Color: $(this).parent().children('.color').val(),   
		AccountID: $(this).parent().children('#accountId').val(),
		CategoryID: $(this).parent().children('.categoryId').val(),
		StatusID: $(this).parent().children('.statusId').val()       
	}
	console.log($(this).parent().children('.noteTitle').val());

	var note = $(this).parent();
	$.ajax({
		url: "NotesServlet",
		method: "get",
		data: { archivedNote: JSON.stringify(archivednote)},
		success: function(data){
			console.log($(this));
			var header = $(note).closest('.header');
			$(note).remove();
			$('#archivedNoteAlert').show();
			checkIfHeaderHasNotes(header);
			//setTimeout(function(){$('#archivedNoteAlert').hide();}, 1000);
		},
		error: function(){
			console.log("failed");
		}
	});
}

function quickDeleteNote(){
	$('#modal').hide();	//Hide modal
	var noteId = $(this).parent().children('.noteId').val()

	$.ajax({
		url: "NotesServlet",
		method: "get",
		data: { deleteId: noteId},
		success: function(data){
			console.log(data);
		}
	});
	var header = $(this).parent().closest('.header');
	$(this).parent().remove();
	
	//check if header has zero notes
	checkIfHeaderHasNotes(header);
}

//Counte modal-text characters to keep under 255
function countCharacters(){
	var count = $('#modal-text').text().length;
	
	if(count > 235 && count < 255){
		$('#savenote').attr('disabled',false);
		$('#charcounter').css('color','gold');
		$('#charcounter').text(255-count);
	}else if(count == 255){
		$('#savenote').attr('disabled',false);
		$('#charcounter').css('color','red');
		$('#charcounter').text(255-count);
	}else if(count > 255){
		$('#savenote').attr('disabled',true);
		$('#charcounter').css('color','red');
		$('#charcounter').text(255-count);
	}else {
		$('#charcounter').text("");
	}
}

//Create new note function
function createNote(){
	
	var accountid = $('#accountId').val();
	var category = $('#newnotecategory').val();
	var title = $('#newnotetitle').val();
	var body = $('#newnotebody').html();
	
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
					'<div class=\"note\">\n'
    				+'<div class=\"noteTitle\">'+ obj.NoteTitle +'</div>\n'
    				+'<div class=\"noteContent\">'+ obj.NoteContent +'</div>\n'
    				+'<input type="hidden" class="noteId" value="'+ obj.NoteID +'"/>\n'
    				+'<input type="hidden" class="categoryId" value="'+ obj.CategoryID +'"/>\n'
    				+'<input type="hidden" class="color" value="'+ obj.Color +'"/>\n'
					+'<input type="hidden" class="statusId" value="'+ obj.StatusID+'"/>\n'
					+'<input type=\"button\" class=\"quickArchive\" value=\"Archive\">'
					+'<input type=\"button\" style=\"display:none;\"class=\"quickTrash\" value=\"Trash\">'
						+'</div>');
        	//Update noteClick
			$('.note').click(noteClick);
			$('.note').hover(noteHover);
			$('.note').mouseleave(noteLeave);
			$('.quickTrash').click(quickDeleteNote);
			$('.quickArchive').click(quickArchiveNote);
        }
	});
	//ensure that header is showing if it has notes under it
	var c = $('#newnotecategory :selected').text();
	$("[id='"+ c +"']").show();
	cancelNewNote(); //hide new note view
}

//Hide clicked note and populate modal values with relevant info
function noteClick(e){
	if(!($(e.target).hasClass('note')))
	{
		if($(e.target).hasClass('noteContent') || $(e.target).hasClass('noteTitle'))
		{
			
		}
		else
		{
			console.log("clicked other btn");
			return;
		}
	}
	$(this).hide();   //hide this note
	var inputs = $(this).find('input');			//get hidden inputs for this note
	$("#noteId").val(inputs.eq(0).val());         //set modal hidden ids 
	$("#categoryId").val(inputs.eq(1).val());
	$('#color').val(inputs.eq(2).val());
	$("#statusId").val(inputs.eq(3).val());

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
	var modalColor = $('#changecolor').val()
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
	$('#newnotebody').html("");
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
	
	//check if header has zero notes
	checkIfHeaderHasNotes(header);
}

function archiveNote(){
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

function checkIfHeaderHasNotes(header){
	//the header doesn't have any children
	if($(header).children().children().length == 0){
		header.hide();

		//i don't know why i did this code here, i'll just ignore it for now

		// var catid = header.children().attr('class').split(' ')[1] //get the category id which is stored in the grid's second class
		// $.ajax({
		// 	url: "NotesServlet",
		// 	method: "get",
		// 	data: { categoryIDToDelete: catid, categoryNameToDelete: header.attr('id')},
		// 	success: function(data){
		// 		console.log("Successfully Delete Empty Categories");
		// 	}
		// });
	}
}

function update(jscolor) {
    //$('#color').val('#' + jscolor);
    $('#modal-content').css('background-color','#'+jscolor);
}