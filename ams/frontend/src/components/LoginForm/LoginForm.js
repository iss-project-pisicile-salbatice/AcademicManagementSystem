import React from "react";
import "./LoginForm.css";
import "./LoginFormAnimation";
import { Link } from "react-router";
import { useNavigate } from "react-router";

import auth from "../../usefullComponents/auth";
const LoginForm = ()  => {
  const navigate = useNavigate();
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
            required
          />
          <i class="validation">
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
            required
          />
          <i class="validation">
            <span></span>
            <span></span>
          </i>
        </p>
        <p>
            <input type="submit" id="login" value="Sign In" formAction="" onClick={
              ()=>{
                console.log("I'VE CLICKED IT");
                auth.login(()=>{
                  navigate("/");
                })
              }
              
            } />
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
