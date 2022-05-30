import {requirePropFactory} from "@mui/material";
import React from "react";
import {useRef} from "react";
import "../Components.css";
import Navbar from "../Navbar";
import MenuBox from "./MenuBox";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import PrintList from "../AdminStaffComponents/PrintList"

export default function Menu({userName, role}) {
    if (role === 'Student') {
        return (
            <div>
                <Navbar userName={userName} role={role} imgUser={'userMockUp.png'}/>
                <div className="components">
                    <MenuBox link={'/enroll'} imgUrl={'enroll.jpg'} title={'Enroll'}/>
                    <MenuBox link={'/optionals'} imgUrl={'optionals.png'} title={'Optionals'}/>
                    <MenuBox link={'/contract'} imgUrl={'contract.jpg'} title={'Contract'}/>
                    <MenuBox link={'/grades'} imgUrl={'grades.jpg'} title={'Grades'}/>
                </div>
            </div>)
    } else if (role==="Administrator")  {
        return (<div>
            <Navbar userName={userName} role={role} imgUser={'userMockUp.png'}/>
            <PrintList/>
        </div>)
    } else if (role==="Chief Teacher") {
        return (
            <div>
                <Navbar userName={userName} role={role} imgUser={'userMockUp.png'}/>
                <div className="components">
                    <MenuBox link={'/chief_optionals_accept'} imgUrl={'contract.jpg'} title={'Accept optionals'}/>
                    <MenuBox link={'/chief_optionals_assign'} imgUrl={'optionals.png'} title={'Assign optionals'}/>
                    <MenuBox link={'/chief_disciplines'} imgUrl={'teacher.png'} title={'Disciplines'}/>
                    <MenuBox link={'/chief_teachers'} imgUrl={'grades.jpg'} title={'Teacher grades'}/>
                </div>
            </div>
        );
    }
    else if (role==="Teacher") {
        return (
            <div>
                <Navbar userName={userName} role={role} imgUser={'userMockUp.png'}/>
                <div className="components">
                    <MenuBox link={'/teacher_propose_optionals'} imgUrl={'optionals.png'} title={'Propose Optionals'}/>
                   
                </div>
            </div>
        );
    }
}
