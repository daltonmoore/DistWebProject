var count = 0;

$(function(){
    $("#createHeader").click(function(){
        var headername = $("#headerName").val();
        var colorSelect = $("#colorSelect").html();
                
        if(headername != "" && headername !=" " && $("#headers tr > td:contains("+ headername +")").length == 0)
        {
            var row = '<tr><td>'+headername+'</td>'+'<td><select>'+colorSelect+'</select></td></tr>';
            var btn = $('<button>',{
                click:removeHeader,
                class:'close',
            });
            $(btn).css('float', 'left')
            //put the x icon in the button
            btn.append('<span aria-hidden="true">&times;</span>');
            //put the row in the table
            $('#headers tbody').append(row);
            //put the button in the row
            $('#headers tr:last').append(btn);
            //wrap the button in a col
            $(btn).wrap('<td></td>')

            $('#submitHeaderForm').append('<input type="hidden" name=\"addedHeader' + count + '\" value=\"'+headername+'\">')
            count++;
            $('#headersAddedCount').val(count);
        }
        else
            alert("invalid header name");
    });
});

function removeHeader()
{
    $('tr:last').remove();
}