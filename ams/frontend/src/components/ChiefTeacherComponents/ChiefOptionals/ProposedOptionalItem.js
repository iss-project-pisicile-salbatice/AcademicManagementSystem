import React, { useState, useEffect } from 'react';
import { Checkbox } from '@mui/material';
import Divider from "@mui/material/Divider";

const ProposedOptionalItem = ({ proposedOptional, toggleAccept }) => {
    const [onToggle, setOnToggle] = useState(false);

    const handleChange = (event) => {
        setOnToggle(event.target.checked);
        toggleAccept(proposedOptional.optionalID);
    };

    return (
        <div className="proposedOptionalItem">
            <div className="proposedOptionalData">
                <h2>{proposedOptional.courseName}</h2>
                <h4>Taught by: {proposedOptional.teacherName}</h4>
            </div>
            <Divider/>

            <div className="proposedOptionalCheckbox">
                <label>Accept optional</label>
                <Checkbox
                    checked={onToggle}
                    onChange={handleChange}
                    inputProps={{ 'aria-label': 'controlled' }}
                />
            </div>
        </div>
    );
}

export default ProposedOptionalItem;