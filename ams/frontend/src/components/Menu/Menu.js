import {requirePropFactory} from "@mui/material";
import React from "react";
import {useRef} from "react";
import "../Components.css";
import Navbar from "../Navbar";
import MenuBox from "./MenuBox";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";

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
    } else {
        return (<div>
            <Navbar userName={userName} role={role} imgUser={'userMockUp.png'}/>
            <div className="components">
                <MenuBox link={'/enroll'} imgUrl={'enroll.jpg'} title={'Enroll'}/>
                <MenuBox link={'/optionals'} imgUrl={'optionals.png'} title={'Optionals'}/>
                <MenuBox link={'/contract'} imgUrl={'contract.jpg'} title={'Contract'}/>
                <MenuBox link={'/grades'} imgUrl={'grades.jpg'} title={'Grades'}/>
            </div>
        </div>)
    }
}
