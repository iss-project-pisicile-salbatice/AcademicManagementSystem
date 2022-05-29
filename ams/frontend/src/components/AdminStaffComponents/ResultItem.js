import React from "react";

const ResultItem = ({ result }) => (
  <div className="result">
    <h5>{result.groupName}</h5>
    {result.studentsWithResults.map((rezultat) => (
      <div>
        <p>{rezultat.result}</p>
        <p>{rezultat.studentName}</p>
      </div>
    ))}
  </div>
);

export default ResultItem;
