import React from "react";
import "../Components.css";
import Navbar from "../Navbar";
import {useState, useEffect} from "react";

export default function Grades(props) {
    const [nrGrades, setNrGrades] = useState(5);
    const [values, setValues] = useState([]);

    const getStudentGrades = async () => {
        var requestOptions = {
            method: 'GET',
            redirect: 'follow'
        };

      await  fetch("http://localhost:8080/students/grades/1", requestOptions)
            .then(response => response.json())
            .then(result => {
                setValues(result);
                console.log(result)
                console.log("lalala")
            })
            .catch(error => console.log('error', error));
    }

    useEffect(() => {
        getStudentGrades();
    }, []);

    return (
        <div>
            <Navbar/>
            <h2 className="pageTitle">Grades</h2>
            <div>
                {/* <table>
                    <tbody>
                    {values.map((value) => (
                        <tr>
                            <td>{value.course.courseName}</td>
                            <td>{value.grade}</td>
                        </tr>
                    ))}
                    </tbody>
                </table> */}
            </div>
        </div>
    );
}
