import React from "react";
import "./ProfilePage.css";
import Divider from '@mui/material/Divider';
import Button from '@mui/material/Button';


const ProfilePage = () => {
    return (
        <div>
            <aside>
                <img
                    src="https://s3.amazonaws.com/cms-assets.tutsplus.com/uploads/users/810/profiles/19338/profileImage/profile-square-extra-small.png"
                    width={250}
                />

                <h3>Student Name</h3>
                <h4>2nd year</h4>

                <img src="https://www.guninetwork.org/files/logo_47.jpg" width={200}/>

            </aside>
            <main>
                <div class="content">
                    <Button variant="contained"  style={{float: 'right', marginRight:"5%"}}>Edit Profile</Button>
                    <h2 align="left">About</h2>
                    <Divider/>
                    <p>LAALLALALLA</p>
                    <br/>
                    <p>LAALLALALLA</p>
                    <br/>
                    <p>LAALLALALLA</p>
                    <br/>
                    <p>LAALLALALLA</p>
                    <br/>
                    <p>LAALLALALLA</p>
                    <br/>
                </div>
            </main>

        </div>
    );
};

export default ProfilePage;
