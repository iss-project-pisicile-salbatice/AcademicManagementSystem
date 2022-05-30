import React from "react";
import { useFormControl } from "@mui/material/FormControl";
import "./PrintList.css";
import { useState, useEffect } from "react";
import TextField from "@mui/material/TextField";
import FormControl from "@mui/material/FormControl";
import Button from "@mui/material/Button";
import ResultList from "./ResultList";
import ResultItem from "./ResultItem";

const PrintList = () => {
  const [resultList, setResultList] = useState([]);
  const [groupId, setGroupId] = useState("");

  const fetchTasks = async () => {
    console.log("groupId:" + groupId);
    const res = await fetch(
      `http://localhost:8080/admin/results/?yearSpecialityId=${groupId}`
    );
    const data = await res.json();
    console.log("data:"+data);
    return data;
  };

  useEffect(() => {
    const getResults = async () => {
      const resultsFromAPI = await fetchTasks();
      setResultList(resultsFromAPI);
      console.log("resultList:"+resultList);
    };

    getResults();
  }, []);

  const handleChange = (event) => {
    setGroupId(event.target.value);

    console.log("value is:", event.target.value);
  };

  const handleClick = async (event) => {
    event.preventDefault();
    const resultsFromAPI = await fetchTasks();
    setResultList(resultsFromAPI);
    console.log("resultList:"+resultList);
  };

  return (
    <div className="printList">
      <h3>See student data</h3>
      <form>
        <div className="printListInput">
          <label for="Id"> Year ID:</label>
          <input
            type="Text"
            id="groupId"
            placeholder="Year ID..."
            onChange={handleChange}
            value={groupId}
            autoComplete="off"
          />
        </div>
        <button
          type="submit"
          className="button"
          onClick={handleClick}
        >
          Submit
        </button>
      </form>
      <p>
        {resultList.length > 0 ? (<ResultList results={resultList} />) : (<h2>Nothing to show</h2>)}
      </p>
    </div>
  );
};

export default PrintList;
