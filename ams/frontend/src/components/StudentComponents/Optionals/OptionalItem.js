import React from "react";
import EnrItem from "./EnrItem";

const OptionalItem = ({ optional }) => {
  // console.log(optional);
  return (
    <div className="optionalData">
      {optional.courseName} - 
      {optional.teacherName}
    </div>
  );
};

export default OptionalItem;
