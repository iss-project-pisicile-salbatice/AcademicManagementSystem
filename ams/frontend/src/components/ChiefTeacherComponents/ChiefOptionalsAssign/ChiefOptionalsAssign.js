import React from "react";
import { useState, useEffect } from "react";
import ChiefAcceptedYearOptionals from "./ChiefAcceptedYearOptionals";
import { Input, Box, Select, MenuItem, InputLabel } from "@mui/material";
import Navbar from "../../Navbar";

const ChiefOptionalsAssign = () => {
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
                role={"Chief teacher"}
                imgUser={"userMockUp.png"}
            />
            <div className="mainAcceptedOptionalsDiv">
                <Box sx={{ width: 400   }}>
                    <InputLabel id="demo-simple-select-label">Specialities</InputLabel>
                    <Select
                        labelId="spec-label"
                        id="spec-label-id"
                        value={selectedYear}
                        label="YearSpeciality"
                        onChange={handleChange}
                    >
                        {yearSpecialities.map((yearSpeciality) => (
                        <MenuItem value={yearSpeciality.yearId} key={yearSpeciality.yearId}>{yearSpeciality.year} {yearSpeciality.speciality}</MenuItem>))}
                    </Select>
                </Box>
                <div>
                    {yearSpecialities.map((yearSpeciality) =>
                    (yearSpeciality.yearId === selectedYear && <ChiefAcceptedYearOptionals key={selectedYear} yearId={selectedYear}/>))}
                </div>
            </div>
        </div>
    );
};

export default ChiefOptionalsAssign;