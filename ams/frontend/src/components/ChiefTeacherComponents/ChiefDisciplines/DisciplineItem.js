import React from 'react'
import Divider from "@mui/material/Divider";

const DisciplineItem = ({discipline}) => {
  return (
    <div>
        <h5>Course name: {discipline.courseName}</h5>
        <h5>Year and speciality: {discipline.yearSpeciality}</h5>
        {discipline.optional == true ? (
            <h5>This is an optional course.</h5>
        ) : (" ")}
        <Divider />
    </div>
  )
}

export default DisciplineItem;