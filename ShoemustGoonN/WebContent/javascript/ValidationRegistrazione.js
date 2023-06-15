/**
 * 
 */

/**
 * 
 */

function formValidation() {
    var emailInput = document.getElementById("emailInput");
    var nomeInput = document.getElementById("nomeInput");
    var cognomeInput = document.getElementById("cognomeInput");
    var dateInput = document.getElementById("dateInput");
    var pswInput = document.getElementById("pswInput");
    var cvInput = document.getElementById("cvInput");

    if(allLetter(nomeInput)) {
        if(allLetter(cognomeInput)) {
            if(validateDateOfBirth(dateInput)){
            	if(validateCodiceFiscale(cvInput)) {
                if (ValidateEmail(emailInput)) {
                    if (passid_validation(pswInput, 7, 12)){
                       
                    }
                }
            }
          }
        }
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

function allLetter(inputtxt) { 
    var letters = /^[A-Za-z]+$/;
    if (inputtxt.value.match(letters))
      {
        return true;
      } else {
          alert('In questi campi non puoi inserire valori numeri!');
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

function validateDateOfBirth(dateOfBirth) {
    var currentDate = new Date();
    var inputDate = new Date(dateOfBirth.value);
    var minimumAge = 12;
  
    // Calcola la differenza di anni tra la data di nascita inserita e la data corrente
    var age = currentDate.getFullYear() - inputDate.getFullYear();
  
    // Verifica se il giorno di compleanno dell'utente è già passato quest'anno
    var isBirthdayPassed = (currentDate.getMonth() < inputDate.getMonth()) ||
      (currentDate.getMonth() === inputDate.getMonth() && currentDate.getDate() < inputDate.getDate());
  
    // Sottrae 1 all'età se il giorno di compleanno non è ancora passato
    if (!isBirthdayPassed) {
      age--;
    }
  
    // Verifica se l'utente ha almeno l'età minima richiesta
    if (age < minimumAge) {
      alert("Devi avere almeno 18 anni per procedere.");
      dateOfBirth.focus();
      return false;
    }
  
    return true;
  }

function validateCodiceFiscale(cfInput) {
	  var codiceFiscale = cfInput.value.trim();
	  
	  // Controllo della lunghezza
	  if (codiceFiscale.length !== 16) {
	    alert("Il codice fiscale deve essere composto da 16 caratteri.");
	    cfInput.focus();
	    return false;
	  }
	  
	  // Controllo del formato
	  var cfFormat = /^[A-Z0-9]+$/;
	  if (!codiceFiscale.match(cfFormat)) {
	    alert("Il codice fiscale deve essere composto solo da lettere maiuscole e numeri.");
	    cfInput.focus();
	    return false;
	  }
	  
	  return true;
}

  