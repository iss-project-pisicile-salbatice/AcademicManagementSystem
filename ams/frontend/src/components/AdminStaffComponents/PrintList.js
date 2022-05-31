import React from "react";
import "./PrintList.css";
import {useState, useEffect} from "react";
import TextField from "@mui/material/TextField";
import FormControl from "@mui/material/FormControl";
import Button from "@mui/material/Button";
import ResultList from "./ResultList";
import ResultItem from "./ResultItem";
import {MenuItem, Box, Select, InputLabel, Divider} from "@mui/material";

const PrintList = () => {
    const [resultList, setResultList] = useState([]);
    const [specialityID, setSpecialityID] = useState("");
    const [yearSpecialities, setYearSpecialities] = useState([]);

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

    useEffect(() => {
        const getResults = async () => {
            const resultsFromAPI = await fetchTasks();
            setResultList(resultsFromAPI);
            console.log("resultList:" + resultList);
        };

        getResults();
    }, []);

    const handleChange = (event) => {
        setSpecialityID(event.target.value);
        console.log("value is:", event.target.value);
    };

    const handleClick = async (event) => {
        event.preventDefault();
        const resultsFromAPI = await fetchTasks();
        setResultList(resultsFromAPI);
        console.log("resultList:" + resultList);
    };

    return (
        <div className="printList">
            <h3>See student data</h3>
            <form>
                <div className="printListInput">
                    {/* <label for="Id"> Year ID:</label>
          <input
            type="Text"
            id="groupId"
            placeholder="Year ID..."
            onChange={handleChange}
            value={groupId}
            autoComplete="off"
          /> */}

                    <Box sx={{minWidth: 240}}>
                        <FormControl>
                            {" "}
                            <InputLabel id="demo-simple-select-label">
                                Specialities
                            </InputLabel>
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
                        </FormControl>
                    </Box>
                </div>
                <button type="submit" className="button" onClick={handleClick}>
                    Submit
                </button>
            </form>
            <p>
                {resultList.length > 0 ? (
                    <ResultList results={resultList}/>
                ) : (
                    <h2>Nothing to show</h2>
                )}
            </p>
        </div>
    );
};

export default PrintList;
