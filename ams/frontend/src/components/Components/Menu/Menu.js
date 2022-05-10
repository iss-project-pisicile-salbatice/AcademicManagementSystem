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
          <div className="box1">Enroll</div>
        </a>
        <a href="/sylabus">
          <div className="box1">Sylabus</div>
        </a>
        <a href="/optionals">
          <div className="box1">Optionals</div>
        </a>
        <a href="/contract">
          <div className="box1">Contract</div>
        </a>
        <a href="/grades">
          <div className="box1">Grades</div>
        </a>
      </div>
    </div>
  );
}
