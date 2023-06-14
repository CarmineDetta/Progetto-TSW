/**
 * 
 */

var emailInput = document.getElementById("emailInput");
var regButton = document.getElementById("regButton");

regButton.addEventListener("click", function() {
	  ValidateEmail(emailInput);
	});


function ValidateEmail(inputText)
{
    var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if  (inputText.value.match(mailformat))
       {
        document.form1.text1.focus();
        return true;
       } else {
                alert("You have entered an invalid email address!");
                document.form1.text1.focus();
                return false;
              }
}
