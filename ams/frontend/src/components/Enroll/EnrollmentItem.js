import React from "react";
import "./Enrollment.css";

const EnrollmentItem = ({ enrollment, onToggle }) => {
  return (
    <div className="enrollmentItem">
      <div className="enrollmentData">
        {" "}
        <h4>{enrollment.speciality} </h4>
        <p>Year: {enrollment.year}</p>
      </div>

      <div class="enrollmentCheckbox">
        <label>Enroll</label>
        <input type="checkbox" checked="true" />
      </div>
    </div>
  );
};

export default EnrollmentItem;
