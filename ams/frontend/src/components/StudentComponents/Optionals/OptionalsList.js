import React from "react";
import OptionalItem from "./OptionalItem";

const OptionalsList = ({ optionals, open }) => {
  const handleChange = () =>{
    open = true;
  }

  if (open == false) {
    return null;
  }
  if (optionals.length == 0) {
    return <div>There are 0 optionals in this speciality</div>;
  }
  return (
    <div className="optionalsList">
      {optionals.map((optional) => (
        <div>
          <div className="optionalItem">
            <OptionalItem optional={optional} />
          </div>
          {optionals.map((optional, index) => (
            <div className="check1">
              <label>{index + 1}</label>
              <input
                // checked={values.engine}
                // value={values.engine}
                // id="engine"
                onChange={handleChange}
                type="checkbox"
              />
            </div>
          ))}
        </div>
      ))}
    </div>
  );
};

export default OptionalsList;
