import React from "react";
import SyllabusItem from "./SyllabusItem";

const SyllabusList = ({ list }) => {
  return (
    <div class="syllabusContainer">
      {list.map((item) => (
        <SyllabusItem syllabus={item} />
      ))}
    </div>
  );
};

export default SyllabusList;
