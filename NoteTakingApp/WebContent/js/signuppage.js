$(function(){
    $('.p').change(validatePassword);
    $('.e').change(validateEmail);
    $('.signup').submit(checkFields);
});

function validatePassword()
{
    var password = document.getElementById("password");
    var confirm_password = document.getElementById("confirm_password");
    
    if(password.value != confirm_password.value) {
        confirm_password.setCustomValidity("Passwords Don't Match");
    } else {
        confirm_password.setCustomValidity('');
    }
}

function validateEmail()
{
    var email = document.getElementById("email");
    var confirm_email = document.getElementById("confirm_email");
    
    if(email.value != confirm_email.value) {
        confirm_email.setCustomValidity("Emails Don't Match");
    } else {
        confirm_email.setCustomValidity('');
    }
}

//this just makes sure there aren't any single quotes in any of the fields
function checkFields(e){
    var username = $('#username').val();
    var password = $('#password').val();
    var email = $('#email').val();
    var firstname = $('#firstname').val();
    var lastname = $('#lastname').val();

    if(username.includes("\'") || password.includes("\'") ||email.includes("\'") ||firstname.includes("\'") ||lastname.includes("\'"))
    {
        alert('None of your fields can have \' in it');
        e.preventDefault(e);
    }
}