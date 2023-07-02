/**
 * 
 */

function formValidation() {
	let scelta = document.querySelector('select[name="scelta"]').value;
    let valore = document.getElementById("valore");

    if(scelta === "email"){
    	ValidateEmail(valore);
    } else if(scelta === "password") {
    	passid_validation(scelta, 7, 12);
    }
    
    return false;
}


function ValidateEmail(inputText)
{
	let mailformat = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;//sono dei campi consentiti
    if  (inputText.value.match(mailformat))				//preleva e confronta
       {
        return true;
       } else {
                alert("You have entered an invalid email address!");
                return false;
              }
}

function passid_validation(passid,mx,my) {
    let passid_len = passid.value.length;
    if (passid_len == 0 ||passid_len >= my || passid_len < mx) {
        alert("Password should not be empty / length be between "+mx+" to "+my);
        passid.focus();		//il cursore viene posizionato automaticamente sull'inputtext della pass
        return false;
    }
    return true;
}