import navbar from "../components/navbar.js";

document.querySelector("#navbar").innerHTML = navbar();

// https://task-planner-backend-production.up.railway.app
const commonUrl = "https://task-planner-backend-production.up.railway.app";

// To get user data after creating user.
const userForm = document.querySelector("#user-form");

const submitUser = async (event) => {
  event.preventDefault();

  let formData = new FormData(event.target);

  let name = formData.get("user-name");
  let username = formData.get("user-username");
  let password = formData.get("user-password");
  let role = formData.get("user-role");
  let gender = formData.get("user-gender");

  let userObject = {
    name: "someName",
    username: "someUsername",
    password: "somePassword",
    role: "enumValue1",
    gender: "enumValue2",
  };

  userObject.name = name;
  userObject.username = username;
  userObject.password = password;
  userObject.role = role;
  userObject.gender = gender;

  const options = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(userObject),
  };

  fetch(commonUrl + "/users/create", options)
    .then((response) => response.json())
    .then((userObject) => {
      console.log("userObject:", userObject);
      if (userObject.description == "Validation error") {
        window.alert(userObject.details);
      } else if (userObject.description == "uri=/users/create") {
        window.alert("Username already exists try with another one.");
      }
      console.log("response:", response.ok);
    })
    .catch((error) => console.error("error : ", error));
};

userForm.addEventListener("submit", submitUser);
