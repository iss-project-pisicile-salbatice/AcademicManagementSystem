import React from "react";
import "../../Components.css";
import "../Grades/Grades.css";
import Navbar from "../../Navbar";
import TeacherTableGrades from "../../TeacherComponents/TeacherGrades/TeacherTableGrades";
import { useState, useEffect } from "react";

export default function Grades() {
  const [values, setValues] = useState([]);
  const [e1, setE1] = useState([]);
  const [e2, setE2] = useState([]);
  const [grades, setGrades] = useState([]);

  console.log(12);
  const getStudentGrades = async () => {
    const studentToken = JSON.parse(localStorage.getItem('userToken'));

    console.log(studentToken.accessToken);

    var requestOptions = {
      method: "GET",
      redirect: "follow",
      headers: {Authorization: `Bearer ${studentToken.accessToken}`}
    };

    await fetch(`http://localhost:8080/students/grades/${studentToken.entityId}`, requestOptions)
      .then((response) => response.json())
      .then((result) => {
        setValues(result);
        if(result.length >= 1){
          setE1(result[0]);
          setGrades(result[0].grades);

        }
        if(result.length==2)
        {
          setE2(result[1]);
          console.log(e2);
        }
      })
      .catch((error) => console.log("error", error));
  };

  useEffect(() => {
    getStudentGrades();
  }, []);
  console.log(e1);
  console.log(grades);
  console.log(e2);
  console.log(values.length);

  return (
    <div>
      <Navbar
        userName={"Ianis Teja"}
        role={"Student"}
        imgUser={"userMockUp.png"}
      />{" "}
      <h2 className="pageTitle">Grades</h2>
      {values.map((value) => (
        <div>
          <h3>{value.speciality}</h3>
          <table className="gradesTable">
            <tbody>
              {grades.map((grade) => {
                <tr>
                  <td>{grade.courseName}</td>
                  <td>{grade.grade}</td>
                </tr>;
              })}
            </tbody>
          </table>
        </div>
      ))}
    </div>
  );
}
