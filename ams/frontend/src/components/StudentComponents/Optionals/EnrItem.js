import React from "react";
import { useState, useEffect } from "react";
import OptionalsList from "./OptionalsList.js";

const EnrItem = ({ enrollment, open }) => {
  const [optionals, setOptionals] = useState([]);
  const yId = enrollment.yId;

  const getOptionals = async () => {
    var requestOptions = {
      method: "GET",
      redirect: "follow",
    };

    fetch(
      `http://localhost:8080/students/proposed_optionals/${yId}`,
      requestOptions
    )
      .then((response) => response.json())
      .then((result) => {
        setOptionals(result);
      })
      .catch((error) => console.log("error", error));
  };

  useEffect(() => {
    getOptionals();
  }, []);

  return (
    <div className="enrollmentData">
      <h4>{enrollment.speciality}</h4>
      <p>Year: {enrollment.year}</p>
      <OptionalsList optionals={optionals} open={open} />
    </div>
  );
};

export default EnrItem;
