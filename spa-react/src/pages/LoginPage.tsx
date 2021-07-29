import React from "react";
import { useForceUpdate } from "../reactUtil/Hooks";
import { lang, Languages, setLanguage } from "../state/LanguageState";
import "./../styles/base.scss";
import "./../styles/login.scss";

function LanguageButton(props: { language: Languages; update: () => void }) {
  function updateLang() {
    setLanguage(props.language);
    props.update();
  }
  return <button onClick={updateLang}>{props.language}</button>;
}

export default function LoginPage() {
  const forceUpdate = useForceUpdate();
  return (
    <div id="login-page">
      <div id="login-form">
        <input value={lang.auth_email} className="login-input" />
        <input value={lang.auth_password} className="login-input" />
        <button id="login-button">{lang.auth_login}</button>
        <a id="forgot-password-link" href="/forgotpassword">
          {lang.auth_forgotPassword}
        </a>
        <div id="divider" />
        <div>
          <button id="new-account-button">{lang.auth_createNew}</button>
        </div>
        <div>
          <LanguageButton language={Languages.ENGLISH} update={forceUpdate} />
          <LanguageButton language={Languages.SPANISH} update={forceUpdate} />
        </div>
      </div>
    </div>
  );
}
