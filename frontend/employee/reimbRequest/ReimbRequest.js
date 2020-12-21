
window.addEventListener('load', function() {
  document.getElementById("submitButton").addEventListener('click', reimbRequest);
});

async function reimbRequest() {
  let inputAmount = document.getElementById("amount").value;
  let inputType = document.getElementById("type").value;
  let inputDescription = document.getElementById("description").value;

  let reimbRequest = {
    amount: inputAmount,
    type: inputType,
    description: inputDescription
  };

  let response = await fetch(url + "reimbrequest", {
    method: "POST",
    body: JSON.stringify(reimbRequest),
    credentials: "include"
  });

  if (response.status === 200) {
    window.location.replace("../Submitted.html");
  }
  else {
    let failedMessage = document.getElementById("submitFailed");
    failedMessage.innerHTML = "Reimbursement Request Rejected.";
    failedMessage.style.display = "block";
    setTimeout(function() {
      document.getElementById("submitFailed").style.display = "none";
    }, 2500);
  }

}
