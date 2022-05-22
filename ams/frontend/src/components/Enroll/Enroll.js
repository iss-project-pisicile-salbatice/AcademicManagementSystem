import React from "react";
import Navbar from '../Navbar';
import { useState, useEffect } from "react";
import EnrollmentList from "./EnrollmentList";

export default function Enroll() {
  const [enrollments, setEnrollments] = useState([]);
  const [chosenEnrollments, setChosenEnrollments] = useState([]);

  useEffect(() => {
    const getEnrollments = async () => {
      const enrollmentsFromAPI = await fetchTasks();
      setEnrollments(enrollmentsFromAPI);
    };

    getEnrollments();
  },[]);

   //Fetch data
   const fetchTasks = async () => {
    const res = await fetch("http://localhost:8080/students/enroll");
    const data = await res.json();

    return data;
  };

  return (
    <div>
      <Navbar userName={'Ianis Teja'} role={'Student'} imgUser={'userMockUp.png'} />
      <div>
      {enrollments.length > 0 ? (<EnrollmentList enrollments={enrollments}/>) : ('No enrollments to show')}
      </div>
    </div>
  );
}
