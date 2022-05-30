import React from "react";
import "../../Components.css";
import "../Grades/Grades.css";
import Navbar from "../../Navbar";
import TeacherTableGrades from "./TeacherTableGrades";
import { useState, useEffect } from "react";

export default function Grades(props) {
  const [isOpen, setIsOpen] = useState(false);
  const [subjects, setSubjects] = useState([]);
  const [values, setValues] = useState([]);

  const getTeacherSubjects = async () => {
    var requestOptions = {
      method: "GET",
      redirect: "follow",
    };

    await fetch("http://localhost:8080/teachers/courses/1", requestOptions)
      .then((response) => response.json())
      .then((result) => {
        setSubjects(result);
      })
      .catch((error) => console.log("error", error));
  };

  const getStudentGrades = async () => {
    var requestOptions = {
      method: "GET",
      redirect: "follow",
    };

    await fetch(
      "http://localhost:8080/students/courses_year/1",
      requestOptions
    );
    await fetch("http://localhost:8080/students/grades/61", requestOptions)
      .then((response) => response.json())
      .then((result) => {
        setValues(result);
      })
      .catch((error) => console.log("error", error));
  };

  useEffect(() => {
    getTeacherSubjects();
    getStudentGrades();
  }, []);

  return (
    <div>
      <Navbar
        userName={"Ianis Teja"}
        role={"Student"}
        imgUser={"userMockUp.png"}
      />{" "}
      <h2 className="pageTitle">Grades</h2>
      <div>
        <div>
          <table className="teacherTable">
            <tbody>
              {subjects.map((subject) => (
                <div>
                  <div className="teacherHeader">{subject.yearSpeciality}</div>

                  <TeacherTableGrades yearSpeciality={subject.yearSpeciality} />
                </div>
              ))}
            </tbody>

            <tbody className="teacherSubjects">
              {subjects.map((subject) => (
                <div>
                  <tr onClick={() => setIsOpen((isOpen) => !isOpen)}>
                    <td className="teacherTableSquare">
                      {subject.yearSpeciality}
                    </td>
                    <td className="teacherTableSquare">{subject.courseName}</td>
                  </tr>
                  <TeacherTableGrades
                    open={isOpen}
                    yearSpeciality={subject.yearSpeciality}
                  />
                </div>
              ))}
            </tbody>
          </table>

          {/* <table className="teacherTable">
            <tbody>
              <tr>
                <th>Year</th>
                <th>Name</th>
                <th>Course</th>
                <th>Grade</th>
              </tr>
              {grades.map((grade) => (
                <tr className="teacherTableRow">
                  <td className="teacherTableSquare">
                    {grade.yearSpeciality[0]}
                  </td>
                  <td className="teacherTableSquare">{grade.studentName}</td>
                  <td className="teacherTableSquare">{grade.courseName}</td>
                  <td className="teacherTableSquare">
                    <input type="text" value={grade.grade}></input>
                  </td>
                </tr>
              ))}
            </tbody>
          </table> */}
        </div>
        {/* <table>
          <tbody>
            {values.map((value) => (
              <tr>
                <td>{value.courseName}</td>
                <td>{value.grade}</td>
              </tr>
            ))}
          </tbody>
        </table> */}
      </div>
    </div>
  );
}
