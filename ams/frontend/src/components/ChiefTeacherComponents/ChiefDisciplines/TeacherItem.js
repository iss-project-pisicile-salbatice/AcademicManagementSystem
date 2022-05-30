import React from "react";
import { useState, useEffect } from "react";
import DisciplineList from "./DisciplineList";

const TeacherItem = ({ teacher }) => {
    const [isActive, setIsActive] = useState(false);
    const [disciplineList, setDisciplineList] = useState([]);
  
    //Fetch data
    const fetchTasks = async () => {
      const res = await fetch(
        `http://localhost:8080/chief/teachers/${teacher.teacherId}`
      );
      const data = await res.json();
  
      return data;
    };
  
    useEffect(() => {
      const getDisciplines = async () => {
        const disciplinesFromAPI = await fetchTasks();
        setDisciplineList(disciplinesFromAPI);
      };
  
      getDisciplines();
    }, []);
  
    return (
      <div className="teacherItem">
        <div className="teacherData" onClick={() => setIsActive(!isActive)}>
          <h4>{teacher.degree} {teacher.name}</h4>
          <p>Email: {teacher.email}</p>
        </div>
  
        {isActive && disciplineList.length > 0 ? (
          <DisciplineList disciplines={disciplineList} />
        ) : (
          " "
        )}
      </div>
    );
};

export default TeacherItem;