import React, { useState } from 'react'
import EnrollmentItem from './EnrollmentItem'
import "./Enrollment.css";


const EnrollmentList = ({enrollments}) => {
    const [onToggleList, setOnToggleList]= useState([]);


    return (
        <div class="enrollmentContainer">{enrollments.map((enrollment) => (
            <EnrollmentItem enrollment={enrollment}/>
            // setOnToggleList(enrollment.target.getAttribute)
        ))}</div>
    )
}

export default EnrollmentList