import React, { useState } from "react";
import "./Components.css"
import OptionsModal from "./OptionsModal";

export default function Navbar() {

  const [isOpen, setIsOpen] = useState(false)
  return (
    <div className="Nav1">
      <div className="d1">
        <button onClick={()=> setIsOpen(true)} className="avatar" style={{backgroundImage:"https://s3.amazonaws.com/cms-assets.tutsplus.com/uploads/users/810/profiles/19338/profileImage/profile-square-extra-small.png"}}/>
        {/* <OptionsModal open={isOpen}/> */}
      </div>
      <div className="d1">Student: Ianis Teja</div>
    </div>

  );
}
