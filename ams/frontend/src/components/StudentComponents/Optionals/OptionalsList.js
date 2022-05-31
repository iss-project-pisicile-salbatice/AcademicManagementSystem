import React, { useState } from "react";
import OptionalItem from "./OptionalItem";
import "./Optionals.css";
const OptionalsList = ({ optionals, yearSpeciality, open }) => {
  const [body, setBody] = useState({
    studentID: "1",
    yearSpeciality: yearSpeciality,
    ratings: [0, 0],
    recievedDate: "2022-10-10",
    receivedTime: "23:12:25",
  });

  const setresponse = (name, value) => {
    setBody((oldValues) => ({ ...oldValues, [name]: value }));
  };

  const handleChange = (event, i) => {
    const arr1 = new Array();
    arr1.push(i);
    console.log("the cID: ", i);
    arr1.push(event.target.value);
    setresponse("ratings", arr1);
    console.log("the body:", body);
    var ok = 0;
    for (var j = 0; j < allPreferfences.length; j++) {
      console.log("234", allPreferfences[j].ratings[0]);
      if (allPreferfences[j].ratings[0] == i) {
        var ok = 1;
        var arr2 = new Array();
        arr2 = allPreferfences;
        arr2[j] = body;
        setAllPreferences(arr2);
      }
    }
    if (ok === 0) {
      console.log("new");
      var arr2 = new Array();
      arr2 = allPreferfences;
      arr2.push(body);
      setAllPreferences(arr2);
    }
  };

  const postStudentGrades = async () => {
    var myHeaders = new Headers();
    myHeaders.append(
      "Authorization",
      "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpYW5pc3RlamEiLCJpYXQiOjE2NTM5MzIxMTAsImV4cCI6MTY1NDAxODUxMH0.t-qZlsHQAUhixn2kVXyB6zXhY3nmOqqp-Ka5mxTSKQDHXwNEpaWqmfRsTAAMxJeaG_isxLXuWiuS-61G4lq9Cw"
    );
    myHeaders.append("Content-Type", "application/json");
    var requestOptions = {
      method: "POST",
      headers: myHeaders,
      body: body,
      redirect: "follow",
    };
    fetch("http://localhost:8080/students/rate_optionals", requestOptions)
      .then((response) => response.text())
      .then((result) => {
        console.log("in post: ");
        console.log(result);
      })
      .catch((error) => console.log("error", error));
  };

  const [allPreferfences, setAllPreferences] = useState([]);
  console.log("sdfasdf", allPreferfences);
  const onSubmit = async () => {
    allPreferfences.map((pref) => postStudentGrades(pref));
  };
  if (open == false) {
    return null;
  }
  if (optionals.length == 0) {
    return <div>There are 0 optionals in this speciality</div>;
  }
  if (optionals.length == 1) {
    return (
      <div className="optionalsList">
        {optionals.map((optional) => (
          <div className="optionalItem">
            <OptionalItem optional={optional} />
          </div>
        ))}
      </div>
    );
  }
  return (
    <div className="optionalsList">
      <form onSubmit={onSubmit}>
        {optionals.map((optional, i) => (
          <div>
            <div className="optionalItem">
              <OptionalItem optional={optional} />
            </div>
            {optionals.map((optional2, index) => (
              <div className="check1">
                <label>{index + 1}</label>
                <input
                  value={index + 1}
                  id="check"
                  name={i}
                  onChange={(event) => handleChange(event, optional.optionalID)}
                  type="radio"
                />
              </div>
            ))}
          </div>
        ))}
        <input type="submit"></input>
      </form>{" "}
    </div>
  );
};

export default OptionalsList;
