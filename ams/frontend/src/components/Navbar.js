import React, { useState } from "react";
import "./Components.css";
import OptionsModal from "./OptionsModal";

export default function Navbar({studentName}) {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <div className="Nav1">
      <div className="c">
        <div className="studentName">
          <button
            onClick={() => setIsOpen((isOpen) => !isOpen)}
            className="avatar" style={{backgroundImage:`url('https://s3.amazonaws.com/cms-assets.tutsplus.com/uploads/users/810/profiles/19338/profileImage/profile-square-extra-small.png')`}}
          ></button>
          <OptionsModal open={isOpen} />
        </div>
        <div className="studentName">Student: {studentName}</div>
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
