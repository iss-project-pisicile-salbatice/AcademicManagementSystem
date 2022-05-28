import React, { useState } from 'react'
import EnrollmentItem from './EnrollmentItem'
import "./Enrollment.css";


const EnrollmentList = ({enrollments, toggleEnroll}) => {
    const [onToggleList, setOnToggleList]= useState([]);


    return (
        <div class="enrollmentContainer">{enrollments.map((enrollment) => (
            <EnrollmentItem enrollment={enrollment} toggleEnroll={toggleEnroll}/>
            // setOnToggleList(enrollment.target.getAttribute)
        ))}</div>
    )
}

export default EnrollmentList