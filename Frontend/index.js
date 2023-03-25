fetch("http://localhost:8080/tasks/all")
  .then((response) => response.json())
  .then((data) => {
    console.log(data);
  })
  .catch((error) => {
    console.error(error);
  });

// http://localhost:8080/users/all
// http://localhost:8080/tasks/all
