import React from "react";
import Navbar from '../Navbar';
import { useState, useEffect } from "react";
import SyllabusList from './SyllabusList'

export default function Sylabus({id}) {
  const [syllabus, setSyllabus] = useState([]);

  useEffect(() => {
    const getSyllabus = async () => {
      const syllabusFromAPI = await fetchTasks();
      setSyllabus(syllabusFromAPI);
    };

    getSyllabus();
  },[]);

   //Fetch data
   const fetchTasks = async () => {
    const res = await fetch(`http://localhost:8080/students/enroll/${id}`);
    const data = await res.json();

    return data;
  };


  return (
    <div>
      <Navbar
        userName={"Ianis Teja"}
        role={"Student"}
        imgUser={"userMockUp.png"}
      />
 {syllabus.length > 0 ? (<SyllabusList syllabusList={syllabus}/>) : (<h1 style={{color: "white"}}>No enrollments to show</h1>)}    </div>
  );
}
