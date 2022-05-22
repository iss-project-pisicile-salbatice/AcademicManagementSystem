import React from "react";
import "./Enrollment.css";
import { useState, useEffect } from "react";

const EnrollmentItem = ({ enrollment }) => {
  const [onToggle, setOnToggle] = useState("false");
  console.log(onToggle);
  return (
    <div className="enrollmentItem">
      <div className="enrollmentData">
        <h4>{enrollment.speciality}</h4>
        <p>Year: {enrollment.year}</p>
      </div>

      <div class="enrollmentCheckbox">
        <label>Enroll</label>
        <input
          type="checkbox"
          checked={onToggle}
          value={onToggle}
          onChange={() => setOnToggle(!onToggle)}

          // onClick={setOnToggle(!onToggle)}
        />
      </div>
    </div>
  );
};

export default EnrollmentItem;
