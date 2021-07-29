import { loadLanguage } from "./LanguageState";

export function loadStates() {
  loadLanguage();
}

export function save(tag: string, obj: any | null) {
  try {
    localStorage.setItem(tag, JSON.stringify(obj));
  } catch (e) {
    console.error(e);
  }
}

export function load<TYPE>(tag: string): TYPE | null {
  try {
    const stateStr = localStorage.getItem(tag);
    const state = stateStr ? JSON.parse(stateStr) : undefined;
    return state == undefined ? null : state;
  } catch (e) {
    console.error(e);
    return null;
  }
}
