import navbar from "../components/navbar.js";

document.querySelector("#navbar").innerHTML = navbar();

let users = "https://task-planner-backend-production.up.railway.app/users/all";
let tasks = "https://task-planner-backend-production.up.railway.app/tasks/all";
let sprints =
  "https://task-planner-backend-production.up.railway.app/sprints/all";

// fetch(sprints)
//   .then((response) => response.json())
//   .then((data) => {
//     console.log("Data : ", data);
//   })
//   .catch((error) => {
//     console.error(error);
//   });
