
const url = 'http://localhost:8080/project1/';

window.onload = function() {
  document.getElementById("loginButton").addEventListener('click', login);
}

async function login() {
  let inputUsername = document.getElementById("username").value;
  let inputPassword = document.getElementById("password").value;

  sessionStorage.setItem('inputUsername', inputUsername);

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

    window.location.replace("employee/Employee.html");
  }
  else {
    // login failed message
  }

}
