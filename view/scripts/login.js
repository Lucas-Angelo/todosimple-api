async function login() {
  let username = document.getElementById("username").value;
  let password = document.getElementById("password").value;

  console.log(username, password);

  const response = await fetch("http://localhost:8080/login", {
    method: "POST",
    headers: new Headers({
      "Content-Type": "application/json; charset=utf8",
      Accept: "application/json",
    }),
    body: JSON.stringify({
      username: username,
      password: password,
    }),
  });

  let key = "Authorization";
  let token = response.headers.get(key);
  window.localStorage.setItem(key, token);

  if (response.ok) {
    showToast("#okToast");
  } else {
    showToast("#errorToast");
  }

  window.setTimeout(function () {
    window.location = "/view/index.html";
  }, 2000);
}

function showToast(id) {
  var toastElList = [].slice.call(document.querySelectorAll(id));
  var toastList = toastElList.map(function (toastEl) {
    return new bootstrap.Toast(toastEl);
  });
  toastList.forEach((toast) => toast.show());
}
