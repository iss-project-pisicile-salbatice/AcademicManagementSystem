import React, { Component, useState, useEffect } from "react";
import Navbar from "../../Navbar";
import Enrollments from "./Enrollments";
import "./Optionals.css";
export default function Optionals() {
  const [enrollments, setEnrollments] = useState([]);
  const getStudentDetails = async () => {
    var requestOptions = {
      method: "GET",
      redirect: "follow",
    };

    fetch("http://localhost:8080/students/enroll/2", requestOptions)
      .then((response) => response.json())
      .then((result) => {
        setEnrollments(result);
      })
      .catch((error) => console.log("error", error));
  };

  useEffect(() => {
    getStudentDetails();
  }, []);

  return (
    <div>
      <Navbar
        userName={"Ianis Teja"}
        role={"Student"}
        imgUser={"userMockUp.png"}
      />
      <h1>Optionals</h1>
      {enrollments.length > 0 ? (
        <Enrollments enrollments={enrollments} />
      ) : (
        "No enrollments to show"
      )}
    </div>
  );
}
