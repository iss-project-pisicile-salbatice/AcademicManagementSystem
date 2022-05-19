import React from 'react'
import EnrollmentItem from './EnrollmentItem'
import "./Enrollment.css";


const EnrollmentList = ({enrollments}) => {
    return (
        <div class="enrollmentContainer">{enrollments.map((enrollment) => (
            <EnrollmentItem enrollment={enrollment}/>
        ))}</div>
    )
}

export default EnrollmentList