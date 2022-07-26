const express = require("express");
const server = express();

server.use(express.static(__dirname + "/public"));

server.get("/", (req, res) => {
    res.sendFile(__dirname + "/html/LoginPage.html");
});

server.get("/lecturePage.html", (req, res) => {
    res.sendFile(__dirname + "/html/lecturePage.html");
});

server.get("/calculatePage.html", (req, res) => {
  res.sendFile(__dirname + "/html/calculatePage.html");
});

server.get("/assignmentPage.html", (req, res) => {
  res.sendFile(__dirname + "/html/assignmentPage.html");
});

server.get("/calculatorTest.html", (req, res) => {
    res.sendFile(__dirname + "/html/calculator.html");
});

server.use((req, res) => {
  res.sendFile(__dirname + "/html/404.html");
});

server.listen(3000, (err) => {
  if (err) return console.log(err);
  console.log("The server is listening on port 3000");
});