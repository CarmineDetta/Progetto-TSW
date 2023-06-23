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
		  let validateDB = document.getElementById("emailInput").value;
		
		  // Effettua la chiamata AJAX per la validazione dell'email
		  validateDatabase(validateDB, function(result) {
		    if (result === "false") {
		      alert("Email esistente, la preghiamo di cambiare email!");
		    } else {
		      // Esegue tutte le altre validazioni
		      if (
		        allLetter(nomeInput) &&
		        allLetter(cognomeInput) &&
		        validateDateOfBirth(dateInput) &&
		        validateCodiceFiscale(cvInput) &&
		        ValidateEmail(emailInput) &&
		        passid_validation(pswInput, 7, 12)
		      ) {
		        // Tutte le validazioni sono passate, invia il form
		        document.getElementById("registerForm").submit();
		      }
		    }
		  });
		
		  // Impedisce l'invio del form tramite il submit predefinito
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


function validateDatabase(stringaParziale, callback) {
	  $.ajax({
	    async: true,
	    url: "AjaxEmailControl",
	    method: "POST",
	    data: "stringaRicerca=" + stringaParziale,
	    dataType: "json",
	    success: function(data, textStatus, jqXHR) {
	      console.log("Sono nella funzione");
	      if (typeof callback === "function") {
	        callback(data);
	      }
	    },
	    error: function(jqXHR, textStatus, errorThrown) {
	      console.error("Errore nella chiamata AJAX: " + textStatus, errorThrown);
	      if (typeof callback === "function") {
	        callback("false");
	      }
	    }
	  });
}
