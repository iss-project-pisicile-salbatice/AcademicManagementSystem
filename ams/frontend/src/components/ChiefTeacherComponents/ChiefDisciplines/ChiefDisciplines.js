import React from "react";
import Navbar from "../../Navbar";
import { useState, useEffect } from "react";
import TeacherList from "./TeacherList";
import './ChiefDisciplines.css';

const ChiefDisciplines = () => {
    // State for the teachers list
    const [teachers, setTeachers] = useState([]);

    useEffect(() => {
        const getTeachers = async () => {
            const teacherFromAPI = await fetchTasks();
            setTeachers(teacherFromAPI);
        };

        getTeachers();
    }, []);

    // Fetch the teachers data
    const fetchTasks = async () => {
        const res = await fetch("http://localhost:8080/chief/teachers/all");
        const data = await res.json();

        return data;
    };

    return (
        <div>
            <Navbar
                userName={"Ianis Teja"}
                role={"Chief teacher"}
                imgUser={"userMockUp.png"}
            />
            <div>
                {teachers.length > 0 ? (
                    <React.Fragment>
                        <h2 className="listTitle">List of teachers:</h2>
                        <TeacherList
                            teachers={teachers}
                        />
                    </React.Fragment>
                ) : (
                    "No teachers to show"
                )}
            </div>
        </div>
    );
};

export default ChiefDisciplines;