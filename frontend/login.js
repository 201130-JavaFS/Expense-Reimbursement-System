
const url = 'http://localhost:8080/project1/';

window.addEventListener('load', function() {
  document.getElementById("loginButton").addEventListener('click', login);
});

async function login() {
  let failedMessage = document.getElementById("loginFailed");
  failedMessage.innerHTML = "";
  failedMessage.style.display = "block";
  let inputUsername = document.getElementById("username").value;
  let inputPassword = document.getElementById("password").value;

  let user = {
    username: inputUsername,
    password: inputPassword
  };

  let response = await fetch(url + "login", {
    method: "POST",
    body: JSON.stringify(user),
    credentials: "include"
  });

  if (response.status === 200) {
    let data = await response.json();

    if (data == 1) window.location.replace("employee/Employee.html");
    else window.location.replace("financeManager/FinanceManager.html");
    
  }
  else {
    let failedMessage = document.getElementById("loginFailed");
    failedMessage.innerHTML = "Username and/or Password Incorrect.";
    setTimeout(function() {
      document.getElementById("loginFailed").style.display = "none";
    }, 2500);
  }

}
