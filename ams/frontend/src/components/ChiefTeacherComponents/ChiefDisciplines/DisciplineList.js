import React from "react";
import DisciplineItem from "./DisciplineItem";

const DisciplineList = ({ disciplines }) => {
    // Return a list of discipline components
    return (
        <div class="disciplinesContainer">
            {disciplines.map((discipline) => (
                <DisciplineItem discipline={discipline} />
            ))}</div>
    )
};

export default DisciplineList;