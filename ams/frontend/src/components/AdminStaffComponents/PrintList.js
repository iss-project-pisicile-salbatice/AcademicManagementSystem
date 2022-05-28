import React from "react";
import { useFormControl } from "@mui/material/FormControl";
import "./PrintList.css";
import TextField from "@mui/material/TextField";
import FormControl from "@mui/material/FormControl";
import Button from "@mui/material/Button";

const PrintList = () => {
  return (
    <div className="printList">
      <h3>See student data</h3>
      <form>
        <div className="printListInput">
          <label for="Id"> Year ID:</label>
          <input type="Text" id="Id" placeholder="Id..." />
        </div>
        <button type="submit" className="button">Submit</button>
      </form>
      
    </div>
  );
};

export default PrintList;
