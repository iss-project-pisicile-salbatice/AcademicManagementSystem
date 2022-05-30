import React from "react";
import Navbar from "../../Navbar";
import { useState, useEffect } from "react";
import EnrollmentList from "./EnrollmentList";
import Button from "@mui/material/Button";

export default function Enroll() {
  const [enrollments, setEnrollments] = useState([]);
  const [chosenEnrollments, setChosenEnrollments] = useState([]);

  useEffect(() => {
    const getEnrollments = async () => {
      const enrollmentsFromAPI = await fetchTasks();
      setEnrollments(enrollmentsFromAPI);
    };

    getEnrollments();
  }, []);

  //Fetch data
  const fetchTasks = async () => {
    const res = await fetch("http://localhost:8080/students/enroll");
    const data = await res.json();

    return data;
  };

  //Toggle Enroll
  const toggleEnroll = (yId) => {
    if (chosenEnrollments.includes(yId))
      setChosenEnrollments(
        chosenEnrollments.filter((enrollment) => enrollment != yId)
      );
    else {
      if (chosenEnrollments.length < 2)
        setChosenEnrollments([...chosenEnrollments, yId]);
    }

    console.log("enrollment List: " + chosenEnrollments);
  };

  const saveSelection = () => {
    console.log("Save enrollment List: " + chosenEnrollments);
    var today = new Date();

    var date =
      today.getFullYear() +
      "-" +
      (today.getMonth() + 1) +
      "-" +
      today.getDate();

    chosenEnrollments.forEach((enrollment) => {
      var myHeaders = new Headers();
      myHeaders.append("Content-Type", "text/plain");

      var raw = `{"yearSpecialityID":${enrollment},\r\n"studentID":1,\r\n"enrollmentDate":"${date}"\r\n}`;

      var requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw,
        redirect: "follow",
      };

      fetch(
        `http://localhost:8080/students/enroll?yearSpecialityID=${enrollment}&studentID=61&enrollmentDate=${date}`,
        requestOptions
      )
        .then((response) => response.text())
        .then((result) => console.log(result))
        .catch((error) => console.log("error", error));
    });
  };

  return (
    <div>
      <Navbar
        userName={"Ianis Teja"}
        role={"Student"}
        imgUser={"userMockUp.png"}
      />
      <div>
        {enrollments.length > 0 ? (
          <EnrollmentList
            enrollments={enrollments}
            toggleEnroll={toggleEnroll}
          />
        ) : (
          "No enrollments to show"
        )}
        <div className="buttonContainer">
          <Button
            size="large"
            variant="contained"
            onClick={() => {
              saveSelection();
              alert("Items:" + chosenEnrollments);
            }}
            style={{ transform: "scale(1.5)" }}
          >
            Save your list
          </Button>
        </div>
      </div>
    </div>
  );
}
