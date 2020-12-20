
window.addEventListener('load', function() {
  document.getElementById("submitButton").addEventListener('click', reimbRequest);
});

async function reimbRequest() {
  let inputAmount = document.getElementById("amount").value;
  let inputType = document.getElementById("type").value;
  let inputDescription = document.getElementById("description").value;
  let file = document.getElementById("image-file").files[0];
  let imageBlob;
  
  //or let file = document.querySelector('input[type=file]').files[0];
  let reader = new FileReader();
  reader.onload = await function(e) {
    imageBlob = new Blob([new Uint8Array(e.target.result)], {type: file.type });
    console.log(imageBlob);
  };
  reader.readAsArrayBuffer(file);
  console.log(reader);
  console.log(imageBlob);

  let reimbRequest = {
    amount: inputAmount,
    type: inputType,
    description: inputDescription,
    receipt: reader
  };

  // console.log(reimbRequest.receipt);

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
