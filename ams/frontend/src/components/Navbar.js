import React, { useState } from "react";
import "./Components.css";
import OptionsModal from "./OptionsModal";

export default function Navbar({userName, role, imgUser}) {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <div className="Nav1">
      <div className="c">
        <div className="studentName">
          <img
            onClick={() => setIsOpen((isOpen) => !isOpen)}
            className="avatar" src={require(`../resources/${imgUser}`)}
          />
          <OptionsModal open={isOpen} />
        </div>
        <div className="studentName">{role}: {userName}</div>
      </div>
      <div className="d2">
        <img
          className="universityLogo"
          src={require("../resources/logo_ubb.png")}
        />
      </div>
    </div>
  );
}
