import React, { useState } from "react";
import "./Components.css";
import OptionsModal from "./OptionsModal";

export default function Navbar() {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <div className="Nav1">
      <div className="c">
        <div className="d1">
          <button
            onClick={() => setIsOpen((isOpen) => !isOpen)}
            className="avatar"
          ></button>
          <OptionsModal open={isOpen} />
        </div>
        <div className="d1">Student: Ianis Teja</div>
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
