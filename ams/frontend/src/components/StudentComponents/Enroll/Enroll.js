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


  const saveSelection = () =>{
    console.log("Save enrollment List: " + chosenEnrollments);

  }

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
              alert("Items saved!");
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
