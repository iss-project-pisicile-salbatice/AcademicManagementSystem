import React, { useState } from "react";
import "./Components.css"
import OptionsModal from "./OptionsModal";

export default function Navbar() {

  const [isOpen, setIsOpen] = useState(false)
  return (
    <div className="Nav1">
      <div className="d1">
        <button onClick={()=> setIsOpen(true)} className="avatar"/>
        {/* <OptionsModal open={isOpen}/> */}
      </div>
      <div className="d1">Student: Ianis Teja</div>
    </div>

  );
}
