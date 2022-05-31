import React from "react";
import "./Enrollment.css";
import { useState, useEffect } from "react";
import SyllabusList from "./SyllabusList";

const EnrollmentItem = ({ enrollment, toggleEnroll }) => {
  const [onToggle, setOnToggle] = useState(false);
  const [isActive, setIsActive] = useState(false);
  const [syllabusList, setSyllabusList] = useState([]);

  //Fetch data
  const fetchTasks = async () => {
    const res = await fetch(
      `http://localhost:8080/students/courses_year/${enrollment.yId}`
    );
    const data = await res.json();

    return data;
  };

  useEffect(() => {
    const getSyllabus = async () => {
      const syllabusFromAPI = await fetchTasks();
      setSyllabusList(syllabusFromAPI);
    };

    getSyllabus();
  }, []);

  console.log(onToggle);
  console.log(isActive);
  return (
    <div className="enrollmentItem">
      <div className="enrollmentData" onClick={() => setIsActive(!isActive)}>
        <h4>{enrollment.speciality}</h4>
        <p>Year: {enrollment.year}</p>
      </div>

      <div class="enrollmentCheckbox">
        <label>Enroll</label>
        <input
          type="checkbox"
          checked={onToggle}
          value={onToggle}
          onChange={() => {
            setOnToggle(!onToggle);
            toggleEnroll(enrollment.yId);
          }}

        // onClick={setOnToggle(!onToggle)}
        />
      </div>
      {isActive && syllabusList.length > 0 ? (
        <SyllabusList list={syllabusList} />
      ) : (
        " "
      )}
    </div>
  );
};

export default EnrollmentItem;
