/**
 * 
 */
function validateCard() {
  let cardNumber = document.getElementById("n_carta").value;
  let cardType = getCardType(cardNumber);

  if (cardType === "Visa") {
    if (!validateVisa(cardNumber)) {
      alert("Not a valid Visa credit card number!");
      return false;
    }
  } else if (cardType === "Mastercard") {
    if (!validateMastercard(cardNumber)) {
      alert("Not a valid Mastercard number!");
      return false;
    }
  } else if (cardType === "JCB") {
    if (!validateJCB(cardNumber)) {
      alert("Not a valid JCB card number!");
      return false;
    }
  } else if (cardType === "Discover") {
    if (!validateDiscover(cardNumber)) {
      alert("Not a valid Discover card number!");
      return false;
    }
  } else if (cardType === "DinersClub") {
    if (!validateDinersClub(cardNumber)) {
      alert("Not a valid Diners Club card number!");
      return false;
    }
  } else if (cardType === "AmericanExpress") {
    if (!validateAmericanExpress(cardNumber)) {
      alert("Not a valid American Express credit card number!");
      return false;
    }
  } else {
    alert("Invalid card type!");
    return false;
  }

  return true; // La carta è valida
}

function getCardType(cardNumber) {
  // Esegui il controllo per determinare il tipo di carta
  // Restituisci "Visa", "Mastercard", "JCB", "Discover", "DinersClub", "AmericanExpress" o "Unknown"

	if (/^4\d{12}(?:\d{3})?$/.test(cardNumber)) {
    return "Visa";
  }

	if (/^5[1-5]\d{14}$/.test(cardNumber)) {
    return "Mastercard";
  }

  if (/^(?:(?:2131|1800|35\d{3})\d{11})$/.test(cardNumber)) {
    return "JCB";
  }

  if (/^(?:6(?:011|5\d{2})\d{12})$/.test(numeroDiCarta)){
    return "Discover";
  }

  if (/^(?:3(?:0[0-5]|[68]\d{2})\d{11})$/.test(numeroDiCarta)) {
    return "DinersClub";
  }

  if (/^(?:3[47]\d{13})$/.test(numeroDiCarta)) {
    return "AmericanExpress";
  }

   return "Unknown";
}

function validateVisa(cardNumber) {
  let visaPattern = /^(?:4\d{12}(?:\d{3})?)$/;
  return visaPattern.test(cardNumber);
}

function validateMastercard(cardNumber) {
  let mastercardPattern = /^(?:5[1-5]\d{14})$/;
  return mastercardPattern.test(cardNumber);
}

function validateJCB(cardNumber) {
  let jcbPattern = /^(?:(?:2131|1800|35\d{3})\d{11})$/;
  return jcbPattern.test(cardNumber);
}

function validateDiscover(cardNumber) {
  let discoverPattern = /^(?:6(?:011|5\d{2})\d{12})$/;
  return discoverPattern.test(cardNumber);
}

function validateDinersClub(cardNumber) {
  let dinersClubPattern = /^(?:3(?:0[0-5]|[68]\d{2})\d{11})$/;
  return dinersClubPattern.test(cardNumber);
}

function validateAmericanExpress(cardNumber) {
  let americanExpressPattern = /^(?:3[47]\d{13})$/;
  return americanExpressPattern.test(cardNumber);
}

