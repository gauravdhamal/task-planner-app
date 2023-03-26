import navbar from "../components/navbar.js";

document.querySelector("#navbar").innerHTML = navbar();

// https://task-planner-backend-production.up.railway.app
const commonUrl = "http://localhost:8080";

// To get task data after creating task.
const taskForm = document.querySelector("#task-form");

const submitTask = async (event) => {
  event.preventDefault();

  let formData = new FormData(event.target);

  let taskType = formData.get("task-type");
  let taskDescription = formData.get("task-description");
  let taskStatus = formData.get("task-status");
  let taskPriority = formData.get("task-priority");
  let taskComment = formData.get("task-comment");

  let taskObject = {
    type: "someType",
    description: "someDescription",
    status: "enumValue1",
    priority: "enumValue2",
    comment: "someComment",
  };

  taskObject.type = taskType;
  taskObject.description = taskDescription;
  taskObject.status = taskStatus;
  taskObject.priority = taskPriority;
  taskObject.comment = taskComment;

  const options = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(taskObject),
  };

  fetch(commonUrl + "/tasks/create", options)
    .then((response) => response.json())
    .then((taskObject) => console.log("TaskObject : ", taskObject))
    .catch((error) => console.error(error));
};

taskForm.addEventListener("submit", submitTask);
