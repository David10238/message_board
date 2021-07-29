import { localLoad, localSave } from "./StorageUtil";

interface Language {
  languageName: string;
  auth_email: string;
  auth_password: string;
  auth_login: string;
  auth_forgotPassword: string;
  auth_createNew: string;
}

const english: Language = {
  languageName: "English",
  auth_email: "Email",
  auth_password: "Password",
  auth_login: "Log in",
  auth_forgotPassword: "Forgot password?",
  auth_createNew: "Create new account",
};

const spanish: Language = {
  languageName: "Español",
  auth_email: "Correo electrónico",
  auth_password: "Contraseña",
  auth_login: "Iniciar sesión",
  auth_forgotPassword: "¿Olvidaste tu contraseña?",
  auth_createNew: "Crear cuenta nueva",
};

export enum Languages {
  ENGLISH = "English",
  SPANISH = "Español",
}

export let lang = english;

export function loadLanguage() {
  console.log("loading lagnuage");
  const fromLocal = localLoad<Languages>("language");
  if (fromLocal != null) setLanguage(fromLocal);
}

export function setLanguage(langage: Languages) {
  switch (langage) {
    case Languages.ENGLISH:
      lang = english;
      break;
    case Languages.SPANISH:
      lang = spanish;
      break;
  }
  localSave("language", langage);
}
