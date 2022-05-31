import React from "react";
import { useState, useEffect } from "react";
import AcceptedOptionalItem from "./AcceptedOptionalItem";

const ChiefAcceptedOptionalsList = ({acceptedOptionals, changeMaximum}) => {
    return (
        <div className="optionalsContainer">{acceptedOptionals.map((acceptedOptional) => (
            <AcceptedOptionalItem key={acceptedOptional.optionalID} 
            acceptedOptionalId={acceptedOptional.courseId} 
            acceptedOptionalName={acceptedOptional.courseName}
            acceptedOptionalTeacher={acceptedOptional.teacherName}
            changeMaximum={changeMaximum}/>
        ))}</div>
    )
}

export default ChiefAcceptedOptionalsList;