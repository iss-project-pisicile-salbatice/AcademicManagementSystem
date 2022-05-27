import { requirePropFactory } from "@mui/material";
import React from "react";
import { useRef } from "react";
import "../Components.css";
import Navbar from "../Navbar";
import MenuBox from "./MenuBox";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

export default function Menu() {
  return (
    <div>
      <Navbar userName={'Ianis Teja'} role={'Student'} imgUser={'userMockUp.png'} />
      <div className="components">
         <MenuBox link={'/enroll'} imgUrl={'enroll.jpg'} title={'Enroll'}/> 
         <MenuBox link={'/optionals'} imgUrl={'optionals.png'} title={'Optionals'}/>
         <MenuBox link={'/contract'} imgUrl={'contract.jpg'} title={'Contract'}/>
         <MenuBox link={'/grades'} imgUrl={'grades.jpg'} title={'Grades'}/>
        {/* <a href="/syllabus">
          <div className="box1">
          <img src={require("../../resources/syllabus.jpg")} className="logos"/>
            Syllabus</div>
        </a>
        <a href="/optionals">
          <div className="box1">
          <img src={require("../../resources/optionals.png")} className="logos"/>
            Optionals</div>
        </a>
        <a href="/contract">
          <div className="box1">
          <img src={require("../../resources/contract.jpg")} className="logos"/>
            Contract</div>
        </a>
        <a href="/grades">
          <div className="box1">
          <img src={require("../../resources/grades.jpg")} className="logos"/>
            Grades</div>
        </a> */}
      </div>
    </div>
  );
}
