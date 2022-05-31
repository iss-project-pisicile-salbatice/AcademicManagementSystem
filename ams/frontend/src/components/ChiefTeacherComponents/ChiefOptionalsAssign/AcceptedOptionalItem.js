import React from "react";
import { useState, useEffect } from "react";
import { TextField } from "@mui/material";

const AcceptedOptionalItem = ({ acceptedOptionalId, acceptedOptionalName, acceptedOptionalTeacher, changeMaximum }) => {
    const [maximumValue, setMaximumValue] = useState(0);

    const handleChange = (event) => {
        setMaximumValue(Number(event.target.value));
        changeMaximum(acceptedOptionalId, Number(event.target.value));
    };

    return (
        <div className="acceptedOptionalItem">
            <div className="acceptedOptionalData">
                <h2>{acceptedOptionalName}</h2>
                <h4>Taught by: {acceptedOptionalTeacher}</h4>
            </div>

            <div className="proposedOptionalCheckbox">
                <label>Maximum number of students</label>
                <TextField id="standard-basic" label="Standard" variant="standard" style={{
                    transform: "scale(1.2)",
                }} onChange={handleChange} />
            </div>
        </div>
    );
}

export default AcceptedOptionalItem;