import React, { useState, useEffect } from "react";
import Navbar from "../../Navbar";
import { Button } from "@mui/material";
import ChiefProposedOptionalsAcceptList from "./ChiefProposedOptionalsAcceptList";

const ChiefYearAccept = ({ yearId }) => {
  const [optionalDisciplines, setOtionalDisciplines] = useState([]);
  const [chosenOptionals, setChosenOptionals] = useState([]);

  useEffect(() => {
    const getDisciplines = async () => {
      const disciplinesFromApi = await fetchTasks();
      setOtionalDisciplines(disciplinesFromApi);
    };

    getDisciplines();
  }, []);

  // Fetch data from API
  const fetchTasks = async () => {
    const res = await fetch(`http://localhost:8080/chief/optionals/${yearId}`);
    const data = await res.json();

    return data;
  };

  // Toggle Accept
  const toggleAccept = (optionalId) => {
    if (chosenOptionals.includes(optionalId))
      setChosenOptionals(
        chosenOptionals.filter((optionalCourse) => optionalCourse != optionalId)
      );
    else {
      if (chosenOptionals.length < 2)
        setChosenOptionals([...chosenOptionals, optionalId]);
    }
  };

  const saveSelection = () => {
    console.log("Save optionals List: " + chosenOptionals);

    chosenOptionals.forEach((proposedOptional) => {
      var myHeaders = new Headers();
      myHeaders.append("Content-Type", "text/plain");

      var raw = `{"optionalId":${proposedOptional}}`;

      const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw,
        redirect: "follow",
      };

      fetch(
        `http://localhost:8080/chief/optionals/?optionalId=${proposedOptional}`,
        requestOptions
      )
        .then((response) => response.text())
        .then((result) => console.log(result))
        .catch((error) => console.log("error", error));
    });
  };

  return (
    <div>
      {optionalDisciplines.length > 0 ? (
        <ChiefProposedOptionalsAcceptList
          optionalsDisciplines={optionalDisciplines}
          toggleAccept={toggleAccept}
        />
      ) : (
        <div className={"specialitiesComboBox"}>
          <h2 style={{ color: "#130C7D", align: "center" }}>
            No proposed optionals to show
          </h2>
        </div>
      )}
      <div className="buttonContainer">
        <Button
          size="large"
          variant="contained"
          onClick={saveSelection}
          style={{ transform: "scale(1.2)" }}
        >
          Accept the selected optionals
        </Button>
      </div>
    </div>
  );
};

export default ChiefYearAccept;
