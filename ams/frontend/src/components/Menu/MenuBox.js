import React from "react";
import "../Components.css";
import { useRef } from "react";
import { requirePropFactory } from "@mui/material";

const MenuBox = ({ link, imgUrl, title }) => {
  return (
    <div>
      <a href={link}>
        <div className="box1">
          <img src={require(`../../resources/${imgUrl}`)} className="logos" />
          <p><b>{title}</b></p>
        </div>
      </a>
    </div>
  );
};

export default MenuBox;
