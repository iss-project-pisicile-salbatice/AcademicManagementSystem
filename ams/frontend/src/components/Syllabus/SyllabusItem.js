import React from "react";
import { useState, useEffect } from "react";
import "./Syllabus.css";

const SyllabusItem = ({ syllabusItem }) => {
  const [onToggle, setOnToggle] = useState("false");

  return (
    <div className="syllabusItem">
      <div className="syllabusData">
        <h4>{syllabusItem.speciality}</h4>
        <p>Year: {syllabusItem.year}</p>
      </div>

      <div class="syllabusCheckbox">
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

export default SyllabusItem;
