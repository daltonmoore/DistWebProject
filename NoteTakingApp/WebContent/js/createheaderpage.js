var count = 0;

$(function(){
    $("#createHeader").click(function(){
        var headername = $("#headerName").val();
        var valid = true;
        $("#headers tr td:nth-child(1)").each(function(){
            var text = this.innerText.trim();
            if(text == headername)
            {
                valid = false;
            }
        });
        if(headername != "" && headername !=" " && valid)
        {
            var row = '<tr><td>'+headername+'</td></tr>';
            var btnPrefab = $('#btnClosePrefab').children()[0];
            var btn = $(btnPrefab).clone();
            $(btn).click(removeHeader);
            $(btn).css('float', 'left');
            //put the row in the table
            $('#headers tbody').append(row);
            //put the button in the row
            $('#headers tr:last').append(btn);
            //wrap the button in a col
            $(btn).wrap('<td></td>')

            //$('#submitHeaderForm').append('<input type="hidden" name=\"addedHeader' + count + '\" value=\"'+headername+'\">')
            //count++;
            //$('#headersAddedCount').val(count);

            $.ajax({
                url: "HeaderPage",
                type: "get",
                data: {headerToInsert: headername, username:$('#username').val()},
                contentType: "application/json; charset=utf-8",
                dataType: "text",
            });
        }
        else
            alert("invalid header name");
    });
});

function removeHeader()
{
    var value = $('tr:last td:first').text();

    $.ajax({
        url: "HeaderPage",
        type: "get",
        data: {headerName: value},
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        success: function(){
            
        },
        error: function(){
            alert('no');
        }
    });
    $('tr:last').remove();
}

function checkHeadersAdded()
{
    if($('#headersAddedCount').val() == 0)
    {
        alert("no new headers added");
        return false;
    }
    return true;
}