import React from "react";
import "./ProfilePage.css";

const ProfilePage = () => {
  return (
    <div>
      <div class="sidebar">
        <img
          src="https://s3.amazonaws.com/cms-assets.tutsplus.com/uploads/users/810/profiles/19338/profileImage/profile-square-extra-small.png"
          width={250}
        />
        
          <h3>Student Name</h3>
          <h4>2nd year</h4>

          <img src="https://s3.amazonaws.com/cms-assets.tutsplus.com/uploads/users/810/profiles/19338/profileImage/profile-square-extra-small.png" width={200}/>
        
      </div>

      <div class="content"></div>
    </div>
  );
};

export default ProfilePage;
