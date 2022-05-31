import React, { Component, useState, useEffect } from "react";
import Navbar from "../../Navbar";
import Enrollments from "./Enrollments";
export default function Optionals() {
  const [enrollments, setEnrollments] = useState([]);
  var myHeaders = new Headers();
  myHeaders.append(
    "Authorization",
    "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpYW5pc3RlamEiLCJpYXQiOjE2NTM5MzIxMTAsImV4cCI6MTY1NDAxODUxMH0.t-qZlsHQAUhixn2kVXyB6zXhY3nmOqqp-Ka5mxTSKQDHXwNEpaWqmfRsTAAMxJeaG_isxLXuWiuS-61G4lq9Cw"
  );

  const getEnrollments = async () => {
    var requestOptions = {
      method: "GET",
      headers: myHeaders,
      redirect: "follow",
    };

    fetch("http://localhost:8080/students/enroll/2", requestOptions)
      .then((response) => response.json())
      .then((result) => {
        setEnrollments(result);
        console.log(result);
      })
      .catch((error) => console.log("error", error));
  };
  useEffect(() => {
    getEnrollments();
  }, []);
  return (
    <div>
      <Navbar
        userName={"Ianis Teja"}
        role={"Student"}
        imgUser={"userMockUp.png"}
      />
      <h1>Optionals</h1>
      <div>
        {enrollments.length > 0 ? (
          <Enrollments enrollments={enrollments} />
        ) : (
          "No enrollments to show"
        )}
      </div>
    </div>
  );
}
