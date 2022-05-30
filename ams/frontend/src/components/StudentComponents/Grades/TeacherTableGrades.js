import React, { useState } from "react";
import { useEffect } from "react";
import "../../Components.css";
import "../Grades/Grades.css";

export default function TeacherTableGrades({ open, yearSpeciality }) {
  const [grades, setGrades] = useState([]);
  const [gradeValue, setGradeValue] = useState({
    teacherID: "1",
    studentID: "1",
    courseID: "2",
    gradeValue: "",
  });

  const set = (name) => {
    return ({ target: { value } }) => {
      setGradeValue((oldValues) => ({ ...oldValues, [name]: value }));
    };
  };

  const setresponse = (name,value) =>{
    setGradeValue(oldValues => ({ ...oldValues, [name]: value}))
  };

  console.log(gradeValue);
  var formdata = new FormData();
  formdata.append("teacherID", "1");
  formdata.append("studentID", "");
  formdata.append("courseID", "");
  formdata.append("gradeValue", "");

  const onSubmit = (event) => {
    formdata.set("studentID", gradeValue.studentID);
    formdata.set("courseID", gradeValue.courseID);
    formdata.set("gradeValue", gradeValue.gradeValue);
    console.log(formdata.get(gradeValue));
    postStudentGrades(event);
  };

  const getTeacherGrades = async () => {
    var requestOptions = {
      method: "GET",
      redirect: "follow",
    };

    await fetch("http://localhost:8080/teachers/grades/1", requestOptions)
      .then((response) => response.json())
      .then((result) => {
        console.log(result[0].grades);
        setresponse('gradeValue',result[0].grades[1].grade);
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
          <form onSubmit={onSubmit}>
            {filteredGrades.map((grade, index) => (
              <div className="studentToGrade">
               {/* <input type="text" value = {grade.studentName} readOnly/> */}
                <p className="gradeElem">{grade.studentName}</p>
                <input className = "gradeElem" id = "giveGrade"
                  type="number"
                  value={gradeValue.gradeValue}
                  onChange={
                    set('gradeValue')
                    // setresponse('studentID',grade.studentID);
                  }
                ></input>
              </div>
            ))}
            <input type="submit"></input>
          </form>
    </div>
  );
}
