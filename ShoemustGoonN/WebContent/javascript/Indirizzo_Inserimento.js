/**
 * 
 */
function formValidation() {
  let citta = document.getElementById("citta");
  let cap = document.getElementById("cap");
  let viaPiazza = document.getElementById("via");
  let nCivico = document.getElementById("civico");

  if (validateCitta(citta) && validateCAP(cap) && validateViaPiazza(viaPiazza) && validateNCivico(nCivico)) {
    return true; // Tutti i controlli di validazione sono superati
  } else {
    return false; // Almeno uno dei controlli di validazione ha fallito
  }
}

function validateCitta(inputtxt) {
  let regex = /^[a-zA-Z\s]+$/;
  if (inputtxt.value.match(regex)) {
    return true;
  } else {
    alert('Inserisci solo caratteri alfabetici per la città');
    inputtxt.focus();
    return false;
  }
}

function validateCAP(inputtxt) {
  let regex = /^\d{5}$/;
  if (inputtxt.value.match(regex)) {
    return true;
  } else {
    alert('Inserisci un CAP valido');
    inputtxt.focus();
    return false;
  }
}

function validateViaPiazza(inputtxt) {
  let regex = /^[a-zA-Z\s]+$/;
  if (inputtxt.value.match(regex)) {
    return true;
  } else {
    alert('Inserisci solo caratteri alfabetici per la via/piazza');
    inputtxt.focus();
    return false;
  }
}

function validateNCivico(inputtxt) {
  let regex = /^\d{1,3}$/;
  if (inputtxt.value === "" || inputtxt.value.match(regex)) {
    return true;
  } else {
    alert('Inserisci un numero civico valido');
    inputtxt.focus();
    return false;
  }
}
