import React, { useState } from "react";
import { useEffect } from "react";
import "../Components.css";
import "../Grades/Grades.css";

export default function TeacherTableGrades({ open, yearSpeciality }) {
  const [grades, setGrades] = useState([]);

    var formdata = new FormData();
    formdata.append("teacherID", "1");
    formdata.append("studentID", "1");
    formdata.append("courseID", "2");
    formdata.append("gradeValue", "4");


  //   const assignPostFetch = async (i) => {
  //     formdata.set("teacherID", "1");
  //     formdata.set("studentID", "3");
  //     formdata.set("courseID", "2");

  //   };


  const getTeacherGrades = async () => {
    var requestOptions = {
      method: "GET",
      redirect: "follow",
    };

    await fetch("http://localhost:8080/teachers/grades/1", requestOptions)
      .then((response) => response.json())
      .then((result) => {
        console.log(result);
        setGrades(result[0].grades);
      })
      .catch((error) => console.log("error", error));
  };

  const postStudentGrades = async (event) => {
    event.preventDefault();
    var requestOptions = {
      method: "POST",
      body: formdata,
      redirect: "follow",
    };

    fetch("http://localhost:8080/teachers/grade_student", requestOptions)
      .then((response) => response.text())
      .then((result) => {
        console.log("in post:");
        console.log(result);
      })
      .catch((error) => console.log("error", error));
  };

  useEffect(() => {
    getTeacherGrades();
  }, []);

  //   console.log("Grade table");
  const filteredGrades = grades.filter(
    (grade) => grade.yearSpeciality == yearSpeciality
  );

  //   console.log(filteredGrades);

  if (open == false) {
    return null;
  }
  if (filteredGrades.length == 0) {
    return (
      <div className="emptyList">
        There are no students enrolled in this speciality
      </div>
    );
  }
  return (
    <div className="studentsList">
      <table>
        <tbody>
          <tr>
            <th>Year</th>
            <th>Name</th>
            <th>Course</th>
            <th>Grade</th>
          </tr>
          {filteredGrades.map((grade, index) => (
            <tr className="teacherTableRow">
              <td className="teacherTableSquare">{grade.yearSpeciality[0]}</td>
              <td className="teacherTableSquare">{grade.studentName}</td>
              <td className="teacherTableSquare">{grade.courseName}</td>
              <td className="teacherTableSquare">
                <input
                  type="int"
                  value={grade.grade}
                  onChange={(event) => {
                    formdata.set("gradeValue", event.target.value);
                    formdata.set("studentID", "3");
                    formdata.set("courseID", "2");
                  }}
                ></input>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <button onClick={postStudentGrades}>Save</button>
    </div>
  );
}
