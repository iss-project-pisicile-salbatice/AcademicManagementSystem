import React, { useState, useEffect } from "react";
import Navbar from "../../Navbar";
import {
  MenuItem,
  Box,
  Select,
  InputLabel,
  Divider,
  TextField,
  Input,
} from "@mui/material";
import FormControl from "@mui/material/FormControl";
import "./TeacherOptional.css";

const TeacherOptional = () => {
  const [specialityID, setSpecialityID] = useState("");
  const [yearSpecialities, setYearSpecialities] = useState([]);
  const [optionalName, setOptionalName] = useState("");

  const fetchOptions = async () => {
    const res = await fetch("http://localhost:8080/admin/years");
    const data = await res.json();
    console.log("optionsData:" + data);
    return data;
  };

  useEffect(() => {
    const getYearSpecialities = async () => {
      const resultsFromAPI = await fetchOptions();
      setYearSpecialities(resultsFromAPI);
      console.log("specialitiesList:" + yearSpecialities);
    };

    getYearSpecialities();
  }, []);

  const fetchTasks = async () => {
    console.log("groupId:" + specialityID);
    const res = await fetch(
      `http://localhost:8080/admin/results/?yearSpecialityId=${specialityID}`
    );
    const data = await res.json();
    console.log("data:" + data);
    return data;
  };

  const handleChange = (event) => {
    setSpecialityID(event.target.value);
    console.log("value is:", event.target.value);
  };

  const handleChangeOptional = (event) => {
    setOptionalName(event.target.value);
    console.log("optionalvalue is:", event.target.value);
  };

  const handleClick = (event) => {
    event.preventDefault();
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "text/plain");


    const requestOptions = {
      method: "POST",
      headers: myHeaders,
      redirect: "follow",
    };

    fetch(
      `http://localhost:8080/teachers/proposed_optionals/?teacherID=3&yearSpecialityID=${specialityID}&optionalName=${optionalName}`,
      requestOptions
    )
      .then((response) => response.text())
      .then((result) => console.log(result))
      .catch((error) => console.log("error", error));

      setOptionalName("");
  };

  return (
    <div>
      <Navbar
        userName={"Ianis Teja"}
        role={"Teacher"}
        imgUser={"userMockUp.png"}
      />
      <div className="TeacherOptionalBody">
        <Box sx={{ minWidth: 240 }}>
          <FormControl>
            <InputLabel id="demo-simple-select-label">Specialities</InputLabel>
            <Select
              labelId="demo-simple-select-label"
              id="spec-label-id"
              value={specialityID}
              label="Specialities"
              onChange={handleChange}
            >
              {yearSpecialities.map((yearSpeciality) => (
                <MenuItem
                  value={yearSpeciality.yearId}
                  key={yearSpeciality.yearId}
                >
                  {yearSpeciality.year} {yearSpeciality.speciality}
                </MenuItem>
              ))}
            </Select>
            <Divider />
            <br />
          </FormControl>
        </Box>

        <Box>
          <FormControl>
            <Input
              margin="dense"
              id="outlined-basic"
              label="Outlined"
              variant="standard"
              placeholder="Optional Name"
              style={{ transform: "scale(1.1)" }}
              value={optionalName}
              onChange={handleChangeOptional}
            />
          </FormControl>
        </Box>
        <button type="submit" className="button" onClick={handleClick}>
          Submit
        </button>
      </div>
    </div>
  );
};

export default TeacherOptional;
