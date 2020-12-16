
window.addEventListener('load', function() {
  document.getElementById("Approve").addEventListener('click', approve);
  document.getElementById("Deny").addEventListener('click', deny);
});

async function approve() {
  let id = {
    id: sessionStorage.getItem("id")
  }

  let response = await fetch(url+'approveticket', {
    method: "POST",
    body: JSON.stringify(id),
    credentials: "include"
  });

  if (response.status === 200) {
    window.location.replace("../../TicketResolved.html");
  }
  else {
    // on fail?
    console.log("failed somehow.");
  }
}

async function deny() {
  let id = {
    id: sessionStorage.getItem("id")
  }

  let response = await fetch(url+'denyticket', {
    method: "POST",
    body: JSON.stringify(id),
    credentials: "include"
  });

  if (response.status === 200) {
    window.location.replace("../../TicketResolved.html");
  }
  else {
    // on fail?
    console.log("failed somehow.");
  }
}