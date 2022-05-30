import React from 'react'
import Divider from "@mui/material/Divider";

const SyllabusItem = ({syllabus}) => {

  return (
    <div>
        <h5>Course: {syllabus.courseName}</h5>
        <h5>Professor: {syllabus.teacherName}</h5>
        <Divider />
    </div>
  )
}

export default SyllabusItem