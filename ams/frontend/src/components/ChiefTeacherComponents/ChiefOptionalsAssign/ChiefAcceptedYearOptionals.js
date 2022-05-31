import React from "react";
import { useState, useEffect } from "react";
import { Button, Divider } from "@mui/material";
import ChiefAcceptedOptionalsList from "./ChiefAcceptedOptionalsList";

const ChiefAcceptedYearOptionals = ({ yearId }) => {
    // State for the accepted optionals
    const [acceptedOptionals, setAcceptedOptionals] = useState([]);

    useEffect(() => {
        const getAcceptedOptionals = async () => {
            const optionalsFromAPI = await fetchTasks();
            setAcceptedOptionals(optionalsFromAPI);
        };

        getAcceptedOptionals();
    }, []);

    // Fetch data from API
    const fetchTasks = async () => {
        const res = await fetch(`http://localhost:8080/chief/accepted_optionals/${yearId}`);
        const data = await res.json();

        return data;
    };

    // Set the new maximum value
    const changeMaximum = (optionalId, newMaximum) => {
        newMaximum = Number(newMaximum);
        //console.log(newMaximum, optionalId);
        let stateCopy = structuredClone(acceptedOptionals);
        //console.log(stateCopy);
        for (let acceptedOptional of stateCopy) {
            if (acceptedOptional.courseId === optionalId) {
                //console.log(acceptedOptional);
                acceptedOptional.maximumStudents = newMaximum;
                //console.log(acceptedOptional);
            }
        }
        //console.log(stateCopy);

        // Set new state
        setAcceptedOptionals(stateCopy);
    };

    const createOptionalsMaxList = () => {
        let optionalsArray = acceptedOptionals.map((optionalVal) => ({
            "optionalId": optionalVal.courseId,
            "maximumStudents": optionalVal.maximumStudents
        }));
        let greatObject = ({
            "yearId": yearId,
            "optionalMaximumList": optionalsArray
        });
        return greatObject;
    }

    const saveSelection = () => {
        let sendData = createOptionalsMaxList();

        var requestOptions = {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(sendData),
            redirect: 'follow'
        };

        fetch(
            `http://localhost:8080/chief/optionals/maximum`,
            requestOptions
        )
            .then(response => response.text())
            .then((result) => {
                try {
                    result = JSON.parse(result);
                    if (result.status && result.status != 200) {
                        alert(result.message);
                    }
                } catch (e) {
                    alert("Maximum students set correctly!");
                }
            })
            .catch((error) => alert(error.message));

        // send here -> /chief/optionals/maximum
        /*
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

        fetch(`http://localhost:8080/chief/optionals/?optionalId=${proposedOptional}`, requestOptions)
            .then((response) => response.text())
            .then((result) => console.log(result))
            .catch((error) => console.log("error", error));
      })*/
    };

    const assignOptionals = () => {
        console.log("Assign optionals");
    };

    return (
        <div>
            {acceptedOptionals.length > 0 ? (
                <ChiefAcceptedOptionalsList
                    acceptedOptionals={acceptedOptionals}
                    changeMaximum={changeMaximum}
                />
            ) : (
                "No proposed optionals to show"
            )}
            <div className="buttonContainer">
                <Button
                    size="large"
                    variant="contained"
                    onClick={saveSelection}
                    style={{ transform: "scale(1.5)" }}
                >
                    Accept the maximum number of students
                </Button>
                <Divider />
                <Button
                    size="large"
                    variant="contained"
                    onClick={saveSelection}
                    style={{ transform: "scale(1.5)" }}
                >
                    Assign the current optionals
                </Button>
            </div>
        </div>
    );
}

export default ChiefAcceptedYearOptionals;