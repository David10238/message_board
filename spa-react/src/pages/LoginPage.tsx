import React from "react";
import "./../styles/base.scss";
import "./../styles/login.scss";

export default function LoginPage() {
  return (
    <div id="login-page">
      <div id="login-form">
        <input value="Email" className="login-input" />
        <input value="Password" className="login-input" />
        <button id="login-button">Log In</button>
        <a id="forgot-password-link" href="/forgotpassword">
          Forgot Password?
        </a>
        <div id="divider" />
        <button id="new-account-button">Create New Account</button>
      </div>
    </div>
  );
}
