var modal;
var modalText;
var modalTitle;
var modalContent;
var colorSelect;
var note;

$(function(){
	modal = $('#modal');
	modalText = $('#modal-text');
	modalTitle = $('#modal-title');
	modalContent = $('#modal-content');
	var jscolorpicker = $(modalContent).children('.noteBtns').children('.jscolor');

	$('.searchbar').keyup(searchBar);

	$('#createnote').click(function(){//start of create note btn click
		var accountid = $('#accountId').val();
		var category = $('#newnotecategory').val();
		var title = $('#newnotetitle').val();
		var body = $('#newnotebody').val();
		
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
        				+'<input type="hidden" id="noteId" value="'+ obj.NoteID +'"/>\n'
        				+'<input type="hidden" id="categoryId" value="'+ obj.CategoryID +'"/>\n'
        				+'<input type="hidden" id="color" value="'+ obj.Color +'"/>\n'
        				+'<input type="hidden" id="statusid" value="'+ obj.StatusID+'"/>\n'
  						+'</div>');
            }
		});
	});//end of create note btn click
	
	$('.note').click(function(e){//start of note btn click
		//if they clicked the archive button
		if(e.target == $(this).children()[6])
			return;

		note = $(this);
		$(modal).css('display', 'block');
		jscolorpicker.val($(note).children('#color').val());//get color attribute from hidden note input and set color picker value to it
		$(modalContent).css('background-color', jscolorpicker.val()) //change background of modal-content to value
		jscolorpicker.css('background-color', jscolorpicker.val());//cahnge color of the color picker to the hex val in it
		$(modalTitle).html(note.children()[0].textContent);
		$(modalText).html(note.children()[1].textContent);
		$(modalTitle).prop('contenteditable', true)
		$(modalText).prop('contenteditable', true)
	});//end of note btn click

	$('#closeNote').click(function(){//start of close note btn click
		$('#modal').css('display', 'none');
	});//end of close note btn click
	$('.note').click(noteClick);
	$("#savenote").click(saveNote);
	$("#closenote").click(closeNote);
	$('#createnote').click(createNote);
	$('#newnotebtn').click(showNewNoteFields);
	$('#cancelnote').click(cancelNewNote);
	$('#modal-text').keyup(countCharacters);
	$('#trash').click(deleteNote);
});

	$('#saveNote').click(function(){//start of save note btn click
		var tempNote = {
			NoteID: $(note).children('#noteId').val(),
			NoteTitle: $(modalTitle).text(),
			NoteContent: $(modalText).text(),
			Color: jscolorpicker.val(),
			AccountID: $('#accountId').val(),
			CategoryID: $(note).children('#categoryId').val(),
			StatusID: $(note).children('#statusId').val()
		}
	
		//push changes to database
		$.ajax({
			url: "NotesServlet",
			type: "get",
			data: {saveNote: JSON.stringify(tempNote)},
			contentType: "application/json; charset=utf-8",
			dataType: "JSON",
			success: function(){
				$(note).children('.noteTitle').text($(modalTitle).text());
				$(note).children('.noteContent').text($(modalText).text());
				$(note).children('#color').val(jscolorpicker.val());
				$('#updateLog').text('Saved Note');
				setTimeout(function(){
					$('#updateLog').text('Saved Note...')
				}, 500);
				setTimeout(function(){
					$('#updateLog').text('')
				}, 2000);
			},
			error: function(){
				alert("fail");
			}
		});
	});//end of save note btn click

	$('#archiveNote').click(function(){
		$(modal).css('display', 'none');
		
		var tempNote = {
			NoteID: $(note).children('#noteId').val(),
			NoteTitle: $(note).children('.noteTitle').text(),
			NoteContent: $(note).children('.noteContent').text(),
			Color: color,
			AccountID: $('#accountId').val(),
			CategoryID: 0,
			StatusID: 2
		}
	
		//push note archive status to database
		$.ajax({
			url: "NotesServlet",
			type: "get",
			data: {clickedNote: JSON.stringify(tempNote)},
			contentType: "application/json; charset=utf-8",
			dataType: "JSON",
			success: function(obj){
				
			},
			error: function(){
				alert("fail");
			}
		});
		$(note).css('display', 'none');
	});

	$('.archiveBtn').click(function(){
		alert('test');
	});

	//change all notes to their colors
	$.each($('.note'), function(){
		$(this).css('background-color', $(this).children('#color').val());
	});
//Send note changes to Servlet
function saveNote(){
	//Get modal data
	var modalTitle = $('#modal-title').html();
	var modalContent = $('#modal-text').html();
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
        	console.log(data);
        }
	});
	
	//update hidden note fields
	$('.note .noteTitle:hidden').html(modalTitle);
	$('.note .noteContent:hidden').html(modalContent);

	//close modal note
	closeNote();
}
	$('#newnotebtn').click(showNewNoteFields);

	$('#cancelnote').click(cancelNewNote);
var visible = false;

$.expr[":"].contains = $.expr.createPseudo(function(arg){
	return function(elem){
		return $(elem).text().toUpperCase().indexOf(arg.toUpperCase()) >= 0;
	};
});

function changeColor(){//jscolor picker calls this
	var color = '#'+$('.jscolor').val();
	$(modalContent).css('background-color', color);
	$(note).children('#color').val(color)
	$(note).css('background-color', color)

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
	
	$('.note:hidden').remove();
	$('#modal').hide();		//Hide modal 
	
	
}
