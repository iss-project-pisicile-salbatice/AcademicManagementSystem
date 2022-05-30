import React from "react";
import { useState, useEffect } from "react";
import { MenuItem, Box, Select, InputLabel, FormControl } from "@mui/material";
import Navbar from "../../Navbar";
import ChiefYearAccept from "./ChiefYearAccept";
import "./ChiefOptionals.css";

const ChiefOptionalsAccept = () => {
  // State part for the year specialities
  const [yearSpecialities, setYearSpecialities] = useState([]);
  // State part for the chosen year in the selection
  const [selectedYear, setSelectedYear] = useState("");

  // Fetch the data in the Effect
  useEffect(() => {
    const getYearSpecialities = async () => {
      const specialitiesFromAPI = await fetchTasks();
      setYearSpecialities(specialitiesFromAPI);
    };

    getYearSpecialities();
  }, []);

  // Fetch the data from the API
  const fetchTasks = async () => {
    const res = await fetch("http://localhost:8080/chief/year_specialities");
    const data = await res.json();

    return data;
  };

  // This is for the menu
  const handleChange = (event) => {
    setSelectedYear(event.target.value);
  };

  return (
    <div>
      <Navbar
        userName={"Ianis Teja"}
        role={"Chief Teacher"}
        imgUser={"userMockUp.png"}
      />
      <div>
        <div className={"specialitiesComboBox"}>
          <Box sx={{ width: "100%" }}>
            <FormControl fullWidth>
              <InputLabel id="demo-simple-select-label">
                Specialities
              </InputLabel>
              <Select
                labelId="spec-label"
                id="spec-label-id"
                value={selectedYear}
                label="YearSpeciality"
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
            </FormControl>
          </Box>
        </div>
        <div>
          {yearSpecialities.map(
            (yearSpeciality) =>
              yearSpeciality.yearId === selectedYear && (
                <ChiefYearAccept key={selectedYear} yearId={selectedYear} />
              )
          )}
        </div>
      </div>
    </div>
  );
};

export default ChiefOptionalsAccept;
