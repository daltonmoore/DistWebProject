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