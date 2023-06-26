/**
 * 
 */
var email = document.getElementById("email");
var loginButton = document.getElementById("loginButton");
var password = document.getElementById("password");

loginButton.addEventListener("click", function() {
    ValidateEmail(email);
    passid_validation(password);
});

function ValidateEmail(inputText) {
    let mailformat = /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/;
    if (inputText.value.match(mailformat)) {
        return true;
    } else {
        alert("Hai inserito un indirizzo email non valido!");
        return false;
    }
}

function passid_validation(passid,mx,my) {
    let passid_len = passid.value.length;
    if (passid_len == 0 ||passid_len >= my || passid_len < mx) {
        alert("Password should not be empty / length be between "+mx+" to "+my);
        passid.focus();
        return false;
    }
    return true;
}

