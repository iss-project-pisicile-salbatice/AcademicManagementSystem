import React, { useState } from "react";
import EnrItem from "./EnrItem";
import "./Optionals.css";

const Enrollments = ({ enrollments }) => {
  const arr1 = new Array();
  enrollments.map(()=>{
    arr1.push(false);
 })
  const [isOpen, setIsOpen] = useState(arr1);

  
  const handleClick = (index) => {
    let newArr = [...isOpen];
    newArr[index] = true;
    setIsOpen(newArr);
  };
  return (
    <div className="enrollmentContainer">
      {enrollments.map((enrollment, index) => (
        <button className="enrollmentItem" onClick={() => handleClick(index)}>
          <EnrItem enrollment={enrollment} open={isOpen[index]} />
        </button>
      ))}
    </div>
  );
};

export default Enrollments;
