import React from "react";
import OptionalItem from "./OptionalItem";

const OptionalsList = ({ optionals, open }) => {
  console.log(open);
  console.log('lalall',optionals);
  if (open == false) {
    console.log(1);
    return null;
  }
  return (
    <div className="optionalsList">
      {optionals.map((optional) => (
        <div className="optionalItem">
          <OptionalItem optional={optional} />
        </div>
      ))}
    </div>
  );
};

export default OptionalsList;
