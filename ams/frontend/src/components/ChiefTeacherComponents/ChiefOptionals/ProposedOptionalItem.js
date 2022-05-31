import React, { useState, useEffect } from 'react';
import { Checkbox, stepClasses } from '@mui/material';
import { MenuItem, Box, Select, InputLabel, Divider } from "@mui/material";


const ProposedOptionalItem = ({ proposedOptional, toggleAccept }) => {
    const [onToggle, setOnToggle] = useState(false);

    const handleChange = (event) => {
        setOnToggle(event.target.checked);
        toggleAccept(proposedOptional.optionalID);
    };

    return (
        <div className="proposedOptionalItem">
            <div className="proposedOptionalData">
                <h2>Name: {proposedOptional.courseName}</h2>
                <h3>Teacher: {proposedOptional.teacherName}</h3>
            </div>
            <Divider/>

            <div className="proposedOptionalCheckbox">
                <InputLabel><b>Accept optional</b></InputLabel>
                <Checkbox
                    checked={onToggle}
                    onChange={handleChange}
                    inputProps={{ 'aria-label': 'controlled' }}
                    style={{
                        transform: "scale(1.2)"
                    }}
                />
            </div>
        </div>
    );
}

export default ProposedOptionalItem;