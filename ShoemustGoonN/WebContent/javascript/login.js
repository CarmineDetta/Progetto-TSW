/**
 * 
 */
var email = document.getElementById("email");
var loginButton = document.getElementById("loginButton");
var password = document.getElementById("password");

loginButton.addEventListener("click", function() {
    ValidateEmail(email);
    ValidatePassword(password);
});

function ValidateEmail(inputText) {
    var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if (inputText.value.match(mailformat)) {
        return true;
    } else {
        alert("Hai inserito un indirizzo email non valido!");
        return false;
    }
}

function ValidatePassword(inputText) {
    // Controlla se la lunghezza è maggiore di 4 caratteri
    if (inputText.value.length > 4) {
        // Controlla se contiene almeno un carattere maiuscolo
        var uppercaseRegex = /[A-Z]/;
        if (uppercaseRegex.test(inputText.value)) {
            return true; // Password valida
        }
    } else {
        alert("La password non è valida!");
    }
}

