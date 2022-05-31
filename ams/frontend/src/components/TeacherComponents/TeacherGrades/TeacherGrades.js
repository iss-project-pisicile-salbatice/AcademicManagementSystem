import React from "react";
import "../../Components.css";
import "../../StudentComponents/Grades/Grades.css";
import Navbar from "../../Navbar";
import TeacherTableGrades from "../../TeacherComponents/TeacherGrades/TeacherTableGrades";
import { useState, useEffect } from "react";

export default function TeacherGrades(props) {
  const [isOpen, setIsOpen] = useState(false);
  const [subjects, setSubjects] = useState([]);

  const getTeacherSubjects = async () => {
    var requestOptions = {
      method: "GET",
      redirect: "follow",
    };

    await fetch("http://localhost:8080/teachers/courses/1", requestOptions)
      .then((response) => response.json())
      .then((result) => {
        console.log(result);
        setSubjects(result);
      })
      .catch((error) => console.log("error", error));
  };

  useEffect(() => {
    getTeacherSubjects();
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
            <tbody className="teacherSubjects">
              {subjects.map((subject) => (
                <div>
                  <tr onClick={() => setIsOpen((isOpen) => !isOpen)}>
                    <td className="teacherTableSquare">
                      {subject.yearSpeciality}-{subject.courseName}
                    </td>
                  </tr>
                  <TeacherTableGrades
                    open={isOpen}
                    yearSpeciality={subject.yearSpeciality}
                  />
                </div>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}
