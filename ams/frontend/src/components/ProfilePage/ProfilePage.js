import React, { useEffect, useState } from "react";
import "./ProfilePage.css";
import Divider from "@mui/material/Divider";
import Button from "@mui/material/Button";

const ProfilePage = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    const getData = async () => {
      const dataFromServer = await fetchData(1);
      setData(dataFromServer);
      console.log(dataFromServer);
    };

    getData();
  }, []);

  const fetchData = async (id) => {
    const res = await fetch(`https://localhost:8080/students/${id}`);
    const data = await res.json();
    console.log(data);
    return data;
  };

  return (
    <div>
      <aside>
        <img
          src="https://s3.amazonaws.com/cms-assets.tutsplus.com/uploads/users/810/profiles/19338/profileImage/profile-square-extra-small.png"
          width={250}
        />

        <h3>Student Name</h3>
        <h4>2nd year</h4>

        <img src="https://www.guninetwork.org/files/logo_47.jpg" width={200} />
      </aside>
      <main>
        <div class="content" style={{ height: "100vh" }}>
          <Button
            variant="contained"
            style={{
              float: "right",
              marginRight: "5%",
              transform: "scale(1.2)",
            }}
            onClick={() => fetchData(1)}
          >
            Edit Profile
          </Button>
          <h2 align="left">About</h2>
          <Divider />
          <div class="userProfileInfoFields">
            <p>
              <b>Email</b>
            </p>
            <br />
            <p>
              <b>Unique personal code</b>
            </p>
            <br />
            <p>
              <b>Faculty</b>
            </p>
            <br />
            <p>
              <b>Form of study</b>
            </p>
            <br />
            <p>
              <b>Phone </b>
            </p>
            <br />
          </div>
          <div class="userProfileInfoFieldsData">
            <p>
              <b>ianisteja@catuni.edu</b>
            </p>
            <br />
            <p>
              <b>000000000000</b>
            </p>
            <br />
            <p>
              <b>Computer Science</b>
            </p>
            <br />
            <p>
              <b>Day form</b>
            </p>
            <br />
            <p>
              <b>123456789</b>
            </p>
            <br />
          </div>
        </div>
      </main>
    </div>
  );
};

export default ProfilePage;
