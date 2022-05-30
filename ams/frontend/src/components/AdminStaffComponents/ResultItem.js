import React from "react";
import Divider from "@mui/material/Divider";

const ResultItem = ({ result }) => (
  <div className="result">
    <Divider orientation="horizontal" flexItem>
      <b> Group: {result.groupName} </b>
    </Divider>
    {result.studentsWithResults.map((rezultat) => (
      <div>
        <p>Student ID: {rezultat.studentName}</p>
        <p>Grade:{rezultat.result.toFixed(2)}</p>
        <Divider/>
      </div>
    ))}
  </div>
);

export default ResultItem;
