import React, { useState } from "react";
import "./LoginForm.css";
import "./LoginFormAnimation";
import { Link } from "react-router";
import { useNavigate } from "react-router";

import auth from "../../usefullComponents/auth";
const LoginForm = () => {
  const navigate = useNavigate();
  const [user, SetUser] = useState({
    username: "",
    password: "",
  });

  const setresponse = (name, value) => {
    SetUser((oldValues) => ({ ...oldValues, [name]: value }));
  };

  const getUserName = (event) => {
    setresponse("username", event.target.value);
  };
  const getPassword = (event) => {
    setresponse("password", event.target.value);
  };
  const postCredantials = async () => {
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    var requestOptions = {
      method: "POST",
      headers: myHeaders,
      body: JSON.stringify(user),
      redirect: "follow",
    };

    fetch("http://localhost:8080/api/auth/signin", requestOptions)
      .then((response) => response.text())
      .then((result) => {
        try 
        {
          console.log(result);
          result = JSON.parse(result);
          localStorage.setItem('userToken', JSON.stringify(result));
          console.log(result);
        } catch (e)
        {
          console.log("Error");
        }
      })
      .catch((error) => console.log("error", error));
  };

  return (
    <div id="login-form-wrap">
      <h2 align="center">Sign In</h2>
      <form id="login-form">
        <p>
          <input
            type="text"
            id="username"
            name="username"
            placeholder="Username..."
            onChange={getUserName}
            required
          />
          <i className="validation">
            <span></span>
            <span></span>
          </i>
        </p>
        <p>
          <input
            type="password"
            id="password"
            name="password"
            placeholder="Password..."
            onChange={getPassword}
            required
          />
          <i className="validation">
            <span></span>
            <span></span>
          </i>
        </p>
        <p>
          <input
            type="submit"
            id="login"
            value="Sign In"
            formAction=""
            onClick={() => {
              postCredantials();
              console.log("I'VE CLICKED IT");
              auth.login(() => {
                navigate("/");
              });
            }}
          />
        </p>
      </form>
      <div id="create-account-wrap">
        <p>
          <a href="#">Forgot your password?</a>
        </p>
      </div>
    </div>
  );
};

export default LoginForm;
