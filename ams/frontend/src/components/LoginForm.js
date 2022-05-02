import React from "react";
import "./LoginForm.css";
import "./LoginFormAnimation";
import { Link } from "react-router";

const LoginForm = () => {
  return (
    <div id="login-form-wrap">
      <h2>Sign In</h2>
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
          {/* <Link to={"/ProfilePage"}> */}
            <input type="submit" id="login" value="Sign In" formAction="" />
          {/* </Link> */}
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
