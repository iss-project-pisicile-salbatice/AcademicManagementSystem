import React from "react";
import TeacherItem from "./TeacherItem"; 

const TeacherList = ({ teachers }) => {
    // Return a list of teacher components
    return (
        <div className="teacherContainer">
            {teachers.map((teacher) => (
                <TeacherItem key={teacher.teacherId} teacher={teacher} />
            ))}</div>
    )
};

export default TeacherList;