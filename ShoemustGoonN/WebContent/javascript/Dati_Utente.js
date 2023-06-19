/**
 * 
 */

function formValidation() {
	var scelta = document.querySelector('select[name="scelta"]').value;
    var valore = document.getElementById("valore");

    if(scelta === "email"){
    	ValidateEmail(valore);
    } else if(scelta === "password") {
    	passid_validation(scelta, 7, 12);
    }
    
    return false;
}


function ValidateEmail(inputText)
{
    var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if  (inputText.value.match(mailformat))
       {
        return true;
       } else {
                alert("You have entered an invalid email address!");
                return false;
              }
}

function passid_validation(passid,mx,my) {
    var passid_len = passid.value.length;
    if (passid_len == 0 ||passid_len >= my || passid_len < mx) {
        alert("Password should not be empty / length be between "+mx+" to "+my);
        passid.focus();
        return false;
    }
    return true;
}