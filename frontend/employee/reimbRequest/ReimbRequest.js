
const url = 'http://localhost:8080/project1/';

window.addEventListener('load', function() {
  document.getElementById("submitButton").addEventListener('click', reimbRequest);
});

async function reimbRequest() {
  console.log("get into reimbRequest");
  let inputAmount = document.getElementById("amount").value;
  let inputType = document.getElementById("type").value;
  let inputDescription = document.getElementById("description").value;

  let reimbRequest = {
    amount: inputAmount,
    type: inputType,
    description: inputDescription
  };

  console.log(reimbRequest);

  let response = await fetch(url + "reimbrequest", {
    method: "POST",
    body: JSON.stringify(reimbRequest),
    credentials: "include"
  });

  if (response.status === 200) {
    window.location.replace("../Submitted.html");
  }
  else {
    // do stuff on fail
    console.log("failed somehow.");
  }

}
