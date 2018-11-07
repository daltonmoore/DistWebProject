$(function(){
    $("#createHeader").click(function(){
        var headername = $("#headerName").val();
        var colorSelect = $("#colorSelect").html();
                
        if(headername != "" && headername !=" " && $("#headers tr > td:contains("+ headername +")").length == 0)
        {
            $('#headers').append(
                '<tr class=row><td>'+headername+'</td>'+'<td>'+colorSelect+'</td></tr>'
            )
            $('.row').append($('<button/>',
            {
                class: 'close',
                click: function () { alert('hi'); }
            }));
            $('.close').append("<span aria-hidden=\"true\">&times;</span>");
        }
        else
            alert("invalid header name");
    });
});