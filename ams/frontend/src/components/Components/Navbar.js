import React, { useState } from "react";
import "./Components.css"
import OptionsModal from "./OptionsModal";

export default function Navbar() {

  const [isOpen, setIsOpen] = useState(false)
  return (
    <div className="Nav1">
      <div className="studentName">
        <button onClick={()=> setIsOpen(true)} className="avatar" style={{backgroundImage:`url(https://images.pexels.com/photos/34153/pexels-photo.jpg?auto=compress&cs=tinysrgb&h=350)`}}/>
        {/* <OptionsModal open={isOpen}/> */}
      </div>
      <div className="studentName">Student: Ianis Teja</div>
    </div>

  );
}
