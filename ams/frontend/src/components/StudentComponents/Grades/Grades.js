import React from "react";
import "../../Components.css";
import "../Grades/Grades.css";
import Navbar from "../../Navbar";
import TeacherTableGrades from "../../TeacherComponents/TeacherGrades/TeacherTableGrades";
import { useState, useEffect } from "react";

export default function Grades(props) {
  const [values, setValues] = useState([]);
  const [e1, setE1] = useState([]);
  const [e2, setE2] = useState([]);
  const [grades, setGrades] = useState([]);

  console.log(12);
  const getStudentGrades = async () => {
    var requestOptions = {
      method: "GET",
      redirect: "follow",
    };

    await fetch("http://localhost:8080/students/grades/5", requestOptions)
      .then((response) => response.json())
      .then((result) => {
        console.log(result);
        setValues(result);
        const arr = new Array();
        if (result.length >= 1) {
          setE1(result[0]);
          arr.push(e1.grades);
          console.log("array",arr);
          setGrades(result[0].grades);
        }
        if (result.length == 2) {
          setE2(result[1]);
          arr.push(e1.grades);
          console.log("array",arr);
          console.log(e2);
        }
      })
      .catch((error) => console.log("error", error));
  };

  useEffect(() => {
    getStudentGrades();
  }, []);
  console.log("e1",e1);
  console.log("grades:", grades);
  console.log("e2",e2);
  console.log("values:", values);

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
              {grades.map((grade) => (
                <div>
                  <tr>
                    <td>{grade.courseName}</td>
                    <td>{grade.grade}</td>
                  </tr>
                </div>
              ))}
            </tbody>
          </table>
        </div>
      ))}
    </div>
  );
}
