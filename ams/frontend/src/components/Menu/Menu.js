import { requirePropFactory } from "@mui/material";
import React from "react";
import { useRef } from "react";
import "../Components.css";
import Navbar from "../Navbar";
export default function Menu() {
  return (
    <div>
      <Navbar />
      <div className="components">
        <a href="/enroll">
          <div className="box1">
            <img src={require("../../resources/enroll.jpg")} className="logos"/>
            Enroll
          </div>
        </a>
        <a href="/syllabus">
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
        </a>
      </div>
    </div>
  );
}
