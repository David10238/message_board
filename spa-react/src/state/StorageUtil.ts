import { loadLanguage } from "./LanguageState";

export function loadStates() {
  loadLanguage();
}

export function localSave(tag: string, obj: any | null) {
  save(localStorage, tag, obj);
}

export function localLoad<TYPE>(tag: string) {
  return load<TYPE>(localStorage, tag);
}

export function sessionSave(tag: string, obj: any | null) {
  save(localStorage, tag, obj);
}

export function sessionLoad<TYPE>(tag: string) {
  return load<TYPE>(localStorage, tag);
}

function save(storage: Storage, tag: string, obj: any | null) {
  try {
    storage.setItem(tag, JSON.stringify(obj));
  } catch (e) {
    console.error(e);
  }
}

function load<TYPE>(storage: Storage, tag: string): TYPE | null {
  try {
    const stateStr = storage.getItem(tag);
    const state = stateStr ? JSON.parse(stateStr) : undefined;
    return state == undefined ? null : state;
  } catch (e) {
    console.error(e);
    return null;
  }
}
